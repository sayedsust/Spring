package com.javatechie.spring.security.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password("{noop}pass1").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user2").password("{noop}pass2").roles("USER");
	}

	// security based on ROLE
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.
				authorizeRequests().antMatchers("/rest/**").hasAnyRole("ADMIN").
				anyRequest().fullyAuthenticated().
				and()
				.httpBasic();
	}
}
