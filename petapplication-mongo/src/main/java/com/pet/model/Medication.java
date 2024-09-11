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
@Document(collection = "medications")
public class Medication {
    @Id
    private ObjectId id;
    private String name;
    private String dosage;
    private String instructions;
}