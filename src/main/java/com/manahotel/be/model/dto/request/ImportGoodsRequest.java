package com.manahotel.be.model.dto.request;

import com.manahotel.be.model.dto.ImportGoodsDTO;
import com.manahotel.be.model.dto.ImportGoodsDetailDTO;
import lombok.Data;

import java.util.List;

@Data
public class ImportGoodsRequest {
    private ImportGoodsDTO importGoodsDTO;
    private List<ImportGoodsDetailDTO> listImportGoodsDetailDTO;
}
