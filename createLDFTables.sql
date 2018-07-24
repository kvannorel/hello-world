

--#############################################################################
-- SCHEMA: LDF
--#############################################################################

-- Role: ldf
DROP OWNED BY ###LDF### CASCADE;
DROP ROLE IF EXISTS ###LDF###;
CREATE ROLE ###LDF### LOGIN
    ENCRYPTED PASSWORD '###LDF###'
    NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;

ALTER ROLE ###LDF### IN DATABASE ###DB_NAME###
    SET search_path = ###MKS###, ###CIR###, ###COO###,
        ###CGS###, ###PLT###, ###CMG###,
        ###CMS###, ###POSTGIS###, ###LDF###;
GRANT ###DB_OWNER### TO ###LDF###;

--DROP SCHEMA IF EXISTS ###LDF### CASCADE;
--CREATE SCHEMA ###LDF### AUTHORIZATION ###OWNER###;
GRANT ALL ON SCHEMA ###LDF### TO ###OWNER###;
GRANT USAGE ON SCHEMA ###LDF### TO ###USER###;

--sequences LDF


CREATE TABLE IF NOT EXISTS ###LDF###.ldf_overzichten (
  id numeric(18,0) not null,
  ldforg character varying(5), -- maker overzicht
  ldfovz character varying(100) not null, -- titel overzicht
  ldftbl character varying(30) not null, -- tabelnaam voor overzicht
  ldfprs character varying(5) not null, -- coo, cir of beide
  ldfwgv character varying(30), -- weergave lijst of grafiek
  ldfsrt character varying(30), -- sorteerveld
  ldfvld text, -- structuur van tabel met gegevens in json
  ldfmnu character varying(255), -- menustructuur
  ldfsql text, -- sql voor overzicht
  ldfsel text, -- selectie op tabel of sql in json
  ldfrow numeric(10,0), -- maximum aantal records op te halen
  ldfmod character varying(30), -- modulenaam
  ldflic character varying(100), -- licentie
  ldfoms text, -- beschrijving overzicht door klant
  ldfprlg text, -- beschrijving overzicht door prlg
  ldfprf text, -- profielnaam, text field to support multiple roles.
  ldfcol character varying(7), -- kleur grid
  updatedat TEXT, -- updated at
  createdat TEXT, -- created at
  constraint ldf_overzichteni00 primary key (id)
)
with (
  oids=false
);

DROP INDEX IF EXISTS ###LDF###.ldf_overzichteni01;
CREATE INDEX ldf_overzichteni01
  on
###LDF###.ldf_overzichten
  (ldfovz);

 Comment on Table ###LDF###.LDF_OVERZICHTEN is 'Linked Data Framework Overzichtsgegevens';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.id is 'Id';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFORG is 'Maker overzicht';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFOVZ is 'Titel overzicht';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFTBL is 'Tabelnaam voor overzicht';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFPRS is 'COO, CIR of beide';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFWGV is 'Weergave lijst of grafiek';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFSRT is 'Sorteerveld';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFVLD is 'Structuur van tabel met gegevens in JSON';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFMNU is 'Menustructuur';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFSQL is 'SQL';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFSEL is 'Selectie';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFROW is 'Maximum aantal records op te halen';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFMOD is 'Modulenaam';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFLIC is 'Licentie';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFOMS is 'Beschrijving overzicht door klant';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFPRLG is 'Beschrijving overzicht door PRLG';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFPRF is 'Profielnaam';
 Comment on Column ###LDF###.LDF_OVERZICHTEN.LDFCOL is 'Kleur voor rapport grid';

 ALTER TABLE ###LDF###.ldf_overzichten
  OWNER TO ###OWNER###;

DROP TABLE IF EXISTS ###LDF###.job_results_ldf;
CREATE TABLE ###LDF###.job_results_ldf
(
  id serial NOT NULL, -- Sleutel
  jobname character varying(100), -- Taaknaam
  lastrun timestamp without time zone, -- Tijd van laatste uitvoer
  result boolean, -- Resultaat
  CONSTRAINT job_results_ldfi00 PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ###LDF###.job_results_ldf
OWNER TO ###OWNER###;
GRANT ALL ON TABLE ###LDF###.job_results_ldf TO ###OWNER###;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE ###LDF###.job_results_ldf TO ###LDF###;

COMMENT ON TABLE ###LDF###.job_results_ldf IS 'Resultaten job taken';
COMMENT ON COLUMN ###LDF###.job_results_ldf.id IS 'Sleutel';
COMMENT ON COLUMN ###LDF###.job_results_ldf.jobname IS 'Taaknaam';
COMMENT ON COLUMN ###LDF###.job_results_ldf.lastrun IS 'Tijd van laatste uitvoer';
COMMENT ON COLUMN ###LDF###.job_results_ldf.result IS 'Resultaat';

DROP TABLE IF EXISTS ###LDF###.ldf_results;
CREATE TABLE ###LDF###.ldf_results
(
  id serial NOT NULL, -- Sleutel
  reportname character varying(100), -- Taaknaam
  lastrun timestamp without time zone, -- Tijd van laatste uitvoer
  namespace character varying(100), -- Onderdeel van.. of Hoort bij raport/module.
  result boolean, -- Resultaat
  CONSTRAINT ldf_resultsi00 PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ###LDF###.ldf_results
OWNER TO ###OWNER###;
GRANT ALL ON TABLE ###LDF###.ldf_results TO ###OWNER###;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE ###LDF###.ldf_results TO ###LDF###;


COMMENT ON TABLE ###LDF###.ldf_results IS 'Resultaten ldf taken';
COMMENT ON COLUMN ###LDF###.ldf_results.id IS 'Sleutel';
COMMENT ON COLUMN ###LDF###.ldf_results.reportname IS 'Taaknaam';
COMMENT ON COLUMN ###LDF###.ldf_results.lastrun IS 'Tijd van laatste uitvoer';
COMMENT ON COLUMN ###LDF###.ldf_results.namespace IS 'Onderdeel van';
COMMENT ON COLUMN ###LDF###.ldf_results.result IS 'Resultaat';

-- table ldf_velden --
drop table if exists ###LDF###.ldf_velden cascade;
create table ###LDF###.ldf_velden (
  vldid character varying(30) NOT NULL, -- Veldnaam in tabellen
  vldtyp character varying(10), -- Type van het veld
  vldlen numeric(10,0), -- Veldlengte
  vldprec numeric(2,0), -- Aantal decimalen
  vldtitle character varying(100), -- Omschrijving van het veld
  vldsum character varying(1), -- Indicatie of veld in search grid CIR getoons moet worden
  vldgeo character varying(1), -- Indicatie geo veld
  CONSTRAINT ldf_veldeni00 PRIMARY KEY (vldid)
)
WITH (
  OIDS=FALSE
);

create index ldf_veldeni01
  on
###LDF###.ldf_velden
  (vldid);

 Comment on Table ###LDF###.LDF_VELDEN is 'Linked Data Framework Metadata Velden';
 Comment on Column ###LDF###.LDF_VELDEN.VLDID is 'Veldnaam in tabellen';
 Comment on Column ###LDF###.LDF_VELDEN.VLDTYP is 'Type van het veld';
 Comment on Column ###LDF###.LDF_VELDEN.VLDLEN is 'Veldlengte';
 Comment on Column ###LDF###.LDF_VELDEN.VLDPREC is 'Aantal decimalen';
 Comment on Column ###LDF###.LDF_VELDEN.VLDTITLE is 'Omschrijving van het veld';
 Comment on Column ###LDF###.LDF_VELDEN.VLDSUM is 'Indicatie of veld in search grid CIR getoond moet worden';
 Comment on Column ###LDF###.LDF_VELDEN.VLDGEO is 'Indicatie geo veld';

ALTER TABLE ###LDF###.ldf_velden
  OWNER TO ###OWNER###;


-- table anl_afn_lev_adres --
drop table if exists ###LDF###.anl_afn_lev_adres cascade;
create table ###LDF###.anl_afn_lev_adres (
    id                             numeric(14, 0) ,
    afnlevind                      character varying(1) ,
    organisatiecode                character varying(200) ,
    applicatiecode                 character varying(50) ,
    administratie                  character varying(50)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_adresi01
on ###LDF###.anl_afn_lev_adres
(id, afnlevind);
create index anl_afn_lev_adresi02
on ###LDF###.anl_afn_lev_adres
(afnlevind, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_adres IS 'Afnemers en leveranciers bij entiteit adres';
comment on column ###LDF###.anl_afn_lev_adres.afnlevind is 'Indicatie afnemer of leverancier';
comment on column ###LDF###.anl_afn_lev_adres.organisatiecode is 'Organisatiecode';
comment on column ###LDF###.anl_afn_lev_adres.applicatiecode is 'Applicatiecode';
comment on column ###LDF###.anl_afn_lev_adres.administratie is 'Administratie';
ALTER TABLE ###LDF###.anl_afn_lev_adres
  OWNER TO ###OWNER###;


-- table anl_afn_lev_atl_adres --
drop table if exists ###LDF###.anl_afn_lev_atl_adres cascade;
create table ###LDF###.anl_afn_lev_atl_adres (
    id                             numeric(14, 0) ,
    afnemers                       character varying(1050) ,
    leveranciers                   character varying(1050) ,
    aantal_afn                     numeric(6, 0) ,
    aantal_lev                     numeric(6, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_atl_adresi01
on ###LDF###.anl_afn_lev_atl_adres
(id, afnemers);
create index anl_afn_lev_atl_adresi02
on ###LDF###.anl_afn_lev_atl_adres
(afnemers, id);
create index anl_afn_lev_atl_adresi03
on ###LDF###.anl_afn_lev_atl_adres
(leveranciers, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_atl_adres IS 'Aantal Afnemers en leveranciers bij entiteit adres';
comment on column ###LDF###.anl_afn_lev_atl_adres.afnemers is 'Lijst met afnemende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_adres.leveranciers is 'Lijst met leverende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_adres.aantal_afn is 'Aantal afnemerindicaties';
comment on column ###LDF###.anl_afn_lev_atl_adres.aantal_lev is 'Aantal leveranciersindicaties';

ALTER TABLE ###LDF###.anl_afn_lev_atl_adres
  OWNER TO ###OWNER###;

-- table anl_afn_lev_persoon --
drop table if exists ###LDF###.anl_afn_lev_persoon cascade;
create table ###LDF###.anl_afn_lev_persoon (
    id                             numeric(14, 0) ,
    afnlevind                      character varying(1) ,
    organisatiecode                character varying(200) ,
    applicatiecode                 character varying(50) ,
    administratie                  character varying(50)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_persooni01
on ###LDF###.anl_afn_lev_persoon
(id, afnlevind);
create index anl_afn_lev_persooni02
on ###LDF###.anl_afn_lev_persoon
(afnlevind, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_persoon IS 'Afnemers en leveranciers bij entiteit persoon';
comment on column ###LDF###.anl_afn_lev_persoon.afnlevind is 'Indicatie afnemer of leverancier';
comment on column ###LDF###.anl_afn_lev_persoon.organisatiecode is 'Organisatiecode';
comment on column ###LDF###.anl_afn_lev_persoon.applicatiecode is 'Applicatiecode';
comment on column ###LDF###.anl_afn_lev_persoon.administratie is 'Administratie';

ALTER TABLE ###LDF###.anl_afn_lev_persoon
  OWNER TO ###OWNER###;

-- table anl_afn_lev_atl_persoon --
drop table if exists ###LDF###.anl_afn_lev_atl_persoon cascade;
create table ###LDF###.anl_afn_lev_atl_persoon (
    id                             numeric(14, 0) ,
    afnemers                       character varying(1050) ,
    leveranciers                   character varying(1050) ,
    aantal_afn                     numeric(6, 0) ,
    aantal_lev                     numeric(6, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_atl_persooni01
on ###LDF###.anl_afn_lev_atl_persoon
(id, afnemers);
create index anl_afn_lev_atl_persooni02
on ###LDF###.anl_afn_lev_atl_persoon
(afnemers, id);
create index anl_afn_lev_atl_persooni03
on ###LDF###.anl_afn_lev_atl_persoon
(leveranciers, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_atl_persoon IS 'Aantal Afnemers en leveranciers bij entiteit persoon';
comment on column ###LDF###.anl_afn_lev_atl_persoon.afnemers is 'Lijst met afnemende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_persoon.leveranciers is 'Lijst met leverende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_persoon.aantal_afn is 'Aantal afnemerindicaties';
comment on column ###LDF###.anl_afn_lev_atl_persoon.aantal_lev is 'Aantal leveranciersindicaties';
ALTER TABLE ###LDF###.anl_afn_lev_atl_persoon
  OWNER TO ###OWNER###;


-- table anl_afn_lev_bedrijf --
drop table if exists ###LDF###.anl_afn_lev_bedrijf cascade;
create table ###LDF###.anl_afn_lev_bedrijf (
    id                             numeric(14, 0) ,
    afnlevind                      character varying(1) ,
    organisatiecode                character varying(200) ,
    applicatiecode                 character varying(50) ,
    administratie                  character varying(50)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_bedrijfi01
on ###LDF###.anl_afn_lev_bedrijf
(id, afnlevind);
create index anl_afn_lev_bedrijfi02
on ###LDF###.anl_afn_lev_bedrijf
(afnlevind, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_bedrijf IS 'Afnemers en leveranciers bij entiteit bedrijf';
comment on column ###LDF###.anl_afn_lev_bedrijf.afnlevind is 'Indicatie afnemer of leverancier';
comment on column ###LDF###.anl_afn_lev_bedrijf.organisatiecode is 'Organisatiecode';
comment on column ###LDF###.anl_afn_lev_bedrijf.applicatiecode is 'Applicatiecode';
comment on column ###LDF###.anl_afn_lev_bedrijf.administratie is 'Administratie';

ALTER TABLE ###LDF###.anl_afn_lev_bedrijf
  OWNER TO ###OWNER###;

-- table anl_afn_lev_atl_bedrijf --
drop table if exists ###LDF###.anl_afn_lev_atl_bedrijf cascade;
create table ###LDF###.anl_afn_lev_atl_bedrijf (
    id                             numeric(14, 0) ,
    afnemers                       character varying(1050) ,
    leveranciers                   character varying(1050) ,
    aantal_afn                     numeric(6, 0) ,
    aantal_lev                     numeric(6, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_atl_bedrijfi01
on ###LDF###.anl_afn_lev_atl_bedrijf
(id, afnemers);
create index anl_afn_lev_atl_bedrijfi02
on ###LDF###.anl_afn_lev_atl_bedrijf
(afnemers, id);
create index anl_afn_lev_atl_bedrijfi03
on ###LDF###.anl_afn_lev_atl_bedrijf
(leveranciers, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_atl_bedrijf IS 'Aantal Afnemers en leveranciers bij entiteit bedrijf';
comment on column ###LDF###.anl_afn_lev_atl_bedrijf.afnemers is 'Lijst met afnemende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_bedrijf.leveranciers is 'Lijst met leverende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_bedrijf.aantal_afn is 'Aantal afnemerindicaties';
comment on column ###LDF###.anl_afn_lev_atl_bedrijf.aantal_lev is 'Aantal leveranciersindicaties';

ALTER TABLE ###LDF###.anl_afn_lev_atl_bedrijf
  OWNER TO ###OWNER###;

-- table anl_afn_lev_vbo --
drop table if exists ###LDF###.anl_afn_lev_vbo cascade;
create table ###LDF###.anl_afn_lev_vbo (
    id                             numeric(14, 0) ,
    afnlevind                      character varying(1) ,
    organisatiecode                character varying(200) ,
    applicatiecode                 character varying(50) ,
    administratie                  character varying(50)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_vboi01
on ###LDF###.anl_afn_lev_vbo
(id, afnlevind);
create index anl_afn_lev_vboi02
on ###LDF###.anl_afn_lev_vbo
(afnlevind, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_vbo IS 'Afnemers en leveranciers bij entiteit vbo';
comment on column ###LDF###.anl_afn_lev_vbo.afnlevind is 'Indicatie afnemer of leverancier';
comment on column ###LDF###.anl_afn_lev_vbo.organisatiecode is 'Organisatiecode';
comment on column ###LDF###.anl_afn_lev_vbo.applicatiecode is 'Applicatiecode';
comment on column ###LDF###.anl_afn_lev_vbo.administratie is 'Administratie';

ALTER TABLE ###LDF###.anl_afn_lev_vbo
  OWNER TO ###OWNER###;

-- table anl_afn_lev_atl_vbo --
drop table if exists ###LDF###.anl_afn_lev_atl_vbo cascade;
create table ###LDF###.anl_afn_lev_atl_vbo (
    id                             numeric(14, 0) ,
    afnemers                       character varying(1050) ,
    leveranciers                   character varying(1050) ,
    aantal_afn                     numeric(6, 0) ,
    aantal_lev                     numeric(6, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_atl_vboi01
on ###LDF###.anl_afn_lev_atl_vbo
(id, afnemers);
create index anl_afn_lev_atl_vboi02
on ###LDF###.anl_afn_lev_atl_vbo
(afnemers, id);
create index anl_afn_lev_atl_vboi03
on ###LDF###.anl_afn_lev_atl_vbo
(leveranciers, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_atl_vbo IS 'Aantal Afnemers en leveranciers bij entiteit vbo';
comment on column ###LDF###.anl_afn_lev_atl_vbo.afnemers is 'Lijst met afnemende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_vbo.leveranciers is 'Lijst met leverende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_vbo.aantal_afn is 'Aantal afnemerindicaties';
comment on column ###LDF###.anl_afn_lev_atl_vbo.aantal_lev is 'Aantal leveranciersindicaties';

ALTER TABLE ###LDF###.anl_afn_lev_atl_vbo
  OWNER TO ###OWNER###;

-- table anl_afn_lev_perceel --
drop table if exists ###LDF###.anl_afn_lev_perceel cascade;
create table ###LDF###.anl_afn_lev_perceel (
    id                             numeric(14, 0) ,
    afnlevind                      character varying(1) ,
    organisatiecode                character varying(200) ,
    applicatiecode                 character varying(50) ,
    administratie                  character varying(1)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_perceeli01
on ###LDF###.anl_afn_lev_perceel
(id, afnlevind);
create index anl_afn_lev_perceeli02
on ###LDF###.anl_afn_lev_perceel
(afnlevind, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_perceel IS 'Afnemers en leveranciers bij entiteit perceel';
comment on column ###LDF###.anl_afn_lev_perceel.afnlevind is 'Indicatie afnemer of leverancier';
comment on column ###LDF###.anl_afn_lev_perceel.organisatiecode is 'Organisatiecode';
comment on column ###LDF###.anl_afn_lev_perceel.applicatiecode is 'Applicatiecode';
comment on column ###LDF###.anl_afn_lev_perceel.administratie is 'Administratie';

ALTER TABLE ###LDF###.anl_afn_lev_perceel
  OWNER TO ###OWNER###;

-- table anl_afn_lev_atl_perceel --
drop table if exists ###LDF###.anl_afn_lev_atl_perceel cascade;
create table ###LDF###.anl_afn_lev_atl_perceel (
    id                             numeric(14, 0) ,
    afnemers                       character varying(1050) ,
    leveranciers                   character varying(1050) ,
    aantal_afn                     numeric(6, 0) ,
    aantal_lev                     numeric(6, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_afn_lev_atl_perceeli01
on ###LDF###.anl_afn_lev_atl_perceel
(id, afnemers);
create index anl_afn_lev_atl_perceeli02
on ###LDF###.anl_afn_lev_atl_perceel
(afnemers, id);
create index anl_afn_lev_atl_perceeli03
on ###LDF###.anl_afn_lev_atl_perceel
(leveranciers, id);

COMMENT ON TABLE ###LDF###.anl_afn_lev_atl_perceel IS 'Aantal Afnemers en leveranciers bij entiteit perceel';
comment on column ###LDF###.anl_afn_lev_atl_perceel.afnemers is 'Lijst met afnemende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_perceel.leveranciers is 'Lijst met leverende applicaties';
comment on column ###LDF###.anl_afn_lev_atl_perceel.aantal_afn is 'Aantal afnemerindicaties';
comment on column ###LDF###.anl_afn_lev_atl_perceel.aantal_lev is 'Aantal leveranciersindicaties';

ALTER TABLE ###LDF###.anl_afn_lev_atl_perceel
  OWNER TO ###OWNER###;

-- table anl_adres --
--------------------------------------------------------------------------
-- TABLE anl_adres
--------------------------------------------------------------------------
DROP TABLE if exists ###LDF###.anl_adres CASCADE ;
CREATE TABLE ###LDF###.anl_adres
(   id   numeric(14)          not null,
    identnummeraanduiding character varying(16 ),
    authidentopenbareruimte character varying(16 ),
    adres1 character varying(110 ),
    adres2 character varying(110 ),
    adres3 character varying(50 ),
    datumbegin numeric(17 ),
    datumeinde numeric(8 ),
    ingangsdatum numeric(8 ),
    statuscodeoms character varying(100 ),
    aandgegevensinodz character varying(1 ),
    geconstateerd character varying(1 ),
    statuscode character varying(2 ),
    codegebeurtenis character varying(16 ),
    authentiek character varying(1 ),
	straatnaam_officieel character varying(100 ),
    straatnaam character varying(30 ),
    postcode character varying(6 ),
    huisnummer numeric(5 ),
    huisletter character varying(1 ),
    huisnummertoevoeging character varying(4 ),
    aanduidingbijhuisnummer character varying(2 ),
    locatieomschrijving character varying(50 ),
	woonplaatsnaam character varying(50 ),
	authentiekewoonplaatscode character varying(2 ),
    authidentwoonplaats character varying(4 ),
    authentiekewoonplaatsnaam character varying(100 ),
    buurtcode numeric(2 ),
    wijkcode numeric(2 ),
	straatcode numeric(5 ),
	woonplaatcode numeric(2 ),
    gemeentecode numeric(4 ),
    authentiekegemeentecode numeric(4 ),
    landcode numeric(4 ),
    identverblijfplaats character varying(16 ),
	centroidxcoordinaat numeric(9, 3),
	centroidycoordinaat numeric(9, 3),
	centroidzcoordinaat numeric(6, 3),
    buurtnaam character varying(50 ),
    wijknaam character varying(50 ),
    gemeentenaam character varying(50 ),
    landnaam character varying(50 ),
	persoon_verblijf_adres character varying(1 ),
	persoon_inschrijf_adres character varying(1 ),
	persoon_corresp_adres character varying(1 ),
	bedrijf_vestigings_adres character varying(1 ),
	bedrijf_corresp_adres character varying(1 ),
	vbo_adres character varying(1 ),
	vbo_neven_adres character varying(1 ),
	kadastraal_adres character varying(1 ),
	actueel character varying(1 ),
	binnengemeentelijk character varying(1 )
)
WITH (
  OIDS=FALSE
);

CREATE INDEX anl_adresI01 on ###LDF###.anl_adres (straatnaam, huisnummer);
CREATE INDEX anl_adresI02 on ###LDF###.anl_adres (gemeentecode);
CREATE INDEX anl_adresI03 on ###LDF###.anl_adres (landcode);
CREATE INDEX anl_adresI04 on ###LDF###.anl_adres (adres1, adres2);
CREATE INDEX anl_adresI05 on ###LDF###.anl_adres (woonplaatsnaam);
CREATE INDEX anl_adresI06 on ###LDF###.anl_adres (postcode);
CREATE INDEX anl_adresI07 on ###LDF###.anl_adres (locatieomschrijving);
--------------------------------------------------------------------------
-- Comments for anl_adres
--------------------------------------------------------------------------
COMMENT ON TABLE ###LDF###.anl_adres IS 'Details adres';
comment on column ###LDF###.anl_adres.id is 'Sleutel makelaar';
comment on column ###LDF###.anl_adres.identnummeraanduiding is 'Identificatie nummeraanduiding';
comment on column ###LDF###.anl_adres.authidentopenbareruimte is 'Authentieke identificatie openbare ruimte';
comment on column ###LDF###.anl_adres.adres1 is 'Adres binnen- of buitenland (1)';
comment on column ###LDF###.anl_adres.adres2 is 'Adres binnen- of buitenland (2)';
comment on column ###LDF###.anl_adres.adres3 is 'Adres binnen- of buitenland (3)';
comment on column ###LDF###.anl_adres.datumbegin is 'Datum begin';
comment on column ###LDF###.anl_adres.datumeinde is 'Datum einde';
comment on column ###LDF###.anl_adres.ingangsdatum is 'Ingangsdatum';
comment on column ###LDF###.anl_adres.statuscodeoms is 'Omschrijving status';
comment on column ###LDF###.anl_adres.aandgegevensinodz is 'Aanduiding gegevens in onderzoek';
comment on column ###LDF###.anl_adres.geconstateerd is 'Geconstateerd';
comment on column ###LDF###.anl_adres.statuscode is 'Status code';
comment on column ###LDF###.anl_adres.codegebeurtenis is 'Code gebeurtenis';
comment on column ###LDF###.anl_adres.authentiek is 'Authentiek';
comment on column ###LDF###.anl_adres.straatnaam_officieel is 'Straatnaam officieel';
comment on column ###LDF###.anl_adres.straatnaam is 'Straatnaam';
comment on column ###LDF###.anl_adres.postcode is 'Postcode';
comment on column ###LDF###.anl_adres.huisnummer is 'Huisnummer';
comment on column ###LDF###.anl_adres.huisletter is 'Huisletter';
comment on column ###LDF###.anl_adres.huisnummertoevoeging is 'Huisnummertoevoeging';
comment on column ###LDF###.anl_adres.aanduidingbijhuisnummer is 'Aanduiding bij huisnummer';
comment on column ###LDF###.anl_adres.locatieomschrijving is 'Locatieomschrijving';
comment on column ###LDF###.anl_adres.woonplaatsnaam is 'Woonplaatsnaam';
comment on column ###LDF###.anl_adres.authentiekewoonplaatscode is 'Woonplaatscode authentiek';
comment on column ###LDF###.anl_adres.authidentwoonplaats is 'Authentieke identificatie woonplaats';
comment on column ###LDF###.anl_adres.authentiekewoonplaatsnaam is 'Woonplaatsnaam authentiek';
comment on column ###LDF###.anl_adres.buurtcode is 'Buurtcode';
comment on column ###LDF###.anl_adres.wijkcode is 'Wijkcode';
comment on column ###LDF###.anl_adres.straatcode is 'Straatcode';
comment on column ###LDF###.anl_adres.woonplaatcode is 'Woonplaatscode';
comment on column ###LDF###.anl_adres.gemeentecode is 'Gemeentecode';
comment on column ###LDF###.anl_adres.authentiekegemeentecode is 'Gemeentecode authentiek';
comment on column ###LDF###.anl_adres.landcode is 'Landcode';
comment on column ###LDF###.anl_adres.identverblijfplaats is 'Identificatiecode verblijfplaats';
comment on column ###LDF###.anl_adres.centroidxcoordinaat is 'Centroid x-coordinaat';
comment on column ###LDF###.anl_adres.centroidycoordinaat is 'Centroid y-coordinaat';
comment on column ###LDF###.anl_adres.centroidzcoordinaat is 'Centroid z-coordinaat';
comment on column ###LDF###.anl_adres.buurtnaam is 'Buurtnaam';
comment on column ###LDF###.anl_adres.wijknaam is 'Wijknaam';
comment on column ###LDF###.anl_adres.gemeentenaam is 'Gemeente naam';
comment on column ###LDF###.anl_adres.landnaam is 'Landnaam';
comment on column ###LDF###.anl_adres.persoon_verblijf_adres is 'Verblijfsadres persoon';
comment on column ###LDF###.anl_adres.persoon_inschrijf_adres is 'Inschrijfadres persoon';
comment on column ###LDF###.anl_adres.persoon_corresp_adres is 'Correspondentieadres persoon';
comment on column ###LDF###.anl_adres.bedrijf_vestigings_adres is 'Vestigingsadres bedrijf';
comment on column ###LDF###.anl_adres.bedrijf_corresp_adres is 'Correspondentieadres bedrijf';
comment on column ###LDF###.anl_adres.vbo_adres is 'Verblijfsobject adres';
comment on column ###LDF###.anl_adres.vbo_neven_adres is 'Verblijfsobject nevenadres';
comment on column ###LDF###.anl_adres.kadastraal_adres is 'Kadastraal adres';
comment on column ###LDF###.anl_adres.actueel is 'Actueel adres';
comment on column ###LDF###.anl_adres.binnengemeentelijk is 'Binnengemeentelijk adres';
--------------------------------------------------------------------------
-- GRANTS for anl_adres
--------------------------------------------------------------------------
ALTER TABLE ###LDF###.anl_adres
OWNER TO ###OWNER###;


------------------------------------------------------------------------------------
--anl_adres_fixed :table used for fixed reports
------------------------------------------------------------------------------------
DROP TABLE IF EXISTS ###LDF###.anl_adres_fixed;
CREATE TABLE ###LDF###.anl_adres_fixed
(
	id numeric(14,0) NOT NULL, -- Sleutel distributie systeem
	rubriek_afwijkend character varying(50),
	eigenaargegeven character varying(50),
	fxx02_applicatiegegeven character varying(1024), -- Afwijkende waarde van het element
	datum_afwijkend character varying(20),  --Datum_afwijkend
	num character varying(6) NOT NULL, -- Nr van het element waarop afwijkende waarde betrekking heeft
	applicatie character varying(50) NOT NULL, -- Code
	administratie character varying(50), -- Administratie
	organisatie character varying(200), -- Organisatie
	--Existing fields
	straatnaam character varying(30), -- Straatnaam
	huisnummer character varying(5), -- Huistnummer
	huisletter character varying(1), -- Huisletter
	huisnummertoevoeging character varying(4), -- Huistnummertoevoeging
	locatieomschrijving character varying(50), -- Locatieomschrijving
	postcode character varying(6) -- Postcode
)
WITH (
  OIDS=FALSE
);

ALTER TABLE ###LDF###.anl_adres_fixed
  OWNER TO ###OWNER###;

COMMENT ON TABLE ###LDF###.anl_adres_fixed IS 'Details adres fixed';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.id IS 'Sleutel makelaar';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.rubriek_afwijkend IS 'Rubriek afwijkend';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.eigenaargegeven IS 'Eigenaar gegevens';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.fxx02_applicatiegegeven IS 'Afwijkende waarde van het element';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.datum_afwijkend IS 'Datum afwijkend';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.num IS 'StUF element nr.';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.applicatie IS 'Applicatie code';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.administratie IS 'Administratie';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.organisatie IS 'Organisatie';

COMMENT ON COLUMN ###LDF###.anl_adres_fixed.straatnaam IS 'Straatnaam';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.postcode IS 'Postcode';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.huisnummer IS 'Huisnummer';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.huisletter IS 'Huisletter';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.huisnummertoevoeging IS 'Huisnummertoevoeging';
COMMENT ON COLUMN ###LDF###.anl_adres_fixed.locatieomschrijving IS 'Locatieomschrijving';

--Indexes

------------------------------------------------------------------------------------

-- table anl_bedrijf --
drop table if exists ###LDF###.anl_bedrijf cascade;
create table ###LDF###.anl_bedrijf (
    id                             numeric(14)          not null,
    zaaknaam                       character varying(55) ,
    statnaamvennschpnaam           character varying(160) ,
    handelsnaam                    character varying(160) ,
    burgerservicenummer            numeric(9, 0) ,
    nnpid                          numeric(9, 0) ,
    rechtsvormcode                 numeric(2, 0) ,
    indhoofdvestiging              character varying(1) ,
    hoofdactiviteitcode            numeric(5, 0) ,
    nevenactiviteitcode            numeric(5, 0) ,
    datumoprichtingnnp             numeric(8, 0) ,
    datumbegin                     numeric(8, 0) ,
    datumeinde                     numeric(8, 0) ,
    datumontbinding                numeric(8, 0) ,
    handelsregisternummer          numeric(8, 0) ,
    handelsregistervolgnummer      numeric(4, 0) ,
    vestigingsnummer               character varying(12) ,
    terattentievan                 character varying(50) ,
    telefoonnummer1                character varying(20) ,
    telefoonnummer2                character varying(20) ,
    telefoonnummer3                character varying(20) ,
    faxnummer1                     character varying(20) ,
    faxnummer2                     character varying(20) ,
    faxnummer3                     character varying(20) ,
    emailadres1                    character varying(80) ,
    emailadres2                    character varying(80) ,
    emailadres3                    character varying(80) ,
    bankgirorekeningnummer1        numeric(10, 0) ,
    bankgirorekeningnummer2        numeric(10, 0) ,
    bankgirorekeningnummer3        numeric(10, 0) ,
    straatnaam_v                   character varying(30) ,
    huisnummer_v                   numeric(5, 0) ,
    postbusnummer_v                numeric(5, 0) ,
    huisletter_v                   character varying(1) ,
    huisnummertoevoeging_v         character varying(4) ,
    aanduidingbijhuisnummer_v      character varying(2) ,
    locatieomschrijving_v          character varying(50) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    adresbuitenland1_v             character varying(70) ,
    adresbuitenland2_v             character varying(70) ,
    adresbuitenland3_v             character varying(70) ,
    postcode_v                     character varying(6) ,
    woonplaatsnaam_v               character varying(50) ,
    gemeentecode_v                 numeric(4, 0) ,
    gemeentenaam_v                 character varying(50) ,
    landcode_v                     numeric(4, 0) ,
    landnaam_v                     character varying(50) ,
    authentiekewoonplaatsnaam_v    character varying(100) ,
    identnummeraanduiding_v        character varying(16) ,
    authentiekewoonplaatscode_v    character varying(4) ,
    officielestraatnaam_v          character varying(100) ,
    authentiekegemeentecode_v      numeric(4, 0) ,
    straatnaam_c                   character varying(30) ,
    huisnummer_c                   numeric(5, 0) ,
    postbusnummer_corresp          numeric(5, 0) ,
    huisletter_c                   character varying(1) ,
    huisnummertoevoeging_c         character varying(4) ,
    aanduidingbijhuisnummer_c      character varying(2) ,
    locatieomschrijving_c          character varying(50) ,
    adresbinnenland1_c             character varying(110) ,
    adresbinnenland2_c             character varying(110) ,
    adresbinnenland3_c             character varying(50) ,
    adresbuitenland1_c             character varying(70) ,
    adresbuitenland2_c             character varying(70) ,
    adresbuitenland3_c             character varying(70) ,
    postcode_c                     character varying(6) ,
    woonplaatsnaam_c               character varying(50) ,
    gemeentecode_c                 numeric(4, 0) ,
    gemeentenaam_c                 character varying(50) ,
    landcode_c                     numeric(4, 0) ,
    landnaam_c                     character varying(50) ,
    authentiekewoonplaatsnaam_c    character varying(100) ,
    identnummeraanduiding_c        character varying(16) ,
    authentiekewoonplaatscode_c    character varying(4) ,
    officielestraatnaam_c          character varying(100) ,
    authentiekegemeentecode_c      numeric(4, 0),
	actueel character varying(1 ),
	binnengemeentelijk character varying(1 )

)
WITH (
  OIDS=FALSE
);

create index anl_bedrijfi01
on ###LDF###.anl_bedrijf
(burgerservicenummer);
create index anl_bedrijfi02
on ###LDF###.anl_bedrijf
(vestigingsnummer);
create index anl_bedrijfi03
on ###LDF###.anl_bedrijf
(adresbinnenland1_v, adresbinnenland2_v);
create index anl_bedrijfi04
on ###LDF###.anl_bedrijf
(nnpid);
create index anl_bedrijfi05
on ###LDF###.anl_bedrijf
(handelsregisternummer);

COMMENT ON TABLE ###LDF###.anl_bedrijf IS 'Details bedrijf';
comment on column ###LDF###.anl_bedrijf.zaaknaam is 'Zaaknaam';
comment on column ###LDF###.anl_bedrijf.statnaamvennschpnaam is 'Statutaire naam / vennootschapsnaam';
comment on column ###LDF###.anl_bedrijf.handelsnaam is 'Handelsnaam';
comment on column ###LDF###.anl_bedrijf.burgerservicenummer is 'Burger Service Nummer';
comment on column ###LDF###.anl_bedrijf.nnpid is 'Nnp-id';
comment on column ###LDF###.anl_bedrijf.rechtsvormcode is 'Rechtsvorm';
comment on column ###LDF###.anl_bedrijf.indhoofdvestiging is 'ind HoofdVestiging';
comment on column ###LDF###.anl_bedrijf.hoofdactiviteitcode is 'HoofdActiviteit';
comment on column ###LDF###.anl_bedrijf.nevenactiviteitcode is 'NevenActiviteit';
comment on column ###LDF###.anl_bedrijf.datumoprichtingnnp is 'Datum oprichting niet natuurlijk persoon';
comment on column ###LDF###.anl_bedrijf.datumbegin is 'Datum begin';
comment on column ###LDF###.anl_bedrijf.datumeinde is 'Datum einde';
comment on column ###LDF###.anl_bedrijf.datumontbinding is 'Datum ontbinding';
comment on column ###LDF###.anl_bedrijf.handelsregisternummer is 'HandelsRegisternummer';
comment on column ###LDF###.anl_bedrijf.handelsregistervolgnummer is 'HandelsRegisterVolgnummer';
comment on column ###LDF###.anl_bedrijf.vestigingsnummer is 'Vestigingsnummer';
comment on column ###LDF###.anl_bedrijf.terattentievan is 'Ter attentie van';
comment on column ###LDF###.anl_bedrijf.telefoonnummer1 is 'Telefoonnummer 1';
comment on column ###LDF###.anl_bedrijf.telefoonnummer2 is 'Telefoonnummer 2';
comment on column ###LDF###.anl_bedrijf.telefoonnummer3 is 'Telefoonnummer 3';
comment on column ###LDF###.anl_bedrijf.faxnummer1 is 'Faxnummer 1';
comment on column ###LDF###.anl_bedrijf.faxnummer2 is 'Faxnummer 2';
comment on column ###LDF###.anl_bedrijf.faxnummer3 is 'Faxnummer 3';
comment on column ###LDF###.anl_bedrijf.emailadres1 is 'E-mail adres 1';
comment on column ###LDF###.anl_bedrijf.emailadres2 is 'E-mail adres 2';
comment on column ###LDF###.anl_bedrijf.emailadres3 is 'E-mail adres 3';
comment on column ###LDF###.anl_bedrijf.bankgirorekeningnummer1 is 'Bank/girorekeningnummer 1';
comment on column ###LDF###.anl_bedrijf.bankgirorekeningnummer2 is 'Bank/girorekeningnummer 2';
comment on column ###LDF###.anl_bedrijf.bankgirorekeningnummer3 is 'Bank/girorekeningnummer 3';
comment on column ###LDF###.anl_bedrijf.straatnaam_v is 'Straatnaam Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.huisnummer_v is 'Huisnummer Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.postbusnummer_v is 'Postbusnummer Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.huisletter_v is 'Huisletter Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.huisnummertoevoeging_v is 'Huisnummertoevoeging Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.aanduidingbijhuisnummer_v is 'Aanduiding bij huisnummer Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.locatieomschrijving_v is 'Locatieomschrijving Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.adresbinnenland1_v is 'Adres binnenland (1) Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.adresbinnenland2_v is 'Adres binnenland (2) Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.adresbinnenland3_v is 'Adres binnenland (3) Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.adresbuitenland1_v is 'Adres buitenland (1) Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.adresbuitenland2_v is 'Adres buitenland (2) Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.adresbuitenland3_v is 'Adres buitenland (3) Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.postcode_v is 'Postcode Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.woonplaatsnaam_v is 'Woonplaatsnaam Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.gemeentecode_v is 'Gemeentecode Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.gemeentenaam_v is 'Gemeente naam Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.landcode_v is 'Landcode Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.landnaam_v is 'Landnaam Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.authentiekewoonplaatsnaam_v is 'Woonplaatsnaam authentiek Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.identnummeraanduiding_v is 'Identificatie nummeraanduiding Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.authentiekewoonplaatscode_v is 'Woonplaatscode authentiek Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.officielestraatnaam_v is 'Officiele straatnaam Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.authentiekegemeentecode_v is 'Gemeentecode authentiek Verblijf vestiging';
comment on column ###LDF###.anl_bedrijf.straatnaam_c is 'Straatnaam Correspondentie';
comment on column ###LDF###.anl_bedrijf.huisnummer_c is 'Huisnummer Correspondentie';
comment on column ###LDF###.anl_bedrijf.postbusnummer_corresp is 'Postbusnummer Correspondentie';
comment on column ###LDF###.anl_bedrijf.huisletter_c is 'Huisletter Correspondentie';
comment on column ###LDF###.anl_bedrijf.huisnummertoevoeging_c is 'Huisnummertoevoeging Correspondentie';
comment on column ###LDF###.anl_bedrijf.aanduidingbijhuisnummer_c is 'Aanduiding bij huisnummer Correspondentie';
comment on column ###LDF###.anl_bedrijf.locatieomschrijving_c is 'Locatieomschrijving Correspondentie';
comment on column ###LDF###.anl_bedrijf.adresbinnenland1_c is 'Adres binnenland (1) Correspondentie';
comment on column ###LDF###.anl_bedrijf.adresbinnenland2_c is 'Adres binnenland (2) Correspondentie';
comment on column ###LDF###.anl_bedrijf.adresbinnenland3_c is 'Adres binnenland (3) Correspondentie';
comment on column ###LDF###.anl_bedrijf.adresbuitenland1_c is 'Adres buitenland (1) Correspondentie';
comment on column ###LDF###.anl_bedrijf.adresbuitenland2_c is 'Adres buitenland (2) Correspondentie';
comment on column ###LDF###.anl_bedrijf.adresbuitenland3_c is 'Adres buitenland (3) Correspondentie';
comment on column ###LDF###.anl_bedrijf.postcode_c is 'Postcode Correspondentie';
comment on column ###LDF###.anl_bedrijf.woonplaatsnaam_c is 'Woonplaatsnaam Correspondentie';
comment on column ###LDF###.anl_bedrijf.gemeentecode_c is 'Gemeentecode Correspondentie';
comment on column ###LDF###.anl_bedrijf.gemeentenaam_c is 'Gemeente naam Correspondentie';
comment on column ###LDF###.anl_bedrijf.landcode_c is 'Landcode Correspondentie';
comment on column ###LDF###.anl_bedrijf.landnaam_c is 'Landnaam Correspondentie';
comment on column ###LDF###.anl_bedrijf.authentiekewoonplaatsnaam_c is 'Woonplaatsnaam authentiek Correspondentie';
comment on column ###LDF###.anl_bedrijf.identnummeraanduiding_c is 'Identificatie nummeraanduiding Correspondentie';
comment on column ###LDF###.anl_bedrijf.authentiekewoonplaatscode_c is 'Woonplaatscode authentiek Correspondentie';
comment on column ###LDF###.anl_bedrijf.officielestraatnaam_c is 'Officiele straatnaam Correspondentie';
comment on column ###LDF###.anl_bedrijf.authentiekegemeentecode_c is 'Gemeentecode authentiek Correspondentie';
comment on column ###LDF###.anl_bedrijf.actueel is 'Actueel bedrijf';
comment on column ###LDF###.anl_bedrijf.binnengemeentelijk is 'Binnengemeentelijk bedrijf';

ALTER TABLE ###LDF###.anl_bedrijf
  OWNER TO ###OWNER###;

-- table anl_persoon --
drop table if exists ###LDF###.anl_persoon cascade;
create table ###LDF###.anl_persoon (
    id                             numeric(14, 0)          not null,
    burgerservicenummer            numeric(9, 0) ,
    gemeentecodeinschrijving       numeric(4, 0) ,
    voornamen                      character varying(240) ,
    voorletters                    character varying(15) ,
    voorvoegselgeslachtsnaam       character varying(15) ,
    geslachtsnaam                  character varying(240) ,
    codegeslachtsaanduiding        character varying(1) ,
    codeadellijketitelpredikaat    character varying(2) ,
    geboortedatum                  numeric(8, 0) ,
    codegeboorteplaats             numeric(4, 0) ,
    geboorteplaats                 character varying(50) ,
    geboortelandcode               numeric(4, 0) ,
    codeburgerlijkestaat           numeric(1, 0) ,
    anummer                        numeric(10, 0) ,
    datumoverlijden                numeric(8, 0) ,
    codeplaatsoverlijden           numeric(4, 0) ,
    omsplaatsoverlijden            character varying(50) ,
    landcodeoverlijden             numeric(4, 0) ,
    datuminschrijving              numeric(8, 0) ,
    redenopschorting               character varying(1) ,
    codeindicatiegeheim            numeric(1, 0) ,
    authentiek                     character varying(1) ,
    telefoonnummer1                character varying(20) ,
    telefoonnummer2                character varying(20) ,
    telefoonnummer3                character varying(20) ,
    faxnummer1                     character varying(20) ,
    faxnummer2                     character varying(20) ,
    faxnummer3                     character varying(20) ,
    emailadres1                    character varying(80) ,
    emailadres2                    character varying(80) ,
    emailadres3                    character varying(80) ,
    bankgirorekeningnummer         numeric(10, 0) ,
    aanduidingnaamgebruik          character varying(1) ,
    opgemnaamzonderpredikaten      character varying(240) ,
    opgemnaam                      character varying(240) ,
    geslachtsnaamechtgenoot        character varying(240) ,
    voorvsgeslachtsnmechtgnt       character varying(15) ,
    datumvertrekuitnederland       numeric(8, 0) ,
    datumsluiting                  numeric(8, 0) ,
    datumontbinding                numeric(8, 0) ,
    coderedenontbinding            character varying(1) ,
    burgerservicenummerechtgnt     numeric(9, 0) ,
    geslachtsaanduidingechtgnt     character varying(1) ,
    begdatrelatie_i                numeric(8, 0) ,
    straatnaam_i                   character varying(30) ,
    huisnummer_i                   numeric(5, 0) ,
    huisletter_i                   character varying(1) ,
    huisnummertoevoeging_i         character varying(4) ,
    aanduidingbijhuisnummer_i      character varying(2) ,
    locatieomschrijving_i          character varying(50) ,
    adresbinnenland1_i             character varying(110) ,
    adresbinnenland2_i             character varying(110) ,
    adresbinnenland3_i             character varying(50) ,
    adresbuitenland1_i             character varying(70) ,
    adresbuitenland2_i             character varying(70) ,
    adresbuitenland3_i             character varying(70) ,
    postcode_i                     character varying(6) ,
    woonplaatsnaam_i               character varying(50) ,
    gemeentecode_i                 numeric(4, 0) ,
    gemeentenaam_i                 character varying(50) ,
    landcode_i                     numeric(4, 0) ,
    landnaam_i                     character varying(50) ,
    authentiekewoonplaatsnaam_i    character varying(100) ,
    identnummeraanduiding_i        character varying(16) ,
    authidentwoonplaats_i          character varying(4) ,
    officielestraatnaam_i          character varying(100) ,
    authentiekegemeentecode_i      numeric(4, 0) ,
    begdatrelatie_v                numeric(8, 0) ,
    straatnaam_v                   character varying(30) ,
    huisnummer_v                   numeric(5, 0) ,
    huisletter_v                   character varying(1) ,
    huisnummertoevoeging_v         character varying(4) ,
    aanduidingbijhuisnummer_v      character varying(2) ,
    locatieomschrijving_v          character varying(50) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    adresbuitenland1_v             character varying(70) ,
    adresbuitenland2_v             character varying(70) ,
    adresbuitenland3_v             character varying(70) ,
    postcode_v                     character varying(6) ,
    woonplaatsnaam_v               character varying(50) ,
    gemeentecode_v                 numeric(4, 0) ,
    gemeentenaam_v                 character varying(50) ,
    landcode_v                     numeric(4, 0) ,
    landnaam_v                     character varying(50) ,
    wijkcode_v                     numeric(2, 0) ,
    buurtcode_v                    numeric(2, 0) ,
    wijknaam_v                     character varying(50) ,
    buurtnaam_v                    character varying(50) ,
    authentiekewoonplaatsnaam_v    character varying(100) ,
    identnummeraanduiding_v        character varying(16) ,
    authidentwoonplaats_v          character varying(4) ,
    officielestraatnaam_v          character varying(100) ,
    authentiekegemeentecode_v      numeric(4, 0) ,
    straatnaam_c                   character varying(30) ,
    huisnummer_c                   numeric(5, 0) ,
    huisletter_c                   character varying(1) ,
    huisnummertoevoeging_c         character varying(4) ,
    aanduidingbijhuisnummer_c      character varying(2) ,
    locatieomschrijving_c          character varying(50) ,
    adresbinnenland1_c             character varying(110) ,
    adresbinnenland2_c             character varying(110) ,
    adresbinnenland3_c             character varying(50) ,
    adresbuitenland1_c             character varying(70) ,
    adresbuitenland2_c             character varying(70) ,
    adresbuitenland3_c             character varying(70) ,
    postcode_c                     character varying(6) ,
    woonplaatsnaam_c               character varying(50) ,
    gemeentecode_c                 numeric(4, 0) ,
    gemeentenaam_c                 character varying(50) ,
    landcode_c                     numeric(4, 0) ,
    landnaam_c                     character varying(50) ,
    authentiekewoonplaatsnaam_c    character varying(100) ,
    identnummeraanduiding_c        character varying(16) ,
    authidentwoonplaats_c          character varying(4) ,
    officielestraatnaam_c          character varying(100) ,
    authentiekegemeentecode_c      numeric(4, 0) ,
    voornamen_o1                   character varying(240) ,
    voorletters_o1                 character varying(15) ,
    voorvoegselgeslachtsnaam_o1    character varying(15) ,
    geslachtsnaam_o1               character varying(240) ,
    codegeslachtsaanduiding_o1     character varying(1) ,
    geboortedatum_o1               numeric(8, 0) ,
    codeburgerlijkestaat_o1        numeric(1, 0) ,
    anummer_o1                     numeric(10, 0) ,
    burgerservicenummer_o1         numeric(9, 0) ,
    datumoverlijden_o1             numeric(8, 0) ,
    codeindicatiegeheim_o1         numeric(1, 0) ,
    adresbinnenland1_o1            character varying(110) ,
    adresbinnenland2_o1            character varying(110) ,
    adresbinnenland3_o1            character varying(50) ,
    adresbuitenland1_o1            character varying(70) ,
    adresbuitenland2_o1            character varying(70) ,
    adresbuitenland3_o1            character varying(70) ,
    voornamen_o2                   character varying(240) ,
    voorletters_o2                 character varying(15) ,
    voorvoegselgeslachtsnaam_o2    character varying(15) ,
    geslachtsnaam_o2               character varying(240) ,
    codegeslachtsaanduiding_o2     character varying(1) ,
    geboortedatum_o2               numeric(8, 0) ,
    codeburgerlijkestaat_o2        numeric(1, 0) ,
    anummer_o2                     numeric(10, 0) ,
    burgerservicenummer_o2         numeric(9, 0) ,
    datumoverlijden_o2             numeric(8, 0) ,
    codeindicatiegeheim_o2         numeric(1, 0) ,
    adresbinnenland1_o2            character varying(110) ,
    adresbinnenland2_o2            character varying(110) ,
    adresbinnenland3_o2            character varying(50) ,
    adresbuitenland1_o2            character varying(70) ,
    adresbuitenland2_o2            character varying(70) ,
    adresbuitenland3_o2            character varying(70) ,
    aantalparkeerplaatsen          numeric(4, 0) ,
    woonoppervlakte                numeric(6, 0) ,
    brutovloeroppervlak            numeric(6, 0) ,
    brutoinhoud                    numeric(6, 0) ,
    statusvoa                      character varying(2) ,
    omschrijvingnationaliteit_1    character varying(55) ,
    omschrijvingnationaliteit_2    character varying(55) ,
    omschrijvingnationaliteit_3    character varying(55),
	actueel                        character varying(1 ),
	binnengemeentelijk             character varying(1 ),
    odz_overlijden                 character varying(1) ,
    odz_verblijfstitel             character varying(1) ,
    odz_inschrijfadres             character varying(1) ,
    odz_verblijfadres              character varying(1) ,
    odz_identificatiebewijs	       character varying(1) ,
    odz_kindgegevens               character varying(1) ,
    odz_nationaliteit              character varying(1) ,
    odz_oudergegevens              character varying(1) ,
    odz_huwelijksgegevens          character varying(1) ,
    verblijfstitelcode             numeric(2, 0) ,
    datumverkrijgingverblfsttl     numeric(8, 0) ,
    datumverliesverblijfstitel     numeric(8, 0) ,
    codeindgezagminderjarige       character varying(2) ,
    indcurateleregister            character varying(1)
)
WITH (
  OIDS=FALSE
);

create index anl_persooni01 on
###LDF###.anl_persoon
(burgerservicenummer);
create index anl_persooni02 on
###LDF###.anl_persoon
(anummer);
create index anl_persooni03 on
###LDF###.anl_persoon
(adresbinnenland1_v, adresbinnenland2_v);

COMMENT ON TABLE ###LDF###.anl_persoon IS 'Details persoon';
comment on column ###LDF###.anl_persoon.burgerservicenummer is 'Burger Service Nummer';
comment on column ###LDF###.anl_persoon.gemeentecodeinschrijving is 'Gemeentecode inschrijving';
comment on column ###LDF###.anl_persoon.voornamen is 'Voornamen';
comment on column ###LDF###.anl_persoon.voorletters is 'Voorletters';
comment on column ###LDF###.anl_persoon.voorvoegselgeslachtsnaam is 'Voorvoegsel geslachtsnaam';
comment on column ###LDF###.anl_persoon.geslachtsnaam is 'Geslachtsnaam';
comment on column ###LDF###.anl_persoon.codegeslachtsaanduiding is 'Geslachtsaanduiding';
comment on column ###LDF###.anl_persoon.codeadellijketitelpredikaat is 'Code adellijke titel predikaat';
comment on column ###LDF###.anl_persoon.geboortedatum is 'Geboortedatum';
comment on column ###LDF###.anl_persoon.codegeboorteplaats is 'Gemeentecode geboorteplaats';
comment on column ###LDF###.anl_persoon.geboorteplaats is 'Geboorteplaats';
comment on column ###LDF###.anl_persoon.geboortelandcode is 'Geboortelandcode';
comment on column ###LDF###.anl_persoon.codeburgerlijkestaat is 'Burgerlijke staat';
comment on column ###LDF###.anl_persoon.anummer is 'A nummer';
comment on column ###LDF###.anl_persoon.datumoverlijden is 'Datum overlijden';
comment on column ###LDF###.anl_persoon.codeplaatsoverlijden is 'Gemeentecode plaats overlijden';
comment on column ###LDF###.anl_persoon.omsplaatsoverlijden is 'Omschrijving plaatsoverlijden';
comment on column ###LDF###.anl_persoon.landcodeoverlijden is 'Landcode overlijden';
comment on column ###LDF###.anl_persoon.datuminschrijving is 'Datum inschrijving gemeente';
comment on column ###LDF###.anl_persoon.redenopschorting is 'Reden opschorting';
comment on column ###LDF###.anl_persoon.codeindicatiegeheim is 'Indicatie geheim';
comment on column ###LDF###.anl_persoon.telefoonnummer1 is 'Telefoonnummer 1';
comment on column ###LDF###.anl_persoon.telefoonnummer2 is 'Telefoonnummer 2';
comment on column ###LDF###.anl_persoon.telefoonnummer3 is 'Telefoonnummer 3';
comment on column ###LDF###.anl_persoon.faxnummer1 is 'Faxnummer 1';
comment on column ###LDF###.anl_persoon.faxnummer2 is 'Faxnummer 2';
comment on column ###LDF###.anl_persoon.faxnummer3 is 'Faxnummer 3';
comment on column ###LDF###.anl_persoon.emailadres1 is 'E-mail adres 1';
comment on column ###LDF###.anl_persoon.emailadres2 is 'E-mail adres 2';
comment on column ###LDF###.anl_persoon.emailadres3 is 'E-mail adres 3';
comment on column ###LDF###.anl_persoon.bankgirorekeningnummer is 'Bank/girorekeningnummer';
comment on column ###LDF###.anl_persoon.aanduidingnaamgebruik is 'Aanduiding naamgebruik';
comment on column ###LDF###.anl_persoon.opgemnaamzonderpredikaten is 'Opgemaakte naam zonder predikaten';
comment on column ###LDF###.anl_persoon.opgemnaam is 'Opgemaakte naam';
comment on column ###LDF###.anl_persoon.geslachtsnaamechtgenoot is 'Geslachtsnaam partner';
comment on column ###LDF###.anl_persoon.voorvsgeslachtsnmechtgnt is 'Voorvoegsels geslachtsnaam partner';
comment on column ###LDF###.anl_persoon.geslachtsaanduidingechtgnt is 'Geslachtsaanduiding partner';
comment on column ###LDF###.anl_persoon.datumvertrekuitnederland is 'Datum vertrek uit Nederland';
comment on column ###LDF###.anl_persoon.datumsluiting is 'Datum sluiting';
comment on column ###LDF###.anl_persoon.datumontbinding is 'Datum ontbinding';
comment on column ###LDF###.anl_persoon.coderedenontbinding is 'Reden ontbinding';
comment on column ###LDF###.anl_persoon.burgerservicenummerechtgnt is 'BSN partner';
comment on column ###LDF###.anl_persoon.begdatrelatie_i is 'Begindatum relatie inschrijfadres';
comment on column ###LDF###.anl_persoon.straatnaam_i is 'Straatnaam Inschrijf';
comment on column ###LDF###.anl_persoon.huisnummer_i is 'Huisnummer Inschrijf';
comment on column ###LDF###.anl_persoon.huisletter_i is 'Huisletter Inschrijf';
comment on column ###LDF###.anl_persoon.huisnummertoevoeging_i is 'Huisnummertoevoeging Inschrijf';
comment on column ###LDF###.anl_persoon.aanduidingbijhuisnummer_i is 'Aanduiding bij huisnummer Inschrijf';
comment on column ###LDF###.anl_persoon.locatieomschrijving_i is 'Locatieomschrijving Inschrijf';
comment on column ###LDF###.anl_persoon.adresbinnenland1_i is 'Adres binnenland (1) Inschrijf';
comment on column ###LDF###.anl_persoon.adresbinnenland2_i is 'Adres binnenland (2) Inschrijf';
comment on column ###LDF###.anl_persoon.adresbinnenland3_i is 'Adres binnenland (3) Inschrijf';
comment on column ###LDF###.anl_persoon.adresbuitenland1_i is 'Adres buitenland (1) Inschrijf';
comment on column ###LDF###.anl_persoon.adresbuitenland2_i is 'Adres buitenland (2) Inschrijf';
comment on column ###LDF###.anl_persoon.adresbuitenland3_i is 'Adres buitenland (3) Inschrijf';
comment on column ###LDF###.anl_persoon.postcode_i is 'Postcode Inschrijf';
comment on column ###LDF###.anl_persoon.woonplaatsnaam_i is 'Woonplaatsnaam Inschrijf';
comment on column ###LDF###.anl_persoon.gemeentecode_i is 'Gemeentecode Inschrijf';
comment on column ###LDF###.anl_persoon.gemeentenaam_i is 'Gemeente naam Inschrijf';
comment on column ###LDF###.anl_persoon.landcode_i is 'Landcode Inschrijf';
comment on column ###LDF###.anl_persoon.landnaam_i is 'Landnaam Inschrijf';
comment on column ###LDF###.anl_persoon.authentiekewoonplaatsnaam_i is 'Woonplaatsnaam authentiek Inschrijf';
comment on column ###LDF###.anl_persoon.identnummeraanduiding_i is 'Identificatie nummeraanduiding Inschrijf';
comment on column ###LDF###.anl_persoon.authidentwoonplaats_i is 'Authentieke identificatie woonplaats Inschrijf';
comment on column ###LDF###.anl_persoon.officielestraatnaam_i is 'Officiele straatnaam Inschrijf';
comment on column ###LDF###.anl_persoon.authentiekegemeentecode_i is 'Gemeentecode authentiek Inschrijf';
comment on column ###LDF###.anl_persoon.begdatrelatie_v is 'Begindatum relatie verblijfadres';
comment on column ###LDF###.anl_persoon.straatnaam_v is 'Straatnaam Verblijf vestiging';
comment on column ###LDF###.anl_persoon.huisnummer_v is 'Huisnummer Verblijf vestiging';
comment on column ###LDF###.anl_persoon.huisletter_v is 'Huisletter Verblijf vestiging';
comment on column ###LDF###.anl_persoon.huisnummertoevoeging_v is 'Huisnummertoevoeging Verblijf vestiging';
comment on column ###LDF###.anl_persoon.aanduidingbijhuisnummer_v is 'Aanduiding bij huisnummer Verblijf vestiging';
comment on column ###LDF###.anl_persoon.locatieomschrijving_v is 'Locatieomschrijving Verblijf vestiging';
comment on column ###LDF###.anl_persoon.adresbinnenland1_v is 'Adres binnenland (1) Verblijf vestiging';
comment on column ###LDF###.anl_persoon.adresbinnenland2_v is 'Adres binnenland (2) Verblijf vestiging';
comment on column ###LDF###.anl_persoon.adresbinnenland3_v is 'Adres binnenland (3) Verblijf vestiging';
comment on column ###LDF###.anl_persoon.adresbuitenland1_v is 'Adres buitenland (1) Verblijf vestiging';
comment on column ###LDF###.anl_persoon.adresbuitenland2_v is 'Adres buitenland (2) Verblijf vestiging';
comment on column ###LDF###.anl_persoon.adresbuitenland3_v is 'Adres buitenland (3) Verblijf vestiging';
comment on column ###LDF###.anl_persoon.postcode_v is 'Postcode Verblijf vestiging';
comment on column ###LDF###.anl_persoon.woonplaatsnaam_v is 'Woonplaatsnaam Verblijf vestiging';
comment on column ###LDF###.anl_persoon.gemeentecode_v is 'Gemeentecode Verblijf vestiging';
comment on column ###LDF###.anl_persoon.gemeentenaam_v is 'Gemeente naam Verblijf vestiging';
comment on column ###LDF###.anl_persoon.landcode_v is 'Landcode Verblijf vestiging';
comment on column ###LDF###.anl_persoon.landnaam_v is 'Landnaam Verblijf vestiging';
comment on column ###LDF###.anl_persoon.wijkcode_v is 'Wijkcode Verblijf vestiging';
comment on column ###LDF###.anl_persoon.buurtcode_v is 'Buurtcode Verblijf vestiging';
comment on column ###LDF###.anl_persoon.wijknaam_v is 'Wijknaam Verblijf vestiging';
comment on column ###LDF###.anl_persoon.buurtnaam_v is 'Buurtnaam Verblijf vestiging';
comment on column ###LDF###.anl_persoon.authentiekewoonplaatsnaam_v is 'Woonplaatsnaam authentiek Verblijf vestiging';
comment on column ###LDF###.anl_persoon.identnummeraanduiding_v is 'Identificatie nummeraanduiding Verblijf vestiging';
comment on column ###LDF###.anl_persoon.authidentwoonplaats_v is 'Authentieke identificatie woonplaats Verblijf vestiging';
comment on column ###LDF###.anl_persoon.officielestraatnaam_v is 'Officiele straatnaam Verblijf vestiging';
comment on column ###LDF###.anl_persoon.authentiekegemeentecode_v is 'Gemeentecode authentiek Verblijf vestiging';
comment on column ###LDF###.anl_persoon.straatnaam_c is 'Straatnaam Correspondentie';
comment on column ###LDF###.anl_persoon.huisnummer_c is 'Huisnummer Correspondentie';
comment on column ###LDF###.anl_persoon.huisletter_c is 'Huisletter Correspondentie';
comment on column ###LDF###.anl_persoon.huisnummertoevoeging_c is 'Huisnummertoevoeging Correspondentie';
comment on column ###LDF###.anl_persoon.aanduidingbijhuisnummer_c is 'Aanduiding bij huisnummer Correspondentie';
comment on column ###LDF###.anl_persoon.locatieomschrijving_c is 'Locatieomschrijving Correspondentie';
comment on column ###LDF###.anl_persoon.adresbinnenland1_c is 'Adres binnenland (1) Correspondentie';
comment on column ###LDF###.anl_persoon.adresbinnenland2_c is 'Adres binnenland (2) Correspondentie';
comment on column ###LDF###.anl_persoon.adresbinnenland3_c is 'Adres binnenland (3) Correspondentie';
comment on column ###LDF###.anl_persoon.adresbuitenland1_c is 'Adres buitenland (1) Correspondentie';
comment on column ###LDF###.anl_persoon.adresbuitenland2_c is 'Adres buitenland (2) Correspondentie';
comment on column ###LDF###.anl_persoon.adresbuitenland3_c is 'Adres buitenland (3) Correspondentie';
comment on column ###LDF###.anl_persoon.postcode_c is 'Postcode Correspondentie';
comment on column ###LDF###.anl_persoon.woonplaatsnaam_c is 'Woonplaatsnaam Correspondentie';
comment on column ###LDF###.anl_persoon.gemeentecode_c is 'Gemeentecode Correspondentie';
comment on column ###LDF###.anl_persoon.gemeentenaam_c is 'Gemeente naam Correspondentie';
comment on column ###LDF###.anl_persoon.landcode_c is 'Landcode Correspondentie';
comment on column ###LDF###.anl_persoon.landnaam_c is 'Landnaam Correspondentie';
comment on column ###LDF###.anl_persoon.authentiekewoonplaatsnaam_c is 'Woonplaatsnaam authentiek Correspondentie';
comment on column ###LDF###.anl_persoon.identnummeraanduiding_c is 'Identificatie nummeraanduiding Correspondentie';
comment on column ###LDF###.anl_persoon.authidentwoonplaats_c is 'Authentieke identificatie woonplaats Correspondentie';
comment on column ###LDF###.anl_persoon.officielestraatnaam_c is 'Officiele straatnaam Correspondentie';
comment on column ###LDF###.anl_persoon.authentiekegemeentecode_c is 'Gemeentecode authentiek Correspondentie';
comment on column ###LDF###.anl_persoon.voornamen_o1 is 'Voornamen Ouder 1';
comment on column ###LDF###.anl_persoon.voorletters_o1 is 'Voorletters Ouder 1';
comment on column ###LDF###.anl_persoon.voorvoegselgeslachtsnaam_o1 is 'Voorvoegsel geslachtsnaam Ouder 1';
comment on column ###LDF###.anl_persoon.geslachtsnaam_o1 is 'Geslachtsnaam Ouder 1';
comment on column ###LDF###.anl_persoon.codegeslachtsaanduiding_o1 is 'Geslachtsaanduiding Ouder 1';
comment on column ###LDF###.anl_persoon.geboortedatum_o1 is 'Geboortedatum Ouder 1';
comment on column ###LDF###.anl_persoon.codeburgerlijkestaat_o1 is 'Burgerlijke staat Ouder 1';
comment on column ###LDF###.anl_persoon.anummer_o1 is 'A nummer Ouder 1';
comment on column ###LDF###.anl_persoon.burgerservicenummer_o1 is 'Burger Service Nummer Ouder 1';
comment on column ###LDF###.anl_persoon.datumoverlijden_o1 is 'Datum overlijden Ouder 1';
comment on column ###LDF###.anl_persoon.codeindicatiegeheim_o1 is 'ind geheim Ouder 1';
comment on column ###LDF###.anl_persoon.adresbinnenland1_o1 is 'Adres binnenland (1) Ouder 1';
comment on column ###LDF###.anl_persoon.adresbinnenland2_o1 is 'Adres binnenland (2) Ouder 1';
comment on column ###LDF###.anl_persoon.adresbinnenland3_o1 is 'Adres binnenland (3) Ouder 1';
comment on column ###LDF###.anl_persoon.adresbuitenland1_o1 is 'Adres buitenland (1) Ouder 1';
comment on column ###LDF###.anl_persoon.adresbuitenland2_o1 is 'Adres buitenland (2) Ouder 1';
comment on column ###LDF###.anl_persoon.adresbuitenland3_o1 is 'Adres buitenland (3) Ouder 1';
comment on column ###LDF###.anl_persoon.voornamen_o2 is 'Voornamen Ouder 2';
comment on column ###LDF###.anl_persoon.voorletters_o2 is 'Voorletters Ouder 2';
comment on column ###LDF###.anl_persoon.voorvoegselgeslachtsnaam_o2 is 'Voorvoegsel geslachtsnaam Ouder 2';
comment on column ###LDF###.anl_persoon.geslachtsnaam_o2 is 'Geslachtsnaam Ouder 2';
comment on column ###LDF###.anl_persoon.codegeslachtsaanduiding_o2 is 'Geslachtsaanduiding Ouder 2';
comment on column ###LDF###.anl_persoon.geboortedatum_o2 is 'Geboortedatum Ouder 2';
comment on column ###LDF###.anl_persoon.codeburgerlijkestaat_o2 is 'Burgerlijke staat Ouder 2';
comment on column ###LDF###.anl_persoon.anummer_o2 is 'A nummer Ouder 2';
comment on column ###LDF###.anl_persoon.burgerservicenummer_o2 is 'Burger Service Nummer Ouder 2';
comment on column ###LDF###.anl_persoon.datumoverlijden_o2 is 'Datum overlijden Ouder 2';
comment on column ###LDF###.anl_persoon.codeindicatiegeheim_o2 is 'ind geheim Ouder 2';
comment on column ###LDF###.anl_persoon.adresbinnenland1_o2 is 'Adres binnenland (1) Ouder 2';
comment on column ###LDF###.anl_persoon.adresbinnenland2_o2 is 'Adres binnenland (2) Ouder 2';
comment on column ###LDF###.anl_persoon.adresbinnenland3_o2 is 'Adres binnenland (3) Ouder 2';
comment on column ###LDF###.anl_persoon.adresbuitenland1_o2 is 'Adres buitenland (1) Ouder 2';
comment on column ###LDF###.anl_persoon.adresbuitenland2_o2 is 'Adres buitenland (2) Ouder 2';
comment on column ###LDF###.anl_persoon.adresbuitenland3_o2 is 'Adres buitenland (3) Ouder 2';
comment on column ###LDF###.anl_persoon.aantalparkeerplaatsen is 'Aantal parkeerplaatsen';
comment on column ###LDF###.anl_persoon.woonoppervlakte is 'Woonoppervlakte';
comment on column ###LDF###.anl_persoon.brutovloeroppervlak is 'Bruto vloeroppervlak';
comment on column ###LDF###.anl_persoon.brutoinhoud is 'Bruto inhoud';
comment on column ###LDF###.anl_persoon.statusvoa is 'HIDE';
comment on column ###LDF###.anl_persoon.omschrijvingnationaliteit_1 is 'Omschrijving nationaliteit 1';
comment on column ###LDF###.anl_persoon.omschrijvingnationaliteit_2 is 'Omschrijving nationaliteit 2';
comment on column ###LDF###.anl_persoon.omschrijvingnationaliteit_3 is 'Omschrijving nationaliteit 3';
comment on column ###LDF###.anl_persoon.actueel is 'Actueel';
comment on column ###LDF###.anl_persoon.binnengemeentelijk is 'Binnengemeentelijk';
comment on column ###LDF###.anl_persoon.odz_overlijden is 'Onderzoek overlijden';
comment on column ###LDF###.anl_persoon.odz_verblijfstitel is 'Onderzoek verblijfstitel';
comment on column ###LDF###.anl_persoon.odz_inschrijfadres is 'Onderzoek inschrijfadres';
comment on column ###LDF###.anl_persoon.odz_verblijfadres is 'Onderzoek verblijfadres';
comment on column ###LDF###.anl_persoon.odz_identificatiebewijs is 'Onderzoek identificatiebewijs';
comment on column ###LDF###.anl_persoon.odz_kindgegevens is 'Onderzoek kindgegevens';
comment on column ###LDF###.anl_persoon.odz_nationaliteit is 'Onderzoek nationaliteit';
comment on column ###LDF###.anl_persoon.odz_oudergegevens is 'Onderzoek oudergegevens';
comment on column ###LDF###.anl_persoon.odz_huwelijksgegevens is 'Onderzoek huwelijk';
comment on column ###LDF###.anl_persoon.verblijfstitelcode  is 'Code verblijfstitel';
comment on column ###LDF###.anl_persoon.datumverkrijgingverblfsttl  is 'Datum verkrijging verblijfstitel';
comment on column ###LDF###.anl_persoon.datumverliesverblijfstitel  is 'Datum verlies verblijfstitel';
comment on column ###LDF###.anl_persoon.codeindgezagminderjarige  is 'Indicatie gezag minderjarige';
comment on column ###LDF###.anl_persoon.indcurateleregister  is 'Indicatie curateleregister';
ALTER TABLE ###LDF###.anl_persoon
  OWNER TO ###OWNER###;

-- Table: ###LDF###.anl_persoon_fixed
drop table if exists ###LDF###.anl_persoon_fixed cascade;
CREATE TABLE ###LDF###.anl_persoon_fixed
(
    id numeric(14, 0),  -- Id
    firstname character varying(240),  -- Voornaam
    surname character varying(240),  -- Opgemaakte naam
    gemeentenaam character varying(50),  -- Gemeentenaam
    bsn numeric(9,0),  -- BSN
    prsadmnum numeric(10,0),  -- A-nummer
    aantal_afn numeric(2, 0),  -- Aantal afnemers
    afnemers character varying(200),  -- Afnemers
    aantal_lev numeric(2, 0),  -- Aantal leveranciers
    leveranciers character varying(200),  -- Leveranciers
    status character varying(50),  -- Status
    registratiedatum numeric(8, 0),  -- Registratie datum
    CONSTRAINT persoonfixedi00 PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ###LDF###.anl_persoon_fixed
  OWNER TO ###OWNER###;
GRANT ALL ON TABLE ###LDF###.anl_persoon_fixed TO ###OWNER###;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE ###LDF###.anl_persoon_fixed TO ###LDF###;

COMMENT ON TABLE ###LDF###.anl_persoon_fixed IS 'Details persoon fixed';
comment on column ###LDF###.anl_persoon_fixed.id  is 'Id';
comment on column ###LDF###.anl_persoon_fixed.firstname  is 'Voornaam';
comment on column ###LDF###.anl_persoon_fixed.surname  is 'Opgemaakte naam';
comment on column ###LDF###.anl_persoon_fixed.gemeentenaam  is 'Gemeentenaam';
comment on column ###LDF###.anl_persoon_fixed.bsn  is 'BSN';
comment on column ###LDF###.anl_persoon_fixed.prsadmnum  is 'A-nummer';
comment on column ###LDF###.anl_persoon_fixed.aantal_afn  is 'Aantal afnemers';
comment on column ###LDF###.anl_persoon_fixed.afnemers  is 'Afnemers';
comment on column ###LDF###.anl_persoon_fixed.aantal_lev  is 'Aantal leveranciers';
comment on column ###LDF###.anl_persoon_fixed.leveranciers  is 'Leveranciers';
comment on column ###LDF###.anl_persoon_fixed.status  is 'Status';
comment on column ###LDF###.anl_persoon_fixed.registratiedatum  is 'Registratie datum';


-- Table: ###LDF###.anl_persoon_fixed_id
drop table if exists ###LDF###.anl_kwal_persoon_fixed_id cascade;
CREATE TABLE ###LDF###.anl_kwal_persoon_fixed_id
(
  report character varying(100) NOT NULL,
  source_table character varying(50),
  CONSTRAINT persoonfixedidi00 PRIMARY KEY (report)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ###LDF###.anl_kwal_persoon_fixed_id
  OWNER TO ###OWNER###;
GRANT ALL ON TABLE ###LDF###.anl_kwal_persoon_fixed_id TO ###OWNER###;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE ###LDF###.anl_kwal_persoon_fixed_id TO ###LDF###;


-- Table: ###LDF###.anl_perceel

DROP TABLE IF EXISTS ###LDF###.anl_perceel;
CREATE TABLE ###LDF###.anl_perceel
(
  kadastralegemeentecode character varying(5), -- Kadastrale gemeentecode
  kadastralesectie character varying(2), -- Kadastrale sectie
  kadastraalperceelnummer numeric(5,0), -- Kadastraal perceelnummer
  kadastraalobjctindexletter character varying(1), -- Kadastraal object indexletter
  kadastraalobjctindexnummer numeric(4,0), -- Kadastraal object index nummer
  ingangsdatumkadastraalobjct numeric(8,0), -- Ingangsdatum Kadastraalobject
  einddatkadastraalobjct numeric(8,0), -- Einddatum Kadastraalobject
  koopjaar numeric(4,0), -- Koopjaar
  oppervlaktekadastraalobjct numeric(8,0), -- Oppervlakte Kadastraalobject
  centroidxcoordinaat numeric(9,0), -- Centroid x-coordinaat
  centroidycoordinaat numeric(9,0), -- Centroid y-coordinaat
  centroidzcoordinaat numeric(6,0) -- Centroid z-coordinaat
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE ###LDF###.anl_perceel
  IS 'Analyse perceel details';
COMMENT ON COLUMN ###LDF###.anl_perceel.kadastralegemeentecode IS 'Kadastrale gemeentecode';
COMMENT ON COLUMN ###LDF###.anl_perceel.kadastralesectie IS 'Kadastrale sectie';
COMMENT ON COLUMN ###LDF###.anl_perceel.kadastraalperceelnummer IS 'Kadastraal perceelnummer';
COMMENT ON COLUMN ###LDF###.anl_perceel.kadastraalobjctindexletter IS 'Kadastraal object indexletter';
COMMENT ON COLUMN ###LDF###.anl_perceel.kadastraalobjctindexnummer IS 'Kadastraal object index nummer';
COMMENT ON COLUMN ###LDF###.anl_perceel.ingangsdatumkadastraalobjct IS 'Ingangsdatum Kadastraalobject';
COMMENT ON COLUMN ###LDF###.anl_perceel.einddatkadastraalobjct IS 'Einddatum Kadastraalobject';
COMMENT ON COLUMN ###LDF###.anl_perceel.koopjaar IS 'Koopjaar';
COMMENT ON COLUMN ###LDF###.anl_perceel.oppervlaktekadastraalobjct IS 'Oppervlakte Kadastraalobject';
COMMENT ON COLUMN ###LDF###.anl_perceel.centroidxcoordinaat IS 'Centroid x-coordinaat';
COMMENT ON COLUMN ###LDF###.anl_perceel.centroidycoordinaat IS 'Centroid y-coordinaat';
COMMENT ON COLUMN ###LDF###.anl_perceel.centroidzcoordinaat IS 'Centroid z-coordinaat';


-- Index: ###LDF###.anl_perceeli01

CREATE INDEX anl_perceeli01
  ON ###LDF###.anl_perceel
  USING btree
  (kadastralegemeentecode COLLATE pg_catalog."default", kadastralesectie COLLATE pg_catalog."default", kadastraalperceelnummer, kadastraalobjctindexletter COLLATE pg_catalog."default", kadastraalobjctindexnummer);

ALTER TABLE ###LDF###.anl_perceel
OWNER TO ###OWNER###;


-- table anl_vbo --
DROP TABLE IF EXISTS ###LDF###.anl_vbo CASCADE ;
CREATE TABLE ###LDF###.anl_vbo
(   id numeric(14 )  not null,
    adresid numeric(14 ),
    identvbo character varying(16 ),
    adres1 character varying(110 ),
    adres2 character varying(110 ),
    adres3 character varying(50 ),
    gebruiksdoeloms1 character varying(100 ),
    gebruiksdoeloms2 character varying(100 ),
    gebruiksdoeloms3 character varying(100 ),
    gebruiksdoeloms4 character varying(100 ),
    gebruiksdoeloms5 character varying(100 ),
    gebruiksdoeloms6 character varying(100 ),
    gebruiksdoeloms7 character varying(100 ),
    gebruiksdoeloms8 character varying(100 ),
    gebruiksdoeloms9 character varying(100 ),
    gebruiksdoeloms10 character varying(100 ),
    gebruiksdoeloms11 character varying(100 ),
    datumaanvangbouw numeric(8 ),
    datumbouwgereed numeric(8 ),
    sloopdatum numeric(8 ),
    datumbegin numeric(8 ),
    datumeinde numeric(8 ),
    bouwjaar numeric(4 ),
    bouwjaarklasse character varying(9 ),
    begdattijdvakgeldigheidbag numeric(17 ),
    einddattijdvakgeldigheidbag numeric(17 ),
    gebeurteniscode character varying(16 ),
    aantalparkeerplaatsen numeric(6 ),
    aantalruimten numeric(6 ),
    aantalwoonlagen numeric(6 ),
    aantalwoonvertrekken numeric(6 ),
    aanuitbouw character varying(1 ),
    bebouwdeterreinoppervlakte numeric(5 ),
    codebrkbrhdhoofdwoonvertrek numeric(1 ),
    omsbereikbaarhfdwoonvertrek character varying(40 ),
    kernoppervlakte numeric(6 ),
    codebouwkbestemmingactueel1 numeric(4 ),
    codebouwkbestemmingactueel2 numeric(4 ),
    codebouwkbestemmingactueel3 numeric(4 ),
    codebouwkundigebestoorspr1 numeric(4 ),
    codebouwkundigebestoorspr2 numeric(4 ),
    codebouwkundigebestoorspr3 numeric(4 ),
    omsbouwkbestemmingactueel1 character varying(40 ),
    omsbouwkbestemmingactueel2 character varying(40 ),
    omsbouwkbestemmingactueel3 character varying(40 ),
    omsbouwkundigebestoorspr1 character varying(40 ),
    omsbouwkundigebestoorspr2 character varying(40 ),
    omsbouwkundigebestoorspr3 character varying(40 ),
    bouwtechnkwalcode character varying(1 ),
    omsbouwtechnkwal character varying(40 ),
    brutoinhoud numeric(6 ),
    brutovloeroppervlak numeric(6 ),
    coderedentoevbeeind character varying(2 ),
    omsrdntoevbeeindiging character varying(40 ),
    frontbreedte numeric(3 ),
    gemiddeldebreedte numeric(3 ),
    gemiddeldehoogte numeric(3 ),
    gemiddeldelengte numeric(3 ),
    hoogstebouwlaag numeric(3 ),
    laagstebouwlaag numeric(3 ),
    lifttypecode1 numeric(1 ),
    lifttypecode2 numeric(1 ),
    lifttypecode3 numeric(1 ),
    lifttypeoms1 character varying(40 ),
    lifttypeoms2 character varying(40 ),
    lifttypeoms3 character varying(40 ),
    monumentaandcode numeric(1 ),
    omsmonumentaand character varying(40 ),
    onbebouwdeterreinopp numeric(10 ),
    codeonderhoudstoestand character varying(1 ),
    omsonderhoudstoestand character varying(40 ),
    renovatiejaar numeric(4 ),
    codesoortwoonobject numeric(1 ),
    omssoortwoonobject character varying(40 ),
    verblijfsobjectnummer numeric(12 ),
    codeverblijfsobjecttype numeric(3 ),
    omstypeverblijfsobject character varying(40 ),
    woonoppervlakte numeric(6 ),
    aandgegevensinodz character varying(1 ),
    geconstateerd character varying(1 ),
    statuscode character varying(2 ),
    status character varying(100 ),
    typeobject character varying(1 ),
    typeoms character varying(90 ),
    typering character varying(40 ),
    woonlaag numeric(2 ),
    authentiek character varying(1 ),
    datumbrondocument numeric(8 ),
    identbrondocument character varying(20 ),
    buurtnaam character varying(50 ),
    registratiedatum numeric(17 ),
    gemeentenaam character varying(50 ),
    puntgeometrie text,
    vlakgeometrie text,
    inwoppervlakte character varying(70 ),
    oppervlakte numeric(6 ),
    statusvoortgangbouw character varying(50 ),
    toegangbouwlaag numeric(3 ),
    type character varying(40 ),
    ontsluitingverdieping character varying(3 ),
    wijknaam character varying(50 ),
    gemeentecode numeric(4 ),
    centroidxcoordinaat numeric(9 ),
    centroidycoordinaat numeric(9 ),
    centroidzcoordinaat numeric(6 ),
    geometrie text,
    actueel character varying(1 ),
    binnengemeentelijk character varying(1 ),
    aantal_personen numeric(5 )
)
WITH (
  OIDS=FALSE
);

CREATE INDEX anl_vboI01 on ###LDF###.anl_vbo USING btree (adresid);
CREATE INDEX anl_vboI02 on ###LDF###.anl_vbo USING btree (identvbo);
CREATE INDEX anl_vboI03 on ###LDF###.anl_vbo USING btree (adres1);
CREATE INDEX anl_vboI04 on ###LDF###.anl_vbo USING btree (adres2);
CREATE INDEX anl_vboI05 on ###LDF###.anl_vbo USING btree (verblijfsobjectnummer);

--------------------------------------------------------------------------
-- Comments for anl_vbo
--------------------------------------------------------------------------
COMMENT ON TABLE ###LDF###.anl_vbo IS 'Details verblijfsobject';
comment on column ###LDF###.anl_vbo.id is 'Sleutel gegevensbeheer (vbo)';
comment on column ###LDF###.anl_vbo.adresid is 'Sleutel gegevensbeheer (adres)';
comment on column ###LDF###.anl_vbo.identvbo is 'Identificatie verblijfsobject';
comment on column ###LDF###.anl_vbo.adres1 is 'Adres binnen- of buitenland (1)';
comment on column ###LDF###.anl_vbo.adres2 is 'Adres binnen- of buitenland (2)';
comment on column ###LDF###.anl_vbo.adres3 is 'Adres binnen- of buitenland (3)';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms1 is 'Gebruiksdoel omschrijving1';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms2 is 'Gebruiksdoel omschrijving2';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms3 is 'Gebruiksdoel omschrijving3';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms4 is 'Gebruiksdoel omschrijving4';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms5 is 'Gebruiksdoel omschrijving5';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms6 is 'Gebruiksdoel omschrijving6';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms7 is 'Gebruiksdoel omschrijving7';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms8 is 'Gebruiksdoel omschrijving8';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms9 is 'Gebruiksdoel omschrijving9';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms10 is 'Gebruiksdoel omschrijving10';
comment on column ###LDF###.anl_vbo.gebruiksdoeloms11 is 'Gebruiksdoel omschrijving11';
comment on column ###LDF###.anl_vbo.datumaanvangbouw is 'Datum aanvangbouw';
comment on column ###LDF###.anl_vbo.datumbouwgereed is 'Datum bouw gereed';
comment on column ###LDF###.anl_vbo.sloopdatum is 'Sloopdatum';
comment on column ###LDF###.anl_vbo.datumbegin is 'Datum begin';
comment on column ###LDF###.anl_vbo.datumeinde is 'Datum einde';
comment on column ###LDF###.anl_vbo.bouwjaar is 'Bouwjaar';
comment on column ###LDF###.anl_vbo.bouwjaarklasse is 'Bouwjaarklasse';
comment on column ###LDF###.anl_vbo.begdattijdvakgeldigheidbag is 'Begindatum tijdvakgeldigheid BAG';
comment on column ###LDF###.anl_vbo.einddattijdvakgeldigheidbag is 'Einddatum tijdvakgeldigheid BAG';
comment on column ###LDF###.anl_vbo.gebeurteniscode is 'Gebeurteniscode';
comment on column ###LDF###.anl_vbo.aantalparkeerplaatsen is 'Aantal parkeerplaatsen';
comment on column ###LDF###.anl_vbo.aantalruimten is 'Aantal ruimten';
comment on column ###LDF###.anl_vbo.aantalwoonlagen is 'Aantal woonlagen';
comment on column ###LDF###.anl_vbo.aantalwoonvertrekken is 'Aantal woonvertrekken';
comment on column ###LDF###.anl_vbo.aanuitbouw is 'Aan of uitbouw';
comment on column ###LDF###.anl_vbo.bebouwdeterreinoppervlakte is 'Bebouwde terreinoppervlakte';
comment on column ###LDF###.anl_vbo.codebrkbrhdhoofdwoonvertrek is 'Bereikbaarheid hoofdwoonvertrek';
comment on column ###LDF###.anl_vbo.omsbereikbaarhfdwoonvertrek is 'Omschrijving bereikbaarheid hoofdwoonvertrek';
comment on column ###LDF###.anl_vbo.kernoppervlakte is 'Kern oppervlakte';
comment on column ###LDF###.anl_vbo.codebouwkbestemmingactueel1 is 'Code Bouwkundige bestemming actueel 1';
comment on column ###LDF###.anl_vbo.codebouwkbestemmingactueel2 is 'Code Bouwkundige bestemming actueel 2';
comment on column ###LDF###.anl_vbo.codebouwkbestemmingactueel3 is 'Code Bouwkundige bestemming actueel 3';
comment on column ###LDF###.anl_vbo.codebouwkundigebestoorspr1 is 'Code Bouwkundige bestemming oorspronkelijk 1';
comment on column ###LDF###.anl_vbo.codebouwkundigebestoorspr2 is 'Code Bouwkundige bestemming oorspronkelijk 2';
comment on column ###LDF###.anl_vbo.codebouwkundigebestoorspr3 is 'Code Bouwkundige bestemming oorspronkelijk 3';
comment on column ###LDF###.anl_vbo.omsbouwkbestemmingactueel1 is 'Bouwkundige bestemming actueel 1';
comment on column ###LDF###.anl_vbo.omsbouwkbestemmingactueel2 is 'Bouwkundige bestemming actueel 2';
comment on column ###LDF###.anl_vbo.omsbouwkbestemmingactueel3 is 'Bouwkundige bestemming actueel 3';
comment on column ###LDF###.anl_vbo.omsbouwkundigebestoorspr1 is 'Bouwkundige bestemming oorspronkelijk 1';
comment on column ###LDF###.anl_vbo.omsbouwkundigebestoorspr2 is 'Bouwkundige bestemming oorspronkelijk 2';
comment on column ###LDF###.anl_vbo.omsbouwkundigebestoorspr3 is 'Bouwkundige bestemming oorspronkelijk 3';
comment on column ###LDF###.anl_vbo.bouwtechnkwalcode is 'Code bouwTechn Kwal';
comment on column ###LDF###.anl_vbo.omsbouwtechnkwal is 'Omschrijving bouwTechn Kwal';
comment on column ###LDF###.anl_vbo.brutoinhoud is 'Bruto inhoud';
comment on column ###LDF###.anl_vbo.brutovloeroppervlak is 'Bruto vloeroppervlak';
comment on column ###LDF###.anl_vbo.coderedentoevbeeind is 'Code reden toevoeging of beeindiging';
comment on column ###LDF###.anl_vbo.omsrdntoevbeeindiging is 'Omschrijving reden toevoeging beeindiging';
comment on column ###LDF###.anl_vbo.frontbreedte is 'Frontbreedte';
comment on column ###LDF###.anl_vbo.gemiddeldebreedte is 'Gemiddelde breedte';
comment on column ###LDF###.anl_vbo.gemiddeldehoogte is 'Gemiddelde hoogte';
comment on column ###LDF###.anl_vbo.gemiddeldelengte is 'Gemiddelde lengte';
comment on column ###LDF###.anl_vbo.hoogstebouwlaag is 'Hoogste bouwlaag';
comment on column ###LDF###.anl_vbo.laagstebouwlaag is 'Laagste bouwlaag';
comment on column ###LDF###.anl_vbo.lifttypecode1 is 'Code lifttype1';
comment on column ###LDF###.anl_vbo.lifttypecode2 is 'Code lifttype2';
comment on column ###LDF###.anl_vbo.lifttypecode3 is 'Code lifttype3';
comment on column ###LDF###.anl_vbo.lifttypeoms1 is 'Omschrijving lifttype1';
comment on column ###LDF###.anl_vbo.lifttypeoms2 is 'Omschrijving lifttype2';
comment on column ###LDF###.anl_vbo.lifttypeoms3 is 'Omschrijving lifttype3';
comment on column ###LDF###.anl_vbo.monumentaandcode is 'Code monumentaanduiding';
comment on column ###LDF###.anl_vbo.omsmonumentaand is 'Omschrijving monumentaanduiding';
comment on column ###LDF###.anl_vbo.onbebouwdeterreinopp is 'Onbebouwde terreinoppervlakte';
comment on column ###LDF###.anl_vbo.codeonderhoudstoestand is 'Onderhoudstoestand';
comment on column ###LDF###.anl_vbo.omsonderhoudstoestand is 'Omschrijving onderhoudstoestand';
comment on column ###LDF###.anl_vbo.renovatiejaar is 'Renovatiejaar';
comment on column ###LDF###.anl_vbo.codesoortwoonobject is 'Soort woonobject';
comment on column ###LDF###.anl_vbo.omssoortwoonobject is 'Omschrijving soort woonobject';
comment on column ###LDF###.anl_vbo.verblijfsobjectnummer is 'Verblijfsobjectnummer';
comment on column ###LDF###.anl_vbo.codeverblijfsobjecttype is 'Verblijfsobjecttype';
comment on column ###LDF###.anl_vbo.omstypeverblijfsobject is 'Omschrijving type verblijfsobject';
comment on column ###LDF###.anl_vbo.woonoppervlakte is 'Woonoppervlakte';
comment on column ###LDF###.anl_vbo.aandgegevensinodz is 'Aanduiding gegevens in onderzoek';
comment on column ###LDF###.anl_vbo.geconstateerd is 'Geconstateerd';
comment on column ###LDF###.anl_vbo.statuscode is 'Status code';
comment on column ###LDF###.anl_vbo.status is 'Status';
comment on column ###LDF###.anl_vbo.typeobject is 'Type object';
comment on column ###LDF###.anl_vbo.typeoms is 'Documenttype-omschrijving';
comment on column ###LDF###.anl_vbo.typering is 'Typering';
comment on column ###LDF###.anl_vbo.woonlaag is 'Woonlaag';
comment on column ###LDF###.anl_vbo.authentiek is 'Authentiek';
comment on column ###LDF###.anl_vbo.datumbrondocument is 'Datum brondocument';
comment on column ###LDF###.anl_vbo.identbrondocument is 'Identificatie brondocument';
comment on column ###LDF###.anl_vbo.buurtnaam is 'Buurtnaam';
comment on column ###LDF###.anl_vbo.registratiedatum is 'Registratiedatum';
comment on column ###LDF###.anl_vbo.gemeentenaam is 'Gemeente naam';
comment on column ###LDF###.anl_vbo.puntgeometrie is 'Puntgeometrie';
comment on column ###LDF###.anl_vbo.vlakgeometrie is 'Vlakgeometrie';
comment on column ###LDF###.anl_vbo.inwoppervlakte is 'Inwinningswijze oppervlakte';
comment on column ###LDF###.anl_vbo.oppervlakte is 'Oppervlakte';
comment on column ###LDF###.anl_vbo.statusvoortgangbouw is 'Status voortgang bouw';
comment on column ###LDF###.anl_vbo.toegangbouwlaag is 'Toegang bouwlaag';
comment on column ###LDF###.anl_vbo.type is 'Type';
comment on column ###LDF###.anl_vbo.ontsluitingverdieping is 'Ontsluiting verdieping';
comment on column ###LDF###.anl_vbo.wijknaam is 'Wijknaam';
comment on column ###LDF###.anl_vbo.gemeentecode is 'Gemeentecode';
comment on column ###LDF###.anl_vbo.centroidxcoordinaat is 'Centroid x-coordinaat';
comment on column ###LDF###.anl_vbo.centroidycoordinaat is 'Centroid y-coordinaat';
comment on column ###LDF###.anl_vbo.centroidzcoordinaat is 'Centroid z-coordinaat';
comment on column ###LDF###.anl_vbo.geometrie is 'Geometrie';
comment on column ###LDF###.anl_vbo.actueel is 'Actueel';
comment on column ###LDF###.anl_vbo.binnengemeentelijk is 'Binnengemeentelijk';
comment on column ###LDF###.anl_vbo.aantal_personen is 'Aantal personen';

ALTER TABLE ###LDF###.anl_vbo
OWNER TO ###OWNER###;

-- MODULE KWALITEIT
-- table anl_kwal_adres_id --
drop table if exists ###LDF###.anl_kwal_adres_id cascade;
create table ###LDF###.anl_kwal_adres_id (
    id                             numeric(14, 0)          not null,
    report                         character varying(100)
)
with (
  oids=false
);

comment on column ###LDF###.anl_kwal_adres_id.id is 'Id';
comment on column ###LDF###.anl_kwal_adres_id.report is 'Rapport';

create index anl_kwal_adres_idi01 on
###LDF###.anl_kwal_adres_id
(report);

ALTER TABLE ###LDF###.anl_kwal_adres_id
  OWNER TO ###OWNER###;

drop table if exists ###LDF###.anl_kwal_adres_fixed_id cascade;
create table ###LDF###.anl_kwal_adres_fixed_id (
    report                         character varying(100) NOT NULL,
    source_table                   character varying(50),
    constraint adresfixedidi00 primary key (report)
)
with (
  oids=false
);

--comment on column ###LDF###.anl_kwal_adres_fixed_id.id is 'Id';
--comment on column ###LDF###.anl_kwal_adres_fixed_id.report is 'Rapport';


ALTER TABLE ###LDF###.anl_kwal_adres_fixed_id
  OWNER TO ###OWNER###;

-- table anl_kwal_persoon_id --
drop table if exists ###LDF###.anl_kwal_persoon_id cascade;
create table ###LDF###.anl_kwal_persoon_id (
    id                             numeric(14, 0)          not null,
    report                         character varying(100)
)
with (
  oids=false
);

comment on column ###LDF###.anl_kwal_persoon_id.id is 'Id';
comment on column ###LDF###.anl_kwal_persoon_id.report is 'Rapport';

create index anl_kwal_persoon_idi01 on
###LDF###.anl_kwal_persoon_id
(report);

ALTER TABLE ###LDF###.anl_kwal_persoon_id
  OWNER TO ###OWNER###;

-- table anl_kwal_bedrijf_id --
drop table if exists ###LDF###.anl_kwal_bedrijf_id cascade;
create table ###LDF###.anl_kwal_bedrijf_id (
    id                             numeric(14, 0)          not null,
    report                         character varying(100)
)
with (
  oids=false
);

comment on column ###LDF###.anl_kwal_bedrijf_id.id is 'Id';
comment on column ###LDF###.anl_kwal_bedrijf_id.report is 'Rapport';

create index anl_kwal_bedrijf_idi01 on
###LDF###.anl_kwal_bedrijf_id
(report);

ALTER TABLE ###LDF###.anl_kwal_bedrijf_id
  OWNER TO ###OWNER###;

-- table anl_kwal_vbo_id --
drop table if exists ###LDF###.anl_kwal_vbo_id cascade;
create table ###LDF###.anl_kwal_vbo_id (
    id                             numeric(14, 0)          not null,
    report                         character varying(100)
)
with (
  oids=false
);

comment on column ###LDF###.anl_kwal_vbo_id.id is 'Id';
comment on column ###LDF###.anl_kwal_vbo_id.report is 'Rapport';

create index anl_kwal_vbo_idi01 on
###LDF###.anl_kwal_vbo_id
(report);

ALTER TABLE ###LDF###.anl_kwal_vbo_id
  OWNER TO ###OWNER###;

-- table anl_kwal_perceel_id --
drop table if exists ###LDF###.anl_kwal_perceel_id cascade;
create table ###LDF###.anl_kwal_perceel_id (
    id                             numeric(14, 0)          not null,
    report                         character varying(100)
)
with (
  oids=false
);

comment on column ###LDF###.anl_kwal_perceel_id.id is 'Id';
comment on column ###LDF###.anl_kwal_perceel_id.report is 'Rapport';

create index anl_kwal_perceel_idi01 on
###LDF###.anl_kwal_perceel_id
(report);

ALTER TABLE ###LDF###.anl_kwal_perceel_id
  OWNER TO ###OWNER###;

-- views over de entiteiten
DROP VIEW IF EXISTS ###LDF###.anl_kwal_adres_view cascade;
CREATE VIEW ###LDF###.anl_kwal_adres_view AS
SELECT id.report,
       det.*,
       afn.afnemers,
       afn.leveranciers,
       afn.aantal_afn,
       afn.aantal_lev
		 FROM ###LDF###.anl_kwal_adres_id id
		 JOIN ###LDF###.anl_adres det ON id.id = det.id
		 LEFT JOIN ###LDF###.anl_afn_lev_atl_adres afn ON id.id = afn.id
		 order by 2, 1;

ALTER VIEW ###LDF###.anl_kwal_adres_view
  OWNER TO ###OWNER###;

-- bij de view worden de indexen van de table gebruikt

DROP VIEW IF EXISTS ###LDF###.anl_kwal_persoon_view cascade;
CREATE VIEW ###LDF###.anl_kwal_persoon_view AS
SELECT id.report,
       det.*,
       afn.afnemers,
       afn.leveranciers,
       afn.aantal_afn,
       afn.aantal_lev
		 FROM ###LDF###.anl_kwal_persoon_id id
		 JOIN ###LDF###.anl_persoon det ON id.id = det.id
		 LEFT JOIN ###LDF###.anl_afn_lev_atl_persoon afn ON id.id = afn.id
		 order by 2, 1;

ALTER VIEW ###LDF###.anl_kwal_persoon_view
  OWNER TO ###OWNER###;

DROP VIEW IF EXISTS ###LDF###.anl_kwal_bedrijf_view cascade;
CREATE VIEW ###LDF###.anl_kwal_bedrijf_view AS
SELECT id.report,
       det.*,
       afn.afnemers,
       afn.leveranciers,
       afn.aantal_afn,
       afn.aantal_lev
		 FROM ###LDF###.anl_kwal_bedrijf_id id
		 JOIN ###LDF###.anl_bedrijf det ON id.id = det.id
		 LEFT JOIN ###LDF###.anl_afn_lev_atl_bedrijf afn ON id.id = afn.id
		 order by 2, 1;

ALTER VIEW ###LDF###.anl_kwal_bedrijf_view
  OWNER TO ###OWNER###;

DROP VIEW IF EXISTS ###LDF###.anl_kwal_vbo_view cascade;
CREATE VIEW ###LDF###.anl_kwal_vbo_view AS
SELECT id.report,
       det.*,
       afn.afnemers,
       afn.leveranciers,
       afn.aantal_afn,
       afn.aantal_lev
		 FROM ###LDF###.anl_kwal_vbo_id id
		 JOIN ###LDF###.anl_vbo det ON id.id = det.id
		 LEFT JOIN ###LDF###.anl_afn_lev_atl_vbo afn ON id.id = afn.id
		 order by 2, 1;

ALTER VIEW ###LDF###.anl_kwal_vbo_view
  OWNER TO ###OWNER###;

-- comments for view anl_kwal_adres_view --
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.report IS 'Rapport';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.id IS 'Sleutel gegevensbeheer';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.identnummeraanduiding IS 'Identificatie nummeraanduiding';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.authidentopenbareruimte IS 'Authentieke identificatie openbare ruimte';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.adres1 IS 'Adres binnen- of buitenland (1)';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.adres2 IS 'Adres binnen- of buitenland (2)';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.adres3 IS 'Adres binnen- of buitenland (3)';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.datumbegin IS 'Datum begin';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.datumeinde IS 'Datum einde';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.ingangsdatum IS 'Ingangsdatum';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.statuscodeoms IS 'Omschrijving status';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.aandgegevensinodz IS 'Aanduiding gegevens in onderzoek';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.geconstateerd IS 'Geconstateerd';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.statuscode IS 'Status code';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.codegebeurtenis IS 'Code gebeurtenis';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.authentiek IS 'Authentiek';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.straatnaam_officieel IS 'Straatnaam officieel';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.straatnaam IS 'Straatnaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.postcode IS 'Postcode';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.huisnummer IS 'Huisnummer';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.huisletter IS 'Huisletter';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.huisnummertoevoeging IS 'Huisnummertoevoeging';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.aanduidingbijhuisnummer IS 'Aanduiding bij huisnummer';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.locatieomschrijving IS 'Locatieomschrijving';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.woonplaatsnaam IS 'Woonplaatsnaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.authentiekewoonplaatscode IS 'Woonplaatscode authentiek';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.authidentwoonplaats IS 'Authentieke identificatie woonplaats';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.authentiekewoonplaatsnaam IS 'Woonplaatsnaam authentiek';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.buurtcode IS 'Buurtcode';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.wijkcode IS 'Wijkcode';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.straatcode IS 'Straatcode';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.woonplaatcode IS 'Woonplaatscode';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.gemeentecode IS 'Gemeentecode';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.authentiekegemeentecode IS 'Gemeentecode authentiek';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.landcode IS 'Landcode';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.identverblijfplaats IS 'Identificatiecode verblijfplaats';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.centroidxcoordinaat IS 'Centroid x-coordinaat';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.centroidycoordinaat IS 'Centroid y-coordinaat';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.centroidzcoordinaat IS 'Centroid z-coordinaat';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.buurtnaam IS 'Buurtnaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.wijknaam IS 'Wijknaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.gemeentenaam IS 'Gemeente naam';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.landnaam IS 'Landnaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.persoon_verblijf_adres IS 'Verblijfsadres persoon';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.persoon_inschrijf_adres IS 'Inschrijfadres persoon';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.persoon_corresp_adres IS 'Correspondentieadres persoon';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.bedrijf_vestigings_adres IS 'Vestigingsadres bedrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.bedrijf_corresp_adres IS 'Correspondentieadres bedrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.vbo_adres IS 'Verblijfsobject adres';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.vbo_neven_adres IS 'Verblijfsobject nevenadres';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.kadastraal_adres IS 'Kadastraal adres';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.actueel IS 'Actueel adres';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.binnengemeentelijk IS 'Binnengemeentelijk adres';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.afnemers IS 'Afnemers';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.leveranciers IS 'Leveranciers';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.aantal_afn IS 'Aantal afnemers';
COMMENT ON COLUMN ###LDF###.anl_kwal_adres_view.aantal_lev IS 'Aantal leveranciers';

-- comments for view anl_kwal_persoon_view --
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.report IS 'Rapport';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.id IS 'Sleutel gegevensbeheer';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.burgerservicenummer IS 'BSN';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.gemeentecodeinschrijving IS 'Gemeentecode inschrijving';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voornamen IS 'Voornamen';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voorletters IS 'Voorletters';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voorvoegselgeslachtsnaam IS 'Voorvoegsel geslachtsnaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geslachtsnaam IS 'Geslachtsnaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codegeslachtsaanduiding IS 'Geslachtsaanduiding';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codeadellijketitelpredikaat IS 'Code adellijke titel predikaat';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geboortedatum IS 'Geboortedatum';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codegeboorteplaats IS 'Gemeentecode geboorteplaats';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geboorteplaats IS 'Geboorteplaats';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geboortelandcode IS 'Geboortelandcode';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codeburgerlijkestaat IS 'Burgerlijke staat';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.anummer IS 'A nummer';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.datumoverlijden IS 'Datum overlijden';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codeplaatsoverlijden IS 'Gemeentecode plaats overlijden';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.omsplaatsoverlijden IS 'Omschrijving plaatsoverlijden';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.landcodeoverlijden IS 'Landcode overlijden';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.redenopschorting IS 'Reden opschorting';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codeindicatiegeheim IS 'Indicatie geheim';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authentiek IS 'Authentiek';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.telefoonnummer1 IS 'Telefoonnummer 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.telefoonnummer2 IS 'Telefoonnummer 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.telefoonnummer3 IS 'Telefoonnummer 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.faxnummer1 IS 'Faxnummer 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.faxnummer2 IS 'Faxnummer 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.faxnummer3 IS 'Faxnummer 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.emailadres1 IS 'E-mail adres 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.emailadres2 IS 'E-mail adres 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.emailadres3 IS 'E-mail adres 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.bankgirorekeningnummer IS 'Bank/girorekeningnummer';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.aanduidingnaamgebruik IS 'Aanduiding naamgebruik';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.opgemnaamzonderpredikaten IS 'Opgemaakte naam zonder predikaten';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.opgemnaam IS 'Opgemaakte naam';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geslachtsnaamechtgenoot IS 'Geslachtsnaam echtgenoot';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voorvsgeslachtsnmechtgnt IS 'Voorvoegsels geslachtsnaam echtgenoot';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.datumvertrekuitnederland IS 'Datum vertrek uit Nederland';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.datumsluiting IS 'Datum sluiting';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.datumontbinding IS 'Datum ontbinding';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.coderedenontbinding IS 'Reden ontbinding';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.burgerservicenummerechtgnt IS 'Burger Service Nummer echtgenoot';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geslachtsaanduidingechtgnt IS 'Geslachts aanduiding echtgenoot';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.begdatrelatie_i IS 'Begindatum relatie Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.straatnaam_i IS 'Straatnaam Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.huisnummer_i IS 'Huisnummer Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.huisletter_i IS 'Huisletter Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.huisnummertoevoeging_i IS 'Huisnummertoevoeging Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.aanduidingbijhuisnummer_i IS 'Aanduiding bij huisnummer Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.locatieomschrijving_i IS 'Locatieomschrijving Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland1_i IS 'Adres binnenland (1) Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland2_i IS 'Adres binnenland (2) Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland3_i IS 'Adres binnenland (3) Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland1_i IS 'Adres buitenland (1) Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland2_i IS 'Adres buitenland (2) Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland3_i IS 'Adres buitenland (3) Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.postcode_i IS 'Postcode Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.woonplaatsnaam_i IS 'Woonplaatsnaam Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.gemeentecode_i IS 'Gemeentecode Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.gemeentenaam_i IS 'Gemeente naam Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.landcode_i IS 'Landcode Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.landnaam_i IS 'Landnaam Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authentiekewoonplaatsnaam_i IS 'Woonplaatsnaam authentiek Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.identnummeraanduiding_i IS 'Identificatie nummeraanduiding Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authidentwoonplaats_i IS 'Authentieke identificatie woonplaats Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.officielestraatnaam_i IS 'Officiele straatnaam Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authentiekegemeentecode_i IS 'Gemeentecode authentiek Inschrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.begdatrelatie_v IS 'Begindatum relatie Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.straatnaam_v IS 'Straatnaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.huisnummer_v IS 'Huisnummer Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.huisletter_v IS 'Huisletter Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.huisnummertoevoeging_v IS 'Huisnummertoevoeging Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.aanduidingbijhuisnummer_v IS 'Aanduiding bij huisnummer Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.locatieomschrijving_v IS 'Locatieomschrijving Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland1_v IS 'Adres binnenland (1) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland2_v IS 'Adres binnenland (2) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland3_v IS 'Adres binnenland (3) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland1_v IS 'Adres buitenland (1) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland2_v IS 'Adres buitenland (2) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland3_v IS 'Adres buitenland (3) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.postcode_v IS 'Postcode Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.woonplaatsnaam_v IS 'Woonplaatsnaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.gemeentecode_v IS 'Gemeentecode Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.gemeentenaam_v IS 'Gemeente naam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.landcode_v IS 'Landcode Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.landnaam_v IS 'Landnaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.wijkcode_v IS 'Wijkcode Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.buurtcode_v IS 'Buurtcode Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.wijknaam_v IS 'Wijknaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.buurtnaam_v IS 'Buurtnaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authentiekewoonplaatsnaam_v IS 'Woonplaatsnaam authentiek Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.identnummeraanduiding_v IS 'Identificatie nummeraanduiding Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authidentwoonplaats_v IS 'Authentieke identificatie woonplaats Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.officielestraatnaam_v IS 'Officiele straatnaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authentiekegemeentecode_v IS 'Gemeentecode authentiek Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.straatnaam_c IS 'Straatnaam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.huisnummer_c IS 'Huisnummer Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.huisletter_c IS 'Huisletter Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.huisnummertoevoeging_c IS 'Huisnummertoevoeging Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.aanduidingbijhuisnummer_c IS 'Aanduiding bij huisnummer Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.locatieomschrijving_c IS 'Locatieomschrijving Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland1_c IS 'Adres binnenland (1) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland2_c IS 'Adres binnenland (2) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland3_c IS 'Adres binnenland (3) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland1_c IS 'Adres buitenland (1) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland2_c IS 'Adres buitenland (2) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland3_c IS 'Adres buitenland (3) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.postcode_c IS 'Postcode Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.woonplaatsnaam_c IS 'Woonplaatsnaam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.gemeentecode_c IS 'Gemeentecode Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.gemeentenaam_c IS 'Gemeente naam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.landcode_c IS 'Landcode Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.landnaam_c IS 'Landnaam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authentiekewoonplaatsnaam_c IS 'Woonplaatsnaam authentiek Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.identnummeraanduiding_c IS 'Identificatie nummeraanduiding Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authidentwoonplaats_c IS 'Authentieke identificatie woonplaats Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.officielestraatnaam_c IS 'Officiele straatnaam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.authentiekegemeentecode_c IS 'Gemeentecode authentiek Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voornamen_o1 IS 'Voornamen Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voorletters_o1 IS 'Voorletters Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voorvoegselgeslachtsnaam_o1 IS 'Voorvoegsel geslachtsnaam Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geslachtsnaam_o1 IS 'Geslachtsnaam Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codegeslachtsaanduiding_o1 IS 'Geslachtsaanduiding Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geboortedatum_o1 IS 'Geboortedatum Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codeburgerlijkestaat_o1 IS 'Burgerlijke staat Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.anummer_o1 IS 'A nummer Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.burgerservicenummer_o1 IS 'Burger Service Nummer Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.datumoverlijden_o1 IS 'Datum overlijden Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codeindicatiegeheim_o1 IS 'Indicatie geheim Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland1_o1 IS 'Adres binnenland (1) Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland2_o1 IS 'Adres binnenland (2) Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland3_o1 IS 'Adres binnenland (3) Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland1_o1 IS 'Adres buitenland (1) Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland2_o1 IS 'Adres buitenland (2) Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland3_o1 IS 'Adres buitenland (3) Ouder 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voornamen_o2 IS 'Voornamen Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voorletters_o2 IS 'Voorletters Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.voorvoegselgeslachtsnaam_o2 IS 'Voorvoegsel geslachtsnaam Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geslachtsnaam_o2 IS 'Geslachtsnaam Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codegeslachtsaanduiding_o2 IS 'Geslachtsaanduiding Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.geboortedatum_o2 IS 'Geboortedatum Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codeburgerlijkestaat_o2 IS 'Burgerlijke staat Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.anummer_o2 IS 'A nummer Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.burgerservicenummer_o2 IS 'Burger Service Nummer Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.datumoverlijden_o2 IS 'Datum overlijden Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codeindicatiegeheim_o2 IS 'Indicatie geheim Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland1_o2 IS 'Adres binnenland (1) Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland2_o2 IS 'Adres binnenland (2) Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbinnenland3_o2 IS 'Adres binnenland (3) Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland1_o2 IS 'Adres buitenland (1) Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland2_o2 IS 'Adres buitenland (2) Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.adresbuitenland3_o2 IS 'Adres buitenland (3) Ouder 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.aantalparkeerplaatsen IS 'Aantal parkeerplaatsen';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.woonoppervlakte IS 'Woonoppervlakte';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.brutovloeroppervlak IS 'Bruto vloeroppervlak';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.brutoinhoud IS 'Bruto inhoud';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.statusvoa IS 'HIDE';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.omschrijvingnationaliteit_1 IS 'Omschrijving nationaliteit_1';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.omschrijvingnationaliteit_2 IS 'Omschrijving nationaliteit_2';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.omschrijvingnationaliteit_3 IS 'Omschrijving nationaliteit_3';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.actueel IS 'Actueel persoon';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.binnengemeentelijk IS 'Binnengemeentelijk persoon';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.odz_overlijden IS 'Onderzoek overlijden';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.odz_verblijfstitel IS 'Onderzoek verblijfstitel';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.odz_inschrijfadres IS 'Onderzoek inschrijfadres';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.odz_verblijfadres IS 'Onderzoek verblijfadres';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.odz_identificatiebewijs IS 'Onderzoek identificatiebewijs';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.odz_kindgegevens IS 'Onderzoek kindgegevens';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.odz_nationaliteit IS 'Onderzoek nationaliteit';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.odz_oudergegevens IS 'Onderzoek oudergegevens';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.odz_huwelijksgegevens IS 'Onderzoek huwelijk';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.verblijfstitelcode IS 'Code verblijfstitel';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.datumverkrijgingverblfsttl IS 'Datum verkrijging verblijfstitel';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.datumverliesverblijfstitel IS 'Datum verlies verblijfstitel';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.codeindgezagminderjarige IS 'Indicatie gezag minderjarige';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.indcurateleregister IS 'Indicatie curateleregister';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.afnemers IS 'Afnemers';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.leveranciers IS 'Leveranciers';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.aantal_afn IS 'Aantal afnemers';
COMMENT ON COLUMN ###LDF###.anl_kwal_persoon_view.aantal_lev IS 'Aantal leveranciers';

-- comments for view anl_kwal_bedrijf_view --
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.report IS 'Rapport';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.id IS 'Sleutel gegevensbeheer';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.zaaknaam IS 'Zaaknaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.statnaamvennschpnaam IS 'Statutaire naam / vennootschapsnaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.handelsnaam IS 'Handelsnaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.burgerservicenummer IS 'Burger Service Nummer';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.nnpid IS 'Nnp-id';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.rechtsvormcode IS 'Rechtsvorm';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.indhoofdvestiging IS 'ind HoofdVestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.hoofdactiviteitcode IS 'HoofdActiviteit';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.nevenactiviteitcode IS 'NevenActiviteit';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.datumoprichtingnnp IS 'Datum oprichting niet natuurlijk persoon';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.datumbegin IS 'Datum begin';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.datumeinde IS 'Datum einde';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.datumontbinding IS 'Datum ontbinding';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.handelsregisternummer IS 'HandelsRegisternummer';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.handelsregistervolgnummer IS 'HandelsRegisterVolgnummer';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.vestigingsnummer IS 'Vestigingsnummer';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.terattentievan IS 'Ter attentie van';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.telefoonnummer1 IS 'Telefoonnummer 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.telefoonnummer2 IS 'Telefoonnummer 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.telefoonnummer3 IS 'Telefoonnummer 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.faxnummer1 IS 'Faxnummer 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.faxnummer2 IS 'Faxnummer 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.faxnummer3 IS 'Faxnummer 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.emailadres1 IS 'E-mail adres 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.emailadres2 IS 'E-mail adres 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.emailadres3 IS 'E-mail adres 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.bankgirorekeningnummer1 IS 'Bank/girorekeningnummer 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.bankgirorekeningnummer2 IS 'Bank/girorekeningnummer 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.bankgirorekeningnummer3 IS 'Bank/girorekeningnummer 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.straatnaam_v IS 'Straatnaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.huisnummer_v IS 'Huisnummer Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.postbusnummer_v IS 'Postbusnummer Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.huisletter_v IS 'Huisletter Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.huisnummertoevoeging_v IS 'Huisnummertoevoeging Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.aanduidingbijhuisnummer_v IS 'Aanduiding bij huisnummer Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.locatieomschrijving_v IS 'Locatieomschrijving Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbinnenland1_v IS 'Adres binnenland (1) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbinnenland2_v IS 'Adres binnenland (2) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbinnenland3_v IS 'Adres binnenland (3) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbuitenland1_v IS 'Adres buitenland (1) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbuitenland2_v IS 'Adres buitenland (2) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbuitenland3_v IS 'Adres buitenland (3) Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.postcode_v IS 'Postcode Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.woonplaatsnaam_v IS 'Woonplaatsnaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.gemeentecode_v IS 'Gemeentecode Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.gemeentenaam_v IS 'Gemeente naam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.landcode_v IS 'Landcode Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.landnaam_v IS 'Landnaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.authentiekewoonplaatsnaam_v IS 'Woonplaatsnaam authentiek Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.identnummeraanduiding_v IS 'Identificatie nummeraanduiding Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.authentiekewoonplaatscode_v IS 'Woonplaatscode authentiek Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.officielestraatnaam_v IS 'Officiele straatnaam Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.authentiekegemeentecode_v IS 'Gemeentecode authentiek Verblijf vestiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.straatnaam_c IS 'Straatnaam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.huisnummer_c IS 'Huisnummer Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.postbusnummer_corresp IS 'Postbusnummer Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.huisletter_c IS 'Huisletter Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.huisnummertoevoeging_c IS 'Huisnummertoevoeging Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.aanduidingbijhuisnummer_c IS 'Aanduiding bij huisnummer Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.locatieomschrijving_c IS 'Locatieomschrijving Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbinnenland1_c IS 'Adres binnenland (1) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbinnenland2_c IS 'Adres binnenland (2) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbinnenland3_c IS 'Adres binnenland (3) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbuitenland1_c IS 'Adres buitenland (1) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbuitenland2_c IS 'Adres buitenland (2) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.adresbuitenland3_c IS 'Adres buitenland (3) Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.postcode_c IS 'Postcode Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.woonplaatsnaam_c IS 'Woonplaatsnaam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.gemeentecode_c IS 'Gemeentecode Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.gemeentenaam_c IS 'Gemeente naam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.landcode_c IS 'Landcode Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.landnaam_c IS 'Landnaam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.authentiekewoonplaatsnaam_c IS 'Woonplaatsnaam authentiek Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.identnummeraanduiding_c IS 'Identificatie nummeraanduiding Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.authentiekewoonplaatscode_c IS 'Woonplaatscode authentiek Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.officielestraatnaam_c IS 'Officiele straatnaam Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.authentiekegemeentecode_c IS 'Gemeentecode authentiek Correspondentie';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.actueel IS 'Actueel bedrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.binnengemeentelijk IS 'Binnengemeentelijk bedrijf';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.afnemers IS 'Afnemers';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.leveranciers IS 'Leveranciers';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.aantal_afn IS 'Aantal afnemers';
COMMENT ON COLUMN ###LDF###.anl_kwal_bedrijf_view.aantal_lev IS 'Aantal leveranciers';
-- comments for view anl_kwal_vbo_view --
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.report IS 'Rapport';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.id IS 'Sleutel gegevensbeheer (vbo)';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.adresid IS 'Sleutel gegevensbeheer (adres)';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.identvbo IS 'Identificatie verblijfsobject';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.adres1 IS 'Adres binnen- of buitenland (1)';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.adres2 IS 'Adres binnen- of buitenland (2)';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.adres3 IS 'Adres binnen- of buitenland (3)';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms1 IS 'Gebruiksdoel omschrijving1';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms2 IS 'Gebruiksdoel omschrijving2';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms3 IS 'Gebruiksdoel omschrijving3';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms4 IS 'Gebruiksdoel omschrijving4';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms5 IS 'Gebruiksdoel omschrijving5';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms6 IS 'Gebruiksdoel omschrijving6';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms7 IS 'Gebruiksdoel omschrijving7';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms8 IS 'Gebruiksdoel omschrijving8';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms9 IS 'Gebruiksdoel omschrijving9';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms10 IS 'Gebruiksdoel omschrijving10';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebruiksdoeloms11 IS 'Gebruiksdoel omschrijving11';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.datumaanvangbouw IS 'Datum aanvangbouw';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.datumbouwgereed IS 'Datum bouw gereed';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.sloopdatum IS 'Sloopdatum';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.datumbegin IS 'Datum begin';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.datumeinde IS 'Datum einde';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.bouwjaar IS 'Bouwjaar';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.bouwjaarklasse IS 'Bouwjaarklasse';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.begdattijdvakgeldigheidbag IS 'Begindatum tijdvakgeldigheid BAG';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.einddattijdvakgeldigheidbag IS 'Einddatum tijdvakgeldigheid BAG';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gebeurteniscode IS 'Gebeurteniscode';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.aantalparkeerplaatsen IS 'Aantal parkeerplaatsen';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.aantalruimten IS 'Aantal ruimten';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.aantalwoonlagen IS 'Aantal woonlagen';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.aantalwoonvertrekken IS 'Aantal woonvertrekken';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.aanuitbouw IS 'Aan of uitbouw';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.bebouwdeterreinoppervlakte IS 'Bebouwde terreinoppervlakte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codebrkbrhdhoofdwoonvertrek IS 'Bereikbaarheid hoofdwoonvertrek';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsbereikbaarhfdwoonvertrek IS 'Omschrijving bereikbaarheid hoofdwoonvertrek';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.kernoppervlakte IS 'Kern oppervlakte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codebouwkbestemmingactueel1 IS 'Code Bouwkundige bestemming actueel 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codebouwkbestemmingactueel2 IS 'Code Bouwkundige bestemming actueel 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codebouwkbestemmingactueel3 IS 'Code Bouwkundige bestemming actueel 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codebouwkundigebestoorspr1 IS 'Code Bouwkundige bestemming oorspronkelijk 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codebouwkundigebestoorspr2 IS 'Code Bouwkundige bestemming oorspronkelijk 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codebouwkundigebestoorspr3 IS 'Code Bouwkundige bestemming oorspronkelijk 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsbouwkbestemmingactueel1 IS 'Bouwkundige bestemming actueel 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsbouwkbestemmingactueel2 IS 'Bouwkundige bestemming actueel 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsbouwkbestemmingactueel3 IS 'Bouwkundige bestemming actueel 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsbouwkundigebestoorspr1 IS 'Bouwkundige bestemming oorspronkelijk 1';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsbouwkundigebestoorspr2 IS 'Bouwkundige bestemming oorspronkelijk 2';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsbouwkundigebestoorspr3 IS 'Bouwkundige bestemming oorspronkelijk 3';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.bouwtechnkwalcode IS 'Code bouwTechn Kwal';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsbouwtechnkwal IS 'Omschrijving bouwTechn Kwal';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.brutoinhoud IS 'Bruto inhoud';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.brutovloeroppervlak IS 'Bruto vloeroppervlak';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.coderedentoevbeeind IS 'Code reden toevoeging of beeindiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsrdntoevbeeindiging IS 'Omschrijving reden toevoeging beeindiging';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.frontbreedte IS 'Frontbreedte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gemiddeldebreedte IS 'Gemiddelde breedte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gemiddeldehoogte IS 'Gemiddelde hoogte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gemiddeldelengte IS 'Gemiddelde lengte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.hoogstebouwlaag IS 'Hoogste bouwlaag';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.laagstebouwlaag IS 'Laagste bouwlaag';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.lifttypecode1 IS 'Code lifttype1';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.lifttypecode2 IS 'Code lifttype2';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.lifttypecode3 IS 'Code lifttype3';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.lifttypeoms1 IS 'Omschrijving lifttype1';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.lifttypeoms2 IS 'Omschrijving lifttype2';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.lifttypeoms3 IS 'Omschrijving lifttype3';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.monumentaandcode IS 'Code monumentaanduiding';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsmonumentaand IS 'Omschrijving monumentaanduiding';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.onbebouwdeterreinopp IS 'Onbebouwde terreinoppervlakte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codeonderhoudstoestand IS 'Onderhoudstoestand';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omsonderhoudstoestand IS 'Omschrijving onderhoudstoestand';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.renovatiejaar IS 'Renovatiejaar';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codesoortwoonobject IS 'Soort woonobject';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omssoortwoonobject IS 'Omschrijving soort woonobject';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.verblijfsobjectnummer IS 'Verblijfsobjectnummer';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.codeverblijfsobjecttype IS 'Verblijfsobjecttype';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.omstypeverblijfsobject IS 'Omschrijving type verblijfsobject';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.woonoppervlakte IS 'Woonoppervlakte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.aandgegevensinodz IS 'Aanduiding gegevens in onderzoek';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.geconstateerd IS 'Geconstateerd';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.statuscode IS 'Status code';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.status IS 'Status';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.typeobject IS 'Type object';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.typeoms IS 'Documenttype-omschrijving';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.typering IS 'Typering';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.woonlaag IS 'Woonlaag';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.authentiek IS 'Authentiek';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.datumbrondocument IS 'Datum brondocument';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.identbrondocument IS 'Identificatie brondocument';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.buurtnaam IS 'Buurtnaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.registratiedatum IS 'Registratiedatum';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gemeentenaam IS 'Gemeente naam';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.puntgeometrie IS 'Puntgeometrie';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.vlakgeometrie IS 'Vlakgeometrie';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.inwoppervlakte IS 'Inwinningswijze oppervlakte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.oppervlakte IS 'Oppervlakte';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.statusvoortgangbouw IS 'Status voortgang bouw';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.toegangbouwlaag IS 'Toegang bouwlaag';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.type IS 'Type';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.ontsluitingverdieping IS 'Ontsluiting verdieping';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.wijknaam IS 'Wijknaam';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.gemeentecode IS 'Gemeentecode';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.aantal_personen IS 'Aantal personen';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.actueel IS 'Actueel VBO';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.afnemers IS 'Afnemers';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.binnengemeentelijk IS 'Binnengemeentelijk VBO';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.leveranciers IS 'Leveranciers';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.aantal_afn IS 'Aantal afnemers';
COMMENT ON COLUMN ###LDF###.anl_kwal_vbo_view.aantal_lev IS 'Aantal leveranciers';

-- functions views procedures LDF

-- table  anl_alg_atl_bew_per_adres --
drop table if exists ###LDF###.anl_alg_atl_bew_per_adres cascade;
create table ###LDF###.anl_alg_atl_bew_per_adres (
    wijknaam_v                     character varying(50) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    aantalpersonen                 numeric(6, 0)           not null
)
WITH (
  OIDS=FALSE
);

create index anl_alg_atl_bew_per_adresi01
on ###LDF###.anl_alg_atl_bew_per_adres
(wijknaam_v, adresbinnenland1_v, adresbinnenland2_v, adresbinnenland3_v);

Comment on  Table ###LDF###.ANL_ALG_ATL_BEW_PER_adres is 'Algemeen aantal bewoners per wijk/adres';
Comment on  Column ###LDF###.ANL_ALG_ATL_BEW_PER_adres.WIJKNAAM_V is 'Wijknaam Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_ATL_BEW_PER_adres.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_ATL_BEW_PER_adres.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_ATL_BEW_PER_adres.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_ATL_BEW_PER_adres.AANTALPERSONEN is 'Aantal personen';
ALTER TABLE ###LDF###.anl_alg_atl_bew_per_adres
  OWNER TO ###OWNER###;

-- table anl_alg_atl_bew_per_wijk --
drop table if exists ###LDF###.anl_alg_atl_bew_per_wijk cascade;
create table ###LDF###.anl_alg_atl_bew_per_wijk (
    wijknaam_v                     character varying(50) ,
    aantalpersonen                 numeric(6, 0)           not null
)
WITH (
  OIDS=FALSE
);

create index anl_alg_atl_bew_per_wijki01
on ###LDF###.anl_alg_atl_bew_per_wijk
(wijknaam_v);

Comment on  Table ###LDF###.ANL_ALG_ATL_BEW_PER_WIJK is 'Algemeen aantal actuele bewoners per wijk';
Comment on  Column ###LDF###.ANL_ALG_ATL_BEW_PER_WIJK.WIJKNAAM_V is 'Wijknaam Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_ATL_BEW_PER_WIJK.AANTALPERSONEN is 'Aantal personen';

ALTER TABLE ###LDF###.anl_alg_atl_bew_per_wijk
  OWNER TO ###OWNER###;

-- table anl_alg_atl_huw_per_wijk --
drop table if exists ###LDF###.anl_alg_atl_huw_per_wijk cascade;
create table ###LDF###.anl_alg_atl_huw_per_wijk (
    wijknaam_v                     character varying(50) ,
    status                         character varying(100) ,
    aantal                         numeric(6, 0)           not null
)
WITH (
  OIDS=FALSE
);

create index anl_alg_atl_huw_per_wijki01
on ###LDF###.anl_alg_atl_huw_per_wijk
(wijknaam_v, status);

Comment on  Table ###LDF###.ANL_ALG_ATL_HUW_PER_WIJK is 'Algemeen huwelijken en scheidingen per wijk';
Comment on  Column ###LDF###.ANL_ALG_ATL_HUW_PER_WIJK.WIJKNAAM_V is 'Wijknaam Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_ATL_HUW_PER_WIJK.STATUS is 'Status';
Comment on  Column ###LDF###.ANL_ALG_ATL_HUW_PER_WIJK.AANTAL is 'Aantal';

ALTER TABLE ###LDF###.anl_alg_atl_huw_per_wijk
  OWNER TO ###OWNER###;

-- table anl_alg_atl_mut_per_wijk --
drop table if exists ###LDF###.anl_alg_atl_mut_per_wijk cascade;
create table ###LDF###.anl_alg_atl_mut_per_wijk (
    wijknaam_v                     character varying(50) ,
    status                         character varying(100) ,
    aantalpersonen                 numeric(6, 0)           not null
)
WITH (
  OIDS=FALSE
);

create index anl_alg_atl_mut_per_wijki01
on ###LDF###.anl_alg_atl_mut_per_wijk
(wijknaam_v, status);

Comment on  Table ###LDF###.ANL_ALG_ATL_MUT_PER_WIJK is 'Algemeen Geboorte, sterfte, vestiging, vertrek en verhuizing, totaal en per wijk';
Comment on  Column ###LDF###.ANL_ALG_ATL_MUT_PER_WIJK.WIJKNAAM_V is 'Wijknaam Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_ATL_MUT_PER_WIJK.STATUS is 'Status';
Comment on  Column ###LDF###.ANL_ALG_ATL_MUT_PER_WIJK.AANTALPERSONEN is 'Aantal personen';

ALTER TABLE ###LDF###.anl_alg_atl_mut_per_wijk
  OWNER TO ###OWNER###;

-- table anl_alg_huwel_jubilaris --
drop table if exists ###LDF###.anl_alg_huwel_jubilaris cascade;
create table ###LDF###.anl_alg_huwel_jubilaris (
    burgerservicenummer            numeric(9, 0)           not null,
    opgemnaam                      character varying(240) ,
    geslachtsnaamechtgenoot        character varying(240) ,
    voorvsgeslachtsnmechtgnt       character varying(15) ,
    adresbinnenland1_i             character varying(110) ,
    adresbinnenland2_i             character varying(110) ,
    adresbinnenland3_i             character varying(50) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    datumsluiting                  numeric(8, 0) ,
    status                         character varying(100)
)
WITH (
  OIDS=FALSE
);

create index anl_alg_huwel_jubilarisi01
on ###LDF###.anl_alg_huwel_jubilaris
(burgerservicenummer);

Comment on  Table ###LDF###.ANL_ALG_HUWEL_JUBILARIS is 'Algemeen huwelijks jubilarissen';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.OPGEMNAAM is 'Opgemaakte naam';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.GESLACHTSNAAMECHTGENOOT is 'Geslachtsnaam echtgenoot';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.VOORVSGESLACHTSNMECHTGNT is 'Voorvoegsels geslachtsnaam echtgenoot';

Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.ADRESBINNENLAND1_I is 'Adres binnenland (1) Inschrijf';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.ADRESBINNENLAND2_I is 'Adres binnenland (2) Inschrijf';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.ADRESBINNENLAND3_I is 'Adres binnenland (3) Inschrijf';

Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.DATUMSLUITING is 'Datum sluiting';
Comment on  Column ###LDF###.ANL_ALG_HUWEL_JUBILARIS.STATUS is 'Status';


ALTER TABLE ###LDF###.anl_alg_huwel_jubilaris
  OWNER TO ###OWNER###;

-- table anl_alg_leeftijdsjubilaris --
drop table if exists ###LDF###.anl_alg_leeftijdsjubilaris cascade;
create table ###LDF###.anl_alg_leeftijdsjubilaris (
    burgerservicenummer            numeric(9, 0)           not null,
    opgemnaam                      character varying(240) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    geboortedatum                  numeric(8, 0) ,
    status                         numeric(4, 0) ,
	resulterende_dagen			   numeric(2, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_alg_leeftijdsjubilarisi01
on ###LDF###.anl_alg_leeftijdsjubilaris
(burgerservicenummer);

Comment on  Table ###LDF###.ANL_ALG_LEEFTIJDSJUBILARIS is 'Algemeen leeftijdsjubilarissen';
Comment on  Column ###LDF###.ANL_ALG_LEEFTIJDSJUBILARIS.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on  Column ###LDF###.ANL_ALG_LEEFTIJDSJUBILARIS.OPGEMNAAM is 'Opgemaakte naam';
Comment on  Column ###LDF###.ANL_ALG_LEEFTIJDSJUBILARIS.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_LEEFTIJDSJUBILARIS.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_LEEFTIJDSJUBILARIS.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_ALG_LEEFTIJDSJUBILARIS.GEBOORTEDATUM is 'Geboortedatum';
Comment on  Column ###LDF###.ANL_ALG_LEEFTIJDSJUBILARIS.STATUS is 'Status';
Comment on  Column ###LDF###.ANL_ALG_LEEFTIJDSJUBILARIS.RESULTERENDE_DAGEN is 'Aantal resulterende dagen';

ALTER TABLE ###LDF###.anl_alg_leeftijdsjubilaris
  OWNER TO ###OWNER###;

-- table anl_alg_prs_ind_inonderz --
drop table if exists ###LDF###.anl_alg_prs_ind_inonderz cascade;
create table ###LDF###.anl_alg_prs_ind_inonderz (
    burgerservicenummer            numeric(9, 0)           not null,
    opgemnaam                      character varying(240) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    geboortedatum                  numeric(8, 0) ,
    odz_overlijden                 character varying(1) ,
    odz_verblijfstitel             character varying(1) ,
    odz_inschrijfadres             character varying(1) ,
    odz_verblijfadres              character varying(1) ,
    odz_identificatiebewijs	       character varying(1) ,
    odz_kindgegevens               character varying(1) ,
    odz_nationaliteit              character varying(1) ,
    odz_oudergegevens              character varying(1) ,
    odz_huwelijksgegevens          character varying(1) ,
    status              character varying(100)
)
WITH (
  OIDS=FALSE
);

create index anl_alg_prs_ind_inonderzi01
on ###LDF###.anl_alg_prs_ind_inonderz
(adresbinnenland1_v, adresbinnenland2_v);
create index anl_alg_prs_ind_inonderzi02
on ###LDF###.anl_alg_prs_ind_inonderz
(burgerservicenummer);

Comment on Table ###LDF###.ANL_ALG_PRS_IND_INONDERZ is 'Algemeen personen met indicatie in onderzoek';
Comment on Column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on Column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.OPGEMNAAM is 'Opgemaakte naam';
Comment on Column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on Column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on Column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on Column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.GEBOORTEDATUM is 'Geboortedatum';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.odz_overlijden is 'Onderzoek overlijden';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.odz_verblijfstitel is 'Onderzoek verblijfstitel';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.odz_inschrijfadres is 'Onderzoek inschrijfadres';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.odz_verblijfadres is 'Onderzoek verblijfadres';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.odz_identificatiebewijs is 'Onderzoek identificatiebewijs';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.odz_kindgegevens is 'Onderzoek kindgegevens';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.odz_nationaliteit is 'Onderzoek nationaliteit';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.odz_oudergegevens is 'Onderzoek oudergegevens';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.odz_huwelijksgegevens is 'Onderzoek huwelijk';
comment on column ###LDF###.ANL_ALG_PRS_IND_INONDERZ.status is 'Status';


ALTER TABLE ###LDF###.anl_alg_prs_ind_inonderz
  OWNER TO ###OWNER###;

-- table anl_alg_verl_ontbr_reisdoc --
drop table if exists ###LDF###.anl_alg_verl_ontbr_reisdoc cascade;
create table ###LDF###.anl_alg_verl_ontbr_reisdoc (
    burgerservicenummer            numeric(9, 0)           not null,
    opgemnaam                      character varying(240),
    codegeslachtsaanduiding        character varying(1),
    adresbinnenland1_i             character varying(110),
    adresbinnenland2_i             character varying(110),
    adresbinnenland3_i             character varying(50),
    adresbinnenland1_v             character varying(110),
    adresbinnenland2_v             character varying(110),
    adresbinnenland3_v             character varying(50),
    adresbinnenland1_c             character varying(110),
    adresbinnenland2_c             character varying(110),
    adresbinnenland3_c             character varying(50),
    geboortedatum                  numeric(8, 0),
    nummeridentiteitsbewijs        character varying(30),
    datumverlopenidentitsbewijs    numeric(8, 0),
    omsidentitsbewijs              character varying(60),
	status                         character varying(100)
)
WITH (
  OIDS=FALSE
);

create index anl_alg_verl_ontbr_reisdoci01
on ###LDF###.anl_alg_verl_ontbr_reisdoc
(adresbinnenland1_v, adresbinnenland2_v);
create index anl_alg_verl_ontbr_reisdoci02
on ###LDF###.anl_alg_verl_ontbr_reisdoc
(burgerservicenummer);

Comment on  Table ###LDF###.anl_alg_verl_ontbr_reisdoc is 'Algemeen Verlopende of ontbrekende reisdocumenten';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.burgerservicenummer is 'Burger Service Nummer';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.opgemnaam is 'Opgemaakte naam';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.codegeslachtsaanduiding is 'Geslacht';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.adresbinnenland1_i is 'Adres binnenland (1) Inschrijf adres';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.adresbinnenland2_i is 'Adres binnenland (2) Inschrijf adres';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.adresbinnenland3_i is 'Adres binnenland (3) Inschrijf adres';

Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.adresbinnenland1_v is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.adresbinnenland2_v is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.adresbinnenland3_v is 'Adres binnenland (3) Verblijf vestiging';

Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.adresbinnenland1_c is 'Adres binnenland (1) Correspondentie adres';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.adresbinnenland2_c is 'Adres binnenland (2) Correspondentie adres';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.adresbinnenland3_c is 'Adres binnenland (3) Correspondentie adres';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.geboortedatum is 'Geboortedatum';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.nummeridentiteitsbewijs is 'Nummer identiteitsbewijs';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.datumverlopenidentitsbewijs is 'Datum verlopen identiteitsbewijs';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.omsidentitsbewijs is 'Omschrijving identiteitsbewijs';
Comment on  Column ###LDF###.anl_alg_verl_ontbr_reisdoc.status is 'Status';

ALTER TABLE ###LDF###.anl_alg_verl_ontbr_reisdoc
  OWNER TO ###OWNER###;

-- table anl_alg_afw_wrd_prs --
drop table if exists ###LDF###.anl_alg_afw_wrd_prs cascade;
create table ###LDF###.anl_alg_afw_wrd_prs (
    rubriek_afwijkend              character varying(200) ,
    eigenaargegeven                character varying(200) ,
    applicatiegegeven              character varying(300) ,
    applicatiecode                 character varying(50) ,
    geslachtsnaam                  character varying(200) ,
    voorletters                    character varying(10) ,
    anummer                        numeric(10, 0) ,
    burgerservicenummer            numeric(9, 0) ,
    geboortedatum                  numeric(8, 0) ,
    datumoverlijden                numeric(8, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_alg_afw_wrd_prsi01
on ###LDF###.anl_alg_afw_wrd_prs
(anummer);
create index anl_alg_afw_wrd_prsi02
on ###LDF###.anl_alg_afw_wrd_prs
(burgerservicenummer);

COMMENT ON TABLE ###LDF###.anl_alg_afw_wrd_prs IS 'Afwijkende waarden persoon';
comment on column ###LDF###.anl_alg_afw_wrd_prs.rubriek_afwijkend is 'Rubriek met afwijkende waarde';
comment on column ###LDF###.anl_alg_afw_wrd_prs.eigenaargegeven is 'Eigenaargegeven';
comment on column ###LDF###.anl_alg_afw_wrd_prs.applicatiegegeven is 'Applicatiegegeven';
comment on column ###LDF###.anl_alg_afw_wrd_prs.applicatiecode is 'Applicatiecode';
comment on column ###LDF###.anl_alg_afw_wrd_prs.geslachtsnaam is 'Geslachtsnaam';
comment on column ###LDF###.anl_alg_afw_wrd_prs.voorletters is 'Voorletters';
comment on column ###LDF###.anl_alg_afw_wrd_prs.anummer is 'A nummer';
comment on column ###LDF###.anl_alg_afw_wrd_prs.burgerservicenummer is 'Burger Service Nummer';
comment on column ###LDF###.anl_alg_afw_wrd_prs.geboortedatum is 'Geboortedatum';
comment on column ###LDF###.anl_alg_afw_wrd_prs.datumoverlijden is 'Datum overlijden';

ALTER TABLE ###LDF###.anl_alg_afw_wrd_prs
  OWNER TO ###OWNER###;

-- table anl_alg_afw_wrd_nnp --
drop table if exists ###LDF###.anl_alg_afw_wrd_nnp cascade;
create table ###LDF###.anl_alg_afw_wrd_nnp (
    rubriek_afwijkend              character varying(200) ,
    eigenaargegeven                character varying(200) ,
    applicatiegegeven              character varying(1024) ,
    applicatiecode                 character varying(50) ,
    zaaknaam                       character varying(200) ,
    burgerservicenummer            numeric(9, 0) ,
    handelsregisternummer          numeric(8, 0) ,
    handelsregistervolgnummer      numeric(4, 0) ,
    datumbegin                     numeric(8, 0) ,
    datumeinde                     numeric(8, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_alg_afw_wrd_nnpi01
on ###LDF###.anl_alg_afw_wrd_nnp
(handelsregisternummer);
create index anl_alg_afw_wrd_nnpi02
on ###LDF###.anl_alg_afw_wrd_nnp
(burgerservicenummer);

COMMENT ON TABLE ###LDF###.anl_alg_afw_wrd_nnp IS 'Afwijkende waarden bedrijf';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.rubriek_afwijkend is 'Rubriek met afwijkende waarde';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.eigenaargegeven is 'Eigenaargegeven';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.applicatiegegeven is 'Applicatiegegeven';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.applicatiecode is 'Applicatiecode';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.zaaknaam is 'Zaaknaam';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.burgerservicenummer is 'Burger Service Nummer';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.handelsregisternummer is 'HandelsRegisternummer';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.handelsregistervolgnummer is 'HandelsRegisterVolgnummer';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.datumbegin is 'Datum begin';
comment on column ###LDF###.anl_alg_afw_wrd_nnp.datumeinde is 'Datum einde';

ALTER TABLE ###LDF###.anl_alg_afw_wrd_nnp
  OWNER TO ###OWNER###;

-- table anl_fraude_mbt_geb_kind --
drop table if exists ###LDF###.anl_fraude_mbt_geb_kind cascade;
create table ###LDF###.anl_fraude_mbt_geb_kind (
    burgerservicenummer            numeric(9, 0) ,
    anummer                        numeric(10, 0) ,
    prs_key                        numeric(14, 0)          not null,
    voornamen                      character varying(240) ,
    voorletters                    character varying(15) ,
    voorvoegselgeslachtsnaam       character varying(15) ,
    geslachtsnaam                  character varying(240) ,
    codegeslachtsaanduiding        character varying(1) ,
    geboortedatum                  numeric(8, 0) ,
    codeburgerlijkestaat           numeric(1, 0) ,
    datumoverlijden                numeric(8, 0) ,
    codeindicatiegeheim            numeric(1, 0) ,
    opgemnaam                      character varying(240) ,
    geslachtsnaamechtgenoot        character varying(240) ,
    voorvsgeslachtsnmechtgnt       character varying(15) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    voornamen_o1                   character varying(240) ,
    voorletters_o1                 character varying(15) ,
    voorvoegselgeslachtsnaam_o1    character varying(15) ,
    geslachtsnaam_o1               character varying(240) ,
    codegeslachtsaanduiding_o1     character varying(1) ,
    geboortedatum_o1               numeric(8, 0) ,
    codeburgerlijkestaat_o1        numeric(1, 0) ,
    anummer_o1                     numeric(10, 0) ,
    burgerservicenummer_o1         numeric(9, 0) ,
    datumoverlijden_o1             numeric(8, 0) ,
    codeindicatiegeheim_o1         numeric(1, 0) ,
    adresbinnenland1_o1            character varying(110) ,
    adresbinnenland2_o1            character varying(110) ,
    adresbinnenland3_o1            character varying(50) ,
    voornamen_o2                   character varying(240) ,
    voorletters_o2                 character varying(15) ,
    voorvoegselgeslachtsnaam_o2    character varying(15) ,
    geslachtsnaam_o2               character varying(240) ,
    codegeslachtsaanduiding_o2     character varying(1) ,
    geboortedatum_o2               numeric(8, 0) ,
    codeburgerlijkestaat_o2        numeric(1, 0) ,
    anummer_o2                     numeric(10, 0) ,
    burgerservicenummer_o2         numeric(9, 0) ,
    datumoverlijden_o2             numeric(8, 0) ,
    codeindicatiegeheim_o2         numeric(1, 0) ,
    adresbinnenland1_o2            character varying(110) ,
    adresbinnenland2_o2            character varying(110) ,
    adresbinnenland3_o2            character varying(50)
)
WITH (
  OIDS=FALSE
);

create index anl_fraude_mbt_geb_kindi01
on ###LDF###.anl_fraude_mbt_geb_kind
(burgerservicenummer);
create index anl_fraude_mbt_geb_kindi02
on ###LDF###.anl_fraude_mbt_geb_kind
(adresbinnenland1_v, adresbinnenland2_v);
create index anl_fraude_mbt_geb_kindi03
on ###LDF###.anl_fraude_mbt_geb_kind
(prs_key);

Comment on  Table ###LDF###.ANL_FRAUDE_MBT_GEB_KIND is 'Analyse fraude mbt geboorte kind';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ANUMMER is 'A nummer';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.PRS_KEY is 'PRS key';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORNAMEN is 'Voornamen';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORLETTERS is 'Voorletters';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORVOEGSELGESLACHTSNAAM is 'Voorvoegsel geslachtsnaam';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.GESLACHTSNAAM is 'Geslachtsnaam';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.CODEGESLACHTSAANDUIDING is 'Geslachtsaanduiding';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.GEBOORTEDATUM is 'Geboortedatum';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.CODEBURGERLIJKESTAAT is 'Burgerlijke staat';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.DATUMOVERLIJDEN is 'Datum overlijden';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.CODEINDICATIEGEHEIM is 'Indicatie geheim';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.OPGEMNAAM is 'Opgemaakte naam';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.GESLACHTSNAAMECHTGENOOT is 'Geslachtsnaam echtgenoot';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORVSGESLACHTSNMECHTGNT is 'Voorvoegsels geslachtsnaam echtgenoot';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORNAMEN_O1 is 'Voornamen Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORLETTERS_O1 is 'Voorletters Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORVOEGSELGESLACHTSNAAM_O1 is 'Voorvoegsel geslachtsnaam Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.GESLACHTSNAAM_O1 is 'Geslachtsnaam Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.CODEGESLACHTSAANDUIDING_O1 is 'Geslachtsaanduiding Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.GEBOORTEDATUM_O1 is 'Geboortedatum Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.CODEBURGERLIJKESTAAT_O1 is 'Burgerlijke staat Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ANUMMER_O1 is 'A nummer Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.BURGERSERVICENUMMER_O1 is 'Burger Service Nummer Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.DATUMOVERLIJDEN_O1 is 'Datum overlijden Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.CODEINDICATIEGEHEIM_O1 is 'ind geheim Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ADRESBINNENLAND1_O1 is 'Adres binnenland (1) Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ADRESBINNENLAND2_O1 is 'Adres binnenland (2) Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ADRESBINNENLAND3_O1 is 'Adres binnenland (3) Ouder 1';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORNAMEN_O2 is 'Voornamen Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORLETTERS_O2 is 'Voorletters Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.VOORVOEGSELGESLACHTSNAAM_O2 is 'Voorvoegsel geslachtsnaam Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.GESLACHTSNAAM_O2 is 'Geslachtsnaam Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.CODEGESLACHTSAANDUIDING_O2 is 'Geslachtsaanduiding Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.GEBOORTEDATUM_O2 is 'Geboortedatum Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.CODEBURGERLIJKESTAAT_O2 is 'Burgerlijke staat Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ANUMMER_O2 is 'A nummer Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.BURGERSERVICENUMMER_O2 is 'Burger Service Nummer Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.DATUMOVERLIJDEN_O2 is 'Datum overlijden Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.CODEINDICATIEGEHEIM_O2 is 'ind geheim Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ADRESBINNENLAND1_O2 is 'Adres binnenland (1) Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ADRESBINNENLAND2_O2 is 'Adres binnenland (2) Ouder 2';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_GEB_KIND.ADRESBINNENLAND3_O2 is 'Adres binnenland (3) Ouder 2';

ALTER TABLE ###LDF###.anl_fraude_mbt_geb_kind
  OWNER TO ###OWNER###;

-- table anl_fraude_mbt_huisvesting --
drop table if exists ###LDF###.anl_fraude_mbt_huisvesting cascade;
create table ###LDF###.anl_fraude_mbt_huisvesting (
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    woonoppervlakte                numeric(6, 0) ,
    aantalpersonen                 numeric(6, 0) ,
    status                         character varying(100)
)
WITH (
  OIDS=FALSE
);

create index anl_fraude_mbt_huisvestingi01
on ###LDF###.anl_fraude_mbt_huisvesting
(adresbinnenland1_v, adresbinnenland2_v);
create index anl_fraude_mbt_huisvestingi02
on ###LDF###.anl_fraude_mbt_huisvesting
(aantalpersonen);

Comment on  Table ###LDF###.ANL_FRAUDE_MBT_HUISVESTING is 'Analyse fraude mbt huisvesting';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_HUISVESTING.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_HUISVESTING.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_HUISVESTING.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_HUISVESTING.WOONOPPERVLAKTE is 'Woonoppervlakte';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_HUISVESTING.AANTALPERSONEN is 'Aantal personen';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_HUISVESTING.STATUS is 'Status';

ALTER TABLE ###LDF###.anl_fraude_mbt_huisvesting
  OWNER TO ###OWNER###;

-- table anl_fraude_mbt_reisdoc --
drop table if exists ###LDF###.anl_fraude_mbt_reisdoc cascade;
create table ###LDF###.anl_fraude_mbt_reisdoc (
    burgerservicenummer            numeric(9, 0)           not null,
    geboortedatum                  numeric(8, 0) ,
    opgemnaam                      character varying(240) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    atlreisdoc                     numeric(6, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_fraude_mbt_reisdoci01
on ###LDF###.anl_fraude_mbt_reisdoc
(adresbinnenland1_v, adresbinnenland2_v);
create index anl_fraude_mbt_reisdoci02
on ###LDF###.anl_fraude_mbt_reisdoc
(burgerservicenummer);

Comment on  Table ###LDF###.ANL_FRAUDE_MBT_REISDOC is 'Analyse fraude mbt reisdocumenten';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_REISDOC.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_REISDOC.GEBOORTEDATUM is 'Geboortedatum';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_REISDOC.OPGEMNAAM is 'Opgemaakte naam';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_REISDOC.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_REISDOC.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_REISDOC.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_REISDOC.ATLREISDOC is 'Aantal reisdocumenten';

ALTER TABLE ###LDF###.anl_fraude_mbt_reisdoc
  OWNER TO ###OWNER###;

-- table anl_fraude_mbt_studie --
drop table if exists ###LDF###.anl_fraude_mbt_studie cascade;
create table ###LDF###.anl_fraude_mbt_studie (
    burgerservicenummer            numeric(9, 0)           not null,
    geboortedatum                  numeric(8, 0) ,
    opgemnaam                      character varying(240) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    begdatrelatie_v                numeric(8, 0) ,
    status                         character varying(100)
)
WITH (
  OIDS=FALSE
);

create index anl_fraude_mbt_studiei01
on ###LDF###.anl_fraude_mbt_studie
(adresbinnenland1_v, adresbinnenland2_v);
create index anl_fraude_mbt_studiei02
on ###LDF###.anl_fraude_mbt_studie
(burgerservicenummer);

Comment on  Table ###LDF###.ANL_FRAUDE_MBT_STUDIE is 'Analyse fraude mbt studie';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_STUDIE.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_STUDIE.GEBOORTEDATUM is 'Geboortedatum';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_STUDIE.OPGEMNAAM is 'Opgemaakte naam';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_STUDIE.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_STUDIE.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_STUDIE.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_STUDIE.BEGDATRELATIE_V is 'Begindatum relatie Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_STUDIE.STATUS is 'Status';

ALTER TABLE ###LDF###.anl_fraude_mbt_studie
  OWNER TO ###OWNER###;

-- table anl_fraude_mbt_verhuizing --
drop table if exists ###LDF###.anl_fraude_mbt_verhuizing cascade;
create table ###LDF###.anl_fraude_mbt_verhuizing (
    burgerservicenummer            numeric(9, 0)           not null,
    geboortedatum                  numeric(8, 0) ,
    opgemnaam                      character varying(240) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    begdatrelatie_v                numeric(8, 0) ,
    status                         character varying(100) ,
    anummer                        numeric(10, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_fraude_mbt_verhuizingi01
on ###LDF###.anl_fraude_mbt_verhuizing
(burgerservicenummer);
create index anl_fraude_mbt_verhuizingi02 on
###LDF###.anl_fraude_mbt_verhuizing
(anummer);
create index anl_fraude_mbt_verhuizingi03
on ###LDF###.anl_fraude_mbt_verhuizing
(adresbinnenland1_v, adresbinnenland2_v);

Comment on  Table ###LDF###.ANL_FRAUDE_MBT_VERHUIZING is 'Analyse fraude mbt verhuizing';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_VERHUIZING.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_VERHUIZING.GEBOORTEDATUM is 'Geboortedatum';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_VERHUIZING.OPGEMNAAM is 'Opgemaakte naam';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_VERHUIZING.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_VERHUIZING.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_VERHUIZING.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_VERHUIZING.BEGDATRELATIE_V is 'Begindatum relatie Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_VERHUIZING.STATUS is 'Status';
Comment on  Column ###LDF###.ANL_FRAUDE_MBT_VERHUIZING.ANUMMER is 'A nummer';

ALTER TABLE ###LDF###.anl_fraude_mbt_verhuizing
  OWNER TO ###OWNER###;

-- table anl_fraude_verh_uitkering --
drop table if exists ###LDF###.anl_fraude_verh_uitkering cascade;
create table ###LDF###.anl_fraude_verh_uitkering (
    burgerservicenummer            numeric(9, 0)           not null,
    geboortedatum                  numeric(8, 0) ,
    opgemnaam                      character varying(240) ,
    adresbinnenland1_v             character varying(110) ,
    adresbinnenland2_v             character varying(110) ,
    adresbinnenland3_v             character varying(50) ,
    begdatrelatie_v                numeric(8, 0) ,
    uitkregeling                   character varying(4) ,
    status                         character varying(100)
)
WITH (
  OIDS=FALSE
);

create index anl_fraude_verh_uitkeringi01
on ###LDF###.anl_fraude_verh_uitkering
(adresbinnenland1_v, adresbinnenland2_v);
create index anl_fraude_verh_uitkeringi02
on ###LDF###.anl_fraude_verh_uitkering
(burgerservicenummer);

Comment on  Table ###LDF###.ANL_FRAUDE_VERH_UITKERING is 'Analyse fraude mbt verhuizing en uitkering';
Comment on  Column ###LDF###.ANL_FRAUDE_VERH_UITKERING.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on  Column ###LDF###.ANL_FRAUDE_VERH_UITKERING.GEBOORTEDATUM is 'Geboortedatum';
Comment on  Column ###LDF###.ANL_FRAUDE_VERH_UITKERING.OPGEMNAAM is 'Opgemaakte naam';
Comment on  Column ###LDF###.ANL_FRAUDE_VERH_UITKERING.ADRESBINNENLAND1_V is 'Adres binnenland (1) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_VERH_UITKERING.ADRESBINNENLAND2_V is 'Adres binnenland (2) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_VERH_UITKERING.ADRESBINNENLAND3_V is 'Adres binnenland (3) Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_VERH_UITKERING.BEGDATRELATIE_V is 'Begindatum relatie Verblijf vestiging';
Comment on  Column ###LDF###.ANL_FRAUDE_VERH_UITKERING.UITKREGELING is 'Uitkerings regeling';
Comment on  Column ###LDF###.ANL_FRAUDE_VERH_UITKERING.STATUS is 'Status';

ALTER TABLE ###LDF###.anl_fraude_verh_uitkering
  OWNER TO ###OWNER###;

-- table anl_hoofdbewoner --
drop table if exists ###LDF###.anl_hoofdbewoner cascade;
create table ###LDF###.anl_hoofdbewoner (
    adresbinnenland1               character varying(110) ,
    adresbinnenland2               character varying(110) ,
    adresbinnenland3               character varying(50) ,
    burgerservicenummer            numeric(9, 0) ,
    anummer                        numeric(10, 0)
)
WITH (
  OIDS=FALSE
);

create index anl_hoofdbewoneri01 on
###LDF###.anl_hoofdbewoner
(burgerservicenummer);
create index anl_hoofdbewoneri02 on
###LDF###.anl_hoofdbewoner
(anummer);
create index anl_hoofdbewoneri03 on
###LDF###.anl_hoofdbewoner
(adresbinnenland1, adresbinnenland2);

Comment on  Table ###LDF###.ANL_HOOFDBEWONER is 'Analyse hoofbewonergegevens';
Comment on  Column ###LDF###.ANL_HOOFDBEWONER.ADRESBINNENLAND1 is 'Adres binnenland (1)';
Comment on  Column ###LDF###.ANL_HOOFDBEWONER.ADRESBINNENLAND2 is 'Adres binnenland (2)';
Comment on  Column ###LDF###.ANL_HOOFDBEWONER.ADRESBINNENLAND3 is 'Adres binnenland (3)';
Comment on  Column ###LDF###.ANL_HOOFDBEWONER.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on  Column ###LDF###.ANL_HOOFDBEWONER.ANUMMER is 'A nummer';

ALTER TABLE ###LDF###.anl_hoofdbewoner
  OWNER TO ###OWNER###;

-- table anl_uitkeringen --
drop table if exists ###LDF###.anl_uitkeringen cascade;
create table ###LDF###.anl_uitkeringen (
    uitkeringsnr                   numeric(10, 0) ,
    clientnr                       numeric(10, 0) ,
    opgemnaamzonderpredikaten      character varying(240) ,
    adresbinnenland1               character varying(110) ,
    adresbinnenland2               character varying(110) ,
    adresbinnenland3               character varying(50) ,
    burgerservicenummer            numeric(9, 0) ,
    geboortedatum                  numeric(8, 0) ,
    leeftijd                       numeric(3, 0) ,
    geslachtsaand                  character varying(1) ,
    omschrijvingnationaliteit      character varying(55) ,
    burgerlijkestaat               character varying(20) ,
    huisvesting                    character varying(100) ,
    uitkregeling                   character varying(4) ,
    normbedrag                     numeric(13, 2) ,
    uitbetaald                     numeric(13, 2) ,
    creatiedatum                   numeric(8)
)
with (
  oids=false
);

create index anl_uitkeringeni01 on
###LDF###.anl_uitkeringen
(burgerservicenummer);
create index anl_uitkeringeni02 on
###LDF###.anl_uitkeringen
(uitkeringsnr);

Comment on  Table ###LDF###.ANL_UITKERINGEN is 'Analyse uitkeringsgegevens';
Comment on  Column ###LDF###.ANL_UITKERINGEN.UITKERINGSNR is 'Uitkerings nummer';
Comment on  Column ###LDF###.ANL_UITKERINGEN.CLIENTNR is 'Client nummer';
Comment on  Column ###LDF###.ANL_UITKERINGEN.OPGEMNAAMZONDERPREDIKATEN is 'Opgemaakte naam zonder predikaten';
Comment on  Column ###LDF###.ANL_UITKERINGEN.ADRESBINNENLAND1 is 'Adres binnenland (1)';
Comment on  Column ###LDF###.ANL_UITKERINGEN.ADRESBINNENLAND2 is 'Adres binnenland (2)';
Comment on  Column ###LDF###.ANL_UITKERINGEN.ADRESBINNENLAND3 is 'Adres binnenland (3)';
Comment on  Column ###LDF###.ANL_UITKERINGEN.BURGERSERVICENUMMER is 'Burger Service Nummer';
Comment on  Column ###LDF###.ANL_UITKERINGEN.GEBOORTEDATUM is 'Geboortedatum';
Comment on  Column ###LDF###.ANL_UITKERINGEN.LEEFTIJD is 'Leeftijd';
Comment on  Column ###LDF###.ANL_UITKERINGEN.GESLACHTSAAND is 'Geslachtsaanduiding';
Comment on  Column ###LDF###.ANL_UITKERINGEN.OMSCHRIJVINGNATIONALITEIT is 'Omschrijving nationaliteit';
Comment on  Column ###LDF###.ANL_UITKERINGEN.BURGERLIJKESTAAT is 'Burgerlijke staat omschrijving';
Comment on  Column ###LDF###.ANL_UITKERINGEN.HUISVESTING is 'Huisvesting';
Comment on  Column ###LDF###.ANL_UITKERINGEN.UITKREGELING is 'Uitkerings regeling';
Comment on  Column ###LDF###.ANL_UITKERINGEN.NORMBEDRAG is 'Norm bedrag';
Comment on  Column ###LDF###.ANL_UITKERINGEN.UITBETAALD is 'Uitbetaald bedrag';
Comment on  Column ###LDF###.ANL_UITKERINGEN.CREATIEDATUM is 'creatie datum';

ALTER TABLE ###LDF###.anl_uitkeringen
  OWNER TO ###OWNER###;

-- table anl_cmp_persoon --
drop table if exists ###LDF###.anl_cmp_persoon cascade;
create table ###LDF###.anl_cmp_persoon (
    bron                           character varying(20)          not null,
    burgerservicenummer            numeric(9, 0) ,
    gemeentecodeinschrijving       numeric(4, 0) ,
    voornamen                      character varying(240) ,
    voorletters                    character varying(15) ,
    voorvoegselgeslachtsnaam       character varying(15) ,
    geslachtsnaam                  character varying(240) ,
    codegeslachtsaanduiding        character varying(1) ,
    codeadellijketitelpredikaat    character varying(2) ,
    geboortedatum                  numeric(8, 0) ,
    geboorteplaats                 character varying(220) ,
    geboortelandcode               numeric(4, 0) ,
    codeburgerlijkestaat           numeric(1, 0) ,
    anummer                        numeric(10, 0) ,
    datumoverlijden                numeric(8, 0) ,
    codeplaatsoverlijden           numeric(4, 0) ,
    omsplaatsoverlijden            character varying(50) ,
    landcodeoverlijden             numeric(4, 0) ,
    redenopschorting               character varying(1) ,
    codeindicatiegeheim            numeric(1, 0) ,
    aanduidingnaamgebruik          character varying(1) ,
    datumsluiting                  numeric(8, 0) ,
    datumontbinding                numeric(8, 0) ,
    coderedenontbinding            character varying(1) ,
    burgerservicenummerechtgnt     numeric(9, 0) ,
    geslachtsaanduidingechtgnt     character varying(1) ,
    straatnaam_i                   character varying(30) ,
    huisnummer_i                   numeric(5, 0) ,
    huisletter_i                   character varying(1) ,
    huisnummertoevoeging_i         character varying(4) ,
    aanduidingbijhuisnummer_i      character varying(2) ,
    locatieomschrijving_i          character varying(50) ,
    postcode_i                     character varying(6) ,
    woonplaatsnaam_i               character varying(50) ,
    gemeentecode_i                 numeric(4, 0) ,
    gemeentenaam_i                 character varying(50) ,
    landcode_i                     numeric(4, 0) ,
    landnaam_i                     character varying(50) ,
    straatnaam_v                   character varying(30) ,
    huisnummer_v                   numeric(5, 0) ,
    huisletter_v                   character varying(1) ,
    huisnummertoevoeging_v         character varying(4) ,
    aanduidingbijhuisnummer_v      character varying(2) ,
    locatieomschrijving_v          character varying(50) ,
    postcode_v                     character varying(6) ,
    woonplaatsnaam_v               character varying(50) ,
    gemeentecode_v                 numeric(4, 0) ,
    gemeentenaam_v                 character varying(50) ,
    landcode_v                     numeric(4, 0) ,
    landnaam_v                     character varying(50) ,
    wijkcode_v                     numeric(2, 0) ,
    buurtcode_v                    numeric(2, 0) ,
    wijknaam_v                     character varying(50) ,
    buurtnaam_v                    character varying(50) ,
    straatnaam_c                   character varying(30) ,
    huisnummer_c                   numeric(5, 0) ,
    huisletter_c                   character varying(1) ,
    huisnummertoevoeging_c         character varying(4) ,
    aanduidingbijhuisnummer_c      character varying(2) ,
    locatieomschrijving_c          character varying(50) ,
    postcode_c                     character varying(6) ,
    woonplaatsnaam_c               character varying(80) ,
    gemeentecode_c                 numeric(4, 0) ,
    gemeentenaam_c                 character varying(50) ,
    landcode_c                     numeric(4, 0) ,
    landnaam_c                     character varying(50) ,
    statusvoa                      character varying(2) ,
	actueel                        character varying(1 ),
	binnengemeentelijk             character varying(1 ),
    odz_overlijden                 character varying(1) ,
    odz_verblijfstitel             character varying(1) ,
    odz_inschrijfadres             character varying(1) ,
    odz_verblijfadres              character varying(1) ,
    odz_identificatiebewijs	       character varying(1) ,
    odz_kindgegevens               character varying(1) ,
    odz_nationaliteit              character varying(1) ,
    odz_oudergegevens              character varying(1) ,
    odz_huwelijksgegevens          character varying(1) ,
    verblijfstitelcode             numeric(2, 0) ,
    datumverkrijgingverblfsttl     numeric(8, 0) ,
    datumverliesverblijfstitel     numeric(8, 0) ,
    codeindgezagminderjarige       character varying(2) ,
    indcurateleregister            character varying(1)
)
WITH (
  OIDS=FALSE
);

create index anl_cmp_persooni01 on
###LDF###.anl_cmp_persoon
(burgerservicenummer);
create index anl_cmp_persooni02 on
###LDF###.anl_cmp_persoon
(anummer);
create index anl_cmp_persooni03 on
###LDF###.anl_cmp_persoon
(straatnaam_v, huisnummer_v);

COMMENT ON TABLE ###LDF###.anl_cmp_persoon IS 'Vergelijkingen persoon';
comment on column ###LDF###.anl_cmp_persoon.bron is 'Bron applicatie';
comment on column ###LDF###.anl_cmp_persoon.burgerservicenummer is 'Burger Service Nummer';
comment on column ###LDF###.anl_cmp_persoon.gemeentecodeinschrijving is 'Gemeentecode inschrijving';
comment on column ###LDF###.anl_cmp_persoon.voornamen is 'Voornamen';
comment on column ###LDF###.anl_cmp_persoon.voorletters is 'Voorletters';
comment on column ###LDF###.anl_cmp_persoon.voorvoegselgeslachtsnaam is 'Voorvoegsel geslachtsnaam';
comment on column ###LDF###.anl_cmp_persoon.geslachtsnaam is 'Geslachtsnaam';
comment on column ###LDF###.anl_cmp_persoon.codegeslachtsaanduiding is 'Geslachtsaanduiding';
comment on column ###LDF###.anl_cmp_persoon.codeadellijketitelpredikaat is 'Code adellijke titel predikaat';
comment on column ###LDF###.anl_cmp_persoon.geboortedatum is 'Geboortedatum';
comment on column ###LDF###.anl_cmp_persoon.geboorteplaats is 'Geboorteplaats';
comment on column ###LDF###.anl_cmp_persoon.geboortelandcode is 'Geboortelandcode';
comment on column ###LDF###.anl_cmp_persoon.codeburgerlijkestaat is 'Burgerlijke staat';
comment on column ###LDF###.anl_cmp_persoon.anummer is 'A nummer';
comment on column ###LDF###.anl_cmp_persoon.datumoverlijden is 'Datum overlijden';
comment on column ###LDF###.anl_cmp_persoon.codeplaatsoverlijden is 'Gemeentecode plaats overlijden';
comment on column ###LDF###.anl_cmp_persoon.omsplaatsoverlijden is 'Omschrijving plaatsoverlijden';
comment on column ###LDF###.anl_cmp_persoon.landcodeoverlijden is 'Landcode overlijden';
comment on column ###LDF###.anl_cmp_persoon.redenopschorting is 'Reden opschorting';
comment on column ###LDF###.anl_cmp_persoon.codeindicatiegeheim is 'ind geheim';
comment on column ###LDF###.anl_cmp_persoon.aanduidingnaamgebruik is 'Aanduiding naamgebruik';
comment on column ###LDF###.anl_cmp_persoon.datumsluiting is 'Datum sluiting';
comment on column ###LDF###.anl_cmp_persoon.datumontbinding is 'Datum ontbinding';
comment on column ###LDF###.anl_cmp_persoon.coderedenontbinding is 'Reden ontbinding';
comment on column ###LDF###.anl_cmp_persoon.burgerservicenummerechtgnt is 'Burger Service Nummer echtgenoot';
comment on column ###LDF###.anl_cmp_persoon.straatnaam_i is 'Straatnaam Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.huisnummer_i is 'Huisnummer Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.huisletter_i is 'Huisletter Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.huisnummertoevoeging_i is 'Huisnummertoevoeging Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.aanduidingbijhuisnummer_i is 'Aanduiding bij huisnummer Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.locatieomschrijving_i is 'Locatieomschrijving Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.postcode_i is 'Postcode Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.woonplaatsnaam_i is 'Woonplaatsnaam Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.gemeentecode_i is 'Gemeentecode Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.gemeentenaam_i is 'Gemeente naam Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.landcode_i is 'Landcode Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.landnaam_i is 'Landnaam Inschrijf';
comment on column ###LDF###.anl_cmp_persoon.straatnaam_v is 'Straatnaam Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.huisnummer_v is 'Huisnummer Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.huisletter_v is 'Huisletter Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.huisnummertoevoeging_v is 'Huisnummertoevoeging Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.aanduidingbijhuisnummer_v is 'Aanduiding bij huisnummer Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.locatieomschrijving_v is 'Locatieomschrijving Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.postcode_v is 'Postcode Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.woonplaatsnaam_v is 'Woonplaatsnaam Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.gemeentecode_v is 'Gemeentecode Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.gemeentenaam_v is 'Gemeente naam Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.landcode_v is 'Landcode Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.landnaam_v is 'Landnaam Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.wijkcode_v is 'Wijkcode Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.buurtcode_v is 'Buurtcode Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.wijknaam_v is 'Wijknaam Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.buurtnaam_v is 'Buurtnaam Verblijf vestiging';
comment on column ###LDF###.anl_cmp_persoon.straatnaam_c is 'Straatnaam Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.huisnummer_c is 'Huisnummer Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.huisletter_c is 'Huisletter Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.huisnummertoevoeging_c is 'Huisnummertoevoeging Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.aanduidingbijhuisnummer_c is 'Aanduiding bij huisnummer Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.locatieomschrijving_c is 'Locatieomschrijving Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.postcode_c is 'Postcode Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.woonplaatsnaam_c is 'Woonplaatsnaam Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.gemeentecode_c is 'Gemeentecode Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.gemeentenaam_c is 'Gemeente naam Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.landcode_c is 'Landcode Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.landnaam_c is 'Landnaam Correspondentie';
comment on column ###LDF###.anl_cmp_persoon.statusvoa is 'HIDE';
comment on column ###LDF###.anl_cmp_persoon.actueel is 'Actueel persoon';
comment on column ###LDF###.anl_cmp_persoon.binnengemeentelijk is 'Binnengemeentelijk persoon';
comment on column ###LDF###.anl_cmp_persoon.odz_overlijden is 'Onderzoek overlijden';
comment on column ###LDF###.anl_cmp_persoon.odz_verblijfstitel is 'Onderzoek verblijfstitel';
comment on column ###LDF###.anl_cmp_persoon.odz_inschrijfadres is 'Onderzoek inschrijfadres';
comment on column ###LDF###.anl_cmp_persoon.odz_verblijfadres is 'Onderzoek verblijfadres';
comment on column ###LDF###.anl_cmp_persoon.odz_identificatiebewijs is 'Onderzoek identificatiebewijs';
comment on column ###LDF###.anl_cmp_persoon.odz_kindgegevens is 'Onderzoek kindgegevens';
comment on column ###LDF###.anl_cmp_persoon.odz_nationaliteit is 'Onderzoek nationaliteit';
comment on column ###LDF###.anl_cmp_persoon.odz_oudergegevens is 'Onderzoek oudergegevens';
comment on column ###LDF###.anl_cmp_persoon.odz_huwelijksgegevens is 'Onderzoek huwelijk';
comment on column ###LDF###.anl_cmp_persoon.verblijfstitelcode  is 'Code verblijfstitel';
comment on column ###LDF###.anl_cmp_persoon.datumverkrijgingverblfsttl  is 'Datum verkrijging verblijfstitel';
comment on column ###LDF###.anl_cmp_persoon.datumverliesverblijfstitel  is 'Datum verlies verblijfstitel';
comment on column ###LDF###.anl_cmp_persoon.codeindgezagminderjarige  is 'Indicatie gezag minderjarige';
comment on column ###LDF###.anl_cmp_persoon.indcurateleregister  is 'Indicatie curateleregister';

ALTER TABLE ###LDF###.anl_cmp_persoon
  OWNER TO ###OWNER###;

DO $$
BEGIN
    DROP VIEW IF EXISTS ###LDF###.anl_kvk_authenticiteit;
EXCEPTION
    WHEN wrong_object_type THEN RAISE NOTICE 'ANL_KVK_AUTHENTICITEIT is a table!';
END$$;

DROP TABLE if exists ###LDF###.anl_kvk_authenticiteit CASCADE ;
CREATE TABLE ###LDF###.anl_kvk_authenticiteit
(
    handelsregisternummer numeric(8 ),
    vestigingsnummer character varying(12 ),
    nnpid numeric(9 ),
    handelsnaam character varying(160 ),
    statnaamvennschpnaam character varying(160 ),
    zaaknaam character varying(55 ),
    opgemaakt_adres character varying(240 ),
    leverancier character varying(1050 ),
    geleverddoorhrs character varying(1 ),
    afnemer character varying(1050 ),
    sleutelgegevensbeheer numeric(14 ),
    handelsregistervolgnummer numeric(4 ),
    postcode character varying(6 ),
    huisnummer numeric(5 ),
    huisletter character varying(1 ),
    huisnummertoevoeging character varying(4 ),
CONSTRAINT anl_kvk_authenticiteiti00 PRIMARY KEY (sleutelgegevensbeheer)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ###LDF###.anl_kvk_authenticiteit OWNER TO ###OWNER###;

CREATE INDEX anl_kvk_authenticiteitI01 on ###LDF###.anl_kvk_authenticiteit USING btree (handelsregisternummer);
CREATE INDEX anl_kvk_authenticiteitI02 on ###LDF###.anl_kvk_authenticiteit USING btree (vestigingsnummer);
CREATE INDEX anl_kvk_authenticiteitI03 on ###LDF###.anl_kvk_authenticiteit USING btree (nnpid);
CREATE INDEX anl_kvk_authenticiteitI04 on ###LDF###.anl_kvk_authenticiteit USING btree (handelsnaam);
CREATE INDEX anl_kvk_authenticiteitI05 on ###LDF###.anl_kvk_authenticiteit USING btree (statnaamvennschpnaam);
CREATE INDEX anl_kvk_authenticiteitI06 on ###LDF###.anl_kvk_authenticiteit USING btree (zaaknaam);
CREATE INDEX anl_kvk_authenticiteitI07 on ###LDF###.anl_kvk_authenticiteit USING btree (opgemaakt_adres);
CREATE INDEX anl_kvk_authenticiteitI08 on ###LDF###.anl_kvk_authenticiteit USING btree (postcode);
CREATE INDEX anl_kvk_authenticiteitI09 on ###LDF###.anl_kvk_authenticiteit USING btree (sleutelgegevensbeheer);

--------------------------------------------------------------------------
-- Comments for anl_kvk_authenticiteit
--------------------------------------------------------------------------
COMMENT ON TABLE ###LDF###.anl_kvk_authenticiteit IS 'Analyse kvk authenticiteit';
comment on column ###LDF###.anl_kvk_authenticiteit.handelsregisternummer is 'HandelsRegisternummer';
comment on column ###LDF###.anl_kvk_authenticiteit.vestigingsnummer is 'Vestigingsnummer';
comment on column ###LDF###.anl_kvk_authenticiteit.nnpid is 'Nnp-id';
comment on column ###LDF###.anl_kvk_authenticiteit.handelsnaam is 'Handelsnaam';
comment on column ###LDF###.anl_kvk_authenticiteit.statnaamvennschpnaam is 'Statutaire naam / vennootschapsnaam';
comment on column ###LDF###.anl_kvk_authenticiteit.zaaknaam is 'Zaaknaam';
comment on column ###LDF###.anl_kvk_authenticiteit.opgemaakt_adres is 'Opgemaakt adres';
comment on column ###LDF###.anl_kvk_authenticiteit.handelsregistervolgnummer is 'HandelsRegisterVolgnummer';
comment on column ###LDF###.anl_kvk_authenticiteit.postcode is 'Postcode';
comment on column ###LDF###.anl_kvk_authenticiteit.huisnummer is 'Huisnummer';
comment on column ###LDF###.anl_kvk_authenticiteit.huisletter is 'Huisletter';
comment on column ###LDF###.anl_kvk_authenticiteit.huisnummertoevoeging is 'Huisnummertoevoeging';
comment on column ###LDF###.anl_kvk_authenticiteit.afnemer is 'Lijst van afnemende applicaties';
comment on column ###LDF###.anl_kvk_authenticiteit.leverancier is 'Lijst van leverende applicaties';
comment on column ###LDF###.anl_kvk_authenticiteit.geleverddoorhrs is 'Geleverd door HRS';
comment on column ###LDF###.anl_kvk_authenticiteit.sleutelgegevensbeheer is 'Sleutelgegevensbeheer';


DROP TABLE IF EXISTS ###LDF###.anl_alg_kvk_mutaties;
CREATE TABLE ###LDF###.anl_alg_kvk_mutaties
(
    mutatiesoort character varying(12 ),
    datumtijdmutatie timestamp without time zone,
    handelsnaam character varying(160 ),
    statnaamvennschpnaam character varying(160 ),
    zaaknaam character varying(55 ),
    kvknum numeric(8 ),
    vestigingsnummer character varying(12 ),
    nnp_key numeric(14 ),
    adres1 character varying(110 )
)
WITH (
  OIDS=FALSE
);

DROP INDEX IF EXISTS ###LDF###.anl_alg_kvk_mutatiesI01  ;
DROP INDEX IF EXISTS ###LDF###.anl_alg_kvk_mutatiesI02  ;
CREATE INDEX anl_alg_kvk_mutatiesI01 on ###LDF###.anl_alg_kvk_mutaties (kvknum);
CREATE INDEX anl_alg_kvk_mutatiesI02 on ###LDF###.anl_alg_kvk_mutaties (nnp_key);

COMMENT ON TABLE ###LDF###.anl_alg_kvk_mutaties IS 'KVK mutaties';
COMMENT ON COLUMN ###LDF###.anl_alg_kvk_mutaties.mutatiesoort is 'Mutatiesoort';
COMMENT ON COLUMN ###LDF###.anl_alg_kvk_mutaties.datumtijdmutatie is 'Datum en tijd mutatie';
COMMENT ON COLUMN ###LDF###.anl_alg_kvk_mutaties.handelsnaam is 'Handelsnaam';
COMMENT ON COLUMN ###LDF###.anl_alg_kvk_mutaties.statnaamvennschpnaam is 'Statutaire naam / vennootschapsnaam';
COMMENT ON COLUMN ###LDF###.anl_alg_kvk_mutaties.zaaknaam is 'Zaaknaam';
COMMENT ON COLUMN ###LDF###.anl_alg_kvk_mutaties.kvknum is 'KvK nummer';
COMMENT ON COLUMN ###LDF###.anl_alg_kvk_mutaties.vestigingsnummer is 'Vestigingsnummer';
COMMENT ON COLUMN ###LDF###.anl_alg_kvk_mutaties.nnp_key is 'NNP key';
COMMENT ON COLUMN ###LDF###.anl_alg_kvk_mutaties.adres1 is 'Adres binnen- of buitenland (1)';

ALTER TABLE ###LDF###.anl_alg_kvk_mutaties OWNER TO ###OWNER###;

-- Table: ###LDF###.anl_tmp_verl_ontbr_reisdoc
-- Table removed as of CIRCOO-6373
DROP TABLE if exists ###LDF###.anl_tmp_verl_ontbr_reisdoc;

-- Table: ###LDF###.anl_tmp_prs_ind_inonderz

DROP TABLE if exists ###LDF###.anl_tmp_prs_ind_inonderz;

CREATE TABLE ###LDF###.anl_tmp_prs_ind_inonderz
(
  prsdbskey numeric(14,0) NOT NULL,
  status character varying(100)
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN ###LDF###.anl_tmp_prs_ind_inonderz.prsdbskey is 'Sleutelveld';
COMMENT ON COLUMN ###LDF###.anl_tmp_prs_ind_inonderz.status is 'Status';

ALTER TABLE ###LDF###.anl_tmp_prs_ind_inonderz OWNER TO ###OWNER###;

-- Table: ###LDF###.anl_beh_prf_elem_gegevens

DROP TABLE if exists ###LDF###.anl_beh_prf_elem_gegevens;

CREATE TABLE ###LDF###.anl_beh_prf_elem_gegevens
(
  profielcode numeric(5, 0),
  profielnaam character varying(25),
  gebruiker character varying(250),
  gebruikersnaam character varying(250),
  entiteitnummer numeric(5, 0),
  elementnummer character varying(6),
  entiteitnaam text,
  elementnaam character varying (100)
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE ###LDF###.anl_beh_prf_elem_gegevens IS 'Profiel met element gegevens';
COMMENT ON COLUMN ###LDF###.anl_beh_prf_elem_gegevens.profielcode is 'Profielcode';
COMMENT ON COLUMN ###LDF###.anl_beh_prf_elem_gegevens.profielnaam is 'Profielnaam';
COMMENT ON COLUMN ###LDF###.anl_beh_prf_elem_gegevens.gebruiker is 'Gebruiker';
COMMENT ON COLUMN ###LDF###.anl_beh_prf_elem_gegevens.gebruikersnaam is 'Loginnaam gebruiker';
COMMENT ON COLUMN ###LDF###.anl_beh_prf_elem_gegevens.entiteitnummer is 'Entiteitnummer';
COMMENT ON COLUMN ###LDF###.anl_beh_prf_elem_gegevens.elementnummer is 'Elementnummer';
COMMENT ON COLUMN ###LDF###.anl_beh_prf_elem_gegevens.entiteitnaam is 'Entiteit naam';
COMMENT ON COLUMN ###LDF###.anl_beh_prf_elem_gegevens.elementnaam is 'Element naam';

ALTER TABLE ###LDF###.anl_beh_prf_elem_gegevens OWNER TO ###OWNER###;


-- Table: ###LDF###.anl_beh_element_group

DROP TABLE if exists ###LDF###.anl_beh_element_group;

CREATE TABLE ###LDF###.anl_beh_element_group
(
  groep numeric(5, 0),
  code_groep character varying(15),
  omschrijving_groep character varying(80),
  entiteitnummer numeric(5, 0),
  elementnummer character varying(6),
  omschrijving_element character varying(50),
  entiteitnaam character varying (100),
  elementnaam character varying (100)
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE ###LDF###.anl_beh_element_group IS 'Elementen per groep';
COMMENT ON COLUMN ###LDF###.anl_beh_element_group.groep is 'Groep';
COMMENT ON COLUMN ###LDF###.anl_beh_element_group.code_groep is 'Groep code';
COMMENT ON COLUMN ###LDF###.anl_beh_element_group.omschrijving_groep is 'Omschrijving groep';
COMMENT ON COLUMN ###LDF###.anl_beh_element_group.entiteitnummer is 'Entiteitnummer';
COMMENT ON COLUMN ###LDF###.anl_beh_element_group.elementnummer is 'Elementnummer';
COMMENT ON COLUMN ###LDF###.anl_beh_element_group.omschrijving_element is 'Omschrijving element';
COMMENT ON COLUMN ###LDF###.anl_beh_element_group.entiteitnaam is 'Entiteit naam';
COMMENT ON COLUMN ###LDF###.anl_beh_element_group.elementnaam is 'Element naam';

ALTER TABLE ###LDF###.anl_beh_element_group OWNER TO ###OWNER###;


-- Table: ###LDF###.anl_beh_afnemerregels_per_lag

DROP TABLE IF EXISTS ###LDF###.anl_beh_afnemerregels_per_lag;
CREATE TABLE ###LDF###.anl_beh_afnemerregels_per_lag
(
    lag numeric (5 ),
    code_lag character varying(7 ),
    omschrijving_lag character varying(80 ),
    referentieletter character varying(2 ),
    applicatie numeric (5 ),
    code_applicatie character varying(50 ),
    omschrijving_applicatie character varying(80 ),
    organisatie character varying(200 ),
    administratie character varying(50 ),
	afnemerregelnummer numeric(10 ),
	entiteitnummer numeric(5 ),
	elementnummer character varying(6 ),
    entiteitnaam character varying (100),
    elementnaam character varying (100),
	ind_kennisgeving character varying(1 ),
	ind_inf_kennisgeving character varying(1 ),
    hierarchie numeric(2 ),
	ind_kennisg_eig_afwijk_waarde character varying(1 ),
	ind_kennisg_apl_afwijk_waarde character varying(1 ),
	ind_aanpassen_leverrecord character varying(1 ),
    ind_hist_kennisgeving character varying(1 ),
	selectiemethode character varying(40 ),
	actie_inf_verwijderd character varying(1 ),
    appl_gerelateerde numeric(5 ),
	code_appl_gerelateerde character varying(50 ),
	omschrijving_appl_gerelateerde character varying(80 )
)
WITH (
  OIDS=FALSE
);

DROP INDEX IF EXISTS ###LDF###.anl_beh_afnemerregels_per_lagI01;
CREATE INDEX anl_beh_afnemerregels_per_lagI01 on ###LDF###.anl_beh_afnemerregels_per_lag (afnemerregelnummer);

COMMENT ON TABLE ###LDF###.anl_beh_afnemerregels_per_lag IS 'Afnemerregels per lag';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.lag is 'Lag';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.code_lag is 'Code lag';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.omschrijving_lag is 'Omschrijving lag';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.referentieletter is 'Referentie letter';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.applicatie is 'Applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.code_applicatie is 'Code applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.omschrijving_applicatie is 'Omschrijving applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.organisatie is 'Organisatie';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.administratie is 'Administratie';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.afnemerregelnummer is 'Afnemer regel nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.entiteitnummer is 'Entiteit nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.elementnummer is 'Element nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.entiteitnaam is 'Entiteit naam';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.elementnaam is 'Element naam';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.ind_kennisgeving is 'Ind kennisgeving';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.ind_inf_kennisgeving is 'Ind inf kennisgeving';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.hierarchie is 'Hierarchie';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.ind_kennisg_eig_afwijk_waarde is 'Ind kennisg eig afwijk waarde';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.ind_kennisg_apl_afwijk_waarde is 'Ind kennisg apl afwijk waarde';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.ind_aanpassen_leverrecord is 'Ind aanpassen leverrecord';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.ind_hist_kennisgeving is 'Ind hist kennisgeving';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.selectiemethode is 'Selectie methode';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.actie_inf_verwijderd is 'Actie inf verwijderd';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.appl_gerelateerde is 'Appl gerelateerde';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.code_appl_gerelateerde is 'Code appl gerelateerde';
COMMENT ON COLUMN ###LDF###.anl_beh_afnemerregels_per_lag.omschrijving_appl_gerelateerde is 'Omschrijving appl gerelateerde';

ALTER TABLE ###LDF###.anl_beh_afnemerregels_per_lag OWNER TO ###OWNER###;

-- Table: ###LDF###.anl_beh_leverregels_per_lag

DROP TABLE IF EXISTS ###LDF###.anl_beh_leverregels_per_lag;
CREATE TABLE ###LDF###.anl_beh_leverregels_per_lag
(
    lag numeric (5 ),
    code_lag character varying(7 ),
    omschrijving_lag character varying(80 ),
    referentie_letter character varying(2 ),
    applicatie numeric (5 ),
    code_applicatie character varying(50 ),
    omschrijving_applicatie character varying(80 ),
    organisatie character varying(200 ),
    administratie character varying(50 ),
	leverregelnummer numeric(10 ),
	entiteitnummer numeric(5 ),
    elementnummer character varying(6 ),
    entiteitnaam character varying (100),
    elementnaam character varying (100),
    indicatie_afwijkend character varying(1 ),
    hierarchie numeric(2 ),
    dagen_afwijkende_waarde numeric(3 ),
    indicatie_nieuwe_relatie character varying(1 ),
    indicatie_bijlezen_waarde character varying(1 ),
    indicatie_vlg_match character varying(1 ),
    applicatie_gerelateerde numeric(5 ),
	code_applicatie_gerelateerde character varying(50 ),
	omschrijving_applicatie_gerel character varying(80 )
)
WITH (
  OIDS=FALSE
);

DROP INDEX IF EXISTS ###LDF###.anl_beh_leverregels_per_lagI01;
CREATE INDEX anl_beh_leverregels_per_lagI01 on ###LDF###.anl_beh_leverregels_per_lag (leverregelnummer);

COMMENT ON TABLE ###LDF###.anl_beh_leverregels_per_lag IS 'Leverregels per lag';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.lag is 'Lag';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.code_lag is 'Code lag';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.omschrijving_lag is 'Omschrijving lag';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.referentie_letter is 'Referentie letter';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.applicatie is 'Applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.code_applicatie is 'Code applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.omschrijving_applicatie is 'Omschrijving applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.organisatie is 'Organisatie';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.administratie is 'Administratie';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.leverregelnummer is 'Leverregel nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.entiteitnummer is 'Entiteit nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.elementnummer is 'Element nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.entiteitnaam is 'Entiteit naam';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.elementnaam is 'Element naam';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.indicatie_afwijkend is 'Indicatie afwijkend';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.hierarchie is 'Hierarchie';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.dagen_afwijkende_waarde is 'Dagen afwijkende waarde';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.indicatie_nieuwe_relatie is 'Indicatie nieuwe relatie';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.indicatie_bijlezen_waarde is 'Indicatie bijlezen waarde';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.indicatie_vlg_match is 'Indicatie vlg match';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.applicatie_gerelateerde is 'Applicatie gerelateerde';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.code_applicatie_gerelateerde is 'Code applicatie gerelateerde';
COMMENT ON COLUMN ###LDF###.anl_beh_leverregels_per_lag.omschrijving_applicatie_gerel is 'Omschrijving applicatie gerel';

ALTER TABLE ###LDF###.anl_beh_leverregels_per_lag OWNER TO ###OWNER###;

-- Table: ###LDF###.anl_beh_selmeth_afnregels

DROP TABLE IF EXISTS ###LDF###.anl_beh_selmeth_afnregels;
CREATE TABLE ###LDF###.anl_beh_selmeth_afnregels
(
    soortregel character varying(160 ),
    regelnummer numeric (10 ),
    applicatienummer numeric (5 ),
    code_applicatie character varying(50 ),
    omschrijving_applicatie character varying(80 ),
    organisatie character varying(200 ),
    administratie character varying(50 ),
    soort numeric(3 ),
    entiteitnummer numeric(5 ),
    entiteitnaam character varying (100),
    elementnaam character varying (100),
	selectiemethode character varying(40 )
)
WITH (
  OIDS=FALSE
);

DROP INDEX IF EXISTS ###LDF###.anl_beh_selmeth_afnregelsI01;
CREATE INDEX anl_beh_selmeth_afnregelsI01 on ###LDF###.anl_beh_selmeth_afnregels (selectiemethode);

COMMENT ON TABLE ###LDF###.anl_beh_selmeth_afnregels IS 'Selectiemethodes in afnemerregels';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.soortregel is 'Soort regel';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.regelnummer is 'Regel nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.applicatienummer is 'Applicatie nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.code_applicatie is 'Code applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.omschrijving_applicatie is 'Omschrijving applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.organisatie is 'Organisatie';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.administratie is 'Administratie';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.soort is 'Soort';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.entiteitnummer is 'Entiteit nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.entiteitnaam is 'Entiteit naam';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.elementnaam is 'Element naam';
COMMENT ON COLUMN ###LDF###.anl_beh_selmeth_afnregels.selectiemethode is 'Selectie methode';

ALTER TABLE ###LDF###.anl_beh_selmeth_afnregels OWNER TO ###OWNER###;

-- Table: ###LDF###.anl_beh_verstrregels_per_lag

DROP TABLE IF EXISTS ###LDF###.anl_beh_verstrregels_per_lag;
CREATE TABLE ###LDF###.anl_beh_verstrregels_per_lag
(
    lag numeric (5 ),
    code_lag character varying(7 ),
    omschrijving_lag character varying(80 ),
    referentie_letter character varying(2 ),
    applicatie numeric (5 ),
    code_applicatie character varying(50 ),
    omschrijving_applicatie character varying(80 ),
    organisatie character varying(200 ),
    administratie character varying(50 ),
	verstrekkingsregelnummer numeric(5 ),
	entiteitnummer numeric(5 ),
    entiteitnaam character varying (100),
    elementnaam character varying (100),
    hierarchie numeric(2 ),
	afnemersindicatie character varying(1 ),
	indicatie_overname character varying(1 ),
	selectiemethode character varying(40 ),
    volgnummer_applicatie numeric(2 )
)
WITH (
  OIDS=FALSE
);

DROP INDEX IF EXISTS ###LDF###.anl_beh_verstrregels_per_lagI01;
CREATE INDEX anl_beh_verstrregels_per_lagI01 on ###LDF###.anl_beh_verstrregels_per_lag (verstrekkingsregelnummer);

COMMENT ON TABLE ###LDF###.anl_beh_verstrregels_per_lag IS 'Verstrekkingregels per lag';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.lag is 'Lag';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.code_lag is 'Code lag';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.omschrijving_lag is 'Omschrijving lag';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.referentie_letter is 'Referentie letter';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.applicatie is 'Applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.code_applicatie is 'Code applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.omschrijving_applicatie is 'Omschrijving applicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.organisatie is 'Organisatie';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.administratie is 'Administratie';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.verstrekkingsregelnummer is 'Verstrekkings regel nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.entiteitnummer is 'Entiteit nummer';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.entiteitnaam is 'Entiteit naam';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.elementnaam is 'Element naam';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.hierarchie is 'Hierarchie';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.afnemersindicatie is 'Afnemers indicatie';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.indicatie_overname is 'Indicatie overname';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.selectiemethode is 'Selectie methode';
COMMENT ON COLUMN ###LDF###.anl_beh_verstrregels_per_lag.volgnummer_applicatie is 'Volgnummer_applicatie';

ALTER TABLE ###LDF###.anl_beh_verstrregels_per_lag OWNER TO ###OWNER###;

-- Table: ###LDF###.ldf_bg_elements

DROP TABLE IF EXISTS ###LDF###.ldf_bg_elements;
CREATE TABLE ###LDF###.ldf_bg_elements
(
    elementid character varying (6 ),
    entiteitnaam character varying(100 ),
    elementnaam character varying(100 )
)
WITH (
  OIDS=FALSE
);

DROP INDEX IF EXISTS ###LDF###.ldf_bg_elementsI01;
CREATE INDEX ldf_bg_elementsI01 on ###LDF###.ldf_bg_elements (elementid);
ALTER TABLE ###LDF###.ldf_bg_elements ADD CONSTRAINT ldf_bg_elementsC01 UNIQUE (elementid);

COMMENT ON TABLE ###LDF###.ldf_bg_elements IS 'Table to keep the mapping of BG elements number->name';
COMMENT ON COLUMN ###LDF###.ldf_bg_elements.elementid is 'BG Element id';
COMMENT ON COLUMN ###LDF###.ldf_bg_elements.entiteitnaam is 'BG Entity name';
COMMENT ON COLUMN ###LDF###.ldf_bg_elements.elementnaam is 'BG field name';

ALTER TABLE ###LDF###.ldf_bg_elements OWNER TO ###OWNER###;

-- Table: ###LDF###.clearable_companies

DROP TABLE IF EXISTS ###LDF###.clearable_companies;

CREATE TABLE ###LDF###.clearable_companies
(
  kvknummer character varying(8),
  vestigingsnummer character varying(12),
  handelsnaam character varying(160),
  leveranciers character varying(50),
  afnemers character varying(50),
  sleutelgegevensbeheer numeric(14,0),
  binnengemeentelijk character varying(1),
  datumeinde numeric(8,0),
  status character varying(100)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ###LDF###.clearable_companies OWNER TO ###OWNER###;


-- create inserts LDF

GRANT ALL ON SCHEMA ###LDF### TO ###OWNER###;
GRANT ALL ON ALL TABLES IN SCHEMA ###LDF### TO ###OWNER###;
GRANT ALL ON ALL SEQUENCES IN SCHEMA ###LDF### TO ###OWNER###;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA ###LDF### TO ###OWNER###;
-- ldf views


-- now set search path since all schema's are available now

ALTER ROLE ###OWNER### IN DATABASE ###DB_NAME###
    SET search_path = ###MKS###, ###CIR###, ###COO###,
        ###CGS###, ###PLT###, ###CMG###,
        ###CMS###, ###POSTGIS###, ###LDF###;
GRANT ###DB_OWNER### TO ###OWNER###;

ALTER ROLE ###USER### IN DATABASE ###DB_NAME###
    SET search_path = ###MKS###, ###CIR###, ###COO###,
        ###CGS###, ###PLT###, ###CMG###,
        ###CMS###, ###POSTGIS###, ###LDF###;
GRANT ###DB_OWNER### TO ###USER###;
