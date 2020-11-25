package com.bike_factory.manufacturingdepartment.service;

import com.bike_factory.manufacturingdepartment.dao.BikeDao;
import com.bike_factory.manufacturingdepartment.model.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BikeService {
    private BikeDao bikeDao;

    @Autowired
    public BikeService(BikeDao bikeDao) {
        this.bikeDao = bikeDao;
    }

    public Optional<List<Bike>> getAllBikes() {
        return Optional.ofNullable(bikeDao.selectAllBikes());
    }

    public Optional<Bike> getBike(UUID bikeUid) {
        return Optional.ofNullable(bikeDao.selectBikeByBikeUid(bikeUid));
    }

    public int updateBike(Bike bike) {
        Optional<Bike> optionalBike = getBike(bike.getBikeUid());
        if (optionalBike.isPresent()) {
            return bikeDao.updateBike(bike);
        }
        throw new NotFoundException("Bike " + bike.getBikeUid() + " not found.");
    }

    public int removeBike(UUID uid) {
        UUID bikeUid = getBike(uid)
                .map(Bike::getBikeUid)
                .orElseThrow(() -> new NotFoundException("Bike " + uid + " not found."));
        return bikeDao.deleteBikeByBikeUid(bikeUid);
    }

    public int insertBike(Bike bike) {
        return bikeDao.insertBike(Bike.newBike(UUID.randomUUID(), bike));
    }
}
