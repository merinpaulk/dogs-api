package com.assessment.dogs.specification;

import com.assessment.dogs.dto.request.DogFilterRequestDTO;
import com.assessment.dogs.entity.Dog;

import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class DogFilterSpecification {

    private DogFilterSpecification() {
    }

    public static Specification<Dog> filter(DogFilterRequestDTO filter) {

        return (root, query, cb) -> {

            var predicate = cb.isFalse(root.get("deleted"));

            if (filter == null) {
                return predicate;
            }

            if (filter.getName() != null && !filter.getName().isBlank()) {

                predicate = cb.and(
                        predicate,
                        cb.like(
                                cb.lower(root.get("name")),
                                "%" + filter.getName().toLowerCase() + "%"
                        )
                );
            }

            if (filter.getBreed() != null && !filter.getBreed().isBlank()) {

                Join<Object, Object> breed = root.join("breed");

                predicate = cb.and(
                        predicate,
                        cb.like(
                                cb.lower(breed.get("name")),
                                "%" + filter.getBreed().toLowerCase() + "%"
                        )
                );
            }

            if (filter.getSupplier() != null && !filter.getSupplier().isBlank()) {

                Join<Object, Object> supplier = root.join("supplier");

                predicate = cb.and(
                        predicate,
                        cb.like(
                                cb.lower(supplier.get("name")),
                                "%" + filter.getSupplier().toLowerCase() + "%"
                        )
                );
            }

            return predicate;

        };

    }

}