/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.validator;

import com.umair.conichi.validator.CurrenctConvertValidator;
import com.umair.conichi.model.CurrencyConvertRequestDTO;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author umair
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CurrenctConvertValidatorTest {
    
    @Autowired
    private CurrenctConvertValidator currenctConvertValidator;
    /**
     * Test of isValid method, of class CurrenctConvertValidator.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        
        CurrencyConvertRequestDTO request = null;
        
        boolean result = currenctConvertValidator.isValid(request);
        
        assertNotNull(result);
        assertEquals(false, result);

        request =  new CurrencyConvertRequestDTO();
        request.setSourceCurrency("");
        request.setSourceCurrencyAmmount(new Double(0));
        request.setTargetCurrency("USD");
        
        result = currenctConvertValidator.isValid(request);
        assertNotNull(result);
        assertEquals(false, result);
        
        request.setSourceCurrency("PKR");
        request.setSourceCurrencyAmmount(new Double(1));
        
        result = currenctConvertValidator.isValid(request);
        assertNotNull(result);
        assertEquals(true, result);
    }
    
}
