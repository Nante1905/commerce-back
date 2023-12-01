-- SSCRIPT VUE
create view v_fournisseur_article as 
select a.id id_article, f.* 
    from article a join fournisseur_categorie fc on a.id_categorie = fc.id_categorie 
    join fournisseur f on fc.id_fournisseur = f.id ;

create view v_demande_details as select * from demande d join demande_details dd on d.id = dd.id_demande;

select sum(vdd.quantite) quantite, vdd.id_article from v_demande_details vdd where vdd.status = 5 and vdd.id_demande in :params GROUP BY vdd.id_article;
create view v_demande_details as 
select * from demande d join demande_details dtl on dtl.id_demande = d.id

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