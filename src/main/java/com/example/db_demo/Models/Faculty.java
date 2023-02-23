package com.example.db_demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Faculty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer faculty_id;

    private String email;
    private String name;

    public Integer getId(){ return faculty_id;}
    public String getEmail(){return email;}
    public String getName() {return name;}

    public void setId(int id){faculty_id = id;}
    public void setEmail(String email){this.email = email;}
    public void setName(String name ){this.name = name;}

    

}
