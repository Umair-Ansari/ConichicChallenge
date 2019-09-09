/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.repository;

import com.umair.conichi.entity.Vat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 *
 * @author umair
 */
public interface VatRepository extends CrudRepository<Vat, Integer> {

    @Query(value = "SELECT * FROM vat v WHERE v.vat_code = :vatCode", nativeQuery = true)
    public Vat findByVatCode(@Param("vatCode") String vatCode);

}
