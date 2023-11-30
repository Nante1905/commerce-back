package com.nante.commerce.controller.proforma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.services.proforma.ProformaService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("proforma")
public class ProformaController {
    @Autowired
    ProformaService proformaService;

    @GetMapping("/sans-reponse")
    public ResponseEntity<?> findProformaSansReponse() {
        try {
            return ResponseEntity.ok().body(new Response(proformaService.findDemandesSansReponse(), null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

}
