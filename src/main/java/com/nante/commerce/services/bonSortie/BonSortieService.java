package com.nante.commerce.services.bonSortie;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.bonSortie.BonSortie;
import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.repositories.bonSortie.BonSortieRepository;
import com.nante.commerce.services.employe.EmployeService;

import jakarta.security.auth.message.AuthException;

@Service
public class BonSortieService extends GenericService<BonSortie> {
    @Autowired
    BonSortieRepository bonSortieRepo;
    @Autowired
    EmployeService empService;

    public List<BonSortie> getDispatch() throws AuthException {
        Employe e = empService.getAuthenticated();
        return bonSortieRepo.getBonSortiePourDirection(e.getDirection().getId());
    }

    public String generateReferenceBonSortie() {
        long count = this.getRepository().count();
        LocalDate today = LocalDate.now();
        return "BS" + today.getYear() + today.getMonthValue() + today.getDayOfMonth()
                + String.format("%03d", count + 1);
    }

    public void modifierStatus(int status, BonSortie bonSortie) {
        this.bonSortieRepo.updateStatus(status, bonSortie.getId());
    }
}
