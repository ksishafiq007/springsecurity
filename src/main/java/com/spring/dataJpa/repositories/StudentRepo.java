package com.spring.dataJpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.dataJpa.entities.Student;


@Component
public interface StudentRepo extends JpaRepository<Student, Long>{

	


}
