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
   est_ouvert BOOLEAN NOT NULL,
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

-- data nante 
INSERT into direction (nom) values ('Direction des Ressources Humaines'), ('Direction des Achats'), ('Direction Financière'), ('Direction des Systèmes d''Information');

INSERT INTO employe (nom, prenom, date_naissance, date_embauche, email, mot_de_passe, id_direction)
VALUES
  ('Razanamahafaly', 'Andriambololona', '1990-01-01', '2020-01-01', 'andriambololona@email.com', 'mdp1', 1),
  ('Rakotonirina', 'Fenoarivo', '1991-02-02', '2021-02-02', 'fenoarivo@email.com', 'mdp2', 2),
  ('Ravelonirina', 'Harimalala', '1992-03-03', '2022-03-03', 'harimalala@email.com', 'mdp3', 3);