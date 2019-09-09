/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umair.conichi.service;

import com.umair.conichi.service.VatServiceI;
import com.umair.conichi.client.RestClient;
import com.umair.conichi.entity.Vat;
import com.umair.conichi.model.CloudmersiveRequest;
import com.umair.conichi.model.CloudmersiveResponse;
import com.umair.conichi.model.VatRequestDTO;
import com.umair.conichi.model.VatResponseDTO;
import com.umair.conichi.repository.VatRepository;
import static org.mockito.ArgumentMatchers.any;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author umair
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class VatServiceITest {

    @InjectMocks
    private VatServiceI vatServiceI;

    @Mock
    private VatRepository vatRepository;

    @Mock
    private RestClient restClient;

    /**
     * Test of getVerified method, of class VatServiceI.
     */
    @Test
    public void testGetVerifiedHappyPath() {
        System.out.println("getVerified");
        CloudmersiveResponse aMockResponse = new CloudmersiveResponse();
        aMockResponse.setIsValid(true);
        aMockResponse.setCountryCode("GB");
        aMockResponse.setVatNumber("656049569");

        when(restClient.getVatStatus(any(CloudmersiveRequest.class))).thenReturn(aMockResponse);

        VatRequestDTO request = new VatRequestDTO();
        request.setVatNumber("GB656049569");

        ResponseEntity<VatResponseDTO> result = vatServiceI.getVerified(request);
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }
    
    @Test
    public void testGetVerifiedHappyPathInvalidVat() {
        System.out.println("getVerified");
        CloudmersiveResponse aMockResponse = new CloudmersiveResponse();
        aMockResponse.setIsValid(false);

        when(restClient.getVatStatus(any(CloudmersiveRequest.class))).thenReturn(aMockResponse);

        VatRequestDTO request = new VatRequestDTO();
        request.setVatNumber("GB656049569");

        ResponseEntity<VatResponseDTO> result = vatServiceI.getVerified(request);
        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());

    }

}
