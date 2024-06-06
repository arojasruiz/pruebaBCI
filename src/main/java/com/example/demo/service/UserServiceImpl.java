package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repo;
	
//	@Autowired
//    private JwtUtil jwtUtil;

	@Override
	public Users createUsers(Users user) {
		// Guardar el usuario en la base de datos
		Users savedUser = repo.save(user);//.createUsers(user);
		
		// Generar el token JWT usando el nombre de usuario
//        String token = jwtUtil.generateToken(savedUser.getPassword());
        
//        savedUser.setToken(token);
		
		return savedUser;//repo.createUsers(user);
	}

}
