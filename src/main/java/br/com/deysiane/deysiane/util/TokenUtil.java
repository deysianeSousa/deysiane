package br.com.deysiane.deysiane.util;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.deysiane.deysiane.domain.User;

public class TokenUtil {
	
	public static String generateToken() {
		return UUID.randomUUID().toString();
	}
	
	public static void tokenExpired(Optional<User> user) {
		int lastLogin = user.get().getLastLogin().getHour(); 
		int expira = (30*60)*1000;
		if(lastLogin > expira){
			new ResponseEntity<String>("Sessão Inválida", HttpStatus.UNAUTHORIZED);
		}
	}
	public static void tokenInvalid(Optional<User> user, String token) {
		if(!user.get().getToken().equals(token) || token == null) {
			new ResponseEntity<String>("Não Autorizado", HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	

}
