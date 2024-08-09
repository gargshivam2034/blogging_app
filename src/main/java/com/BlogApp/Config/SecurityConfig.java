package com.BlogApp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import static org.springframework.security.config.Customizer.withDefaults;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.modelmapper.internal.bytebuddy.asm.Advice.Return;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.BlogApp.Security.CustomUserDetailsService;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebMvc
public class SecurityConfig {
	
	@Autowired
	private  CustomUserDetailsService customUserDetailsService;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		 httpSecurity.csrf((e)->e.disable()).
         authorizeHttpRequests((authz) -> authz
             .requestMatchers("/").permitAll().anyRequest().authenticated()
         )
         .httpBasic(withDefaults());
		 httpSecurity.authenticationProvider(daoAuthenticationProvider());
     return httpSecurity.build();
     
	}
	
	@Bean 
	public DaoAuthenticationProvider daoAuthenticationProvider()
	{
	DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.customUserDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
