package com.pet.controller;

import com.pet.model.Owner;
import com.pet.service.OwnerService;
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
@RequestMapping("/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @Operation(summary = "Create a new Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Owner.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        return ResponseEntity.ok(ownerService.createOwner(owner));
    }

    @Operation(summary = "Get a Owner by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Owner",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Owner.class))}),
            @ApiResponse(responseCode = "404", description = "Owner not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Owner> findOwnerById(@PathVariable ObjectId id) {
        Optional<Owner> owner = ownerService.findOwnerById(id);
        return owner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all Owners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Owners",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Owner.class))})})
    @GetMapping
    public ResponseEntity<List<Owner>> findAllOwners() {
        return ResponseEntity.ok(ownerService.findAllOwners());
    }

    @Operation(summary = "Delete a Owner by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Owner deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Owner not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable ObjectId id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}
