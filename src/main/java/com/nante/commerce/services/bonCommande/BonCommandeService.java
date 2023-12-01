package com.nante.commerce.services.bonCommande;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.bonCommande.BonDeCommande;
import com.nante.commerce.model.bonCommande.BonDeCommandeDetails;
import com.nante.commerce.model.demande.DemandeParDetails;
import com.nante.commerce.model.demande.DemandeParNature;
import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.item.Fournisseur;
import com.nante.commerce.model.proforma.resultat.ResultatProformaDetails;
import com.nante.commerce.repositories.bonCommande.BonDeCommandeRepo;
import com.nante.commerce.repositories.proforma.resultat.ResultatProformaDetailsRepo;
import com.nante.commerce.services.authentication.AuthenticationService;
import com.nante.commerce.services.demande.DemandeService;
import com.nante.commerce.services.employe.EmployeService;

import jakarta.security.auth.message.AuthException;
import jakarta.transaction.Transactional;

@Service
public class BonCommandeService extends GenericService<BonDeCommande> {
    @Autowired
    BonDeCommandeRepo bonDeCommandeRepo;
    @Autowired
    DemandeService demandeService;
    @Autowired
    ResultatProformaDetailsRepo resultatProformaDetailsRepo;
    @Autowired
    EmployeService empService;

    public List<BonDeCommande> genererBonDeCommande(int idProforma) {
        // Demandes an'ilay proforma groupé par nature
        List<DemandeParNature> demandesParNatures = demandeService.findDemandeParNatureDeProforma(idProforma);

        // Résultat an'io proforma io groupé par nature
        List<ResultatProformaDetails> resultats = this.resultatProformaDetailsRepo
                .findResultatHorsTaxeOfProforma(idProforma);
        Map<Article, List<ResultatProformaDetails>> group = resultats.stream()
                .collect(Collectors.groupingBy(ResultatProformaDetails::getArticle));

        HashMap<String, List<ResultatProformaDetails>> resultatsParNature = new HashMap<>();
        group.entrySet().forEach(g -> {
            g.getValue().sort(Comparator.comparing(ResultatProformaDetails::getPu));
            resultatsParNature.put(g.getKey().getDesignation(), g.getValue());
        });

        HashMap<Fournisseur, BonDeCommande> bonDeCommandes = new HashMap<>();
        double qtePrendre = 0;
        double pu = 0;
        double qteInitial = 0;

        for (DemandeParNature demande : demandesParNatures) {
            System.out.println(demande.getArticle().getId() + " " + demande.getTotal());
            qteInitial = demande.getTotal();
            List<ResultatProformaDetails> reponse = resultatsParNature.get(demande.getArticle().getDesignation());
            for (int i = 0; (i < reponse.size() && qteInitial > 0); i++) {
                Fournisseur fournisseur = reponse.get(i).getFournisseur();
                if (qteInitial <= reponse.get(i).getQuantiteDispo()) {
                    qtePrendre = qteInitial;
                } else {
                    qtePrendre = reponse.get(i).getQuantiteDispo();
                }
                pu = reponse.get(i).getPu();
                BonDeCommandeDetails detailsCommande = new BonDeCommandeDetails(demande.getArticle(), qtePrendre, pu);
                detailsCommande.setBonDeCommande(bonDeCommandes.get(fournisseur));
                if (bonDeCommandes.get(fournisseur) == null) {
                    System.out.println(
                            "vao ampiditra " + detailsCommande.getQuantite() + " an'i " + fournisseur.getNom());
                    BonDeCommande bonDeCommande = new BonDeCommande();
                    bonDeCommande.setDateCreation(LocalDate.now());
                    bonDeCommande.setDelaiLivraison(reponse.get(i).getProforma().getDelaiLivraison());
                    // bonDeCommande.setPaiement(null);
                    bonDeCommande.setFournisseur(fournisseur);
                    // bonDeCommande.setLivraisonPartielle(false);
                    detailsCommande.setBonDeCommande(bonDeCommande);
                    bonDeCommande.addDetails(detailsCommande);

                    bonDeCommandes.put(fournisseur, bonDeCommande);
                } else {
                    System.out
                            .println("efa manampy " + detailsCommande.getQuantite() + " an'i " + fournisseur.getNom());
                    bonDeCommandes.get(fournisseur).addDetails(detailsCommande);
                }
                qteInitial -= qtePrendre;
            }
        }

        List<BonDeCommande> reponses = new ArrayList<>();
        bonDeCommandes.entrySet().forEach(b -> {
            reponses.add(b.getValue());
        });

        for (BonDeCommande b : reponses) {
            b.setIdDemandeProforma(idProforma);
            b.setReference(generateReference());
            bonDeCommandeRepo.save(b);
        }

        return reponses;
    }

    private String generateReference() {
        long count = this.bonDeCommandeRepo.count();
        LocalDate today = LocalDate.now();
        return "BC" + today.getYear() + today.getMonthValue() + today.getDayOfMonth()
                + String.format("%03d", count + 1);
    }

    @Transactional
    public void valider(int id) throws AuthException {
        Employe e = empService.getAuthenticated();
        bonDeCommandeRepo.insererValidation(id, e.getId());
        bonDeCommandeRepo.updateStatus(5, id);
    }
}
