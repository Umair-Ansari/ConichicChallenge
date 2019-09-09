/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.controller;

import com.umair.conichi.model.CurrencyConvertRequestDTO;
import com.umair.conichi.model.CurrencyConvertResponseDTO;
import com.umair.conichi.model.VatRequestDTO;
import com.umair.conichi.model.VatResponseDTO;
import com.umair.conichi.service.VatService;
import com.umair.conichi.validator.VatValidator;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

/**
 *
 * @author umair
 */
@RestController
@RequestMapping("/api/vat")
public class VatController {

    @Autowired
    private VatService vatService;

    @Autowired
    private VatValidator vatValidator;

    private static final Logger LOGGER = Logger.getLogger(VatController.class.getName());

    @RequestMapping(method = RequestMethod.POST, path = "/validate")
    public ResponseEntity<VatResponseDTO> convertCurrency(@RequestBody VatRequestDTO request) {

        try {

            if (vatValidator.isValid(request)) {
                return vatService.getVerified(request);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occur in convertCurrency.", e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
