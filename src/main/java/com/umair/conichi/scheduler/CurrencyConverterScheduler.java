/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.scheduler;

import com.umair.conichi.service.CurrencyService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author umair
 */
@Component
public class CurrencyConverterScheduler {
    
    @Autowired
    private CurrencyService currencyService;
    
    @PostConstruct
    public void onStartup() {
         System.out.println("Startup Scheduler Started for : Currency Layer");
        
        currencyService.syncCurrencyLayer();
        
        System.out.println("Startup Scheduler Finished for : Currency Layer");
    }
    
    @Scheduled(cron = "${com.umair.quotes.sync.cron}")
    public void CurrencyLayerScheduler() {
        System.out.println("Scheduler Started for : Currency Layer");
        
        currencyService.syncCurrencyLayer();
        
        System.out.println("Scheduler Finished for : Currency Layer");

    }
}
