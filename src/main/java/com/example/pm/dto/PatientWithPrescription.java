package com.example.pm.dto;

import java.util.List;

import com.example.pm.model.Prescription;

public class PatientWithPrescription {
    private Integer id;

	private String name;

	private String email;

    private List<Prescription> scripts;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Prescription> getScripts() {
        return this.scripts;
    }

    public void setScripts(List<Prescription> scripts) {
        this.scripts = scripts;
    }



}
