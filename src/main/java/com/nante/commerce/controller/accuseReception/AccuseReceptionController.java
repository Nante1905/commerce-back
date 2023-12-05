package com.nante.commerce.controller.accuseReception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.accuseReception.AccuseReception;
import com.nante.commerce.model.stock.EntreStock;
import com.nante.commerce.services.accuseReception.AccuseReceptionService;
import com.nante.commerce.services.employe.EmployeService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("accuse-reception")
public class AccuseReceptionController extends GenericController<AccuseReception> {
    @Autowired
    EmployeService empService;
    @Autowired
    AccuseReceptionService accuseReceptionService;

    @Secured({ "CHEF" })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody AccuseReception model) {
        try {
            model.setEmploye(empService.getAuthenticated());
            accuseReceptionService.save(model);
            return ResponseEntity.ok(new Response(null, "Inséré avec succes"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
