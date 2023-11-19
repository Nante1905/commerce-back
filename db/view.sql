-- SSCRIPT VUE
create view v_fournisseur_article as 
select a.id id_article, f.* 
    from article a join fournisseur_categorie fc on a.id_categorie = fc.id_categorie 
    join fournisseur f on fc.id_fournisseur = f.id ;

create view v_demande_details as 
select * from demande d join demande_details dtl on dtl.id_demande = d.id