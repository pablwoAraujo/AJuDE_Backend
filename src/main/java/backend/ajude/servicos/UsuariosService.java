package backend.ajude.servicos;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import backend.ajude.entidades.Usuario;
import backend.ajude.repositories.UsuariosRepository;

/**
 * Servico de Usuarios
 */
@Service
public class UsuariosService {

    private UsuariosRepository<Usuario, String> usuariosDAO;
    private EnvioEmail envioEmail;

    public UsuariosService(UsuariosRepository<Usuario, String> usuariosDAO, EnvioEmail envioEmail){
        super();
        this.usuariosDAO = usuariosDAO;
        this.envioEmail = envioEmail;
    }

    /**
     * Registrar um Usuario no sistema
     * @param usuario o Usuario a ser cadastrado
     * @return Usuario o Usuario apos o cadastro
     * @throws ServletException se ja existir Usuario antes cadastrado com mesmo email
     */
	public Usuario adicionaUsuario(Usuario usuario) throws ServletException {
        Optional<Usuario> provisory = usuariosDAO.findByEmail(usuario.getEmail());
	    if(provisory.isPresent()) {
            throw new ServletException("Usuario ja cadastrado");
        }
        this.envioEmail.sendNotification(usuario);
        return usuariosDAO.save(usuario);
	}
    
    /**
     * Recupera um Usuario do repositorio
     * @param email o email do Usuario
     * @return Optional<Usuario> um Optional com as informacoes do Usuario
     */
    public Optional<Usuario> getUsuario(String email) {
		return this.usuariosDAO.findByEmail(email);
	}
}