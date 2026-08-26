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

                
                Utente creatore = session.get(Utente.class, creUsername);
                Background background = session.get(Background.class, appNome);
                Razza razza = session.get(Razza.class, fonNome);
                Classe classe = session.get(Classe.class, nomeClasse);
                Campagna campagna = session.get(Campagna.class, new CampagnaId(master, nomeCampagna));

                if (creatore == null || classe == null) {
                    System.err.println("❌ Errore: Utente creatore o Classe non trovati nel database.");
                    return false;
                }

                
                Scheda scheda = Scheda.builder()
                        .nome(nome)
                        .maxHp(hp)
                        .ca(ca)
                        .taglia(taglia)
                        .creatore(creatore)
                        .campagna(campagna)
                        .build();
                session.persist(scheda);
                session.flush(); 

                int codiceScheda = scheda.getCodiceScheda();

                
                Personaggio personaggio = Personaggio.builder()
                        .scheda(scheda)
                        .allineamento(allineamento)
                        .hp(hp)
                        .expAccumulata(0)
                        .background(background)
                        .razza(razza)
                        .build();
                session.persist(personaggio);

                
                if (punteggiCaratteristiche != null) {
                    for (Map.Entry<String, Integer> entry : punteggiCaratteristiche.entrySet()) {
                        String nomeCaratteristica = entry.getKey();
                        int punteggio = entry.getValue();
                        boolean compSalvezza = competenzeSalvezza != null && competenzeSalvezza.getOrDefault(nomeCaratteristica, false);

                        
                        
                        Caratteristica caratteristica = session.get(Caratteristica.class, nomeCaratteristica);

                        PossessoId possessoId = new PossessoId(nomeCaratteristica, codiceScheda); 
                        
                        Possesso possesso = Possesso.builder()
                                .id(possessoId)
                                .scheda(scheda)                      
                                .caratteristica(caratteristica)      
                                .punteggio(punteggio)
                                .competenzaSalvezza(compSalvezza)
                                .build();
                        session.persist(possesso);
                    }
                }

                
                ProgressoId progressoId = new ProgressoId(codiceScheda, nomeClasse);
                Progresso progresso = Progresso.builder()
                        .id(progressoId)
                        .personaggio(personaggio)
                        .classe(classe)
                        .livello(1)
                        .build();
                session.persist(progresso);

                
                if (abilitaSelezionate != null) {
                    for (Map.Entry<String, String> entry : abilitaSelezionate.entrySet()) {
                        String nomeCapacita = entry.getKey();
                        String livelloCapacita = entry.getValue();

                        Skill skill = session.get(Skill.class, nomeCapacita);

                        if (List.of("N", "C", "E").contains(livelloCapacita)) {
                            CapacitaId capacitaId = new CapacitaId(nomeCapacita, codiceScheda);
                            Capacita capacita = Capacita.builder()
                                    .id(capacitaId)
                                    .scheda(scheda) 
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

    

    public List<String> getAllNomiRazze() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            return session.createQuery("SELECT r.id FROM Razza r", String.class).list();
        } catch (Exception e) {
            System.err.println("Errore nel recupero delle razze: " + e.getMessage());
            return List.of(); 
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
            
            return session.createQuery("SELECT DISTINCT c.master.username FROM Campagna c", String.class).list();
        } catch (Exception e) {
            System.err.println("Errore nel recupero dei master: " + e.getMessage());
            return List.of();
        }
    }

    public List<String> getAllNomiCampagne() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            
            return session.createQuery("SELECT DISTINCT c.id.nome FROM Campagna c", String.class).list();
        } catch (Exception e) {
            System.err.println("Errore nel recupero delle campagne: " + e.getMessage());
            return List.of();
        }
    }

}