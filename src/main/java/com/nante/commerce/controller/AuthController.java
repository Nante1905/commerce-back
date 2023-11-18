package com.nante.commerce.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.services.authentication.AuthenticationService;
import com.nante.commerce.types.response.Response;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody HashMap<String, Object> body) throws Exception {

        String email = body.get("email").toString();
        String password = body.get("password").toString();

        try {
            String token = this.authenticationService.login(email, password);
            HashMap<String, Object> data = new HashMap<>();
            data.put("token", token);
            return ResponseEntity.ok().body(new Response(data, "Authentification réussie"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body(new Response(e.getMessage()));
        }
    }
}
