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
@Table(name="chatroom") 
public class Chatroom
{ 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user; 
    private int status;  
    private Timestamp updatedat; 
  
}
