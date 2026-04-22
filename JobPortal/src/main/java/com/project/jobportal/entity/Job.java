package com.project.jobportal.entity;


import java.sql.Timestamp;
import java.util.List;

import com.project.jobportal.LoginTypes;

import jakarta.persistence.*; 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@Table(name="posts")
@Entity(name = "posts")
public class Job
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user;
    private String jobtitle;
    private int category;
    private String joblevel;
    private int vacancycount;
    private String employmenttype;
    private String salary;
    private String joblocation;
    private String deadline;
    private String educationlevel;
    private String experience;
    private String skills;
    private String specifications;
    private int views;

    private int status; 

    
    private Timestamp createdat;
    private Timestamp updatedat; 
  
}
