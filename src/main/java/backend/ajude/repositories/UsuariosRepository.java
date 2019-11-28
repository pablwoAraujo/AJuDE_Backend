package backend.ajude.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.ajude.entidades.Usuario;

/**
 * Repositorio de Usuarios
 */
@Repository
public interface UsuariosRepository<T, ID extends Serializable> extends JpaRepository<Usuario, String> {

	/**
	 * Faz uma busca no repositorio a partir do email do Usuario
	 * @param email o email do Usuario
	 * @return um Optional com a informacao do Usuario
	 */
	Optional<Usuario> findByEmail(String email);
}