package com.nante.commerce.services.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.item.Fournisseur;
import com.nante.commerce.repositories.item.FournisseurRepository;

@Service
public class FournisseurService extends GenericService<Fournisseur> {
    @Autowired
    FournisseurRepository fournisseurRepo;

    public List<Fournisseur> findForArticles(List<Integer> articles) {
        return fournisseurRepo.findForArticles(articles);
    }
}
