package com.pet.controller;

import com.pet.model.Vet;
import com.pet.service.VetService;
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
@RequestMapping("/vets")
public class VetController {
    @Autowired
    private VetService vetService;

    @Operation(summary = "Create a new vet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "vet created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vet.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Prescription already exists",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Vet> createVet(@RequestBody Vet vet) {
        return ResponseEntity.ok(vetService.createVet(vet));
    }

    @Operation(summary = "Get a vet by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the vet",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vet.class))}),
            @ApiResponse(responseCode = "404", description = "vet not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Vet> findVetById(@PathVariable ObjectId id) {
        Optional<Vet> vet = vetService.findVetById(id);
        return vet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all vets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the vet",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vet.class))})})
    @GetMapping
    public ResponseEntity<List<Vet>> findAllVets() {
        return ResponseEntity.ok(vetService.findAllVets());
    }

    @Operation(summary = "Delete a vet by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "vet deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "vet not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVet(@PathVariable ObjectId id) {
        vetService.deleteVet(id);
        return ResponseEntity.noContent().build();
    }
}
