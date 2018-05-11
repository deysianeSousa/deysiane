package br.com.deysiane.deysiane.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.deysiane.deysiane.domain.User;
import br.com.deysiane.deysiane.exception.EmailExistException;
import br.com.deysiane.deysiane.repositories.PhoneRepository;
import br.com.deysiane.deysiane.repositories.UserRepository;
import br.com.deysiane.deysiane.util.TokenUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User insertUser(User user){
		if(!userRepository.isEmailUserExist(user.getEmail())) {
			user.setToken(TokenUtil.generateToken());
			user.setCreationDate(LocalDateTime.now());
			user.setLastLogin(LocalDateTime.now());
			user = userRepository.save(user);
			
		}
		else {
			throw new EmailExistException();
		}
		return user;
	}
	
	public User updateUser(User user){
		Optional<User> user1 = userRepository.findById(user.getId());
		user.setToken(user1.get().getToken());
		user.setCreationDate(user1.get().getCreationDate());
		user.setLastLogin(user1.get().getLastLogin());
		user.setAlterationDate(LocalDateTime.now());
		return userRepository.save(user);
	}
	
	public User findUserById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName(), null));
	}
	
	public User userProfile(Long id, String token) {

		Optional<User> user = userRepository.findById(id);
		
		TokenUtil.tokenInvalid(user, token);

		TokenUtil.tokenExpired(user);

		return user.orElse(null);
	}
	

}
