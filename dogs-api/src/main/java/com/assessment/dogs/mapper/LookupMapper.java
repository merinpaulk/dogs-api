package com.assessment.dogs.mapper;

import com.assessment.dogs.dto.response.LookupResponseDTO;
import com.assessment.dogs.entity.Breed;
import com.assessment.dogs.entity.Supplier;
import com.assessment.dogs.entity.Status;
import com.assessment.dogs.entity.LeavingReason;

import org.mapstruct.Mapper;


@Mapper(config = DogMapperConfig.class)
public interface LookupMapper {


    LookupResponseDTO toResponse(Breed breed);


    LookupResponseDTO toResponse(Supplier supplier);


    LookupResponseDTO toResponse(Status status);


    LookupResponseDTO toResponse(LeavingReason leavingReason);

}