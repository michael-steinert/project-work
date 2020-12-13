package com.bike_factory.manufacturingdepartment.controller;

import com.bike_factory.manufacturingdepartment.model.Bike;
import com.bike_factory.manufacturingdepartment.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/v1/bike")
public class BikeController {
    private final BikeService bikeService;

    @Autowired
    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }

    @GetMapping(path = "{bikeUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bike fetchBike(@PathVariable("bikeUid") UUID bikeUid) {
        return bikeService.getBike(bikeUid).orElseThrow(() -> new NotFoundException("Bike " + bikeUid + " not found."));
    }

    @GetMapping(path = "/bikes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bike> fetchBikes() {
        // throw new ApiRequestException("Bike Exception");
        return bikeService.getAllBikes().get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer insertBike(@RequestBody @Valid Bike bike) {
        return bikeService.insertBike(bike);
    }

    @PutMapping(path = "{bikeUid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateBike(@PathVariable("bikeUid") UUID bikeUid, @RequestBody @Valid Bike bike) {
        return bikeService.updateBike(bikeUid, bike);
    }

    @DeleteMapping(path = "{bikeUid}")
    public Integer deleteBike(@PathVariable("bikeUid") UUID bikeUid) {
        return bikeService.removeBike(bikeUid);
    }
}
