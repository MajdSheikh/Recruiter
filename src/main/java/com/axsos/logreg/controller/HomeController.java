package com.axsos.logreg.controller;

import java.util.HashMap;
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
    
    
    
    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("user_id") != null) {
        Long user_id = (Long) session.getAttribute("user_id");
        User thisUser = userServ.findUserById(user_id);
        model.addAttribute("thisUser", thisUser);
        return "home.jsp";
    }
        else {
            return "redirect:/";
        }
    }
    
    @GetMapping("/new/service")
    public String newservice(Model model) {

        Service service =new Service();
        model.addAttribute("service", service);
        
        
        return "createservice.jsp";
   
    }
    
    
    
    @PostMapping("/new/service/add")
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
