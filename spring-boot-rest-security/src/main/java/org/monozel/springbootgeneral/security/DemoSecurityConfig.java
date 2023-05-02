package org.monozel.springbootgeneral.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // (retrieving users from db) add support for JDBC , no more hardcoded users
    // no custom table
//    @Bean
//    public UserDetailsManager userDetailsManager (DataSource dataSource) {
//                                            // inject DataSource AutoConfigured by spring boot
//            // tell spring security to use JDBC authentication with our data source and spring security
//        // knows table name and columns because we are using predefined schema
//        return new JdbcUserDetailsManager(dataSource);
//
//    }

    // retrieving users from db and using custom tables (not using predefined schema)
    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource) {
                                            // inject DataSource AutoConfigured by spring boot
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username (how to find users)
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );
                                // ? is parameter value will be the user name from login

        // define query to retrieve the authorities/roles by username (how to find roles)
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );
        return jdbcUserDetailsManager;

    }



    /*      HARD Coded users

    @Bean
    public InMemoryUserDetailsManager userDetailsManager (){

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
*/
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
        );

        // use http basic authentication
        http.httpBasic();

        // disable cross site request forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, DELETE and/or PATCH
        http.csrf().disable();

        return http.build();
    }

}
