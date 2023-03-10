package com.microservice.oauth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfoTest {

	@Id
	private String userId;
	
	@Email
	@Column(unique = true)
	private String username;
	
	@NotNull(message = "Password Should be value")
	@NotEmpty
	private String password;
	
	@NotNull(message = "Password Should be value")
	@NotEmpty
	private String roles;
	
}








