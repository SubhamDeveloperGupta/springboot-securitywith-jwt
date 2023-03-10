package com.microservice.oauth.controller;


import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.oauth.entity.AuthRequest;
import com.microservice.oauth.entity.UserInfoTest;
import com.microservice.oauth.service.JwtService;
import com.microservice.oauth.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoService service;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@GetMapping("/get")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<UserInfoTest> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/msg")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public String message() {
		return "Hello";
	}
	
	@PostMapping("/create")
	public UserInfoTest createUser(@Valid @RequestBody UserInfoTest userInfo) {
		log.info("UserInfo : {}",userInfo);
		UserInfoTest createUser = service.createUser(userInfo);
		log.info("UserInfo : {}",createUser);
		return createUser;
	}
	
	@PostMapping("/auth")
	public String authenticationAndGetToken(@RequestBody AuthRequest authRequest) {
		
		Authentication authenticate = authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(
							authRequest.getUsername(), authRequest.getPassword()));
		
		if(authenticate.isAuthenticated()) {
			return jwtService.generatedToken(authRequest.getUsername());			
		}
		throw new UsernameNotFoundException("invalid User Request ! ");
	}
	
	
}














