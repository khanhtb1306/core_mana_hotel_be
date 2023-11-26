package com.manahotel.be.model.dto.request;

import lombok.Data;

@Data
public class QRCodeRequest {
    public String accountNo;
    public String accountName;
    public int acqId;
    public int amount;
    public String addInfo;
    public String format;
    public String template;
}
