package br.com.deysiane.deysiane.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @RequestMapping("/home")
    public String hello() {
    	
        return "Se voce conseguiu acessar esta rota significa que esta autenticado e ocorreu tudo certo!";
    }
    
}
