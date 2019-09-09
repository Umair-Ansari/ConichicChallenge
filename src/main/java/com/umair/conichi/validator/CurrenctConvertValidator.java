/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.validator;

import com.umair.conichi.model.CurrencyConvertRequestDTO;
import org.springframework.stereotype.Component;

/**
 *
 * @author umair
 */

@Component
public class CurrenctConvertValidator{
    public boolean isValid(CurrencyConvertRequestDTO request){
        return !(request == null || request.getSourceCurrency() == null ||  request.getTargetCurrency() == null || request.getSourceCurrencyAmmount() == null || request.getSourceCurrency().isEmpty() || request.getTargetCurrency().isEmpty() || request.getSourceCurrencyAmmount() == 0);
    }
    
}
