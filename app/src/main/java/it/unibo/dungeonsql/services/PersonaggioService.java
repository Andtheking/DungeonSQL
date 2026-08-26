package it.unibo.dungeonsql.services;

import it.unibo.dungeonsql.models.*;
import it.unibo.dungeonsql.models.ids.*;
import it.unibo.dungeonsql.util.HibernateUtil;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonaggioService {

    public boolean creaPersonaggioCompleto(
            String nome, int hp, int ca, String taglia, String creUsername,
            String allineamento, String fonNome, String appNome,
            Map<String, Integer> punteggiCaratteristiche,
            Map<String, Boolean> competenzeSalvezza,
            String nomeClasse,
            Map<String, String> abilitaSelezionate,
            String nomeCampagna, String master) {

        if (nome == null || nome.trim().isEmpty() ||
            creUsername == null || creUsername.trim().isEmpty() ||
            nomeClasse == null || nomeClasse.trim().isEmpty()) {
            return false;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();

                // 1. Recuperiamo le entità esterne di riferimento (Foreign Keys)
                Utente creatore = session.get(Utente.class, creUsername);
                Background background = session.get(Background.class, appNome);
                Razza razza = session.get(Razza.class, fonNome);
                Classe classe = session.get(Classe.class, nomeClasse);
                Campagna campagna = session.get(Campagna.class, new CampagnaId(master, nomeCampagna));

                if (creatore == null || classe == null) {
                    System.err.println("❌ Errore: Utente creatore o Classe non trovati nel database.");
                    return false;
                }

                // 2. SCHEDA: Salviamo prima questa per generare il SERIAL
                Scheda scheda = Scheda.builder()
                        .nome(nome)
                        .maxHp(hp)
                        .ca(ca)
                        .taglia(taglia)
                        .creatore(creatore)
                        .campagna(campagna)
                        .build();
                session.persist(scheda);
                session.flush(); // Forza Hibernate a scrivere sul DB e valorizzare l'ID

                int codiceScheda = scheda.getCodiceScheda();

                // 3. PERSONAGGIO: Ora 'codiceScheda' ha il valore corretto e non è più 0 o null
                Personaggio personaggio = Personaggio.builder()
                        .scheda(scheda)
                        .allineamento(allineamento)
                        .hp(hp)
                        .expAccumulata(0)
                        .background(background)
                        .razza(razza)
                        .build();
                session.persist(personaggio);

                // 4. POSSESSO (Le 6 caratteristiche)
                if (punteggiCaratteristiche != null) {
                    for (Map.Entry<String, Integer> entry : punteggiCaratteristiche.entrySet()) {
                        String nomeCaratteristica = entry.getKey();
                        int punteggio = entry.getValue();
                        boolean compSalvezza = competenzeSalvezza != null && competenzeSalvezza.getOrDefault(nomeCaratteristica, false);

                        // Recupera l'entità Caratteristica dal database (presumendo che la classe si chiami Caratteristica)
                        // Se la tua entità si chiama in un altro modo (es. Statistica o simile, adattane il nome)
                        Caratteristica caratteristica = session.get(Caratteristica.class, nomeCaratteristica);

                        PossessoId possessoId = new PossessoId(nomeCaratteristica, codiceScheda); 
                        
                        Possesso possesso = Possesso.builder()
                                .id(possessoId)
                                .scheda(scheda)                      // Collega la scheda
                                .caratteristica(caratteristica)      // <-- AGGIUNGI QUESTO: Risolve l'errore sul one-to-one!
                                .punteggio(punteggio)
                                .competenzaSalvezza(compSalvezza)
                                .build();
                        session.persist(possesso);
                    }
                }

                // 5. PROGRESSO (Livello classe)
                ProgressoId progressoId = new ProgressoId(codiceScheda, nomeClasse);
                Progresso progresso = Progresso.builder()
                        .id(progressoId)
                        .personaggio(personaggio)
                        .classe(classe)
                        .livello(1)
                        .build();
                session.persist(progresso);

                // 6. CAPACITÀ / ABILITÀ
                if (abilitaSelezionate != null) {
                    for (Map.Entry<String, String> entry : abilitaSelezionate.entrySet()) {
                        String nomeCapacita = entry.getKey();
                        String livelloCapacita = entry.getValue();

                        Skill skill = session.get(Skill.class, nomeCapacita);

                        if (List.of("N", "C", "E").contains(livelloCapacita)) {
                            CapacitaId capacitaId = new CapacitaId(nomeCapacita, codiceScheda);
                            Capacita capacita = Capacita.builder()
                                    .id(capacitaId)
                                    .scheda(scheda) // <-- ASSICURATI DI AVERLO ANCHE QUI
                                    .skill(skill)
                                    .livelloCapacita(livelloCapacita)
                                    .build();
                            session.persist(capacita);
                        }
                    }
                }

                tx.commit();
                return true;

            } catch (Exception e) {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
                System.err.println("❌ ERRORE DURANTE LA CREAZIONE DEL PERSONAGGIO CON HIBERNATE: ");
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            System.err.println("❌ Errore critico connessione DB: " + e.getMessage());
            return false;
        }
    }

    // --- METODI PER POPOLARE LE COMBOBOX ---

    public List<String> getAllNomiRazze() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // 'r.id' recupera automaticamente la Primary Key (il nome) della Razza
            return session.createQuery("SELECT r.id FROM Razza r", String.class).list();
        } catch (Exception e) {
            System.err.println("Errore nel recupero delle razze: " + e.getMessage());
            return List.of(); // Ritorna lista vuota in caso di errore per non far crashare la grafica
        }
    }

    public List<String> getAllNomiBackground() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT b.id FROM Background b", String.class).list();
        } catch (Exception e) {
            System.err.println("Errore nel recupero dei background: " + e.getMessage());
            return List.of();
        }
    }

    public List<String> getAllNomiClassi() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT c.id FROM Classe c", String.class).list();
        } catch (Exception e) {
            System.err.println("Errore nel recupero delle classi: " + e.getMessage());
            return List.of();
        }
    }

    public List<String> getAllUsernamesMaster() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Estraiamo tutti i Master che hanno almeno una campagna creata, senza duplicati (DISTINCT)
            return session.createQuery("SELECT DISTINCT c.master.username FROM Campagna c", String.class).list();
        } catch (Exception e) {
            System.err.println("Errore nel recupero dei master: " + e.getMessage());
            return List.of();
        }
    }

    public List<String> getAllNomiCampagne() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Adatta 'c.id.nome' con il nome reale della variabile stringa dentro la tua classe CampagnaId
            return session.createQuery("SELECT DISTINCT c.id.nome FROM Campagna c", String.class).list();
        } catch (Exception e) {
            System.err.println("Errore nel recupero delle campagne: " + e.getMessage());
            return List.of();
        }
    }

}