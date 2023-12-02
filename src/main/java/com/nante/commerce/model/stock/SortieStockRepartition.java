package com.nante.commerce.model.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.item.Article;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sortie_stock_repartition")
public class SortieStockRepartition extends GenericModel {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_sortie_details")
    SortieStockDetails sortieStockDetails;
    @ManyToOne
    @JoinColumn(name = "id_article")
    Article article;
    String refArticle;
    double puHt;
    double qte;
    int idEntreStock;

    public SortieStockDetails getSortieStockDetails() {
        return sortieStockDetails;
    }

    public void setSortieStockDetails(SortieStockDetails sortieStockDetails) {
        this.sortieStockDetails = sortieStockDetails;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

    public double getPuHt() {
        return puHt;
    }

    public void setPuHt(double puHt) {
        this.puHt = puHt;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public int getIdEntreStock() {
        return idEntreStock;
    }

    public void setIdEntreStock(int idEntreStock) {
        this.idEntreStock = idEntreStock;
    }
}
