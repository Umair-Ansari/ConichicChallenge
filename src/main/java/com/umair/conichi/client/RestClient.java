/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.client;

import com.umair.conichi.model.CloudmersiveRequest;
import com.umair.conichi.model.CloudmersiveResponse;
import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.umair.conichi.model.CurrencylayerResponse;
import com.umair.conichi.properties.Properties;
import com.umair.conichi.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import com.umair.conichi.constant.Constants;
/**
 *
 * @author umair
 */
@Component
public class RestClient {

    @Autowired
    private Util util;
    
    @Autowired
    private Properties properties;

    public CurrencylayerResponse getExchnageRates() {

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeader.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeader);
        ResponseEntity<CurrencylayerResponse> responseEntity;

        try {
            responseEntity = new RestTemplate().exchange(util.getCurrencyLayerUrl(), HttpMethod.GET, requestEntity, CurrencylayerResponse.class);
            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                return responseEntity.getBody();
            }

        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    public CloudmersiveResponse getVatStatus(CloudmersiveRequest cloudmersiveRequest) {

        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeader.add(Constants.API_KEY_NAME, properties.getCloudmersiveAccessKey());
        httpHeader.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CloudmersiveRequest> requestEntity = new HttpEntity<>(cloudmersiveRequest, httpHeader);
        ResponseEntity<CloudmersiveResponse> responseEntity;

        try {
            responseEntity = new RestTemplate().exchange(util.getCloudmersiveUrl(), HttpMethod.POST, requestEntity, CloudmersiveResponse.class);
            if (HttpStatus.OK == responseEntity.getStatusCode()) {
                return responseEntity.getBody();
            }

        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }
}
