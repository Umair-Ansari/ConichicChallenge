/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.controller;

import com.umair.conichi.model.CurrencyConvertRequestDTO;
import com.umair.conichi.model.CurrencyConvertResponseDTO;
import com.umair.conichi.service.CurrencyService;
import com.umair.conichi.validator.CurrenctConvertValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author umair
 */
@RestController
@RequestMapping("/api/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrenctConvertValidator currenctConvertValidator;

    @RequestMapping(method = RequestMethod.POST, path = "/convert")
    public ResponseEntity<CurrencyConvertResponseDTO> convertCurrency(@RequestBody CurrencyConvertRequestDTO request) {

        try {

            if (currenctConvertValidator.isValid(request)) {
                return currencyService.convert(request);
            }

        } catch (Exception e) {
           e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
