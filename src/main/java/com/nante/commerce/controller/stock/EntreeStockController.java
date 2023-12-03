package com.nante.commerce.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.stock.EntreStock;
import com.nante.commerce.model.stock.SortieStock;
import com.nante.commerce.services.employe.EmployeService;
import com.nante.commerce.services.stock.EntreStockService;
import com.nante.commerce.services.stock.SortieStockService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("entre-stock")
public class EntreeStockController extends GenericController<EntreStock> {
    @Autowired
    EntreStockService service;
    @Autowired
    EmployeService employeService;

    @Secured({ "MAG" })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody EntreStock model) {
        try {
            model.setEmploye(employeService.getAuthenticated());
            service.inserer(model);
            return ResponseEntity.ok(new Response(null, "Inseréré avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
