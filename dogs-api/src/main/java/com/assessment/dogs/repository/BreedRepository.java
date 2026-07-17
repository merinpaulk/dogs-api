package com.assessment.dogs.repository;


import com.assessment.dogs.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface BreedRepository 
        extends JpaRepository<Breed, Long> {


    Optional<Breed> findByName(String name);

}