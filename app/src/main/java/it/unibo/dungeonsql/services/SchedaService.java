package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.dtos.SchedaPersonaggio;
import it.unibo.dungeonsql.models.*;
import it.unibo.dungeonsql.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class SchedaService {

    public List<Scheda> getSchedeByUtente(String usernameLoggato) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT s FROM Scheda s JOIN FETCH s.campagna WHERE s.creatore.username = :username";
            Query<Scheda> query = session.createQuery(hql, Scheda.class);
            query.setParameter("username", usernameLoggato);
            return query.list();
        }
    }

    public SchedaPersonaggio getSchedaCompleta(int codiceScheda) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            Scheda scheda = session.get(Scheda.class, codiceScheda);
            Personaggio personaggio = session.get(Personaggio.class, codiceScheda);
            
            if (scheda != null) {
                Hibernate.initialize(scheda.getCreatore());
                Hibernate.initialize(scheda.getCampagna());
            }

            if (personaggio != null) {
                Hibernate.initialize(personaggio.getBackground());
                Hibernate.initialize(personaggio.getRazza());
            }

            List<Possesso> caratteristiche = session.createQuery(
                "FROM Possesso p WHERE p.id.codiceScheda = :codice", Possesso.class)
                .setParameter("codice", codiceScheda).getResultList();

            List<Capacita> capacita = session.createQuery(
                "FROM Capacita c WHERE c.id.codiceScheda = :codice", Capacita.class)
                .setParameter("codice", codiceScheda).getResultList();

            List<Progresso> progressi = session.createQuery(
                "FROM Progresso pr WHERE pr.id.codiceScheda = :codice", Progresso.class)
                .setParameter("codice", codiceScheda).getResultList();

            
            for (Progresso pr : progressi) {
                Hibernate.initialize(pr.getClasse());
                Hibernate.initialize(pr.getSottoclasse());
                
                
                Hibernate.initialize(pr.getAbilitazioniRisorse());
                if (pr.getAbilitazioniRisorse() != null) {
                    for (AbilitazioneRisorsa ar : pr.getAbilitazioniRisorse()) {
                        Hibernate.initialize(ar.getRisorsaClasse()); 
                    }
                }

                
                Hibernate.initialize(pr.getAbilitazioniTratti());
                if (pr.getAbilitazioniTratti() != null) {
                    for (var at : pr.getAbilitazioniTratti()) {
                        Hibernate.initialize(at.getTrattoClasse()); 
                    }
                }
            }

            List<RigaInventario> inventario = session.createQuery(
                "SELECT new it.unibo.dungeonsql.services.SchedaService$RigaInventario(" +
                "o.nome, i.quantita, o.tipoOggetto, o.peso, o.descrizione, o.danno) " +
                "FROM Inventario i " +
                "JOIN Oggetto o ON i.id.codiceOggetto = o.codiceOggetto " +
                "WHERE i.id.codiceScheda = :codice",
                RigaInventario.class)
                .setParameter("codice", codiceScheda)
                .getResultList();

            List<Magia> magie = session.createQuery(
                "SELECT m FROM Conoscenza c JOIN Magia m ON c.id.codiceMagia = m.codiceMagia " +
                "WHERE c.id.codiceScheda = :codice", Magia.class)
                .setParameter("codice", codiceScheda).getResultList();

            return SchedaPersonaggio.builder()
                    .scheda(scheda)
                    .personaggio(personaggio)
                    .campagna(scheda != null ? scheda.getCampagna() : null)
                    .creatore(scheda != null ? scheda.getCreatore() : null)
                    .caratteristiche(caratteristiche)
                    .capacita(capacita)
                    .progressi(progressi)
                    .inventario(inventario)
                    .magie(magie)
                    .build();
        }
    }

    public record RigaInventario(String nome, int quantita, String tipo, double peso, String descrizione, String danno) {}
}