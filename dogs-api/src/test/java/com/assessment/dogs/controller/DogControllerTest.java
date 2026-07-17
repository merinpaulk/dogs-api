package com.assessment.dogs.controller;


import com.assessment.dogs.dto.request.DogRequestDTO;
import com.assessment.dogs.dto.response.DogResponseDTO;
import com.assessment.dogs.service.DogService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class DogControllerTest {


    @Mock
    private DogService dogService;


    @InjectMocks
    private DogController dogController;



    @Test
    void getDogById_ShouldReturnDogSuccessfully() {


        DogResponseDTO response = new DogResponseDTO();

        response.setId(1L);
        response.setName("Sky");


        when(dogService.getDogById(1L))
                .thenReturn(response);



        ResponseEntity<DogResponseDTO> result =
                dogController.getDogById(1L);



        assertNotNull(result);

        assertEquals(200, result.getStatusCode().value());

        assertEquals("Sky",
                result.getBody().getName());



        verify(dogService)
                .getDogById(1L);
    }





    @Test
    void createDog_ShouldReturnCreatedDog() {


        DogRequestDTO request = new DogRequestDTO();

        request.setName("Sky");
        request.setBreedId(1L);
        request.setSupplierId(1L);
        request.setStatusId(2L);



        DogResponseDTO response = new DogResponseDTO();

        response.setId(1L);
        response.setName("Sky");



        when(dogService.createDog(request))
                .thenReturn(response);



        ResponseEntity<DogResponseDTO> result =
                dogController.createDog(request);



        assertNotNull(result);

        assertEquals(201,
                result.getStatusCode().value());

        assertEquals("Sky",
                result.getBody().getName());



        verify(dogService)
                .createDog(request);
    }





    @Test
    void updateDog_ShouldReturnUpdatedDog() {


        DogRequestDTO request = new DogRequestDTO();

        request.setName("Sky Updated");



        DogResponseDTO response = new DogResponseDTO();

        response.setId(1L);
        response.setName("Sky Updated");



        when(dogService.updateDog(1L, request))
                .thenReturn(response);



        ResponseEntity<DogResponseDTO> result =
                dogController.updateDog(1L, request);



        assertEquals(200,
                result.getStatusCode().value());

        assertEquals("Sky Updated",
                result.getBody().getName());



        verify(dogService)
                .updateDog(1L, request);
    }





    @Test
    void deleteDog_ShouldReturnNoContent() {


        doNothing()
                .when(dogService)
                .deleteDog(1L);



        ResponseEntity<Void> result =
                dogController.deleteDog(1L);



        assertEquals(204,
                result.getStatusCode().value());



        verify(dogService)
                .deleteDog(1L);
    }

}