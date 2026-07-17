package com.assessment.dogs.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;


@MapperConfig(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DogMapperConfig {


}

