/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.validator;

import com.umair.conichi.validator.VatValidator;
import com.umair.conichi.model.VatRequestDTO;
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
public class VatValidatorTest {
    
    @Autowired
    private VatValidator vatValidator;
    /**
     * Test of isValid method, of class VatValidator.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        
        VatRequestDTO request = null;

        boolean result = vatValidator.isValid(request);
        assertNotNull(result);
        assertEquals(false, result);

        request =  new VatRequestDTO();
        request.setVatNumber("");
        
        result = vatValidator.isValid(request);
        
        assertNotNull(result);
        assertEquals(false, result);
        
        request.setVatNumber("GB12334545");
        result = vatValidator.isValid(request);
        
        assertNotNull(result);
        assertEquals(true, result);
    }
    
}
