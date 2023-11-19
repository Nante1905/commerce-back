package com.nante.commerce.model.demande;

import java.util.ArrayList;
import java.util.List;

import com.nante.commerce.model.item.Article;

public class DemandeParNature {
    Article article;
    List<DemandeParNatureDetails> details;
    double total;

    public DemandeParNature() {
        this.details = new ArrayList<DemandeParNatureDetails>();
    }

    public void init() {
        setArticle(null);
        setDetails(new ArrayList<DemandeParNatureDetails>());
    }

    public void addDetails(DemandeParNatureDetails d) {
        details.add(d);
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<DemandeParNatureDetails> getDetails() {
        return details;
    }

    public void setDetails(List<DemandeParNatureDetails> details) {
        this.details = details;
    }

    public double getTotal() {
        double total = 0;
        for (DemandeParNatureDetails d : details) {
            total += d.getQuantite();
        }
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
