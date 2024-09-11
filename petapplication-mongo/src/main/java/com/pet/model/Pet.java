package com.pet.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "pets")
public class Pet {
    @Id
    private ObjectId id;
    private Long petId;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private Date birthdate;

    @DBRef
    private Owner owner;

}
