package com.pet.repository;

import com.pet.model.Owner;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OwnerRepository extends MongoRepository<Owner, ObjectId> {
}