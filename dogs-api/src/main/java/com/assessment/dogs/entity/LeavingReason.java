package com.assessment.dogs.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="leaving_reasons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeavingReason {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable=false,unique=true)
    private String name;


}