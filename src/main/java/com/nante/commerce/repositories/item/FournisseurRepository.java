package com.nante.commerce.repositories.item;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.item.Fournisseur;

public interface FournisseurRepository extends GenericRepository<Fournisseur> {

    @Query(value = "select distinct id, nom, reference, email, telephone from v_fournisseur_article where id_article in :articles ", nativeQuery = true)
    public List<Fournisseur> findForArticles(@Param("articles") List<Integer> articles);
}
