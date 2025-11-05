package com.example.demo.service;

import com.example.demo.dto.request.PhoneRequest;
import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.exception.UserCustomErrorException;
import com.example.demo.model.Users;
import com.example.demo.repository.PhoneRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequest request;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        PhoneRequest phone = new PhoneRequest();
        phone.setNumber(123456789);
        phone.setCitycode(32);
        phone.setContrycode("56");

        request = new UserRequest();
        request.setName("Andrea");
        request.setEmail("andrea@example.com");
        request.setPassword("Password1");
        request.setPhones(Collections.singletonList(phone));
    }

    @Test
    void testCreateUser_Success() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(jwtUtil.generateToken(anyString())).thenReturn("fake-jwt-token");
        when(userRepository.save(any(Users.class))).thenAnswer(i -> {
            Users u = i.getArgument(0);
            u.setId(1L);
            return u;
        });

        UserResponse response = userService.createUser(request);

        assertNotNull(response);
        assertEquals(request.getEmail(), response.getEmail());
        assertTrue(response.isActive());
        assertNotNull(response.getToken());
        verify(userRepository, times(1)).save(any(Users.class));
    }

    @Test
    void testCreateUser_DuplicateEmail() {
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(new Users()));

        assertThrows(UserCustomErrorException.class, () -> userService.createUser(request));
    }

    @Test
    void testCreateUser_InvalidPassword() {
        request.setPassword("abc");

        assertThrows(UserCustomErrorException.class, () -> userService.createUser(request));
    }

    @Test
    void testCreateUser_InvalidEmail() {
        request.setEmail("andreaexample.com");

        assertThrows(UserCustomErrorException.class, () -> userService.createUser(request));
    }
}
