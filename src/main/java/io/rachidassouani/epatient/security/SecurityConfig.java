package io.rachidassouani.epatient.security;

import io.rachidassouani.epatient.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
             /*
                auth.inMemoryAuthentication()
                .withUser("user1").password(bCryptPasswordEncoder.encode("password")).roles("USER")
                .and()
                .withUser("user2").password(bCryptPasswordEncoder.encode("password")).roles("USER")
                .and()
                .withUser("admin").password(bCryptPasswordEncoder.encode("password")).roles("USER", "ADMIN");
              */

        auth.userDetailsService(userDetailsServiceImpl);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeRequests().antMatchers("/delete/**", "/update/**", "/new/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/patients").hasAuthority("USER");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();

        http.authorizeRequests().anyRequest().authenticated();

        http.exceptionHandling().accessDeniedPage("/403");

    }
}
