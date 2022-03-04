package com.example.pm.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="PRESCRIPTION")
public class Prescription {
	@Id
	private Integer scriptId;

	private String drugName;
	
	private String patientName;
	
	private String drugId;
	
	private Integer quantity;
	
	private String customerInstruction;
	
	public Integer getScriptId() {
		return scriptId;
	}

	public void setScriptId(Integer prescription_id) {
		this.scriptId = prescription_id;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patient_name) {
		this.patientName = patient_name;
	}
	
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drug_name) {
		this.drugName = drug_name;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drug_id) {
		this.drugId = drug_id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer prescription_quantity) {
		this.quantity = prescription_quantity;
	}

	public String getCustomerInstruction() {
		return customerInstruction;
	}

	public void setCustomerInstruction(String instruction_to_customer) {
		this.customerInstruction = instruction_to_customer;
	}

}
