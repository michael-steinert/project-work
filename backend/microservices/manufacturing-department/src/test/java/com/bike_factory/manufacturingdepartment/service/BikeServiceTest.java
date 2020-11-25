package com.bike_factory.manufacturingdepartment.service;

import com.bike_factory.manufacturingdepartment.dao.BikeDao;
import com.bike_factory.manufacturingdepartment.model.Bike;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class BikeServiceTest {

    @Mock
    private BikeDao bikeDao;
    private BikeService bikeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        bikeService = new BikeService(bikeDao);
    }

    @Test
    void shouldGetAllBikes() {
        UUID bikeUid = UUID.randomUUID();
        Bike bike = new Bike(bikeUid,"Cube Agree", "Cube Agree is a Racing Bike", "Racing Bike", Bike.BikeType.RACINGBIKE, 599.99f);
        ImmutableList<Bike> bikeImmutableList = new ImmutableList.Builder<Bike>().add(bike).build();

        given(bikeDao.selectAllBikes()).willReturn(bikeImmutableList);

        Optional<List<Bike>> allOptionalBikes = bikeService.getAllBikes();
        List<Bike> allBikes = allOptionalBikes.get();
        Bike bikeFromService = allBikes.get(0);

        assertThat(allOptionalBikes.isPresent()).isTrue();
        assertThat(allBikes).hasSize(1);
        assertThat(bikeFromService.getBikeUid()).isNotNull();
        assertThat(bikeFromService.getBikeUid()).isEqualTo(bikeUid);
        assertThat(bikeFromService.getBikeUid()).isInstanceOf(UUID.class);
        assertThat(bikeFromService.getBikeName()).isEqualTo("Cube Agree");
        assertThat(bikeFromService.getDescription()).isEqualTo("Cube Agree is a Racing Bike");
        assertThat(bikeFromService.getShortDescription()).isEqualTo("Racing Bike");
        assertThat(bikeFromService.getBikeType()).isEqualTo(Bike.BikeType.RACINGBIKE);
        assertThat(bikeFromService.getPrice()).isEqualTo(599.99f);
    }

    @Test
    void shouldGetBike() {
        UUID bikeUid = UUID.randomUUID();
        Bike bike = new Bike(bikeUid,"Cube Agree", "Cube Agree is a Racing Bike", "Racing Bike", Bike.BikeType.RACINGBIKE, 599.99f);

        given(bikeDao.selectBikeByBikeUid(bikeUid)).willReturn(bike);

        Optional<Bike> optionalBikeFromService = bikeService.getBike(bikeUid);
        Bike bikeFromService = optionalBikeFromService.get();

        assertThat(bikeFromService.getBikeUid()).isNotNull();
        assertThat(bikeFromService.getBikeUid()).isEqualTo(bikeUid);
        assertThat(bikeFromService.getBikeUid()).isInstanceOf(UUID.class);
        assertThat(bikeFromService.getBikeName()).isEqualTo("Cube Agree");
        assertThat(bikeFromService.getDescription()).isEqualTo("Cube Agree is a Racing Bike");
        assertThat(bikeFromService.getShortDescription()).isEqualTo("Racing Bike");
        assertThat(bikeFromService.getBikeType()).isEqualTo(Bike.BikeType.RACINGBIKE);
        assertThat(bikeFromService.getPrice()).isEqualTo(599.99f);
    }

    @Test
    void shouldInsertBike() {
        UUID bikeUid = UUID.randomUUID();
        Bike bike = new Bike(bikeUid,"Cube Agree", "Cube Agree is a Racing Bike", "Racing Bike", Bike.BikeType.RACINGBIKE, 599.99f);
        ArgumentCaptor<Bike> captor = ArgumentCaptor.forClass(Bike.class);

        given(bikeDao.insertBike(any(Bike.class))).willReturn(1);

        int insertResult = bikeService.insertBike(bike);
        verify(bikeDao).insertBike(captor.capture());
        Bike bikeFromCaptor = captor.getValue();

        assertThat(bikeFromCaptor.getBikeUid()).isNotNull();
        assertThat(bikeFromCaptor.getBikeUid()).isInstanceOf(UUID.class);
        assertThat(bikeFromCaptor.getBikeName()).isEqualTo("Cube Agree");
        assertThat(bikeFromCaptor.getDescription()).isEqualTo("Cube Agree is a Racing Bike");
        assertThat(bikeFromCaptor.getShortDescription()).isEqualTo("Racing Bike");
        assertThat(bikeFromCaptor.getBikeType()).isEqualTo(Bike.BikeType.RACINGBIKE);
        assertThat(bikeFromCaptor.getPrice()).isEqualTo(599.99f);
    }
}

