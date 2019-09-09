/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.util;

import com.umair.conichi.util.Util;
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
public class UtilTest {
    
    @Autowired
    private Util util;
    /**
     * Test of getCurrencyLayerUrl method, of class Util.
     */
    @Test
    public void testGetCurrencyLayerUrl() {
        System.out.println("getCurrencyLayerUrl");

        String expResult = "http://apilayer.net/api/live?access_key=8f293590f5ad79f6dd6f39dc4e4f401a";
        String result = util.getCurrencyLayerUrl();

        assertNotNull(result);
        assertEquals(expResult, result);

    }

    /**
     * Test of getCloudmersiveUrl method, of class Util.
     */
    @Test
    public void testGetCloudmersiveUrl() {
        System.out.println("getCloudmersiveUrl");

        String expResult = "https://api.cloudmersive.com/validate/vat/lookup";
        String result = util.getCloudmersiveUrl();

        assertNotNull(result);
        assertEquals(expResult, result);

    }

    /**
     * Test of convertCurrency method, of class Util.
     */
    @Test
    public void testConvertCurrency() {
        System.out.println("convertCurrency");
        
        Double sourceExchangeRate = new Double(1);
        Double sourceAmmount = new Double(1);
        Double targetExchangeRate = new Double(156.692461);
        
        Double expResult = new Double(156.692461);
        Double result = util.convertCurrency(sourceExchangeRate, sourceAmmount, targetExchangeRate);
        
        assertNotNull(result);
        assertEquals(expResult, result);

        
    }
    
}
