package com.pet.repository;

import com.pet.model.Visit;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VisitRepository extends MongoRepository<Visit, ObjectId> {
}
