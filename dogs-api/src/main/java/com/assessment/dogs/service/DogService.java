package com.assessment.dogs.service;

import com.assessment.dogs.dto.request.DogFilterRequestDTO;
import com.assessment.dogs.dto.request.DogRequestDTO;
import com.assessment.dogs.dto.response.DogResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DogService {

    DogResponseDTO createDog(DogRequestDTO request);

    DogResponseDTO updateDog(Long id, DogRequestDTO request);

    DogResponseDTO getDogById(Long id);

    Page<DogResponseDTO> getAllDogs(DogFilterRequestDTO filter,Pageable pageable);

    void deleteDog(Long id);

}