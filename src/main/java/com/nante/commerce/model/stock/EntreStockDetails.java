package com.nante.commerce.model.stock;

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
public class EntreStockDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "id_entre_stock")
    EntreStock entreStock;
    Article article;
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

}
