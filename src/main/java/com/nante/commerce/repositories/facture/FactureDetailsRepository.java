package com.nante.commerce.repositories.facture;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.facture.FactureDetails;

public interface FactureDetailsRepository extends GenericRepository<FactureDetails> {
    @Query(nativeQuery = true, value = "select * from v_facture_details_ht where id_facture = ?1")
    public List<FactureDetails> findInHT(int idFacture);

    @Modifying
    @Query(nativeQuery = true, value = "update facture set jour_validation = now(), etat = 5 where id = ?1")
    public void valider(int id);
}
