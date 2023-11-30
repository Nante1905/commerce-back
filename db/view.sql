-- SSCRIPT VUE
create view v_fournisseur_article as 
select a.id id_article, f.* 
    from article a join fournisseur_categorie fc on a.id_categorie = fc.id_categorie 
    join fournisseur f on fc.id_fournisseur = f.id ;

create view v_demande_details as select * from demande d join demande_details dd on d.id = dd.id_demande;

select sum(vdd.quantite) quantite, vdd.id_article from v_demande_details vdd where vdd.status = 5 and vdd.id_demande in :params GROUP BY vdd.id_article;