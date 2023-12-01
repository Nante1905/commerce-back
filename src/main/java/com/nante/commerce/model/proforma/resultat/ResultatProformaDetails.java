package com.nante.commerce.model.proforma.resultat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.item.Fournisseur;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "resultat_proforma_details")
public class ResultatProformaDetails extends GenericModel {
    @ManyToOne
    @JoinColumn(name = "id_article")
    Article article;
    double quantiteDispo;
    double pu;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_resultat_proforma")
    ResultatProforma proforma;

    public Fournisseur getFournisseur() {
        return this.getProforma().getProforma().getFournisseur();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getQuantiteDispo() {
        return quantiteDispo;
    }

    public void setQuantiteDispo(double quantiteDispo) {
        this.quantiteDispo = quantiteDispo;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public ResultatProforma getProforma() {
        return proforma;
    }

    public void setProforma(ResultatProforma proforma) {
        this.proforma = proforma;
    }

}
