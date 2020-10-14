/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.dataJpa.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.dataJpa.entities.UsersRole;

/**
 *
 * @author riad
 */
public interface UsersRoleRepository extends JpaRepository<UsersRole, Long>{
   
    public List<UsersRole> findByUsername(String username);

}
