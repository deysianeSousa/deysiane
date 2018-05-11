package br.com.deysiane.deysiane.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.deysiane.deysiane.domain.User;
import br.com.deysiane.deysiane.exception.InvalidLoginException;
import br.com.deysiane.deysiane.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginResource {
	
	@Autowired 
	LoginService loginService;

  	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user) {
  		User UserDataBase = loginService.login(user);
  		return new ResponseEntity<User>(UserDataBase, HttpStatus.OK);
    }
  	
  	@ResponseBody
  	@ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<String> handleCredentialsException() {
  		return new ResponseEntity<String>("Usuário e/ou senha inválidos", HttpStatus.UNAUTHORIZED);
    }

}
