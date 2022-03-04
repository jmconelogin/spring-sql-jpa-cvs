package com.example.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;

import com.example.pm.repository.PrescriptionRepository;
import com.example.pm.model.Patient;
import com.example.pm.model.Prescription;
import com.example.pm.repository.PatientRepository;

@Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewPatient (@RequestParam String name
			, @RequestParam String email) {
		Patient patient = new Patient();
		patient.setName(name);
		patient.setEmail(email);
		patientRepository.save(patient);
		return "Saved";
	}

	@PostMapping(path="/addPrescription")
	public @ResponseBody String addPatientPrescription (@RequestParam String drug_name,
			@RequestParam String patient_name,
			@RequestParam Integer quantity,
			@RequestParam String instruction) {
		Prescription prescrip = new Prescription();
		prescrip.setDrugName(drug_name);
		prescrip.setPatientName(patient_name);
		prescrip.setQuantity(quantity);
		prescrip.setCustomerInstruction(instruction);
		prescriptionRepository.save(prescrip);
		return "Saved";
	}
	
	@GetMapping(path="/patients/all")
	public @ResponseBody Iterable<Patient> getAllPatients() {
		// This returns a JSON or XML with the users

		return patientRepository.findAll();
	}

	@GetMapping(path="/prescriptions")
	public @ResponseBody Iterable<Prescription> getPrescriptionsByPatient(@RequestParam String patient_name) {
		// This returns a JSON or XML with the prescriptions for a patient

		return prescriptionRepository.findByPatientName(patient_name);
	}

	@GetMapping(path="/prescriptions/all")
	public @ResponseBody Iterable<Prescription> getAllPrescriptions() {
		// This returns a JSON or XML with all prescriptions
		Iterable<Prescription> result = prescriptionRepository.findAll();
		return  result != null ? result : Collections.emptyList();
	}

}
