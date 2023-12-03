package com.nante.commerce.services.bonReception;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.bonReception.BonReception;
import com.nante.commerce.repositories.bonReception.BonReceptionRepo;

@Service
public class BonReceptionService extends GenericService<BonReception> {
    @Autowired
    BonReceptionRepo bonReceptionRepo;

    public List<BonReception> findAllValide() {
        return bonReceptionRepo.findAllValide();
    }
}
