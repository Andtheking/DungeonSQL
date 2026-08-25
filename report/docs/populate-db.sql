-- =========================================================================
-- SCRIPT DI POPOLAMENTO (INSERT) ADATTATO AL NUOVO SCHEMA POSTGRESQL
-- =========================================================================

INSERT INTO UTENTE (Username, Email, Password) VALUES 
('hammon', 'hammon@dnd.it', '83cbeae5e480819e2eefb42dae50e2e5e203cf125e1ff663c114b62296f76818c94abb691f2c954954b8da3856d62bfcf17a549479a7fc2436b8289c1e453a78'),
('drast', 'drast@dnd.it', '8b50a54a9d1ce56ad04557d972d76123342708ce655e971464c62a74cbcdd62d76ecb1fedfa11f41a3cdf87b0c7565fd603ec163a0f25e966372e0421f0d7d22'),
('lilkvneki', 'lilkvneki@dnd.it', '1bcd2ff05cba06bf6c81e38567862d88bb187e8b4e2132af80f8e227fab9385fef1e76b5aa1c09e35efdd0fa103fb4caae7ba24116be35a5f56ae5bf31026937'),
('Balvio', 'balvio@dnd.it', 'a15fb73c00f1aed1bd49a0e8ebf7dcae457a7a89a06eb383e16215b97c67280fd51cdb547d5e1588922b7cd7b16fd36a6d28ac94425e2e24073521f317455e91'),
('Fypupis', 'fypupis@dnd.it', 'e159dbd9903ac4fa0b7dbb830a01d7d2251fb7920c6f72818a6bdbd96de5d57382581fdd27c683e7bb298a3e7d4d8e01502cba3eb362b2e292635fd838794f57'),
('Fykimyo', 'fykimyo@dnd.it', '1f9720f871674c18e5fecff61d92c1355cd4bfac25699fb7ddfe7717c9669b4d085193982402156122dfaa706885fd64741704649795c65b2a5bdec40347e28a'),
('Tr3cNik', 'tr3cnik@dnd.it', '2d1a3dccecad1b1ee5d39b4a9fdb4e1b1272816b7090d842b46db8c373b8c31ff645fc3861cc67b07c5fdc8b9a4a72151bef9f55ee116d93a1dc033fba88f3a3'),
('Andtheking', 'andtheking@dnd.it', 'a0c299b71a9e59d5ebb07917e70601a3570aa103e99a7bb65a58e780ec9077b1902d1dedb31b1457beda595fe4d71d779b6ca9cad476266cc07590e31d84b206'),
('Fabri Fibra', 'fabri.fibra@dnd.it', 'c624344720428e66d65f89c61f951fbbea1e9b18cd964983d52dd23af41aa6dcc46a77bf0c9c1d0360dfab0625012bba02201531a39e19796fd95346bbf19ecd'),
('M4rta', 'marta@dnd.it', '230935662272b62b039967c99eabbd1dddd1e1b3280ca70cef6336e0343ab8e26766c84ceee0d35565d18983968cd016541247ea7e64f93808deaa272e9e7413'),
('Luuca29', 'luca@dnd.it', 'fee7dae007e1ae369671fdb1504f4d58d6bcfafe18a5e96fe78a4b45c91abaf91572b5bc5939f1964172615e3e86cefb4483529f92c311afa4f212d1a518eaab');

INSERT INTO CAMPAGNA (UsernameMaster, Nome, Descrizione, DataInizio) VALUES 
('Fabri Fibra', 'La Miniera Perduta', 'Campagna ufficiale D&D 5e ambientata nei Forgotten Realms', '2026-08-01'),
('Fykimyo', 'DuckInvasion', 'Invasione delle papere mutanti', '2026-04-12'),
('Tr3cNik', 'Curse of Strahd', 'Avventura gotica nel semipiano del terrore, Barovia', '2026-09-10'),
('lilkvneki', 'Tokyo Ghoul', 'Campagna basata sull''anime di Tokyo Ghoul', '2026-04-12'),
('Andtheking', 'Il villaggio incantato', 'Campagna basata sul mondo Sanrio', '2026-04-12');

INSERT INTO SESSIONE (Username, NomeCampagna, DataSvolgimento, Diario) 
VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 'Scontro epico contro una pattuglia di goblin e un orco nelle caverne.'),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', 'Il party scopre l''ingresso segreto della miniera. Un bandito cerca di tendergli un agguato.'),
('Tr3cNik', 'Curse of Strahd', '2026-09-15', 'Arrivo al villaggio di Barovia. La nebbia si infittisce.');

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

-- ID Oggetti trasformati in Numeri (1, 2, 3, 4). Peso convertito in numeric.
INSERT INTO OGGETTO (CodiceOggetto, Nome, Descrizione, Peso, EffettoMagico, TipoOggetto, Danno, TipoArma, ProprietaArma, UsernameMasterCampagna, NomeCampagna) VALUES 
(1, 'Spada Lunga', 'Arma da mischia versatile', 3.00, 'No', 'Arma', '1d8 tagliente', 'Mischia', 'Versatile (1d10)', 'Fabri Fibra', 'La Miniera Perduta'),
(2, 'Arco Lungo', 'Arco per la lunga distanza', 1.00, 'No', 'Arma', '1d8 perforante', 'Distanza', 'Gittata (45/180)', 'Fabri Fibra', 'La Miniera Perduta'),
(3, 'Pugnale Velenoso', 'Lama verde smeraldo', 0.50, 'Si', 'Arma', '1d4 perforante', 'Mischia', 'Accurata, Leggera', 'Fabri Fibra', 'La Miniera Perduta');

INSERT INTO OGGETTO (CodiceOggetto, Nome, Descrizione, Peso, EffettoMagico, TipoOggetto, EffettoCons, DurataCons, UsernameMasterCampagna, NomeCampagna) VALUES 
(4, 'Pozione di Cura', 'Liquido rosso vivo', 0.20, 'Si', 'Consumabile', 'Cura 2d4+2 HP', 'Istantaneo', 'Fabri Fibra', 'La Miniera Perduta');

-- ID Magie in Numeri. Rituale in BOOLEAN (FALSE)
INSERT INTO MAGIA (CodiceMagia, Nome, Descrizione, Livello, Rituale, UsernameMasterCampagna, NomeCampagna) VALUES 
(1, 'Dardo Incantato', 'Tre dardi di energia magica colpiscono.', 1, FALSE, 'Fabri Fibra', 'La Miniera Perduta'),
(2, 'Cura Ferite', 'Una creatura recupera punti ferita.', 1, FALSE, 'Fabri Fibra', 'La Miniera Perduta'),
(3, 'Palla di Fuoco', 'Esplosione di 8m di raggio (8d6 fuoco).', 3, FALSE, 'Fabri Fibra', 'La Miniera Perduta');

INSERT INTO PROPENSIONE (NomeSkill, NomeBackground) VALUES 
('Atletica', 'Soldato'),
('Intimidazione', 'Soldato'),
('Furtività', 'Criminale'),
('Inganno', 'Criminale'),
('Religione', 'Accolito');

-- SCHEDE (Codici passati a INT: PG da 1 a 4, Mostri da 5 a 8)
INSERT INTO SCHEDA (CodiceScheda, Nome, MaxHP, CA, Taglia, UsernameCreatore, UsernameMaster, NomeCampagna) VALUES 
(1, 'Aragorn', 25, 16, 'Media', 'Andtheking', 'Fabri Fibra', 'La Miniera Perduta'),
(2, 'Legolas', 20, 15, 'Media', 'Andtheking', 'Fabri Fibra', 'La Miniera Perduta'),
(3, 'Gimli', 30, 18, 'Media', 'Luuca29', 'Fabri Fibra', 'La Miniera Perduta'),
(4, 'Garret', 18, 14, 'Media', 'M4rta', 'Fabri Fibra', 'La Miniera Perduta'),
(5, 'Goblin', 7, 15, 'Piccola', 'Fabri Fibra', 'Fabri Fibra', 'La Miniera Perduta'),
(6, 'Orco', 15, 13, 'Media', 'Fabri Fibra', 'Fabri Fibra', 'La Miniera Perduta'),
(7, 'Bandito', 11, 12, 'Media', 'Fabri Fibra', 'Fabri Fibra', 'La Miniera Perduta'),
(8, 'Scheletro', 13, 13, 'Media', 'Fabri Fibra', 'Fabri Fibra', 'La Miniera Perduta');

-- PERSONAGGIO
INSERT INTO PERSONAGGIO (CodiceScheda, Allineamento, HP, ExpAccumulata, NomeBackground, NomeRazza) VALUES 
(1, 'Neutrale Buono', 25, 300, 'Soldato', 'Umano'),
(2, 'Caotico Buono', 20, 300, 'Soldato', 'Elfo'),
(3, 'Legale Buono', 30, 300, 'Nobile', 'Nano'),
(4, 'Caotico Neutrale', 18, 300, 'Criminale', 'Tiefling');

-- MOSTRO (CR rimasto varchar es. '1/4', Velocita int)
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

-- POSSESSO (Punteggio INT, Competenza BOOLEAN)
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

-- INVENTARIO (Rimosso il terzo valore Quantità perchè non esiste nello schema)
INSERT INTO INVENTARIO (CodiceOggetto, CodiceScheda, Quantita) VALUES 
(1, 1, 1),
(2, 2, 1),
(4, 3, 1),
(3, 4, 1);

INSERT INTO COMBATTIMENTO (Username, Nome, DataSvolgimento, NumCombattimento) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', 2);

INSERT INTO ISTANZA_COMB (Username, Nome, DataSvolgimento, NumCombattimento, NumeroIstanza, Iniziativa, HP, CodiceScheda) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1, 1, 18, 25, 1),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1, 2, 16, 20, 2),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1, 3, 13, 7, 5),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1, 4, 8, 15, 6),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', 2, 1, 20, 18, 4),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', 2, 2, 14, 30, 3),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', 2, 3, 19, 11, 7),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', 2, 4, 9, 13, 8);

INSERT INTO TURNO (Username, Nome, DataSvolgimento, NumCombattimento, NumTurno) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1, 1),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', 2, 1);

INSERT INTO AZIONE (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione, Tipo, CodiceOggetto, NumeroIstanza, CodiceScheda) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1, 1, 1, 'Attacco', 1, 1, 1),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', 2, 1, 1, 'Attacco', 3, 1, 4);

-- CONTRO (Sconfitto convertito in BOOLEAN)
INSERT INTO CONTRO (NumeroIstanza, Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione, Danno, Esito, Sconfitto, CodiceScheda) VALUES 
(4, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1, 1, 1, 10, 'Colpito', FALSE, 6),
(3, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31', 2, 1, 1, 14, 'Colpo Critico', TRUE, 7);

-- STATO ATTIVO (Scaduto BOOLEAN, Durata INT)
INSERT INTO STATO_ATTIVO (Nome, Numero, Scaduto, Durata, Note, AFF_Username, AFF_Nome, AFF_DataSvolgimento, AFF_NumCombattimento, AFF_Numero, CodiceScheda, SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione) VALUES 
('Avvelenato', 1, FALSE, 1, 'Lama arrugginita', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1, 4, 6, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24', 1, 1, 1);

-- TAG (Codici passati ad INT)
INSERT INTO TAG_MAGIA (CodiceMagia, Username, NomeCampagna, DataSvolgimento) VALUES 
(1, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24'),
(2, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31');

INSERT INTO TAG_OGGETTO (CodiceOggetto, Username, NomeCampagna, DataSvolgimento) VALUES 
(1, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24'),
(3, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31');

INSERT INTO TAG_PARTECIPANTE (CodiceScheda, Username, NomeCampagna, DataSvolgimento) VALUES 
(1, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24'),
(2, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24'),
(4, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31'),
(3, 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31');