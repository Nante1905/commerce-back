-- Pour la table categorie
INSERT INTO categorie (reference, nom) VALUES
('C001', 'Cahier'),
('C002', 'Stylo'),
('C003', 'Papier'),
('C004', 'Ordinateur');

-- Pour la table direction
INSERT INTO direction (nom) VALUES
('Direction des Ressources Humaines'),
('Direction des Achats'),
('Direction Financière'),
('Direction des Systèmes d''Information');

-- Pour la table demande
INSERT INTO demande (reference, jour, est_ouvert, id_direction) VALUES
('D001', '2023-01-01', true, 1),
('D002', '2023-02-02', true, 2),
('D003', '2023-03-03', false, 3),
('D004', '2023-04-04', true, 4);

-- Pour la table fournisseur
INSERT INTO fournisseur (nom, reference, email, telephone) VALUES
('Fournisseur A', 'F001', 'fournisseurA@email.com', '0123456789'),
('Fournisseur B', 'F002', 'fournisseurB@email.com', '0987654321'),
('Fournisseur C', 'F003', 'fournisseurC@email.com', '1234567890');

-- Pour la table mode_paiement
INSERT INTO mode_paiement (nom) VALUES
('Carte de crédit'),
('Carte de débit'),
('Virement bancaire');

-- Pour la table article
INSERT INTO article (designation, reference, id_categorie) VALUES
('Cahier A4', 'A001', 1),
('Stylo Bic', 'A002', 2),
('Papier A4', 'A003', 3),
('Ordinateur Portable', 'A004', 4),
('Carnet de notes', 'A005', 1),
('Ciseaux', 'A006', 2),
('Carte de visite', 'A007', 3),
('Imprimante laser', 'A008', 4),
('Téléphone portable', 'A009', 1),
('Moniteur d''ordinateur', 'A010', 2);

-- Pour la table resultat_proforma
INSERT INTO resultat_proforma (format_prix, delai_livraison, date_saisie, id_demande_proforma_fournisseur) VALUES
(100, '2023-01-01', '2023-01-01', 1),
(200, '2023-02-02', '2023-02-02', 2),
(300, '2023-03-03', '2023-03-03', 3);

-- Pour la table bon_commande
INSERT INTO bon_commande (reference, date_creation, livraison_partielle, delai_livraison, status, id_mode_paiement, id_fournisseur) VALUES
('BC001', '2023-01-01', true, '2023-01-01', true, 1, 1),
('BC002', '2023-02-02', false, '2023-02-02', false, 2, 2),
('BC003', '2023-03-03', true, '2023-03-03', true, 3, 3);

-- Pour la table demande_details
INSERT INTO demande_details (id_article, id_demande, quantite, status) VALUES
(1, 1, 10, 1),
(2, 2, 20, 2),
(3, 3, 30, 3);

-- Pour la table demande_proforma_details
INSERT INTO demande_proforma_details (id_article, id_demande_proforma, quantite) VALUES
(1, 1, 10),
(2, 2, 20),
(3, 3, 30);

-- Pour la table fournisseur_categorie
INSERT INTO fournisseur_categorie (id_categorie, id_fournisseur) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Pour la table resultat_proforma_details
INSERT INTO resultat_proforma_details (id_article, id_resultat_proforma, quantite_dispo, pu) VALUES
(1, 1, 10, 100),
(2, 2, 20, 200),
(3, 3, 30, 300);

-- Pour la table bon_commande_details
INSERT INTO bon_commande_details (id_article, id_bon_commande, quantite, pu_ht, pu_ttc, TVA) VALUES
(1, 1, 10, 100, 120, 20),
(2, 2, 20, 200, 240, 40),
(3, 3, 30, 300, 360, 60);

-- Pour la table proforma_besoin
INSERT INTO proforma_besoin (id_demande, id_proforma) VALUES
(1, 1),
(2, 2),
(3, 3);

-- Pour la table chef_direction
INSERT INTO chef_direction (id_direction, id_employe, date_promotion) VALUES
(1, 1, '2023-01-01'),
(2, 2, '2023-02-02'),
(3, 3, '2023-03-03'),
(4, 4, '2023-04-04');

-- Pour la table employe
INSERT INTO employe (nom, prenom, email, telephone, id_direction) VALUES
('Dupont', 'Jean', 'jean.dupont@email.com', '0123456789', 1),
('Martin', 'Paul', 'paul.martin@email.com', '0987654321', 2),
('Leclerc', 'Marie', 'marie.leclerc@email.com', '1234567890', 3),
('Durand', 'Sophie', 'sophie.durand@email.com', '1122334455', 4);

-- Pour la table employe_categorie
-- Pour la table employe_categorie
INSERT INTO employe_categorie (id_categorie, id_employe) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- Pour la table employe_direction
INSERT INTO employe_direction (id_employe, id_direction) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);




-- -- Insérer des catégories d'articles
-- INSERT INTO categorie (reference, nom) VALUES 
-- ('REF001', 'Papeterie'),
-- ('REF002', 'Électronique');

-- -- Insérer des articles spécifiques
-- INSERT INTO article (designation, reference, id_categorie) VALUES 
-- ('Cahier', 'CAH001', 1),
-- ('Stylo', 'STY001', 1),
-- ('Ordinateur portable', 'ORD001', 2);

-- -- Insérer les directions
-- INSERT INTO direction (nom) VALUES 
-- ('Direction des Ressources Humaines'), 
-- ('Direction des Achats'), 
-- ('Direction Financière'), 
-- ('Direction des Systèmes d''Information');

-- -- Insérer les demandes pour chaque direction avec leurs besoins spécifiques
-- INSERT INTO demande (reference, jour, est_ouvert, id_direction) VALUES 
-- ('DEMANDE_RH_001', '2023-11-18', true, 1),
-- ('DEMANDE_ACHAT_001', '2023-11-18', true, 2),
-- ('DEMANDE_FINANCE_001', '2023-11-18', true, 3),
-- ('DEMANDE_SI_001', '2023-11-18', true, 4);

-- INSERT INTO demande_details (id_article, id_demande, quantite, status) VALUES 
-- (1, 1, 100, 1), -- 100 cahiers pour la direction RH
-- (2, 1, 150, 1), -- 150 stylos pour la direction RH
-- (1, 2, 200, 1), -- 200 cahiers pour la direction des Achats
-- (2, 2, 100, 1), -- 100 stylos pour la direction des Achats
-- (1, 3, 50, 1),  -- 50 cahiers pour la direction Financière
-- (2, 3, 50, 1),  -- 50 stylos pour la direction Financière
-- (3, 4, 5, 1);   -- 5 ordinateurs portables pour la direction des Systèmes d'Information

-- -- Insérer les fournisseurs pour les articles
-- INSERT INTO fournisseur (nom, reference, email, telephone) VALUES 
-- ('Fournisseur Papeterie', 'F001', 'contact@fournisseurpapeterie.com', '0123456789'),
-- ('Fournisseur Électronique', 'F002', 'contact@fournisseurelectronique.com', '9876543210'),
-- ('Leader Price', 'F003', 'contact@leaderprice.com', '1111111111'),
-- ('Jumbo Score', 'F004', 'contact@jumboscore.com', '2222222222'),
-- ('Shoprite', 'F005', 'contact@shoprite.com', '3333333333');

-- -- Associer des fournisseurs aux catégories d'articles
-- INSERT INTO fournisseur_categorie (id_categorie, id_fournisseur) VALUES 
-- (1, 3), -- Leader Price pour les articles de papeterie
-- (2, 4), -- Jumbo Score pour les articles électroniques
-- (1, 5), -- Shoprite pour les articles de papeterie
-- (1, 1), -- Fournisseur Papeterie pour les articles de papeterie
-- (2, 2); -- Fournisseur Électronique pour les articles électroniques

-- -- Créer une demande de proforma pour les besoins spécifiques
-- INSERT INTO demande_proforma (reference, delai_livraison, jour_demande) VALUES 
-- ('PROFORMA001', '2023-11-20', '2023-11-18'),
-- ('PROFORMA002', '2023-11-25', '2023-11-18');

-- -- Associer des articles spécifiques à chaque demande de proforma
-- INSERT INTO demande_proforma_details (id_article, id_demande_proforma, quantite) VALUES 
-- (1, 1, 100), -- Leader Price - 100 cahiers
-- (2, 1, 100), -- Fournisseur Électronique - 100 stylos
-- (1, 2, 100), -- Jumbo Score - 100 cahiers
-- (2, 3, 100); -- Shoprite - 100 stylos

-- -- Associer les demandes de proforma aux fournisseurs
-- INSERT INTO demande_proforma_fournisseur (id_fournisseur, id_demande_proforma) VALUES 
-- (1, 1),
-- (2, 1),
-- (1, 2),
-- (5, 2); -- Shoprite est associé à la deuxième demande de proforma
