package com.pet.controller;

import com.pet.model.Medication;
import com.pet.service.MedicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medications")
public class MedicationController {
    @Autowired
    private MedicationService medicationService;

    @Operation(summary = "Create a new medication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medication.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Medication already exists",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Medication> createMedication(@RequestBody Medication medication) {
        return ResponseEntity.ok(medicationService.createMedication(medication));
    }

    @Operation(summary = "Get a medication by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the medication",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medication.class))}),
            @ApiResponse(responseCode = "404", description = "Medication not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Medication> findMedicationById(@PathVariable ObjectId id) {
        Optional<Medication> medication = medicationService.findMedicationById(id);
        return medication.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all medications")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the medications",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Medication.class))})})
    @GetMapping
    public ResponseEntity<List<Medication>> findAllMedications() {
        return ResponseEntity.ok(medicationService.findAllMedications());
    }

    @Operation(summary = "Delete a medication by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Medication deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Medication not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable ObjectId id) {
        medicationService.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }
}
