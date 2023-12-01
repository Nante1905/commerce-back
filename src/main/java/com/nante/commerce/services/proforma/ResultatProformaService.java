package com.nante.commerce.services.proforma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.proforma.resultat.ResultatProforma;
import com.nante.commerce.model.proforma.resultat.ResultatProformaDetails;
import com.nante.commerce.repositories.proforma.DemandeProformaFournisseurRepo;
import com.nante.commerce.repositories.proforma.resultat.ResultatProformaDetailsRepo;
import com.nante.commerce.repositories.proforma.resultat.ResultatProformaRepo;

import jakarta.transaction.Transactional;

@Service
public class ResultatProformaService extends GenericService<ResultatProforma> {
    @Autowired
    ResultatProformaRepo resultatProformaRepo;
    @Autowired
    DemandeProformaFournisseurRepo demandeFournisseurRepo;
    @Autowired
    ResultatProformaDetailsRepo resultatProformaDetailsRepo;

    @Transactional
    @Override
    public ResultatProforma save(ResultatProforma proforma) {
        for (ResultatProformaDetails details : proforma.getDetails()) {
            details.setProforma(proforma);
        }
        proforma = resultatProformaRepo.save(proforma);
        demandeFournisseurRepo.updateEtat(5, proforma.getProforma().getId());
        return proforma;
    }

    public List<ResultatProformaDetails> findDetailsHorsTaxe(int idProforma) {
        return this.resultatProformaDetailsRepo.findResultatHorsTaxeOfProforma(idProforma);
    }
}
