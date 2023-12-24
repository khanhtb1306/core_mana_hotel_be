package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.model.dto.response.RoomCategoryDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Mockito.when(roomClassRepository.findTopByOrderByRoomCategoryIdDesc()).thenReturn(null);

        RoomCategoryDTO dto = new RoomCategoryDTO();
        dto.setRoomCategoryName("Create Room Category");
        dto.setPriceByDay(120.0F);
        dto.setPriceByNight(100.0F);
        dto.setPriceByHour(25.0F);
        dto.setNumOfAdults(3L);
        dto.setNumOfChildren(3L);
        dto.setRoomArea(35F);
        dto.setDescription("Create Description");
        ResponseEntity<String> result = roomClassService.createRoomClass(dto);

        if (result.getStatusCode() == HttpStatus.OK) {
            assertEquals("Thêm hạng phòng thành công", result.getBody());
        } else {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
            assertEquals("Thêm hạng phòng thất bại", result.getBody());
        }
    }

    @Test
    public void testUpdateRoomClass() {
        String id = "HP000001";
        RoomCategoryDTO dto = new RoomCategoryDTO();
        dto.setRoomCategoryName("Updated Room Category");
        dto.setPriceByDay(120.0F);
        dto.setPriceByNight(100.0F);
        dto.setPriceByHour(25.0F);
        dto.setNumOfAdults(3L);
        dto.setNumOfChildren(3L);
        dto.setRoomArea(35F);
        dto.setDescription("Updated Description");

        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setRoomCategoryId(id);
        Mockito.when(roomClassRepository.findById(id)).thenReturn(java.util.Optional.of(roomCategory));
        Mockito.when(roomClassRepository.save(roomCategory)).thenReturn(roomCategory);

        // Execute the method
        ResponseEntity<String> result = roomClassService.updateRoomClass(id, dto);

        if (result.getStatusCode() == HttpStatus.OK) {
            assertEquals("Cập nhật hạng phòng thành công", result.getBody());
        } else {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
            assertEquals("Cập nhật hạng phòng thất bại", result.getBody());
        }
    }

    @Test
    public void testDeleteRoomClassById() {
        // Scenario 1: Successfully deleting a room class
        String id1 = "HP000001";
        RoomCategory roomCategory1 = new RoomCategory();
        roomCategory1.setRoomCategoryId(id1);
        roomCategory1.setStatus(Status.ACTIVATE);

        Mockito.when(roomClassRepository.findById(id1)).thenReturn(java.util.Optional.of(roomCategory1));

        ResponseEntity<String> result1 = roomClassService.deleteRoomClassById(id1);
        assertEquals(HttpStatus.OK, result1.getStatusCode());
        assertEquals("Xóa hạng phòng thành công", result1.getBody());
        assertEquals(Status.DELETE, roomCategory1.getStatus());
        Mockito.verify(roomClassRepository).save(roomCategory1);

        // Scenario 2: Room class not found
        String id2 = "NonExistentID";
        Mockito.when(roomClassRepository.findById(id2)).thenReturn(java.util.Optional.empty());

        ResponseEntity<String> result2 = roomClassService.deleteRoomClassById(id2);
        assertEquals(HttpStatus.NOT_FOUND, result2.getStatusCode());
        assertEquals("Không tìm thấy hạng phòng", result2.getBody());

    }
}
