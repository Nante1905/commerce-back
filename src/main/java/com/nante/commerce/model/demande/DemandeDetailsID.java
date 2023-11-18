package com.nante.commerce.model.demande;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DemandeDetailsID implements Serializable {
    @Column(name = "id_demande", insertable = false, updatable = false)
    int idDemande;
    @Column(name = "id_article", insertable = false, updatable = false)
    int idArticle;

    public DemandeDetailsID() {
    }

    public DemandeDetailsID(int idDemande, int idArticle) {
        setIdDemande(idDemande);
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
