package com.pet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "visits")
public class Visit {
    @Id
    private ObjectId id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;
    private String reason;
    private String notes;

    @DBRef(lazy = true)
    private Pet pet;

    @DBRef(lazy = true)
    private Vet vet;
}