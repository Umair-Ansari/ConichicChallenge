/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.service;

import com.umair.conichi.client.RestClient;
import com.umair.conichi.entity.Vat;
import com.umair.conichi.model.CloudmersiveRequest;
import com.umair.conichi.model.CloudmersiveResponse;
import com.umair.conichi.model.VatRequestDTO;
import com.umair.conichi.model.VatResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.umair.conichi.repository.VatRepository;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 *
 * @author umair
 */
@Component
public class VatServiceI implements VatService {

    @Autowired
    private VatRepository vatRepository;


    @Autowired
    private RestClient restClient;

    @Override
    public ResponseEntity<VatResponseDTO> getVerified(VatRequestDTO request) {
        Vat vat = vatRepository.findByVatCode(request.getVatNumber());
        if (vat == null) {
            vat =  new Vat();
            vat.setValid(false);
            vat.setUpdatedAt(new Date());
            vat.setVatCountryCode(request.getVatNumber());
            CloudmersiveResponse response = restClient.getVatStatus(new CloudmersiveRequest(request.getVatNumber()));
            if (response != null && response.isIsValid()) {
                vat.setCountryCode(response.getCountryCode());
                vat.setValid(true);
            }
            vatRepository.save(vat);
        }

        if (vat.isValid()) {
            VatResponseDTO response = new VatResponseDTO();
            response.setCountryCode(vat.getCountryCode());
            response.setVatNumber(request.getVatNumber());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
