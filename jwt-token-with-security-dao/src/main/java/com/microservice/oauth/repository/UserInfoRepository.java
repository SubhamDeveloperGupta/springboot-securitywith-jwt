package com.microservice.oauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.oauth.entity.UserInfoTest;

public interface UserInfoRepository extends JpaRepository<UserInfoTest, String>{

	Optional<UserInfoTest> findByUsername(String username);
	
}
