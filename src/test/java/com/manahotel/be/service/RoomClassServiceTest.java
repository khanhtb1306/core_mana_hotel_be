package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.model.dto.RoomCategoryDTO;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.RoomClassRepository;
import com.manahotel.be.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RoomClassServiceTest {

    @Mock
    private RoomClassRepository roomClassRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomClassService roomClassService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRoomClass() {
        RoomCategoryDTO dto = new RoomCategoryDTO();
        dto.setRoomCategoryName("Test Room Category");
        dto.setPriceByDay(100.0F);
        dto.setPriceByNight(80.0F);
        dto.setPriceByHour(20.0F);
        dto.setRoomCapacity(2L);
        dto.setRoomArea(30.0F);
        dto.setDescription("Test Description");
        dto.setCreatedById(1L);

        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setRoomCategoryId("HP000001");
        roomCategory.setRoomCategoryName(dto.getRoomCategoryName());
        roomCategory.setPriceByDay(dto.getPriceByDay());
        roomCategory.setPriceByNight(dto.getPriceByNight());
        roomCategory.setPriceByHour(dto.getPriceByHour());
        roomCategory.setRoomCapacity(dto.getRoomCapacity());
        roomCategory.setRoomArea(dto.getRoomArea());
        roomCategory.setDescription(dto.getDescription());
        roomCategory.setStatus(Status.ACTIVATE);
        roomCategory.setCreatedDate(Mockito.any(Timestamp.class));
        roomCategory.setCreatedById(dto.getCreatedById());

        Mockito.when(roomClassRepository.findTopByOrderByRoomCategoryIdDesc()).thenReturn(null);
        Mockito.when(roomClassRepository.save(Mockito.any(RoomCategory.class))).thenReturn(roomCategory);

        String result = roomClassService.createRoomClass(dto);
        assertEquals("Tạo hạng phòng thành công", result);
    }

    @Test
    public void testUpdateRoomClass() {
        String id = "HP000001";
        RoomCategoryDTO dto = new RoomCategoryDTO();
        dto.setRoomCategoryName("Updated Room Category");
        dto.setPriceByDay(120.0F);
        dto.setPriceByNight(100.0F);
        dto.setPriceByHour(25.0F);
        dto.setRoomCapacity(3L);
        dto.setRoomArea(35F);
        dto.setDescription("Updated Description");
        dto.setCreatedById(2L);

        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setRoomCategoryId(id);
        roomCategory.setRoomCategoryName(dto.getRoomCategoryName());
        roomCategory.setPriceByDay(dto.getPriceByDay());
        roomCategory.setPriceByNight(dto.getPriceByNight());
        roomCategory.setPriceByHour(dto.getPriceByHour());
        roomCategory.setRoomCapacity(dto.getRoomCapacity());
        roomCategory.setRoomArea(dto.getRoomArea());
        roomCategory.setDescription(dto.getDescription());
        roomCategory.setUpdatedDate(Mockito.any(Timestamp.class));
        roomCategory.setUpdatedById(dto.getCreatedById());

        Mockito.when(roomClassRepository.findById(id)).thenReturn(java.util.Optional.of(roomCategory));
        Mockito.when(roomClassRepository.save(Mockito.any(RoomCategory.class))).thenReturn(roomCategory);

        String result = roomClassService.updateRoomClass(id, dto);
        assertEquals("Cập nhật hạng phòng thành công", result);
    }

    @Test
    public void testDeleteRoomClassById() {
        String id = "HP000001";
        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setRoomCategoryId(id);
        roomCategory.setStatus(Status.ACTIVATE);

        Mockito.when(roomClassRepository.findById(id)).thenReturn(java.util.Optional.of(roomCategory));

        String result = roomClassService.deleteRoomClassById(id);
        assertEquals("Xóa hạng phòng thành công", result);
    }

    @Test
    public void testDeleteRoomClassByIdNotFound() {
        String id = "HP000001";

        Mockito.when(roomClassRepository.findById(id)).thenReturn(java.util.Optional.empty());

        String result = roomClassService.deleteRoomClassById(id);
        assertEquals(null, result);
    }
}
