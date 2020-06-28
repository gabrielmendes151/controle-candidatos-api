package com.code.controlecandidatosapi.secutiry;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Profile("!test")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static String [] URI_PUBLICAS = {
        "/api/candidatos/**",
    };

    @SneakyThrows
    @Override
    protected void configure(HttpSecurity httpSecurity) {
        httpSecurity.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.DELETE, URI_PUBLICAS).permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated()
            .and()

            .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)

            .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

    @SneakyThrows
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
    }
}
