package com.rampup.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/singup/**").permitAll()
            .antMatchers("/orders/{id}").permitAll()
            .antMatchers("/customers/{id}").permitAll()
            .antMatchers("/users/{id}").permitAll()
            .antMatchers("/productofferings/**").permitAll()
            .antMatchers("/characteristics/**").permitAll()
            .antMatchers("/users/**").hasRole("ADMIN")
            .antMatchers("/roles/**").hasRole("ADMIN")
            .antMatchers("/customers/**").hasRole("ADMIN")
            .antMatchers("/orders/**").hasRole("ADMIN")
            .anyRequest().denyAll();

    }
}
