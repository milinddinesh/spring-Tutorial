package com.example.db_demo.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.db_demo.Models.Allocation;

public interface AllocationRepository extends CrudRepository<Allocation,Integer>{
    
}
