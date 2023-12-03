package com.nante.commerce.controller.bonLivraison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.bonLivraison.BonDeLivraison;
import com.nante.commerce.model.stock.SortieStock;
import com.nante.commerce.services.bonLivraison.BonLivraisonService;
import com.nante.commerce.services.employe.EmployeService;
import com.nante.commerce.services.stock.SortieStockService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("bon-livraison")
public class BonLivraisonController extends GenericController<BonDeLivraison> {
    @Autowired
    BonLivraisonService service;
    @Autowired
    EmployeService employeService;

    @Secured({ "MAG RECEP", "MAG EMP" })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody BonDeLivraison model) {
        try {
            model.setEmploye(employeService.getAuthenticated());
            BonDeLivraison results = service.save(model);
            return ResponseEntity.ok(new Response(results, "Inserer avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
