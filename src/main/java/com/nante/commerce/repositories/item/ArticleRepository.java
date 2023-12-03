package com.nante.commerce.repositories.item;

import java.util.List;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.item.Article;

import jakarta.persistence.NamedNativeQuery;
import com.nante.commerce.model.stock.etatStock.DetailsEtatStock;

@NamedNativeQuery(name = "getEtatStock", resultClass = DetailsEtatStock.class, resultSetMapping = "DetailsEtatStock", query = "select * from f_etat_stock(?1, ?2, ?3)")
public interface ArticleRepository extends GenericRepository<Article> {

    Article findByDesignation(String designation);

    public List<Article> findByReference(String reference);
}
