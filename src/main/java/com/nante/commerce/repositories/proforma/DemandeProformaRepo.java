package com.nante.commerce.repositories.proforma;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.model.proforma.DemandeProforma;

public interface DemandeProformaRepo extends JpaRepository<DemandeProforma, Integer> {
    @Query(nativeQuery = true, value = "select count(*) + 1 from demande_proforma_fournisseur where id_demande_proforma = ?1 and id_fournisseur = ?2")
    public int countDemandeProformaFournisseur(int idProforma, int idFournisseur);
}
