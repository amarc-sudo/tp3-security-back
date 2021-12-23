package com.uqac.back.configuration;

import com.uqac.back.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class APISecurity extends WebSecurityConfigurerAdapter {

    private final TokenService tokenService;

    private final String[] urlAllDispo = new String[]{
            "/rest/api/user/login",
            "/rest/api/user/checkToken",
            "/rest/api/user/disconnect"
    };

    private final String[] urlResidentielConnected = new String[]{
            "/rest/api/user/residentiel/check",
            "/rest/api/client-residentiel/getAll"
    };

    private final String[] urlAdminConnected = new String[]{
            "/rest/api/user/admin/check",
    };

    private final String[] urlAffaireConnected = new String[]{
            "/rest/api/user/affaire/check",
            "/rest/api/client-affaire/getAll"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .and()
                .addFilterBefore(new FiltreAuthentification(tokenService), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(urlAllDispo)
                .permitAll()
                .antMatchers(urlAdminConnected)
                .hasAuthority("admin")
                .antMatchers(urlResidentielConnected)
                .hasAnyAuthority("residentiel", "admin")
                .antMatchers(urlAffaireConnected)
                .hasAnyAuthority("affaire", "admin")
                .anyRequest().denyAll()
                .and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable().cors().disable();
    }
}
