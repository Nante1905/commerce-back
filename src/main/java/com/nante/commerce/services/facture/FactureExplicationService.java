package com.nante.commerce.services.facture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.facture.FactureExplication;
import com.nante.commerce.repositories.facture.FactureExplicationRepository;
import com.nante.commerce.services.employe.EmployeService;

import jakarta.security.auth.message.AuthException;
import jakarta.transaction.Transactional;

@Service
public class FactureExplicationService extends GenericService<FactureExplication> {
    @Autowired
    EmployeService empService;
    @Autowired
    FactureExplicationRepository factureExplicationRepo;

    @Transactional
    public FactureExplication save(int idFacture, String text) throws AuthException {
        FactureExplication explication = new FactureExplication(idFacture, text, empService.getAuthenticated());
        return factureExplicationRepo.save(explication);
    }
}
