package com.nante.commerce.services.proforma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.proforma.resultat.ResultatProforma;
import com.nante.commerce.model.proforma.resultat.ResultatProformaDetails;
import com.nante.commerce.repositories.proforma.resultat.ResultatProformaRepo;

@Service
public class ResultatProformaService extends GenericService<ResultatProforma> {
    @Autowired
    ResultatProformaRepo resultatProformaRepo;

    @Override
    public ResultatProforma save(ResultatProforma proforma) {
        for (ResultatProformaDetails details : proforma.getDetails()) {
            details.setProforma(proforma);
        }
        return resultatProformaRepo.save(proforma);
    }
}
