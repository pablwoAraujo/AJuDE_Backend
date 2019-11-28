package backend.ajude.servicos;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import backend.ajude.entidades.Usuario;
import backend.ajude.filtros.TokenFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * Servico de Autenticacao
 */
@Service
public class JWTService {
	private UsuariosService usuariosService;
	private final String TOKEN_KEY = "login";

	public JWTService(UsuariosService usuariosService) {
		super();
		this.usuariosService = usuariosService;
	}

	/**
	 * Verifica se o Usuario existe a partir do header da requisicao
	 * @param authorizationHeader o header da requisicao
	 * @return boolean, True se existir e False se nao existir
	 */
	public boolean usuarioExiste(String authorizationHeader) throws ServletException {
		String subject = getSujeitoDoToken(authorizationHeader);

		return usuariosService.getUsuario(subject).isPresent();
	}

	/**
	 * Verifica se o Usuario tem permissao de acessar a rota
	 * @param authorizationHeader o header da requisicao
	 * @param email o email do usuario
	 * @return boolean, True se tiver permissao, False se nao tiver permissao
	 */
	public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
		String subject = getSujeitoDoToken(authorizationHeader);

		Optional<Usuario> optUsuario = usuariosService.getUsuario(subject);
		return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
	}

	/**
	 * Recupera o email do Usuario a partir do header da requisicao
	 * @param authorizationHeader o header da requisicao
	 * @return String o email do Usuario
	 */
	public String getSujeitoDoToken(String authorizationHeader) throws ServletException {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		// Extraindo apenas o token do cabecalho.
		String token = authorizationHeader.substring(TokenFilter.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey("login").parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new ServletException("Token invalido ou expirado!");
		}
		return subject;
	}

	/**
	 * Gera um token com validade de 20 min
	 * @param email o email do Usuario
	 * @return String o token valido
	 */
	public String geraToken(String email) {
		return Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000)).compact();// 20 min
	}

}