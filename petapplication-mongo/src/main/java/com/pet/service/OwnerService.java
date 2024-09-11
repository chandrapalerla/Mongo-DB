package com.pet.service;

import com.pet.model.Owner;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    Owner createOwner(Owner owner);

    Optional<Owner> findOwnerById(ObjectId id);

    List<Owner> findAllOwners();

    void deleteOwner(ObjectId id);
}
