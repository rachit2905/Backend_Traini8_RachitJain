package com.org.Traini8.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.Traini8.ExceptionalHandling.CustomExceptions.TrainingCenterException;
import com.org.Traini8.Service.TrainingCenterRetreiveService;
import com.org.Traini8.pojo.TrainingCentre;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controller for handling HTTP requests related to the retrieval of training
 * centers.
 */
@RestController
@RequestMapping("/api/training-centers")
public class TrainingCenteresRetreiveController {

    private static final Logger logger = LoggerFactory.getLogger(TrainingCenteresRetreiveController.class);

    @Autowired
    private TrainingCenterRetreiveService trainingCenterRetrieveService;

    /**
     * Retrieves training centers based on optional filter criteria. Always returns
     * a JSON result.
     */
    @Operation(summary = "Retrieve training centers", description = "Fetches a list of training centers based on various optional filters such as center code, name, capacity, and contact details. Returns all centers if no filters are specified.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of training centers", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrainingCentre.class))),
            @ApiResponse(responseCode = "204", description = "No training centers found", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid parameters", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(mediaType = "application/json")) })
    @GetMapping
    public ResponseEntity<?> getTrainingCenters(
            @Parameter(description = "Filter by center code") @RequestParam(value = "centerCode", required = false) final String centerCode,
            @Parameter(description = "Filter by center name") @RequestParam(value = "centerName", required = false) final String centerName,
            @Parameter(description = "Minimum capacity filter") @RequestParam(value = "minCapacity", required = false) final Integer minCapacity,
            @Parameter(description = "Maximum capacity filter") @RequestParam(value = "maxCapacity", required = false) final Integer maxCapacity,
            @Parameter(description = "Filter by contact email") @RequestParam(value = "contactEmail", required = false) final String contactEmail,
            @Parameter(description = "Filter by contact phone number") @RequestParam(value = "contactPhone", required = false) final String contactPhone,
            @Parameter(description = "Filter by city") @RequestParam(value = "city", required = false) final String city,
            @Parameter(description = "Filter by state") @RequestParam(value = "state", required = false) final String state,
            @Parameter(description = "Filter by pincode") @RequestParam(value = "pincode", required = false) final String pincode,
            @Parameter(description = "Filter by courses offered") @RequestParam(value = "courses", required = false) final List<String> courses) {

        try {
            final var trainingCenters = trainingCenterRetrieveService.findByMultipleCriteria(centerCode, centerName,
                    minCapacity, maxCapacity, contactEmail, contactPhone, city, state, pincode, courses);
            if (trainingCenters.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{}"); // Return empty JSON object on no
                                                                                // content
            }
            return ResponseEntity.ok(trainingCenters);
        } catch (final Exception e) {
            logger.error("Error while fetching training centers", e);
            throw new TrainingCenterException("Failed to retrieve training centers", e);
        }
    }
}
