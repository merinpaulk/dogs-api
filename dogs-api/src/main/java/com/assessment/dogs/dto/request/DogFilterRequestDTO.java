package com.assessment.dogs.dto.request;

import lombok.Data;

@Data
public class DogFilterRequestDTO {

    private String name;

    private String breed;

    private String supplier;

}