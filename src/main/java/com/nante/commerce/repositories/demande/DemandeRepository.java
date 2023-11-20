package com.nante.commerce.repositories.demande;

import java.util.List;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.demande.Demande;

public interface DemandeRepository extends GenericRepository<Demande> {
    List<Demande> findByEstOuvert(boolean estOuvert);

    List<Demande> findByDirectionId(int idDirection);

}