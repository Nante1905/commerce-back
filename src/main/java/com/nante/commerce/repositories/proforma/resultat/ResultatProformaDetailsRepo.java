package com.nante.commerce.repositories.proforma.resultat;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.proforma.resultat.ResultatProformaDetails;

public interface ResultatProformaDetailsRepo extends GenericRepository<ResultatProformaDetails> {

    @Query(nativeQuery = true, value = "select id, id_article, id_resultat_proforma, quantite_dispo, pu from v_resultat_proforma_ht_avec_demande where id_demande_proforma = ?1")
    public List<ResultatProformaDetails> findResultatHorsTaxeOfProforma(int id);
}
