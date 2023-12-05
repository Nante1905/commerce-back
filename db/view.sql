-- SSCRIPT VUE
create view v_fournisseur_article as 
select a.id id_article, f.* 
    from article a join fournisseur_categorie fc on a.id_categorie = fc.id_categorie 
    join fournisseur f on fc.id_fournisseur = f.id ;

create view v_demande_details as select * from demande d join demande_details dd on d.id = dd.id_demande;

select sum(vdd.quantite) quantite, vdd.id_article from v_demande_details vdd where vdd.status = 5 and vdd.id_demande in :params GROUP BY vdd.id_article;
create view v_demande_details as 
select * from demande d join demande_details dtl on dtl.id_demande = d.id

-- MIALISOA 30/11/2023
-- resultat proforma en HORS - TAXE
create view v_resultat_proforma_ht as
select d.id, d.id_article, d.id_resultat_proforma, d.quantite_dispo,
case
	when r.format_prix = 5 then pu/1.2
	else pu
end pu
from resultat_proforma r
	join resultat_proforma_details d on d.id_resultat_proforma = r.id


create view v_resultat_proforma_ht_avec_demande as 
select h.*, id_demande_proforma from v_resultat_proforma_ht h
join resultat_proforma r on h.id_resultat_proforma = r.id
join demande_proforma_fournisseur d on r.id_demande_proforma_fournisseur = d.id

create view v_nbr_reponse_proforma as select id_demande_proforma, count (distinct(id_resultat_proforma)) as nbr 
from v_resultat_proforma_ht_avec_demande 
group by id_demande_proforma
create view v_nbr_fournisseur_proforma as select id_demande_proforma, count(id_fournisseur) as nbr 
from demande_proforma_fournisseur 
group by id_demande_proforma;

create view v_statistique_proforma as select r.id_demande_proforma as id, coalesce(r.nbr, 0) reponse, coalesce(f.nbr, 0) demande from 
v_nbr_reponse_proforma r
left outer join v_nbr_fournisseur_proforma f
on r.id_demande_proforma = f.id_demande_proforma;
--  --------------------------------------------

-- MIALISOA STOCK
create view v_facture_details_ht as
select d.id, d.id_facture, d.id_article, d.qte,
case
	when f.format_prix = 5 then pu/1.2
	else pu
end pu
from facture f
	join facture_details d
	on d.id_facture = f.id

create view v_bon_reception_valide as 
select br.* 
from bon_reception br
	join bon_livraison bl on br.id_bon_livraison = bl.id
	join facture f on f.id_bon_commande = bl.id_bon_commande
where f.jour_validation is not null;

 create view v_bon_reception_avec_livraison as
 SELECT br.*,
 f.id as id_facture
   FROM bon_reception br
     JOIN bon_livraison bl ON br.id_bon_livraison = bl.id
     JOIN facture f ON f.id_bon_commande = bl.id_bon_commande
  WHERE f.jour_validation IS NOT NULL;

-- MIALISOA EETAT DE STOCK
create view v_entre_stock_details_jour as select d.*, e.jour 
from  entre_stock e
	join entre_stock_details d 
	on d.id_entre_stock = e.id;

create view v_sortie_stock_details_jour as select s.jour, r.* 
from sortie_stock_repartition r
	join sortie_stock_details d on r.id_sortie_details = d.id
	join sortie_stock s on d.id_sortie_stock = s.id;

create view v_sortie_details_lifofifo as 
select * 
	from v_sortie_stock_details_jour 
	where id_entre_stock is not null;

-- MIALISOA ACCUSE DE RECEPTION
create view v_dispatch_article as
select b.*, id_direction from bon_sortie b
join sortie_stock s on b.id_sortie = s.id
where id_direction is not null

-- MODIFIER VIEW V_DETAILS_FACTURE_HT
create OR REPLACE view v_facture_details_ht as SELECT d.id,
    d.id_facture,
    d.id_article,
    d.qte,
        CASE
            WHEN f.format_prix = 5 THEN round(d.pu::numeric / 1.2, 2)
            ELSE round(d.pu::numeric, 2)
        END AS pu
   FROM facture f
     JOIN facture_details d ON d.id_facture = f.id;