package com.nante.commerce.model.demande;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.model.item.Article;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.MapKeyClass;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "demande_details")
public class DemandeDetails {
    // @JsonIgnore
    @EmbeddedId
    DemandeDetailsID id = new DemandeDetailsID();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_article", insertable = false, updatable = false)
    @MapsId("idDemande")
    Article article;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_demande")
    @MapsId("idDemande")
    Demande demande;
    double quantite;
    int status;
    @Transient
    int idDemande;
    @Transient
    int idArticle;

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) throws Exception {
        if (quantite <= 0) {
            throw new Exception("La quantité soit être strictement positive");
        }
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

    public DemandeDetailsID getId() {
        return id;
    }

    public void setId(DemandeDetailsID id) {
        this.id = id;
    }

    public int getIdDemande() {
        return demande.getId();
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

}
