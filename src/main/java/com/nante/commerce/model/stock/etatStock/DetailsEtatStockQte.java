package com.nante.commerce.model.stock.etatStock;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.model.item.Article;

public class DetailsEtatStockQte implements Serializable {
    Article article;
    double qteInitial;
    double qteEntre;
    double qteSortie;

    public DetailsEtatStockQte() {
    }

    public DetailsEtatStockQte(DetailsEtatStock d) {
        setArticle(d.getArticle());
        setQteInitial(d.getQteInitial());
        setQteEntre(d.getQteEntre());
        setQteSortie(d.getQteSortie());
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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
}
