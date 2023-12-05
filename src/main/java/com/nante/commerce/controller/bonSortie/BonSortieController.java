package com.nante.commerce.controller.bonSortie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.bonSortie.BonSortie;
import com.nante.commerce.services.bonSortie.BonSortieService;
import com.nante.commerce.types.response.Response;

import jakarta.security.auth.message.AuthException;

@RestController
@RequestMapping("bon-sortie")
public class BonSortieController extends GenericController<BonSortie> {
    @Autowired
    BonSortieService bonSortieService;

    @Secured({ "MAG" })
    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<BonSortie> results = this.getService().findAll();
            return ResponseEntity.ok(new Response(results, ""));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    @Secured({ "MAG", "CHEF" })
    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable(name = "id") int id) {
        try {
            BonSortie results = this.getService().find(id);
            return ResponseEntity.ok(new Response(results, ""));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Response(e.getMessage()));
        }
    }

    // @Secured({"CHEF"})
    @GetMapping("/dispatch")
    public ResponseEntity<?> findDispatch() {
        try {
            List<BonSortie> results = bonSortieService.getDispatch();
            return ResponseEntity.ok().body(new Response(results, null));
        } catch (AuthException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(e.getMessage()));
        }
    }
}
