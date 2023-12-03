package com.nante.commerce.model.bonSortie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.stock.SortieStockDetails;
import com.nante.commerce.model.stock.SortieStockRepartition;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bon_sortie_details")
public class BonSortieDetails extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_bon_sortie")
    BonSortie sortie;
    @ManyToOne
    @JoinColumn(name = "id_article")
    Article article;
    double qte;

    public BonSortieDetails() {
    }

    public BonSortieDetails(SortieStockDetails details) {
        setArticle(details.getArticle());
        setQte(details.getQte());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BonSortie getSortie() {
        return sortie;
    }

    public void setSortie(BonSortie sortie) {
        this.sortie = sortie;
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
}
