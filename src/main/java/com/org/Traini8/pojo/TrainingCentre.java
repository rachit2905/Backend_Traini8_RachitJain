package com.org.Traini8.pojo;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.org.Traini8.validators.ValidCenterCode;
import com.org.Traini8.validators.ValidContactPhone;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a training center entity within the application. This class is
 * mapped to the "TrainingCentre" table in the database.
 */
@Entity
@Table(name = "TrainingCentre")
public class TrainingCentre {

    @Id
    @Column(name = "centerCode", nullable = false, length = 12)
    @NotBlank(message = "Center code is required") // Center code should not be blank
    @ValidCenterCode // Custom validation for center code format
    private String centerCode;

    @Column(name = "centerName", nullable = false, length = 40)
    @NotBlank(message = "Center name is required") // Center name should not be blank
    @Size(max = 40, message = "Center name must be less than 40 characters") // Center name length validation
    private String centerName;

    @Embedded
    @Valid
    @NotNull(message = "Address is required") // Address should not be null
    private Address address; // Address of the training center

    @Column(name = "studentCapacity")
    @NotNull(message = "Student capacity is required") // Student capacity should not be null
    @Min(value = 10, message = "Student Capacity should be at least 10") // Minimum student capacity validation
    private Integer studentCapacity; // Maximum number of students the center can accommodate

    @ElementCollection
    @CollectionTable(name = "courses_offered", joinColumns = @JoinColumn(name = "centerCode"))
    @Column(name = "course")
    @NotEmpty(message = "At least one course must be offered") // At least one course should be offered
    private List<String> coursesOffered; // List of courses offered by the center

    @Column(name = "createdOn", updatable = false)
    private Long createdOn = Instant.now().toEpochMilli(); // Timestamp when the center was created

    @Column(name = "contactEmail")
    @Email(message = "Invalid email format") // Email format validation
    private String contactEmail; // Email address of the center

    @Column(name = "contactPhone", nullable = false)
    @NotBlank(message = "Contact phone is required") // Contact phone should not be blank
    @ValidContactPhone // Custom validation for contact phone format
    private String contactPhone; // Phone number of the center

    // Getters and setters with detailed comments

    /**
     * Gets the address of the training center.
     *
     * @return The address of the training center.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets the center code.
     *
     * @return The center code.
     */
    public String getCenterCode() {
        return centerCode;
    }

    /**
     * Gets the name of the training center.
     *
     * @return The name of the training center.
     */
    public String getCenterName() {
        return centerName;
    }

    /**
     * Gets the email address of the training center.
     *
     * @return The email address of the training center.
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * Gets the contact phone number of the training center.
     *
     * @return The contact phone number of the training center.
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Gets the list of courses offered by the training center.
     *
     * @return The list of courses offered by the training center.
     */
    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    /**
     * Gets the timestamp when the training center was created.
     *
     * @return The timestamp when the training center was created.
     */
    public LocalDateTime getCreatedOn() {
        return createdOn == null ? null
                : LocalDateTime.ofInstant(Instant.ofEpochMilli(createdOn), ZoneId.systemDefault());
    }

    /**
     * Gets the maximum number of students the training center can accommodate.
     *
     * @return The maximum number of students the training center can accommodate.
     */
    public Integer getStudentCapacity() {
        return studentCapacity;
    }

    /**
     * Sets the address of the training center.
     *
     * @param address The new address of the training center.
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * Sets the center code.
     *
     * @param centerCode The new center code.
     */
    public void setCenterCode(final String centerCode) {
        this.centerCode = centerCode;
    }

    /**
     * Sets the name of the training center.
     *
     * @param centerName The new name of the training center.
     */
    public void setCenterName(final String centerName) {
        this.centerName = centerName;
    }

    /**
     * Sets the email address of the training center.
     *
     * @param contactEmail The new email address of the training center.
     */
    public void setContactEmail(final String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Sets the contact phone number of the training center.
     *
     * @param contactPhone The new contact phone number of the training center.
     */
    public void setContactPhone(final String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * Sets the list of courses offered by the training center.
     *
     * @param coursesOffered The new list of courses offered by the training center.
     */
    public void setCoursesOffered(final List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    /**
     * Sets the timestamp when the training center was created.
     *
     * @param createdOn The new timestamp when the training center was created.
     */
    public void setCreatedOn(final LocalDateTime createdOn) {
        this.createdOn = createdOn == null ? null : createdOn.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * Sets the maximum number of students the training center can accommodate.
     *
     * @param studentCapacity The new maximum number of students the training center
     *                        can accommodate.
     */
    public void setStudentCapacity(final Integer studentCapacity) {
        this.studentCapacity = studentCapacity;
    }
}
