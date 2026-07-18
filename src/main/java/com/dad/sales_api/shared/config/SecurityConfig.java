package com.dad.sales_api.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.dad.sales_api.shared.persistence.postgres.entities.UserEntity;
import com.dad.sales_api.shared.persistence.postgres.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig implements UserDetailsService {
  private final UserRepository customerRepository;
  public static final String SECURITY = "BearerAuth";

  @Override
  public UserDetails loadUserByUsername(String email) {
    UserEntity user = customerRepository.findByEmail(email);

    if (user != null) {
      return new CustomUserDetails(
          user.getId(),
          user.getEmail(),
          user.getPassword(),
          user.getRole()
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
                "/api/auth/**",
                "/api/figure",
                "/api/figure/**",
                "/api/category",
                "/api/category/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/v3/api-docs/**"
            ).permitAll()

            .requestMatchers("/api/admin/**")
            .hasRole("ADMIN")

            .requestMatchers("/api/user", "/api/user/**")
            .authenticated()

            .anyRequest()
            .authenticated()
        )
        .addFilterBefore(
            jwtAuthenticationFilter,
            UsernamePasswordAuthenticationFilter.class
        );

    return http.build();
  }
}