/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author umair
 */
@Component
public class Properties {
    
    @Value("${com.umair.currency-layer.url}")
    private String currencyLayerUrl;
    
    @Value("${com.umair.currency-layer.access-key}")
    private String currencyLayerAccessKey;

    
    @Value("${com.umair.cloudmersive.url}")
    private String cloudmersiveuUrl;
    
    @Value("${com.umair.cloudmersive.access-key}")
    private String cloudmersiveAccessKey;
    
    public String getCurrencyLayerUrl() {
        return currencyLayerUrl;
    }

    public String getCurrencyLayerAccessKey() {
        return currencyLayerAccessKey;
    }

    public String getCurrencyLayeraccessKey() {
        return currencyLayerAccessKey;
    }

    public String getCloudmersiveuUrl() {
        return cloudmersiveuUrl;
    }

    public String getCloudmersiveAccessKey() {
        return cloudmersiveAccessKey;
    }
    
}
