package com.nante.commerce.model.demande;

import com.nante.commerce.model.employe.Direction;

public class DemandeParNatureDetails {
    Direction direction;
    int status;
    double quantite;
    int idDemande;

    public DemandeParNatureDetails(DemandeParDetails d) {
        setDirection(d.getDirection());
        setStatus(d.getStatus());
        setQuantite(d.getQuantite());
        setIdDemande(d.getId());
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public DemandeParNatureDetails(Direction direction, int status, double quantite) {
        setDirection(direction);
        setStatus(status);
        setQuantite(quantite);
    }

    public DemandeParNatureDetails() {
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
}
