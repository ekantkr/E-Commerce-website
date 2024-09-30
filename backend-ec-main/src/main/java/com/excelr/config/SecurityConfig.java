package com.excelr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.excelr.security.JwtAutehnticationFilter;
import com.excelr.security.JwtAuthenticationEntryPoint;


@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    @Lazy
    private JwtAutehnticationFilter jwtAuthenticationFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .authorizeHttpRequests(authorize -> authorize
                // User-related endpoints
                .requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/users/email/{email}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/api/users/entry").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/users/{id}").hasRole("ADMIN")

                // Cart-related endpoints
                .requestMatchers(HttpMethod.POST, "/api/users/{id}/cart").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/cart/{userId}/add").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/cart/{userId}/remove/{cartItemId}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/cart/{userId}").hasAnyRole("ADMIN", "USER")

                // Shoe-related endpoints
                .requestMatchers(HttpMethod.POST, "/api/shoes/add").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/shoes/update/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/shoes/getAlltheShoes").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/shoes/getByCategory/{category}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/shoes/mens").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/shoes/womens").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/shoes/kids").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE, "/api/shoes/delete/{id}").hasRole("ADMIN")

                // Order-related endpoints
                .requestMatchers(HttpMethod.POST, "/api/orders/place").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/orders/{orderId}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/orders/user/{userId}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.GET, "/api/orders/all").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/orders/date/{date}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/orders/user/{userId}/{orderId}").hasAnyRole("ADMIN", "USER")

                // Payment-related endpoints
                .requestMatchers(HttpMethod.POST, "/api/order-payments/makePayment/{orderId}/{userId}/{paymentMethod}").hasAnyRole("ADMIN", "USER")

                // Wishlist-related endpoints
                .requestMatchers(HttpMethod.GET, "/api/wishlists/user/{userId}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/api/wishlists/{userId}/add").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE, "/api/wishlists/{wishlistId}").hasAnyRole("ADMIN", "USER")

                // Shipping-related endpoints
                .requestMatchers(HttpMethod.POST, "/api/order-shipping/{orderId}/{shipperId}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT, "/api/order-shipping/{shippingId}").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE, "/api/order-shipping/{shippingId}").hasAnyRole("ADMIN", "USER")

                // Shipper-related endpoints
                .requestMatchers(HttpMethod.POST, "/api/order-shippers/add").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/order-shippers/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/order-shippers/Getallshippers").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/order-shippers/{id}").hasRole("ADMIN")

                // Public endpoints
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/register").permitAll()

                // Any other request must be authenticated
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
