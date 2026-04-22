package com.project.jobportal.controller;
 
 
import com.project.jobportal.CommonFuns;
import com.project.jobportal.LoginTypes;
import com.project.jobportal.entity.Job;
import com.project.jobportal.entity.Jobapplication;
import com.project.jobportal.entity.Usercompany;
import com.project.jobportal.entity.Userjobseeker;
import com.project.jobportal.entity.Users;
import com.project.jobportal.repository.*;
import com.project.jobportal.service.LoginsService;

import jakarta.annotation.Resource;  
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.context.SecurityContextHolder;  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest; 
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployerController { 
	
 
	 
	@Autowired 
	private LoginsService employerService;  
	  
    @Resource    private UsersRepository employers;   
	
 
    @Resource    private CompanycategoriesRepository compcategories;
    @Resource    private JobcategoriesRepository jobcategories;
    @Resource    private JobRepository jobs;
    @Resource    private JobapplicationRepository jobapplications;
    @Resource    private UsercompanyRepository usercompanies;
    @Resource    private UserjobseekerRepository userjobseekers;
    @Resource    private UsereducationRepository usereducations;
    @Resource    private UserexperienceRepository userexperiences;
    
	 
	
	 @GetMapping("/employer") 
	    public String index(Model model){
	        //model.addAttribute("users", users.findByTypeOrderByIdDesc(LoginTypes.EMPLOYEE.toString()));
	        return "redirect:/employer/dashboard";
	    } 
	    
	
    @GetMapping("/employer/dashboard") 
    public String dashboard(Model model){
        //model.addAttribute("users", users.findByTypeOrderByIdDesc(LoginTypes.EMPLOYEE.toString()));
    	Users user = employerService.findByEmail(
    		      SecurityContextHolder.getContext().getAuthentication().getName());

    	
    	model.addAttribute("users", employers.countByStatus(1));
    	model.addAttribute("jobs", jobs.count());
    	model.addAttribute("employers", employers.countByTypeAndStatus(LoginTypes.EMPLOYER.name(), 1));
    	model.addAttribute("jobseekers", employers.countByTypeAndStatus(LoginTypes.JOBSEEKER.name(), 1));
    	model.addAttribute("liveposts", jobs.countByStatus(1)); 
    	
    	
        return "employer/dashboard";
    } 
 
    
   
    
     
    
    @GetMapping("/employer/changepassword") 
    public String changepassword(Model model){ 
        return "employer/changepassword";
    } 
     
    
    @GetMapping("/employer/profile")
    public String myprofile(Model model) {
    	Users s = employerService.findByEmail(
  		      SecurityContextHolder.getContext().getAuthentication().getName()); 

    	model.addAttribute("fullname", s.getName()); 
    	model.addAttribute("mobileno", s.getMobile());  
    	model.addAttribute("id",s.getId());   
    	model.addAttribute("username",s.getEmail());   
    	model.addAttribute("regdate", s.getCreatedat().toLocalDateTime().toLocalDate()); 
    	model.addAttribute("updationtime", s.getUpdatedat());  
        model.addAttribute("categories", compcategories.findAll());
        Usercompany cobj=usercompanies.findByUser(s.getId()); 
       	model.addAttribute("category", 0); 
    	model.addAttribute("website","");
    	model.addAttribute("desc", ""); 
	       if(cobj!=null)
	       { 
	       	model.addAttribute("category", cobj.getCategory()); 
	    	model.addAttribute("website", cobj.getUrl());
	    	model.addAttribute("desc", cobj.getCdesc()); 
	       } 
    	return "employer/myprofile";
    } 
     
    
    @PostMapping("/employer/updatepassword")
    public String updatepassword(HttpServletRequest request,Model m)
    {
    	String password=request.getParameter("password");
    	String newpassword=request.getParameter("newpassword");
    	String confirmpassword=request.getParameter("confirmpassword");
    	
    	Users user = employerService.findByEmail(
    		      SecurityContextHolder.getContext().getAuthentication().getName());
    		    if(newpassword.compareTo(confirmpassword)>0)
    		    {
    		    	m.addAttribute("updatepassword_errors", "New Password and Confirm Password Field do not match  !!");
    		    }
    		    else if (!employerService.checkIfValidOldPassword(user, password)) {
    		        m.addAttribute("updatepassword_errors", "Inavlid Current Password");
    		    }
    		    else if(!employerService.validation_Password(newpassword))
    		    {
    		    	  m.addAttribute("updatepassword_errors", "Inavlid New Password.Please follow specified policies.");
    		    }
    		    else
    		    {
    		    	employerService.changeUserPassword(user, newpassword);
    		      m.addAttribute("updatepassword_success", "Password updated successfully");
    		    }
    	return changepassword(m);
    } 
    
    @PostMapping("/employer/updateprofile") 
	   public String updateprofile(MultipartHttpServletRequest request,Model model) throws  Exception   { 
        String fullname = request.getParameter("fullname");   
	     String mobileno =  request.getParameter("mobileno");    
		 MultipartFile img=request.getFile("profileimg");   
	     String website =  request.getParameter("website");    
	     String description =  request.getParameter("description");  
	     int catg=Integer.parseInt(request.getParameter("category"));
	     
	     
	     List < String > errors = new ArrayList < String > ();
	    	Users user = employerService.findByEmail(
	    		      SecurityContextHolder.getContext().getAuthentication().getName());
	     Users obj;
		if (mobileno.trim() .isBlank() || mobileno == null)
	       errors.add("Mobile no");
	     else
	     {
	        obj = employers.findByMobile(mobileno);	   
	      if (obj != null && obj.getId()!=employers.findByEmail(user.getEmail()).getId())
	         errors.add("Mobile no exists!");
	     } 
	     
	     if (  fullname == null || fullname.trim().isBlank()) errors.add("Full name");
	     if (! CommonFuns.isValidUrl(website)) errors.add("Website");
	     if (  description == null || description.trim().isBlank()) errors.add("Description"); 
     
	     if (errors.size() == 0) {  
	       obj = employers.findByEmail(user.getEmail()); 
	       obj.setName(fullname);
	       obj.setMobile(mobileno); 
	       employers.save(obj);
	       Usercompany cobj=usercompanies.findByUser(obj.getId());
	       if(cobj==null)
	    	   cobj=new Usercompany();
	       cobj.setUser(obj.getId());
	       cobj.setCategory(catg);
	       cobj.setUrl(website);
	       cobj.setCdesc(description);
	       usercompanies.save(cobj);
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
    
    @GetMapping("/employer/jobs") 
    public String jobs(Model model){
    	List<Map < String, Object >> arr = new ArrayList<Map < String, Object >> ();
    	Map < String, Object > obj = new HashMap < > ();
    	Users user = employerService.findByEmail(
  		      SecurityContextHolder.getContext().getAuthentication().getName());
    	for(Job s:jobs.findByUserOrderByIdDesc(user.getId())) 
    	{
    		obj = new HashMap < > (); 
    		obj.put("id", s.getId()); 
    		obj.put("position", s.getJobtitle());
    		obj.put("level", s.getJoblevel()); 
    		obj.put("vacancy", s.getVacancycount()); 
    		obj.put("deadline", s.getDeadline());   
    		obj.put("status", s.getStatus());   
    		obj.put("applied",jobapplications.countByPost(s.getId()));   
    		arr.add(obj);
    	}
    	model.addAttribute("jobs",arr);
        return "employer/jobs";
    } 
    
    
    @GetMapping("/employer/viewapplied/{id}") 
    public String viewapplied(@PathVariable int id,Model model){
    	Map < String, Object > obj = new HashMap < > ();
    	
    	Users user = employerService.findByEmail(
  		      SecurityContextHolder.getContext().getAuthentication().getName());

    	List<Map < String, Object >> arr = new ArrayList<Map < String, Object >> ();
    	for(Jobapplication obj1:jobapplications.findByPostAndStatus(id,0)) 
    	{
    		obj = new HashMap < > (); 
    		Users s=employers.findById(obj1.getUser());
    		obj.put("id", obj1.getId());
    		obj.put("uid", s.getId());
    		obj.put("fullname", s.getName());
    		obj.put("emailid", s.getEmail());
    		obj.put("mobile", s.getMobile()); 
    		obj.put("appliedon", obj1.getCreatedat().toLocalDateTime().toLocalDate());  
    		arr.add(obj);
    	}
    	model.addAttribute("applied",arr);
    	
    	arr = new ArrayList<Map < String, Object >> ();
    	for(Jobapplication obj1:jobapplications.findByPostAndStatus(id,1)) 
    	{
    		obj = new HashMap < > (); 
    		Users s=employers.findById(obj1.getUser());
    		obj.put("id", obj1.getId());
    		obj.put("uid", s.getId());
    		obj.put("fullname", s.getName());
    		obj.put("emailid", s.getEmail());
    		obj.put("mobile", s.getMobile()); 
    		obj.put("appliedon", obj1.getCreatedat().toLocalDateTime().toLocalDate());  
    		arr.add(obj);
    	}
    	model.addAttribute("accepted",arr);
    	
    	
    	arr = new ArrayList<Map < String, Object >> ();
    	for(Jobapplication obj1:jobapplications.findByPostAndStatus(id,2)) 
    	{
    		obj = new HashMap < > (); 
    		Users s=employers.findById(obj1.getUser());
    		obj.put("id", obj1.getId());
    		obj.put("uid", s.getId());
    		obj.put("fullname", s.getName());
    		obj.put("emailid", s.getEmail());
    		obj.put("mobile", s.getMobile()); 
    		obj.put("appliedon", obj1.getCreatedat().toLocalDateTime().toLocalDate());  
    		arr.add(obj);
    	}
    	model.addAttribute("rejected",arr);
    	
    	model.addAttribute("job", jobs.findById(id));

    	model.addAttribute("appcount",jobapplications.countByPost(id)); 
        return "employer/viewapplied";
    } 
    
    @PostMapping("/employer/changeapplicationstatus/{status}/{id}")  
	 @ResponseBody
  public  Map < String, Object > changeapplicationstatus(@PathVariable int status,@PathVariable  int id,Model model){ 
  	 Map < String, Object > arr = new HashMap < > ();
		 
		arr.put("success",  1); 
		try {
		 Jobapplication obj=jobapplications.findById(id);
		 obj.setStatus(status);
		 jobapplications.save(obj);
		arr.put("html",  " Changed Successfully! ");
		}
		catch(Exception e)
		{
			arr.put("success",  0);
			arr.put("html",  "Error in changing!");
		}
		return arr;
  }
    
    @GetMapping("/employer/createpost") 
    public String createpost(Model model){  

    	Users user = employerService.findByEmail(
  		      SecurityContextHolder.getContext().getAuthentication().getName());
        Usercompany cobj=usercompanies.findByUser(user.getId()); 
        if(cobj==null)
        	model.addAttribute("pnotupdated", 1); 
        else
    	model.addAttribute("pnotupdated", 0); 
        
        model.addAttribute("categories", jobcategories.findAll());
        return "employer/createpost";
    } 
    
    
    @GetMapping("/employer/companydetails") 
    public String companydetails(Model model){ 
        return "employer/companydetails";
    } 
    
    
    @PostMapping("/employer/addpostdb")  
	 @ResponseBody
   public  Map < String, Object > addpostdb( HttpServletRequest request,Model model){ 
   	    Map < String, Object > arr = new HashMap < > ();
   	    List < String > errors = new ArrayList < String > (); 

    	Users user = employerService.findByEmail(
  		      SecurityContextHolder.getContext().getAuthentication().getName());
		arr.put("success",  1);  
		try { 
		   	    		Job obj=new Job();  
		   	    		obj.setUser(user.getId());
		   	    		obj.setJobtitle(request.getParameter("jobtitle")); 
		   	    		obj.setCategory(CommonFuns.cint(request.getParameter("catg")));
		   	    		obj.setJoblevel(request.getParameter("joblevel"));
		   	    		obj.setVacancycount(CommonFuns.cint(request.getParameter("vacancycount"))); 
		   	    		obj.setEmploymenttype(request.getParameter("employmenttype")); 
		   	    		obj.setJoblocation(request.getParameter("joblocation"));
		   	    		obj.setSalary(request.getParameter("salary"));
		   	    		obj.setDeadline(request.getParameter("deadline"));
		   	    		obj.setEducationlevel(request.getParameter("educationlevel"));
		   	    		obj.setExperience(request.getParameter("experience"));
		   	    		obj.setSkills(request.getParameter("skills"));
		   	    		obj.setSpecifications(request.getParameter("specifications")); 
		   	    		obj.setCreatedat(CommonFuns.ctm());
		   	    		jobs.save(obj); 
		   	 		    arr.put("html",  "Added Successfully! "); 
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
    
    @PostMapping("/employer/changejobstatus/{id}/{status}")  
	 @ResponseBody
   public  Map < String, Object > changejobstatus(@PathVariable("id") int id,@PathVariable("status") int status,Model model){ 
   	 Map < String, Object > arr = new HashMap < > ();
		 
		arr.put("success",  1); 
		try {
		 Job obj=jobs.findById(id);
		 obj.setStatus(status);
		 jobs.save(obj);
		arr.put("html",  " Changed Successfully! ");
		}
		catch(Exception e)
		{
			arr.put("success",  0);
			arr.put("html",  "Error in changing!");
		}
		return arr;
   }
    
    
    @GetMapping("/employer/viewjob/{id}") 
    public String viewjob(@PathVariable("id") int id,Model model) throws Exception
    	{ 

    	 model.addAttribute("applied",jobapplications.countByPost(id));   
    	 Job job=jobs.findById(id);
    	 model.addAttribute("job", job); 
    	 model.addAttribute("deadline", CommonFuns.Formatdt(CommonFuns.toDate(job.getDeadline().substring(0, 10))));
    	 model.addAttribute("catg",jobcategories.findById(job.getCategory()).getName());
  	   return "employer/viewpost";
    	}
    
    @GetMapping("/viewjobseeker/{id}") 
    public String viewjobseeker(@PathVariable("id") int id,Model model) throws Exception
    	{ 

   	         model.addAttribute("user", employers.findById(id)); 
   	         Userjobseeker uj=userjobseekers.findByUser(id);
   	         if(uj!=null) {
   	         model.addAttribute("aboutme", uj.getAboutme()==null?"":uj.getAboutme());
   	         model.addAttribute("subtitle", uj.getSubtitle()==null?"":uj.getSubtitle());
   	         model.addAttribute("skills", uj.getSkills()==null?"":uj.getSkills().split(","));
   	         model.addAttribute("address",uj.getAddress()==null?"": uj.getAddress()); 
   	         }
   	         model.addAttribute("educations", usereducations.findByUser(id)); 

   	         model.addAttribute("experiences", userexperiences.findByUser(id)); 
   	          
    	 
  	         return "employer/viewjobseeker";
    	}
}