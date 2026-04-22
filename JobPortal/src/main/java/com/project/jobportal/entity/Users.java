package com.project.jobportal.entity;


import java.sql.Timestamp;

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
@Table(name="users")
public class Users
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    private String type; 
    
    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String email ;
     
    @Column(nullable=false)
    private String mobile;
    
     
    @Column(nullable=false)
    private String password; 
    
    private int status; 

    
    private Timestamp createdat;
    private Timestamp updatedat; 
  
}
