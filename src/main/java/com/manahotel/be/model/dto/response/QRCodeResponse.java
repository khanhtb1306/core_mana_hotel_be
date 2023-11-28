package com.manahotel.be.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class QRCodeResponse {
    private String transactionCode;

    @JsonProperty("data")
    private QRCodeData data;

    @Data
    public static class QRCodeData {
        @JsonProperty("qrDataURL")
        private String qrDataURL;
    }
}
