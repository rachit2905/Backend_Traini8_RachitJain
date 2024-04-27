package com.org.Traini8.Controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.Traini8.ExceptionalHandling.CustomExceptions.TrainingCenterRegistrationException;
import com.org.Traini8.Service.TrainingCenterRegistrationService;
import com.org.Traini8.pojo.TrainingCentre;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * Controller to manage registration of new training centers via HTTP POST
 * requests. Includes functionality for data validation and handles client and
 * server errors during the registration process.
 */
@RestController
@RequestMapping("/api/training-centers")
public class TrainingCentersRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(TrainingCentersRegistrationController.class);

    @Autowired
    private TrainingCenterRegistrationService trainingCenterService;

    /**
     * Registers a new training center using the provided data in the POST request
     * body. Data must comply with Jakarta Bean Validation constraints defined in
     * the TrainingCentre model.
     *
     * @param trainingCentre the {@link TrainingCentre} object to be registered;
     *                       must be valid.
     * @return ResponseEntity containing the registered {@link TrainingCentre} and
     *         HTTP status OK.
     * @throws TrainingCenterRegistrationException if registration fails due to
     *                                             server-related issues.
     */
    @Operation(summary = "Register a new training center", description = "Creates a new training center entry in the database from the provided training center data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Training center registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrainingCentre.class))),
            @ApiResponse(responseCode = "400", description = "Invalid training center data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error while registering the training center") })
    @PostMapping
    public ResponseEntity<?> createTrainingCenter(@RequestBody @Valid final TrainingCentre trainingCentre) {
        try {
            final var savedTrainingCentre = trainingCenterService.saveTrainingCentre(trainingCentre);
            return ResponseEntity.ok(savedTrainingCentre);
        } catch (final Exception ex) {
            logger.error("Error creating training center", ex);
            throw new TrainingCenterRegistrationException("Error creating training center: " + ex.getMessage(), ex);
        }
    }

    /**
     * Custom exception handler for validation errors that may occur during training
     * center creation. Provides detailed feedback on what fields failed validation.
     *
     * @param ex the captured validation error exception.
     * @return A ResponseEntity with detailed validation error messages in JSON
     *         format.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        logger.error("Validation errors: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

}
