package com.nante.commerce.services.demande;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.demande.Demande;
import com.nante.commerce.model.demande.DemandeDetails;
import com.nante.commerce.model.demande.DemandeDetailsID;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.repositories.demande.DemandeDetailsRepository;
import com.nante.commerce.repositories.demande.DemandeRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class DemandeService extends GenericService<Demande> {
    @Autowired
    DemandeRepository demandeRepo;
    @Autowired
    EntityManager entityManager;
    @Autowired
    DemandeDetailsRepository detailsRepo;

    @Override
    @Transactional
    public Demande save(Demande model) {
        model.setJour(LocalDate.now());
        model.setEstOuvert(true);
        model.setReference("D2023/11/0003");

        for (DemandeDetails details : model.getDetails()) {
            details.setDemande(model);
            details.setId(
                    new DemandeDetailsID(details.getIdArticle()));
            // entityManager.persist(details);
            // detailsRepo.save(details);
        }
        System.out.println("INSERRRRRT >>>>>>>>>>");
        // entityManager.persist(model);
        return demandeRepo.save(model);
        // return model;
    }

    public List<Demande> findOuverts() {
        return this.demandeRepo.findByEstOuvert(true);
    }
}
