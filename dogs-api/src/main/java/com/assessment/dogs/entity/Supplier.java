package com.assessment.dogs.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="suppliers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable=false)
    private String name;


}
