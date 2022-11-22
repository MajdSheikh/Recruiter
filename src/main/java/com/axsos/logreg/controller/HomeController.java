package com.axsos.logreg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.axsos.logreg.AppService.AppService;
import com.axsos.logreg.models.Company;
import com.axsos.logreg.models.LoginUser;
import com.axsos.logreg.models.Service;
import com.axsos.logreg.models.User;

//    Hello Ali
//Second Test

//Is every thing ok
@Controller
public class HomeController {
    
    @Autowired
    private AppService userServ;
    
    @Autowired
	private JavaMailSender javaMailSender;
	
    
    @GetMapping("/")
    public String index(Model model) {
         return "base.jsp";
    }
    @GetMapping("/loginpage")
    public String login(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        
        return "/login/login.jsp";
    }
    @GetMapping("/Regpage")
    public String register(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        
        return "/login/register.jsp";
    }
    	
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "login/register.jsp";
        }
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "/login/login.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) { 
            session.invalidate();
            return "redirect:/";

    }
    
   
    @GetMapping("/jobs/dashboard")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("user_id") != null) {
        Long user_id = (Long) session.getAttribute("user_id");
        User thisUser = userServ.findUserById(user_id);
        model.addAttribute("user", thisUser);
        model.addAttribute("companies", thisUser.getContractorcompanies());

        
        List<Service> service =userServ.allService();
        model.addAttribute("home",1);
        model.addAttribute("allServices", service);   
        return "/login/jobsDashboard.jsp";
    }
        else {
            return "redirect:/";
        }
    }
    
    @GetMapping("/projects/new")
    public String newservice(Model model) {

        Service service =new Service();
        model.addAttribute("service", service);
        
        
        return "/login/createJob.jsp";
   
    }
    
    
    
    @PostMapping("/jobs/create")
    public String addservice(@Valid @ModelAttribute("service") Service service, 
            BindingResult result, Model model, HttpSession session) {
        if(result.hasErrors()) {
            model.addAttribute("service", new Service());
            return "createservice.jsp";
        }
        Long user_id = (Long) session.getAttribute("user_id");
        User thisUser = userServ.findUserById(user_id);
        service.setOwner(thisUser);
        userServ.createService(service);
        return "redirect:/home";
    }
    
    
    
    @GetMapping("/show/service")
    public String showservice(Model model) {
    
        model.addAttribute("services", userServ.allService());
        return "showservice.jsp";
    }
    
    @GetMapping("/services/{id}/edit")
    public String editservice(@PathVariable("id") Long id,Model model,@ModelAttribute("service") Service service) {
        Service serv = userServ.findService(id);
        model.addAttribute("service", serv);
        return "/login/editJob.jsp";
    }
    @PostMapping("/jobs/{id}/edit")
    public String editjob(@PathVariable("id") Long id,Model model,@Valid @ModelAttribute("service") Service service) {
       
    	userServ.editService(service, id);
        return "redirect:/home";
    }
    
    @PostMapping("/services/{id}/apply")
    public String applyforservice( @RequestParam("company")Long id2,@PathVariable("id") Long id, Model model, HttpSession session) {
    	
    	   if (session.getAttribute("user_id") != null) {
    	        Long user_id = (Long) session.getAttribute("user_id");
    	        User thisUser = userServ.findUserById(user_id);
    	        
    	Company comp = userServ.findCompanyById(id2);
        
        User ser = userServ.joinService(userServ.findService(id),comp).getOwner();

		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("aazzoqa@gmail.com");
		sm.setTo(ser.getEmail());
		sm.setSubject("Welcome to Java SpringBoot Application");
		sm.setText("Hi \n\n Mr. : "+thisUser.getFirstName() +"\n\n   has Applied your Job  .");
		javaMailSender.send(sm);
		 System.out.println(generateResponse("Email Sent to the mail "+thisUser.getEmail(), HttpStatus.OK, thisUser)); 
        
    	
    	   }
    	   
    	      return "redirect:/jobs/dashboard";

  }
    @GetMapping("/services/{id}/unjoin")
    public String unjoinservice( @PathVariable("id") Long id, Model model, HttpSession session) {
    	
    	   if (session.getAttribute("user_id") != null) {
    	        Long user_id = (Long) session.getAttribute("user_id");
    	        User thisUser = userServ.findUserById(user_id);
    	        
    	
        User ser =userServ.unjoinService(userServ.findService(id),thisUser).getOwner();
        SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("aazzoqa@gmail.com");
		sm.setTo(ser.getEmail());
		sm.setSubject("Welcome to Java SpringBoot Application");
		sm.setText("Hi \n\n Mr. : "+thisUser.getFirstName() +"\n\n   has dismiss your Job  .");
		javaMailSender.send(sm);
		 System.out.println(generateResponse("Email Sent to the mail "+thisUser.getEmail(), HttpStatus.OK, thisUser)); 
       
    	
    	   }
    	   
 	      return "redirect:/jobs/dashboard";

  }
    
    
    
    /// Adding Employee
    
    @GetMapping("/team")
    public String teams(Model model, HttpSession session) {
    	
        model.addAttribute("companies", userServ.allcompany());

    	  if (session.getAttribute("user_id") != null) {
  	        Long user_id = (Long) session.getAttribute("user_id");
  	        User thisUser = userServ.findUserById(user_id);
  	        
        model.addAttribute("companies", userServ.allcompany());
        model.addAttribute("user", thisUser);

        
    	  }
        return "/login/viewCompany.jsp";
    }
    @PostMapping("/company/add")
    public String addEployees(Model model, HttpSession session,@RequestParam("company")Long id ) {
    	

    	  if (session.getAttribute("user_id") != null) {
    		  
  	        Long user_id = (Long) session.getAttribute("user_id");
  	        User thisUser = userServ.findUserById(user_id);
  	        
  	        Company com = userServ.findCompanyById(id);
  	        userServ.addEmployeetocompany(com, thisUser);
  	        
        
    	  }
        return "redirect:/team";
    }
    
    @GetMapping("/getout/{id}")
    public String getoutofcompany(@PathVariable("id") Long id,Model model, HttpSession session) {
    	
        model.addAttribute("companies", userServ.allcompany());

    	  if (session.getAttribute("user_id") != null) {
  	        Long user_id = (Long) session.getAttribute("user_id");
  	        User thisUser = userServ.findUserById(user_id);
  	      Company com = userServ.findCompanyById(id);
	        userServ.getoutEmployeetocompany(com, thisUser);
        
    	  }
          return "redirect:/team";
    }
    
    
    
    //home
    
    @GetMapping("/home")
    public String ownerdashboard(Model model, HttpSession session) {
    	 if (session.getAttribute("user_id") != null) {
    	        Long user_id = (Long) session.getAttribute("user_id");
    	        User thisUser = userServ.findUserById(user_id);
    	        model.addAttribute("thisUser", thisUser);
    	        model.addAttribute("home",1);

    	        model.addAttribute("user",thisUser);
    	        
    	        model.addAttribute("ownerServices", thisUser.getServices());
    	        return "/login/ownerDashboard.jsp";
    	    }
    	        else {
    	            return "redirect:/";
    	        }
    	
    	 

    }
    
    @GetMapping("/user/card/{id}")
 public String carduser(Model model,@PathVariable("id") Long id, HttpSession session) {
    	
        User serUser = userServ.findUserById(id);
     
        
        model.addAttribute("user", serUser);
        return "/login/showcard.jsp";
    }
    
    @GetMapping("/owner/dash/{id}")
    public String owner(Model model,@PathVariable("id") Long id, HttpSession session) {
    	
        User serUser = userServ.findUserById(id);
        Long user_id = (Long) session.getAttribute("user_id");
        User thisUser = userServ.findUserById(user_id);
        
        model.addAttribute("user",thisUser);
        
        model.addAttribute("ownerServices", serUser.getServices());
        return "/login/ownerDashboard.jsp";
    }
    
   
    @GetMapping("/create/company")
    public String newcompany(Model model, @ModelAttribute("company") Company company
           , HttpSession session) {
    	
    	model.addAttribute("companies", userServ.allcompany());
    
        return "/login/createcompany.jsp";

    }
    
    @PostMapping("/company")
    public String createcompany(Model model,@Valid @ModelAttribute("company") Company company
           , BindingResult result, HttpSession session) {
    	
    	model.addAttribute("companies", userServ.allcompany());

    	if (session.getAttribute("user_id") != null) {
    		
    		if(result.hasErrors()) {
    	        return "/login/createcompany.jsp";

    		}else {
	        Long user_id = (Long) session.getAttribute("user_id");
	        User thisUser = userServ.findUserById(user_id);
	        company.setContractor(thisUser);
	        userServ.createcom(company);
    		}
        
    	}


        return "redirect:/create/company";

    }
    
    
    @GetMapping("/delete/{id}")
    public String deletecompany(@PathVariable("id") Long id) {
    	
    	userServ.deletecompany(userServ.findCompanyById(id));
    	
        return "redirect:/create/company";

    }
    @GetMapping("/service/delete/{id}")
    public String finishService(@PathVariable("id") Long id) {
    	
    	Service ser =userServ.findService(id);
    	userServ.deleteService(ser);
        return "redirect:/rating/"+id;

    }
    @GetMapping("/rating/{id}")
    public String rating(@PathVariable("id") Long id,Model model) {
    	
    	model.addAttribute("service", userServ.findService(id));
        return "/login/contractorRating.jsp";

    }
    @PostMapping("/job/rating/{id}")
    public String rate(@RequestParam("rate")Integer rate ,@PathVariable("id") Long id,Model model) {
    	 Service ser = userServ.findService(id);
    	 ser.setOwnerRating(rate);
    	 userServ.rateService(ser);
         return "redirect:/home";

    }
    
    
    
    
    
    
    
    @GetMapping("/company/team/add")
    public String addingtoTeam(Model model) {
    	
        return "/login/addteam.jsp";

    }
    @GetMapping("/company/team/{id}")
    public String addingintoTeam(@RequestParam("user")Long id2,@PathVariable("id") Long id,Model model) {
    	
//    	userServ.addToteam();
        return "/login/addteam.jsp";

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @PostMapping("/send/Email/{id_owner}")
     public String sendEmail(@PathVariable("id_owner") Long idowner,@RequestParam("contracter")Long id2){
        User thisUser = userServ.findUserById(idowner);
        User contUser = userServ.findUserById(id2);

		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("aazzoqa@gmail.com");
		sm.setTo(contUser.getEmail());
		sm.setSubject("Welcome to Java SpringBoot Application");
		sm.setText("Welcome Mr. : "+thisUser.getFirstName() +"\n\n   .");
		javaMailSender.send(sm);
		generateResponse("Email Sent to the mail "+thisUser.getEmail(), HttpStatus.OK, thisUser);
		return "redirect:/";
	}
	
	public ResponseEntity<Object> generateResponse(String msg, HttpStatus st , Object response){
		Map<String, Object> mp = new HashMap<String, Object>();
		
		mp.put("message", msg);
		mp.put("status", st.value());
		mp.put("data",response);
		
		return  new ResponseEntity<Object>(mp,st);
	}

    
}
