package com.nante.commerce.services.proforma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.proforma.DemandeProforma;
import com.nante.commerce.model.proforma.DemandeProformaFournisseur;
import com.nante.commerce.model.proforma.StatistiqueProforma;
import com.nante.commerce.repositories.proforma.DemandeProformaFournisseurRepo;
import com.nante.commerce.repositories.proforma.DemandeProformaRepo;
import com.nante.commerce.repositories.proforma.StatistiqueProformaRepository;

@Service
public class ProformaService {
    @Autowired
    DemandeProformaFournisseurRepo demandeProformaFournisseurRepo;
    @Autowired
    StatistiqueProformaRepository statistiqueProformaRepo;
    @Autowired
    DemandeProformaRepo demandeProformaRepo;

    public List<StatistiqueProforma> findAllAvecReponse() {
        List<StatistiqueProforma> stats = statistiqueProformaRepo.findAllAvecReponse();
        for (StatistiqueProforma s : stats) {
            System.out.println(s.getId());
            s.setProforma(demandeProformaRepo.findById(s.getId()).get());
        }
        return stats;
    }

    public List<DemandeProformaFournisseur> findDemandesSansReponse() {
        return this.demandeProformaFournisseurRepo.findByEtat(0);
    }

}
