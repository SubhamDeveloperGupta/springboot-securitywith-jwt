package com.microservice.oauth.globalexception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> accessDeniedExceptionMethod(AccessDeniedException ex) {
		log.error("Error : {}",ex.getMessage());
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	public ResponseEntity<String> authenticationCredentials(AuthenticationCredentialsNotFoundException ex) {
		String message = "Most be you have to pass the 'Bearer' token If you don't have please Go to /auth";
		log.error("Error : {}",message);
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(message);
	}
	
	@ExceptionHandler(BadCredentialsException.class) 
	public ResponseEntity<String> badCredentiaExceptionMethod(BadCredentialsException ex) {
		String message = "Username and Password Not match";
		log.error("Error : {}",message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(message);
	}
	
	
}










