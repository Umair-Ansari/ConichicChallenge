/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.service;

import com.umair.conichi.model.CurrencyConvertRequestDTO;
import com.umair.conichi.model.CurrencyConvertResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author umair
 */
@Component
public interface CurrencyService {
    ResponseEntity<CurrencyConvertResponseDTO> convert(CurrencyConvertRequestDTO request);
    void syncCurrencyLayer();
}
