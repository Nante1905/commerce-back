-- create database si_commerce;
-- \c si_commerce;

CREATE TABLE categorie(
   id serial,
   reference VARCHAR(30)  NOT NULL,
   nom VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(reference),
   UNIQUE(nom)
);

CREATE TABLE direction(
   id serial,
   nom VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);

CREATE TABLE demande(
   id serial,
   reference VARCHAR(30)  NOT NULL,
   jour DATE NOT NULL,
   etat  integer NOT NULL default 0,
   id_direction INTEGER NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(reference),
   FOREIGN KEY(id_direction) REFERENCES direction(id)
);

CREATE TABLE demande_proforma(
   id serial,
   reference VARCHAR(30)  NOT NULL,
   delai_livraison DATE,
   jour_demande DATE NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(reference)
);

CREATE TABLE fournisseur(
   id serial,
   nom VARCHAR(255)  NOT NULL,
   reference VARCHAR(30)  NOT NULL,
   email VARCHAR(255)  NOT NULL,
   telephone VARCHAR(30) ,
   PRIMARY KEY(id),
   UNIQUE(nom),
   UNIQUE(reference)
);

CREATE TABLE demande_proforma_fournisseur(
   id serial,
   id_fournisseur INTEGER NOT NULL,
   id_demande_proforma INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_fournisseur) REFERENCES fournisseur(id),
   FOREIGN KEY(id_demande_proforma) REFERENCES demande_proforma(id)
);

CREATE TABLE mode_paiement(
   id serial,
   nom VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);

CREATE TABLE employe(
   id serial,
   nom VARCHAR(255)  NOT NULL,
   prenom VARCHAR(255)  NOT NULL,
   date_naissance DATE NOT NULL,
   date_embauche DATE NOT NULL,
   email VARCHAR(255)  NOT NULL,
   mot_de_passe VARCHAR(30)  NOT NULL,
   id_direction INTEGER NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(email),
   FOREIGN KEY(id_direction) REFERENCES direction(id)
);

CREATE TABLE article(
   id serial,
   designation VARCHAR(255)  NOT NULL,
   reference VARCHAR(30)  NOT NULL,
   id_categorie INTEGER NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(designation),
   UNIQUE(reference),
   FOREIGN KEY(id_categorie) REFERENCES categorie(id)
);

CREATE TABLE resultat_proforma(
   id serial,
   format_prix INTEGER NOT NULL,
   delai_livraison DATE,
   date_saisie DATE NOT NULL,
   id_demande_proforma_fournisseur INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_demande_proforma_fournisseur) REFERENCES demande_proforma_fournisseur(id)
);

CREATE TABLE bon_commande(
   id serial,
   reference VARCHAR(30)  NOT NULL,
   date_creation DATE NOT NULL,
   livraison_partielle BOOLEAN NOT NULL,
   delai_livraison DATE,
   status BOOLEAN NOT NULL,
   id_mode_paiement INTEGER NOT NULL,
   id_fournisseur INTEGER NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(reference),
   FOREIGN KEY(id_mode_paiement) REFERENCES mode_paiement(id),
   FOREIGN KEY(id_fournisseur) REFERENCES fournisseur(id)
);

CREATE TABLE demande_details(
   id_article INTEGER,
   id_demande INTEGER,
   quantite DOUBLE PRECISION NOT NULL,
   status INTEGER NOT NULL,
   PRIMARY KEY(id_article, id_demande),
   FOREIGN KEY(id_article) REFERENCES article(id),
   FOREIGN KEY(id_demande) REFERENCES demande(id)
);

CREATE TABLE demande_proforma_details(
   id_article INTEGER,
   id_demande_proforma INTEGER,
   quantite DOUBLE PRECISION NOT NULL,
   PRIMARY KEY(id_article, id_demande_proforma),
   FOREIGN KEY(id_article) REFERENCES article(id),
   FOREIGN KEY(id_demande_proforma) REFERENCES demande_proforma(id)
);

CREATE TABLE fournisseur_categorie(
   id_categorie INTEGER,
   id_fournisseur INTEGER,
   PRIMARY KEY(id_categorie, id_fournisseur),
   FOREIGN KEY(id_categorie) REFERENCES categorie(id),
   FOREIGN KEY(id_fournisseur) REFERENCES fournisseur(id)
);

CREATE TABLE resultat_proforma_details(
   id_article INTEGER,
   id_resultat_proforma INTEGER,
   quantite_dispo DOUBLE PRECISION NOT NULL,
   pu DOUBLE PRECISION NOT NULL,
   PRIMARY KEY(id_article, id_resultat_proforma),
   FOREIGN KEY(id_article) REFERENCES article(id),
   FOREIGN KEY(id_resultat_proforma) REFERENCES resultat_proforma(id)
);

CREATE TABLE bon_commande_details(
   id_article INTEGER,
   id_bon_commande INTEGER,
   quantite DOUBLE PRECISION NOT NULL,
   pu_ht DOUBLE PRECISION,
   pu_ttc DOUBLE PRECISION,
   TVA DOUBLE PRECISION,
   PRIMARY KEY(id_article, id_bon_commande),
   FOREIGN KEY(id_article) REFERENCES article(id),
   FOREIGN KEY(id_bon_commande) REFERENCES bon_commande(id)
);

CREATE TABLE proforma_besoin(
   id_demande INTEGER,
   id_proforma INTEGER,
   PRIMARY KEY(id_demande, id_proforma),
   FOREIGN KEY(id_demande) REFERENCES demande(id),
   FOREIGN KEY(id_proforma) REFERENCES demande_proforma(id)
);

CREATE TABLE chef_direction(
   id_direction INTEGER,
   id_employe INTEGER,
   date_promotion DATE NOT NULL,
   FOREIGN KEY(id_direction) REFERENCES direction(id),
   FOREIGN KEY(id_employe) REFERENCES employe(id)
);

-- MIAMPY POUR PROFORMA
alter table demande_proforma_fournisseur add reference VARCHAR(50) unique;
alter table demande_proforma_fournisseur add etat integer not null default 0;
alter table resultat_proforma alter column date_saisie set default now();
alter table bon_commande alter column date_creation set default now();
alter table bon_commande_details drop column pu_ttc;
alter table bon_commande_details drop column tva;
alter table bon_commande add column id_demande_proforma integer not null references demande_proforma(id);
alter table resultat_proforma_details drop constraint resultat_proforma_details_pkey;
alter table resultat_proforma_details add column id serial;
alter table resultat_proforma_details add PRIMARY key (id);
alter table bon_commande_details drop constraint bon_commande_details_pkey;
alter table bon_commande_details add column id serial;
alter table bon_commande_details add PRIMARY key (id);
alter table bon_commande drop column status;
alter table bon_commande add status integer default 0;

create table validation_bon_commande (
   id serial PRIMARY key,
   jour date not null default now(),
   id_bon_commande integer not NULL references bon_commande(id),
   id_employe integer not null references employe(id)
);

--------sarobidy script stock

create table bon_livraison (
    id serial primary key,
    id_bon_commande integer not NULL references bon_commande(id),
    id_employe integer not NULL references employe(id)
);

create table bon_livraison_details (
   id serial primary key,
   id_article integer not NULL references article(id),
   qte double precision,
   id_bon_livraison integer not NULL references bon_livraison(id)
);

create table demande_explication (
   id serial primary key,
   motif varchar(100),
   jour date not null,
   text text,
   id_destinataire integer not NULL references employe(id)
);

create table facture (
   id serial primary key,
   format_prix integer,
   id_bon_commande integer not Null references bon_commande(id),
   jour date not null,
   jour_reception date not null,
   id_employe integer not null references employe(id),
   etat integer,
   jour_validation date not null
);

create table facture_details (
   id serial primary key,
   id_facture integer not null references facture(id),
   id_article integer not NULL references article(id),
   qte double precision,
   pu double precision
);

create table bon_reception (
   id serial primary key,
   id_bon_livraison integer not NULL references bon_livraison(id),
   jour date not null default now(),
   id_employe integer not null references employe(id)
);

create table bon_reception_details (
   id serial primary key,
   id_bon_reception integer not null references bon_reception(id),
   qte double precision,
   id_article integer not NULL references article(id)
);

create table bon_entre (
   id serial primary key,
   id_bon_reception  integer not NULL references bon_reception (id),
   jour date not null default now(),
   id_employe integer not null references employe(id)
);

create table bon_entre_details (
   id serial primary key,
   id_bon_entre integer not null references bon_entre(id),
   qte double precision,
   id_article integer not NULL references article(id)
);

create table entre_stock (
   id serial primary key,
   jour date not null,
   id_bon_entre integer not null references bon_entre(id)
);

create table entre_stock_details (
   id serial primary key,
   id_entre_stock integer not null references entre_stock(id),
   id_article integer not NULL references article(id),
   pu_ht double precision,
   qte double precision
);

create table type_sortie (
   id serial primary key,
   nom varchar(100)
);

create table bon_sortie (
   id serial primary key,
   id_type integer not null references type_sortie(id),
   id_direction integer not null references direction(id),
   jour date not null default now(),
   id_employe integer not null references employe(id)
);

create table bon_sortie_details (
   id serial primary key,
   id_bon_sortie integer not null references bon_sortie(id),
   id_article integer not NULL references article(id),
   qte double precision
);

create table sortie_stock (
   id serial primary key,
   jour date not null,
   id_bon_sortie integer not null references bon_sortie(id)
);

create table sortie_stock_details (
   id serial primary key,
   id_sortie_stock integer not null references sortie_stock(id),
   id_article integer not NULL references article(id),
   pu_ht double precision,
   qte double precision,
   id_entre_stock integer not null references entre_stock(id)
);