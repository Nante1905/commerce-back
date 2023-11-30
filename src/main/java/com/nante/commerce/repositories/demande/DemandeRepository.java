package com.nante.commerce.repositories.demande;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.demande.Demande;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DemandeRepository extends GenericRepository<Demande> {
    List<Demande> findByEtat(int etat);

}
