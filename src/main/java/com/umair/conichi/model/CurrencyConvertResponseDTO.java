/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author umair
 */
public class CurrencyConvertResponseDTO {
    @JsonProperty("target-currency")
    private Double targetCurrency;

    public CurrencyConvertResponseDTO(Double targetCurrency) {
        this.targetCurrency = targetCurrency;
    }
    
    public Double getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Double targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

}
