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
@Table(name="user_company")
public class Usercompany
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user;  
    private int category;
    private String url;
    private String cdesc;
    private Timestamp updatedat; 
  
}
