-- *********************************************
-- * SQL PostgreSQL generation                 
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.2              
-- * Generator date: Sep 20 2021              
-- * Generation date: Mon Aug 24 16:06:26 2026 
-- * LUN file: /home/andrea/studio/database/DungeonSQL/report/docs/DungeonSQL+.lun 
-- * Schema: RELATIONAL/SQL 
-- ********************************************* 


-- Database Section
-- ________________ 


create database RELATIONAL;


-- Tables Section
-- _____________ 

create table ABILITAZIONE_RISORSA (
     CodiceScheda VARCHAR(255) not null,
     RIF_NomeClasse VARCHAR(255) not null,
     FOR_NomeClasse VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     constraint ID_ABILITAZIONE_RISORSA primary key (CodiceScheda, RIF_NomeClasse, FOR_NomeClasse, Nome));

create table ABILITAZIONE_TRATTO (
     CodiceScheda VARCHAR(255) not null,
     RIF_NomeClasse VARCHAR(255) not null,
     OFF_NomeClasse VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     MaxQuantita VARCHAR(255) not null,
     Quantita VARCHAR(255) not null,
     constraint ID_ABILITAZIONE_TRATTO primary key (OFF_NomeClasse, Nome, CodiceScheda, RIF_NomeClasse));

create table APPRENDIMENTO (
     NomeClasse VARCHAR(255) not null,
     CodiceMagia VARCHAR(255) not null,
     constraint ID_APPRENDIMENTO primary key (CodiceMagia, NomeClasse));

create table AZIONE (
     SUD_Username VARCHAR(255) not null,
     SUD_Nome VARCHAR(255) not null,
     SUD_DataSvolgimento VARCHAR(255) not null,
     SUD_NumCombattimento VARCHAR(255) not null,
     SUD_NumTurno VARCHAR(255) not null,
     NumAzione VARCHAR(255) not null,
     Tipo VARCHAR(255) not null,
     CodiceOggetto VARCHAR(255),
     CodiceMagia VARCHAR(255),
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     NumCombattimento VARCHAR(255) not null,
     Numero VARCHAR(255) not null,
     constraint ID_AZIONE primary key (SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione));

create table BACKGROUND (
     Nome VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     constraint ID_BACKGROUND_ID primary key (Nome));

create table CAMPAGNA (
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     DataInizio VARCHAR(255) not null,
     constraint ID_CAMPAGNA primary key (Username, Nome));

create table CAPACITA (
     CodiceScheda VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     LivelloCapacita VARCHAR(255) not null,
     constraint ID_CAPACITA primary key (Nome, CodiceScheda));

create table CARATTERISTICA (
     Nome VARCHAR(255) not null,
     constraint ID_CARATTERISTICA_ID primary key (Nome));

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
     SUD_Username VARCHAR(255) not null,
     SUD_Nome VARCHAR(255) not null,
     SUD_DataSvolgimento VARCHAR(255) not null,
     SUD_NumCombattimento VARCHAR(255) not null,
     SUD_NumTurno VARCHAR(255) not null,
     NumAzione VARCHAR(255) not null,
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     NumCombattimento VARCHAR(255) not null,
     Numero VARCHAR(255) not null,
     Danno VARCHAR(255) not null,
     Esito VARCHAR(255) not null,
     Sconfitto VARCHAR(255) not null,
     constraint ID_CONTRO primary key (Username, Nome, DataSvolgimento, NumCombattimento, Numero, SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione));

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
     Numero VARCHAR(255) not null,
     Iniziativa VARCHAR(255) not null,
     HP VARCHAR(255) not null,
     constraint ID_ISTANZA_COMB_ID primary key (Username, Nome, DataSvolgimento, NumCombattimento, Numero));

create table ISTANZA_XOR (
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     NumCombattimento VARCHAR(255) not null,
     Numero VARCHAR(255) not null,
     I_P_CodiceScheda VARCHAR(255) not null,
     I_M_CodiceScheda VARCHAR(255) not null,
     constraint ID_ISTAN_ISTAN_ID primary key (Username, Nome, DataSvolgimento, NumCombattimento, Numero));

create table MAGIA (
     CodiceMagia VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     Livello VARCHAR(255) not null,
     Rituale VARCHAR(255) not null,
     CRE_Username VARCHAR(255),
     CRE_Nome VARCHAR(255),
     constraint ID_MAGIA_ID primary key (CodiceMagia));

create table MOSTRO (
     CodiceScheda VARCHAR(255) not null,
     CR VARCHAR(255) not null,
     ExpRilasciata VARCHAR(255) not null,
     Velocita VARCHAR(255) not null,
     constraint ID_MOSTR_SCHED_ID primary key (CodiceScheda));

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
     CRE_Username VARCHAR(255),
     CRE_Nome VARCHAR(255),
     constraint ID_OGGETTO primary key (CodiceOggetto));

create table PERSONAGGIO (
     CodiceScheda VARCHAR(255) not null,
     Allineamento VARCHAR(255) not null,
     HP VARCHAR(255) not null,
     ExpAccumulata VARCHAR(255) not null,
     FON_Nome VARCHAR(255) not null,
     APP_Nome VARCHAR(255) not null,
     constraint ID_PERSO_SCHED_ID primary key (CodiceScheda));

create table POSSESSO (
     Nome VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     Punteggio VARCHAR(255) not null,
     CompetenzaSalvezza VARCHAR(255) not null,
     constraint ID_POSSESSO primary key (Nome, CodiceScheda));

create table PROGRESSO (
     CodiceScheda VARCHAR(255) not null,
     RIF_NomeClasse VARCHAR(255) not null,
     Livello VARCHAR(255) not null,
     NomeClasse VARCHAR(255),
     NomeSottoclasse VARCHAR(255),
     constraint ID_PROGRESSO primary key (CodiceScheda, RIF_NomeClasse));

create table PROPENSIONE (
     NomeSkill VARCHAR(255) not null,
     NomeBackground VARCHAR(255) not null,
     constraint ID_PROPENSIONE primary key (NomeBackground, NomeSkill));

create table RAZZA (
     Nome VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     VelocitaBase VARCHAR(255) not null,
     Scurovisione VARCHAR(255) not null,
     Sopra VARCHAR(255) not null,
     constraint ID_RAZZA primary key (Nome));

create table RISORSA_CLASSE (
     NomeClasse VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     Recupero VARCHAR(255) not null,
     constraint ID_RISORSA_CLASSE primary key (NomeClasse, Nome));

create table SCHEDA (
     CodiceScheda VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     MaxHP VARCHAR(255) not null,
     CA VARCHAR(255) not null,
     Taglia VARCHAR(255) not null,
     CRE_Username VARCHAR(255) not null,
     PRE_Username VARCHAR(255) not null,
     PRE_Nome VARCHAR(255) not null,
     constraint ID_SCHEDA_ID primary key (CodiceScheda));

create table SESSIONE (
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     Diario VARCHAR(255),
     constraint ID_SESSIONE primary key (Username, Nome, DataSvolgimento));

create table SKILL (
     Nome VARCHAR(255) not null,
     BAS_Nome VARCHAR(255) not null,
     constraint ID_SKILL primary key (Nome));

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
     SUD_Username VARCHAR(255),
     SUD_Nome VARCHAR(255),
     SUD_DataSvolgimento VARCHAR(255),
     SUD_NumCombattimento VARCHAR(255),
     SUD_NumTurno VARCHAR(255),
     NumAzione VARCHAR(255),
     constraint ID_STATO_ATTIVO primary key (Nome, Numero));

create table TAG_MAGIA (
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     CodiceMagia VARCHAR(255) not null,
     constraint ID_TAG_MAGIA primary key (CodiceMagia, Username, Nome, DataSvolgimento));

create table TAG_OGGETTO (
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     CodiceOggetto VARCHAR(255) not null,
     constraint ID_TAG_OGGETTO primary key (CodiceOggetto, Username, Nome, DataSvolgimento));

create table TAG_PARTECIPANTE (
     Username VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     DataSvolgimento VARCHAR(255) not null,
     CodiceScheda VARCHAR(255) not null,
     constraint ID_TAG_PARTECIPANTE primary key (CodiceScheda, Username, Nome, DataSvolgimento));

create table TRATTO_CLASSE (
     NomeClasse VARCHAR(255) not null,
     Nome VARCHAR(255) not null,
     Descrizione VARCHAR(255) not null,
     LivelloRichiesto VARCHAR(255) not null,
     constraint ID_TRATTO_CLASSE primary key (NomeClasse, Nome));

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

alter table ABILITAZIONE_RISORSA add constraint REF_ABILI_RISOR_FK
     foreign key (FOR_NomeClasse, Nome)
     references RISORSA_CLASSE;

alter table ABILITAZIONE_RISORSA add constraint REF_ABILI_PROGR_1
     foreign key (CodiceScheda, RIF_NomeClasse)
     references PROGRESSO;

alter table ABILITAZIONE_TRATTO add constraint REF_ABILI_TRATT
     foreign key (OFF_NomeClasse, Nome)
     references TRATTO_CLASSE;

alter table ABILITAZIONE_TRATTO add constraint REF_ABILI_PROGR_FK
     foreign key (CodiceScheda, RIF_NomeClasse)
     references PROGRESSO;

alter table APPRENDIMENTO add constraint EQU_APPRE_MAGIA
     foreign key (CodiceMagia)
     references MAGIA;

alter table APPRENDIMENTO add constraint REF_APPRE_CLASS_FK
     foreign key (NomeClasse)
     references CLASSE;

alter table AZIONE add constraint REF_AZION_OGGET_FK
     foreign key (CodiceOggetto)
     references OGGETTO;

alter table AZIONE add constraint REF_AZION_MAGIA_FK
     foreign key (CodiceMagia)
     references MAGIA;

alter table AZIONE add constraint REF_AZION_ISTAN_FK
     foreign key (Username, Nome, DataSvolgimento, NumCombattimento, Numero)
     references ISTANZA_COMB;

alter table AZIONE add constraint EQU_AZION_TURNO
     foreign key (SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno)
     references TURNO;

--Not implemented
--alter table BACKGROUND add constraint ID_BACKGROUND_CHK
--     check(exists(select * from PROPENSIONE
--                  where PROPENSIONE.NomeSkill = Nome)); 

alter table CAMPAGNA add constraint REF_CAMPA_UTENT
     foreign key (Username)
     references UTENTE;

alter table CAPACITA add constraint REF_CAPAC_SKILL
     foreign key (Nome)
     references SKILL;

alter table CAPACITA add constraint REF_CAPAC_SCHED_FK
     foreign key (CodiceScheda)
     references SCHEDA;

--Not implemented
--alter table CARATTERISTICA add constraint ID_CARATTERISTICA_CHK
--     check(exists(select * from SKILL
--                  where SKILL.BAS_Nome = Nome)); 

--Not implemented
--alter table COMBATTIMENTO add constraint ID_COMBATTIMENTO_CHK
--     check(exists(select * from TURNO
--                  where TURNO.Username = Username and TURNO.Nome = Nome and TURNO.DataSvolgimento = DataSvolgimento and TURNO.NumCombattimento = NumCombattimento)); 

--Not implemented
--alter table COMBATTIMENTO add constraint ID_COMBATTIMENTO_CHK
--     check(exists(select * from ISTANZA_COMB
--                  where ISTANZA_COMB.Username = Username and ISTANZA_COMB.Nome = Nome and ISTANZA_COMB.DataSvolgimento = DataSvolgimento and ISTANZA_COMB.NumCombattimento = NumCombattimento)); 

alter table COMBATTIMENTO add constraint REF_COMBA_SESSI
     foreign key (Username, Nome, DataSvolgimento)
     references SESSIONE;

alter table CONOSCENZA add constraint REF_CONOS_SCHED
     foreign key (CodiceScheda)
     references SCHEDA;

alter table CONOSCENZA add constraint REF_CONOS_MAGIA_FK
     foreign key (CodiceMagia)
     references MAGIA;

alter table CONTRO add constraint REF_CONTR_ISTAN
     foreign key (Username, Nome, DataSvolgimento, NumCombattimento, Numero)
     references ISTANZA_COMB;

alter table CONTRO add constraint REF_CONTR_AZION_FK
     foreign key (SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione)
     references AZIONE;

alter table INVENTARIO add constraint REF_INVEN_SCHED
     foreign key (CodiceScheda)
     references SCHEDA;

alter table INVENTARIO add constraint REF_INVEN_OGGET_FK
     foreign key (CodiceOggetto)
     references OGGETTO;

--Not implemented
--alter table ISTANZA_COMB add constraint ID_ISTANZA_COMB_CHK
--     check(exists(select * from ISTANZA_XOR
--                  where ISTANZA_XOR.Username = Username and ISTANZA_XOR.Nome = Nome and ISTANZA_XOR.DataSvolgimento = DataSvolgimento and ISTANZA_XOR.NumCombattimento = NumCombattimento and ISTANZA_XOR.Numero = Numero)); 

alter table ISTANZA_COMB add constraint EQU_ISTAN_COMBA
     foreign key (Username, Nome, DataSvolgimento, NumCombattimento)
     references COMBATTIMENTO;

alter table ISTANZA_XOR add constraint REF_ISTAN_PERSO_FK
     foreign key (I_P_CodiceScheda)
     references PERSONAGGIO;

alter table ISTANZA_XOR add constraint REF_ISTAN_MOSTR_FK
     foreign key (I_M_CodiceScheda)
     references MOSTRO;

alter table ISTANZA_XOR add constraint ID_ISTAN_ISTAN_FK
     foreign key (Username, Nome, DataSvolgimento, NumCombattimento, Numero)
     references ISTANZA_COMB;

--Not implemented
--alter table MAGIA add constraint ID_MAGIA_CHK
--     check(exists(select * from APPRENDIMENTO
--                  where APPRENDIMENTO.CodiceMagia = CodiceMagia)); 

alter table MAGIA add constraint REF_MAGIA_CAMPA_FK
     foreign key (CRE_Username, CRE_Nome)
     references CAMPAGNA;

alter table MAGIA add constraint REF_MAGIA_CAMPA_CHK
     check((CRE_Username is not null and CRE_Nome is not null)
           or (CRE_Username is null and CRE_Nome is null)); 

alter table MOSTRO add constraint ID_MOSTR_SCHED_FK
     foreign key (CodiceScheda)
     references SCHEDA;

alter table OGGETTO add constraint REF_OGGET_CAMPA_FK
     foreign key (CRE_Username, CRE_Nome)
     references CAMPAGNA;

alter table OGGETTO add constraint REF_OGGET_CAMPA_CHK
     check((CRE_Username is not null and CRE_Nome is not null)
           or (CRE_Username is null and CRE_Nome is null)); 

alter table PERSONAGGIO add constraint ID_PERSO_SCHED_FK
     foreign key (CodiceScheda)
     references SCHEDA;

--Not implemented
--alter table PERSONAGGIO add constraint ID_PERSO_SCHED_CHK
--     check(exists(select * from PROGRESSO
--                  where PROGRESSO.CodiceScheda = CodiceScheda)); 

alter table PERSONAGGIO add constraint REF_PERSO_BACKG_FK
     foreign key (FON_Nome)
     references BACKGROUND;

alter table PERSONAGGIO add constraint REF_PERSO_RAZZA_FK
     foreign key (APP_Nome)
     references RAZZA;

alter table POSSESSO add constraint EQU_POSSE_SCHED_FK
     foreign key (CodiceScheda)
     references SCHEDA;

alter table POSSESSO add constraint REF_POSSE_CARAT
     foreign key (Nome)
     references CARATTERISTICA;

alter table PROGRESSO add constraint REF_PROGR_SOTTO_FK
     foreign key (NomeClasse, NomeSottoclasse)
     references SOTTOCLASSE;

alter table PROGRESSO add constraint REF_PROGR_SOTTO_CHK
     check((NomeClasse is not null and NomeSottoclasse is not null)
           or (NomeClasse is null and NomeSottoclasse is null)); 

alter table PROGRESSO add constraint REF_PROGR_CLASS_FK
     foreign key (RIF_NomeClasse)
     references CLASSE;

alter table PROGRESSO add constraint EQU_PROGR_PERSO
     foreign key (CodiceScheda)
     references PERSONAGGIO;

alter table PROPENSIONE add constraint REF_PROPE_SKILL
     foreign key (NomeBackground)
     references SKILL;

alter table PROPENSIONE add constraint EQU_PROPE_BACKG_FK
     foreign key (NomeSkill)
     references BACKGROUND;

alter table RAZZA add constraint REF_RAZZA_RAZZA_FK
     foreign key (Sopra)
     references RAZZA;

alter table RISORSA_CLASSE add constraint REF_RISOR_CLASS
     foreign key (NomeClasse)
     references CLASSE;

--Not implemented
--alter table SCHEDA add constraint ID_SCHEDA_CHK
--     check(exists(select * from POSSESSO
--                  where POSSESSO.CodiceScheda = CodiceScheda)); 

alter table SCHEDA add constraint REF_SCHED_UTENT_FK
     foreign key (CRE_Username)
     references UTENTE;

alter table SCHEDA add constraint REF_SCHED_CAMPA_FK
     foreign key (PRE_Username, PRE_Nome)
     references CAMPAGNA;

alter table SESSIONE add constraint REF_SESSI_CAMPA
     foreign key (Username, Nome)
     references CAMPAGNA;

alter table SKILL add constraint EQU_SKILL_CARAT_FK
     foreign key (BAS_Nome)
     references CARATTERISTICA;

alter table SOTTOCLASSE add constraint REF_SOTTO_CLASS
     foreign key (NomeClasse)
     references CLASSE;

alter table STATO_ATTIVO add constraint REF_STATO_EFFET
     foreign key (Nome)
     references EFFETTO_STATO;

alter table STATO_ATTIVO add constraint REF_STATO_ISTAN_FK
     foreign key (AFF_Username, AFF_Nome, AFF_DataSvolgimento, AFF_NumCombattimento, AFF_Numero)
     references ISTANZA_COMB;

alter table STATO_ATTIVO add constraint REF_STATO_AZION_FK
     foreign key (SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione)
     references AZIONE;

alter table STATO_ATTIVO add constraint REF_STATO_AZION_CHK
     check((SUD_Username is not null and SUD_Nome is not null and SUD_DataSvolgimento is not null and SUD_NumCombattimento is not null and SUD_NumTurno is not null and NumAzione is not null)
           or (SUD_Username is null and SUD_Nome is null and SUD_DataSvolgimento is null and SUD_NumCombattimento is null and SUD_NumTurno is null and NumAzione is null)); 

alter table TAG_MAGIA add constraint REF_TAG_M_MAGIA
     foreign key (CodiceMagia)
     references MAGIA;

alter table TAG_MAGIA add constraint REF_TAG_M_SESSI_FK
     foreign key (Username, Nome, DataSvolgimento)
     references SESSIONE;

alter table TAG_OGGETTO add constraint REF_TAG_O_OGGET
     foreign key (CodiceOggetto)
     references OGGETTO;

alter table TAG_OGGETTO add constraint REF_TAG_O_SESSI_FK
     foreign key (Username, Nome, DataSvolgimento)
     references SESSIONE;

alter table TAG_PARTECIPANTE add constraint REF_TAG_P_SCHED
     foreign key (CodiceScheda)
     references SCHEDA;

alter table TAG_PARTECIPANTE add constraint REF_TAG_P_SESSI_FK
     foreign key (Username, Nome, DataSvolgimento)
     references SESSIONE;

alter table TRATTO_CLASSE add constraint REF_TRATT_CLASS
     foreign key (NomeClasse)
     references CLASSE;

--Not implemented
--alter table TURNO add constraint ID_TURNO_CHK
--     check(exists(select * from AZIONE
--                  where AZIONE.SUD_Username = Username and AZIONE.SUD_Nome = Nome and AZIONE.SUD_DataSvolgimento = DataSvolgimento and AZIONE.SUD_NumCombattimento = NumCombattimento and AZIONE.SUD_NumTurno = NumTurno)); 

alter table TURNO add constraint EQU_TURNO_COMBA
     foreign key (Username, Nome, DataSvolgimento, NumCombattimento)
     references COMBATTIMENTO;


-- Index Section
-- _____________ 

create index REF_ABILI_RISOR_IND
     on ABILITAZIONE_RISORSA (FOR_NomeClasse, Nome);

create index REF_ABILI_PROGR_IND
     on ABILITAZIONE_TRATTO (CodiceScheda, RIF_NomeClasse);

create index REF_APPRE_CLASS_IND
     on APPRENDIMENTO (NomeClasse);

create index REF_AZION_OGGET_IND
     on AZIONE (CodiceOggetto);

create index REF_AZION_MAGIA_IND
     on AZIONE (CodiceMagia);

create index REF_AZION_ISTAN_IND
     on AZIONE (Username, Nome, DataSvolgimento, NumCombattimento, Numero);

create index REF_CAPAC_SCHED_IND
     on CAPACITA (CodiceScheda);

create index REF_CONOS_MAGIA_IND
     on CONOSCENZA (CodiceMagia);

create index REF_CONTR_AZION_IND
     on CONTRO (SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione);

create index REF_INVEN_OGGET_IND
     on INVENTARIO (CodiceOggetto);

create index REF_ISTAN_PERSO_IND
     on ISTANZA_XOR (I_P_CodiceScheda);

create index REF_ISTAN_MOSTR_IND
     on ISTANZA_XOR (I_M_CodiceScheda);

create index REF_MAGIA_CAMPA_IND
     on MAGIA (CRE_Username, CRE_Nome);

create index REF_OGGET_CAMPA_IND
     on OGGETTO (CRE_Username, CRE_Nome);

create index REF_PERSO_BACKG_IND
     on PERSONAGGIO (FON_Nome);

create index REF_PERSO_RAZZA_IND
     on PERSONAGGIO (APP_Nome);

create index EQU_POSSE_SCHED_IND
     on POSSESSO (CodiceScheda);

create index REF_PROGR_SOTTO_IND
     on PROGRESSO (NomeClasse, NomeSottoclasse);

create index REF_PROGR_CLASS_IND
     on PROGRESSO (RIF_NomeClasse);

create index EQU_PROPE_BACKG_IND
     on PROPENSIONE (NomeSkill);

create index REF_RAZZA_RAZZA_IND
     on RAZZA (Sopra);

create index REF_SCHED_UTENT_IND
     on SCHEDA (CRE_Username);

create index REF_SCHED_CAMPA_IND
     on SCHEDA (PRE_Username, PRE_Nome);

create index EQU_SKILL_CARAT_IND
     on SKILL (BAS_Nome);

create index REF_STATO_ISTAN_IND
     on STATO_ATTIVO (AFF_Username, AFF_Nome, AFF_DataSvolgimento, AFF_NumCombattimento, AFF_Numero);

create index REF_STATO_AZION_IND
     on STATO_ATTIVO (SUD_Username, SUD_Nome, SUD_DataSvolgimento, SUD_NumCombattimento, SUD_NumTurno, NumAzione);

create index REF_TAG_M_SESSI_IND
     on TAG_MAGIA (Username, Nome, DataSvolgimento);

create index REF_TAG_O_SESSI_IND
     on TAG_OGGETTO (Username, Nome, DataSvolgimento);

create index REF_TAG_P_SESSI_IND
     on TAG_PARTECIPANTE (Username, Nome, DataSvolgimento);

