package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.dtos.SchedaPersonaggio;
import it.unibo.dungeonsql.models.*;
import it.unibo.dungeonsql.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SchedaService {

    /**
     * Recupera tutte le informazioni correlate a una scheda personaggio,
     * restituendo un DTO completo pronto per essere visualizzato nella grafica.
     */
    public SchedaPersonaggio getSchedaCompleta(String codiceScheda) {
        // try-with-resources: chiude automaticamente la sessione Hibernate alla fine
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            Personaggio personaggio = session.get(Personaggio.class, codiceScheda);
            
            if (personaggio == null) {
                return null; // O gestisci l'eccezione se la scheda non esiste
            }

            List<Possesso> caratteristiche = session.createQuery(
                "FROM Possesso p WHERE p.id.codiceScheda = :codice", Possesso.class)
                .setParameter("codice", codiceScheda)
                .getResultList();

            List<Capacita> capacita = session.createQuery(
                "FROM Capacita c WHERE c.id.codiceScheda = :codice", Capacita.class)
                .setParameter("codice", codiceScheda)
                .getResultList();

            List<Progresso> progressi = session.createQuery(
                "FROM Progresso pr WHERE pr.id.codiceScheda = :codice", Progresso.class)
                .setParameter("codice", codiceScheda)
                .getResultList();

            List<Object[]> inventario = session.createQuery(
                "SELECT o.nome, i.quantita, o.tipoOggetto, o.peso, o.descrizione " +
                "FROM Inventario i JOIN Oggetto o ON i.id.codiceOggetto = o.codiceOggetto " +
                "WHERE i.id.codiceScheda = :codice", Object[].class)
                .setParameter("codice", codiceScheda)
                .getResultList();

            List<Magia> magie = session.createQuery(
                "SELECT m FROM Conoscenza c JOIN Magia m ON c.id.codiceMagia = m.codiceMagia " +
                "WHERE c.id.codiceScheda = :codice", Magia.class)
                .setParameter("codice", codiceScheda)
                .getResultList();

            return SchedaPersonaggio.builder()
                    .personaggio(personaggio)
                    .caratteristiche(caratteristiche)
                    .capacita(capacita)
                    .progressi(progressi)
                    .inventario(inventario)
                    .magie(magie)
                    .build();
        }
    }
}