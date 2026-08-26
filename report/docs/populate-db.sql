-- =========================================================================
-- SCRIPT DI POPOLAMENTO (INSERT) ADATTATO AL NUOVO SCHEMA POSTGRESQL
-- VERSIONE DEMO ACCADEMICA
-- =========================================================================

INSERT INTO UTENTE (Username, Email, Password) VALUES 
('m_rossi', 'm.rossi@dnd.it', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2'),
('l_bianchi', 'l.bianchi@dnd.it', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2'),
('g_verdi', 'g.verdi@dnd.it', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2'),
('a_russo', 'a.russo@dnd.it', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2'),
('f_romano', 'f.romano@dnd.it', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2'),
('s_ferrari', 's.ferrari@dnd.it', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2'),
('m_esposito', 'm.esposito@dnd.it', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2');

INSERT INTO CAMPAGNA (UsernameMaster, Nome, Descrizione, DataInizio) VALUES 
('m_rossi', 'La Miniera Perduta', 'Campagna ufficiale D&D 5e ambientata nei Forgotten Realms', '2026-08-01'),
('l_bianchi', 'L''Ascesa dei Draghi', 'Intrigo politico e risveglio di antichi draghi nel regno di Cormyr', '2026-04-12'),
('g_verdi', 'Curse of Strahd', 'Avventura gotica nel semipiano del terrore, Barovia', '2026-09-10'),
('a_russo', 'Il Tempio Elementale', 'Esplorazione di un antico santuario dedicato ai signori elementali', '2026-04-12'),
('f_romano', 'L''Ombra del Re Folle', 'Ribellione contro un monarca corrotto dalle arti oscure', '2026-04-12');

INSERT INTO SESSIONE (Username, NomeCampagna, DataSvolgimento, Diario) 
VALUES 
('m_rossi', 'La Miniera Perduta', '2026-08-24', 'Scontro epico contro una pattuglia di goblin e un orco nelle caverne.'),
('m_rossi', 'La Miniera Perduta', '2026-08-31', 'Il party scopre l''ingresso segreto della miniera. Un bandito cerca di tendergli un agguato.'),
('g_verdi', 'Curse of Strahd', '2026-09-15', 'Arrivo al villaggio di Barovia. La nebbia si infittisce.');

INSERT INTO CARATTERISTICA (NomeCaratteristica) VALUES ('Forza'), ('Destrezza'), ('Costituzione'), ('Intelligenza'), ('Saggezza'), ('Carisma');

INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES 
('Atletica', 'Forza'),
('Acrobazia', 'Destrezza'),
('Furtività', 'Destrezza'),
('Rapidità di Mano', 'Destrezza'),
('Arcana', 'Intelligenza'),
('Indagare', 'Intelligenza'),
('Natura', 'Intelligenza'),
('Religione', 'Intelligenza'),
('Storia', 'Intelligenza'),
('Addestrare Animali', 'Saggezza'),
('Intuizione', 'Saggezza'),
('Medicina', 'Saggezza'),
('Percezione', 'Saggezza'),
('Sopravvivenza', 'Saggezza'),
('Inganno', 'Carisma'),
('Intimidazione', 'Carisma'),
('Intrattenere', 'Carisma'),
('Persuasione', 'Carisma');

INSERT INTO BACKGROUND (NomeBackground, Descrizione) VALUES 
('Soldato', 'Veterano di innumerevoli campagne militari.'),
('Criminale', 'Abile nei furti e nei contatti con la malavita.'),
('Nobile', 'Cresciuto in una famiglia che possiede ricchezza e potere.'),
('Accolito', 'Hai trascorso la vita al servizio di un tempio.');

INSERT INTO RAZZA (NomeRazza, Descrizione, VelocitaBase, Scurovisione, NomeRazzaPadre) VALUES 
('Umano', 'Versatili, intraprendenti e diffusi ovunque.', 9, TRUE, NULL),
('Elfo', 'Creature agili dotate di grazia soprannaturale.', 9, TRUE, NULL),
('Nano', 'Bassi, robusti e noti per la loro abilità in battaglia.', 7, TRUE, NULL),
('Tiefling', 'Umanoidi con un lignaggio demoniaco o diabolico.', 9, TRUE, NULL);

INSERT INTO CLASSE (NomeClasse) VALUES ('Guerriero'), ('Mago'), ('Ladro'), ('Chierico');

INSERT INTO SOTTOCLASSE (NomeClasse, NomeSottoclasse) VALUES 
('Guerriero', 'Campione'),
('Mago', 'Scuola di Evocazione'),
('Ladro', 'Assassino'),
('Chierico', 'Dominio della Vita');

INSERT INTO TRATTO_CLASSE (NomeClasse, NomeTratto, Descrizione, LivelloRichiesto) VALUES 
('Guerriero', 'Attacco Extra', 'Puoi attaccare due volte invece di una.', 5),
('Ladro', 'Attacco Furtivo', 'Infliggi 1d6 danni extra se hai vantaggio.', 1),
('Chierico', 'Incanalare Divinità', 'Usi l''energia divina per effetti magici.', 2);

INSERT INTO RISORSA_CLASSE (NomeClasse, NomeRisorsa, Recupero) VALUES 
('Guerriero', 'Seconda Vita', 'Riposo Breve o Lungo'),
('Chierico', 'Usi Incanalare Divinità', 'Riposo Breve');

INSERT INTO EFFETTO_STATO (Nome, Descrizione) VALUES 
('Avvelenato', 'Il bersaglio ha svantaggio nei tiri per colpire e prove di abilità.'),
('Invisibile', 'Non puoi essere visto senza l''ausilio di magia.'),
('Pietrificato', 'Trasformato in pietra solida. Sei inabile.');

INSERT INTO OGGETTO (CodiceOggetto, Nome, Descrizione, Peso, EffettoMagico, TipoOggetto, Danno, TipoArma, ProprietaArma, UsernameMasterCampagna, NomeCampagna) VALUES 
(1, 'Spada Lunga', 'Arma da mischia versatile', 3.00, 'No', 'Arma', '1d8 tagliente', 'Mischia', 'Versatile (1d10)', 'm_rossi', 'La Miniera Perduta'),
(2, 'Arco Lungo', 'Arco per la lunga distanza', 1.00, 'No', 'Arma', '1d8 perforante', 'Distanza', 'Gittata (45/180)', 'm_rossi', 'La Miniera Perduta'),
(3, 'Pugnale Velenoso', 'Lama verde smeraldo', 0.50, 'Si', 'Arma', '1d4 perforante', 'Mischia', 'Accurata, Leggera', 'm_rossi', 'La Miniera Perduta');

INSERT INTO OGGETTO (CodiceOggetto, Nome, Descrizione, Peso, EffettoMagico, TipoOggetto, EffettoCons, DurataCons, UsernameMasterCampagna, NomeCampagna) VALUES 
(4, 'Pozione di Cura', 'Liquido rosso vivo', 0.20, 'Si', 'Consumabile', 'Cura 2d4+2 HP', 'Istantaneo', 'm_rossi', 'La Miniera Perduta');

INSERT INTO MAGIA (CodiceMagia, Nome, Descrizione, Livello, Rituale, UsernameMasterCampagna, NomeCampagna) VALUES 
(1, 'Dardo Incantato', 'Tre dardi di energia magica colpiscono.', 1, FALSE, 'm_rossi', 'La Miniera Perduta'),
(2, 'Cura Ferite', 'Una creatura recupera punti ferita.', 1, FALSE, 'm_rossi', 'La Miniera Perduta'),
(3, 'Palla di Fuoco', 'Esplosione di 8m di raggio (8d6 fuoco).', 3, FALSE, 'm_rossi', 'La Miniera Perduta');

INSERT INTO PROPENSIONE (NomeSkill, NomeBackground) VALUES 
('Atletica', 'Soldato'),
('Intimidazione', 'Soldato'),
('Furtività', 'Criminale'),
('Inganno', 'Criminale'),
('Religione', 'Accolito');

INSERT INTO SCHEDA (CodiceScheda, Nome, MaxHP, CA, Taglia, UsernameCreatore, UsernameMaster, NomeCampagna) VALUES 
(1, 'Kaelen', 25, 16, 'Media', 'f_romano', 'm_rossi', 'La Miniera Perduta'),
(2, 'Thia Amakiir', 20, 15, 'Media', 'f_romano', 'm_rossi', 'La Miniera Perduta'),
(3, 'Thorin', 30, 18, 'Media', 's_ferrari', 'm_rossi', 'La Miniera Perduta'),
(4, 'Garret', 18, 14, 'Media', 'm_esposito', 'm_rossi', 'La Miniera Perduta'),
(5, 'Goblin', 7, 15, 'Piccola', 'm_rossi', 'm_rossi', 'La Miniera Perduta'),
(6, 'Orco', 15, 13, 'Media', 'm_rossi', 'm_rossi', 'La Miniera Perduta'),
(7, 'Bandito', 11, 12, 'Media', 'm_rossi', 'm_rossi', 'La Miniera Perduta'),
(8, 'Scheletro', 13, 13, 'Media', 'm_rossi', 'm_rossi', 'La Miniera Perduta');

INSERT INTO PERSONAGGIO (CodiceScheda, Allineamento, HP, ExpAccumulata, NomeBackground, NomeRazza) VALUES 
(1, 'Neutrale Buono', 25, 300, 'Soldato', 'Umano'),
(2, 'Caotico Buono', 20, 300, 'Soldato', 'Elfo'),
(3, 'Legale Buono', 30, 300, 'Nobile', 'Nano'),
(4, 'Caotico Neutrale', 18, 300, 'Criminale', 'Tiefling');

INSERT INTO MOSTRO (CodiceScheda, CR, ExpRilasciata, Velocita) VALUES 
(5, '1/4', 50, 9),
(6, '1', 100, 9),
(7, '1/8', 25, 9),
(8, '1/4', 50, 9);

INSERT INTO PROGRESSO (CodiceScheda, NomeClasse, Livello, NomeSottoclasse) VALUES 
(1, 'Guerriero', 2, 'Campione'),
(2, 'Guerriero', 2, 'Campione'),
(3, 'Chierico', 2, 'Dominio della Vita'),
(4, 'Ladro', 2, 'Assassino');

INSERT INTO ABILITAZIONE_TRATTO (CodiceScheda, NomeClasse, NomeTratto, MaxQuantita, Quantita) VALUES 
(4, 'Ladro', 'Attacco Furtivo', 1, 1),
(3, 'Chierico', 'Incanalare Divinità', 1, 1);

INSERT INTO POSSESSO (NomeCaratteristica, CodiceScheda, Punteggio, CompetenzaSalvezza) VALUES 
('Forza', 1, 16, TRUE), ('Destrezza', 1, 10, FALSE), ('Costituzione', 1, 10, FALSE), ('Intelligenza', 1, 10, FALSE), ('Saggezza', 1, 10, FALSE), ('Carisma', 1, 10, FALSE),
('Forza', 2, 10, FALSE), ('Destrezza', 2, 10, FALSE), ('Costituzione', 2, 10, FALSE), ('Intelligenza', 2, 10, FALSE), ('Saggezza', 2, 10, FALSE), ('Carisma', 2, 10, FALSE),
('Forza', 3, 10, FALSE), ('Destrezza', 3, 10, FALSE), ('Costituzione', 3, 10, FALSE), ('Intelligenza', 3, 10, FALSE), ('Saggezza', 3, 10, FALSE), ('Carisma', 3, 10, FALSE),
('Forza', 4, 10, FALSE), ('Costituzione', 4, 10, FALSE), ('Intelligenza', 4, 10, FALSE), ('Saggezza', 4, 10, FALSE), ('Carisma', 4, 10, FALSE), ('Destrezza', 4, 17, TRUE);

INSERT INTO CAPACITA (CodiceScheda, NomeSkill, LivelloCapacita) VALUES 
(1, 'Atletica', 'Competente'),
(2, 'Percezione', 'Maestria'),
(4, 'Furtività', 'Maestria'),
(3, 'Religione', 'Competente');

INSERT INTO APPRENDIMENTO (NomeClasse, CodiceMagia) VALUES 
('Mago', 1), ('Mago', 3), ('Chierico', 2);

INSERT INTO CONOSCENZA (CodiceMagia, CodiceScheda) VALUES (2, 3);

INSERT INTO INVENTARIO (CodiceOggetto, CodiceScheda, Quantita) VALUES 
(1, 1, 1),
(2, 2, 1),
(4, 3, 1),
(3, 4, 1);

INSERT INTO COMBATTIMENTO (Username, Nome, DataSvolgimento, NumCombattimento) VALUES 
('m_rossi', 'La Miniera Perduta', '2026-08-24', 1),
('m_rossi', 'La Miniera Perduta', '2026-08-31', 2);

INSERT INTO ISTANZA_COMB (Username, Nome, DataSvolgimento, NumCombattimento, NumeroIstanza, Iniziativa, HP, CodiceScheda) VALUES 
('m_rossi', 'La Miniera Perduta', '2026-08-24', 1, 1, 18, 25, 1),
('m_rossi', 'La Miniera Perduta', '2026-08-24', 1, 2, 16, 20, 2),
('m_rossi', 'La Miniera Perduta', '2026-08-24', 1, 3, 13, 7, 5),
('m_rossi', 'La Miniera Perduta', '2026-08-24', 1, 4, 8, 15, 6),
('m_rossi', 'La Miniera Perduta', '2026-08-31', 2, 1, 20, 18, 4),
('m_rossi', 'La Miniera Perduta', '2026-08-31', 2, 2, 14, 30, 3),
('m_rossi', 'La Miniera Perduta', '2026-08-31', 2, 3, 19, 11, 7),
('m_rossi', 'La Miniera Perduta', '2026-08-31', 2, 4, 9, 13, 8);

INSERT INTO TURNO (Username, Nome, DataSvolgimento, NumCombattimento, NumTurno) VALUES 
('m_rossi', 'La Miniera Perduta', '2026-08-24', 1, 1),
('m_rossi', 'La Miniera Perduta', '2026-08-31', 2, 1);

INSERT INTO AZIONE (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione, Tipo, CodiceOggetto, NumeroIstanza, CodiceScheda) VALUES 
('m_rossi', 'La Miniera Perduta', '2026-08-24', 1, 1, 1, 'Attacco', 1, 1, 1),
('m_rossi', 'La Miniera Perduta', '2026-08-31', 2, 1, 1, 'Attacco', 3, 1, 4);

INSERT INTO CONTRO (NumeroIstanza, Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione, Danno, Esito, Sconfitto, CodiceScheda) VALUES 
(4, 'm_rossi', 'La Miniera Perduta', '2026-08-24', 1, 1, 1, 10, 'Colpito', FALSE, 6),
(3, 'm_rossi', 'La Miniera Perduta', '2026-08-31', 2, 1, 1, 14, 'Colpo Critico', TRUE, 7);

INSERT INTO STATO_ATTIVO (Nome, Numero, Scaduto, Durata, Note, AFF_Username, AFF_Nome, AFF_DataSvolgimento, AFF_NumCombattimento, AFF_Numero, CodiceScheda, SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione) VALUES 
('Avvelenato', 1, FALSE, 1, 'Lama arrugginita', 'm_rossi', 'La Miniera Perduta', '2026-08-24', 1, 4, 6, 'm_rossi', 'La Miniera Perduta', '2026-08-24', 1, 1, 1);

INSERT INTO TAG_MAGIA (CodiceMagia, Username, NomeCampagna, DataSvolgimento) VALUES 
(1, 'm_rossi', 'La Miniera Perduta', '2026-08-24'),
(2, 'm_rossi', 'La Miniera Perduta', '2026-08-31');

INSERT INTO TAG_OGGETTO (CodiceOggetto, Username, NomeCampagna, DataSvolgimento) VALUES 
(1, 'm_rossi', 'La Miniera Perduta', '2026-08-24'),
(3, 'm_rossi', 'La Miniera Perduta', '2026-08-31');

INSERT INTO TAG_PARTECIPANTE (CodiceScheda, Username, NomeCampagna, DataSvolgimento) VALUES 
(1, 'm_rossi', 'La Miniera Perduta', '2026-08-24'),
(2, 'm_rossi', 'La Miniera Perduta', '2026-08-24'),
(4, 'm_rossi', 'La Miniera Perduta', '2026-08-31'),
(3, 'm_rossi', 'La Miniera Perduta', '2026-08-31');

SELECT setval(pg_get_serial_sequence('scheda', 'codicescheda'), (SELECT MAX(codicescheda) FROM scheda));