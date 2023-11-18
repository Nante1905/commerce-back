package com.nante.commerce.repositories.demande;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.demande.Demande;
import java.util.List;

public interface DemandeRepository extends GenericRepository<Demande> {
    List<Demande> findByEstOuvert(boolean estOuvert);
}
