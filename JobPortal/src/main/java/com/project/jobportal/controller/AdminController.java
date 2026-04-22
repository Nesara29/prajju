package com.project.jobportal.controller;
 
  
import com.project.jobportal.CommonFuns;  
import com.project.jobportal.LoginTypes; 
import com.project.jobportal.entity.*;
import com.project.jobportal.repository.*;
import com.project.jobportal.service.LoginsService;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;  
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.List;
import java.util.Map; 
import java.util.stream.Collectors; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest; 

@Controller
public class AdminController { 
	
 
	 @Autowired 
	private LoginsService adminService;  
	   
    @Resource    private UsersRepository members;
    @Resource    private CompanycategoriesRepository compcategories;
    @Resource    private JobcategoriesRepository jobcategories;
    @Resource    private JobRepository jobs;
	        
	@PersistenceContext
    private EntityManager em;
	  
	 @GetMapping("/admin") 
	    public String index(Model model){
	        //model.addAttribute("users", users.findByTypeOrderByIdDesc(LoginTypes.EMPLOYEE.toString()));
	        return "redirect:/admin/dashboard";
	    } 
	    
	
 
	 
    @GetMapping("/admin/dashboard") 
    public String dashboard(Model model){   
    	
    	model.addAttribute("users", members.countByStatus(1));
    	model.addAttribute("jobs", jobs.count());
    	model.addAttribute("employers", members.countByTypeAndStatus(LoginTypes.EMPLOYER.name(), 1));
    	model.addAttribute("jobseekers", members.countByTypeAndStatus(LoginTypes.JOBSEEKER.name(), 1));
    	model.addAttribute("liveposts", jobs.countByStatus(1)); 
        return "admin/dashboard";
    }  
    
    @GetMapping("/admin/changepassword") 
    public String changepassword(Model model){ 
        return "admin/changepassword";
    } 
     
    
    
    @GetMapping("/admin/employercats") 
    public String employercats(Model model){ 

    	model.addAttribute("categories",compcategories.findAll()); 
    	
        return "admin/employercats";
    } 
    
    
    @PostMapping("/admin/employercats/{action}")  
	 @ResponseBody
   public  Map < String, Object > employercats(@PathVariable String   action,HttpServletRequest request,Model model){ 
   	    Map < String, Object > arr = new HashMap < > ();
   	    List < String > errors = new ArrayList < String > ();
		int id; 
		String name;
		Companycategories obj;
		arr.put("success",  1); 
		try {
		 switch(action)
		 {
		 case "add": 
		   	    name=request.getParameter("name");
		   	    if(name!=null && !name.isBlank())
		   	    {
		   	    	if(compcategories.findByName(name)==null)
		   	    	{
		   	    		obj=new Companycategories();
		   	    		obj.setName(name);
		   	    		compcategories.save(obj); 
		   	 		    arr.put("html",  "Added Successfully! ");
		   	    	}
			   	    else
			   	    	errors.add("Name Already Exists!");
		   	    }
		   	    else
		   	    	errors.add("Empty Name field");
			 break;
		 case "edit": 
		   	    name=request.getParameter("name");
		   	    id=CommonFuns.cint( request.getParameter("id"));
		   	    if(name!=null && !name.isBlank())
		   	    {
		   	    	obj=compcategories.findByName(name);
		   	    	if(obj==null || (obj.getId()==id))
		   	    	{
		   	    		obj=compcategories.findById(id);
		   	    		obj.setName(name);
		   	    		compcategories.save(obj); 
		   	 		    arr.put("html",  "Updated Successfully! ");
		   	    	}
			   	    else
			   	    	errors.add("Name Already Exists!");
		   	    }
		   	    else
		   	    	errors.add("Empty Name field");
			 break;
		 case "delete":
		   	    id=CommonFuns.cint( request.getParameter("id"));
		   	    compcategories.deleteById(id);
	 		    arr.put("html",  "Deleted Successfully! ");
		   	  break;
		 } 
		}
		catch(Exception e)
		{
			errors.add(e.getMessage());
		}
		if(errors.size()>0)
		{
			arr.put("success",  0);
		    arr.put("html", "The following fields contains errors: " + errors.stream().collect(Collectors.joining(", ")));
		}
		return arr;
   }
    
    
    @GetMapping("/admin/jobcats") 
    public String jobcats(Model model){ 

    	model.addAttribute("categories",compcategories.findAll()); 
    	
        return "admin/jobcats";
    } 
    
    
    @PostMapping("/admin/jobcats/{action}")  
	 @ResponseBody
   public  Map < String, Object > jobcats(@PathVariable String   action,HttpServletRequest request,Model model){ 
   	    Map < String, Object > arr = new HashMap < > ();
   	    List < String > errors = new ArrayList < String > ();
		int id; 
		String name;
		Jobcategories obj;
		arr.put("success",  1); 
		try {
		 switch(action)
		 {
		 case "add": 
		   	    name=request.getParameter("name");
		   	    if(name!=null && !name.isBlank())
		   	    {
		   	    	if(jobcategories.findByName(name)==null)
		   	    	{
		   	    		obj=new Jobcategories();
		   	    		obj.setName(name);
		   	    		jobcategories.save(obj); 
		   	 		    arr.put("html",  "Added Successfully! ");
		   	    	}
			   	    else
			   	    	errors.add("Name Already Exists!");
		   	    }
		   	    else
		   	    	errors.add("Empty Name field");
			 break;
		 case "edit": 
		   	    name=request.getParameter("name");
		   	    id=CommonFuns.cint( request.getParameter("id"));
		   	    if(name!=null && !name.isBlank())
		   	    {
		   	    	obj=jobcategories.findByName(name);
		   	    	if(obj==null || (obj.getId()==id))
		   	    	{
		   	    		obj=jobcategories.findById(id);
		   	    		obj.setName(name);
		   	    		jobcategories.save(obj); 
		   	 		    arr.put("html",  "Updated Successfully! ");
		   	    	}
			   	    else
			   	    	errors.add("Name Already Exists!");
		   	    }
		   	    else
		   	    	errors.add("Empty Name field");
			 break;
		 case "delete":
		   	    id=CommonFuns.cint( request.getParameter("id"));
		     	 jobcategories.deleteById(id);
	 		    arr.put("html",  "Deleted Successfully! ");
		   	  break;
		 } 
		}
		catch(Exception e)
		{
			errors.add(e.getMessage());
		}
		if(errors.size()>0)
		{
			arr.put("success",  0);
		    arr.put("html", "The following fields contains errors: " + errors.stream().collect(Collectors.joining(", ")));
		}
		return arr;
   }
    
    
    @PostMapping("/admin/updatepassword")
    public String updatepassword(HttpServletRequest request,Model m)
    {
    	String password=request.getParameter("password");
    	String newpassword=request.getParameter("newpassword");
    	String confirmpassword=request.getParameter("confirmpassword");
    	
    	Users user = adminService.findByEmail(
    		      SecurityContextHolder.getContext().getAuthentication().getName());
    		    if(newpassword.compareTo(confirmpassword)>0)
    		    {
    		    	m.addAttribute("updatepassword_errors", "New Password and Confirm Password Field do not match  !!");
    		    }
    		    else if (!adminService.checkIfValidOldPassword(user, password)) {
    		        m.addAttribute("updatepassword_errors", "Inavlid Current Password");
    		    }
    		    else if(!adminService.validation_Password(newpassword))
    		    {
    		    	  m.addAttribute("updatepassword_errors", "Inavlid New Password.Please follow specified policies.");
    		    }
    		    else
    		    {
    		    	adminService.changeUserPassword(user, newpassword);
    		      m.addAttribute("updatepassword_success", "Password updated successfully");
    		    }
    	return changepassword(m);
    } 

 
    @GetMapping("/admin/employers") 
    public String employers(Model model){
    	List<Map < String, Object >> arr = new ArrayList<Map < String, Object >> ();
    	Map < String, Object > obj = new HashMap < > ();
    	Users obj1=new Users();
    	for(Users s:members.findByTypeOrderByNameAsc(LoginTypes.EMPLOYER.name())) 
    	{
    		obj = new HashMap < > ();
    		obj1=adminService.findByEmail(s.getEmail());
    		obj.put("id", obj1.getId());
    		obj.put("fullname", s.getName());
    		obj.put("emailid", s.getEmail());
    		obj.put("mobile", s.getMobile()); 
    		obj.put("regdate", s.getCreatedat().toLocalDateTime().toLocalDate()); 
    		obj.put("status", obj1.getStatus());
    		arr.add(obj);
    	}
    	model.addAttribute("regusers",arr);
        return "admin/employers";
    } 
    
    @GetMapping("/admin/job-seekers") 
    public String jobseekers(Model model){
    	List<Map < String, Object >> arr = new ArrayList<Map < String, Object >> ();
    	Map < String, Object > obj = new HashMap < > ();
    	Users obj1=new Users();
    	for(Users s:members.findByTypeOrderByNameAsc(LoginTypes.JOBSEEKER.name())) 
    	{
    		obj = new HashMap < > ();
    		obj1=adminService.findByEmail(s.getEmail());
    		obj.put("id", obj1.getId());
    		obj.put("fullname", s.getName());
    		obj.put("emailid", s.getEmail());
    		obj.put("mobile", s.getMobile()); 
    		obj.put("regdate", s.getCreatedat().toLocalDateTime().toLocalDate()); 
    		obj.put("status", obj1.getStatus());
    		arr.add(obj);
    	}
    	model.addAttribute("regusers",arr);
        return "admin/jobseekers";
    } 
     @PostMapping("/admin/changeuserstatus/{id}/{status}")  
	 @ResponseBody
    public  Map < String, Object > changeuserstatus(@PathVariable("id") int id,@PathVariable("status") int status,Model model){ 
    	 Map < String, Object > arr = new HashMap < > ();
		 
		arr.put("success",  1); 
		try {
		 Users obj=members.findById(id);
		 obj.setStatus(status);
		 members.save(obj);
		arr.put("html",  " Changed Successfully! ");
		}
		catch(Exception e)
		{
			arr.put("success",  0);
			arr.put("html",  "Error in changing!");
		}
		return arr;
    }
    
    @GetMapping("/admin/profile")
    public String myprofile(Model model) {
    	
    	Users s = adminService.findByEmail(
    		      SecurityContextHolder.getContext().getAuthentication().getName());
    	 
    	model.addAttribute("fullname", s.getName()); 
    	model.addAttribute("mobileno", s.getMobile());  
    	model.addAttribute("id",s.getId());   
    	model.addAttribute("username",s.getEmail());   
    	model.addAttribute("regdate", s.getCreatedat().toLocalDateTime().toLocalDate()); 
    	model.addAttribute("updationtime", s.getUpdatedat()); 
    	
    	return "admin/myprofile";
    } 
    
    @PostMapping("/admin/updateprofile") 
	   public String updateprofile( MultipartHttpServletRequest request,Model model) throws Exception   { 

	     String fullname = request.getParameter("fullname");   
	     String mobileno =  request.getParameter("mobileno");    
		    MultipartFile img=request.getFile("profileimg");   
	     
	     List < String > errors = new ArrayList < String > ();
	    	Users user = adminService.findByEmail(
	    		      SecurityContextHolder.getContext().getAuthentication().getName());
	     Users obj;
		if (mobileno.trim() .isBlank() || mobileno == null)
	       errors.add("Mobile no");
	     else
	     {
	        obj = members.findByMobile(mobileno);	   
	      if (obj != null && obj.getId()!=members.findByEmail(user.getEmail()).getId())
	         errors.add("Mobile no exists!");
	     } 
	     
	     if (  fullname == null || fullname.trim().isBlank()) errors.add("Full name");

	     if (errors.size() == 0) {  
	       obj = members.findByEmail(user.getEmail()); 
	       obj.setName(fullname);
	       obj.setMobile(mobileno); 
	       members.save(obj);
	       if(img.getSize()>0)
	        {
	    		 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\profileimage\\"; 
				(new File(fpath+user.getId())).createNewFile();
	             FileOutputStream fl;
	             fl=new FileOutputStream(fpath+user.getId()); 
	             fl.write(img.getBytes());                       
	             fl.close(); 
	        }
	       model.addAttribute("success",  " Updated successfully!."); 
	       
	     } else
	    	 model.addAttribute("error",  "The following fields contains errors: " + errors.stream().collect(Collectors.joining(", ")));

	     return  myprofile(model);
	   } 
    
    
    @GetMapping("/viewprofileimage/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> vieweprofileimage(@PathVariable("id") int id,HttpServletResponse response) throws Exception
    	{ 
    	 InputStreamResource file;
  	   String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\profileimage\\"; 
  	   if(new File(fpath+id).exists())
  	        file=  new InputStreamResource(new FileInputStream(new File(fpath+id)));
  	   else 
  		     file=  new InputStreamResource(new FileInputStream(new File(fpath+"profile-default.jpg")));
  	   
  	   return ResponseEntity.ok()
  			   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
  			   .body(file); 
    	}
    
    @GetMapping("/viewbannerimage/{id}")
    @ResponseBody
    public ResponseEntity<InputStreamResource> viewbannerimage(@PathVariable("id") int id,HttpServletResponse response) throws Exception
    	{ 
    	 InputStreamResource file;
  	   String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\banners\\"; 
  	   if(new File(fpath+id).exists())
  	        file=  new InputStreamResource(new FileInputStream(new File(fpath+id)));
  	   else 
  		     file=  new InputStreamResource(new FileInputStream(new File(fpath+"nocover.jpg")));
  	   
  	   return ResponseEntity.ok()
  			   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
  			   .body(file); 
    	}
    
    @GetMapping("/admin/jobs") 
    public String jobs(Model model){
    	List<Map < String, Object >> arr = new ArrayList<Map < String, Object >> ();
    	Map < String, Object > obj = new HashMap < > ();
    	Users obj1=new Users();
    	for(Job s:jobs.findByStatus(1)) 
    	{
    		obj = new HashMap < > ();
    		obj1=members.findById(s.getUser());
    		obj.put("id", s.getId());
    		obj.put("postedby", obj1.getName());
    		obj.put("position", s.getJobtitle());
    		obj.put("level", s.getJoblevel()); 
    		obj.put("vacancy", s.getVacancycount()); 
    		obj.put("deadline", s.getDeadline());   
    		arr.add(obj);
    	}
    	model.addAttribute("jobs",arr);
        return "admin/jobs";
    } 
    
    @GetMapping("/myprofileimage")
    @ResponseBody
    public ResponseEntity<InputStreamResource> myprofileimage(HttpServletResponse response) throws Exception
    	{ 
    	Users user = adminService.findByEmail(
  		      SecurityContextHolder.getContext().getAuthentication().getName());
    	int id=user.getId();
    	 InputStreamResource file;
  	   String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\profileimage\\"; 
  	   if(new File(fpath+id).exists())
  	        file=  new InputStreamResource(new FileInputStream(new File(fpath+id)));
  	   else 
  		     file=  new InputStreamResource(new FileInputStream(new File(fpath+"profile-default.jpg")));
  	   
  	   return ResponseEntity.ok()
  			   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
  			   .body(file); 
    	}
    

}