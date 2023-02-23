package com.example.db_demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.db_demo.Models.Courses;

public interface CoursesRepository extends CrudRepository<Courses,Integer>{
    
}
