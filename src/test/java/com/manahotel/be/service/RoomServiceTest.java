package com.manahotel.be.service;

import com.manahotel.be.common.constant.Status;
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

import java.util.Optional;

import static org.junit.Assert.*;

public class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private FloorRepository floorRepository;

    @Mock
    private RoomClassService roomClassService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateRoom() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomName("Test Room");
        roomDTO.setRoomCategoryId("HP000001");
        roomDTO.setFloorId(1L);
        roomDTO.setNote("Test Room Note");

        RoomCategory existingRoomCategory = new RoomCategory();
        existingRoomCategory.setRoomCategoryId("HP000001");

        Floor existingFloor = new Floor();
        existingFloor.setFloorName("Test Floor");

        Mockito.when(roomRepository.findTopByOrderByRoomIdDesc()).thenReturn(null); // Assuming no existing room
        Mockito.when(roomClassService.getRoomCategoryById("HP000001")).thenReturn(existingRoomCategory);
        Mockito.when(roomService.getFloorById(1L)).thenReturn(existingFloor);

        String result = roomService.createRoom(roomDTO);
        assertNotNull(result);
        assertEquals("CreateRoomSuccess", result);
    }

    @Test
    public void testUpdateRoom() {
        String roomId = "HP000001";

        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomName("Updated Room");
        roomDTO.setRoomCategoryId("HP000002");
        roomDTO.setFloorId(2L); // New floor
        roomDTO.setNote("Updated Room Note");

        Room existingRoom = new Room();
        existingRoom.setRoomId(roomId);

        RoomCategory updatedRoomCategory = new RoomCategory();
        updatedRoomCategory.setRoomCategoryId("HP000002");

        Floor updatedFloor = new Floor();
        updatedFloor.setFloorName("New Floor"); // New floor

        Mockito.when(roomRepository.findById(roomId)).thenReturn(Optional.of(existingRoom));
        Mockito.when(roomClassService.getRoomCategoryById("HP000002")).thenReturn(updatedRoomCategory);
        Mockito.when(roomService.getFloorById(2L)).thenReturn(updatedFloor);

        String result = roomService.updateRoom(roomId, roomDTO);
        assertNotNull(result);
        assertEquals("UpdateRoomSuccess", result);
    }


    @Test
    public void testCreateFloor() {
        FloorDTO floorDTO = new FloorDTO();
        floorDTO.setFloorName("Test Floor");
        floorDTO.setStatus(Status.ACTIVATE);

        Mockito.when(floorRepository.save(Mockito.any(Floor.class))).thenReturn(new Floor()); // Assuming the floor is saved successfully

        String result = roomService.createFloor(floorDTO);
        assertNotNull(result);
        assertEquals("CreateFloorSuccess", result); // Assuming the method returns "CreateFloorSuccess" upon successful creation
    }

    @Test
    public void testUpdateFloor() {
        int floorId = 1; // Assuming an existing floor with this ID

        FloorDTO floorDTO = new FloorDTO();
        floorDTO.setFloorName("Updated Floor");
        floorDTO.setStatus(Status.ACTIVATE);

        Floor existingFloor = new Floor();
        existingFloor.setFloorId((long) floorId);

        Mockito.when(floorRepository.findById((long) floorId)).thenReturn(Optional.of(existingFloor));
        Mockito.when(floorRepository.save(Mockito.any(Floor.class))).thenReturn(existingFloor); // Assuming the floor is updated successfully

        String result = roomService.updateFloor(floorId, floorDTO);
        assertNotNull(result);
        assertEquals("UpdateFloorSuccess", result); // Assuming the method returns "UpdateFloorSuccess" upon successful update
    }
}
