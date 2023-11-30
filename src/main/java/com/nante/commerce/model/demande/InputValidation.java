package com.nante.commerce.model.demande;

import java.util.List;

/**
 * InputValidation
 */
public class InputValidation {
    List<SelectionDetailsDemande> selected;
    List<SelectionDetailsDemande> rejected;
    int[] demandes;

    public List<SelectionDetailsDemande> getSelected() {
        return selected;
    }

    public void setSelected(List<SelectionDetailsDemande> selected) {
        this.selected = selected;
    }

    public List<SelectionDetailsDemande> getRejected() {
        return rejected;
    }

    public void setRejected(List<SelectionDetailsDemande> rejected) {
        this.rejected = rejected;
    }

    public int[] getDemandes() {
        return demandes;
    }

    public void setDemandes(int[] demandes) {
        this.demandes = demandes;
    }

}