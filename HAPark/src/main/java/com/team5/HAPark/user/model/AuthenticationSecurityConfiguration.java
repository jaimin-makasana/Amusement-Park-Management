package com.team5.HAPark.user.model;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//I referenced these sources to create this class:
//https://www.baeldung.com/spring-security-multiple-auth-providers
//https://docs.spring.io/spring-security/site/docs/3.2.4.RELEASE/reference/htmlsingle/#csrf-logout
@EnableWebSecurity
public class AuthenticationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new Authenticator());
    }

    //Used this source to solve issue of program redirecting to static resources after login by permitting the resources
    //https://stackoverflow.com/questions/62531927/spring-security-redirect-to-static-resources-after-authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/welcome").anonymous().
                antMatchers("/main").permitAll().
                antMatchers("/register").permitAll().
                antMatchers("/rides").permitAll().
                antMatchers("/rides/*").permitAll().
                antMatchers("/test").permitAll().
                antMatchers("/testdb").permitAll().
                antMatchers("/ridetest").permitAll().
                antMatchers("/reservations").permitAll().
                antMatchers("/rides/waittime").permitAll().
                antMatchers(HttpMethod.GET, "/menu/**").permitAll().
                antMatchers(HttpMethod.GET, "/tickets/**").permitAll().
                antMatchers(HttpMethod.GET, "/cartsummary").permitAll().
                antMatchers("/reserve").permitAll().
                antMatchers("/css/**", "/images/**","/pics/**").permitAll().
                antMatchers("/**").authenticated().
                and().formLogin().defaultSuccessUrl("/main", false);
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/");
    }
}


