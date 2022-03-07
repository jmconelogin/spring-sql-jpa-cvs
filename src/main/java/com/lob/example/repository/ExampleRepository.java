package com.lob.example.repository;

import com.lob.example.model.ExampleModel;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ExampleRepository extends CrudRepository<ExampleModel, Integer> {

}
