package com.pet.repository;

import com.pet.model.Vet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VetRepository extends MongoRepository<Vet, ObjectId> {
}
