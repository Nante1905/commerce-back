package com.nante.commerce.services.bonSortie;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.bonSortie.BonSortie;

@Service
public class BonSortieService extends GenericService<BonSortie> {

    public String generateReferenceBonSortie() {
        long count = this.getRepository().count();
        LocalDate today = LocalDate.now();
        return "BS" + today.getYear() + today.getMonthValue() + today.getDayOfMonth()
                + String.format("%03d", count + 1);
    }
}
