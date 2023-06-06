package com.example.onlineshopphicen.config;

import com.example.onlineshopphicen.security.UserDetailsImpl;
import com.example.onlineshopphicen.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class  SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetails;

    @Autowired
    public SecurityConfig(UserDetailsService userDetails) {
        this.userDetails = userDetails;
    }

    //Настраивает аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
