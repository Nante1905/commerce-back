package com.nante.commerce.services.stock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.bonSortie.BonSortie;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.stock.SortieStock;
import com.nante.commerce.model.stock.SortieStockDetails;
import com.nante.commerce.model.stock.SortieStockRepartition;
import com.nante.commerce.model.stock.etatStock.ResteEntre;
import com.nante.commerce.repositories.item.ArticleRepository;
import com.nante.commerce.repositories.stock.SortieStockRepository;
import com.nante.commerce.services.bonSortie.BonSortieService;
import com.nante.commerce.services.item.ArticleService;

import jakarta.transaction.Transactional;

@Service
public class SortieStockService extends GenericService<SortieStock> {
    @Autowired
    SortieStockRepository sortieStockRepo;
    @Autowired
    ArticleService articleService;
    @Autowired
    BonSortieService bonSortieService;

    @Transactional
    public SortieStock saisie(SortieStock sortieStock) throws Exception {
        double reste = 0;

        for (SortieStockDetails details : sortieStock.getDetails()) {
            details.setArticle(articleService.find(details.getArticle().getId()));
            // Vérifier stock
            reste = sortieStockRepo.getResteStock(sortieStock.getJour().toString(), details.getArticle().getReference())
                    .orElseThrow(() -> new Exception("Stock insuffisant"));
            if (details.getQte() > reste) {
                throw new Exception(
                        "Stock insuffisant pour " + details.getArticle().getDesignation() + ". Il reste " + reste
                                + " unité ");
            }

            details.setSortieStock(sortieStock);
            // décomposer la sortie
            details.setRepartition(decomposer(details));
        }
        // créer bon de sortie
        BonSortie bonSortie = new BonSortie();
        bonSortie.setJour(sortieStock.getJour());
        bonSortie.setReference(bonSortieService.generateReferenceBonSortie());
        bonSortie.setSortie(sortieStock);
        bonSortie.setDetailsFromStock(sortieStock.getDetails());

        sortieStockRepo.save(sortieStock);
        // insérer bon sortie
        bonSortieService.save(bonSortie);
        return sortieStock;
    }

    private List<SortieStockRepartition> decomposer(SortieStockDetails details) throws Exception {
        String jour = details.getSortieStock().getJour().toString();
        System.out.println("ilay ho zaraina " + details.getQte());

        // Si CUMP
        List<SortieStockRepartition> repartitions = new ArrayList<SortieStockRepartition>();
        if (details.getArticle().getGestion() == 10) {
            System.out.println("repartition " + details.getQte());

            double pu = sortieStockRepo.getCUMP(jour, details.getArticle().getId())
                    .orElseThrow(() -> new Exception("Stock insuffisant à " + jour + " ou article inexistant "));
            repartitions.add(new SortieStockRepartition(details, details.getArticle(),
                    details.getArticle().getReference(), pu, details.getQte()));
        } else {
            List<ResteEntre> stocks = articleService.findResteParEntre(jour,
                    details.getArticle());
            double soustraire = 0;
            double qteInitial = details.getQte();
            for (int i = 0; (i < stocks.size() && qteInitial > 0); i++) {
                soustraire = 0;
                if (stocks.get(i).getReste() == 0) {
                    continue;
                }
                if (qteInitial > stocks.get(i).getReste()) {
                    soustraire = stocks.get(i).getReste();
                } else {
                    soustraire = qteInitial;
                }
                qteInitial = qteInitial - soustraire;
                System.out.println("repartition " + soustraire);
                repartitions.add(new SortieStockRepartition(details, details.getArticle(),
                        details.getArticle().getReference(), stocks.get(i).getPu(), soustraire,
                        stocks.get(i).getIdEntree()));
            }

        }

        return repartitions;
    }

}
