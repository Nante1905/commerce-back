-- debut, fin, article,compris ve lay borne de date
create function f_entree(debut varchar, fin varchar, ref_article varchar, inclusive boolean) RETURNS TABLE (art_entre varchar, qte_entre double precision, montant_entre double precision) as 
$$  
    select ref_article as art_entre, sum(qte)qte_entre, sum(pu_ht*qte)montant_entre from v_entre_stock_details_jour where ref_article like $3 and 
    case 
        when $1 is null and  $4 = true then jour <= $2::date 
        when $1 is null and $4 = false then jour < $2::date
        when $2 is null then jour >= $1::date
        else jour >= $1::date and jour <= $2::date
    end
    group by id_article, ref_article
$$
    LANGUAGE SQL;


-- ITO ILAY MILA OVAINA V8_stoc_sortie_valide
-- debut, fin, article,magasin, compris ve lay debut
create function f_sortie(debut varchar,fin varchar,ref_article varchar, inclusive boolean) RETURNS TABLE (art_sortie varchar, qte_sortie double precision, montant_sortie double precision) as 
$$  
    select ref_article as art_sortie, sum(qte) qte_sortie, sum(pu_ht*qte) montant_sortie from v_sortie_stock_details_jour where ref_article like $3 and 
    case 
        when $1 is null and  $4 = true then jour <= $2::date 
        when $1 is null and $4 = false then jour < $2::date
        when $2 is null then jour >= $1::date
        else jour >= $1::date and jour <= $2::date
    end
    group by id_article, ref_article 
$$
    LANGUAGE SQL;

-- fin, article, compris ve lay debut
-- ref qte initial d false lay compris, ref reste am sortie stock d true
create or replace function f_qte_initial(fin varchar, article varchar, inclusive boolean) returns table (ref_article varchar, qte_initial double precision, montant_initial double precision) as 
$$
BEGIN
    if ($1 is null) then 
        return query select a.reference ref_article,  0::double precision qte_initial, 0::double precision montant_initial from article a where a.reference like $2 ;
    else 
        return query select tmp.ref_article, sum(qte) qte_initial, sum(montant) montant_initial from (
            select art_entre as ref_article, qte_entre as qte, montant_entre as montant from f_entree(null, $1, $2, $3) 
            union 
            select art_sortie as ref_article, -qte_sortie as qte, -montant_sortie as montant from f_sortie(null, $1, $2, $3) 
        ) tmp group by tmp.ref_article;
    end if;
END;
$$
    LANGUAGE plpgsql;


-- create function f_qte_initial(varchar, varchar, varchar, boolean) returns table (ref_article varchar, ref_magasin varchar, qte_initial double precision, montant_initial double precision) as 
-- $$
    
--     select ref_article, ref_magasin, sum(qte) qte_initial, sum(montant) montant_initial from (
--         select art_entre as ref_article, mag_entre as ref_magasin, qte_entre as qte, montant_entre as montant from f_entree(null, $1, $2, $3, $4) 
--         union 
--         select art_sortie as ref_article, mag_sortie as ref_magasin, -qte_sortie as qte, -montant_sortie as montant from f_sortie(null, $1, $2, $3, $4) 
--     ) tmp group by ref_article, ref_magasin
-- $$
--     LANGUAGE plpsql;    

-- alaina aloha qte initial avant date_debut (pas compris donc inclusive = false)
-- d join am entree sy sortie entre an'ilay intervalle (borne compris donc inclusive = true)
create function f_etat_stock(debut varchar, fin varchar, article varchar) returns table (refarticle varchar, qteinitial double precision, montantinitial double precision, qteentre double precision, montantentre double precision, qtesortie double precision, montantsortie double precision) as 
$$
    select coalesce(initial.ref_article, mvt.ref_article) as refarticle, coalesce(initial.qte_initial, 0) as qteinitial, coalesce(initial.montant_initial, 0) as montantinitial, coalesce(mvt.qte_entre, 0) qteentre, coalesce(mvt.montant_entre, 0) montantentre, coalesce(mvt.qte_sortie, 0) qtesortie, coalesce(mvt.montant_sortie, 0) montantsortie 
        from f_qte_initial($1, $3, false) initial
full join (
    select coalesce(art_entre, art_sortie) ref_article, coalesce(qte_entre, 0) qte_entre, coalesce(montant_entre, 0) montant_entre , coalesce(qte_sortie, 0) qte_sortie , coalesce(montant_sortie, 0) montant_sortie from
    (
        select * from f_entree($1, $2, $3, true) e 
    ) e
    full join (
        select * from f_sortie($1, $2, $3, true) 
    ) s on e.art_entre = s.art_sortie
) mvt on initial.ref_article = mvt.ref_article 
$$
    LANGUAGE SQL;


create function f_reste_par_entre(jour varchar, article integer) returns table  (id_entree integer, jour_entree date, ref_article varchar, qte_entre double precision, qte_sortie double precision, reste double precision, pu double precision, gestion integer) as
$$
    select coalesce(s.id_entre, e.id) id_entre, e.jour jour_entre, ref_article, coalesce(e.qte, 0::double precision) qte_entre, coalesce(s.qte_sortie, 0::double precision) qte_sortie , coalesce(e.qte - qte_sortie, e.qte) reste, e.pu_ht, a.gestion  
    from (select id_entre_stock id_entre, sum(details.qte) qte_sortie 
        from v_sortie_details_lifofifo details   
            where details.jour <= $1::date 
            group by id_entre) as s 
        full join v_entre_stock_details_jour e on s.id_entre = e.id
	    join article a on e.id_article = a.id
    where e.jour <= $1::date and id_article = $2
$$
    LANGUAGE SQL;

create function f_calcul_cump(jour varchar, article integer) returns table  (id_article integer, qte_entre double precision, montant_entre double precision, qte_sortie double precision, montant_sortie double precision) as
$$
    select entre.*, coalesce(qte_sortie, 0) qte_sortie, coalesce(montant_sortie, 0) montant_sortie
    from (
        select id_article, sum(qte) qte_entre, sum(qte * pu_ht) montant_entre
            from v_entre_stock_details_jour
            where jour <= $1::date and id_article = $2
            group by id_article
        ) entre
    full join (
        select id_article, sum(qte) qte_sortie, sum(qte * pu_ht) montant_sortie
            from v_sortie_stock_details_jour
            where jour <= $1::date and id_article = $2
            group by id_article
    ) sortie
    on entre.id_article = sortie.id_article
$$
    LANGUAGE SQL;