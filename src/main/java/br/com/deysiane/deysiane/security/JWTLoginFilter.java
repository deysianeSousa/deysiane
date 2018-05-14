package br.com.deysiane.deysiane.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.deysiane.deysiane.domain.User;
import br.com.deysiane.deysiane.security.service.TokenAuthenticationService;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
	
    protected JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        User credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getEmail(),
                        credentials.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication auth) throws IOException, ServletException {

        TokenAuthenticationService.addAuthentication(response, auth.getName());
        
    }

    @Override
    protected void unsuccessfulAuthentication(  HttpServletRequest request,
                                                HttpServletResponse response,
                                                AuthenticationException exception) throws IOException, ServletException {
        this.onAuthenticationFailure(response, exception);
    }

    
		 
    public void onAuthenticationFailure(HttpServletResponse response, AuthenticationException exception)
    		throws IOException, ServletException {
    	response.setStatus(401);
    	response.setContentType("application/json"); 
    	response.getWriter().append(json());
    }

    private String json() {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ", "
            + "\"status\": 401, "
            + "\"error\": \"Não autorizado\", "
            + "\"message\": \"Usuário e/ou senha inválidos\", "
            + "\"path\": \"/login\"}";
    }
}
