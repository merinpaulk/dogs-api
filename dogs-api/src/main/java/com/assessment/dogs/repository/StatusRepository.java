package com.assessment.dogs.repository;


import com.assessment.dogs.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StatusRepository 
        extends JpaRepository<Status, Long> {


    Optional<Status> findByName(String name);
    
    List<Status> findAllByOrderByIdAsc();

}