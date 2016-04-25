package com.yitao.core.config;
/**
 * 
 */

import com.yitao.core.jersey.Rest403ForbiddenEntryPoint;
import com.yitao.core.jersey.RestAccessDeniedHandler;
import com.yitao.core.jersey.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Inject;

/**
 * @author iDay
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Inject
	private UserDetailsService userDetailsService;

	@Inject
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Configuration
	@Order(1)
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**").authorizeRequests().anyRequest().permitAll()
                    .and()
                        .httpBasic()
                        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                    .and()
                        .exceptionHandling()
                        .authenticationEntryPoint(new Rest403ForbiddenEntryPoint())
                        .accessDeniedHandler(new RestAccessDeniedHandler())
                    .and()
                        .logout()
                        .logoutUrl("/api/signout")
                    .and()
                        .csrf().disable()
                        .headers().disable();
        }
    }

	@Configuration
	@Order(2)
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.regexMatchers("^/admin/((?!login).)*$")
					.hasAuthority("ROLE_ADMIN").anyRequest().permitAll()
			.and()
				.formLogin()
					.loginProcessingUrl("/admin/login.do")
					.loginPage("/admin/login.html")
					.defaultSuccessUrl("/admin/index.html")
					.usernameParameter("username")
					.passwordParameter("password")
			.and()
				.logout()
					.logoutSuccessUrl("/admin/index.html")
					.logoutUrl("/admin/logout.do")
					.invalidateHttpSession(true)
			.and()
				.rememberMe()
			.and()
				.csrf().disable()
				.headers().disable();
		}
	}
}
