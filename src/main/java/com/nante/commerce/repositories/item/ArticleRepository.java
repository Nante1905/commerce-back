package com.nante.commerce.repositories.item;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.stock.etatStock.ResteEntre;

import jakarta.persistence.NamedNativeQuery;

// @NamedNativeQuery(name = "getResteParEntre", resultClass = ResteEntre.class, resultSetMapping = "resteEntre", query = )
public interface ArticleRepository extends GenericRepository<Article> {

    Article findByDesignation(String designation);

}
