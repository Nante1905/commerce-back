package com.nante.commerce.services.accuseReception;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.accuseReception.AccuseReception;
import com.nante.commerce.repositories.accuseReception.AccuseReceptionRepo;
import com.nante.commerce.services.bonSortie.BonSortieService;

import jakarta.transaction.Transactional;

@Service
public class AccuseReceptionService extends GenericService<AccuseReception> {
    @Autowired
    AccuseReceptionRepo accuseReceptionRepo;
    @Autowired
    BonSortieService bonSortieService;

    @Override
    @Transactional
    public AccuseReception save(AccuseReception model) {
        model.setReference(generateReference());
        accuseReceptionRepo.save(model);
        bonSortieService.modifierStatus(5, model.getBonSortie());
        return model;
    }

    private String generateReference() {
        long count = this.accuseReceptionRepo.count();
        LocalDate today = LocalDate.now();
        return "BC" + today.getYear() + today.getMonthValue() + today.getDayOfMonth()
                + String.format("%03d", count + 1);
    }
}
