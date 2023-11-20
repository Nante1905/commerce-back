package com.nante.commerce.services.demande;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.demande.Demande;
import com.nante.commerce.model.demande.DemandeDetails;
import com.nante.commerce.model.demande.DemandeDetailsID;
import com.nante.commerce.repositories.demande.DemandeRepository;

import jakarta.transaction.Transactional;

@Service
public class DemandeService extends GenericService<Demande> {
    @Autowired
    DemandeRepository demandeRepo;

    @Override
    @Transactional
    public Demande save(Demande model) {
        model.setJour(LocalDate.now());
        model.setEstOuvert(true);
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
        return this.demandeRepo.findByEstOuvert(true);
    }

    public List<Demande> findByDirectionId(int idDirection) {
        return this.demandeRepo.findByDirectionId(idDirection);
    }
}
