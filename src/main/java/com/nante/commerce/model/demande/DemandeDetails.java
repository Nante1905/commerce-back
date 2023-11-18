package com.nante.commerce.model.demande;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.model.item.Article;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "demande_details")
public class DemandeDetails {
    @JsonIgnore
    @EmbeddedId
    DemandeDetailsID id = new DemandeDetailsID();
    // @ManyToOne(cascade = CascadeType.ALL)
    // @MapsId("idArticle")
    // @JoinColumn(name = "id_article", updatable = false, insertable = false)
    // Article article;

    // @JsonIgnore
    // @ManyToOne(cascade = CascadeType.MERGE)
    // @MapsId("idDemande")
    // @JoinColumn(name = "id_demande", updatable = false, insertable = false)
    // Demande demande;

    // @Id
    // @Column(name = "id_demande", insertable = false, updatable = false)
    // int idDemande;
    // @Id
    // @Column(name = "id_article", insertable = false, updatable = false)
    // int idArticle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_demande")
    @MapsId("idDemande")
    Demande demande;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_article")
    @MapsId("idArticle")
    Article article;

    double quantite;
    int status;

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public Article getArticle() {
        return this.article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    // public DemandeDetailsID getId() {
    // return id;
    // }

    // public void setId(DemandeDetailsID id) {
    // this.id = id;
    // }

}
