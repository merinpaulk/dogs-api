package com.assessment.dogs.entity;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;



@Entity
@Table(name="dogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dog {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable=false)
    private String name;



    @ManyToOne
    @JoinColumn(name="breed_id")
    private Breed breed;



    @ManyToOne
    @JoinColumn(name="supplier_id")
    private Supplier supplier;



    @Column(unique=true)
    private String badgeId;



    private String gender;



    private LocalDate birthDate;



    private LocalDate dateAcquired;



    @ManyToOne
    @JoinColumn(name="status_id")
    private Status status;



    private LocalDate leavingDate;



    @ManyToOne
    @JoinColumn(name="leaving_reason_id")
    private LeavingReason leavingReason;



    @Column(columnDefinition="TEXT")
    private String kennelCharacteristic;



    @Column(nullable=false)
    private boolean deleted=false;


}