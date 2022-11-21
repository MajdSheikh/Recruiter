package com.axsos.logreg.AppService;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.axsos.logreg.models.Company;
import com.axsos.logreg.models.LoginUser;
import com.axsos.logreg.models.User;
import com.axsos.logreg.repository.CompanyRepo;
import com.axsos.logreg.repository.ServiceRepo;
import com.axsos.logreg.repository.UserRepository;


@Service
public class AppService {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private ServiceRepo serviceRepo;
    
    @Autowired
    private CompanyRepo companyRepo;
    
    public User register(User newUser, BindingResult result) {
        if(userRepo.findByEmail(newUser.getEmail()).isPresent()) {
            result.rejectValue("email", "Unique", "This email is already in use!");
        }
        if(!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepo.save(newUser);
        }
    }
    
    public User login(LoginUser newLogin, BindingResult result) {
        if(result.hasErrors()) {
            return null;
        }
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "Unique", "Invalid Credentials");
            return null;
        }
        User user = potentialUser.get();
        if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Credentials");
        }
        if(result.hasErrors()) {
            return null;
        } else {
            return user;
        }
        
        }
    
    public User findUserById(Long id) {
    	Optional<User> u = userRepo.findById(id);
    	
    	if(u.isPresent()) {
    		return u.get();
    	} else {
    		return null;
    	}
    	
    }
    
    
    
    
//    public Service findServiceById(Long id) {
//    	Optional<Service> s = serviceRepo.findById(id);
//    	
//    	if(s.isPresent()) {
//    		return s.get();
//    	} else {
//    		return null;
//    	}
//    	
//    }
    
    
    public Company findCompanyById(Long id) {
    	Optional<Company> c = companyRepo.findById(id);
    	
    	if(c.isPresent()) {
    		return c.get();
    	} else {
    		return null;
    	}
    	
    }
    
    
    
    
//    public Service createServ(Service team) {
//    	return serviceRepo.save(team);
//    }
    
    public Company createcom(Company com) {
    	return companyRepo.save(com);
    }
    
    
    public User createUser(User user) {
    	return userRepo.save(user);
    }
    
    
    
    public com.axsos.logreg.models.Service createService(com.axsos.logreg.models.Service service) {
    	return serviceRepo.save(service);
    }
    
    public List<com.axsos.logreg.models.Service> allService() {
    	return serviceRepo.findAll();
    }
}
