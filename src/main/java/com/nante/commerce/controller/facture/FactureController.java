package com.nante.commerce.controller.facture;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.facture.Facture;

@RestController
@RequestMapping("facture")
public class FactureController extends GenericController<Facture> {

}
