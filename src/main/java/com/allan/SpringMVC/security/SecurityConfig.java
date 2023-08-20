package com.allan.SpringMVC.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserServiceSecurity userServiceSecurity;

    public SecurityConfig(UserServiceSecurity userServiceSecurity) {
        this.userServiceSecurity = userServiceSecurity;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceSecurity).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/resources/**");

        http.authorizeRequests().antMatchers("/signup").permitAll();
        http.authorizeRequests().anyRequest().authenticated().and().formLogin()
                .loginPage("/login").loginProcessingUrl("/authentication").defaultSuccessUrl("/task/list",true)
                .permitAll().and().authorizeRequests();

        http.csrf().disable();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
