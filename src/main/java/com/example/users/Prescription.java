package com.example.users;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity // This tells Hibernate to make a table out of this class
@Table(name="PRESCRIPTION", schema = "PUBLIC") // USER is a reserved word for many SQL databases
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long prescriptionId;

    @Column(name="PRESCRIPTION_NAME")
    private String presciptionName;


}
