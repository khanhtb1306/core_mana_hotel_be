package com.manahotel.be.service;

import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.PriceListRepository;
import com.manahotel.be.repository.RoomClassRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomClassService {
    @Autowired
    private RoomClassRepository roomClassRepository;

    @Autowired
    PriceListRepository priceListRepository;

    public List<RoomCategory>getAllRoomClass(){
        return roomClassRepository.findAll();
    }
}
