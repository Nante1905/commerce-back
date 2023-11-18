package com.nante.commerce.model.demande;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nante.commerce.model.item.Article;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class DemandeDetailsID implements Serializable {
    @Column(name = "id_demande", insertable = false, updatable = false)
    int idDemande;
    @Column(name = "id_article")
    int idArticle;

    public DemandeDetailsID() {
    }

    public DemandeDetailsID(int idDemande, int idArticle) {
        setIdDemande(idDemande);
        setIdArticle(idArticle);
    }

    public DemandeDetailsID(int idArticle) {
        setIdArticle(idArticle);
    }

    public int getIdDemande() {
        return idDemande;
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
