package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;

import java.time.LocalDate;

public class DiarioService {

    /**
     * Aggiorna il diario della sessione e inserisce eventuali tag.
     * Restituisce true se l'operazione va a buon fine, false altrimenti.
     */
    public boolean salvaDiarioETag(String usernameMaster, String nomeCampagna, LocalDate dataSessione, 
                                   String testoDiario, String tagScheda, String tagOggetto, String tagMagia) {
        
        // Validazione campi obbligatori
        if (usernameMaster == null || usernameMaster.trim().isEmpty() ||
            nomeCampagna == null || nomeCampagna.trim().isEmpty() ||
            dataSessione == null || 
            testoDiario == null || testoDiario.trim().isEmpty()) {
            return false;
        }

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // 1. Update del Diario
            MutationQuery updateSessione = session.createNativeQuery(
                    "UPDATE SESSIONE SET Diario = :diario " +
                    "WHERE Username = :user AND Nome = :campagna AND DataSvolgimento = :data", void.class);
            updateSessione.setParameter("diario", testoDiario);
            updateSessione.setParameter("user", usernameMaster);
            updateSessione.setParameter("campagna", nomeCampagna);
            updateSessione.setParameter("data", dataSessione);
            
            int updatedRows = updateSessione.executeUpdate();
            if (updatedRows == 0) {
                System.err.println("Errore: Nessuna sessione trovata con i dati forniti.");
                tx.rollback();
                return false;
            }

            // 2. Insert Tag Partecipante
            if (tagScheda != null && !tagScheda.trim().isEmpty()) {
                eseguiInsertTag(session, "TAG_PARTECIPANTE", "CodiceScheda", 
                                usernameMaster, nomeCampagna, dataSessione, tagScheda.trim());
            }

            // 3. Insert Tag Oggetto
            if (tagOggetto != null && !tagOggetto.trim().isEmpty()) {
                eseguiInsertTag(session, "TAG_OGGETTO", "CodiceOggetto", 
                                usernameMaster, nomeCampagna, dataSessione, tagOggetto.trim());
            }

            // 4. Insert Tag Magia
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
            e.printStackTrace();
            return false;
        }
    }

    // Metodo helper interno per non ripetere il codice degli INSERT
    private void eseguiInsertTag(Session session, String tabella, String colonnaCodice, 
                                 String username, String campagna, LocalDate data, String codiceValore) {
        String sql = String.format(
            "INSERT INTO %s (Username, Nome, DataSvolgimento, %s) VALUES (:user, :campagna, :data, :codice)",
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