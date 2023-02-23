package com.example.db_demo.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.example.db_demo.Models.Courses;
import com.example.db_demo.Models.Faculty;

@Entity
public class Allocation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer allocation_id;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course",referencedColumnName = "course_id")
    private Courses course;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty", referencedColumnName="faculty_id")
    private Faculty faculty;

}



