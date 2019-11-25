package backend.ajude.controladores;

import java.util.Optional;

import javax.servlet.ServletException;

//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.ajude.entidades.AuthUsuario;
import backend.ajude.entidades.Usuario;
import backend.ajude.servicos.JWTService;
import backend.ajude.servicos.UsuariosService;

@RestController
@RequestMapping("/auth")
public class LoginController {

	private JWTService jwtService;
	private UsuariosService usuariosService;

	public LoginController(UsuariosService usuariosService, JWTService jwtService) {
		super();
		this.usuariosService = usuariosService;
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody AuthUsuario usuario) throws ServletException {

		// Recupera o usuario
		Optional<Usuario> authUsuario = usuariosService.getUsuario(usuario.getEmail());

		// verificacoes
		if (!authUsuario.isPresent()) {
			throw new ServletException("Usuario nao encontrado!");
		}

		verificaSenha(usuario, authUsuario);

		String token = jwtService.geraToken(authUsuario.get().getEmail());

		return new LoginResponse(token);

	}

	private void verificaSenha(AuthUsuario usuario, Optional<Usuario> authUsuario) throws ServletException {
		if (!authUsuario.get().getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Senha invalida!");
		}
	}

	private class LoginResponse {
		public String token;

		public LoginResponse(String token) {
			this.token = token;

		}
	}
}