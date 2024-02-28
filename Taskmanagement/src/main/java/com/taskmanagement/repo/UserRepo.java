package com.taskmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagement.model.CreateUser;

@Repository
public interface UserRepo extends JpaRepository<CreateUser, String> {
	CreateUser findByUsername(String username);


}
