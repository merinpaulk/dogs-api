package com.assessment.dogs.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;


import java.time.LocalDate;


@Data
public class DogRequestDTO {


    @NotBlank(message = "Dog name is required")
    private String name;



    @NotNull(message = "Breed is required")
    private Long breedId;



    @NotNull(message = "Supplier is required")
    private Long supplierId;



    private String badgeId;



    private String gender;



    private LocalDate birthDate;



    private LocalDate dateAcquired;



    @NotNull(message = "Status is required")
    private Long statusId;



    private LocalDate leavingDate;



    private Long leavingReasonId;



    private String kennelCharacteristic;


}