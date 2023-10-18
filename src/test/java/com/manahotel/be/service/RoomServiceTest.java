package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
import com.manahotel.be.exception.ResourceNotFoundException;
import com.manahotel.be.model.dto.FloorDTO;
import com.manahotel.be.model.dto.RoomDTO;
import com.manahotel.be.model.entity.Floor;
import com.manahotel.be.model.entity.Room;
import com.manahotel.be.model.entity.RoomCategory;
import com.manahotel.be.repository.FloorRepository;
import com.manahotel.be.repository.RoomRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@SpringBootTest
class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private FloorRepository floorRepository;

    @Mock
    private RoomClassService roomClassService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRoom() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomName("Test Room");
        roomDTO.setRoomCategoryId("HP000001");
        roomDTO.setFloorId(1L);
        roomDTO.setNote("Test Room Note");
//        roomDTO.setCreatedById(1L);

        RoomCategory existingRoomCategory = new RoomCategory();
        existingRoomCategory.setRoomCategoryId("HP000001");

        Floor existingFloor = new Floor();
        existingFloor.setFloorName("Test Floor");

        Mockito.when(roomRepository.findTopByOrderByRoomIdDesc()).thenReturn(new Room());
        Mockito.when(roomClassService.getRoomCategoryById("HP000001")).thenReturn(existingRoomCategory);
        Mockito.when(roomService.getFloorById(1L)).thenReturn(existingFloor);

        String result = roomService.createRoom(roomDTO);
        assertNotNull(result);
        assertEquals("Tạo phòng thành công", result);
    }

    @Test
    public void testUpdateRoom() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomName("Updated Room");
        roomDTO.setRoomCategoryId("HP000002");
        roomDTO.setFloorId(2L);
        roomDTO.setNote("Updated Room Note");
//        roomDTO.setCreatedById(2L);

        Room existingRoom = new Room();
        existingRoom.setRoomId("P000001");

        RoomCategory existingRoomCategory = new RoomCategory();
        existingRoomCategory.setRoomCategoryId("HP000002");

        Floor existingFloor = new Floor();
        existingFloor.setFloorName("Updated Floor");

        Mockito.when(roomRepository.findById("P000001")).thenReturn(java.util.Optional.of(existingRoom));
        Mockito.when(roomClassService.getRoomCategoryById("HP000002")).thenReturn(existingRoomCategory);
        Mockito.when(roomService.getFloorById(2L)).thenReturn(existingFloor);

        String result = roomService.updateRoom("P000001", roomDTO);
        assertNotNull(result);
        assertEquals("Cập nhật phòng thành công", result);
    }

    @Test
    public void testDeleteRoomById() {
        Room existingRoom = new Room();
        existingRoom.setRoomId("P000002");

        Mockito.when(roomRepository.findById("P000002")).thenReturn(java.util.Optional.of(existingRoom));

        String result = roomService.deleteRoomById("P000002");
        assertNotNull(result);
        assertEquals("Xóa phòng thành công", result);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetRoomById_NotFound() {
        Mockito.when(roomRepository.findById("P000003")).thenReturn(java.util.Optional.empty());
        roomService.getRoomById("P000003");
    }

    @Test
    public void testGetRoomById_Found() {
        Room existingRoom = new Room();
        existingRoom.setRoomId("P000004");

        Mockito.when(roomRepository.findById("P000004")).thenReturn(java.util.Optional.of(existingRoom));

        Room result = roomService.getRoomById("P000004");
        assertNotNull(result);
        assertEquals("P000004", result.getRoomId());
    }

    @Test
    public void testGetFloorById() {
        Floor existingFloor = new Floor();
        existingFloor.setFloorName("Test Floor");

        Mockito.when(floorRepository.findById(1L)).thenReturn(java.util.Optional.of(existingFloor));

        Floor result = roomService.getFloorById(1L);
        assertNotNull(result);
        assertEquals("Test Floor", result.getFloorName());
    }

    @Test
    public void testGetAllFloor() {
        List<Floor> floorList = new ArrayList<>();
        Floor floor1 = new Floor();
        floor1.setFloorName("Floor 1");
        floorList.add(floor1);
        Floor floor2 = new Floor();
        floor2.setFloorName("Floor 2");
        floorList.add(floor2);

        Mockito.when(floorRepository.findAll()).thenReturn(floorList);

        List<Floor> result = roomService.getAllFloor();
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateFloor() {
        FloorDTO floorDTO = new FloorDTO();
        floorDTO.setFloorName("Test Floor");
        floorDTO.setStatus(Status.ACTIVATE);

        String result = roomService.createFloor(floorDTO);
        assertNotNull(result);
        assertEquals("Tạo tầng thành công", result);
    }

    @Test
    public void testUpdateFloor() {
        FloorDTO floorDTO = new FloorDTO();
        floorDTO.setFloorName("Updated Floor");
        floorDTO.setStatus(Status.DEACTIVATE);

        Floor existingFloor = new Floor();
        existingFloor.setFloorName("Test Floor");

        Mockito.when(floorRepository.findById(1L)).thenReturn(java.util.Optional.of(existingFloor));

        String result = roomService.updateFloor(1, floorDTO);
        assertNotNull(result);
        assertEquals("Cập nhật tầng thành công", result);
    }

    @Test
    public void testDeleteFloorById() {
        Floor existingFloor = new Floor();
        existingFloor.setFloorName("Test Floor");

        Mockito.when(floorRepository.findById(1L)).thenReturn(java.util.Optional.of(existingFloor));

        String result = roomService.deleteFloorById(1);
        assertNotNull(result);
        assertEquals("Xóa tầng thành công", result);
    }
}
