package com.nante.commerce.services.proforma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.proforma.DemandeProformaFournisseur;
import com.nante.commerce.repositories.proforma.DemandeProformaFournisseurRepo;

@Service
public class ProformaService {
    @Autowired
    DemandeProformaFournisseurRepo demandeProformaFournisseurRepo;

    public List<DemandeProformaFournisseur> findDemandesSansReponse() {
        return this.demandeProformaFournisseurRepo.findByEtat(0);
    }
}
