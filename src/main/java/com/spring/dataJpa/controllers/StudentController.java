package com.spring.dataJpa.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.spring.dataJpa.dtos.StudentDTO;
import com.spring.dataJpa.services.StudentService;

@Controller
@ManagedBean
@Scope("session") 
public class StudentController implements Serializable{
	private static final long serialVersionUID = 1L;

	private StudentDTO studentDTO;
	private List<StudentDTO> studentList;
	
	@Autowired
	private StudentService studentService;
	
	public void saveOrUpdateStudent() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getPrincipal());
		
		FacesContext context=FacesContext.getCurrentInstance();		
		System.out.println(context.getExternalContext().getSessionMap().get("username"));
		studentService.saveOrUpdateStudent(studentDTO); 
		context.addMessage(null, new FacesMessage("Success"));
		
	}
	
	
	
	
	public void deleteStudent() {
		FacesContext context=FacesContext.getCurrentInstance();		
		studentService.deleteStudent(studentDTO); 
		context.addMessage(null, new FacesMessage("Success"));
		studentDTO=new StudentDTO();
	}
	
	
	public void findAllStudents() {
		try {
			studentList=new ArrayList<>();
			studentList=studentService.findAllStudents();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findByStudentID() {
		try {	
			studentDTO=studentService.findByStudentID(studentDTO.getStudentID());
		}catch(Exception e) {
			e.printStackTrace(); 
		}
	}
	
	
	

	public StudentDTO getStudentDTO() {
		if(studentDTO==null) studentDTO=new StudentDTO();
		return studentDTO;
	}

	public void setStudentDTO(StudentDTO studentDTO) {
		this.studentDTO = studentDTO;
	}


	public List<StudentDTO> getStudentList() {
		findAllStudents();
		return studentList;
	}


	public void setStudentList(List<StudentDTO> studentList) {
		this.studentList = studentList;
	}	
	
	
	
}
