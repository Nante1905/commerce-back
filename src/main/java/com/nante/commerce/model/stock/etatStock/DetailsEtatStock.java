package com.nante.commerce.model.stock.etatStock;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.model.item.Article;

public class DetailsEtatStock implements Serializable {
    Article article;
    @JsonIgnore
    String refArticle;
    double qteInitial;
    double qteEntre;
    double qteSortie;
    double montantInitial;
    double montantEntre;
    double montantSortie;

    public DetailsEtatStock() {
    }

    public DetailsEtatStock(String refArticle, double qteInitial, double montantInitial, double qteEntre,
            double montantEntre, double qteSortie, double montantSortie) {
        setRefArticle(refArticle);
        setQteInitial(qteInitial);
        setMontantInitial(montantInitial);
        setQteEntre(qteEntre);
        setMontantEntre(montantEntre);
        setQteSortie(qteSortie);
        setMontantSortie(montantSortie);
    }

    public double getReste() {
        return (this.getQteEntre() - this.getQteSortie()) + this.getQteInitial();
    }

    public double getMontant() {
        return (this.getMontantEntre() - this.getMontantSortie()) + this.getMontantInitial();
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

    public double getQteInitial() {
        return qteInitial;
    }

    public void setQteInitial(double qteInitial) {
        this.qteInitial = qteInitial;
    }

    public double getQteEntre() {
        return qteEntre;
    }

    public void setQteEntre(double qteEntre) {
        this.qteEntre = qteEntre;
    }

    public double getQteSortie() {
        return qteSortie;
    }

    public void setQteSortie(double qteSortie) {
        this.qteSortie = qteSortie;
    }

    public double getMontantInitial() {
        return montantInitial;
    }

    public void setMontantInitial(double montantInitial) {
        this.montantInitial = montantInitial;
    }

    public double getMontantEntre() {
        return montantEntre;
    }

    public void setMontantEntre(double montantEntre) {
        this.montantEntre = montantEntre;
    }

    public double getMontantSortie() {
        return montantSortie;
    }

    public void setMontantSortie(double montantSortie) {
        this.montantSortie = montantSortie;
    }
}
