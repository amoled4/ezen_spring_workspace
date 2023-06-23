package com.myweb.www.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.myweb.www.security.CustomAuthMemberService;
import com.myweb.www.security.LoginFailureHandler;
import com.myweb.www.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
		
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
		
		@Bean
		public AuthenticationSuccessHandler authSuccessHandler() {
			return new LoginSuccessHandler();
		}
		
		@Bean
		public AuthenticationFailureHandler authFailureHandler() {
			return new LoginFailureHandler();
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
			http.authorizeRequests() //어쏘라이즈리퀘스트 (승인요청)
			.antMatchers("/member/list").hasRole("ADMIN")
			.antMatchers("/", "/board/list", "/board/detail", "/resources/**", "/member/register", "/member/login").permitAll()
			.anyRequest().authenticated();
			
			// 커스텀 로그인 페이지를 구성해서 security 적용을 하기 위한 설정
			// Controller에 주소요청 매핑도 같이 해주어야 함
			http.formLogin()
			.usernameParameter("email")
			.passwordParameter("pwd")
			.loginPage("/member/login")
			.successHandler(authSuccessHandler())
			.failureHandler(authFailureHandler());
			
			// post 방식으로 로그아웃을 진행해야함!
			http.logout().logoutUrl("/member/logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/");
		}
		
		@Bean
		public UserDetailsService customUserService() {
			return new CustomAuthMemberService();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
		}
}
