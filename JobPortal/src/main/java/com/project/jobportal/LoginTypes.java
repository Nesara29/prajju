package com.project.jobportal;

public enum LoginTypes {
ADMIN("ADMIN"),  
EMPLOYER("EMPLOYER"),
JOBSEEKER("JOBSEEKER");
	private String name;
LoginTypes(String name) {
	this.name=name;
	// TODO Auto-generated constructor stub
} 
}
