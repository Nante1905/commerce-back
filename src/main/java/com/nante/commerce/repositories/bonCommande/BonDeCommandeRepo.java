package com.nante.commerce.repositories.bonCommande;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.bonCommande.BonDeCommande;
import java.util.List;

public interface BonDeCommandeRepo extends GenericRepository<BonDeCommande> {

    public List<BonDeCommande> findByStatus(int status);

    @Modifying
    @Query(nativeQuery = true, value = "update bon_commande set status = ?1 where id = ?2")
    public void updateStatus(int status, int id);

    @Modifying
    @Query(nativeQuery = true, value = "update bon_commande set status = ?1, id_mode_paiement = ?2 where id = ?3")
    public void updateStatusAndModePaiement(int status, int paiement, int id);

    @Modifying
    @Query(nativeQuery = true, value = "insert into validation_bon_commande(jour, id_bon_commande, id_employe) values (now(), ?1, ?2) ")
    public void insererValidation(int idBonDeCommande, int idEmploye);

}
