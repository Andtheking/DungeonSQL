package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;

public class PersonaggioService {

    /**
     * Esegue la transazione atomica per creare un personaggio completo.
     * Restituisce true in caso di successo, false in caso di errore.
     */
    public boolean creaPersonaggioCompleto(
            String codiceScheda, String nome, int hp, int ca, String taglia, String creUsername,
            String allineamento, String fonNome, String appNome,
            int punteggioForza, boolean competenzaForza,
            String nomeClasse,
            String nomeCapacita, int livelloCapacita,
            String codiceOggetto) {

        // Validazione base dei campi obbligatori testuali
        if (codiceScheda == null || codiceScheda.trim().isEmpty() ||
            nome == null || nome.trim().isEmpty() ||
            creUsername == null || creUsername.trim().isEmpty() ||
            nomeClasse == null || nomeClasse.trim().isEmpty()) {
            return false;
        }

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // 1. INSERT INTO SCHEDA
            MutationQuery qScheda = session.createNativeQuery(
                "INSERT INTO SCHEDA (CodiceScheda, Nome, MaxHP, CA, Taglia, CRE_Username, PRE_Username, PRE_Nome) " +
                "VALUES (:codice, :nome, :maxHp, :ca, :taglia, :creUser, NULL, NULL)", void.class);
            qScheda.setParameter("codice", codiceScheda);
            qScheda.setParameter("nome", nome);
            qScheda.setParameter("maxHp", hp);
            qScheda.setParameter("ca", ca);
            qScheda.setParameter("taglia", taglia);
            qScheda.setParameter("creUser", creUsername);
            qScheda.executeUpdate();

            // 2. INSERT INTO PERSONAGGIO
            MutationQuery qPersonaggio = session.createNativeQuery(
                "INSERT INTO PERSONAGGIO (CodiceScheda, Allineamento, HP, ExpAccumulata, FON_Nome, APP_Nome) " +
                "VALUES (:codice, :allineamento, :hp, 0, :fonNome, :appNome)", void.class);
            qPersonaggio.setParameter("codice", codiceScheda);
            qPersonaggio.setParameter("allineamento", allineamento);
            qPersonaggio.setParameter("hp", hp);
            qPersonaggio.setParameter("fonNome", fonNome);
            qPersonaggio.setParameter("appNome", appNome);
            qPersonaggio.executeUpdate();

            // 3. INSERT INTO POSSESSO (Forza)
            MutationQuery qPossesso = session.createNativeQuery(
                "INSERT INTO POSSESSO (Nome, CodiceScheda, Punteggio, CompetenzaSalvezza) " +
                "VALUES ('Forza', :codice, :punteggio, :competenza)", void.class);
            qPossesso.setParameter("codice", codiceScheda);
            qPossesso.setParameter("punteggio", punteggioForza);
            qPossesso.setParameter("competenza", competenzaForza);
            qPossesso.executeUpdate();

            // 4. INSERT INTO PROGRESSO (Livello 1)
            MutationQuery qProgresso = session.createNativeQuery(
                "INSERT INTO PROGRESSO (CodiceScheda, RIF_NomeClasse, Livello, NomeClasse, NomeSottoclasse) " +
                "VALUES (:codice, :rifClasse, 1, :nomeClasse, NULL)", void.class);
            qProgresso.setParameter("codice", codiceScheda);
            qProgresso.setParameter("rifClasse", nomeClasse);
            qProgresso.setParameter("nomeClasse", nomeClasse);
            qProgresso.executeUpdate();

            // 5. INSERT INTO CAPACITA
            MutationQuery qCapacita = session.createNativeQuery(
                "INSERT INTO CAPACITA (CodiceScheda, Nome, LivelloCapacita) " +
                "VALUES (:codice, :nomeCapacita, :livelloCapacita)", void.class);
            qCapacita.setParameter("codice", codiceScheda);
            qCapacita.setParameter("nomeCapacita", nomeCapacita);
            qCapacita.setParameter("livelloCapacita", livelloCapacita);
            qCapacita.executeUpdate();

            // 6. INSERT INTO INVENTARIO
            MutationQuery qInventario = session.createNativeQuery(
                "INSERT INTO INVENTARIO (CodiceOggetto, CodiceScheda, Quantita) " +
                "VALUES (:codiceOggetto, :codice, 1)", void.class);
            qInventario.setParameter("codiceOggetto", codiceOggetto);
            qInventario.setParameter("codice", codiceScheda);
            qInventario.executeUpdate();

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