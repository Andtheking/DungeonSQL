-- *********************************************
-- * SQL PostgreSQL generation                 
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.2              
-- * Generator date: Sep 20 2021              
-- * Generation date: Mon Aug 24 19:45:55 2026 
-- * LUN file: /home/andrea/studio/database/DungeonSQL/report/docs/DungeonSQL+.lun 
-- * Schema: RELATIONAL/1 
-- ********************************************* 


-- Database Section
-- ________________ 

create database RELATIONAL;


-- Tables Section
-- _____________ 

create table ABILITAZIONE_RISORSA (
     CodiceScheda char(1) not null,
     NomeClasse char(1) not null,
     NomeRisorsa char(1) not null,
     constraint ID_ABILITAZIONE_RISORSA primary key (CodiceScheda, NomeClasse, NomeRisorsa));

create table ABILITAZIONE_TRATTO (
     CodiceScheda char(1) not null,
     NomeClasse char(1) not null,
     NomeTratto char(1) not null,
     MaxQuantita char(1) not null,
     Quantita char(1) not null,
     constraint ID_ABILITAZIONE_TRATTO primary key (NomeTratto, CodiceScheda, NomeClasse));

create table APPRENDIMENTO (
     NomeClasse char(1) not null,
     CodiceMagia char(1) not null,
     constraint ID_APPRENDIMENTO primary key (CodiceMagia, NomeClasse));

create table AZIONE (
     Username char(1) not null,
     NomeCampagna char(1) not null,
     DataSvolgimento char(1) not null,
     NumCombattimento char(1) not null,
     NumTurno char(1) not null,
     NumAzione char(1) not null,
     Tipo char(1) not null,
     CodiceOggetto char(1),
     CodiceMagia char(1),
     NumeroIstanza char(1) not null,
     constraint ID_AZIONE primary key (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione));

create table BACKGROUND (
     Nome char(1) not null,
     Descrizione char(1) not null,
     constraint ID_BACKGROUND_ID primary key (Nome));

create table CAMPAGNA (
     UsernameMaster char(1) not null,
     Nome char(1) not null,
     Descrizione char(1) not null,
     DataInizio char(1) not null,
     constraint ID_CAMPAGNA primary key (UsernameMaster, Nome));

create table CAPACITA (
     CodiceScheda char(1) not null,
     NomeSkill char(1) not null,
     LivelloCapacita char(1) not null,
     constraint ID_CAPACITA primary key (NomeSkill, CodiceScheda));

create table CARATTERISTICA (
     Nome char(1) not null,
     constraint ID_CARATTERISTICA_ID primary key (Nome));

create table CLASSE (
     NomeClasse char(1) not null,
     constraint ID_CLASSE primary key (NomeClasse));

create table COMBATTIMENTO (
     Username char(1) not null,
     Nome char(1) not null,
     DataSvolgimento char(1) not null,
     NumCombattimento char(1) not null,
     constraint ID_COMBATTIMENTO_ID primary key (Username, Nome, DataSvolgimento, NumCombattimento));

create table CONOSCENZA (
     CodiceMagia char(1) not null,
     CodiceScheda char(1) not null,
     constraint ID_CONOSCENZA primary key (CodiceScheda, CodiceMagia));

create table CONTRO (
     Username char(1) not null,
     NomeCampagna char(1) not null,
     DataSvolgimento char(1) not null,
     NumCombattimento char(1) not null,
     NumTurno char(1) not null,
     NumAzione char(1) not null,
     NumeroIstanza char(1) not null,
     Danno char(1) not null,
     Esito char(1) not null,
     Sconfitto char(1) not null,
     constraint ID_CONTRO primary key (NumeroIstanza, Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno, NumAzione));

create table EFFETTO_STATO (
     Nome char(1) not null,
     Descrizione char(1) not null,
     constraint ID_EFFETTO_STATO primary key (Nome));

create table INVENTARIO (
     CodiceOggetto char(1) not null,
     CodiceScheda char(1) not null,
     Quantita char(1) not null,
     constraint ID_INVENTARIO primary key (CodiceScheda, CodiceOggetto));

create table ISTANZA_COMB (
     Username char(1) not null,
     Nome char(1) not null,
     DataSvolgimento char(1) not null,
     NumCombattimento char(1) not null,
     Numero char(1) not null,
     Iniziativa char(1) not null,
     HP char(1) not null,
     CodiceScheda char(1) not null,
     constraint ID_ISTANZA_COMB_ID primary key (Username, Nome, DataSvolgimento, NumCombattimento, Numero));

create table ISTANZA_XOR (
     Username char(1) not null,
     Nome char(1) not null,
     DataSvolgimento char(1) not null,
     NumCombattimento char(1) not null,
     Numero char(1) not null,
     CodiceSchedaPersonaggio char(1) not null,
     CodiceSchedaMostro char(1) not null,
     constraint FKIST_IST_ID primary key (Username, Nome, DataSvolgimento, NumCombattimento, Numero));

create table MAGIA (
     CodiceMagia char(1) not null,
     Nome char(1) not null,
     Descrizione char(1) not null,
     Livello char(1) not null,
     Rituale char(1) not null,
     UsernameMasterCampagna char(1),
     NomeCampagna char(1),
     constraint ID_MAGIA_ID primary key (CodiceMagia));

create table MOSTRO (
     CodiceScheda char(1) not null,
     CR char(1) not null,
     ExpRilasciata char(1) not null,
     Velocita char(1) not null,
     constraint FKNATURA_MOSTRO_ID primary key (CodiceScheda));

create table OGGETTO (
     CodiceOggetto char(1) not null,
     Nome char(1) not null,
     Descrizione char(1) not null,
     Peso char(1) not null,
     EffettoMagico char(1) not null,
     TipoOggetto char(1) not null,
     Danno char(1),
     TipoArma char(1),
     ProprietaArma char(1),
     BonusCA char(1),
     ReqArmatura char(1),
     Furtiva char(1),
     EffettoCons char(1),
     DurataCons char(1),
     UsernameMasterCampagna char(1),
     NomeCampagna char(1),
     constraint ID_OGGETTO primary key (CodiceOggetto));

create table PERSONAGGIO (
     CodiceScheda char(1) not null,
     Allineamento char(1) not null,
     HP char(1) not null,
     ExpAccumulata char(1) not null,
     NomeBackground char(1) not null,
     NomeRazza char(1) not null,
     constraint FKNATURA_PERSONAGGIO_ID primary key (CodiceScheda));

create table POSSESSO (
     NomeCaratteristica char(1) not null,
     CodiceScheda char(1) not null,
     Punteggio char(1) not null,
     CompetenzaSalvezza char(1) not null,
     constraint ID_POSSESSO primary key (NomeCaratteristica, CodiceScheda));

create table PROGRESSO (
     CodiceScheda char(1) not null,
     NomeClasse char(1) not null,
     Livello char(1) not null,
     NomeSottoclasse char(1),
     constraint ID_PROGRESSO primary key (CodiceScheda, NomeClasse));

create table PROPENSIONE (
     NomeSkill char(1) not null,
     NomeBackground char(1) not null,
     constraint ID_PROPENSIONE primary key (NomeBackground, NomeSkill));

create table RAZZA (
     Nome char(1) not null,
     Descrizione char(1) not null,
     VelocitaBase char(1) not null,
     Scurovisione char(1) not null,
     NomeRazzaPadre char(1),
     constraint ID_RAZZA primary key (Nome));

create table RISORSA_CLASSE (
     NomeClasse char(1) not null,
     Nome char(1) not null,
     Recupero char(1) not null,
     constraint ID_RISORSA_CLASSE primary key (NomeClasse, Nome));

create table SCHEDA (
     CodiceScheda char(1) not null,
     Nome char(1) not null,
     MaxHP char(1) not null,
     CA char(1) not null,
     Taglia char(1) not null,
     UsernameCreatore char(1) not null,
     UsernameMaster char(1) not null,
     NomeCampagna char(1) not null,
     constraint ID_SCHEDA_ID primary key (CodiceScheda));

create table SESSIONE (
     Username char(1) not null,
     NomeCampagna char(1) not null,
     DataSvolgimento char(1) not null,
     Diario char(1),
     constraint ID_SESSIONE primary key (Username, NomeCampagna, DataSvolgimento));

create table SKILL (
     Nome char(1) not null,
     NomeCaratteristica char(1) not null,
     constraint ID_SKILL primary key (Nome));

create table SOTTOCLASSE (
     NomeClasse char(1) not null,
     NomeSottoclasse char(1) not null,
     constraint ID_SOTTOCLASSE primary key (NomeClasse, NomeSottoclasse));

create table STATO_ATTIVO (
     Nome char(1) not null,
     Numero char(1) not null,
     Scaduto char(1) not null,
     Durata char(1) not null,
     Note char(1),
     AFF_Username char(1) not null,
     AFF_Nome char(1) not null,
     AFF_DataSvolgimento char(1) not null,
     AFF_NumCombattimento char(1) not null,
     AFF_Numero char(1) not null,
     SUD_Username char(1),
     SUD_Nome char(1),
     SUD_DataSvolgimento char(1),
     SUD_NumCombattimento char(1),
     SUD_NumTurno char(1),
     NumAzione char(1),
     constraint ID_STATO_ATTIVO primary key (Nome, Numero));

create table TAG_MAGIA (
     Username char(1) not null,
     NomeCampagna char(1) not null,
     DataSvolgimento char(1) not null,
     CodiceMagia char(1) not null,
     constraint ID_TAG_MAGIA primary key (CodiceMagia, Username, NomeCampagna, DataSvolgimento));

create table TAG_OGGETTO (
     Username char(1) not null,
     NomeCampagna char(1) not null,
     DataSvolgimento char(1) not null,
     CodiceOggetto char(1) not null,
     constraint ID_TAG_OGGETTO primary key (CodiceOggetto, Username, NomeCampagna, DataSvolgimento));

create table TAG_PARTECIPANTE (
     Username char(1) not null,
     NomeCampagna char(1) not null,
     DataSvolgimento char(1) not null,
     CodiceScheda char(1) not null,
     constraint ID_TAG_PARTECIPANTE primary key (CodiceScheda, Username, NomeCampagna, DataSvolgimento));

create table TRATTO_CLASSE (
     NomeClasse char(1) not null,
     Nome char(1) not null,
     Descrizione char(1) not null,
     LivelloRichiesto char(1) not null,
     constraint ID_TRATTO_CLASSE primary key (NomeClasse, Nome));

create table TURNO (
     Username char(1) not null,
     Nome char(1) not null,
     DataSvolgimento char(1) not null,
     NumCombattimento char(1) not null,
     NumTurno char(1) not null,
     constraint ID_TURNO_ID primary key (Username, Nome, DataSvolgimento, NumCombattimento, NumTurno));

create table UTENTE (
     Username char(1) not null,
     Email char(1) not null,
     Password char(1) not null,
     constraint ID_UTENTE primary key (Username));


-- Constraints Section
-- ___________________ 

alter table ABILITAZIONE_RISORSA add constraint FKABI_RIS
     foreign key (NomeRisorsa)
     references RISORSA_CLASSE;

alter table ABILITAZIONE_RISORSA add constraint FKABI_PRO_1
     foreign key (CodiceScheda, NomeClasse)
     references PROGRESSO;

alter table ABILITAZIONE_TRATTO add constraint FKABI_TRA
     foreign key (NomeTratto)
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
     foreign key (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumeroIstanza)
     references ISTANZA_COMB;

alter table AZIONE add constraint FKSUDDIVISIONE
     foreign key (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumTurno)
     references TURNO;

--Not implemented
--alter table BACKGROUND add constraint ID_BACKGROUND_CHK
--     check(exists(select * from PROPENSIONE
--                  where PROPENSIONE.NomeSkill = Nome)); 

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
--                  where SKILL.NomeCaratteristica = Nome)); 

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
     foreign key (Username, NomeCampagna, DataSvolgimento, NumCombattimento, NumeroIstanza)
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

--Not implemented
--alter table ISTANZA_COMB add constraint ID_ISTANZA_COMB_CHK
--     check(exists(select * from ISTANZA_XOR
--                  where ISTANZA_XOR.Username = Username and ISTANZA_XOR.Nome = Nome and ISTANZA_XOR.DataSvolgimento = DataSvolgimento and ISTANZA_XOR.NumCombattimento = NumCombattimento and ISTANZA_XOR.Numero = Numero)); 

alter table ISTANZA_COMB add constraint FKPARTE
     foreign key (Username, Nome, DataSvolgimento, NumCombattimento)
     references COMBATTIMENTO;

alter table ISTANZA_COMB add constraint GRISTANZA_COMB
     foreign key (CodiceScheda)
     references SCHEDA;

alter table ISTANZA_XOR add constraint FKIST_PER
     foreign key (CodiceSchedaPersonaggio)
     references PERSONAGGIO;

alter table ISTANZA_XOR add constraint FKIST_MOS
     foreign key (CodiceSchedaMostro)
     references MOSTRO;

alter table ISTANZA_XOR add constraint FKIST_IST_FK
     foreign key (Username, Nome, DataSvolgimento, NumCombattimento, Numero)
     references ISTANZA_COMB;

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

alter table PROGRESSO add constraint FKSCELTA_FK
     foreign key (NomeSottoclasse)
     references SOTTOCLASSE;

alter table PROGRESSO add constraint FKSCELTA_CHK
     check((NomeSottoclasse is not null)
           or (NomeSottoclasse is null)); 

alter table PROGRESSO add constraint FKRIFERIMENTO
     foreign key (NomeClasse)
     references CLASSE;

alter table PROGRESSO add constraint FKAPPARTENENZA
     foreign key (CodiceScheda)
     references PERSONAGGIO;

alter table PROPENSIONE add constraint FKPRO_SKI
     foreign key (NomeBackground)
     references SKILL;

alter table PROPENSIONE add constraint FKPRO_BAC
     foreign key (NomeSkill)
     references BACKGROUND;

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
     foreign key (AFF_Username, AFF_Nome, AFF_DataSvolgimento, AFF_NumCombattimento, AFF_Numero)
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

