package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;

import java.time.LocalDate;

public class CombattimentoService {

    /**
     * Avvia un nuovo combattimento inserendo il primo turno e la prima istanza partecipante.
     * Restituisce true se l'operazione ha successo, false altrimenti.
     */
    public boolean avviaCombattimento(String username, String campagna, LocalDate data, int numCombattimento,
                                      int numeroIstanza, int iniziativa, int hp, 
                                      boolean isPersonaggio, String codiceScheda) {

        if (username == null || username.trim().isEmpty() ||
            campagna == null || campagna.trim().isEmpty() ||
            data == null || codiceScheda == null || codiceScheda.trim().isEmpty()) {
            return false;
        }

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            
            MutationQuery qCombattimento = session.createNativeQuery(
                "INSERT INTO COMBATTIMENTO (Username, Nome, DataSvolgimento, NumCombattimento) " +
                "VALUES (:user, :campagna, :data, :numComb)", void.class);
            qCombattimento.setParameter("user", username);
            qCombattimento.setParameter("campagna", campagna);
            qCombattimento.setParameter("data", data);
            qCombattimento.setParameter("numComb", numCombattimento);
            qCombattimento.executeUpdate();

            
            MutationQuery qTurno = session.createNativeQuery(
                "INSERT INTO TURNO (Username, Nome, DataSvolgimento, NumCombattimento, NumTurno) " +
                "VALUES (:user, :campagna, :data, :numComb, 1)", void.class);
            qTurno.setParameter("user", username);
            qTurno.setParameter("campagna", campagna);
            qTurno.setParameter("data", data);
            qTurno.setParameter("numComb", numCombattimento);
            qTurno.executeUpdate();

            
            MutationQuery qIstanza = session.createNativeQuery(
                "INSERT INTO ISTANZA_COMB (Username, Nome, DataSvolgimento, NumCombattimento, Numero, Iniziativa, HP) " +
                "VALUES (:user, :campagna, :data, :numComb, :numero, :iniziativa, :hp)", void.class);
            qIstanza.setParameter("user", username);
            qIstanza.setParameter("campagna", campagna);
            qIstanza.setParameter("data", data);
            qIstanza.setParameter("numComb", numCombattimento);
            qIstanza.setParameter("numero", numeroIstanza);
            qIstanza.setParameter("iniziativa", iniziativa);
            qIstanza.setParameter("hp", hp);
            qIstanza.executeUpdate();

            
            String sqlXor = "INSERT INTO ISTANZA_XOR (Username, Nome, DataSvolgimento, NumCombattimento, Numero, I_P_CodiceScheda, I_M_CodiceScheda) " +
                            "VALUES (:user, :campagna, :data, :numComb, :numero, :pCodice, :mCodice)";
            MutationQuery qXor = session.createNativeQuery(sqlXor, void.class);
            qXor.setParameter("user", username);
            qXor.setParameter("campagna", campagna);
            qXor.setParameter("data", data);
            qXor.setParameter("numComb", numCombattimento);
            qXor.setParameter("numero", numeroIstanza);
            
            
            if (isPersonaggio) {
                qXor.setParameter("pCodice", codiceScheda);
                qXor.setParameter("mCodice", null);
            } else {
                qXor.setParameter("pCodice", null);
                qXor.setParameter("mCodice", codiceScheda);
            }
            qXor.executeUpdate();

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
