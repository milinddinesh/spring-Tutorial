package com.example.db_demo.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.db_demo.Models.Faculty;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface FacultyRepository extends CrudRepository<Faculty, Integer> {

}