package com.highwaytoheaven.estudentsbookapi.infrastructure.security;

import static com.highwaytoheaven.estudentsbookapi.infrastructure.security.SecurityConfig.Authority.STUDENT;

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
        .antMatchers(HttpMethod.GET, "/users/new").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
        .antMatchers(HttpMethod.GET, "/users/professors/**").hasAuthority("PROFESSOR")
        .antMatchers(HttpMethod.GET, "/users/students/groups").hasAuthority("PROFESSOR")
        .antMatchers(HttpMethod.GET, "/users/students/subject-cards/semesters/{semester-number}")
        .hasAuthority("STUDENT")
        .antMatchers(HttpMethod.GET, "/users/students/subject-cards/subjects/{subject-uuid}")
        .hasAuthority("STUDENT")
//                    .antMatchers(HttpMethod.POST, "/users/students/{student-uuid}/subject-cards/{subject-card-uuid}/grades/")
//                    .hasAuthority("PROFESSOR")
//                    .antMatchers(HttpMethod.PATCH,"/users/students/{student-uuid}/subject-cards/{subject-card-uuid}/grades/{grade-uuid}")
//                    .hasAuthority("PROFESSOR")
        .antMatchers("/users/students/{student-uuid}/subject-cards/{subject-card-uuid}/**")
        .hasAuthority("PROFESSOR")
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
