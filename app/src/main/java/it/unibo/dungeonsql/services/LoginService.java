package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.models.Utente; // Assicurati che punti alla tua entity
import it.unibo.dungeonsql.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginService {

    public boolean autentica(String username, String passwordRaw) {
        if (username == null || username.trim().isEmpty() || passwordRaw == null) {
            return false;
        }

        String passwordHash = calcolaSha512(passwordRaw);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Utente> root = cq.from(Utente.class);
            cq.select(cb.count(root))
              .where(cb.and(
                  cb.equal(root.get("username"), username),
                  cb.equal(root.get("password"), passwordHash)
              ));
            Long count = session.createQuery(cq).uniqueResult();
            return count != null && count > 0;

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