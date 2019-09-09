/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.service;

import com.umair.conichi.model.VatRequestDTO;
import com.umair.conichi.model.VatResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 *
 * @author umair
 */
@Component
public interface VatService {
    ResponseEntity<VatResponseDTO> getVerified(VatRequestDTO request);
}
