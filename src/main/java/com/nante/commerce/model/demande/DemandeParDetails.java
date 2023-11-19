package com.nante.commerce.model.demande;

import java.time.LocalDate;

import com.nante.commerce.model.employe.Direction;
import com.nante.commerce.model.item.Article;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_demande_details")
public class DemandeParDetails {
    @EmbeddedId
    DemandeDetailsID identifiant;
    int id;
    String reference;
    LocalDate jour;
    boolean estOuvert;
    double quantite;
    int status;
    // @ManyToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id_demande")
    // @MapsId("idDemande")
    // Demande demande;

    @ManyToOne
    @JoinColumn(name = "id_direction")
    Direction direction;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_article", insertable = false, updatable = false)
    @MapsId("idArticle")
    Article article;

    public DemandeDetailsID getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(DemandeDetailsID identifiant) {
        this.identifiant = identifiant;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public boolean isEstOuvert() {
        return estOuvert;
    }

    public void setEstOuvert(boolean estOuvert) {
        this.estOuvert = estOuvert;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
