package br.com.deysiane.deysiane;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.deysiane.deysiane.domain.User;
import br.com.deysiane.deysiane.keys.ErrorKey;
import br.com.deysiane.deysiane.resource.UserResourse;
import br.com.deysiane.deysiane.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeysianeApplicationTests {


	private final Long id_user = 1L;

	User user;

	@Mock
	UserService userService;

	@InjectMocks
	UserResourse userResourse;

	@Before
	public void setUp() {
		user = new User();
	}

	@Test
	public void deveInserirUmUsuario() {
		ResponseEntity<User> userResponse  = userResourse.insertUser(user);
		verify(userService, times(1)).insertUser(user);
		assertNotNull(userResponse);
	}

	@Test
	public void deveRetornarUmusuarioPeloId() {
		when(userService.findUserById(id_user)).thenReturn(user);
		ResponseEntity<User> userResponse = userResourse.findUserById(id_user);
		assertNotNull(userResponse);
	}
	@Test
	public void deveDeletarUmUsuario() {
		ResponseEntity<Void> userResponse = userResourse.delete(id_user);
		assertNotNull(userResponse);
	}
	
	@Test
	public void deveAtualizarUmUsurio() {
		when(userService.findUserById(id_user)).thenReturn(user);
		ResponseEntity<User> userResponse = userResourse.findUserById(id_user);
		assertNotNull(userResponse);
	}


	@Test
	public void deveValidarExceptionEmailExistente() throws JSONException {
		ResponseEntity<ErrorKey> erro = userResourse.handleEmailExistException();
		assertNotNull(erro);
	}
	@Test
	public void deveValidarExceptionUsuarioNaoEncontrado() throws JSONException {
		ResponseEntity<ErrorKey> erro = userResourse.handleUserNotFoundException();
		assertNotNull(erro);
	}

}

