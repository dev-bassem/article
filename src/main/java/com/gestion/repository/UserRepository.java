package com.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.entities.User;



public interface UserRepository  extends JpaRepository<User, Long> {

}
