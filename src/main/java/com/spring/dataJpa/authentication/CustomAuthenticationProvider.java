package com.spring.dataJpa.authentication;




import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.dataJpa.entities.Users;
import com.spring.dataJpa.entities.UsersRole;
import com.spring.dataJpa.repositories.UsersRepository;
import com.spring.dataJpa.repositories.UsersRoleRepository;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    public UsersRepository usersRepository;
    
    @Autowired
    public UsersRoleRepository usersRoleRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getPrincipal().toString();
        
        Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();  
    	String password = passwordEncoder.encodePassword(authentication.getCredentials().toString(), null);       
        Users user=usersRepository.findByUsernameAndPasswordAndEnabled(username, password, true);
        
        if(user!=null){
           List<UsersRole> userRoleList=usersRoleRepository.findByUsername(username);
           List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
           for(UsersRole ur:userRoleList){
             grantedAuthorities.add(new SimpleGrantedAuthority(ur.getRoleName()));
           }
           return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        }else{
            throw new UsernameNotFoundException("Sorry! Username or Password is invalid"); 
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
