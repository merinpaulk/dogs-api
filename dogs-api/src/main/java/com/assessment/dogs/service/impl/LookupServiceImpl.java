package com.assessment.dogs.service.impl;

import com.assessment.dogs.dto.response.LookupResponseDTO;
import com.assessment.dogs.entity.Breed;
import com.assessment.dogs.entity.Supplier;
import com.assessment.dogs.entity.Status;
import com.assessment.dogs.entity.LeavingReason;
import com.assessment.dogs.exception.ResourceNotFoundException;
import com.assessment.dogs.mapper.LookupMapper;
import com.assessment.dogs.repository.BreedRepository;
import com.assessment.dogs.repository.SupplierRepository;
import com.assessment.dogs.repository.StatusRepository;
import com.assessment.dogs.repository.LeavingReasonRepository;
import com.assessment.dogs.service.LookupService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LookupServiceImpl implements LookupService {

    private final BreedRepository breedRepository;

    private final SupplierRepository supplierRepository;

    private final StatusRepository statusRepository;

    private final LeavingReasonRepository leavingReasonRepository;

    private final LookupMapper lookupMapper;

    @Override
    public List<LookupResponseDTO> getBreeds() {

        return breedRepository.findAll()
                .stream()
                .map(lookupMapper::toResponse)
                .toList();

    }

    @Override
    public LookupResponseDTO getBreedById(Long id) {

        Breed breed = breedRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Breed not found with id: " + id
                        ));

        return lookupMapper.toResponse(breed);

    }

    @Override
    public List<LookupResponseDTO> getSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(lookupMapper::toResponse)
                .toList();

    }

    @Override
    public LookupResponseDTO getSupplierById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Supplier not found with id: " + id
                        ));

        return lookupMapper.toResponse(supplier);

    }

    @Override
    public List<LookupResponseDTO> getStatuses() {

        return statusRepository.findAllByOrderByIdAsc()
                .stream()
                .map(lookupMapper::toResponse)
                .toList();

    }

    @Override
    public LookupResponseDTO getStatusById(Long id) {

        Status status = statusRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Status not found with id: " + id
                        ));

        return lookupMapper.toResponse(status);

    }

    @Override
    public List<LookupResponseDTO> getLeavingReasons() {

        return leavingReasonRepository.findAllByOrderByIdAsc()
                .stream()
                .map(lookupMapper::toResponse)
                .toList();

    }

    @Override
    public LookupResponseDTO getLeavingReasonById(Long id) {

        LeavingReason leavingReason = leavingReasonRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Leaving reason not found with id: " + id
                        ));

        return lookupMapper.toResponse(leavingReason);

    }

}