package com.nante.commerce.model.bonEntre;

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
@Table(name = "bon_entre_details")
public class BonEntreDetails extends GenericModel {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_bon_entre")
    BonEntre bonEntre;
    double qte;
    @ManyToOne
    @JoinColumn(name = "id_article")
    Article article;

    public BonEntre getBonEntre() {
        return bonEntre;
    }

    public void setBonEntre(BonEntre bonEntre) {
        this.bonEntre = bonEntre;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
