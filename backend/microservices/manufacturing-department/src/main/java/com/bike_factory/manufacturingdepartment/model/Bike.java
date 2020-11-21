package com.bike_factory.manufacturingdepartment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bike {
    private UUID bikeUid;
    @NotNull
    private final String bikeName;
    @NotNull
    private final String description;
    @NotNull
    private final String shortDescription;
    @NotNull
    private final BikeType bikeType;
    @NotNull
    @Min(value = 1)
    private final Float price;

    public enum BikeType {
        RACINGBIKE,
        MOUNTAINBIKE,
        CITYBIKE
    }

    public Bike(@JsonProperty("bikeUid") UUID bikeUid,
                @JsonProperty("bikeName") String bikeName,
                @JsonProperty("description") String description,
                @JsonProperty("shortDescription") String shortDescription,
                @JsonProperty("bikeType") BikeType bikeType,
                @JsonProperty("price") Float price) {
        this.bikeUid = bikeUid;
        this.bikeName = bikeName;
        this.description = description;
        this.shortDescription = shortDescription;
        this.bikeType = bikeType;
        this.price = price;
    }

    public UUID getBikeUid() {
        return bikeUid;
    }

    public String getBikeName() {
        return bikeName;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public BikeType getBikeType() {
        return bikeType;
    }

    public Float getPrice() {
        return price;
    }

    public static Bike newBike(UUID bikeUid, Bike bike) {
        return new Bike(bikeUid, bike.getBikeName(), bike.description, bike.shortDescription, bike.getBikeType(), bike.getPrice());
    }

    @Override
    public String toString() {
        return "Bike{" +
                "bikeUid=" + bikeUid +
                ", bikeName='" + bikeName + '\'' +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", bikeType=" + bikeType +
                ", price=" + price +
                '}';
    }
}
