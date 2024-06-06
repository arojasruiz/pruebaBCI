package com.example.demo.controller.in;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequest {

	@JsonProperty("name")
	public String name;
	
	@JsonProperty("email")
	public String email;
	
	@JsonProperty("password")
	public String password;
	
	@JsonProperty("phones")
	public List<PhonesRequest> phones;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<PhonesRequest> getPhones() {
		return phones;
	}
	public void setPhones(List<PhonesRequest> phones) {
		this.phones = phones;
	}
	

}