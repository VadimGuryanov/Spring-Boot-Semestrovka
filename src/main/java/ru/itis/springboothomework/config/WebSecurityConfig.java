package ru.itis.springboothomework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.springboothomework.controllers.ExceptionController;
import ru.itis.springboothomework.security.AuthIpUserProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("ru.itis.springboothomework.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/signup").anonymous()
                .antMatchers("/signin").anonymous()
                .antMatchers("/cities").permitAll()
                .antMatchers("/profile").authenticated()
                .antMatchers("/profile/{id}").permitAll()
                .antMatchers("/profile/edit").authenticated()
                .antMatchers("/summary").authenticated()
                .antMatchers("/summary/{id}").permitAll()
                .antMatchers("/summary/new").authenticated()
                .antMatchers("/summary/edit/{id}").authenticated()
                .antMatchers("/search").permitAll()
                .antMatchers("/").authenticated();

        http.formLogin()
                .loginPage("/signin")
                .usernameParameter("login")
                .defaultSuccessUrl("/search")
                .failureUrl("/signin?error")
                .permitAll();


        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
