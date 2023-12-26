package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.response.GoodsUnitDTO;
import com.manahotel.be.model.entity.Goods;
import com.manahotel.be.model.entity.GoodsUnit;
import com.manahotel.be.repository.GoodsRepository;
import com.manahotel.be.repository.GoodsUnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GoodsUnitService {

    @Autowired
    private GoodsUnitRepository repository;

    @Autowired
    private GoodsRepository repository2;

    public ResponseEntity<List<GoodsUnit>> getAll() {
        return new ResponseEntity<>(repository.findByStatusNot(Status.DELETE), HttpStatus.OK);
    }

    public ResponseEntity<String> createGoodsUnit(GoodsUnitDTO dto) {
        try {
            log.info("----- Add Unit Start -----");
            GoodsUnit goodsUnit = new GoodsUnit();

            Goods goods = findGoods(dto.getGoodsId());
            goodsUnit.setGoods(goods);

            commonMapping(goodsUnit, dto);

            goodsUnit.setIsDefault(false);
            goodsUnit.setStatus(Status.ACTIVATE);

            repository.save(goodsUnit);
            log.info("----- Add Unit End -----");

            return new ResponseEntity<>("Tạo đơn vị thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't add unit", e.getMessage());
            return new ResponseEntity<>("Tạo đơn vị thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateGoodsUnit(Long id, GoodsUnitDTO dto) {
        try {
            log.info("----- Update Unit Start -----");
            GoodsUnit goodsUnit = findGoodsUnitById(id);

            commonMapping(goodsUnit, dto);

            repository.save(goodsUnit);
            log.info("----- Update Unit End -----");

            return new ResponseEntity<>("Cập nhật đơn vị thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't update unit", e.getMessage());
            return new ResponseEntity<>("Cập nhật đơn vị thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> updateGoodsUnitByList(List<GoodsUnitDTO> goodsUnitDTOs) {
        try {
            log.info("----- Create Update Goods Unit Start -----");
            String goodsId = null;
            for(GoodsUnitDTO dto : goodsUnitDTOs) {
                if(dto.getGoodsId() != null) {
                    goodsId = dto.getGoodsId();
                    break;
                }
            }

            List<GoodsUnit> existingGoodsUnits = repository.getGoodsUnitByGoodsIdNotStatus6(goodsId);
            for(GoodsUnit gu : existingGoodsUnits) {
                if(!goodsUnitDTOs.stream()
                        .anyMatch(dto -> dto.getGoodsUnitId() != null
                        && dto.getGoodsUnitId().equals(gu.getGoodsUnitId()))) {
                    gu.setStatus(Status.DELETE);
                    repository.save(gu);
                    log.info("----- Delete goods unit success ----- " + gu.getGoodsUnitId());
                }
            }

            for (GoodsUnitDTO goodsUnitDTO : goodsUnitDTOs) {
                GoodsUnit goodsUnit;

                if(goodsUnitDTO.getGoodsUnitId() != null) {
                    goodsUnit = findGoodsUnitById(goodsUnitDTO.getGoodsUnitId());

                    if(!goodsUnitDTO.getGoodsUnitName().equals(goodsUnit.getGoodsUnitName())
                    || !goodsUnitDTO.getCost().equals(goodsUnit.getCost())
                    || !goodsUnitDTO.getPrice().equals(goodsUnit.getPrice())) {
                        commonMapping(goodsUnit, goodsUnitDTO);
                        repository.save(goodsUnit);
                    }
                }
                else {
                    goodsUnit = new GoodsUnit();

                    Goods goods = findGoods(goodsUnitDTO.getGoodsId());
                    goodsUnit.setGoods(goods);

                    commonMapping(goodsUnit, goodsUnitDTO);

                    goodsUnit.setIsDefault(false);
                    goodsUnit.setStatus(Status.ACTIVATE);

                    repository.save(goodsUnit);
                }
            }
            log.info("----- Create Update Goods Unit End -----");
            return new ResponseEntity<>("Lưu đơn vị thành công", HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>("Lưu đơn vị thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void commonMapping(GoodsUnit goodsUnit, GoodsUnitDTO goodsUnitDTO) {
        goodsUnit.setGoodsUnitName(goodsUnitDTO.getGoodsUnitName() != null ? goodsUnitDTO.getGoodsUnitName() : goodsUnit.getGoodsUnitName());
        goodsUnit.setPrice(goodsUnitDTO.getPrice() != null ? goodsUnitDTO.getPrice() : goodsUnit.getPrice());
        goodsUnit.setCost(goodsUnitDTO.getCost() != null ? goodsUnitDTO.getCost() : goodsUnit.getCost());
    }

    public ResponseEntity<String> deleteGoodsUnit(String id) {
        try {
            log.info("----- Delete Unit Start -----");
            List<GoodsUnit> listGoodsUnit = repository.findGoodsUnitByGoodsIdAndAndIsDefault(id, false);
            repository.deleteAll(listGoodsUnit);
            log.info("----- Delete Unit End -----");

            return new ResponseEntity<>("Xóa đơn vị thành công", HttpStatus.OK);
        } catch (Exception e) {
            log.info("Can't delete unit", e.getMessage());
            return new ResponseEntity<>("Xóa đơn vị thất bại", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Goods findGoods(String id) {
        return repository2.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods not found with id " + id));
    }

    private GoodsUnit findGoodsUnitById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goods Unit not found with id " + id));
    }
}
