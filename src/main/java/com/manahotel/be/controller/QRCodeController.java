package com.manahotel.be.controller;

import com.manahotel.be.model.dto.request.QRCodeRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/qr-code")
public class QRCodeController {

    @PostMapping
    public ResponseEntity<String> generateQRCode(QRCodeRequest qrCodeRequest) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<QRCodeRequest> entity = new HttpEntity<>(qrCodeRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.vietqr.io/v2/generate",
                HttpMethod.POST,
                entity,
                String.class
        );

        return response;
    }
}
