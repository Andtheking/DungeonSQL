package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.models.Campagna;
import it.unibo.dungeonsql.models.Utente;
import it.unibo.dungeonsql.models.ids.CampagnaId;
import it.unibo.dungeonsql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class CampagnaService {

    /**
     * Tenta di creare una nuova campagna nel database.
     * Restituisce true se l'operazione ha successo, false altrimenti.
     */
    public boolean creaCampagna(String usernameMaster, String nomeCampagna, String descrizione) {
        // Validazione base
        if (usernameMaster == null || usernameMaster.trim().isEmpty() ||
            nomeCampagna == null || nomeCampagna.trim().isEmpty() ||
            descrizione == null || descrizione.trim().isEmpty()) {
            return false;
        }

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // 1. Verifica che il Master esista
            Utente master = session.get(Utente.class, usernameMaster);
            if (master == null) {
                System.err.println("Errore: Utente Master non trovato.");
                return false;
            }

            // 2. Creazione ID e Entity
            CampagnaId campagnaId = new CampagnaId(usernameMaster, nomeCampagna);
            
            Campagna campagna = Campagna.builder()
                    .id(campagnaId)
                    .master(master)
                    .descrizione(descrizione)
                    .dataInizio(LocalDate.now())
                    .build();

            // 3. Salvataggio
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