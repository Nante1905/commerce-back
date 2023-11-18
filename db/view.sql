-- SSCRIPT VUE
create view v_fournisseur_article as 
select a.id id_article, f.* 
    from article a join fournisseur_categorie fc on a.id_categorie = fc.id_categorie 
    join fournisseur f on fc.id_fournisseur = f.id ;