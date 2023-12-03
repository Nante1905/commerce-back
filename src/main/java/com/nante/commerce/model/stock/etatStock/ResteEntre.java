package com.nante.commerce.model.stock.etatStock;

import java.io.Serializable;

public class ResteEntre implements Serializable {
    int idEntree;
    double reste;
    double pu;

    public ResteEntre(int idEntree, double reste, double pu) {
        setIdEntree(idEntree);
        setReste(reste);
        setPu(pu);
    }

    public ResteEntre() {
    }

    public int getIdEntree() {
        return idEntree;
    }

    public void setIdEntree(int idEntree) {
        this.idEntree = idEntree;
    }

    public double getReste() {
        return reste;
    }

    public void setReste(double reste) {
        this.reste = reste;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

}
