package com.excelr.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.model.JwtRequest;
import com.excelr.model.JwtResponse;
import com.excelr.model.User;
import com.excelr.service.UserService;
import com.excelr.security.JwtHelper;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        logger.info("Attempting to authenticate user: {}", request.getUsername());
        this.doAuthenticate(request.getUsername(), request.getPassword());

        // Load user details after authentication
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);

        // Fetch user ID using the UserService
        User user = userService.findUserByEmail(request.getUsername()); // Assuming email is used as username
        Long userId = user.getUserId().longValue(); // Cast to Long if necessary

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .userId(userId)
                .username(userDetails.getUsername())
                .build();
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private void doAuthenticate(String email, String password) {

        logger.info("Authenticating with email: {} and password: {}", email, password);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for user: {}", email);
            throw new BadCredentialsException("Invalid Username or Password !!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        logger.info("Registering new user with email: {}", user.getEmail());

        try {
            userService.registerNewUser(user); // This will hash the password and save the user
            return new ResponseEntity<>("User registered successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage());
            return new ResponseEntity<>("User registration failed.", HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> exceptionHandler(BadCredentialsException e, jakarta.servlet.http.HttpServletRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.error("Invalid credentials for user: {}", username);
        return new ResponseEntity<>("Invalid login credentials.", HttpStatus.UNAUTHORIZED);
    }
}
