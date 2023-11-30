package com.nante.commerce.model.proforma.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DemandeProformaDetailsId {
    @Column(name = "id_article")
    int idArticle;

    @Column(name = "id_demande_proforma")
    int idDemandeProforma;
}
