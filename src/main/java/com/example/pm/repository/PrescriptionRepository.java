package com.example.pm.repository;

import java.util.List;

import com.example.pm.model.Prescription;

import org.springframework.data.repository.CrudRepository;

public interface PrescriptionRepository extends CrudRepository<Prescription, Integer> {
    List<Prescription> findByPatientName(String patientName);
}
