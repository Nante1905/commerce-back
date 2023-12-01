package com.nante.commerce.model.bonCommande;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.item.Article;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bon_commande_details")
public class BonDeCommandeDetails extends GenericModel {
    @ManyToOne
    @JoinColumn(name = "id_article")
    Article article;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_bon_commande")
    BonDeCommande bonDeCommande;
    double quantite;
    double puHt;

    public BonDeCommandeDetails(Article article, double quantite, double puHt) {
        setArticle(article);
        setQuantite(quantite);
        setPuHt(puHt);
    }

    public BonDeCommandeDetails() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public BonDeCommande getBonDeCommande() {
        return bonDeCommande;
    }

    public void setBonDeCommande(BonDeCommande bonDeCommande) {
        this.bonDeCommande = bonDeCommande;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public double getPuHt() {
        return puHt;
    }

    public void setPuHt(double puHt) {
        this.puHt = puHt;
    }

    public double getPuTTC() {
        return this.getPuHt() * 1.22;
    }

    public double getTVA() {
        return this.getPuHt() * 0.2;
    }
}
