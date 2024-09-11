package com.pet.repository;

import com.pet.model.Medication;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicationRepository extends MongoRepository<Medication, ObjectId> {
}
