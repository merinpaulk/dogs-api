package com.assessment.dogs.controller;


import com.assessment.dogs.dto.request.DogFilterRequestDTO;
import com.assessment.dogs.dto.request.DogRequestDTO;
import com.assessment.dogs.dto.response.DogResponseDTO;
import com.assessment.dogs.service.DogService;
import com.fasterxml.jackson.core.JsonProcessingException;


import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import tools.jackson.databind.ObjectMapper;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing registered police dogs.
 *
 * Base path: /api/dogs
 *
 * Note:
 * The assessment specification requires dog collection endpoints under
 * /api/dogs/dogs, therefore the additional "/dogs" segment is intentional.
 */

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {



    private final DogService dogService;
    
    private final ObjectMapper objectMapper;


    @PostMapping("/dogs")
    public ResponseEntity<DogResponseDTO> createDog(
            @Valid @RequestBody DogRequestDTO request) {


        DogResponseDTO response =
                dogService.createDog(request);


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }





    @GetMapping("/dogs")
    public ResponseEntity<Page<DogResponseDTO>> getAllDogs(

            @RequestParam(required = false) String filter,
            @ParameterObject Pageable pageable) throws JsonProcessingException {

        DogFilterRequestDTO dogFilter = null;

        if (filter != null && !filter.isBlank()) {
            dogFilter = objectMapper.readValue(filter, DogFilterRequestDTO.class);
        }

        return ResponseEntity.ok(
                dogService.getAllDogs(dogFilter, pageable)
        );

    }





    @GetMapping("/dogs/{id}")
    public ResponseEntity<DogResponseDTO> getDogById(
            @PathVariable Long id) {


        DogResponseDTO dog =
                dogService.getDogById(id);


        return ResponseEntity.ok(dog);
    }





    @PutMapping("/dogs/{id}")
    public ResponseEntity<DogResponseDTO> updateDog(
            @PathVariable Long id,
            @Valid @RequestBody DogRequestDTO request) {


        DogResponseDTO updatedDog =
                dogService.updateDog(id, request);


        return ResponseEntity.ok(updatedDog);
    }





    @DeleteMapping("/dogs/{id}")
    public ResponseEntity<Void> deleteDog(
            @PathVariable Long id) {


        dogService.deleteDog(id);


        return ResponseEntity.noContent().build();
    }

}