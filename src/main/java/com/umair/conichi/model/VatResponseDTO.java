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
public class VatResponseDTO{
    
    @JsonProperty("vat-number")
    private String vatNumber;
    
    @JsonProperty("country-code")
    private String countryCode;

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
}
