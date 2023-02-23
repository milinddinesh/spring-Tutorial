package com.example.db_demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer course_id;

    private String courseCode;
    private String courseTitle;
    private Integer coursCredit;

    public Integer getId(){return course_id;}
    public String getCourse_Code(){return courseCode;}
    public String getCourse_Title(){return courseTitle;}
    public Integer getCourse_Credit(){return coursCredit;}

    public void setId(int id){course_id = id;}
    public void setCourse_Code(String courseCode){this.courseCode = courseCode;}
    public void setCourse_Title(String courseTitle){this.courseTitle = courseTitle;}
    public void setCourse_Credit(Integer courseCredit){this.coursCredit = courseCredit;}
}
