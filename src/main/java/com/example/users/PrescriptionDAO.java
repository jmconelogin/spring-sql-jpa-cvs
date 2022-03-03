package com.example.users;

import org.springframework.data.repository.CrudRepository;

public interface PrescriptionDAO extends CrudRepository<Prescription, Integer> {

    Prescription findPrescriptionByPresciptionName(String prescriptionName);
}
