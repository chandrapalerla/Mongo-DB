package com.pet.controller;

import com.pet.model.Prescription;
import com.pet.service.PrescriptionService;
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
@RequestMapping("/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @Operation(summary = "Create a new prescription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prescription created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Prescription.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Prescription already exists",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        return ResponseEntity.ok(prescriptionService.createPrescription(prescription));
    }

    @Operation(summary = "Get a prescription by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the prescription",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Prescription.class))}),
            @ApiResponse(responseCode = "404", description = "Prescription not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> findPrescriptionById(@PathVariable ObjectId id) {
        Optional<Prescription> prescription = prescriptionService.findPrescriptionById(id);
        return prescription.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all prescriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the prescriptions",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Prescription.class))})})
    @GetMapping
    public ResponseEntity<List<Prescription>> findAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.findAllPrescriptions());
    }

    @Operation(summary = "Delete a prescription by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Prescription deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Prescription not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable ObjectId id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}
