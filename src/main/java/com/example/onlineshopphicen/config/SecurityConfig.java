package com.example.onlineshopphicen.config;

import com.example.onlineshopphicen.security.UserDetailsImpl;
import com.example.onlineshopphicen.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class  SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetails;

    @Autowired
    public SecurityConfig(UserDetailsService userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //конфигурируем сам Spring Security
        //конфигурируем авторизацию
        // .antMatchers("/admin").hasRole("ADMIN") - Доступ по ролям
        http.authorizeRequests()
                .antMatchers("/auth/login", "/auth/registration", "/error", "/css/**", "/img/**").permitAll()
                .anyRequest().hasAnyRole("CLIENT", "ADMIN")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login") /*по этому аддресу Spring Security будет принимать логин и пароль*/
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")/* если логин или пароль неправильный - перенапрявляет обратно на страницу login, но с папраметром(param) - error*/
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login");
    }

    //Настраивает аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
