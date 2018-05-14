package br.com.deysiane.deysiane.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.deysiane.deysiane.domain.User;
import br.com.deysiane.deysiane.exception.InvalidLoginException;
import br.com.deysiane.deysiane.services.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
    @Autowired
    UserService userService;
   
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    	User user = userService.findByEmail(authentication.getName());
    	if (user != null) {

    		String password = (String) authentication.getCredentials();
    		if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
    			throw new BadCredentialsException("Senha incorreta!");
    		}

    		return new UsernamePasswordAuthenticationToken(user.getEmail(), password, new ArrayList<>());
    	}
    	
    	else {
    		throw new InvalidLoginException("Usuário e/ou senha inválidos");
    	}
    	
    	
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
