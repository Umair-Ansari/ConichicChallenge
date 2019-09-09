/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.repository;

import com.umair.conichi.entity.Quote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author umair
 */
public interface QuoteRepository extends CrudRepository<Quote, Integer> {

    @Query(value = "SELECT * FROM quote q WHERE q.currency = :currency", nativeQuery = true)
    public Quote findBycurrency(@Param("currency") String currency);
    
}
