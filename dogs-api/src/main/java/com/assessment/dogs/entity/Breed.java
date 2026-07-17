package com.assessment.dogs.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="breeds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Breed {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable=false, unique=true)
    private String name;


}
