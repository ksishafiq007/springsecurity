package com.spring.dataJpa.authentication;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
@Scope("session") 
public class ApplicationUtilsMB {
	public Collection<SimpleGrantedAuthority> authorities;
	
	public boolean userRoleAsAdmin;
	public boolean userRoleAsUser;
	public boolean userRoleAsGuest;

	

	@PostConstruct
	public void init() {
		authorities=(Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();	
	}


	@SuppressWarnings("unlikely-arg-type")
	public boolean isUserRoleAsAdmin() {
		for(SimpleGrantedAuthority auth:authorities) {
			if(auth.getAuthority().equals("ROLE_ADMIN")) {
				userRoleAsAdmin=true;
				break;
			}
		}
			
		return userRoleAsAdmin;
	}


	public void setUserRoleAsAdmin(boolean userRoleAsAdmin) {		
		this.userRoleAsAdmin = userRoleAsAdmin;
	}


	public boolean isUserRoleAsUser() {
		for(SimpleGrantedAuthority auth:authorities) {
			if(auth.getAuthority().equals("ROLE_USER")) {
				userRoleAsUser=true;
				break;
			}
		}
	
		return userRoleAsUser;
	}


	public void setUserRoleAsUser(boolean userRoleAsUser) {
		this.userRoleAsUser = userRoleAsUser;
	}


	public boolean isUserRoleAsGuest() {
		if(authorities.contains("ROLE_GUEST"))userRoleAsGuest=true;
		return userRoleAsGuest;
	}


	public void setUserRoleAsGuest(boolean userRoleAsGuest) {
		this.userRoleAsGuest = userRoleAsGuest;
	}
	

}
