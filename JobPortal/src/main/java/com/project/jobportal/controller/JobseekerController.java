package com.project.jobportal.controller;
 
 
import com.project.jobportal.CommonFuns;
import com.project.jobportal.CountByUser;
import com.project.jobportal.LoginTypes; 
import com.project.jobportal.entity.*;
import com.project.jobportal.repository.*;
import com.project.jobportal.service.LoginsService;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;  
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest; 
 

@Controller
public class JobseekerController { 
	
 
	 
	@Autowired 
	private LoginsService jobseekerService;  
	  
    @Resource    private UsersRepository jobseekers;   
	

    @Resource    private CompanycategoriesRepository compcategories;
    @Resource    private JobcategoriesRepository jobcategories;
    @Resource    private JobRepository jobs;
    @Resource    private JobapplicationRepository jobapplications;
    @Resource    private SavedpostRepository savedjobs;
    @Resource    private UsercompanyRepository usercompanies;
    @Resource    private UserjobseekerRepository userjobseekers;
    @Resource    private UsereducationRepository usereducations;
    @Resource    private UserexperienceRepository userexperiences;
    @Resource    private ChatroomRepository chatroom;
    
	     @GetMapping("/")
	    public String index(Model model){  
		   List<Object> jbs=new ArrayList<>();
		   List<Object> companies=new ArrayList<>();
		   Map<String,Object> arr = new HashMap < > ();
		   int ctr=0;
		   for(Job j:jobs.findByStatusOrderByIdDesc(1))
		    {
			    arr = new HashMap < > ();
			    arr.put("id", j.getId());
			    arr.put("title", j.getJobtitle()); 
			    arr.put("etype", j.getEmploymenttype()); 
			    arr.put("salary", j.getSalary()); 
			    arr.put("location", j.getJoblocation()); 
			    arr.put("catg",jobcategories.findById(j.getCategory()).getName());
			    Users u=jobseekers.findById(j.getUser());
			    arr.put("postedby",u.getName()); 
			    arr.put("postedbyid",u.getId());
		    	jbs.add(arr);
		    	if(++ctr>=6) break;
		    }
		    model.addAttribute("jobs", jbs);
		    
		    for( CountByUser uc:jobs.CountByUserInterface())
		    {
		    	    Users u=jobseekers.findById(uc.getUser());
				    arr.put("company",u.getName()); 
				    arr.put("id",u.getId());
				    companies.add(arr);
		    }
		    model.addAttribute("companies", companies);
		    model.addAttribute("jobcategories", jobcategories.findAll());
		    
	        return "index";
	    }  

	    @GetMapping("/signup") 
	    public String signup(Authentication auth,Model model){  
	    	
	    	 if(auth!=null)
	 		    if(auth.isAuthenticated())
	 		    {
	 		    	if(!auth.getAuthorities().isEmpty())
	 		    	{
	 		    		for(LoginTypes l:LoginTypes.values())
	 		    		if(auth.getAuthorities().contains(new SimpleGrantedAuthority(l.toString())))
	 		              	return "redirect:/"+l.toString().toLowerCase()+"/dashboard"; 
	 		    	}
	 		    }
	    	 
	        return "jobseeker/signup";
	    } 
	    
	    
	    @PostMapping("/search") 
	    public String search( HttpServletRequest request,Model model){  
	    	 Map < String, Object > arr = new HashMap < > (); 
	         List<Object> jbs=new ArrayList<Object>();
	    	 String jobtitle = request.getParameter("jobtitle");    
		     for(Job j:jobs.filterrecords(em, 1,jobtitle, 0, "0", "0",0))
		     {
		    	 arr = new HashMap < > ();
				    arr.put("id", j.getId());
				    arr.put("title", j.getJobtitle()); 
				    arr.put("etype", j.getEmploymenttype()); 
				    arr.put("salary", j.getSalary()); 
				    arr.put("location", j.getJoblocation()); 
				    arr.put("catg",jobcategories.findById(j.getCategory()).getName());
				    Users u=jobseekers.findById(j.getUser());
				    arr.put("postedby",u.getName()); 
				    arr.put("postedbyid",u.getId());
				    arr.put("postedon",CommonFuns.FormatdtOnly(j.getCreatedat()));
			    	jbs.add(arr); 
		     }
		     
	    	 model.addAttribute("jobtitle", jobtitle);
	    	 model.addAttribute("jobs", jbs); 
	    	 model.addAttribute("count", jbs.size());
	        return "jobseeker/search";
	    } 
	    
	    
	    @GetMapping("/fromcategory/{id}") 
	    public String fromcategory( @PathVariable int id,Model model){  
	    	 Map < String, Object > arr = new HashMap < > ();  
	         List<Object> jbs=new ArrayList<Object>(); 
		     for(Job j:jobs.filterrecords(em, 1,"", id, "0", "0",0))
		     {
		    	 arr = new HashMap < > ();
				    arr.put("id", j.getId());
				    arr.put("title", j.getJobtitle()); 
				    arr.put("etype", j.getEmploymenttype()); 
				    arr.put("salary", j.getSalary()); 
				    arr.put("location", j.getJoblocation()); 
				    arr.put("catg",jobcategories.findById(j.getCategory()).getName());
				    Users u=jobseekers.findById(j.getUser());
				    arr.put("postedby",u.getName()); 
				    arr.put("postedbyid",u.getId());
				    arr.put("postedon",CommonFuns.FormatdtOnly(j.getCreatedat()));
			    	jbs.add(arr); 
		     }
		     
	    	 model.addAttribute("jobtitle", "Category : "+jobcategories.findById(id).getName());
	    	 model.addAttribute("jobs", jbs); 
	    	 model.addAttribute("count", jbs.size());
	        return "jobseeker/search";
	    } 
	    
	    
	    
	    @GetMapping("/fromcompany/{id}") 
	    public String fromcompany( @PathVariable int id,Model model){  
	    	 Map < String, Object > arr = new HashMap < > ();  
	         List<Object> jbs=new ArrayList<Object>(); 
		     for(Job j:jobs.filterrecords(em, 1,"", 0, "0", "0",id))
		     {
		    	 arr = new HashMap < > ();
				    arr.put("id", j.getId());
				    arr.put("title", j.getJobtitle()); 
				    arr.put("etype", j.getEmploymenttype()); 
				    arr.put("salary", j.getSalary()); 
				    arr.put("location", j.getJoblocation()); 
				    arr.put("catg",jobcategories.findById(j.getCategory()).getName());
				    Users u=jobseekers.findById(j.getUser());
				    arr.put("postedby",u.getName()); 
				    arr.put("postedbyid",u.getId());
				    arr.put("postedon",CommonFuns.FormatdtOnly(j.getCreatedat()));
			    	jbs.add(arr); 
		     }
		     
	    	 model.addAttribute("jobtitle", "Company : "+jobseekers.findById(id).getName());
	    	 model.addAttribute("jobs", jbs); 
	    	 model.addAttribute("count", jbs.size());
	        return "jobseeker/search";
	    } 
	    
	    
	    @GetMapping("/getaccountlink/{name}") 
	    public String getaccountlink(@PathVariable String name,Authentication auth,Model model){  
 
	    	 if(auth!=null)
	 		    if(auth.isAuthenticated())
	 		    {
	 		    	if(!auth.getAuthorities().isEmpty())
	 		    	{
	 		    		for(LoginTypes l:LoginTypes.values())
	 		    		if(auth.getAuthorities().contains(new SimpleGrantedAuthority(l.toString())))
	 		              	return "redirect:/"+l.toString().toLowerCase()+"/"+name; 
	 		    	}
	 		    }
	        return "/login";
	    } 
	    
	   
	    
	    @PostMapping("/checkAvailability")
	 	@ResponseBody
	    public Map<String, Object>   checkAvailability(HttpServletRequest request, Model model){  
	        Map < String, Object > arr = new HashMap < > ();  
	     	String un=request.getParameter("emailid"); 
	     	  arr.put("html", "<span style='color:green'> Email available for Registration .</span><script>$('#submit').prop('disabled',false);</script>");
	     	if(jobseekerService.findByEmail(un)!=null)
	     	  arr.put("html", "<span style='color:red'> Email already exists .</span><script>$('#submit').prop('disabled',true);</script>");
	   		arr.put("success",  1); 
	   		return arr;
	    }
	    
	    
	    @PostMapping("/registerdb") 
	    @ResponseBody
		   public Map < String, Object > registerdb( HttpServletRequest request,Model model)   {
		     Map < String, Object > arr = new HashMap < > ();  

		     String fullname = request.getParameter("name");   
		     String mobileno =  request.getParameter("mobile");    
		     String emailid =  request.getParameter("email");    
		     String password =  request.getParameter("password");    
		     String type =  request.getParameter("type");    
		     String confirmpassword =  request.getParameter("confirmpassword");   
		      
		     List < String > errors = new ArrayList < String > ();  
		     
		     Users obj;
			if (mobileno.trim().isBlank() || mobileno == null)
		       errors.add("Mobile no");
		     else
		     {
		        obj = jobseekers.findByMobile(mobileno);	   
		      if (obj != null)
		         errors.add("Mobile no exists!");
		     } 
		     
		     if (password.compareTo(confirmpassword)!=0) errors.add("Both passwords should match!");
		     if (!jobseekerService.validation_Password(password)) errors.add("A valid Password");
		     
		     if (emailid.trim() .isBlank() || emailid == null)
			       errors.add("Emailid");
			     else
			     {
			        Users u = jobseekerService.findByEmail(emailid);	   
			      if (u != null)
			         errors.add("Emailid exists!");
			     }
		     
		     if (  fullname == null || fullname.trim().isBlank()) errors.add("Full name");
		     
		     if (type.compareTo(LoginTypes.EMPLOYER.name())!=0 && type.compareTo(LoginTypes.JOBSEEKER.name())!=0)
		       errors.add("Inavlid login Type!");
		       
		     arr.put("success",  0); 
		     if (errors.size() == 0) {   
		       Users u=new Users();
		       u.setPassword(CommonFuns.encode64(password));
		       u.setStatus(0);
		       u.setName(fullname); 
		       u.setEmail(emailid);
		       u.setMobile(mobileno); 
		       u.setType(type);
		       u.setCreatedat(CommonFuns.ctm());
		       jobseekers.save(u);
		       jobseekerService.changeUserPassword(u, password);
		    	   arr.put("success",  1); 
			       arr.put("html",  "Submitted successfully!.You can login after ADMIN approve your registration.");   
		     } else
		    	 arr.put("html",  "The following fields contains errors: " + errors.stream().collect(Collectors.joining(", ")));

		     return  arr;
		   }   
	    
	 @GetMapping("/login")
	    public String login(Authentication auth){  
		 //jobseekerService.changeUserPassword(jobseekerService.findByEmail("admin@admin.com"),"admin@123");
		   if(auth!=null)
		    if(auth.isAuthenticated())
		    { 
		    	if(!auth.getAuthorities().isEmpty())
		    	{  
		    		for(LoginTypes l:LoginTypes.values())
		    		{
		    		if(auth.getAuthorities().contains(new SimpleGrantedAuthority(l.toString())))
		    		{
		    			
		    			return "redirect:/"+l.toString().toLowerCase()+"/dashboard"; 
		    	   }
		    	}
		    }
		    }
	        return "jobseeker/login";
	    }  
 
	
	 @GetMapping("/jobseeker") 
	    public String jobseeker(Model model){
	        //model.addAttribute("users", users.findByTypeOrderByIdDesc(LoginTypes.EMPLOYEE.toString()));
	        return "redirect:/jobseeker/dashboard";
	    } 
	 
	
	    
	
    @GetMapping("/jobseeker/dashboard") 
    public String dashboard(Model model){
        //model.addAttribute("users", users.findByTypeOrderByIdDesc(LoginTypes.EMPLOYEE.toString()));
    	Users user = jobseekerService.findByEmail(
    		      SecurityContextHolder.getContext().getAuthentication().getName());


    	 
    	
    	
        return "jobseeker/dashboard";
    } 
 
    
    
   
    
     
    
    @GetMapping("/jobseeker/changepassword") 
    public String changepassword(Model model){ 
        return "jobseeker/changepassword";
    } 
     
    
    @GetMapping("/jobseeker/profile")
    public String myprofile(Model model) {
    	Users user = jobseekerService.findByEmail(
  		      SecurityContextHolder.getContext().getAuthentication().getName());
    	Users s=jobseekers.findByEmail(user.getEmail());  
    	model.addAttribute("fullname", s.getName()); 
    	model.addAttribute("mobileno", s.getMobile());  
    	model.addAttribute("id",s.getId());   
    	model.addAttribute("username",s.getEmail());   
    	model.addAttribute("regdate", s.getCreatedat().toLocalDateTime().toLocalDate()); 
    	model.addAttribute("updationtime", s.getUpdatedat()); 

	         Userjobseeker uj=userjobseekers.findByUser(s.getId());
	         if(uj!=null) {
	         model.addAttribute("aboutme", uj.getAboutme());
	         model.addAttribute("skills", uj.getSkills());
	         model.addAttribute("address", uj.getAddress());  
	     	model.addAttribute("subtitle", uj.getSubtitle()); 
	         }
	        	 model.addAttribute("educations",usereducations.findByUser(s.getId()));
	        	 model.addAttribute("experiences",userexperiences.findByUser(s.getId()));
	        
	         
    	return "jobseeker/myprofile";
    } 
     
    
    @PostMapping("/jobseeker/updatepassword")
    public String updatepassword(HttpServletRequest request,Model m)
    {
    	String password=request.getParameter("password");
    	String newpassword=request.getParameter("newpassword");
    	String confirmpassword=request.getParameter("confirmpassword");
    	
    	Users user = jobseekerService.findByEmail(
    		      SecurityContextHolder.getContext().getAuthentication().getName());
    		    if(newpassword.compareTo(confirmpassword)>0)
    		    {
    		    	m.addAttribute("updatepassword_errors", "New Password and Confirm Password Field do not match  !!");
    		    }
    		    else if (!jobseekerService.checkIfValidOldPassword(user, password)) {
    		        m.addAttribute("updatepassword_errors", "Inavlid Current Password");
    		    }
    		    else if(!jobseekerService.validation_Password(newpassword))
    		    {
    		    	  m.addAttribute("updatepassword_errors", "Inavlid New Password.Please follow specified policies.");
    		    }
    		    else
    		    {
    		    	jobseekerService.changeUserPassword(user, newpassword);
    		      m.addAttribute("updatepassword_success", "Password updated successfully");
    		    }
    	return changepassword(m);
    } 
    
    
    @PostMapping("/jobseeker/updateprofile") 
    @ResponseBody
	   public Map<String,Object> updateprofile(MultipartHttpServletRequest request,Model model) throws  Exception   { 
    	Map < String, Object > arr = new HashMap < > ();
    	
    	String fullname = request.getParameter("fullname");   
	     String mobileno =  request.getParameter("mobileno");    
	     String aboutme =  request.getParameter("aboutme");    
	     String skills =  request.getParameter("skills");    
	     String subtitle =  request.getParameter("subtitle");   
	     String address =  request.getParameter("address");   
	     MultipartFile img=request.getFile("profileimg");   
	     List < String > errors = new ArrayList < String > ();
	     arr.put("success",  1);
	     Users user = jobseekerService.findByEmail(
		      SecurityContextHolder.getContext().getAuthentication().getName());
	     
	     Users obj;
		if (mobileno.trim() .isBlank() || mobileno == null)
	       errors.add("Mobile no");
	     else
	     {
	        obj = jobseekers.findByMobile(mobileno);	   
	      if (obj != null && obj.getId()!=jobseekers.findByEmail(user.getEmail()).getId())
	         errors.add("Mobile no exists!");
	     } 
	     
	     if (  fullname == null || fullname.trim().isBlank()) errors.add("Full name");
	     String[] t1,t2,t3,t4,t5,t6,t7,t8;int len;
	     Map < String, Object > e = new HashMap < > (); 
         t1=t2=t3=t4=t5=t6=t7=t8=null;  
	     List<Object> experience=new ArrayList<>();
	     if(request.getParameterValues("institution[]")!=null)
	     {
	     len=request.getParameterValues("institution[]").length;
	      t1=new String[len];t2=new String[len];t3=new String[len];t4=new String[len]; 
	     //org.json.JSONObject.valueToString(
	     if(request.getParameterValues("institution[]").length>0)
             t1= request.getParameterValues("institution[]");
	     
	     if(request.getParameterValues("course[]").length>0)
	    	 t2= request.getParameterValues("course[]");
	     
	     if(request.getParameterValues("efrom[]").length>0)
	    	 t3= request.getParameterValues("efrom[]") ;
	     
	     if(request.getParameterValues("eto[]").length>0)
	    	 t4=  request.getParameterValues("eto[]") ;
	     
	      
	     }
	     
	     if(request.getParameterValues("company[]")!=null)
	     {
	     len=request.getParameterValues("company[]").length;
	      t5=new String[len];t6=new String[len];t7=new String[len];t8=new String[len]; 
	      
	     if(request.getParameterValues("company[]").length>0)
             t5= request.getParameterValues("company[]");
	     
	     if(request.getParameterValues("position[]").length>0)
	    	 t6= request.getParameterValues("position[]");
	     
	     if(request.getParameterValues("exfrom[]").length>0)
	    	 t7= request.getParameterValues("exfrom[]") ;
	     
	     if(request.getParameterValues("exto[]").length>0)
	    	 t8=  request.getParameterValues("exto[]") ; 
	     
	     }	     
	     
	     if (errors.size() == 0) {  
	       obj = jobseekers.findByEmail(user.getEmail()); 
	       obj.setName(fullname);
	       obj.setMobile(mobileno); 
	       jobseekers.save(obj);
	       if(img.getSize()>0)
	        {
	    		 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\profileimage\\"; 
				(new File(fpath+user.getId())).createNewFile();
	             FileOutputStream fl;
	             fl=new FileOutputStream(fpath+user.getId()); 
	             fl.write(img.getBytes());                       
	             fl.close(); 
	        }
	       Userjobseeker uj=userjobseekers.findByUser(obj.getId());
	       if(uj==null)
	    	   uj=new Userjobseeker();
	       uj.setUser(obj.getId());
	       uj.setAboutme(aboutme);
	       uj.setAddress(address);
	       uj.setSubtitle(subtitle);
	       uj.setSkills(skills);  
	       userjobseekers.save(uj);
	       usereducations.deleteByUser(em,obj.getId());
	       if(t1.length>0)
		     {
		    	 for(int i=0;i<t1.length;i++)
		    	 {
		    		 Usereducation eobj = new Usereducation();
		    		 eobj.setInstitution(t1[i]);
		    		 eobj.setUser(obj.getId());
		    		 eobj.setCourse(t2[i]);
		    		 eobj.setDtfrom(t3[i]);
		    		 eobj.setDtto(t4[i]);
		    		 usereducations.save(eobj);
		    	 }
		     }
	       
	       userexperiences.deleteByUser(em,obj.getId());
	       if(t5.length>0)
		     {
		    	 for(int i=0;i<t5.length;i++)
		    	 {
		    		 Userexperience eobj = new Userexperience();
		    		 eobj.setCompany(t5[i]);
		    		 eobj.setUser(obj.getId());
		    		 eobj.setPosition(t6[i]);
		    		 eobj.setDtfrom(t7[i]);
		    		 eobj.setDtto(t8[i]);
		    		 userexperiences.save(eobj);
		    	 }
		     }
	       arr.put("html",  " Updated successfully!."); 
	       
	     } else
	     {
	    	 arr.put("success",  0);
	    	 arr.put("html",  "The following fields contains errors: " + errors.stream().collect(Collectors.joining(", ")));
	     }
	     return  arr;
	   }
    

    
    @GetMapping("/jobseeker/searchjobs") 
    public String searchjobs(Model model){ 

        model.addAttribute("categories", jobcategories.findAll());
        return "jobseeker/searchjobs";
    } 
     
    

    @GetMapping("/jobseeker/appliedjobs") 
    public String appliedjobs(Model model){
    	List<Map < String, Object >> arr = new ArrayList<Map < String, Object >> ();
    	Map < String, Object > obj = new HashMap < > ();
    	Users user = jobseekerService.findByEmail(
  		      SecurityContextHolder.getContext().getAuthentication().getName());
    	for(Jobapplication ja:jobapplications.findByUserOrderByIdDesc(user.getId())) 
    	{
    		obj = new HashMap < > ();
    		Job s=jobs.findById(ja.getPost());
    		obj.put("id", s.getId()); 
    		obj.put("position", s.getJobtitle());
    		obj.put("level", s.getJoblevel()); 
    		obj.put("vacancy", s.getVacancycount()); 
    		obj.put("deadline", s.getDeadline());   
    		obj.put("status", s.getStatus());    
    		obj.put("appliedon", ja.getCreatedat());   
    		obj.put("postedby",jobseekers.findById(s.getUser()).getName());   
    		arr.add(obj);
    	}
    	model.addAttribute("jobs",arr);
        return "jobseeker/appliedjobs";
    } 
    
    

    @GetMapping("/jobseeker/savedjobs") 
    public String savedjobs(Model model){
    	List<Map < String, Object >> arr = new ArrayList<Map < String, Object >> ();
    	Map < String, Object > obj = new HashMap < > ();
    	Users user = jobseekerService.findByEmail(
  		      SecurityContextHolder.getContext().getAuthentication().getName());
    	for(Savedpost ja:savedjobs.findByUserOrderByIdDesc(user.getId())) 
    	{
    		obj = new HashMap < > ();
    		Job s=jobs.findById(ja.getPost());
    		obj.put("id", s.getId()); 
    		obj.put("position", s.getJobtitle());
    		obj.put("level", s.getJoblevel()); 
    		obj.put("vacancy", s.getVacancycount()); 
    		obj.put("deadline", s.getDeadline());   
    		obj.put("status", s.getStatus());    
    		obj.put("appliedon", ja.getCreatedat());   
    		obj.put("postedby",jobseekers.findById(s.getUser()).getName());   
    		arr.add(obj);
    	}
    	model.addAttribute("jobs",arr);
        return "jobseeker/savedjobs";
    } 
    
	@PersistenceContext
    private EntityManager em;
	
    @PostMapping("/getjobsearch") 
    @ResponseBody
	   public List<Object> getjobsearch( HttpServletRequest request,Model model)   {
	     Map < String, Object > obj = new HashMap < > (); 
         List<Object> jbs=new ArrayList<Object>();
	     
	     String jobtitle = request.getParameter("jobtitle");   
	     int catg =CommonFuns.cint(request.getParameter("catg"));    
	     String employmenttype =  request.getParameter("employmenttype");    
	     String experience =  request.getParameter("experience");    
	     for(Job s:jobs.filterrecords(em, 1,jobtitle, catg, employmenttype, experience,0))
	     {
	    	 obj = new HashMap < > (); 
	    	    obj.put("id", s.getId()); 
	    		obj.put("position", s.getJobtitle());
	    		obj.put("level", s.getJoblevel()); 
	    		obj.put("vacancy", s.getVacancycount()); 
	    		obj.put("deadline", s.getDeadline());   
	    		obj.put("status", s.getStatus());     
	    		obj.put("postedby",jobseekers.findById(s.getUser()).getName());   
	    		jbs.add(obj);
	     }
	     return jbs;
    }
    
    @GetMapping("/viewjob/{id}") 
    public String viewjob(@PathVariable("id") int id,Model model) throws Exception
    	{ 

	     Map < String, Object > obj = new HashMap < > (); 
    	 model.addAttribute("applied",jobapplications.countByPost(id));   
    	 Job job=jobs.findById(id);
    	 model.addAttribute("job", job); 
    	 model.addAttribute("skills", job.getSkills().split(","));
    	 model.addAttribute("employer",jobseekers.findById(job.getUser())); 
    	 Usercompany uc=usercompanies.findByUser(job.getUser());
    	 model.addAttribute("employerdet",uc); 
    	 model.addAttribute("employercatg",compcategories.findById(uc.getCategory()).getName());
    	 
    	 job.setViews(job.getViews()+1);
    	 jobs.save(job);
    	 
    	 
    	  Users user = jobseekerService.findByEmail(
	  		      SecurityContextHolder.getContext().getAuthentication().getName());

			if(user!=null && jobapplications.findByUserAndPost(user.getId(),id)!=null)
				 model.addAttribute("applied",1); 
			else
				 model.addAttribute("applied",0); 
			 
			
			if(user!=null && savedjobs.findByUserAndPost(user.getId(),id)!=null)
				 model.addAttribute("saved",1); 
			else
				 model.addAttribute("saved",0); 
			
    	 List<Object> sjobs=new ArrayList<>();
    	 int tct=0;
    	 for(Job s:jobs.findByStatusAndCategory(1,job.getCategory(),PageRequest.of(0,5)))
    	 {
	    		if(s.getId()==job.getId()) continue;
    		    obj = new HashMap < > (); 
	    	    obj.put("id", s.getId()); 
	    		obj.put("jobtitle", s.getJobtitle()); 
	    		obj.put("deadline",  CommonFuns.Formatdt(CommonFuns.toDate(s.getDeadline().substring(0, 10))));   
	    		obj.put("employerid", s.getUser());   
	    		obj.put("employer",jobseekers.findById(s.getUser()).getName());   
	    		sjobs.add(obj);
	    		tct++;
	    		if(tct==3) break;
    	 }
    	 model.addAttribute("deadline", CommonFuns.Formatdt(CommonFuns.toDate(job.getDeadline().substring(0, 10))));
    	 model.addAttribute("catg",jobcategories.findById(job.getCategory()).getName());
    	 model.addAttribute("similarjobs", sjobs);
  	   return "jobseeker/viewpost";
    	}
    
    @PostMapping("/jobseeker/applyjob/{id}")  
	 @ResponseBody
   public  Map < String, Object > applyjob(@PathVariable("id") int id ,Model model){ 
   	 Map < String, Object > arr = new HashMap < > ();
		 
		arr.put("success",  1); 
		try {
			Jobapplication obj=new Jobapplication();
	      

	    	Users user = jobseekerService.findByEmail(
	  		      SecurityContextHolder.getContext().getAuthentication().getName()); 
	    	
	        if(usereducations.findByUser(user.getId()).size()==0)
	        	throw new Exception("Please Update Your Education details in profile updation page.");

			if(jobapplications.findByUserAndPost(user.getId(),id)==null)
			{
	    	obj.setPost(id);
	    	obj.setUser(user.getId());
	    	obj.setCreatedat(CommonFuns.ctm());
	    	obj.setStatus(0); 
			jobapplications.save(obj);
		     arr.put("html",  "Applied Successfully! ");
			}
			else
				  arr.put("html",  "Your Already Applied! ");
		}
		catch(Exception e)
		{
			arr.put("success",  0);
			arr.put("html",  e.getMessage());
		}
		return arr;
   }
   

    @PostMapping("/jobseeker/savejob/{id}")  
	 @ResponseBody
   public  Map < String, Object > savejob(@PathVariable("id") int id ,Model model){ 
   	 Map < String, Object > arr = new HashMap < > ();
		 
		arr.put("success",  1); 
		try {
			Savedpost obj=new Savedpost();
           
	    	Users user = jobseekerService.findByEmail(
	  		      SecurityContextHolder.getContext().getAuthentication().getName());

	        if(usereducations.findByUser(user.getId()).size()==0)
	        	throw new Exception("Please Update Your Education details in profile updation page.");
	        
			if(savedjobs.findByUserAndPost(user.getId(),id)==null)
			{
	    	obj.setPost(id);
	    	obj.setUser(user.getId());
	    	obj.setCreatedat(CommonFuns.ctm()); 
	    	savedjobs.save(obj);
		     arr.put("html",  "Saved Successfully! ");
			}
			else
				  arr.put("html",  "Your Already Saved! ");
		}
		catch(Exception e)
		{
			arr.put("success",  0);
			arr.put("html",  e.getMessage());
		}
		return arr;
   }
    
    @GetMapping("/chatroom") 
    public String chatroom(Model model){

  	    Users user = jobseekerService.findByEmail(
  	  		      SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("uname", user.getName());
    	model.addAttribute("uid", user.getId());
        return "chatroom";
    } 
    
    @PostMapping("/chatroom/inform")  
	@ResponseBody
    public  Map < String, Object > chatroominform( HttpServletRequest request,Model model){ 
     	 Map < String, Object > arr = new HashMap < > ();
     	 String type=request.getParameter("type");
     	 String uid=request.getParameter("uid");
     	 switch(type)
     	 {
     	 case "JOIN":
    		 this.SetOnlineStaus( CommonFuns.cint(uid),1);
    		 break;
     	 case "LEAVE":
     		this.SetOnlineStaus(  CommonFuns.cint(uid),0);
    		 break; 
     	 }
		 
 		 arr.put("success",  1); 
 		 return arr;
    }
     
	private void SetOnlineStaus(int user,int status) {
	   Chatroom c=chatroom.findByUser(user);
	   if(c==null) c=new Chatroom();
	   c.setUser(user);
	   c.setStatus(status);
	   c.setUpdatedat(CommonFuns.ctm());
	   chatroom.save(c);
	}
	
    @PostMapping("/chatroom/onlinelist")  
	@ResponseBody
    public  List<Object> chatroomonlinelist( HttpServletRequest request,Model model){ 
     	 Map < String, Object > arr = new HashMap < > ();
     	 List<Object> ols=new ArrayList<>();
     	 for(Chatroom c:chatroom.findByStatusOrderByUpdatedatAsc(1))
     	 {
     		arr = new HashMap < > ();
     		arr.put("uname", jobseekers.findById(c.getUser()).getName());
     		arr.put("uid",  c.getUser() );
     		arr.put("time",CommonFuns.formatTime(c.getUpdatedat()) );
     		ols.add(arr);
     	 } 
 		 return ols;
    }
}