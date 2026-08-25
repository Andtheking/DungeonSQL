-- *********************************************
-- * SQL PostgreSQL generation                 
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.2              
-- * Generator date: Sep 20 2021              
-- * Generation date: Mon Aug 24 21:36:46 2026 
-- * LUN file: /home/andrea/studio/database/DungeonSQL/report/docs/DungeonSQL+.lun 
-- * Schema: RELATIONAL/1 
-- ********************************************* 

-- Database Section
-- ________________ 

-- create database dungeonsql;

-- Tables Section
-- _____________ 

create table ABILITAZIONE_RISORSA (
     CodiceScheda VARCHAR(255) not null,
     NomeClasse VARCHAR(255) not null,
     NomeRisorsa VARCHAR(255) not null,
     constraint ID_ABILITAZIONE_RISORSA primary key (CodiceScheda, NomeClasse, NomeRisorsa));

create table ABILITAZIONE_TRATTO (
     CodiceScheda VARCHAR(255) not null,
     NomeClasse VARCHAR(255) not null,
     NomeTratto VARCHAR(255) not null,
     MaxQuantita VARCHAR(255) not null,
     Quantita VARCHAR(255) not null,
     constraint ID_ABILITAZIONE_TRATTO primary key (NomeTratto, CodiceScheda, NomeClasse));

create table APPRENDIMENTO (
     NomeClasse VARCHAR(255) not null,
     CodiceMagia VARCHAR(255) not null,
     constraint ID_APPRENDIMENTO primary key (CodiceMagia, NomeClasse));

create table AZIONE (
     Username VARCHAR(255) not null,
     NomeCampagna VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     NumCombattimento VARCHAR(255) not null,
     NumTurno VARCHAR(255) not null,
     NumAzione VARCHAR(255) not null,
     Tipo VARCHAR(255) not null,
     CodiceOggetto VARCHAR(255),
     CodiceMagia VARCHAR(255),
     NumeroIstanza VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     constraint ID_AZIONE primary key (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione));

create table BACKGROUND (
     NomeBackground VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     constraint ID_BACKGROUND primary key (NomeBackground));

create table CAMPAGNA (
     UsernameMaster VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     DataInizio VARCHAR(255) not null,
     constraint ID_CAMPAGNA primary key (UsernameMaster, Nome));

create table CAPACITA (
     CodiceScheda VARCHAR(255) not null,
     NomeSkill VARCHAR(255) not null,
     LivelloCapacita VARCHAR(255) not null,
     constraint ID_CAPACITA primary key (NomeSkill, CodiceScheda));

create table CARATTERISTICA (
     NomeCaratteristica VARCHAR(255) not null,
     constraint ID_CARATTERISTICA_ID primary key (NomeCaratteristica));

create table CLASSE (
     NomeClasse VARCHAR(255) not null,
     constraint ID_CLASSE primary key (NomeClasse));

create table COMBATTIMENTO (
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     NumCombattimento VARCHAR(255) not null,
     constraint ID_COMBATTIMENTO_ID primary key (Username, Nome, DataSvolgimento, NumCombattimento));

create table CONOSCENZA (
     CodiceMagia VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     constraint ID_CONOSCENZA primary key (CodiceScheda, CodiceMagia));

create table CONTRO (
     Username VARCHAR(255) not null,
     NomeCampagna VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     NumCombattimento VARCHAR(255) not null,
     NumTurno VARCHAR(255) not null,
     NumAzione VARCHAR(255) not null,
     NumeroIstanza VARCHAR(255) not null,
     Danno VARCHAR(255) not null,
     Esito VARCHAR(255) not null,
     Sconfitto VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     constraint ID_CONTRO primary key (NumeroIstanza, Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione));

create table EFFETTO_STATO (
     Nome VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     constraint ID_EFFETTO_STATO primary key (Nome));

create table INVENTARIO (
     CodiceOggetto VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     Quantita VARCHAR(255) not null,
     constraint ID_INVENTARIO primary key (CodiceScheda, CodiceOggetto));

create table ISTANZA_COMB (
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     NumCombattimento VARCHAR(255) not null,
     NumeroIstanza VARCHAR(255) not null,
     Iniziativa VARCHAR(255) not null,
     HP VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     constraint ID_ISTANZA_COMB primary key (CodiceScheda, Username, Nome, DataSvolgimento, NumCombattimento, NumeroIstanza));

create table MAGIA (
     CodiceMagia VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     Livello VARCHAR(255) not null,
     Rituale VARCHAR(255) not null,
     UsernameMasterCampagna VARCHAR(255),
     NomeCampagna VARCHAR(255),
     constraint ID_MAGIA_ID primary key (CodiceMagia));

create table MOSTRO (
     CodiceScheda VARCHAR(255) not null,
     CR VARCHAR(255) not null,
     ExpRilasciata VARCHAR(255) not null,
     Velocita VARCHAR(255) not null,
     constraint FKNATURA_MOSTRO_ID primary key (CodiceScheda));

create table OGGETTO (
     CodiceOggetto VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     Peso VARCHAR(255) not null,
     EffettoMagico VARCHAR(255) not null,
     TipoOggetto VARCHAR(255) not null,
     Danno VARCHAR(255),
     TipoArma VARCHAR(255),
     ProprietaArma VARCHAR(255),
     BonusCA VARCHAR(255),
     ReqArmatura VARCHAR(255),
     Furtiva VARCHAR(255),
     EffettoCons VARCHAR(255),
     DurataCons VARCHAR(255),
     UsernameMasterCampagna VARCHAR(255),
     NomeCampagna VARCHAR(255),
     constraint ID_OGGETTO primary key (CodiceOggetto));

create table PERSONAGGIO (
     CodiceScheda VARCHAR(255) not null,
     Allineamento VARCHAR(255) not null,
     HP VARCHAR(255) not null,
     ExpAccumulata VARCHAR(255) not null,
     NomeBackground VARCHAR(255) not null,
     NomeRazza VARCHAR(255) not null,
     constraint FKNATURA_PERSONAGGIO_ID primary key (CodiceScheda));

create table POSSESSO (
     NomeCaratteristica VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     Punteggio VARCHAR(255) not null,
     CompetenzaSalvezza VARCHAR(255) not null,
     constraint ID_POSSESSO primary key (NomeCaratteristica, CodiceScheda));

create table PROGRESSO (
     CodiceScheda VARCHAR(255) not null,
     NomeClasse VARCHAR(255) not null,
     Livello VARCHAR(255) not null,
     NomeSottoclasse VARCHAR(255),
     constraint ID_PROGRESSO primary key (CodiceScheda, NomeClasse));

create table PROPENSIONE (
     NomeSkill VARCHAR(255) not null,
     NomeBackground VARCHAR(255) not null,
     constraint ID_PROPENSIONE primary key (NomeBackground, NomeSkill));

create table RAZZA (
     NomeRazza VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     VelocitaBase VARCHAR(255) not null,
     Scurovisione VARCHAR(255) not null,
     NomeRazzaPadre VARCHAR(255),
     constraint ID_RAZZA primary key (NomeRazza));

create table RISORSA_CLASSE (
     NomeClasse VARCHAR(255) not null,
     NomeRisorsa VARCHAR(255) not null,
     Recupero VARCHAR(255) not null,
     constraint ID_RISORSA_CLASSE primary key (NomeClasse, NomeRisorsa));

create table SCHEDA (
     CodiceScheda VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     MaxHP VARCHAR(255) not null,
     CA VARCHAR(255) not null,
     Taglia VARCHAR(255) not null,
     UsernameCreatore VARCHAR(255) not null,
     UsernameMaster VARCHAR(255) not null,
     NomeCampagna VARCHAR(255) not null,
     constraint ID_SCHEDA_ID primary key (CodiceScheda));

create table SESSIONE (
     Username VARCHAR(255) not null,
     NomeCampagna VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     Diario VARCHAR(255),
     constraint ID_SESSIONE primary key (Username, NomeCampagna, DataSvolgimento));

create table SKILL (
     NomeSkill VARCHAR(255) not null,
     NomeCaratteristica VARCHAR(255) not null,
     constraint ID_SKILL primary key (NomeSkill));

create table SOTTOCLASSE (
     NomeClasse VARCHAR(255) not null,
     NomeSottoclasse VARCHAR(255) not null,
     constraint ID_SOTTOCLASSE primary key (NomeClasse, NomeSottoclasse));

create table STATO_ATTIVO (
     Nome VARCHAR(255) not null,
     Numero VARCHAR(255) not null,
     Scaduto VARCHAR(255) not null,
     Durata VARCHAR(255) not null,
     Note VARCHAR(255),
     AFF_Username VARCHAR(255) not null,
     AFF_Nome VARCHAR(255) not null,
     AFF_DataSvolgimento VARCHAR(255) not null,
     AFF_NumCombattimento VARCHAR(255) not null,
     AFF_Numero VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     SUD_Username VARCHAR(255),
     SUD_Nome VARCHAR(255),
     SUD_DataSvolgimento VARCHAR(255),
     SUD_NumCombattimento VARCHAR(255),
     SUD_NumTurno VARCHAR(255),
     NumAzione VARCHAR(255),
     constraint ID_STATO_ATTIVO primary key (Nome, Numero));

create table TAG_MAGIA (
     Username VARCHAR(255) not null,
     NomeCampagna VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     CodiceMagia VARCHAR(255) not null,
     constraint ID_TAG_MAGIA primary key (CodiceMagia, Username, NomeCampagna, DataSvolgimento));

create table TAG_OGGETTO (
     Username VARCHAR(255) not null,
     NomeCampagna VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     CodiceOggetto VARCHAR(255) not null,
     constraint ID_TAG_OGGETTO primary key (CodiceOggetto, Username, NomeCampagna, DataSvolgimento));

create table TAG_PARTECIPANTE (
     Username VARCHAR(255) not null,
     NomeCampagna VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     constraint ID_TAG_PARTECIPANTE primary key (CodiceScheda, Username, NomeCampagna, DataSvolgimento));

create table TRATTO_CLASSE (
     NomeClasse VARCHAR(255) not null,
     NomeTratto VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     LivelloRichiesto VARCHAR(255) not null,
     constraint ID_TRATTO_CLASSE primary key (NomeClasse, NomeTratto));

create table TURNO (
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     NumCombattimento VARCHAR(255) not null,
     NumTurno VARCHAR(255) not null,
     constraint ID_TURNO_ID primary key (Username, Nome, DataSvolgimento, NumCombattimento, NumTurno));

create table UTENTE (
     Username VARCHAR(255) not null,
     Email VARCHAR(255) not null,
     Password VARCHAR(255) not null,
     constraint ID_UTENTE primary key (Username));


-- Constraints Section
-- ___________________ 

alter table ABILITAZIONE_RISORSA add constraint FKABI_RIS
     foreign key (NomeClasse, NomeRisorsa)
     references RISORSA_CLASSE;

alter table ABILITAZIONE_RISORSA add constraint FKABI_PRO_1
     foreign key (CodiceScheda, NomeClasse)
     references PROGRESSO;

alter table ABILITAZIONE_TRATTO add constraint FKABI_TRA
     foreign key (NomeClasse, NomeTratto)
     references TRATTO_CLASSE;

alter table ABILITAZIONE_TRATTO add constraint FKABI_PRO
     foreign key (CodiceScheda, NomeClasse)
     references PROGRESSO;

alter table APPRENDIMENTO add constraint FKAPP_MAG
     foreign key (CodiceMagia)
     references MAGIA;

alter table APPRENDIMENTO add constraint FKAPP_CLA
     foreign key (NomeClasse)
     references CLASSE;

alter table AZIONE add constraint FKUSO
     foreign key (CodiceOggetto)
     references OGGETTO;

alter table AZIONE add constraint FKLANCIO
     foreign key (CodiceMagia)
     references MAGIA;

alter table AZIONE add constraint FKESECUZIONE
     foreign key (CodiceScheda, Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumeroIstanza)
     references ISTANZA_COMB;

alter table AZIONE add constraint FKSUDDIVISIONE
     foreign key (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno)
     references TURNO;

alter table CAMPAGNA add constraint FKMASTER
     foreign key (UsernameMaster)
     references UTENTE;

alter table CAPACITA add constraint FKCAP_SKI
     foreign key (NomeSkill)
     references SKILL;

alter table CAPACITA add constraint FKCAP_SCH
     foreign key (CodiceScheda)
     references SCHEDA;

--Not implemented
--alter table CARATTERISTICA add constraint ID_CARATTERISTICA_CHK
--     check(exists(select * from SKILL
--                  where SKILL.NomeCaratteristica = NomeCaratteristica)); 

--Not implemented
--alter table COMBATTIMENTO add constraint ID_COMBATTIMENTO_CHK
--     check(exists(select * from TURNO
--                  where TURNO.Username = Username and TURNO.Nome = Nome and TURNO.DataSvolgimento = DataSvolgimento and TURNO.NumCombattimento = NumCombattimento)); 

--Not implemented
--alter table COMBATTIMENTO add constraint ID_COMBATTIMENTO_CHK
--     check(exists(select * from ISTANZA_COMB
--                  where ISTANZA_COMB.Username = Username and ISTANZA_COMB.Nome = Nome and ISTANZA_COMB.DataSvolgimento = DataSvolgimento and ISTANZA_COMB.NumCombattimento = NumCombattimento)); 

alter table COMBATTIMENTO add constraint FKAVVENIMENTO
     foreign key (Username, Nome, DataSvolgimento)
     references SESSIONE;

alter table CONOSCENZA add constraint FKCON_SCH
     foreign key (CodiceScheda)
     references SCHEDA;

alter table CONOSCENZA add constraint FKCON_MAG
     foreign key (CodiceMagia)
     references MAGIA;

alter table CONTRO add constraint FKCON_IST
     foreign key (CodiceScheda, Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumeroIstanza)
     references ISTANZA_COMB;

alter table CONTRO add constraint FKCON_AZI
     foreign key (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione)
     references AZIONE;

alter table INVENTARIO add constraint FKINV_SCH
     foreign key (CodiceScheda)
     references SCHEDA;

alter table INVENTARIO add constraint FKINV_OGG
     foreign key (CodiceOggetto)
     references OGGETTO;

alter table ISTANZA_COMB add constraint FKPARTE
     foreign key (Username, Nome, DataSvolgimento, NumCombattimento)
     references COMBATTIMENTO;

alter table ISTANZA_COMB add constraint GRISTANZA_COMB
     foreign key (CodiceScheda)
     references SCHEDA;

--Not implemented
--alter table MAGIA add constraint ID_MAGIA_CHK
--     check(exists(select * from APPRENDIMENTO
--                  where APPRENDIMENTO.CodiceMagia = CodiceMagia)); 

alter table MAGIA add constraint FKCREAZIONE_MAGIA_FK
     foreign key (UsernameMasterCampagna, NomeCampagna)
     references CAMPAGNA;

alter table MAGIA add constraint FKCREAZIONE_MAGIA_CHK
     check((UsernameMasterCampagna is not null and NomeCampagna is not null)
           or (UsernameMasterCampagna is null and NomeCampagna is null)); 

alter table MOSTRO add constraint FKNATURA_MOSTRO_FK
     foreign key (CodiceScheda)
     references SCHEDA;

alter table OGGETTO add constraint FKCREAZIONE_OGGETTO_FK
     foreign key (UsernameMasterCampagna, NomeCampagna)
     references CAMPAGNA;

alter table OGGETTO add constraint FKCREAZIONE_OGGETTO_CHK
     check((UsernameMasterCampagna is not null and NomeCampagna is not null)
           or (UsernameMasterCampagna is null and NomeCampagna is null)); 

alter table PERSONAGGIO add constraint FKNATURA_PERSONAGGIO_FK
     foreign key (CodiceScheda)
     references SCHEDA;

--Not implemented
--alter table PERSONAGGIO add constraint FKNATURA_PERSONAGGIO_CHK
--     check(exists(select * from PROGRESSO
--                  where PROGRESSO.CodiceScheda = CodiceScheda)); 

alter table PERSONAGGIO add constraint FKFONDAMENTO
     foreign key (NomeBackground)
     references BACKGROUND;

alter table PERSONAGGIO add constraint FKAPPARTENENZA_RAZZA
     foreign key (NomeRazza)
     references RAZZA;

alter table POSSESSO add constraint FKPOS_SCH
     foreign key (CodiceScheda)
     references SCHEDA;

alter table POSSESSO add constraint FKPOS_CAR
     foreign key (NomeCaratteristica)
     references CARATTERISTICA;

alter table PROGRESSO add constraint FKSCELTA
     foreign key (NomeClasse, NomeSottoclasse)
     references SOTTOCLASSE;

alter table PROGRESSO add constraint FKRIFERIMENTO
     foreign key (NomeClasse)
     references CLASSE;

alter table PROGRESSO add constraint FKAPPARTENENZA
     foreign key (CodiceScheda)
     references PERSONAGGIO;

alter table PROPENSIONE add constraint FKPRO_SKI
     foreign key (NomeBackground)
     references BACKGROUND;

alter table PROPENSIONE add constraint FKPRO_BAC
     foreign key (NomeSkill)
     references SKILL;

alter table RAZZA add constraint FKSOTTORAZZA
     foreign key (NomeRazzaPadre)
     references RAZZA;

alter table RISORSA_CLASSE add constraint FKFORNITURA
     foreign key (NomeClasse)
     references CLASSE;

--Not implemented
--alter table SCHEDA add constraint ID_SCHEDA_CHK
--     check(exists(select * from POSSESSO
--                  where POSSESSO.CodiceScheda = CodiceScheda)); 

alter table SCHEDA add constraint FKCREAZIONE
     foreign key (UsernameCreatore)
     references UTENTE;

alter table SCHEDA add constraint FKPRESENZA
     foreign key (UsernameMaster, NomeCampagna)
     references CAMPAGNA;

alter table SESSIONE add constraint FKCOMPOSIZIONE
     foreign key (Username, NomeCampagna)
     references CAMPAGNA;

alter table SKILL add constraint FKBASE
     foreign key (NomeCaratteristica)
     references CARATTERISTICA;

alter table SOTTOCLASSE add constraint FKSVILUPPO
     foreign key (NomeClasse)
     references CLASSE;

alter table STATO_ATTIVO add constraint FKATTIVAZIONE
     foreign key (Nome)
     references EFFETTO_STATO;

alter table STATO_ATTIVO add constraint FKAFFLIZIONE
     foreign key (CodiceScheda, AFF_Username, AFF_Nome, AFF_DataSvolgimento, AFF_NumCombattimento, AFF_Numero)
     references ISTANZA_COMB;

alter table STATO_ATTIVO add constraint FKCAUSA_FK
     foreign key (SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione)
     references AZIONE;

alter table STATO_ATTIVO add constraint FKCAUSA_CHK
     check((SUD_Username is not null and SUD_Nome is not null and SUD_DataSvolgimento is not null and SUD_NumCombattimento is not null and SUD_NumTurno is not null and NumAzione is not null)
           or (SUD_Username is null and SUD_Nome is null and SUD_DataSvolgimento is null and SUD_NumCombattimento is null and SUD_NumTurno is null and NumAzione is null)); 

alter table TAG_MAGIA add constraint FKTAG_MAG
     foreign key (CodiceMagia)
     references MAGIA;

alter table TAG_MAGIA add constraint FKTAG_SES_2
     foreign key (Username, NomeCampagna, DataSvolgimento)
     references SESSIONE;

alter table TAG_OGGETTO add constraint FKTAG_OGG
     foreign key (CodiceOggetto)
     references OGGETTO;

alter table TAG_OGGETTO add constraint FKTAG_SES_1
     foreign key (Username, NomeCampagna, DataSvolgimento)
     references SESSIONE;

alter table TAG_PARTECIPANTE add constraint FKTAG_SCH
     foreign key (CodiceScheda)
     references SCHEDA;

alter table TAG_PARTECIPANTE add constraint FKTAG_SES
     foreign key (Username, NomeCampagna, DataSvolgimento)
     references SESSIONE;

alter table TRATTO_CLASSE add constraint FKOFFERTA
     foreign key (NomeClasse)
     references CLASSE;

--Not implemented
--alter table TURNO add constraint ID_TURNO_CHK
--     check(exists(select * from AZIONE
--                  where AZIONE.Username = Username and AZIONE.NomeCampagna = Nome and AZIONE.DataSvolgimento = DataSvolgimento and AZIONE.NumCombattimento = NumCombattimento and AZIONE.NumTurno = NumTurno)); 

alter table TURNO add constraint FKCOMPOSIZIONE_COMB
     foreign key (Username, Nome, DataSvolgimento, NumCombattimento)
     references COMBATTIMENTO;


-- Index Section
-- _____________ 

