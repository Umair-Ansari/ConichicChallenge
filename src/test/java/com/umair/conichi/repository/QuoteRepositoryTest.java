/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.repository;


import com.umair.conichi.repository.QuoteRepository;
import com.umair.conichi.entity.Quote;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
/**
 *
 * @author umair
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class QuoteRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private QuoteRepository quoteRepository;
    

    /**
     * Test of findBycurrency method, of class QuoteRepository.
     */
    @Test
    public void testFindBycurrencyHappyPath() {
        System.out.println("findBycurrency");

        Quote quote = new Quote();
        quote.setCurrency("USD");
        quote.setExchangeRate(new Double(13));
        quote.setUpdatedAt(new Date());
        
        entityManager.persist(quote);
        
        Quote result = quoteRepository.findBycurrency("USD");
        assertThat(result.getExchangeRate(), is(equalTo(new Double(13))));
        
    }
    
    @Test
    public void testFindBycurrencyUnhappyPath() {
        System.out.println("findBycurrency");

        Quote quote = new Quote();
        quote.setCurrency("USD");
        quote.setExchangeRate(new Double(13));
        quote.setUpdatedAt(new Date());
        
        entityManager.persist(quote);
        
        entityManager.remove(quote);
        
        Quote result = quoteRepository.findBycurrency("USD");
        assertThat(result, is(equalTo(null)));
        
    }

    
}
