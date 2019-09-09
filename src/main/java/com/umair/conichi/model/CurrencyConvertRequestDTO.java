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
public class CurrencyConvertRequestDTO{
    
    @JsonProperty("source-currency")
    private String sourceCurrency;
    
    @JsonProperty("target-currency")
    private String targetCurrency;
    
    @JsonProperty("source-currency-ammount")
    private Double sourceCurrencyAmmount;

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getSourceCurrencyAmmount() {
        return sourceCurrencyAmmount;
    }

    public void setSourceCurrencyAmmount(Double sourceCurrencyAmmount) {
        this.sourceCurrencyAmmount = sourceCurrencyAmmount;
    }

    
}
