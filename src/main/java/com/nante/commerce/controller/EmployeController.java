package com.nante.commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.employe.Employe;
import com.nante.commerce.services.employe.EmployeService;
import com.nante.commerce.types.response.Response;

/**
 * EmployeController
 */
@RestController
@RequestMapping("employes")
public class EmployeController extends GenericController<Employe> {

    @Autowired
    EmployeService employeService;

    @GetMapping("/me")
    public ResponseEntity<?> me() throws Exception {

        try {
            Employe emp = this.employeService.getAuthenticated();
            return ResponseEntity.ok().body(new Response(emp, "auth user"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body(new Response(e.getMessage()));
        }
    }
}