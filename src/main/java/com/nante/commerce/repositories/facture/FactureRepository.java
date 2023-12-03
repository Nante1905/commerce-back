package com.nante.commerce.repositories.facture;

import org.springframework.stereotype.Repository;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.facture.Facture;

@Repository
public interface FactureRepository extends GenericRepository<Facture>{
    
}
