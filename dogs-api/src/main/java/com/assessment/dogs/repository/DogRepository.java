package com.assessment.dogs.repository;


import com.assessment.dogs.entity.Dog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface DogRepository 
        extends JpaRepository<Dog, Long>,
                JpaSpecificationExecutor<Dog> {

	  Page<Dog> findByDeletedFalse(Pageable pageable);
}