package com.pet.controller;

import com.pet.model.Pet;
import com.pet.service.PetService;
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
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @Operation(summary = "Create a new Pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Pet already exists",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(petService.createPet(pet));
    }

    @Operation(summary = "Get a Pet by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Pet",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class))}),
            @ApiResponse(responseCode = "404", description = "Pet not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Pet> findPetById(@PathVariable ObjectId id) {
        Optional<Pet> pet = petService.findPetById(id);
        return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all pets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the pets",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pet.class)) }) })
    @GetMapping
    public ResponseEntity<List<Pet>> findAllPets() {
        return ResponseEntity.ok(petService.findAllPets());
    }

    @Operation(summary = "Delete a pet by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pet deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pet not found",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable ObjectId id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
