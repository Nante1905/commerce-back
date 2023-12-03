package com.nante.commerce.model.stock;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.item.Article;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "sortie_stock_details")
public class SortieStockDetails extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_sortie_stock")
    SortieStock sortieStock;
    @ManyToOne
    @JoinColumn(name = "id_article")
    Article article;
    double qte;
    @OneToMany(mappedBy = "sortieStockDetails", cascade = CascadeType.PERSIST)
    List<SortieStockRepartition> repartition;

    @PrePersist
    public void prePersist() {
        if (getRepartition() != null && getRepartition().size() > 0) {
            for (SortieStockRepartition r : repartition) {
                r.setSortieStockDetails(this);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SortieStock getSortieStock() {
        return sortieStock;
    }

    public void setSortieStock(SortieStock sortieStock) {
        this.sortieStock = sortieStock;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public List<SortieStockRepartition> getRepartition() {
        return repartition;
    }

    public void setRepartition(List<SortieStockRepartition> repartition) {
        this.repartition = repartition;
    }
}
