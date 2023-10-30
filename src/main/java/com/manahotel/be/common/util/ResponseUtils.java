package com.manahotel.be.common.util;

import com.manahotel.be.model.dto.ResponseDTO;

public class ResponseUtils {

    public static ResponseDTO success(Object result, String displayMessage) {
        ResponseDTO responseDto = new ResponseDTO();
        responseDto.setResult(result);
        responseDto.setDisplayMessage(displayMessage);
        return responseDto;
    }

    public static ResponseDTO success(String displayMessage) {
        ResponseDTO responseDto = new ResponseDTO();
        responseDto.setDisplayMessage(displayMessage);
        return responseDto;
    }

    public static ResponseDTO error(String errorMessage) {
        ResponseDTO responseDto = new ResponseDTO();
        responseDto.setSuccess(false);
        responseDto.setDisplayMessage(errorMessage);
        return responseDto;
    }
}
