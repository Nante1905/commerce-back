package com.nante.commerce.model.bonLivraison;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.item.Article;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bon_livraison_details")
public class BonDeLivraisonDetails extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "id_article")
    Article article;
    double qte;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_bon_livraison")
    BonDeLivraison livraison;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public BonDeLivraison getLivraison() {
        return livraison;
    }

    public void setLivraison(BonDeLivraison livraison) {
        this.livraison = livraison;
    }
}
