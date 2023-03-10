package com.microservice.oauth.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.oauth.entity.UserInfoTest;
import com.microservice.oauth.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<UserInfoTest> getAll() {
		return repository.findAll();
	}

	@Override
	public UserInfoTest createUser(UserInfoTest userInfo) {
		log.info("UserInfo : {}",userInfo);
		userInfo.setUserId(UUID.randomUUID().toString());
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		UserInfoTest save = repository.save(userInfo);
		log.info("UserInfo : {}",save);
		return save;
	}

}
