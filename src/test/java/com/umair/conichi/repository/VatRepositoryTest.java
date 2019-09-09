/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.repository;

import com.umair.conichi.repository.VatRepository;
import com.umair.conichi.entity.Quote;
import com.umair.conichi.entity.Vat;
import java.util.Date;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author umair
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class VatRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private VatRepository vatRepository;

    /**
     * Test of findByVatCode method, of class VatRepository.
     */

    @Test
    public void testFindBycurrency() {
        System.out.println("findByVatCode");

        Vat vat = new Vat();
        vat.setCountryCode("GB");
        vat.setVatCountryCode("GB123456");
        vat.setValid(true);
        vat.setUpdatedAt(new Date());
        
        entityManager.persist(vat);
        
        Vat result = vatRepository.findByVatCode("GB123456");
        assertThat(result.getCountryCode(), is(equalTo("GB")));
        
    }
    
    @Test
    public void testFindBycurrencyUnhappyPath() {
        System.out.println("findByVatCode");

        Vat vat = new Vat();
        vat.setCountryCode("GB");
        vat.setVatCountryCode("123456");
        vat.setValid(true);
        vat.setUpdatedAt(new Date());
        
        entityManager.persist(vat);
        
        entityManager.remove(vat);
        
        Vat result = vatRepository.findByVatCode("GB123456");
        assertThat(result, is(equalTo(null)));
        
    }
}
