-- =========================================================================
-- SCRIPT DI POPOLAMENTO (INSERT) ESTESO PER DEMO
-- =========================================================================

INSERT INTO UTENTE (Username, Email, Password) VALUES 
('hammon', 'hammon@dnd.it', '83cbeae5e480819e2eefb42dae50e2e5e203cf125e1ff663c114b62296f76818c94abb691f2c954954b8da3856d62bfcf17a549479a7fc2436b8289c1e453a78'),
('drast', 'drast@dnd.it', '8b50a54a9d1ce56ad04557d972d76123342708ce655e971464c62a74cbcdd62d76ecb1fedfa11f41a3cdf87b0c7565fd603ec163a0f25e966372e0421f0d7d22'),
('lilkvneki', 'lilkvneki@dnd.it', '1bcd2ff05cba06bf6c81e38567862d88bb187e8b4e2132af80f8e227fab9385fef1e76b5aa1c09e35efdd0fa103fb4caae7ba24116be35a5f56ae5bf31026937'),
('Balvio', 'balvio@dnd.it', 'a15fb73c00f1aed1bd49a0e8ebf7dcae457a7a89a06eb383e16215b97c67280fd51cdb547d5e1588922b7cd7b16fd36a6d28ac94425e2e24073521f317455e91'),
('Fypupis', 'fypupis@dnd.it', 'e159dbd9903ac4fa0b7dbb830a01d7d2251fb7920c6f72818a6bdbd96de5d57382581fdd27c683e7bb298a3e7d4d8e01502cba3eb362b2e292635fd838794f57'),
('Fykimyo', 'fykimyo@dnd.it', '1f9720f871674c18e5fecff61d92c1355cd4bfac25699fb7ddfe7717c9669b4d085193982402156122dfaa706885fd64741704649795c65b2a5bdec40347e28a'),
('Tr3cNik', 'tr3cnik@dnd.it', '2d1a3dccecad1b1ee5d39b4a9fdb4e1b1272816b7090d842b46db8c373b8c31ff645fc3861cc67b07c5fdc8b9a4a72151bef9f55ee116d93a1dc033fba88f3a3'),
('Andtheking', 'andtheking@dnd.it', '0354b484489432fe8aebec7d1de7df48902a63a347a830cbb504913b8ce22b24df5fe9a3a9ce702087145b28b168f74e5a4cd8d34528f6ee59d5982af763d688'),
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

INSERT INTO CARATTERISTICA (NomeCaratteristica) VALUES ('Forza');
INSERT INTO CARATTERISTICA (NomeCaratteristica) VALUES ('Destrezza');
INSERT INTO CARATTERISTICA (NomeCaratteristica) VALUES ('Costituzione');
INSERT INTO CARATTERISTICA (NomeCaratteristica) VALUES ('Intelligenza');
INSERT INTO CARATTERISTICA (NomeCaratteristica) VALUES ('Saggezza');
INSERT INTO CARATTERISTICA (NomeCaratteristica) VALUES ('Carisma');

-- Forza
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Atletica', 'Forza');
-- Destrezza
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Acrobazia', 'Destrezza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Furtività', 'Destrezza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Rapidità di Mano', 'Destrezza');
-- Intelligenza
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Arcana', 'Intelligenza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Indagare', 'Intelligenza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Natura', 'Intelligenza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Religione', 'Intelligenza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Storia', 'Intelligenza');
-- Saggezza
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Addestrare Animali', 'Saggezza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Intuizione', 'Saggezza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Medicina', 'Saggezza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Percezione', 'Saggezza');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Sopravvivenza', 'Saggezza');
-- Carisma
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Inganno', 'Carisma');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Intimidazione', 'Carisma');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Intrattenere', 'Carisma');
INSERT INTO SKILL (NomeSkill, NomeCaratteristica) VALUES ('Persuasione', 'Carisma');

-- 6. BACKGROUND E RAZZE (Aggiunti)
INSERT INTO BACKGROUND (NomeBackground, Descrizione) VALUES 
('Soldato', 'Veterano di innumerevoli campagne militari.'),
('Criminale', 'Abile nei furti e nei contatti con la malavita.'),
('Nobile', 'Cresciuto in una famiglia che possiede ricchezza e potere.'),
('Accolito', 'Hai trascorso la vita al servizio di un tempio.');

INSERT INTO RAZZA (NomeRazza, Descrizione, VelocitaBase, Scurovisione, NomeRazzaPadre) VALUES 
('Umano', 'Versatili, intraprendenti e diffusi ovunque.', '9 metri', 'No', NULL),
('Elfo', 'Creature agili dotate di grazia soprannaturale.', '9 metri', 'Sì (18 metri)', NULL),
('Nano', 'Bassi, robusti e noti per la loro abilità in battaglia.', '7.5 metri', 'Sì (18 metri)', NULL),
('Tiefling', 'Umanoidi con un lignaggio demoniaco o diabolico.', '9 metri', 'Sì (18 metri)', NULL);

-- 7. CLASSI, SOTTOCLASSI, TRATTI E RISORSE (Aggiunti Ladro e Chierico)
INSERT INTO CLASSE (NomeClasse) VALUES ('Guerriero'), ('Mago'), ('Ladro'), ('Chierico');

INSERT INTO SOTTOCLASSE (NomeClasse, NomeSottoclasse) VALUES 
('Guerriero', 'Campione'),
('Mago', 'Scuola di Evocazione'),
('Ladro', 'Assassino'),
('Chierico', 'Dominio della Vita');

INSERT INTO TRATTO_CLASSE (NomeClasse, NomeTratto, Descrizione, LivelloRichiesto) VALUES 
('Guerriero', 'Attacco Extra', 'Puoi attaccare due volte invece di una.', '5'),
('Ladro', 'Attacco Furtivo', 'Infliggi 1d6 danni extra se hai vantaggio.', '1'),
('Chierico', 'Incanalare Divinità', 'Usi l''energia divina per effetti magici.', '2');

INSERT INTO RISORSA_CLASSE (NomeClasse, NomeRisorsa, Recupero) VALUES 
('Guerriero', 'Seconda Vita', 'Riposo Breve o Lungo'),
('Chierico', 'Usi Incanalare Divinità', 'Riposo Breve');

-- 8. EFFETTI DI STATO, OGGETTI E MAGIE (Estesi)
INSERT INTO EFFETTO_STATO (Nome, Descrizione) VALUES 
('Avvelenato', 'Il bersaglio ha svantaggio nei tiri per colpire e prove di abilità.'),
('Invisibile', 'Non puoi essere visto senza l''ausilio di magia.'),
('Pietrificato', 'Trasformato in pietra solida. Sei inabile.');

INSERT INTO OGGETTO (CodiceOggetto, Nome, Descrizione, Peso, EffettoMagico, TipoOggetto, Danno, TipoArma, ProprietaArma, UsernameMasterCampagna, NomeCampagna) VALUES 
('arma_spada_lunga', 'Spada Lunga', 'Arma da mischia versatile', '3 kg', 'No', 'Arma', '1d8 tagliente', 'Mischia', 'Versatile (1d10)', 'Fabri Fibra', 'La Miniera Perduta'),
('arma_arco_lungo', 'Arco Lungo', 'Arco per la lunga distanza', '1 kg', 'No', 'Arma', '1d8 perforante', 'Distanza', 'Gittata (45/180)', 'Fabri Fibra', 'La Miniera Perduta'),
('arma_pugnale_velenoso', 'Pugnale Velenoso', 'Lama verde smeraldo', '0.5 kg', 'Si', 'Arma', '1d4 perforante', 'Mischia', 'Accurata, Leggera', 'Fabri Fibra', 'La Miniera Perduta');

INSERT INTO OGGETTO (CodiceOggetto, Nome, Descrizione, Peso, EffettoMagico, TipoOggetto, EffettoCons, DurataCons, UsernameMasterCampagna, NomeCampagna) VALUES 
('cons_pozione_cura', 'Pozione di Cura', 'Liquido rosso vivo', '0.2 kg', 'Si', 'Consumabile', 'Cura 2d4+2 HP', 'Istantaneo', 'Fabri Fibra', 'La Miniera Perduta');

INSERT INTO MAGIA (CodiceMagia, Nome, Descrizione, Livello, Rituale, UsernameMasterCampagna, NomeCampagna) VALUES 
('incantesimo_dardo', 'Dardo Incantato', 'Tre dardi di energia magica colpiscono.', '1', 'No', 'Fabri Fibra', 'La Miniera Perduta'),
('incantesimo_cura', 'Cura Ferite', 'Una creatura recupera punti ferita.', '1', 'No', 'Fabri Fibra', 'La Miniera Perduta'),
('incantesimo_palla_fuoco', 'Palla di Fuoco', 'Esplosione di 8m di raggio (8d6 fuoco).', '3', 'No', 'Fabri Fibra', 'La Miniera Perduta');

-- 9. PROPENSIONE
INSERT INTO PROPENSIONE (NomeSkill, NomeBackground) VALUES 
('Atletica', 'Soldato'),
('Intimidazione', 'Soldato'),
('Furtività', 'Criminale'),
('Inganno', 'Criminale'),
('Religione', 'Accolito');

-- 10. SCHEDE (Espansione di Personaggi e Mostri)
-- Personaggi Giocanti
INSERT INTO SCHEDA (CodiceScheda, Nome, MaxHP, CA, Taglia, UsernameCreatore, UsernameMaster, NomeCampagna) VALUES 
('pg_aragorn', 'Aragorn', '25', '16', 'Media', 'Andtheking', 'Fabri Fibra', 'La Miniera Perduta'),
('pg_legolas', 'Legolas', '20', '15', 'Media', 'Andtheking', 'Fabri Fibra', 'La Miniera Perduta'),
('pg_gimli', 'Gimli', '30', '18', 'Media', 'Luuca29', 'Fabri Fibra', 'La Miniera Perduta'),
('pg_garret', 'Garret', '18', '14', 'Media', 'M4rta', 'Fabri Fibra', 'La Miniera Perduta');

-- Mostri
INSERT INTO SCHEDA (CodiceScheda, Nome, MaxHP, CA, Taglia, UsernameCreatore, UsernameMaster, NomeCampagna) VALUES 
('scheda_goblin', 'Goblin', '7', '15', 'Piccola', 'Fabri Fibra', 'Fabri Fibra', 'La Miniera Perduta'),
('scheda_orco', 'Orco', '15', '13', 'Media', 'Fabri Fibra', 'Fabri Fibra', 'La Miniera Perduta'),
('scheda_bandito', 'Bandito', '11', '12', 'Media', 'Fabri Fibra', 'Fabri Fibra', 'La Miniera Perduta'),
('scheda_scheletro', 'Scheletro', '13', '13', 'Media', 'Fabri Fibra', 'Fabri Fibra', 'La Miniera Perduta');

-- 11. DETTAGLI PERSONAGGI E MOSTRI
INSERT INTO PERSONAGGIO (CodiceScheda, Allineamento, HP, ExpAccumulata, NomeBackground, NomeRazza) VALUES 
('pg_aragorn', 'Neutrale Buono', '25', '300', 'Soldato', 'Umano'),
('pg_legolas', 'Caotico Buono', '20', '300', 'Soldato', 'Elfo'),
('pg_gimli', 'Legale Buono', '30', '300', 'Nobile', 'Nano'),
('pg_garret', 'Caotico Neutrale', '18', '300', 'Criminale', 'Tiefling');

INSERT INTO MOSTRO (CodiceScheda, CR, ExpRilasciata, Velocita) VALUES 
('scheda_goblin', '1/4', '50', '9 metri'),
('scheda_orco', '1', '100', '9 metri'),
('scheda_bandito', '1/8', '25', '9 metri'),
('scheda_scheletro', '1/4', '50', '9 metri');

-- 12. PROGRESSI E ABILITAZIONI CLASSE
INSERT INTO PROGRESSO (CodiceScheda, NomeClasse, Livello, NomeSottoclasse) VALUES 
('pg_aragorn', 'Guerriero', '2', 'Campione'),
('pg_legolas', 'Guerriero', '2', 'Campione'),
('pg_gimli', 'Chierico', '2', 'Dominio della Vita'),
('pg_garret', 'Ladro', '2', 'Assassino');

INSERT INTO ABILITAZIONE_TRATTO (CodiceScheda, NomeClasse, NomeTratto, MaxQuantita, Quantita) VALUES 
('pg_garret', 'Ladro', 'Attacco Furtivo', '1', '1'),
('pg_gimli', 'Chierico', 'Incanalare Divinità', '1', '1');

-- 13. POSSESSO, CAPACITÀ, APPRENDIMENTO E INVENTARIO
INSERT INTO POSSESSO (NomeCaratteristica, CodiceScheda, Punteggio, CompetenzaSalvezza) VALUES 
('Forza', 'pg_aragorn', '16', 'Sì'),
('Destrezza', 'pg_aragorn', '10', 'No'),
('Costituzione', 'pg_aragorn', '10', 'No'),
('Intelligenza', 'pg_aragorn', '10', 'No'),
('Saggezza', 'pg_aragorn', '10', 'No'),
('Carisma', 'pg_aragorn', '10', 'No'),
('Forza', 'pg_legolas', '10', 'No'),
('Destrezza', 'pg_legolas', '10', 'No'),
('Costituzione', 'pg_legolas', '10', 'No'),
('Intelligenza', 'pg_legolas', '10', 'No'),
('Saggezza', 'pg_legolas', '10', 'No'),
('Carisma', 'pg_legolas', '10', 'No'),
('Forza', 'pg_gimli', '10', 'No') ,
('Destrezza', 'pg_gimli', '10', 'No'),
('Costituzione', 'pg_gimli', '10', 'No'),
('Intelligenza', 'pg_gimli', '10', 'No'),
('Saggezza', 'pg_gimli', '10', 'No'),
('Carisma', 'pg_gimli', '10', 'No'),
('Forza', 'pg_garret', '10', 'No'),
('Costituzione', 'pg_garret', '10', 'No'),
('Intelligenza', 'pg_garret', '10', 'No'),
('Saggezza', 'pg_garret', '10', 'No'),
('Carisma', 'pg_garret', '10', 'No'),
('Destrezza', 'pg_garret', '17', 'Sì');

INSERT INTO CAPACITA (CodiceScheda, NomeSkill, LivelloCapacita) VALUES 
('pg_aragorn', 'Atletica', 'Competente'),
('pg_legolas', 'Percezione', 'Maestria'),
('pg_garret', 'Furtività', 'Maestria'),
('pg_gimli', 'Religione', 'Competente');

INSERT INTO APPRENDIMENTO (NomeClasse, CodiceMagia) VALUES 
('Mago', 'incantesimo_dardo'),
('Mago', 'incantesimo_palla_fuoco'),
('Chierico', 'incantesimo_cura');

INSERT INTO CONOSCENZA (CodiceMagia, CodiceScheda) VALUES 
('incantesimo_cura', 'pg_gimli');

INSERT INTO INVENTARIO (CodiceOggetto, CodiceScheda, Quantita) VALUES 
('arma_spada_lunga', 'pg_aragorn', '1'),
('arma_arco_lungo', 'pg_legolas', '1'),
('cons_pozione_cura', 'pg_gimli', '3'),
('arma_pugnale_velenoso', 'pg_garret', '2');

-- 14. COMBATTIMENTO (Due combattimenti separati per mostrare il log storico)
INSERT INTO COMBATTIMENTO (Username, Nome, DataSvolgimento, NumCombattimento) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1'),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', '2');

-- 15. ISTANZE DI COMBATTIMENTO
-- Combattimento 1: Aragorn & Legolas vs Goblin & Orco
INSERT INTO ISTANZA_COMB (Username, Nome, DataSvolgimento, NumCombattimento, NumeroIstanza, Iniziativa, HP, CodiceScheda) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1', '1', '18', '25', 'pg_aragorn'),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1', '2', '16', '20', 'pg_legolas'),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1', '3', '13', '7', 'scheda_goblin'),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1', '4', '8', '15', 'scheda_orco');

-- Combattimento 2: Party completo vs Banditi e Scheletri
INSERT INTO ISTANZA_COMB (Username, Nome, DataSvolgimento, NumCombattimento, NumeroIstanza, Iniziativa, HP, CodiceScheda) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', '2', '1', '20', '18', 'pg_garret'),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', '2', '2', '14', '30', 'pg_gimli'),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', '2', '3', '19', '11', 'scheda_bandito'),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', '2', '4', '9', '13', 'scheda_scheletro');

-- 16. TURNO
INSERT INTO TURNO (Username, Nome, DataSvolgimento, NumCombattimento, NumTurno) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1', '1'),
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', '2', '1');

-- 17. AZIONE
-- Aragorn attacca l'orco (Comb 1)
INSERT INTO AZIONE (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione, Tipo, CodiceOggetto, NumeroIstanza, CodiceScheda) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1', '1', '1', 'Attacco', 'arma_spada_lunga', '1', 'pg_aragorn');

-- Garret attacca a sorpresa il bandito (Comb 2)
INSERT INTO AZIONE (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione, Tipo, CodiceOggetto, NumeroIstanza, CodiceScheda) VALUES 
('Fabri Fibra', 'La Miniera Perduta', '2026-08-31', '2', '1', '1', 'Attacco', 'arma_pugnale_velenoso', '1', 'pg_garret');

-- 18. CONTRO
-- L'azione di Aragorn colpisce l'Orco istanza 4
INSERT INTO CONTRO (NumeroIstanza, Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione, Danno, Esito, Sconfitto, CodiceScheda) VALUES 
('4', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1', '1', '1', '10', 'Colpito', 'No', 'scheda_orco');

-- L'azione di Garret colpisce il Bandito e lo uccide istantaneamente (Attacco Furtivo!)
INSERT INTO CONTRO (NumeroIstanza, Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione, Danno, Esito, Sconfitto, CodiceScheda) VALUES 
('3', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31', '2', '1', '1', '14', 'Colpo Critico', 'Sì', 'scheda_bandito');

-- 19. STATO ATTIVO 
-- L'orco sopravvive ma viene avvelenato (giusto per demo)
INSERT INTO STATO_ATTIVO (Nome, Numero, Scaduto, Durata, Note, AFF_Username, AFF_Nome, AFF_DataSvolgimento, AFF_NumCombattimento, AFF_Numero, CodiceScheda, SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione) VALUES 
('Avvelenato', '1', 'No', '1 Turno', 'Lama arrugginita', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1', '4', 'scheda_orco', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24', '1', '1', '1');

-- 20. TAG DI SESSIONE (Metadati extra)
INSERT INTO TAG_MAGIA (CodiceMagia, Username, NomeCampagna, DataSvolgimento) VALUES 
('incantesimo_dardo', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24'),
('incantesimo_cura', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31');

INSERT INTO TAG_OGGETTO (CodiceOggetto, Username, NomeCampagna, DataSvolgimento) VALUES 
('arma_spada_lunga', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24'),
('arma_pugnale_velenoso', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31');

INSERT INTO TAG_PARTECIPANTE (CodiceScheda, Username, NomeCampagna, DataSvolgimento) VALUES 
('pg_aragorn', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24'),
('pg_legolas', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-24'),
('pg_garret', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31'),
('pg_gimli', 'Fabri Fibra', 'La Miniera Perduta', '2026-08-31');