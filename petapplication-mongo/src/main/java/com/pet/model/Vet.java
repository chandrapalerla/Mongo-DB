package com.pet.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "vets")
public class Vet {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String clinicName;
    private String phoneNumber;
    private String email;

}