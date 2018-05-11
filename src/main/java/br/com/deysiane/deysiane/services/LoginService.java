package br.com.deysiane.deysiane.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.deysiane.deysiane.domain.User;
import br.com.deysiane.deysiane.exception.InvalidLoginException;
import br.com.deysiane.deysiane.repositories.UserRepository;

@Service
public class LoginService {
	
	@Autowired 
	UserRepository userRepository;
	
	public User login(User user){
		User userDataBase = userRepository.findByEmailAddress(user.getEmail());
		if(userDataBase != null) {
			return userDataBase;
		}
		else {
			throw new InvalidLoginException();
		}
		
	}
}
