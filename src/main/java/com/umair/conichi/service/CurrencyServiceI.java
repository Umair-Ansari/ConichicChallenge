/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.service;

import com.umair.conichi.client.RestClient;
import com.umair.conichi.entity.Quote;
import com.umair.conichi.model.CurrencyConvertRequestDTO;
import com.umair.conichi.model.CurrencyConvertResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.umair.conichi.model.CurrencylayerResponse;
import com.umair.conichi.repository.QuoteRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.umair.conichi.util.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author umair
 */
@Component
public class CurrencyServiceI implements CurrencyService {

    @Autowired
    private RestClient restClient;
    
    @Autowired
    private Util util;

    @Autowired
    private QuoteRepository quoteRepository;

    @Override
    public ResponseEntity<CurrencyConvertResponseDTO> convert(CurrencyConvertRequestDTO request) {
        
        Quote sourceQuote = quoteRepository.findBycurrency(request.getSourceCurrency());
        Quote targetQuote = quoteRepository.findBycurrency(request.getTargetCurrency());
        if(sourceQuote != null && targetQuote != null){
            return new ResponseEntity<>(new CurrencyConvertResponseDTO(util.convertCurrency(sourceQuote.getExchangeRate(), request.getSourceCurrencyAmmount(), targetQuote.getExchangeRate())), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public void syncCurrencyLayer() {
        CurrencylayerResponse currencylayerResponse = restClient.getExchnageRates();

        if (currencylayerResponse == null || !currencylayerResponse.getSuccess()) {
            if (currencylayerResponse.getError() != null) {
                System.err.println(currencylayerResponse.getError().getInfo());
            }
        }
        System.err.println(currencylayerResponse.getQuotes());
        List<Quote> quotes = new ArrayList<>();
        currencylayerResponse.getQuotes().forEach((key, val) -> {
            String currency = key.toString().substring(3);
            Quote quote = quoteRepository.findBycurrency(currency);
            double exchnageRate;
            try {
                exchnageRate = (double) val;
            } catch (Exception e) {
                exchnageRate = new Double((Integer) val);
            }

            if (quote == null || quote.getExchangeRate() != exchnageRate) {
                if (quote == null) {
                    quote = new Quote();
                    quote.setCurrency(currency);
                }
                if (quote.getExchangeRate() != exchnageRate) {
                    quote.setExchangeRate(exchnageRate);
                }
                quote.setUpdatedAt(new Date());
                quotes.add(quote);
            }

        });
        if (quotes.size() > 0) {
            quoteRepository.saveAll(quotes);
        }
    }

}
