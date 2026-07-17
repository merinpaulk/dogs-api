package com.assessment.dogs.service;

import com.assessment.dogs.dto.response.LookupResponseDTO;

import java.util.List;

public interface LookupService {

    // Breeds
    List<LookupResponseDTO> getBreeds();

    LookupResponseDTO getBreedById(Long id);

    // Suppliers
    List<LookupResponseDTO> getSuppliers();

    LookupResponseDTO getSupplierById(Long id);

    // Statuses
    List<LookupResponseDTO> getStatuses();

    LookupResponseDTO getStatusById(Long id);

    // Leaving Reasons
    List<LookupResponseDTO> getLeavingReasons();

    LookupResponseDTO getLeavingReasonById(Long id);

}