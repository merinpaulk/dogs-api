package com.assessment.dogs.service.impl;

import com.assessment.dogs.dto.request.DogFilterRequestDTO;
import com.assessment.dogs.dto.request.DogRequestDTO;
import com.assessment.dogs.dto.response.DogResponseDTO;
import com.assessment.dogs.entity.*;
import com.assessment.dogs.exception.ResourceNotFoundException;
import com.assessment.dogs.mapper.DogMapper;
import com.assessment.dogs.repository.*;
import com.assessment.dogs.service.DogService;
import com.assessment.dogs.specification.DogFilterSpecification;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class DogServiceImpl implements DogService {


    private final DogRepository dogRepository;
    private final BreedRepository breedRepository;
    private final SupplierRepository supplierRepository;
    private final StatusRepository statusRepository;
    private final LeavingReasonRepository leavingReasonRepository;

    private final DogMapper dogMapper;



    @Override
    public DogResponseDTO createDog(DogRequestDTO request) {


        Dog dog = dogMapper.toEntity(request);


        dog.setBreed(
                findBreed(request.getBreedId())
        );

        dog.setSupplier(
                findSupplier(request.getSupplierId())
        );

        dog.setStatus(
                findStatus(request.getStatusId())
        );


        if(request.getLeavingReasonId() != null) {

            dog.setLeavingReason(
                    findLeavingReason(request.getLeavingReasonId())
            );

        }


        Dog savedDog = dogRepository.save(dog);


        return dogMapper.toResponse(savedDog);
    }


    /**
     * Retrieves an active dog by ID.
     *
     * Soft deleted dogs are intentionally excluded from all
     * standard API operations. Records are retained in the
     * database for audit purposes.
     */
    @Override
    @Transactional(readOnly = true)
    public DogResponseDTO getDogById(Long id) {


        Dog dog = dogRepository.findById(id)
                .filter(existingDog -> !existingDog.isDeleted())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Dog not found with id: " + id
                        )
                );


        return dogMapper.toResponse(dog);
    }



    @Override
    @Transactional(readOnly = true)
    public Page<DogResponseDTO> getAllDogs(
            DogFilterRequestDTO filter,
            Pageable pageable) {

        return dogRepository.findAll(
                        DogFilterSpecification.filter(filter),
                        pageable)
                .map(dogMapper::toResponse);

    }



    @Override
    public DogResponseDTO updateDog(Long id, DogRequestDTO request) {


        Dog dog = dogRepository.findById(id)
                .filter(existingDog -> !existingDog.isDeleted())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Dog not found with id: " + id
                        )
                );


        // Map simple fields from DTO to existing entity
        dogMapper.updateDogFromRequest(request, dog);


        // Resolve and update relationships
        dog.setBreed(findBreed(request.getBreedId()));
        dog.setSupplier(findSupplier(request.getSupplierId()));
        dog.setStatus(findStatus(request.getStatusId()));


        if(request.getLeavingReasonId() != null) {

            dog.setLeavingReason(
                    findLeavingReason(request.getLeavingReasonId())
            );

        } else {

            dog.setLeavingReason(null);

        }


        Dog updatedDog = dogRepository.save(dog);


        return dogMapper.toResponse(updatedDog);
    }



    @Override
    public void deleteDog(Long id) {


        Dog dog = dogRepository.findById(id)
                .filter(existingDog -> !existingDog.isDeleted())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Dog not found with id: " + id
                        )
                );


        dog.setDeleted(true);


        dogRepository.save(dog);

    }



    private Breed findBreed(Long id) {

        return breedRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Breed not found with id: " + id
                        )
                );

    }



    private Supplier findSupplier(Long id) {

        return supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Supplier not found with id: " + id
                        )
                );

    }



    private Status findStatus(Long id) {

        return statusRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Status not found with id: " + id
                        )
                );

    }



    private LeavingReason findLeavingReason(Long id) {

        return leavingReasonRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leaving reason not found with id: " + id
                        )
                );

    }

}