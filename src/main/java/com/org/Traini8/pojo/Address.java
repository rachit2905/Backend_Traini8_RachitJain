package com.org.Traini8.pojo;

import com.org.Traini8.validators.ValidPincode;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents an address entity within the application. This class is used to
 * store address details of entities like training centers. Each field in the
 * class is annotated to ensure it aligns with database requirements and
 * validation rules for data integrity.
 */
public class Address {

    @Column(name = "detailedAddress", nullable = false)
    @NotBlank(message = "Detailed address is required") // Ensures the detailed address is not blank
    private String detailedAddress;

    @Column(name = "city", nullable = false)
    @NotBlank(message = "City is required") // Ensures the city name is not blank
    private String city;

    @Column(name = "state", nullable = false)
    @NotBlank(message = "State is required") // Ensures the state name is not blank
    private String state;

    @Column(name = "pincode", nullable = false, length = 6)
    @NotBlank(message = "Pincode is required") // Ensures the pincode is not blank
    @ValidPincode // Custom validation annotation to ensure the pincode meets a specific pattern
                  // or criteria
    private String pincode;

    // Getters

    /**
     * Returns the city of the address.
     *
     * @return the city part of the address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the detailed address.
     *
     * @return the detailed address of the entity.
     */
    public String getDetailedAddress() {
        return detailedAddress;
    }

    /**
     * Returns the pincode of the address.
     *
     * @return the pincode part of the address.
     */
    public String getPincode() {
        return pincode;
    }

    /**
     * Returns the state of the address.
     *
     * @return the state part of the address.
     */
    public String getState() {
        return state;
    }

    // Setters

    /**
     * Sets the city of the address.
     *
     * @param city New city.
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Sets the detailed address of the entity.
     *
     * @param detailedAddress New detailed address.
     */
    public void setDetailedAddress(final String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    /**
     * Sets the pincode of the address.
     *
     * @param pincode New pincode that adheres to the specified validation rules.
     */
    public void setPincode(final String pincode) {
        this.pincode = pincode;
    }

    /**
     * Sets the state of the address.
     *
     * @param state New state.
     */
    public void setState(final String state) {
        this.state = state;
    }
}
