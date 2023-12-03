package com.nante.commerce.services.stock;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.bonEntre.BonEntre;
import com.nante.commerce.model.bonEntre.BonEntreDetails;
import com.nante.commerce.model.facture.Facture;
import com.nante.commerce.model.facture.FactureDetails;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.stock.EntreStock;
import com.nante.commerce.model.stock.EntreStockDetails;
import com.nante.commerce.repositories.bonEntre.BonEntreRepository;
import com.nante.commerce.repositories.facture.FactureDetailsRepository;
import com.nante.commerce.repositories.facture.FactureRepository;
import com.nante.commerce.services.bonEntre.BonEntreService;

import jakarta.transaction.Transactional;

@Service
public class EntreStockService extends GenericService<EntreStock> {
    @Autowired
    FactureRepository factureRepository;
    @Autowired
    FactureDetailsRepository factureDetailsRepo;
    @Autowired
    BonEntreService bonEntreService;
    @Autowired
    BonEntreRepository bonEntreRepo;

    @Transactional
    public void inserer(EntreStock entreStock) throws Exception {
        int idFacture = factureRepository.findFactureOfReception(entreStock.getBonReception().getId())
                .orElseThrow(() -> new Exception("Aucune facture n'est associé au bon de réception"));

        Map<Article, List<FactureDetails>> detailsFactureHTMap = factureDetailsRepo.findInHT(idFacture).stream()
                .collect(Collectors.groupingBy(FactureDetails::getArticle));

        HashMap<Integer, FactureDetails> detailsFacture = new HashMap<>();
        detailsFactureHTMap.entrySet().forEach(g -> {
            detailsFacture.put(g.getKey().getId(), g.getValue().get(0));
        });

        BonEntre bonEntre = new BonEntre();
        bonEntre.setReference(generateReferenceBonEntre());
        bonEntre.setJour(LocalDate.now());
        bonEntre.setEntreStock(entreStock);

        for (EntreStockDetails e : entreStock.getDetails()) {
            if (detailsFacture.get(e.getArticle().getId()) == null) {
                throw new Exception("Article absent sur le facture, donc prix méconnaissable");
            }
            e.setPu_ht(detailsFacture.get(e.getArticle().getId()).getPu());
            e.setRefArticle(detailsFacture.get(e.getArticle().getId()).getArticle().getReference());
            BonEntreDetails details = new BonEntreDetails(bonEntre, e.getQte(), e.getArticle());
            bonEntre.addDetails(details);
        }
        this.save(entreStock);
        bonEntreService.save(bonEntre);
    }

    private String generateReferenceBonEntre() {
        long count = this.bonEntreRepo.count();
        LocalDate today = LocalDate.now();
        return "BE" + today.getYear() + today.getMonthValue() + today.getDayOfMonth()
                + String.format("%04d", count + 1);
    }
}
