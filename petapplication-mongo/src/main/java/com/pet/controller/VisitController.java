package com.pet.controller;

import com.pet.model.Visit;
import com.pet.service.VisitService;
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
@RequestMapping("/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @Operation(summary = "Create a new visit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Visit created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Visit.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Visit already exists",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        return ResponseEntity.ok(visitService.createVisit(visit));
    }

    @Operation(summary = "Get a visit by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the visit",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Visit.class))}),
            @ApiResponse(responseCode = "404", description = "Visit not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Visit> findVisitById(@PathVariable ObjectId id) {
        Optional<Visit> visit = visitService.findVisitById(id);
        return visit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all visits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the visits",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Visit.class))})})
    @GetMapping
    public ResponseEntity<List<Visit>> findAllVisits() {
        return ResponseEntity.ok(visitService.findAllVisits());
    }

    @Operation(summary = "Delete a visit by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Visit deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Visit not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable ObjectId id) {
        visitService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }
}
