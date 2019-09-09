/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.util;

import com.umair.conichi.properties.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author umair
 */
@Component
public class Util {

    @Autowired
    private Properties properties;

    public String getCurrencyLayerUrl() {
        return properties.getCurrencyLayerUrl() + "?access_key=" + properties.getCurrencyLayerAccessKey();
    }

    public String getCloudmersiveUrl() {
        return properties.getCloudmersiveuUrl();
    }
    
    public Double convertCurrency(Double sourceExchangeRate, Double sourceAmmount, Double targetExchangeRate){
        return ((1/sourceExchangeRate)*sourceAmmount*targetExchangeRate);
    }
}
