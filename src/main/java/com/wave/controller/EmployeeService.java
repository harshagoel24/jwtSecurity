package com.wave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wave.jwtSecurity.JwtGenerator;
import com.wave.modelJwt.JwtUser;


@CrossOrigin(allowedHeaders = "*")
@RestController
public class EmployeeService {

	@Autowired
	private JwtGenerator jwtGenerator;

	@CrossOrigin
	@RequestMapping(value = "/token", method = RequestMethod.POST)
    public String generate() {
		JwtUser jwtUser = new JwtUser();
		jwtUser.setUserName("Harsha Goel");
		jwtUser.setId(1);
		jwtUser.setRole("developer");
        return jwtGenerator.generate(jwtUser);

    }
	
	
	
	@CrossOrigin
	@RequestMapping(value = "/service/details", method = RequestMethod.POST)
    public String details() {
        return "success";

    }



}
