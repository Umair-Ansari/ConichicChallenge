/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author umair
 */
public class CurrencylayerResponse {

    private boolean success;
    private String terms;
    private String privacy;
    private float timestamp;
    private String source;
    private Map quotes;
    private Error error;

    // Getter Methods 
    public boolean getSuccess() {
        return success;
    }

    public String getTerms() {
        return terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public void setSource(String source) {
        this.source = source;
    }


    public Map getQuotes() {
        return quotes;
    }

    public void setQuotes(Map quotes) {
        this.quotes = quotes;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
    
}
