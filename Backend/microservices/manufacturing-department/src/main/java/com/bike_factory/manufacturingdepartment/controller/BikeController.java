package com.bike_factory.manufacturingdepartment.controller;

import com.bike_factory.manufacturingdepartment.model.Bike;
import com.bike_factory.manufacturingdepartment.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/bikes")
public class BikeController {
    private final BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping
    public List<Bike> getAllBikes() {
        // throw new ApiRequestException("Bike Exception");
        return bikeService.getAllBikes();
    }

    @PostMapping
    public void addNewBike(@RequestBody @Valid Bike bike) {
        bikeService.insertBike(bike);
    }
}
