package com.org.Traini8.Service;

import com.org.Traini8.pojo.TrainingCentre;

import jakarta.validation.Valid;

/**
 * The {@code TrainingCenterRegistrationService} interface defines the contract
 * for services that manage the registration of new training centers.
 *
 * This service interface is used to abstract the complexities involved in the
 * data persistence of training centers. It ensures that any implementation will
 * handle the registration process while adhering to necessary validation
 * constraints.
 */
public interface TrainingCenterRegistrationService {

    /**
     * Saves a {@link TrainingCentre} object to the database.
     *
     * This method is responsible for persisting a new or updated training center in
     * the database. The {@link TrainingCentre} object must be valid in terms of
     * constraints defined by Jakarta Bean Validation.
     *
     * @param trainingCentre The {@link TrainingCentre} object to be saved; must not
     *                       be null and should meet all validation constraints.
     * @return The {@link TrainingCentre} instance that has been saved to the
     *         database, reflecting any changes or additional properties (like
     *         generated IDs) set during the persistence process.
     * @throws IllegalArgumentException if the provided {@link TrainingCentre}
     *                                  object does not meet validation criteria or
     *                                  if it is null.
     */
    TrainingCentre saveTrainingCentre(@Valid TrainingCentre trainingCentre);
}
