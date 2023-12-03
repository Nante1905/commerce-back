package com.nante.commerce.services.item;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.stock.etatStock.ResteEntre;
import com.nante.commerce.types.Gestion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class ArticleService extends GenericService<Article> {
    @PersistenceContext
    EntityManager entityManager;

    public List<ResteEntre> findResteParEntre(String fin, Article article) {
        String queryString = "select id_entree idEntree, reste, pu from f_reste_par_entre(:fin, :article) ";
        if (article.getGestion() == Gestion.FIFO) {
            queryString += "order by jour_entree, id_entree asc ";
        } else if (article.getGestion() == Gestion.LIFO) {
            queryString += "order by jour_entree, id_entree desc ";
        }
        Query query = entityManager.createNativeQuery(
                queryString, "ResteEntre");
        query.setParameter("fin", fin);
        query.setParameter("article", article.getId());

        List<ResteEntre> stocks = query.getResultList();
        return stocks;
    }
}
