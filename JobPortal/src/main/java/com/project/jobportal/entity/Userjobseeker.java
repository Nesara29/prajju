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
@Entity
@Table(name="user_jobseeker")
public class Userjobseeker
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user;   
    private String subtitle;
    private String aboutme;
    private String skills;
    private String experience;
    private String education;
    private String address; 
    private Timestamp createdat; 
    private Timestamp updatedat; 
  
}
