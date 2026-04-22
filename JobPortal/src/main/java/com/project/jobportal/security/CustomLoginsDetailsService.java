package com.project.jobportal.security;
 
import com.project.jobportal.entity.Users;
import com.project.jobportal.repository.UsersRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet; 

@Service
public class CustomLoginsDetailsService implements UserDetailsService {

    private UsersRepository userRepository;
    
    public CustomLoginsDetailsService(UsersRepository userRepository ) {
        this.userRepository = userRepository; 
    }
    
   

    @Override
    public UserDetails loadUserByUsername(String un) throws UsernameNotFoundException {
    	 
    	 
    	Users user = userRepository.findByEmail(un);
        
        if (user != null && user.getStatus()==1) {
        	 
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getType()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    	 
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(String role ) {
    	HashSet<GrantedAuthority> obj= new HashSet<GrantedAuthority>(); 
    	obj.add(new SimpleGrantedAuthority(role));
    	return obj;
    }
     
}

