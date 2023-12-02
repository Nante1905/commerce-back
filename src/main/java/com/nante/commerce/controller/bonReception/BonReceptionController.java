package com.nante.commerce.controller.bonReception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nante.commerce.crud.controller.GenericController;
import com.nante.commerce.model.bonReception.BonReception;

@RestController
@RequestMapping("bon-reception")
public class BonReceptionController extends GenericController<BonReception> {

}
