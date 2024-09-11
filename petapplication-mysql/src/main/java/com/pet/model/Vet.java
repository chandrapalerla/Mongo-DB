package com.pet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Vet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vetId;
    private String firstName;
    private String lastName;
    private String clinicName;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "vet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Visit> visits;
}