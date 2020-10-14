/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.dataJpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dataJpa.entities.Users;

/**
 *
 * @author riad
 */
public interface UsersRepository extends JpaRepository<Users, String>{
    
  public  Users findByUsernameAndPasswordAndEnabled(String username,String password,boolean enabled);
  public  Users findByUsername(String username);
}
 