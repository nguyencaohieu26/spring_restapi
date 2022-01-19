package com.api.spring_restapi.Config;

import com.api.spring_restapi.Contrain.RequestURL;
import com.api.spring_restapi.Security.AccountAuthenticationFilter;
import com.api.spring_restapi.Security.AccountAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        AccountAuthenticationFilter accountAuthenticationFilter = new AccountAuthenticationFilter(authenticationManagerBean());
        accountAuthenticationFilter.setFilterProcessesUrl(RequestURL.URL_ACCOUNT_LOGIN);
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeHttpRequests().antMatchers(RequestURL.URL_ACCOUNT_LOGIN,RequestURL.URL_REFRESH_TOKEN).permitAll();
        http.authorizeHttpRequests().antMatchers(HttpMethod.GET,RequestURL.URL_PRODUCTS+"/list").hasAnyAuthority("ROLE_USER");
        http.authorizeHttpRequests().antMatchers(HttpMethod.GET,RequestURL.URL_ACCOUNT).hasAnyAuthority("ROLE_ADMIN");

        http.authorizeHttpRequests().anyRequest().authenticated();
        http.cors().and().csrf().disable().httpBasic();

        http.addFilter(accountAuthenticationFilter);
        http.addFilterBefore(new AccountAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    //stackoverflow cors stuff
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH",
                "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type",
                "x-auth-token"));
        configuration.setExposedHeaders(Collections.singletonList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new
                UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
