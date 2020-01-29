package com.iServices.iContact.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.iServices.iContact.util.JwtAuthenticationEntryPoint;
import com.iServices.iContact.util.JwtRequestFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private UserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	// configure AuthenticationManager so that it knows from where to load
	// user for matching credentials
	// Use BCryptPasswordEncoder
	auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		/*http.authorizeRequests().antMatchers("/showReg","/","/login","/log","/images/**","/js/**","/css/**","/fonts/**","/vendor/**"
				, "/registerUser","/logout","/login/login/**").permitAll()
		.antMatchers("pendingContacts/").hasAuthority("ADMIN").anyRequest().authenticated().and().csrf().disable();
*/
		http.
		authorizeRequests()
							.antMatchers("/login").permitAll()
							.antMatchers("/login/**").permitAll()
							.antMatchers("/showReg").permitAll()
							.antMatchers("/authenticate").permitAll()
							.antMatchers("/authenticate/**").permitAll()
							.antMatchers("/pendingContacts").hasAuthority("ADMIN")
							.and().csrf().disable().formLogin()
							.loginPage("/log").failureUrl("/login?error=true")
							.defaultSuccessUrl("/displayContactss") 
							.usernameParameter("username")
							.passwordParameter("password")
							.and().logout().logoutUrl("/logout").logoutSuccessUrl("/log")
							.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).deleteCookies("remember-me")
							.and().exceptionHandling()
							.accessDeniedPage("/access-denied")
							.and().rememberMe().and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
							.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		

	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
