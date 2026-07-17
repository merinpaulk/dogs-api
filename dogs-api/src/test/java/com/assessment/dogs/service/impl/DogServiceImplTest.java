package com.assessment.dogs.service.impl;

import com.assessment.dogs.dto.request.DogRequestDTO;
import com.assessment.dogs.dto.response.DogResponseDTO;
import com.assessment.dogs.entity.*;
import com.assessment.dogs.exception.ResourceNotFoundException;
import com.assessment.dogs.mapper.DogMapper;
import com.assessment.dogs.repository.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceImplTest {


    @Mock
    private DogRepository dogRepository;

    @Mock
    private BreedRepository breedRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @Mock
    private StatusRepository statusRepository;

    @Mock
    private LeavingReasonRepository leavingReasonRepository;

    @Mock
    private DogMapper dogMapper;


    @InjectMocks
    private DogServiceImpl dogService;


    @Test
    void createDog_ShouldCreateDogSuccessfully() {

        // Arrange

        DogRequestDTO request = new DogRequestDTO();

        request.setName("Sky");
        request.setBreedId(1L);
        request.setSupplierId(1L);
        request.setStatusId(2L);
        request.setBadgeId("DOG011");
        request.setGender("MALE");
        request.setBirthDate(LocalDate.of(2022,1,10));
        request.setDateAcquired(LocalDate.of(2023,3,1));
        request.setKennelCharacteristic("Highly obedient");


        Breed breed = new Breed();
        breed.setId(1L);
        breed.setName("German Shepherd");


        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("ABC Kennels");


        Status status = new Status();
        status.setId(2L);
        status.setName("Available");


        Dog dog = new Dog();
        dog.setName("Sky");
        dog.setBreed(breed);
        dog.setSupplier(supplier);
        dog.setStatus(status);


        Dog savedDog = new Dog();
        savedDog.setId(1L);
        savedDog.setName("Sky");


        DogResponseDTO response = new DogResponseDTO();
        response.setId(1L);
        response.setName("Sky");


        when(breedRepository.findById(1L))
                .thenReturn(Optional.of(breed));

        when(supplierRepository.findById(1L))
                .thenReturn(Optional.of(supplier));

        when(statusRepository.findById(2L))
                .thenReturn(Optional.of(status));


        when(dogMapper.toEntity(request))
                .thenReturn(dog);


        when(dogRepository.save(any(Dog.class)))
                .thenReturn(savedDog);


        when(dogMapper.toResponse(savedDog))
                .thenReturn(response);


        // Act

        DogResponseDTO result = dogService.createDog(request);


        // Assert

        assertNotNull(result);
        assertEquals("Sky", result.getName());
        assertEquals(1L, result.getId());


        verify(breedRepository)
                .findById(1L);

        verify(supplierRepository)
                .findById(1L);

        verify(statusRepository)
                .findById(2L);

        verify(dogRepository)
                .save(any(Dog.class));
    }
    
    @Test
    void getDogById_ShouldReturnDog_WhenDogExists() {

        Dog dog = new Dog();
        dog.setId(1L);
        dog.setName("Sky");


        DogResponseDTO response = new DogResponseDTO();
        response.setId(1L);
        response.setName("Sky");


        when(dogRepository.findById(1L))
                .thenReturn(Optional.of(dog));

        when(dogMapper.toResponse(dog))
                .thenReturn(response);


        DogResponseDTO result = dogService.getDogById(1L);


        assertNotNull(result);
        assertEquals("Sky", result.getName());


        verify(dogRepository)
                .findById(1L);

        verify(dogMapper)
                .toResponse(dog);
    }
    
    @Test
    void getDogById_ShouldThrowException_WhenDogDoesNotExist() {


        when(dogRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                ResourceNotFoundException.class,
                () -> dogService.getDogById(99L)
        );


        verify(dogMapper, never())
                .toResponse(any());
    }
    
    
    @Test
    void updateDog_ShouldUpdateSuccessfully() {


        Long dogId = 1L;


        DogRequestDTO request = new DogRequestDTO();

        request.setName("Sky Updated");
        request.setBreedId(2L);
        request.setSupplierId(1L);
        request.setStatusId(2L);


        Dog existingDog = new Dog();
        existingDog.setId(dogId);
        existingDog.setName("Sky");


        Breed breed = new Breed();
        Supplier supplier = new Supplier();
        Status status = new Status();


        DogResponseDTO response = new DogResponseDTO();
        response.setId(dogId);
        response.setName("Sky Updated");


        when(dogRepository.findById(dogId))
                .thenReturn(Optional.of(existingDog));


        when(breedRepository.findById(2L))
                .thenReturn(Optional.of(breed));


        when(supplierRepository.findById(1L))
                .thenReturn(Optional.of(supplier));


        when(statusRepository.findById(2L))
                .thenReturn(Optional.of(status));


        when(dogRepository.save(existingDog))
                .thenReturn(existingDog);


        when(dogMapper.toResponse(existingDog))
                .thenReturn(response);


        DogResponseDTO result =
                dogService.updateDog(dogId, request);


        assertEquals("Sky Updated", result.getName());


        verify(dogRepository)
                .save(existingDog);
    }
    
    @Test
    void deleteDog_ShouldMarkDogAsDeleted() {


        Dog dog = new Dog();
        dog.setId(1L);
        dog.setDeleted(false);


        when(dogRepository.findById(1L))
                .thenReturn(Optional.of(dog));


        dogService.deleteDog(1L);


        assertTrue(dog.isDeleted());


        verify(dogRepository)
                .save(dog);
    }
    
    
    @Test
    void updateDog_ShouldThrowException_WhenDogNotFound() {


        when(dogRepository.findById(99L))
                .thenReturn(Optional.empty());


        DogRequestDTO request = new DogRequestDTO();


        assertThrows(
                ResourceNotFoundException.class,
                () -> dogService.updateDog(99L, request)
        );


        verify(dogRepository, never())
                .save(any());
    }
    
    @Test
    void deleteDog_ShouldThrowException_WhenDogDoesNotExist() {


        when(dogRepository.findById(99L))
                .thenReturn(Optional.empty());


        assertThrows(
                ResourceNotFoundException.class,
                () -> dogService.deleteDog(99L)
        );


        verify(dogRepository, never())
                .save(any());
    }
}