package com.microservice.oauth.service;

import java.util.List;

import com.microservice.oauth.entity.UserInfoTest;

public interface UserInfoService {
	
	List<UserInfoTest> getAll();
	
	UserInfoTest createUser(UserInfoTest userInfo);

}
