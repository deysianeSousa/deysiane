package br.com.deysiane.deysiane.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.deysiane.deysiane.domain.User;
import br.com.deysiane.deysiane.exception.EmailExistException;
import br.com.deysiane.deysiane.exception.IdUserNotFoundException;
import br.com.deysiane.deysiane.keys.ErrorKey;
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
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
  	public ResponseEntity<User> findUserById(@PathVariable("id") Long id){
    	User user =  service.findUserById(id);
    	return new ResponseEntity<User>(user, HttpStatus.OK);
  	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteUser(id);
		return new ResponseEntity<Void> (HttpStatus.OK);
	}
	
	
	@ExceptionHandler(EmailExistException.class)
    public ResponseEntity<ErrorKey> handleEmailExistException() {
		ErrorKey errorKey = new ErrorKey();
		errorKey.setMensagem("E-mail Existente");
  		return new ResponseEntity<ErrorKey>(errorKey, HttpStatus.OK);
    }
	
	@ExceptionHandler(IdUserNotFoundException.class)
	public ResponseEntity<ErrorKey> handleUserNotFoundException() {
		ErrorKey errorKey = new ErrorKey();
		errorKey.setMensagem("Usuário não encontrado");
		return new ResponseEntity<ErrorKey>(errorKey, HttpStatus.OK);
	}
	
}
