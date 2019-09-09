/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.model;

/**
 *
 * @author umair
 */
public class CloudmersiveRequest {

    private String vatCode;

    public CloudmersiveRequest(String vatCode) {
        this.vatCode = vatCode;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }
    
    
}
