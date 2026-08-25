package it.unibo.dungeonsql.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unibo.dungeonsql.models.Utente;
import it.unibo.dungeonsql.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


public class SignInService {

    private boolean invalidString(String field) {
        return field == null || field.trim().isEmpty();
    }

    public boolean registraUtente(String username, String email, String passwordRaw) {
        if (invalidString(username) || 
            invalidString(email) || 
            invalidString(passwordRaw)) {
            return false;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Utente> root = cq.from(Utente.class);
            cq.select(cb.count(root)).where(cb.equal(root.get("username"), username));
            
            Long count = session.createQuery(cq).uniqueResult();
            if (count != null && count > 0) {
                return false;
            }

            String passwordHash = calcolaSha512(passwordRaw);

            Utente nuovoUtente = new Utente();
            nuovoUtente.setUsername(username);
            nuovoUtente.setPassword(passwordHash);
            nuovoUtente.setEmail(email);

            Transaction tx = session.beginTransaction();
            session.persist(nuovoUtente);
            tx.commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String calcolaSha512(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(input.getBytes());
            
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Errore critico: Algoritmo SHA-512 non trovato!", e);
        }
    }
}
