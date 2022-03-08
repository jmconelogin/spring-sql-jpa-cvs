package com.lob.example.repository;

import com.lob.example.model.OtherModel;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface OtherRepository extends CrudRepository<OtherModel, Integer> {

}
