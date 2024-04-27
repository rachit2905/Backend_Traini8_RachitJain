package com.org.Traini8.ServiceImplementations;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.Traini8.Repository.TrainingCenterRepository;
import com.org.Traini8.Service.TrainingCenterRegistrationService;
import com.org.Traini8.pojo.TrainingCentre;

import jakarta.validation.Valid;

/**
 * Implementation of {@link TrainingCenterRegistrationService} that handles the
 * registration and data persistence of training centers in the database.
 *
 * This class uses the {@link TrainingCenterRepository} to interact with the
 * database and ensures that the data being saved is valid and transactions are
 * correctly managed.
 */
@Service
public class TrainingCenterRegistrationServiceImpl implements TrainingCenterRegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingCenterRegistrationServiceImpl.class);

    @Autowired
    private TrainingCenterRepository trainingCentreRepository;

    /**
     * Saves a validated {@link TrainingCentre} to the database with transaction
     * management.
     *
     * This method sets the 'createdOn' field to the current date and time just
     * before persisting the object. If any exception occurs during the save
     * operation, it logs the error and rethrows a {@link RuntimeException}.
     *
     * @param trainingCentre The {@link TrainingCentre} object to be saved; must be
     *                       valid according to Jakarta Bean Validation.
     * @return The {@link TrainingCentre} object after it has been saved, reflecting
     *         any automatic updates from the database such as generated IDs or
     *         timestamps.
     * @throws RuntimeException if there is an error during the database operation,
     *                          encapsulating the original exception.
     */
    @Override
    @Transactional
    public TrainingCentre saveTrainingCentre(@Valid final TrainingCentre trainingCentre) {
        try {
            trainingCentre.setCreatedOn(LocalDateTime.now()); // Set the creation time just before saving
            return trainingCentreRepository.save(trainingCentre);
        } catch (final Exception e) {
            logger.error("Failed to save Training Centre: {}", trainingCentre.getCenterCode(), e);
            throw new RuntimeException("Failed to save Training Centre: " + e.getMessage(), e);
        }
    }
}
