package com.pet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Prescriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;
    private Date startDate;
    private Date endDate;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;
}