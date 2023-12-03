package com.nante.commerce.controller.stock;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.model.stock.etatStock.EtatStock;
import com.nante.commerce.services.stock.EtatStockService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("stock")
public class EtatStockController {
    @Autowired
    EtatStockService etatStockService;

    @PostMapping("/etat")
    public ResponseEntity<?> findAll(@RequestBody HashMap<String, String> inputs) {
        try {
            String debut = inputs.get("debut");
            String fin = inputs.get("fin");
            String article = inputs.get("article");

            EtatStock results = etatStockService.findEtatStock(debut, fin, article);
            return ResponseEntity.ok(new Response(results, ""));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }
}
