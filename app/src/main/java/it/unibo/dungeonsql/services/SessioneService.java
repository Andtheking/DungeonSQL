package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.models.Campagna;
import it.unibo.dungeonsql.models.Sessione;
import it.unibo.dungeonsql.util.HibernateUtil;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;

import java.time.LocalDate;
import java.util.List;

public class SessioneService {

    
    public SessioneService() {
        try {
            HibernateUtil.getSessionFactory();
        } catch (Exception e) {
            System.err.println("❌ Errore critico durante l'inizializzazione di Hibernate: " + e.getMessage());
        }
    }

    public Sessione getSessioneByCampagnaEData(Campagna campagna, LocalDate datasvolgimento) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Sessione sessione = session.createQuery(
                "FROM Sessione s WHERE s.campagna = :campagna AND s.datasvolgimento = :datasvolgimento",
                Sessione.class)
                .setParameter("campagna", campagna)
                .setParameter("datasvolgimento", datasvolgimento)
                .uniqueResult();

            Hibernate.initialize(sessione.getCampagna());

            return sessione;
        }
    }

    public List<Sessione> getSessioniByCampagna(Campagna campagna) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Sessione> sessione = session.createQuery(
                "FROM Sessione s JOIN FETCH s.campagna WHERE s.campagna = :campagna",
                Sessione.class)
                .setParameter("campagna", campagna)
                .getResultList();


            return sessione;
        }
    }

    /**
     * Aggiorna il diario della sessione e inserisce eventuali tag.
     * Restituisce true se l'operazione va a buon fine, false altrimenti.
     */
    public boolean salvaDiarioETag(String usernameMaster, Campagna campagna, LocalDate dataSessione, 
                               String testoDiario, String tagScheda, String tagOggetto, String tagMagia) {
        // 1. Corretto il controllo: rimosso .trim().isEmpty() su campagna
        if (usernameMaster == null || usernameMaster.trim().isEmpty() ||
            campagna == null || 
            dataSessione == null || 
            testoDiario == null || testoDiario.trim().isEmpty()) {
            return false;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();

                // 2. Estraiamo il nome della campagna come stringa per passarla alla query SQL.
                // NOTA: Adatta 'getId().getNome()' in base a come hai chiamato i getter nella tua classe Campagna!
                // Se non hai la chiave composta, potrebbe essere semplicemente campagna.getNome()
                String nomeCampagnaStr = campagna.getId().getNome(); 

                MutationQuery insertSessione = session.createNativeQuery(
                        "INSERT INTO SESSIONE (Username, NomeCampagna, DataSvolgimento, Diario) " +
                        "VALUES (:user, :campagna, :data, :diario)", void.class);
                
                insertSessione.setParameter("user", usernameMaster);
                insertSessione.setParameter("campagna", nomeCampagnaStr); // <-- Passiamo la stringa, non l'oggetto
                insertSessione.setParameter("data", dataSessione);
                insertSessione.setParameter("diario", testoDiario);
                
                insertSessione.executeUpdate();

                // 3. Passiamo la stringa 'nomeCampagnaStr' al metodo eseguiInsertTag
                // (Assicurati che eseguiInsertTag accetti una String. Se accetta un oggetto Campagna, 
                // rimetti semplicemente 'campagna' al posto di 'nomeCampagnaStr')
                if (tagScheda != null && !tagScheda.trim().isEmpty()) {
                    eseguiInsertTag(session, "TAG_PARTECIPANTE", "CodiceScheda", 
                                    usernameMaster, nomeCampagnaStr, dataSessione, tagScheda.trim());
                }

                if (tagOggetto != null && !tagOggetto.trim().isEmpty()) {
                    eseguiInsertTag(session, "TAG_OGGETTO", "CodiceOggetto", 
                                    usernameMaster, nomeCampagnaStr, dataSessione, tagOggetto.trim());
                }

                if (tagMagia != null && !tagMagia.trim().isEmpty()) {
                    eseguiInsertTag(session, "TAG_MAGIA", "CodiceMagia", 
                                    usernameMaster, nomeCampagnaStr, dataSessione, tagMagia.trim());
                }

                tx.commit();
                return true;

            } catch (Exception e) {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
                System.err.println("❌ ERRORE SQL durante l'inserimento della sessione: ");
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            System.err.println("❌ Errore critico nell'apertura della sessione DB: " + e.getMessage());
            return false;
        }
    }

    
    private void eseguiInsertTag(Session session, String tabella, String colonnaCodice, 
                                 String username, String campagna, LocalDate data, String codiceValore) {
        String sql = String.format(
            "INSERT INTO %s (Username, NomeCampagna, DataSvolgimento, %s) VALUES (:user, :campagna, :data, :codice)",
            tabella, colonnaCodice
        );
        MutationQuery insertQuery = session.createNativeQuery(sql, void.class);
        insertQuery.setParameter("user", username);
        insertQuery.setParameter("campagna", campagna);
        insertQuery.setParameter("data", data);
        insertQuery.setParameter("codice", codiceValore);
        insertQuery.executeUpdate();
    }
}