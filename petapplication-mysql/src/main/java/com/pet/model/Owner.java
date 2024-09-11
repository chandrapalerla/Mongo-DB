package com.pet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "owners")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;
}