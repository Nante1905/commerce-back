package com.nante.commerce.repositories.proforma;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nante.commerce.model.proforma.DemandeProformaFournisseur;
import java.util.List;

public interface DemandeProformaFournisseurRepo extends JpaRepository<DemandeProformaFournisseur, Integer> {
    public List<DemandeProformaFournisseur> findByEtat(int etat);
}
