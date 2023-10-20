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

        RoomCategory roomCategory = new RoomCategory();
        roomCategory.setRoomCategoryId("HP000001");

        Mockito.when(roomClassRepository.findTopByOrderByRoomCategoryIdDesc()).thenReturn(null);
        Mockito.when(roomClassRepository.save(Mockito.any(RoomCategory.class))).thenReturn(roomCategory);

        String result = roomClassService.createRoomClass(dto);
        assertEquals("CreateRoomClassSuccess", result); // Update the expected result
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

        // Mock the behavior of repository
        Mockito.when(roomClassRepository.findById(id)).thenReturn(java.util.Optional.of(roomCategory));
        Mockito.when(roomClassRepository.save(roomCategory)).thenReturn(roomCategory);

        // Execute the method
        String result = roomClassService.updateRoomClass(id, dto);

        // Verify the method behavior
        assertEquals("UpdateRoomClassSuccess", result);

        // Verify that save() method was called on the repository
        Mockito.verify(roomClassRepository).save(roomCategory);
    }

    @Test
    public void testDeleteRoomClassById() {
        String id = "HP000001";

        // Scenario 1: Successfully deleting a room class
//        RoomCategory roomCategory1 = new RoomCategory();
//        roomCategory1.setRoomCategoryId(id);
//        roomCategory1.setStatus(Status.ACTIVATE);
//
//        Mockito.when(roomClassRepository.findById(id)).thenReturn(java.util.Optional.of(roomCategory1));
//        Mockito.when(roomClassRepository.save(roomCategory1)).thenReturn(roomCategory1);
//
//        String result1 = String.valueOf(roomClassService.deleteRoomClassById(id));
//
//        assertEquals("success", result1);
//        assertEquals(Status.DELETE, roomCategory1.getStatus());
//        Mockito.verify(roomClassRepository).save(roomCategory1);

//        // Scenario 2: Room class not found
//        Mockito.when(roomClassRepository.findById(id)).thenReturn(java.util.Optional.empty());
//
//        String result2 = roomClassService.deleteRoomClassById(id);
//
//        assertEquals("NOT_FOUND", result2); // Now the test should pass

        // Scenario 3: Room class has rooms associated with it
//        RoomCategory roomCategory3 = new RoomCategory();
//        roomCategory3.setRoomCategoryId(id);
//        roomCategory3.setStatus(Status.ACTIVATE);
//
//        // Mock the behavior of repository
//        Mockito.when(roomClassRepository.findById(id)).thenReturn(java.util.Optional.of(roomCategory3));
//        // Mock the method roomClassHasRooms to return true
//        Mockito.when(roomClassService.roomClassHasRooms(roomCategory3)).thenReturn(true);
//
//        String result3 = roomClassService.deleteRoomClassById(id);
//
//        assertEquals("BAD_REQUEST", result3);
//
//        // Verify that the room class status and repository were not modified or called
//        assertEquals(Status.ACTIVATE, roomCategory3.getStatus());
//        Mockito.verifyNoMoreInteractions(roomClassRepository);

    }
}
