package br.com.deysiane.deysiane.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.deysiane.deysiane.domain.User;
import br.com.deysiane.deysiane.exception.EmailExistException;
import br.com.deysiane.deysiane.services.UserService;

@RestController
@RequestMapping(value="/user")
public class UserResourse {
	
	@Autowired
	private UserService service;
	
 	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> insertUser(@RequestBody User user){
        service.insertUser(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id){
		user.setId(id);
		user = service.updateUser(user);
		return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
  	public ResponseEntity<User> findUserById(@PathVariable("id") Long id){
    	User user =  service.findUserById(id);
    	return new ResponseEntity<User>(user, HttpStatus.OK);
  	}
	
	@RequestMapping(value ="/perfil/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> userProfile(@PathVariable("id") Long id, @RequestHeader (value="token", required = false) String token){
		User user =  service.userProfile(id, token);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@ExceptionHandler(EmailExistException.class)
    public ResponseEntity<String> handleEmailExistException() {
  		return new ResponseEntity<String>("E-mail Existente", HttpStatus.OK);
    }

}
