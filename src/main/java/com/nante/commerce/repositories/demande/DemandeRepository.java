package com.nante.commerce.repositories.demande;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.demande.Demande;

public interface DemandeRepository extends GenericRepository<Demande> {

    List<Demande> findByDirectionId(int idDirection);

    @Query(value = "select sum(vdd.quantite) quantite, vdd.id_article from v_demande_details vdd where vdd.status = 5 and vdd.id_demande in :demandeIds GROUP BY vdd.id_article", nativeQuery = true)
    List<Object[]> findArticlesWithQteOf(List<Integer> demandeIds);

    List<Demande> findByEtat(int etat);

    @Modifying
    @Query(nativeQuery = true, value = "update demande set etat = 10 where id in (select id_demande from proforma_besoin where id_proforma = ?1)")
    public void fermerDemande(int id);

}
