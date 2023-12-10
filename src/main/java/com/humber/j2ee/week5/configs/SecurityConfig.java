package com.humber.j2ee.week5.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/search/key/")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/add/**")).hasRole("ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/edit/**")).hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403");

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.logout(new Customizer<LogoutConfigurer<HttpSecurity>>() {
            @Override
            public void customize(LogoutConfigurer<HttpSecurity> httpSecurityLogoutConfigurer) {
                httpSecurityLogoutConfigurer.logoutSuccessUrl("/");
            }
        });
        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("jay")
                .password("password").roles("USER").build();
        UserDetails admin =User.withDefaultPasswordEncoder()
                .username("admin").password("admin").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,admin);
    }

//    @Bean
//    public WebSecurityCustomizer ignoreResources() {
//        return (webSecurity) ->webSecurity.ignoring().requestMatchers("/","/login");
//    }
}
