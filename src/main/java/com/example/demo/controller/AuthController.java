package com.example.demo.controller;
//
//import com.example.demo.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
public class AuthController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @PostMapping("/authenticate")
//    public String createAuthenticationToken(@RequestParam String username, @RequestParam String password) throws Exception {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        return jwtUtil.generateToken(userDetails.getUsername());
//    }
}
