package com.excelr.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excelr.model.User;
import com.excelr.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;  // Inject PasswordEncoder

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);  // Add logger

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        // Log user details before returning UserDetails object
        logger.info("Found user with email: {}", user.getEmail());

        return UserDetailsImpl.build(user);
    }
}
