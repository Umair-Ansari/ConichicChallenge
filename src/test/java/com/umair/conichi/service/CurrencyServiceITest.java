/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.service;

import com.umair.conichi.service.CurrencyServiceI;
import com.umair.conichi.client.RestClient;
import com.umair.conichi.entity.Quote;
import com.umair.conichi.model.CurrencyConvertRequestDTO;
import com.umair.conichi.model.CurrencyConvertResponseDTO;
import com.umair.conichi.model.CurrencylayerResponse;
import com.umair.conichi.repository.QuoteRepository;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.umair.conichi.util.Util;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
/**
 *
 * @author umair
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class CurrencyServiceITest {

    @InjectMocks
    private CurrencyServiceI currencyService;
    
    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private Util util;
    
    @Mock
    private RestClient restClient;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    /**
     * Test of convert method, of class CurrencyServiceI.
     */
    @Test
    public void testConvertHappyPath() {
        System.out.println("convert");

        Quote aMockQuote = new Quote();
        aMockQuote.setId(1);
        aMockQuote.setCurrency("USD");
        aMockQuote.setExchangeRate(new Double(2));
        aMockQuote.setUpdatedAt(new Date());

        when(quoteRepository.findBycurrency(any(String.class))).thenReturn(aMockQuote);
        when(util.convertCurrency(any(Double.class),any(Double.class),any(Double.class))).thenReturn(new Double(2));
        CurrencyConvertRequestDTO request = new CurrencyConvertRequestDTO();
        request.setSourceCurrency("USD");
        request.setSourceCurrencyAmmount(new Double(1));
        request.setTargetCurrency("USD");
        
        CurrencyConvertResponseDTO response = new CurrencyConvertResponseDTO(new Double(2));
        ResponseEntity<CurrencyConvertResponseDTO> expResult = new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity<CurrencyConvertResponseDTO> result = currencyService.convert(request);

        assertNotNull(result);
        assertNotNull(result.getBody());
        if (result.getBody() != null) {
            assertNotNull(result.getBody().getTargetCurrency());
            assertEquals(expResult.getBody().getTargetCurrency(), result.getBody().getTargetCurrency());
        }
       
    }

    @Test
    public void testConvertUnHappyPathUtil() {
        System.out.println("convert");

        Quote aMockQuote = new Quote();
        aMockQuote.setId(1);
        aMockQuote.setCurrency("USD");
        aMockQuote.setExchangeRate(new Double(2));
        aMockQuote.setUpdatedAt(new Date());

        when(quoteRepository.findBycurrency(any(String.class))).thenReturn(aMockQuote);
        when(util.convertCurrency(any(Double.class),any(Double.class),any(Double.class))).thenReturn(new Double(3));
        CurrencyConvertRequestDTO request = new CurrencyConvertRequestDTO();
        request.setSourceCurrency("USD");
        request.setSourceCurrencyAmmount(new Double(1));
        request.setTargetCurrency("USD");
        
        CurrencyConvertResponseDTO response = new CurrencyConvertResponseDTO(new Double(2));
        ResponseEntity<CurrencyConvertResponseDTO> expResult = new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity<CurrencyConvertResponseDTO> result = currencyService.convert(request);

        assertNotNull(result);
        assertNotNull(result.getBody());
        if (result.getBody() != null) {
            assertNotNull(result.getBody().getTargetCurrency());
            assertNotEquals(expResult.getBody().getTargetCurrency(), result.getBody().getTargetCurrency());
        }
       
    }
    @Test
    public void testConvertUnHappyPathRepo() {
        System.out.println("convert");

        Quote aMockQuote = new Quote();
        aMockQuote.setId(1);
        aMockQuote.setCurrency("USD");
        aMockQuote.setExchangeRate(new Double(1));
        aMockQuote.setUpdatedAt(new Date());

        when(quoteRepository.findBycurrency(any(String.class))).thenReturn(aMockQuote);
        when(util.convertCurrency(any(Double.class),any(Double.class),any(Double.class))).thenReturn(new Double(1));
        CurrencyConvertRequestDTO request = new CurrencyConvertRequestDTO();
        request.setSourceCurrency("USD");
        request.setSourceCurrencyAmmount(new Double(1));
        request.setTargetCurrency("USD");
        
        CurrencyConvertResponseDTO response = new CurrencyConvertResponseDTO(new Double(2));
        ResponseEntity<CurrencyConvertResponseDTO> expResult = new ResponseEntity<>(response, HttpStatus.OK);
        ResponseEntity<CurrencyConvertResponseDTO> result = currencyService.convert(request);

        assertNotNull(result);
        assertNotNull(result.getBody());
        if (result.getBody() != null) {
            assertNotNull(result.getBody().getTargetCurrency());
            assertNotEquals(expResult.getBody().getTargetCurrency(), result.getBody().getTargetCurrency());
        }
       
    }
    /**
     * Test of syncCurrencyLayer method, of class CurrencyServiceI.
     */
    @Test
    public void testSyncCurrencyLayerHappyPath() {

        CurrencylayerResponse aMockResponse = new CurrencylayerResponse();
        Map<Object, Object> quotes = new HashMap<>();
        quotes.put("USDUSD", 1);
        
        aMockResponse.setSuccess(true);
        aMockResponse.setQuotes(quotes);
        
        when(restClient.getExchnageRates()).thenReturn(aMockResponse);
        
        Quote aMockQuote = new Quote();
        aMockQuote.setId(1);
        aMockQuote.setCurrency("USD");
        aMockQuote.setExchangeRate(new Double(1));
        aMockQuote.setUpdatedAt(new Date());

        when(quoteRepository.findBycurrency(any(String.class))).thenReturn(aMockQuote);
        currencyService.syncCurrencyLayer();
        
        Quote quote = quoteRepository.findBycurrency("USD");

        quote.getExchangeRate();
        
        assertNotNull(quote);
        assertNotNull(quote.getExchangeRate());
        
        Double expected =  new Double(1);
        
        assertThat(quote.getExchangeRate(), is(equalTo(expected)));
        
    }
    
    
    @Test
    public void testSyncCurrencyLayerUnHappyPathUpdateExchnageRate() {

        CurrencylayerResponse aMockResponse = new CurrencylayerResponse();
        Map<Object, Object> quotes = new HashMap<>();
        quotes.put("USDUSD", 2);
        
        aMockResponse.setSuccess(true);
        aMockResponse.setQuotes(quotes);
        
        when(restClient.getExchnageRates()).thenReturn(aMockResponse);
        
        Quote aMockQuote = new Quote();
        aMockQuote.setId(1);
        aMockQuote.setCurrency("USD");
        aMockQuote.setExchangeRate(new Double(1));
        aMockQuote.setUpdatedAt(new Date());

        when(quoteRepository.findBycurrency(any(String.class))).thenReturn(aMockQuote);
        currencyService.syncCurrencyLayer();
        
        Quote quote = quoteRepository.findBycurrency("USD");

        quote.getExchangeRate();
        
        assertNotNull(quote);
        assertNotNull(quote.getExchangeRate());
        
        Double expected =  new Double(2);
        
        assertThat(quote.getExchangeRate(), is(equalTo(expected)));
        
    }

    
    
}
