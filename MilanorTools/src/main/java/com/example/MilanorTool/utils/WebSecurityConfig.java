package com.example.MilanorTool.utils;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/css/**", "/assets/**","/fonts/**","/js/**",
							 "/js/**", "/account/**").permitAll() //접근권한 설정
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/account/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	//Authentication 로그인 관련
    //Authoization 권한 관련
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
	      auth.jdbcAuthentication()
		      .dataSource(dataSource)
		      .passwordEncoder(passwordEncoder()) //패스워드 암호화
		      .usersByUsernameQuery("select username, password, enabled "
							        + "from users "
							        + "where username = ? ")
		      
		      .authoritiesByUsernameQuery("select u.username, r.rolename "
								          + "from user_role ur "
								          + "inner join users u on ur.user_id = u.id "
								          + "inner join role r on ur.role_id = r.id "
								          + "where u.username = ? ");
	}
	
	//static을 써서 정적으로 변경
	@Bean
	public static PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}