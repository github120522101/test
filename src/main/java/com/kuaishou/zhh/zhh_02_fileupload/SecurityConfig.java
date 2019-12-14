package com.kuaishou.zhh.zhh_02_fileupload;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("what the fuck...........");
		http.csrf().disable().authorizeRequests().antMatchers("/test?*").authenticated().anyRequest().permitAll();
	}
	
}
