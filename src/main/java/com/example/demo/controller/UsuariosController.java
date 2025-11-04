package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.in.PhonesRequest;
import com.example.demo.controller.in.UserRequest;
import com.example.demo.exception.UserCustomErrorException;
import com.example.demo.model.Phones;
import com.example.demo.model.Users;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/users")
public class UsuariosController {
	
	@Autowired
	private PhoneRepository phoneRepo;
	
	@Autowired
    private JwtUtil jwtUtil;
	
	@Autowired
    private UserService service;
	
	
	@GetMapping("/error")
    public String generateError() {
        throw new UserCustomErrorException("mensaje de error");
    }
	
	@GetMapping("/generateToken")
    public String generateToken(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }
	
	@Operation(summary = "Registrar un nuevo usuario", description = "Este endpoint permite registrar un nuevo usuario en el sistema y devuelve un token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
	@PostMapping(value = "/create")
	public Users createUser(
			@Parameter(description = "Detalles del usuario para registrar", required = true)@RequestBody UserRequest request) {
		Users createUser = new Users();
		Users user = new Users();
		Phones phone;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");		
		String dateStr = sdf.format(date);
		final String PASSWORD_REGEX = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";
		final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
		final String EMAIL_REGEX = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
		final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
		
		user.setName(request.getName());		
		
		if (EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
			System.out.print("El Email " + request.getEmail() + " es correcto. ");
			user.setEmail(request.getEmail());
		} else {
			System.out.print("El Email " + request.getEmail() + " isn't valid. ");
			throw new UserCustomErrorException("El Email " + request.getEmail() + " no es correcto. ");
		}
		

		if (PASSWORD_PATTERN.matcher(request.getPassword()).matches()) {
			System.out.print("La Password " + request.getPassword() + " es correcta. ");
			user.setPassword(request.getPassword());
		} else {
			System.out.print("La Password " + request.getPassword() + " isn't valid. ");
			throw new UserCustomErrorException("La Password " + request.getPassword() + " no es correcta. ");
		}
		user.setCreated(dateStr);
		user.setActive(true);
		user.setLastLogin(dateStr);
		//user.setToken("1234");
        String token = jwtUtil.generateToken(user.getEmail());
        user.setToken(token);

        createUser = service.createUsers(user);
		for(PhonesRequest phones : request.getPhones()) {
			phone = new Phones();
			phone.setPhone(phones.getPhone());
			phone.setCitycode(phones.getCitycode());
			phone.setContrycode(phones.getContrycode());
			phone.setUsers(user);
			phoneRepo.save(phone);
			
		}

        boolean valid = jwtUtil.validateToken(token, user.getEmail());
        System.out.println("Token válido: " + valid);

        return createUser;
	}

}