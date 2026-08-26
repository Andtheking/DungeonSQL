package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;

import java.time.LocalDate;

public class DiarioService {

    
    public DiarioService() {
        try {
            
            
            HibernateUtil.getSessionFactory();
        } catch (Exception e) {
            System.err.println("❌ Errore critico durante l'inizializzazione di Hibernate: " + e.getMessage());
        }
    }

    /**
     * Aggiorna il diario della sessione e inserisce eventuali tag.
     * Restituisce true se l'operazione va a buon fine, false altrimenti.
     */
    public boolean salvaDiarioETag(String usernameMaster, String nomeCampagna, LocalDate dataSessione, 
                                   String testoDiario, String tagScheda, String tagOggetto, String tagMagia) {
        
        
        if (usernameMaster == null || usernameMaster.trim().isEmpty() ||
            nomeCampagna == null || nomeCampagna.trim().isEmpty() ||
            dataSessione == null || 
            testoDiario == null || testoDiario.trim().isEmpty()) {
            return false;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = null;
            
            try {
                tx = session.beginTransaction();

                
                MutationQuery insertSessione = session.createNativeQuery(
                        "INSERT INTO SESSIONE (Username, NomeCampagna, DataSvolgimento, Diario) " +
                        "VALUES (:user, :campagna, :data, :diario)", void.class);
                
                insertSessione.setParameter("user", usernameMaster);
                insertSessione.setParameter("campagna", nomeCampagna);
                insertSessione.setParameter("data", dataSessione);
                insertSessione.setParameter("diario", testoDiario);
                
                
                insertSessione.executeUpdate();

                
                if (tagScheda != null && !tagScheda.trim().isEmpty()) {
                    eseguiInsertTag(session, "TAG_PARTECIPANTE", "CodiceScheda", 
                                    usernameMaster, nomeCampagna, dataSessione, tagScheda.trim());
                }

                
                if (tagOggetto != null && !tagOggetto.trim().isEmpty()) {
                    eseguiInsertTag(session, "TAG_OGGETTO", "CodiceOggetto", 
                                    usernameMaster, nomeCampagna, dataSessione, tagOggetto.trim());
                }

                
                if (tagMagia != null && !tagMagia.trim().isEmpty()) {
                    eseguiInsertTag(session, "TAG_MAGIA", "CodiceMagia", 
                                    usernameMaster, nomeCampagna, dataSessione, tagMagia.trim());
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