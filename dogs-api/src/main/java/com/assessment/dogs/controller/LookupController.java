package com.assessment.dogs.controller;

import com.assessment.dogs.dto.response.LookupResponseDTO;
import com.assessment.dogs.service.LookupService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class LookupController {

    private final LookupService lookupService;

    @Operation(summary = "Get all dog breeds")
    @GetMapping("/breeds")
    public List<LookupResponseDTO> getBreeds() {

        return lookupService.getBreeds();

    }

    @Operation(summary = "Get dog breed by ID")
    @GetMapping("/breeds/{id}")
    public LookupResponseDTO getBreedById(@PathVariable Long id) {

        return lookupService.getBreedById(id);

    }

    @Operation(summary = "Get all dog suppliers")
    @GetMapping("/suppliers")
    public List<LookupResponseDTO> getSuppliers() {

        return lookupService.getSuppliers();

    }

    @Operation(summary = "Get supplier by ID")
    @GetMapping("/suppliers/{id}")
    public LookupResponseDTO getSupplierById(@PathVariable Long id) {

        return lookupService.getSupplierById(id);

    }

    @Operation(summary = "Get all dog statuses")
    @GetMapping("/statuses")
    public List<LookupResponseDTO> getStatuses() {

        return lookupService.getStatuses();

    }

    @Operation(summary = "Get status by ID")
    @GetMapping("/statuses/{id}")
    public LookupResponseDTO getStatusById(@PathVariable Long id) {

        return lookupService.getStatusById(id);

    }

    @Operation(summary = "Get all dog leaving reasons")
    @GetMapping("/leaving-reasons")
    public List<LookupResponseDTO> getLeavingReasons() {

        return lookupService.getLeavingReasons();

    }

    @Operation(summary = "Get leaving reason by ID")
    @GetMapping("/leaving-reasons/{id}")
    public LookupResponseDTO getLeavingReasonById(@PathVariable Long id) {

        return lookupService.getLeavingReasonById(id);

    }

}