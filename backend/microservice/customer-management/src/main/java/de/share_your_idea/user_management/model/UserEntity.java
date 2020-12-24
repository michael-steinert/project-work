package de.share_your_idea.user_management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEntity {
    private UUID customerUid;
    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    private final Gender gender;
    @NotNull
    @Email
    private final String email;
    @NotNull
    private final String street;
    @NotNull
    private final String houseNumber;
    @NotNull
    @Max(value = 99999)
    @Min(value = 11111)
    private final Integer zipCode;
    @NotNull
    private final String city;

    public enum Gender {
        MALE,
        FEMALE
    }

    public UserEntity(@JsonProperty("customerUid") UUID customerUid,
                      @JsonProperty("firstName") String firstName,
                      @JsonProperty("lastName") String lastName,
                      @JsonProperty("gender") Gender gender,
                      @JsonProperty("email") String email,
                      @JsonProperty("street") String street,
                      @JsonProperty("houseNumber") String houseNumber,
                      @JsonProperty("zipCode") Integer zipCode,
                      @JsonProperty("city") String city) {
        this.customerUid = customerUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    public static UserEntity newCustomer(UUID customerUid, UserEntity userEntity) {
        return new UserEntity(customerUid, userEntity.getFirstName(), userEntity.getLastName(), userEntity.getGender(), userEntity.getEmail(), userEntity.getStreet(), userEntity.getHouseNumber(), userEntity.getZipCode(), userEntity.getCity());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerUid=" + customerUid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", zipCode=" + zipCode +
                ", city='" + city + '\'' +
                '}';
    }
}
