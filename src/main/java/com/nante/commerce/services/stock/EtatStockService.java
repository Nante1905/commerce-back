package com.nante.commerce.services.stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.model.stock.etatStock.DetailsEtatStock;
import com.nante.commerce.model.stock.etatStock.EtatStock;
import com.nante.commerce.model.stock.etatStock.EtatStockQte;
import com.nante.commerce.repositories.item.ArticleRepository;
import com.nante.commerce.services.employe.EmployeService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.security.auth.message.AuthException;

@Service
public class EtatStockService {
    @Autowired
    ArticleRepository articleRepo;
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    EmployeService employeService;

    public Object findEtatStock(String debut, String fin, String refArticle) throws AuthException {
        Employe e = employeService.getAuthenticated();
        if (e.getDirection().getCode().equals("FIN")) {
            return findEtatStockAvecMontant(debut, fin, refArticle);
        }
        return findEtatStockQte(debut, fin, refArticle);

    }

    public EtatStock findEtatStockAvecMontant(String debut, String fin, String refArticle) {
        if (refArticle == null || refArticle.trim().equals("")) {
            refArticle = "%";
        }
        EtatStock etatStock = new EtatStock();
        etatStock.setDebut(debut);
        etatStock.setFin(fin);
        List<DetailsEtatStock> stocks = new ArrayList<DetailsEtatStock>();
        stocks = getDetailsStock(debut, fin, refArticle);
        for (DetailsEtatStock s : stocks) {
            s.setArticle(articleRepo.findByReference(s.getRefArticle()).get(0));
        }
        etatStock.setDetails(stocks);
        return etatStock;
    }

    public EtatStockQte findEtatStockQte(String debut, String fin, String refArticle) {
        EtatStock stock = findEtatStockAvecMontant(debut, fin, refArticle);
        return new EtatStockQte(stock);
    }

    private List<DetailsEtatStock> getDetailsStock(String debut, String fin, String refArticle) {
        Query query = entityManager.createNativeQuery("select * from f_etat_stock(:debut, :fin, :article)",
                "DetailsEtatStock");
        query.setParameter("debut", debut);
        query.setParameter("fin", fin);
        query.setParameter("article", refArticle);
        List<DetailsEtatStock> stocks = query.getResultList();
        return stocks;
    }
}
