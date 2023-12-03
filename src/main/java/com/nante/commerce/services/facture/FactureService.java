package com.nante.commerce.services.facture;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.bonCommande.BonDeCommandeDetails;
import com.nante.commerce.model.facture.Facture;
import com.nante.commerce.model.facture.FactureDetails;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.repositories.facture.FactureDetailsRepository;

import jakarta.transaction.Transactional;

@Service
public class FactureService extends GenericService<Facture> {
    @Autowired
    FactureDetailsRepository factureDetailsRepo;

    @Transactional
    public void valider(int idFacture) {
        factureDetailsRepo.valider(idFacture);
    }

    public void findProblemAvecBonCommande(Facture facture) {
        Map<Article, List<BonDeCommandeDetails>> bonCmdMap = facture.getBonDeCommande().getDetails().stream()
                .collect(Collectors.groupingBy(BonDeCommandeDetails::getArticle));
        ;
        HashMap<Integer, BonDeCommandeDetails> bonCmdDetails = new HashMap<>();
        bonCmdMap.entrySet().forEach(g -> {
            bonCmdDetails.put(g.getKey().getId(), g.getValue().get(0));
        });

        Map<Article, List<FactureDetails>> factureMap = factureDetailsRepo.findInHT(facture.getId()).stream()
                .collect(Collectors.groupingBy(FactureDetails::getArticle));
        ;
        HashMap<Integer, FactureDetails> factureDetails = new HashMap<>();
        factureMap.entrySet().forEach(g -> {
            factureDetails.put(g.getKey().getId(), g.getValue().get(0));
        });

        List<String> errors = new ArrayList<String>();
        for (Map.Entry<Integer, BonDeCommandeDetails> bc : bonCmdDetails.entrySet()) {
            if (factureDetails.get(bc.getKey()) == null) {
                errors.add("L'article " + bc.getValue().getArticle().getDesignation() + " n'est pas dans la facture.");
            } else {
                if (factureDetails.get(bc.getKey()).getPu() != bc.getValue().getPuHt()) {
                    errors.add(" Le prix de " + bc.getValue().getArticle().getDesignation() + " ne correspond pas. ");
                }
                if (factureDetails.get(bc.getKey()).getQte() != bc.getValue().getQuantite()) {
                    errors.add(
                            " La quantité de " + bc.getValue().getArticle().getDesignation() + " ne correspond pas.");
                }
            }
        }

        facture.setProbleme(errors);
    }
}
