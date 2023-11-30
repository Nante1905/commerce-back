package com.nante.commerce.services.demande;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.demande.Demande;
import com.nante.commerce.model.demande.DemandeDetails;
import com.nante.commerce.model.demande.DemandeDetailsID;
import com.nante.commerce.model.demande.DemandeParDetails;
import com.nante.commerce.model.demande.DemandeParNature;
import com.nante.commerce.model.demande.DemandeParNatureDetails;
import com.nante.commerce.model.demande.SelectionDetailsDemande;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.repositories.demande.DemandeDetailsRepository;
import com.nante.commerce.repositories.demande.DemandeParDetailsRepository;
import com.nante.commerce.repositories.demande.DemandeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;

@Service
public class DemandeService extends GenericService<Demande> {
    @Autowired
    DemandeRepository demandeRepo;
    @Autowired
    DemandeParDetailsRepository demandeParDetailsRepo;
    @Autowired
    DemandeDetailsRepository detailsRepository;
    @PersistenceContext
    EntityManager entityManager;

    public Map<?, ?> findByNature() {
        Demande demande = demandeRepo.findById(1).get();
        return demande.getDetails().stream().collect(Collectors.groupingBy(DemandeDetails::getArticle));
    }

    @Override
    @Transactional
    public Demande save(Demande model) {
        model.setJour(LocalDate.now());
        model.setReference(generateReference());

        for (DemandeDetails details : model.getDetails()) {
            details.setDemande(model);
            details.setId(
                    new DemandeDetailsID(details.getIdArticle()));
        }

        return demandeRepo.save(model);
    }

    public String generateReference() {
        long count = this.demandeRepo.count();
        LocalDate today = LocalDate.now();
        return "D" + today.getYear() + today.getMonthValue() + String.format("%04d", count + 1);
    }

    public List<Demande> findOuverts() {
        return this.demandeRepo.findByEtat(0);
    }

    // Zay demande mbola ouvert et article mbola tsy nandalo validation
    public List<DemandeParNature> findAllDemandeOuvertParDetails() {
        List<DemandeParDetails> demandes = this.demandeParDetailsRepo.findByEtatDemandeAndStatus(0, 0);
        return groupDemandeParNature(demandes);
    }

    public List<DemandeParNature> findAllDemandeValideParDetails() {
        List<DemandeParDetails> demandes = this.demandeParDetailsRepo.findByEtatDemandeAndStatus(5, 5);
        return groupDemandeParNature(demandes);
    }

    // Zay demande mbola ouvert et article mbola tsy nandalo validation d'une
    // direction
    public List<DemandeParNature> findAllDemandeOuvertParDetailsParDirection(int idDirection) {

        List<DemandeParDetails> demandes = this.demandeParDetailsRepo.findByEtatDemandeAndStatusAndDirection_id(0, 0,
                idDirection);
        return groupDemandeParNature(demandes);
    }

    @Transactional
    public void validerDemande(List<SelectionDetailsDemande> selected, List<SelectionDetailsDemande> rejected) {
        Set<Integer> demandes = new HashSet<Integer>();
        List<List<Integer>> selectedList = new ArrayList<>();
        for (SelectionDetailsDemande s : selected) {
            selectedList.add(new ArrayList<Integer>(2) {
                {
                    add(s.getArticle());
                    add(s.getDemande());
                }
            });
            demandes.add(s.getDemande());
        }

        List<List<Integer>> rejectedList = new ArrayList<>();
        for (SelectionDetailsDemande s : rejected) {
            rejectedList.add(new ArrayList<Integer>(2) {
                {
                    add(s.getArticle());
                    add(s.getDemande());
                }
            });
            demandes.add(s.getDemande());

        }
        String formattedSelected = selectedList.toString().replace("[", "(").replace("]", ")");
        String formattedRejected = rejectedList.toString().replace("[", "(").replace("]", ")");
        String formattedDemandes = demandes.toString().replace("[", "(").replace("]", ")");

        if (formattedSelected.equals("()") == false) {
            entityManager.createNativeQuery(
                    "update demande_details set status = 5 where (id_article, id_demande) in " + formattedSelected,
                    int.class).executeUpdate();
        }
        if (formattedRejected.equals("()") == false) {
            entityManager.createNativeQuery(
                    "update demande_details set status = -5 where (id_article, id_demande) in " + formattedRejected,
                    int.class).executeUpdate();
        }

        entityManager.createNativeQuery(
                "update demande set etat = 5 where (id) in " + formattedDemandes,
                int.class).executeUpdate();
    }

    public List<DemandeParNature> groupDemandeParNature(List<DemandeParDetails> demandes) {
        Map<Article, List<DemandeParDetails>> group = demandes.stream()
                .collect(Collectors.groupingBy(DemandeParDetails::getArticle));
        List<DemandeParNature> demandesParNatures = new ArrayList<DemandeParNature>();
        DemandeParNature nature = new DemandeParNature();

        for (Article article : group.keySet()) {
            System.out.println(article.getDesignation());

            nature = new DemandeParNature();
            nature.setArticle(article);
            for (DemandeParDetails details : group.get(article)) {
                nature.addDetails(new DemandeParNatureDetails(details));
            }
            demandesParNatures.add(nature);
        }

        return demandesParNatures;
    }
}
