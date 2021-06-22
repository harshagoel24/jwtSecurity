package com.wave.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wave.jwtSecurity.JwtAuthenticationEntryPoint;


//@EnableWebSecurity
@Configuration
@Order(1)
public class BasicAuthenticationConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser("user1").password(passwordEncoder().encode("user1Pass"))
          .roles("ADMIN");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        .antMatchers("/service/**").permitAll()
        .antMatchers("/token").authenticated()
         
          .anyRequest().authenticated()
          .and()
          .httpBasic()
          .and()
          .exceptionHandling()
          .authenticationEntryPoint(authenticationEntryPoint)
          .and()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
// 
//        http.addFilterAfter(new CustomFilter(),
//          BasicAuthenticationFilter.class);
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}