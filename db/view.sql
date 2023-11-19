-- SSCRIPT VUE
CREATE OR REPLACE VIEW v_demande_par_nature AS
SELECT
    demande_details.id_demande AS id_demande,
    article.id AS id_article,
    article.designation AS article,
    demande.reference AS reference_demande,
    demande_details.quantite AS quantite_total,
    demande_details.status AS status
FROM
    demande_details
JOIN
    article ON demande_details.id_article = article.id
JOIN
    demande ON demande_details.id_demande = demande.id
WHERE
    demande.est_ouvert = true;


---------liste demande_proforma sans resultatproforma  
CREATE OR REPLACE VIEW demandes_proforma AS
SELECT dp.reference, dp.jour_demande AS date, f.nom AS fournisseur
FROM demande_proforma dp
JOIN demande_proforma_fournisseur dpf ON dp.id = dpf.id_demande_proforma
JOIN fournisseur f ON dpf.id_fournisseur = f.id
WHERE dp.id NOT IN (SELECT id_demande_proforma_fournisseur FROM resultat_proforma);

