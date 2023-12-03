package com.nante.commerce.repositories.stock;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.nante.commerce.crud.repository.GenericRepository;
import com.nante.commerce.model.stock.SortieStock;

public interface SortieStockRepository extends GenericRepository<SortieStock> {
    @Query(nativeQuery = true, value = "select qte_initial from f_qte_initial(?1, ?2, true)")
    public Optional<Double> getResteStock(String jour, String refArticle);

    @Query(nativeQuery = true, value = "select (montant_entre - montant_sortie)/(qte_entre - qte_sortie) cump from f_calcul_cump(?1, ?2)")
    public Optional<Double> getCUMP(String jour, int idArticle);
}
