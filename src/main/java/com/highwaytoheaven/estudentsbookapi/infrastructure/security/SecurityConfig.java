package com.highwaytoheaven.estudentsbookapi.infrastructure.security;

import com.highwaytoheaven.estudentsbookapi.infrastructure.security.jwt.JWTAuthenticationFilter;
import com.highwaytoheaven.estudentsbookapi.infrastructure.security.jwt.JWTAuthorizationFilter;
import com.highwaytoheaven.estudentsbookapi.infrastructure.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.highwaytoheaven.estudentsbookapi.infrastructure.security.SecurityConfig.Authority.*;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final AuthenticationService authService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  enum Authority {
    STUDENT("STUDENT"),
    ADMIN("ADMIN"),
    PROFESSOR("PROFESSOR");

    private String name;

    Authority(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.cors()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/users/auth").permitAll()
        .antMatchers(HttpMethod.GET, "/users/students").hasAuthority(STUDENT.toString())
        .antMatchers(HttpMethod.GET, "/users/students/all").hasAuthority(ADMIN.toString())
        .antMatchers(HttpMethod.GET, "/users/professors/all").hasAuthority(ADMIN.toString())
        .antMatchers(HttpMethod.GET, "/subjects/all").hasAuthority(ADMIN.toString())
        .antMatchers(HttpMethod.PATCH, "/subjects/{subject-uuid}").hasAuthority(ADMIN.toString())
        .antMatchers(HttpMethod.POST, "/subjects").hasAuthority(ADMIN.toString())
        .antMatchers(HttpMethod.POST, "/subject-cards").hasAuthority(ADMIN.toString())
        .antMatchers(HttpMethod.POST, "/users").hasAuthority(ADMIN.toString())
        .antMatchers(HttpMethod.GET, "/users/professors/**").hasAuthority(PROFESSOR.toString())
        .antMatchers(HttpMethod.GET, "/users/students/groups").hasAuthority(PROFESSOR.toString())
        .antMatchers(HttpMethod.GET, "/users/students/subject-cards/semesters/{semester-number}")
        .hasAuthority(STUDENT.toString())
        .antMatchers(HttpMethod.GET, "/users/students/subject-cards/subjects/{subject-uuid}")
        .hasAuthority(STUDENT.toString())
        .antMatchers("/users/students/{student-uuid}/subject-cards/{subject-card-uuid}/**")
        .hasAuthority(PROFESSOR.toString())
        .anyRequest().authenticated()
        .and()
        .addFilter(new JWTAuthenticationFilter(authenticationManager(), authService))
        .addFilter(new JWTAuthorizationFilter(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }


  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder());
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();

    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
