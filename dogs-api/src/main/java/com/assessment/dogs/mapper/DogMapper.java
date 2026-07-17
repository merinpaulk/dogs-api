package com.assessment.dogs.mapper;

import com.assessment.dogs.dto.request.DogRequestDTO;
import com.assessment.dogs.dto.response.DogResponseDTO;
import com.assessment.dogs.entity.Dog;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(config = DogMapperConfig.class)
public interface DogMapper {


    // Create mapping
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "breed", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "leavingReason", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Dog toEntity(DogRequestDTO dto);



    // Update existing entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "breed", ignore = true)
    @Mapping(target = "supplier", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "leavingReason", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    void updateDogFromRequest(
            DogRequestDTO dto,
            @MappingTarget Dog dog
    );



    // Response mapping
    @Mapping(
        target = "breed",
        source = "breed.name"
    )
    @Mapping(
        target = "supplier",
        source = "supplier.name"
    )
    @Mapping(
        target = "status",
        source = "status.name"
    )
    @Mapping(
        target = "leavingReason",
        source = "leavingReason.name"
    )
    DogResponseDTO toResponse(Dog dog);

}