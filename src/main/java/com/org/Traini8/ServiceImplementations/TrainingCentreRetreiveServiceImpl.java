package com.org.Traini8.ServiceImplementations;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.Traini8.Repository.TrainingCenterRepository;
import com.org.Traini8.Service.TrainingCenterRetreiveService;
import com.org.Traini8.pojo.TrainingCentre;

/**
 * Implementation of {@link TrainingCenterRetreiveService} that interacts with
 * the database to fetch and filter data regarding training centers.
 *
 * This service utilizes Spring's Dependency Injection to integrate components
 * and the {@link TrainingCenterRepository} for data access operations.
 */
@Service
public class TrainingCentreRetreiveServiceImpl implements TrainingCenterRetreiveService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingCentreRetreiveServiceImpl.class);

    @Autowired
    private TrainingCenterRepository trainingCenterRepository;

    /**
     * Retrieves all training centers from the database. This method handles any
     * exceptions by logging them and rethrowing as a runtime exception.
     *
     * @return a list of all {@link TrainingCentre} objects from the database. If no
     *         training centers are found, this method returns an empty list.
     * @throws RuntimeException if there is an error during database access.
     */
    @Override
    public List<TrainingCentre> findAllTrainingCenters() {
        try {
            return trainingCenterRepository.findAll();
        } catch (final Exception e) {
            logger.error("Error retrieving training centers", e);
            throw new RuntimeException("Error retrieving training centers: " + e.getMessage(), e);
        }
    }

    /**
     * Retrieves a list of training centers based on multiple filtering criteria.
     * Parameters are optional and used to filter the results accordingly.
     *
     * @param centerCode   Optional filter by center code.
     * @param centerName   Optional filter by center name.
     * @param minCapacity  Optional filter for minimum capacity.
     * @param maxCapacity  Optional filter for maximum capacity.
     * @param contactEmail Optional filter by contact email.
     * @param contactPhone Optional filter by contact phone.
     * @param city         Optional filter by city.
     * @param state        Optional filter by state.
     * @param pincode      Optional filter by pincode.
     * @param courses      Optional list of courses to filter by; centers must offer
     *                     all listed courses if specified.
     * @return a list of {@link TrainingCentre} that meet the specified criteria. If
     *         no centers match the criteria, this method returns an empty list.
     * @throws RuntimeException if there is an error during database access or data
     *                          filtering.
     */
    @Override
    public List<TrainingCentre> findByMultipleCriteria(final String centerCode, final String centerName,
            final Integer minCapacity, final Integer maxCapacity, final String contactEmail, final String contactPhone,
            final String city, final String state, final String pincode, final List<String> courses) {
        try {
            return trainingCenterRepository.findTrainingCentresByMultipleFields(centerCode, centerName, minCapacity,
                    maxCapacity, contactEmail, contactPhone, city, state, pincode, courses);
        } catch (final Exception e) {
            logger.error("Error retrieving training centers with filters", e);
            throw new RuntimeException("Error retrieving training centers with filters: " + e.getMessage(), e);
        }
    }
}
