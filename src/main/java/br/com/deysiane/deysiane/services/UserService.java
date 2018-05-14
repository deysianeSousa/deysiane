package br.com.deysiane.deysiane.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.deysiane.deysiane.domain.User;
import br.com.deysiane.deysiane.exception.EmailExistException;
import br.com.deysiane.deysiane.exception.IdUserNotFoundException;
import br.com.deysiane.deysiane.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Autowired
	private UserRepository userRepository;
	
	public User insertUser(User user){
		if(!userRepository.isEmailUserExist(user.getEmail())) {
			user.setCreationDate(LocalDateTime.now());
			user.setLastLogin(LocalDateTime.now());
			user.setPassword(pwdEncoder.encode(user.getPassword()));
			user = userRepository.save(user);
			
		}
		else {
			throw new EmailExistException();
		}
		return null;
	}
	
	public User updateUser(User user){
		Optional<User> userById = userRepository.findById(user.getId());
		
		user.setCreationDate(userById.get().getCreationDate());
		user.setLastLogin(userById.get().getLastLogin());
		user.setAlterationDate(LocalDateTime.now());
		
		if(user.getPassword() != null ) {
			user.setPassword(pwdEncoder.encode(user.getPassword()));
		}
		
		if(user.getPhones().isEmpty()) {
			user.setPhones(userById.get().getPhones());
		}else {
			user.setPhones(user.getPhones());
		}
		
		return userRepository.save(user);
	}
	
	public void deleteUser(Long id){
		userRepository.deleteById(id);
	}
	
	public User findUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new IdUserNotFoundException());
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
