package com.example.db_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.db_demo.Models.Faculty;
import com.example.db_demo.Models.Courses;
import com.example.db_demo.Repositories.FacultyRepository;
import com.example.db_demo.Repositories.CoursesRepository;
// import com.example.db_demo.Repositories.AllocationRepository;

@Controller
@RequestMapping(path = "/test")
public class MainController {
    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    // @Autowired
    // private AllocationRepository allocationRepository;

    @PostMapping(path = "/addFaculty")
    public @ResponseBody String addFaculty(@RequestParam String name, @RequestParam String email) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setEmail(email);
        facultyRepository.save(faculty);
        return "Faculty Added Successfully";
    }

    @PostMapping(path = "/addCourse")
    public @ResponseBody String addFaculty(@RequestParam String title, @RequestParam String code,
            @RequestParam Integer credits) {
        Courses course = new Courses();
        course.setCourse_Title(title);
        course.setCourse_Code(code);
        course.setCourse_Credit(credits);
        coursesRepository.save(course);
        return "Course added successfully";
    }

    @GetMapping(path = "/viewFaculty")
    public @ResponseBody Iterable<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @GetMapping(path = "/viewCourses")
    public @ResponseBody Iterable<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

}
