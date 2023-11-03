package com.springbootlearn.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootlearn.todo.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
