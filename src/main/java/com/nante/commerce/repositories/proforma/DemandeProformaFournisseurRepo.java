package com.nante.commerce.repositories.proforma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.model.proforma.DemandeProformaFournisseur;
import java.util.List;

public interface DemandeProformaFournisseurRepo extends JpaRepository<DemandeProformaFournisseur, Integer> {
    public List<DemandeProformaFournisseur> findByEtat(int etat);

    @Modifying
    @Query(nativeQuery = true, value = "update demande_proforma_fournisseur set etat = ?1 where id = ?2")
    public void updateEtat(int etat, int id);
}
