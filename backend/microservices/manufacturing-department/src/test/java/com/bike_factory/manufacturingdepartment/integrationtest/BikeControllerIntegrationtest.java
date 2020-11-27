package com.bike_factory.manufacturingdepartment.integrationtest;

import com.bike_factory.manufacturingdepartment.controller.BikeController;
import com.bike_factory.manufacturingdepartment.model.Bike;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class BikeControllerIntegrationtest {

    @Autowired
    private BikeController bikeController;

    @Test
    public void itShouldFetchAllBikes() throws Exception {
        //Given
        UUID bikeUid = UUID.randomUUID();
        Bike bike = new Bike(bikeUid,"Cube Agree", "Cube Agree is a Racing Bike", "Racing Bike", Bike.BikeType.RACINGBIKE, 599.99f);

        //When
        bikeController.insertBike(bike);
        List<Bike> bikes = bikeController.fetchBikes();

        //Then
        assertThat(bikes).hasSizeGreaterThanOrEqualTo(1);
        assertThat(bikes).extracting("bikeUid").contains(bike.getBikeUid());
        assertThat(bikes).extracting("bikeName").contains(bike.getBikeName());
        assertThat(bikes).extracting("description").contains(bike.getDescription());
        assertThat(bikes).extracting("shortDescription").contains(bike.getShortDescription());
        assertThat(bikes).extracting("bikeType").contains(Bike.BikeType.RACINGBIKE);
        assertThat(bikes).extracting("price").contains(bike.getPrice());
    }

    @Test
    public void itShouldFetchBikeByUid() throws Exception {
        //Given
        UUID bikeUid = UUID.randomUUID();
        Bike bike = new Bike(bikeUid,"Cube Agree", "Cube Agree is a Racing Bike", "Racing Bike", Bike.BikeType.RACINGBIKE, 599.99f);

        //When
        bikeController.insertBike(bike);

        //Then
        Bike bikeFromController = bikeController.fetchBike(bikeUid);
        assertThat(bikeFromController).isEqualToComparingFieldByField(bike);
    }

    @Test
    public void itShouldInsertBike() throws Exception {
        //Given
        UUID bikeUid = UUID.randomUUID();
        Bike bike = new Bike(bikeUid,"Cube Agree", "Cube Agree is a Racing Bike", "Racing Bike", Bike.BikeType.RACINGBIKE, 599.99f);

        //When
        bikeController.insertBike(bike);

        //Then
        Bike bikeFromController = bikeController.fetchBike(bikeUid);
        assertThat(bikeFromController).isEqualToComparingFieldByField(bike);
    }

    @Test
    public void itShouldUpdateBike() throws Exception {
        //Given
        UUID bikeUid = UUID.randomUUID();
        Bike bike = new Bike(bikeUid,"Cube Agree", "Cube Agree is a Racing Bike", "Racing Bike", Bike.BikeType.RACINGBIKE, 599.99f);

        //When
        bikeController.insertBike(bike);
        Bike newBike = new Bike(bikeUid,"Cube Attain", "Cube Attain is a Racing Bike", "Racing Bike", Bike.BikeType.RACINGBIKE, 499.99f);
        bikeController.updateBike(bikeUid, newBike);

        //Then
        Bike bikeFromController = bikeController.fetchBike(bikeUid);
        assertThat(bikeFromController).isEqualToComparingFieldByField(newBike);
    }

    @Test
    public void itShouldDeleteBike() throws Exception {
        //Given
        UUID bikeUid = UUID.randomUUID();
        Bike bike = new Bike(bikeUid,"Cube Agree", "Cube Agree is a Racing Bike", "Racing Bike", Bike.BikeType.RACINGBIKE, 599.99f);

        //When
        bikeController.insertBike(bike);
        bikeController.deleteBike(bikeUid);

        //Then
        //assertThat(userController.fetchUser(userUid)).isEqualTo(NotFoundException.class);
    }
}