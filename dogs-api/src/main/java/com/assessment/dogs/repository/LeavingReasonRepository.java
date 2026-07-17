package com.assessment.dogs.repository;


import com.assessment.dogs.entity.LeavingReason;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LeavingReasonRepository 
        extends JpaRepository<LeavingReason, Long> {


    Optional<LeavingReason> findByName(String name);
    
    List<LeavingReason> findAllByOrderByIdAsc();

}