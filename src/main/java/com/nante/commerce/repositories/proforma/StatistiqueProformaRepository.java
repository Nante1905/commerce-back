package com.nante.commerce.repositories.proforma;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.proforma.DemandeProforma;
import com.nante.commerce.model.proforma.StatistiqueProforma;

public interface StatistiqueProformaRepository extends GenericRepository<StatistiqueProforma> {

    public Optional<StatistiqueProforma> findById(DemandeProforma id);

    @Query(nativeQuery = true, value = "select * from v_statistique_proforma where reponse > 0")
    public List<StatistiqueProforma> findAllAvecReponse();
}
