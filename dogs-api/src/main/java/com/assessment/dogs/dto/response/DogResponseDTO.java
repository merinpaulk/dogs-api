package com.assessment.dogs.dto.response;


import lombok.Data;


import java.time.LocalDate;


@Data
public class DogResponseDTO {


    private Long id;



    private String name;



    private String breed;



    private String supplier;



    private String badgeId;



    private String gender;



    private LocalDate birthDate;



    private LocalDate dateAcquired;



    private String status;



    private LocalDate leavingDate;



    private String leavingReason;



    private String kennelCharacteristic;


}