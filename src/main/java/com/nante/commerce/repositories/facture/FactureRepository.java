package com.nante.commerce.repositories.facture;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.facture.Facture;

public interface FactureRepository extends GenericRepository<Facture> {
    @Query(nativeQuery = true, value = "select id_facture from v_bon_reception_avec_livraison where id = ?1")
    public Optional<Integer> findFactureOfReception(int id);
}
