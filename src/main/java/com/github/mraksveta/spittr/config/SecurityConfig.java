package com.github.mraksveta.spittr.config;

import com.github.mraksveta.spittr.service.impl.SpitterUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SpitterUserDetailsService spitterUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider authProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        daoAuthProvider.setUserDetailsService(spitterUserDetailsService);
        daoAuthProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/spitters/login", "/spitters/register",
                        "/resources/**", "images/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/spitters/login")
                .loginProcessingUrl("/spitters/login");

        http.httpBasic();

        http.rememberMe()
                .tokenValiditySeconds(2419200) // two weeks
                .key("spittrKey");

        http.logout()
                .logoutUrl("/spitters/logout")
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider(passwordEncoder));
    }
}
