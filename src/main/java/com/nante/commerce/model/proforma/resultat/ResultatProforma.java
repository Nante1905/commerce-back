package com.nante.commerce.model.proforma.resultat;

import java.time.LocalDate;
import java.util.List;

import com.nante.commerce.crud.model.GenericModel;
import com.nante.commerce.model.proforma.DemandeProformaFournisseur;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "resultat_proforma")
public class ResultatProforma extends GenericModel {
    int formatPrix;
    LocalDate delaiLivraison;
    LocalDate dateSaisie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_demande_proforma_fournisseur")
    DemandeProformaFournisseur proforma;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "proforma", cascade = CascadeType.PERSIST)
    List<ResultatProformaDetails> details;

    @PrePersist
    public void prePersist() {
        if (dateSaisie == null) {
            setDateSaisie(LocalDate.now());
        }
    }

    public int getFormatPrix() {
        return formatPrix;
    }

    public void setFormatPrix(int formatPrix) {
        this.formatPrix = formatPrix;
    }

    public LocalDate getDelaiLivraison() {
        return delaiLivraison;
    }

    public void setDelaiLivraison(LocalDate delaiLivraison) {
        this.delaiLivraison = delaiLivraison;
    }

    public LocalDate getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(LocalDate dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public List<ResultatProformaDetails> getDetails() {
        return details;
    }

    public void setDetails(List<ResultatProformaDetails> details) {
        this.details = details;
    }

    public DemandeProformaFournisseur getProforma() {
        return proforma;
    }

    public void setProforma(DemandeProformaFournisseur proforma) {
        this.proforma = proforma;
    }
}
