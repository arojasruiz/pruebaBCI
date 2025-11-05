package com.example.demo.service;

import com.example.demo.dto.request.PhoneRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.model.*;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import com.example.demo.exception.UserCustomErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private JwtUtil jwtUtil;

    //@Value("${user.password.regex}")
    //private String passwordRegex;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");
    //Pattern PASSWORD_PATTERN = Pattern.compile(passwordRegex);


    public UserResponse createUser(UserRequest request) {
        log.info("Iniciando creación de usuario: {}", request.getEmail());

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);

        if (!EMAIL_PATTERN.matcher(request.getEmail()).matches()) {
            log.warn("Email inválido: {}", request.getEmail());
            throw new UserCustomErrorException("El Email " + request.getEmail() + " no es correcto.");
        }

        if (!PASSWORD_PATTERN.matcher(request.getPassword()).matches()) {
            log.warn("Contraseña inválida para el usuario: {}", request.getEmail());
            throw new UserCustomErrorException("La Password no cumple el formato requerido.");
        }

        Optional<Users> existMail = userRepository.findByEmail(request.getEmail());
        if (existMail.isPresent()) {
            throw new UserCustomErrorException("El correo ya está registrado.");
        }

        Users user = Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .created(dateStr)
                .modified(dateStr)
                .lastLogin(dateStr)
                .isActive(true)
                .build();

        String token = jwtUtil.generateToken(request.getEmail());
        user.setToken(token);

        Users savedUser = userRepository.save(user);
        log.info("Usuario {} creado correctamente", savedUser.getEmail());

        if (request.getPhones() != null && !request.getPhones().isEmpty()) {
            for (PhoneRequest phoneRequest : request.getPhones()) {
                Phones phone = Phones.builder()
                        .phone(phoneRequest.getNumber())
                        .citycode(phoneRequest.getCitycode())
                        .contrycode(phoneRequest.getContrycode())
                        .users(savedUser)
                        .build();
                phoneRepository.save(phone);
            }
            log.info("Teléfonos asociados guardados correctamente para el usuario {}", savedUser.getEmail());
        }

        log.info("Usuario creado exitosamente: {}", savedUser.getEmail());

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .created(savedUser.getCreated())
                .modified(savedUser.getModified())
                .lastLogin(savedUser.getLastLogin())
                .token(savedUser.getToken())
                .isActive(savedUser.isActive())
                .build();
    }

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }
}
