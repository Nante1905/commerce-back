package com.nante.commerce.model.stock.etatStock;

import java.time.LocalDate;
import java.util.List;

public class EtatStock {
    LocalDate debut;
    LocalDate fin;
    List<DetailsEtatStock> details;

    public void setDebut(String debut) {
        if (debut == null) {
            this.debut = null;
        } else {
            setDebut(LocalDate.parse(debut));
        }
    }

    public void setFin(String fin) {
        if (fin == null) {
            this.fin = null;
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

    public double getMontantTotal() {
        double total = 0;
        for (DetailsEtatStock article : details) {
            total += article.getMontant();
        }
        return total;
    }

    public String getFinString() {
        if (this.getFin() == null) {
            return null;
        }
        return this.getFin().toString();
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

    public List<DetailsEtatStock> getDetails() {
        return details;
    }

    public void setDetails(List<DetailsEtatStock> details) {
        this.details = details;
    }

}
