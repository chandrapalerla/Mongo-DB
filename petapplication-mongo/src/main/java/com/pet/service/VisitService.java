package com.pet.service;

import com.pet.model.Visit;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface VisitService {
    Visit createVisit(Visit visit);
    Optional<Visit> findVisitById(ObjectId id);
    List<Visit> findAllVisits();
    void deleteVisit(ObjectId id);
}
