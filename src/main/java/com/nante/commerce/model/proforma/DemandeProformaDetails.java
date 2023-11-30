package com.nante.commerce.model.proforma;

import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.proforma.id.DemandeProformaDetailsId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(DemandeProformaDetailsId.class)
public class DemandeProformaDetails {
    @Id
    int idArticle;
    @Id
    int idDemandeProforma;

    double quantite;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_article", insertable = false, updatable = false)
    Article article;

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getIdDemandeProforma() {
        return idDemandeProforma;
    }

    public void setIdDemandeProforma(int idDemandeProforma) {
        this.idDemandeProforma = idDemandeProforma;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
