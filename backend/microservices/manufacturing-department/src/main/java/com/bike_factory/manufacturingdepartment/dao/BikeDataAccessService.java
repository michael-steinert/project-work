package com.bike_factory.manufacturingdepartment.dao;

import com.bike_factory.manufacturingdepartment.model.Bike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class BikeDataAccessService implements BikeDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BikeDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Bike> selectAllBikes() {
        String sql = "SELECT bike_id, bike_name, description, short_description, bike_type, price FROM bike";
        return jdbcTemplate.query(sql, mapBikeFromDb());
    }

    public Bike selectBikeByBikeUid(UUID bikeUid) {
        String sql = "SELECT bike_id, bike_name, description, short_description, bike_type, price FROM bike WHERE bike_id = ?";
        return jdbcTemplate.queryForObject(sql, mapBikeFromDb(), bikeUid);
    }

    public int insertBike(Bike bike) {
        String sql = "INSERT INTO bike (bike_id, bike_name, description, short_description, bike_type, price) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, bike.getBikeUid(), bike.getBikeName(), bike.getDescription(), bike.getShortDescription(), bike.getBikeType().name().toUpperCase(), bike.getPrice());
    }

    public int updateBike(UUID bikeUid, Bike bike) {
        String sql = "UPDATE bike SET bike_name = ?, description = ?, short_description = ?, bike_type = ?, price = ? WHERE bike_id = ?";
        return jdbcTemplate.update(sql, bike.getBikeName(), bike.getDescription(), bike.getShortDescription(), bike.getBikeType().name().toUpperCase(), bike.getPrice(), bikeUid);
    }

    public int deleteBikeByBikeUid(UUID bikeUid) {
        String sql = "DELETE FROM bike where bike_id = ?";
        return jdbcTemplate.update(sql, bikeUid);
    }

    private RowMapper<Bike> mapBikeFromDb() {
        return (resultSet, i) -> {
            String bikeIdStr = resultSet.getString("bike_id");
            UUID bikeUid = UUID.fromString(bikeIdStr);
            String bikeName = resultSet.getString("bike_name");
            String description = resultSet.getString("description");
            String shortDescription = resultSet.getString("short_description");
            String bikeTypeStr = resultSet.getString("bike_type").toUpperCase();
            Bike.BikeType bikeType = Bike.BikeType.valueOf(bikeTypeStr);
            Float price = Float.parseFloat(resultSet.getString("price"));
            return new Bike(bikeUid, bikeName, description, shortDescription, bikeType, price);
        };
    }
}
