package com.spring.dataJpa.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.spring.dataJpa.dtos.StudentDTO;
import com.spring.dataJpa.entities.Student;
import com.spring.dataJpa.repositories.StudentRepo;


@Service
@Transactional
public class StudentService{
	
@Autowired
private StudentRepo studentRepo;

	public void saveOrUpdateStudent(StudentDTO studentDTO) {
		Student student =new Student();
		BeanUtils.copyProperties(studentDTO, student);
		studentRepo.save(student);
		
	}

	public List<StudentDTO> findAllStudents() throws Exception{
		List<Student> studentList=new ArrayList<>();
		List<StudentDTO> studentDTOList=new ArrayList<>();
		studentList=studentRepo.findAll();
		for(Student student:studentList) {
			StudentDTO studentDTO=new StudentDTO();
			BeanUtils.copyProperties(student, studentDTO);
			studentDTOList.add(studentDTO);
			
		}
		return studentDTOList;
	}

	public StudentDTO findByStudentID(Long studentID) {
	StudentDTO studentDTO=new StudentDTO();
	Student student=new Student();
	student=studentRepo.findOne(studentID);
	BeanUtils.copyProperties(student, studentDTO);
	return studentDTO;	
	}

	public void deleteStudent(StudentDTO studentDTO) {
		studentRepo.delete(studentDTO.getStudentID());		
	}

	
}
