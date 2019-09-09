/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.validator;

import com.umair.conichi.model.VatRequestDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author umair
 */

@Component
public class VatValidator{
    public boolean isValid(VatRequestDTO request){
        return !(request == null || request.getVatNumber() == null || request.getVatNumber().isEmpty());
    }
}
