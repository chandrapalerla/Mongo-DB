package com.pet.controller;

import com.pet.model.Visit;
import com.pet.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.getAllVisits();
    }

    @GetMapping("/{id}")
    public Visit getVisitById(@PathVariable Long id) {
        return visitService.getVisitById(id);
    }

    @PostMapping
    public Visit createVisit(@RequestBody Visit Visit) {
        return visitService.saveVisit(Visit);
    }

    @PutMapping("/{id}")
    public Visit updateVisit(@PathVariable Long id, @RequestBody Visit visit) {
        Visit existingVisit = visitService.getVisitById(id);
        if (existingVisit != null) {
            visit.setVisitId(id);
            return visitService.saveVisit(visit);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
    }
}
