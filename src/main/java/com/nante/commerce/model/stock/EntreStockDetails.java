package com.nante.commerce.model.stock;

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
@Table(name = "entre_stock_details")
public class EntreStockDetails extends GenericModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_entre_stock")
    EntreStock entreStock;
    @ManyToOne
    @JoinColumn(name = "id_article")
    Article article;
    String refArticle;
    double pu_ht;
    double qte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntreStock getEntreStock() {
        return entreStock;
    }

    public void setEntreStock(EntreStock entreStock) {
        this.entreStock = entreStock;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public double getPu_ht() {
        return pu_ht;
    }

    public void setPu_ht(double pu_ht) {
        this.pu_ht = pu_ht;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public String getRefArticle() {
        return refArticle;
    }

    public void setRefArticle(String refArticle) {
        this.refArticle = refArticle;
    }

}
