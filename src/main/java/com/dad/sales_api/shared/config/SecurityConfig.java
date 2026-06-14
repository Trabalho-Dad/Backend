package com.dad.sales_api.shared.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.dad.sales_api.shared.entities.AdminEntity;
import com.dad.sales_api.shared.entities.CustomerEntity;
import com.dad.sales_api.shared.repositories.AdminRepository;
import com.dad.sales_api.shared.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig implements UserDetailsService {
  private final CustomerRepository customerRepository;
  private final AdminRepository adminRepository;

  @Override
  public UserDetails loadUserByUsername(String email) {
    AdminEntity admin = adminRepository.findByEmail(email);

    if (admin != null) {
      return new User(
        admin.getEmail(),
        admin.getPassword(),
        List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
      );
    }

    CustomerEntity customer = customerRepository.findByEmail(email);

    if (customer != null) {
      return new User(
        customer.getEmail(),
        customer.getPassword(),
        List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
      );
    }

    throw new UsernameNotFoundException("Usuário e/ou senha inválidos");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter(
    JwtUtils jwtUtils,
    UserDetailsService userDetailsService
  ) {
    return new JwtAuthenticationFilter(jwtUtils, userDetailsService);
  }

  @Bean
  public SecurityFilterChain filterChain(
    HttpSecurity http,
    JwtAuthenticationFilter jwtAuthenticationFilter
  ) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(
          "/auth/register",
          "/auth/login"
        ).permitAll()
          .anyRequest().authenticated()
      )
      .addFilterBefore(
        jwtAuthenticationFilter,
        UsernamePasswordAuthenticationFilter.class
      );

    return http.build();
  }
}