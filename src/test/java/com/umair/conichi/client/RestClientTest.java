/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.client;

import com.umair.conichi.model.CloudmersiveRequest;
import com.umair.conichi.model.CloudmersiveResponse;
import com.umair.conichi.model.CurrencylayerResponse;
import com.umair.conichi.util.Util;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import static org.mockito.ArgumentMatchers.any;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.umair.conichi.properties.Properties;
import org.junit.Ignore;

/**
 *
 * @author umair
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RestClientTest {

    @InjectMocks
    private RestClient restClient;

    @Mock
    private Util util;


    @Mock
    RestTemplate restTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test of getExchnageRates method, of class RestClient.
     */
    @Test
    public void testGetExchnageRates() {
        CurrencylayerResponse aMockResponse = new CurrencylayerResponse();
        Map<Object, Object> quotes = new HashMap<>();
        quotes.put("USDUSD", 1);
        aMockResponse.setSuccess(true);
        aMockResponse.setQuotes(quotes);

        ResponseEntity<CurrencylayerResponse> responseEntity
                = new ResponseEntity<>(aMockResponse, HttpStatus.ACCEPTED);
        //when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(new ResponseEntity(aMockResponse, HttpStatus.OK));
        
        when(util.getCurrencyLayerUrl()).thenReturn("http://apilayer.net/api/live?access_key=8f293590f5ad79f6dd6f39dc4e4f401a");

        CurrencylayerResponse response = restClient.getExchnageRates();

        assertNotNull(response);
        assertTrue(response.getSuccess());
        assertNotNull(response.getQuotes());

    }

    /**
     * Test of getVatStatus method, of class RestClient.
     */
    @Test
    @Ignore
    public void testGetVatStatusHappyPath() {
        System.out.println("getVatStatus");

        CloudmersiveResponse aMockResponse = new CloudmersiveResponse();
        aMockResponse.setIsValid(true);
        aMockResponse.setCountryCode("GB");
        aMockResponse.setVatNumber("656049569");

        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), any(Class.class))).thenReturn(new ResponseEntity(aMockResponse, HttpStatus.OK));
        when(util.getCloudmersiveUrl()).thenReturn("https://api.cloudmersive.com/validate/vat/lookup");

        CloudmersiveRequest request = new CloudmersiveRequest("GB656049569");

        CloudmersiveResponse response = restClient.getVatStatus(request);

        assertNotNull(response);
        assertNotNull(response.getVatNumber());
        assertNotNull(response.getCountryCode());
        assertThat(response.getVatNumber(), is(equalTo("656049569")));
        assertThat(response.getCountryCode(), is(equalTo("GB")));
    }

}
