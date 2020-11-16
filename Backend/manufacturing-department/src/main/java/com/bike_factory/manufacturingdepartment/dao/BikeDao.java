package com.bike_factory.manufacturingdepartment.dao;

import com.bike_factory.manufacturingdepartment.model.Bike;

import java.util.List;
import java.util.UUID;

public interface BikeDao {
    List<Bike> selectAllBikes();
    Bike selectBikeByBikeUid(UUID bikeUid);
    int insertBike(Bike bike);
    int updateBike(Bike bike);
    int deleteBikeByBikeUid(UUID bikeUid);
}
