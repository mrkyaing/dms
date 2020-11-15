package com.prodev.dms.config;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.prodev.dms.services.UserSecurityService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UserSecurityService userSecurityService;
	
    /** The encryption SALT. */
    private static final String SALT = "2&23asfw12%&*235";

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    /** Public URLs. */
    private static final String[] PUBLIC_MATCHERS = {
    		"/",
    		"/theme/**",
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/console/**",
            "/error/**/*",
    };
    
    

    @Override
    protected void configure(HttpSecurity httpsecurity) throws Exception {
        httpsecurity
    		    .cors().and()
    		    .csrf().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll().and()
                .sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).expiredUrl("/")
                .sessionRegistry(sessionRegistry());

        List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
//        if (activeProfiles.contains("dev")) {
            httpsecurity.csrf().disable();
            httpsecurity.headers().frameOptions().disable();
//        }
            httpsecurity.authorizeRequests().antMatchers("/").permitAll().and()
            .authorizeRequests().antMatchers("/console/**").permitAll();

            httpsecurity.csrf().disable();
            httpsecurity.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	 auth
         .userDetailsService(userSecurityService)
         .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    // Register HttpSessionEventPublisher
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}

