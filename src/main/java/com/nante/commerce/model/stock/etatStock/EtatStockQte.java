package com.nante.commerce.model.stock.etatStock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EtatStockQte {
    LocalDate debut;
    LocalDate fin;
    List<DetailsEtatStockQte> details;
    boolean avecMontant = false;

    public EtatStockQte() {
    }

    public EtatStockQte(EtatStock e) {
        setDebut(e.getDebut());
        setFin(e.getFin());
        for (DetailsEtatStock d : e.getDetails()) {
            this.addDetails(new DetailsEtatStockQte(d));
        }
    }

    public void addDetails(DetailsEtatStockQte d) {
        if (getDetails() == null) {
            setDetails(new ArrayList<DetailsEtatStockQte>());
        }
        details.add(d);
    }

    public void setDebut(String debut) {
        if (debut == null) {
            this.debut = null;
        } else {
            setDebut(LocalDate.parse(debut));
        }
    }

    public void setFin(String fin) {
        if (fin == null) {
            this.fin = LocalDate.now();
        } else {
            setFin(LocalDate.parse(fin));
        }
    }

    public String getDebutString() {
        if (this.getDebut() == null) {
            return null;
        }
        return this.getDebut().toString();
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public List<DetailsEtatStockQte> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsEtatStockQte> details) {
        this.details = details;
    }

    public boolean isAvecMontant() {
        return avecMontant;
    }

    public void setAvecMontant(boolean avecMontant) {
        this.avecMontant = avecMontant;
    }

}
