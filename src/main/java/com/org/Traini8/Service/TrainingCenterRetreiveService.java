package com.org.Traini8.Service;

import java.util.List;

import com.org.Traini8.pojo.TrainingCentre;

/**
 * The {@code TrainingCenterRetrieveService} interface defines the operations
 * that can be performed to retrieve information about training centers.
 *
 * This service interface is used to abstract the retrieval of training center
 * data, providing various methods to fetch training centers based on different
 * criteria.
 */
public interface TrainingCenterRetreiveService {

    /**
     * Retrieves all training centers available in the database.
     *
     * @return a list of {@link TrainingCentre} representing all training centers.
     *         If no training centers are found, this method returns an empty list.
     */
    List<TrainingCentre> findAllTrainingCenters();

    /**
     * Retrieves a list of training centers based on multiple search criteria. Any
     * parameter can be null, in which case it will not be used as a filter.
     *
     * @param centerCode   Optional filter by center code.
     * @param centerName   Optional filter by center name.
     * @param minCapacity  Optional filter for minimum capacity of the center.
     * @param maxCapacity  Optional filter for maximum capacity of the center.
     * @param contactEmail Optional filter by contact email address.
     * @param contactPhone Optional filter by contact phone number.
     * @param city         Optional filter by city where the center is located.
     * @param state        Optional filter by state where the center is located.
     * @param pincode      Optional filter by postal code.
     * @param courses      Optional list of courses; centers must offer all listed
     *                     courses if specified.
     *
     * @return a list of {@link TrainingCentre} that match the specified criteria.
     *         If no centers meet the criteria, this method returns an empty list.
     */
    List<TrainingCentre> findByMultipleCriteria(String centerCode, String centerName, Integer minCapacity,
            Integer maxCapacity, String contactEmail, String contactPhone, String city, String state, String pincode,
            List<String> courses);
}
