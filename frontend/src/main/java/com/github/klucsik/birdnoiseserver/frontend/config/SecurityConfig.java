package com.github.klucsik.birdnoiseserver.frontend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    //This is not particalry safe, because we use one password for all the users, and we store it in an envrinment variable which is visible to the whole process.
    //But our main goal here is to prevent accidental clicking around in the application, so this will be suffficient.
    @Value("${USER_PASSWORD:password}") //give the userpassword from an env variable, or if not found, default to password.
    private String rawPassword;

    @Value("${USE_AUTH:false}") //only use auth if specifically asked
    private Boolean useAuth;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if(useAuth) {
            http.
                    authorizeRequests().
                    antMatchers("/**")
                    .hasRole("USER")
                    .and()
                    .formLogin();
        }
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("user")
                .password(passwordEncoder().encode(rawPassword))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
