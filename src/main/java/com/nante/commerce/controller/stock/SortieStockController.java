package com.nante.commerce.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.stock.SortieStock;
import com.nante.commerce.services.employe.EmployeService;
import com.nante.commerce.services.stock.SortieStockService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("sortie-stock")
public class SortieStockController extends GenericController<SortieStock> {
    @Autowired
    SortieStockService service;
    @Autowired
    EmployeService employeService;

    @Secured("MAG")
    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody SortieStock model) {
        try {
            model.setEmploye(employeService.getAuthenticated());
            SortieStock results = service.saisie(model);
            return ResponseEntity.ok(new Response(results, "Inséré avec succes"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
