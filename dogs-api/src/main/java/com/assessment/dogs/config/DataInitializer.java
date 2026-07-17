package com.assessment.dogs.config;

import com.assessment.dogs.entity.Breed;
import com.assessment.dogs.entity.Supplier;
import com.assessment.dogs.entity.LeavingReason;
import com.assessment.dogs.entity.Status;
import com.assessment.dogs.repository.BreedRepository;
import com.assessment.dogs.repository.LeavingReasonRepository;
import com.assessment.dogs.repository.StatusRepository;
import com.assessment.dogs.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class DataInitializer 
        implements CommandLineRunner {

	private final BreedRepository breedRepository;

	private final SupplierRepository supplierRepository;

    private final StatusRepository statusRepository;


    private final LeavingReasonRepository leavingReasonRepository;



    @Override
    public void run(String... args) {


    	createBreeds();

        createSuppliers();

        createStatuses();

        createLeavingReasons();

    }


    private void createBreeds() {

        String[] breeds = {

                "German Shepherd",

                "Belgian Malinois",

                "Labrador Retriever",

                "Springer Spaniel",

                "Cocker Spaniel",

                "Dutch Shepherd"

        };


        for (String value : breeds) {

            if (breedRepository.findByName(value).isEmpty()) {

                Breed breed = new Breed();

                breed.setName(value);

                breedRepository.save(breed);

            }

        }

    }
    
    private void createSuppliers() {

        String[] suppliers = {

                "National Police Kennels",

                "Elite Dog Breeders",

                "K9 Working Dogs Ltd",

                "Police Dog Training Centre"

        };


        for (String value : suppliers) {

            if (supplierRepository.findByName(value).isEmpty()) {

                Supplier supplier = new Supplier();

                supplier.setName(value);

                supplierRepository.save(supplier);

            }

        }

    }

    private void createStatuses(){


        String[] statuses = {


                "In Training",

                "In Service",

                "Retired",

                "Left"

        };


        for(String value : statuses){


            if(statusRepository.findByName(value)
                    .isEmpty()){


                Status status = new Status();


                status.setName(value);


                statusRepository.save(status);

            }

        }


    }



    private void createLeavingReasons(){


        String[] reasons = {


                "Transferred",

                "Retired (Put Down)",

                "KIA",

                "Rejected",

                "Retired (Re-housed)",

                "Died"

        };



        for(String value : reasons){


            if(leavingReasonRepository.findByName(value)
                    .isEmpty()){


                LeavingReason reason =
                        new LeavingReason();


                reason.setName(value);


                leavingReasonRepository.save(reason);

            }

        }


    }


}