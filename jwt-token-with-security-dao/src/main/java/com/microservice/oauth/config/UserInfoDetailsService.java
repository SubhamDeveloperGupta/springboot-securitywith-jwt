package com.microservice.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.microservice.oauth.entity.UserInfoTest;
import com.microservice.oauth.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserInfoDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfoTest user = repository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		
		log.info("UserLoginDetails : {}",user);
		
		return new UserInfoUserDetails(user);
	}

}
