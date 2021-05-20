package com.cinetoile.SpringAPI.Room;


import com.cinetoile.SpringAPI.dto.dtoIn.RoomDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.RoomDTOOut;
import com.cinetoile.SpringAPI.models.RoomEntity;
import com.cinetoile.SpringAPI.models.TheaterEntity;
import com.cinetoile.SpringAPI.repository.RoomRepository;
import com.cinetoile.SpringAPI.services.RoomService;
import com.cinetoile.SpringAPI.services.TheaterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTest {

    @Mock
    RoomRepository repository;

    @Mock
    TheaterService theaterService;

    @InjectMocks
    RoomService service;

    TheaterEntity fakeTheater = new TheaterEntity("Ciné'croyable", "Incroyable", "Rue bidon", "123", "Lille", "59000", "0606060606", "cinema@incroyable.com", null);
    RoomEntity fakeRoom = new RoomEntity("Salle 1", 50, fakeTheater);
    RoomEntity fakeRoom2 = new RoomEntity("Salle 2", 75, fakeTheater);
    List<RoomEntity> fakeRoomList = Arrays.asList(fakeRoom, fakeRoom2);
    RoomDTOIn fakeRoomIn = new RoomDTOIn("Salle 1", 50, fakeTheater.getId());

    @Before
    public void setUp() {
        Mockito.when(repository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(fakeRoom));
        Mockito.when(repository.findAll()).thenReturn(fakeRoomList);
        Mockito.when(repository.save(Mockito.any(RoomEntity.class))).thenReturn(fakeRoom);
    }

    @Test
    public void testGetRoomById() {
        RoomEntity room = service.findById(1);

        Assert.assertEquals("", room.getId(), fakeRoom.getId());
    }

    @Test
    public void testGetAllRooms() {
        List<RoomDTOOut> roomList = service.findAll();
        Assert.assertEquals("", roomList.size(), fakeRoomList.size());
    }

    @Test
    public void testGetRoomCreation() {
        RoomDTOOut newRoom = service.add(fakeRoomIn);

        Assert.assertEquals("", newRoom.getName(), fakeRoomIn.getName());
    }

    @Test
    public void testRoomEdition() {
        RoomDTOIn updatedRoomReceived = new RoomDTOIn("Salle 1", 75, fakeTheater.getId());

        RoomDTOOut updatedRoom = service.update(updatedRoomReceived, 1);

        Assert.assertEquals("", updatedRoom.getPlace(), updatedRoomReceived.getPlace());
    }

}
