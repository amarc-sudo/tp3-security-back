package com.uqac.back.configuration;

import com.uqac.back.beans.Token;
import com.uqac.back.services.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FiltreAuthentification extends GenericFilterBean {

    private TokenService tokenService;

    private List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    public FiltreAuthentification(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String authToken = httpServletRequest.getHeader("AUTHORIZATION");
            authorityList = new ArrayList<>();
            if (authToken != null) {
                authToken = StringUtils.removeStart(authToken, "Bearer").trim();

                Optional<Token> tokenOptional = tokenService.checkTokenUnique(authToken);

                if (tokenOptional.isPresent()) {
                    Token token = tokenOptional.get();
                    if (token.getDateExpirationToken().isAfter(Instant.now())) {
                        authorityList.add(new SimpleGrantedAuthority(token.getUser().getRole().getTagRole()));
                        UsernamePasswordAuthenticationToken tokens = new UsernamePasswordAuthenticationToken("", "", authorityList);
                        SecurityContextHolder.getContext().setAuthentication(tokens);
                    } else{
                        tokenService.deleteToken(token);
                    }
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
