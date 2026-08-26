package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.models.Campagna;
import it.unibo.dungeonsql.models.Utente;
import it.unibo.dungeonsql.models.ids.CampagnaId;
import it.unibo.dungeonsql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class CampagnaService {

    public List<Campagna> getCampagneByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Campagna> campagne = session.createQuery(
                "FROM Campagna c WHERE c.master.username = :username",
                Campagna.class)
                .setParameter("username", username)
                .getResultList();

            return campagne;
        }
    }

    public boolean isMaster(Campagna campagna, String username) {
        return campagna.getId().getUsernameMaster().equals(username);
    }
    
    public List<Campagna> getCampagnaGiocataByUtente(String username) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        
        String hql = "SELECT DISTINCT s.campagna FROM Personaggio p " +
                     "JOIN p.scheda s " +
                     "WHERE s.creatore.username = :username " +
                     "AND s.campagna.master.username != :username";
                     
        Query<Campagna> query = session.createQuery(hql, Campagna.class);
        query.setParameter("username", username);
        
        return query.list();
    }
}

    public boolean creaCampagna(String usernameMaster, String nomeCampagna, String descrizione) {
        if (usernameMaster == null || usernameMaster.trim().isEmpty() ||
            nomeCampagna == null || nomeCampagna.trim().isEmpty() ||
            descrizione == null || descrizione.trim().isEmpty()) {
            return false;
        }

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Utente master = session.get(Utente.class, usernameMaster);

            CampagnaId campagnaId = new CampagnaId(usernameMaster, nomeCampagna);
            
            Campagna campagna = Campagna.builder()
                    .id(campagnaId)
                    .master(master)
                    .descrizione(descrizione)
                    .dataInizio(LocalDate.now())
                    .build();

            session.persist(campagna);
            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}