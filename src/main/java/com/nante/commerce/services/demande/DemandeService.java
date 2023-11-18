package com.nante.commerce.services.demande;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.demande.Demande;
import com.nante.commerce.model.demande.DemandeDetails;
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
        Demande demande = demandeRepo.save(model);
        for (DemandeDetails details : model.getDetails()) {
            details.setDemande(demande);

            // entityManager.persist(details);
            detailsRepo.save(details);
        }
        // return demandeRepo.save(model);
        return model;
    }

    public List<Demande> findOuverts() {
        return this.demandeRepo.findByEstOuvert(true);
    }
}
