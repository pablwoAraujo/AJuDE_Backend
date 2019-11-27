package backend.ajude.servicos;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import backend.ajude.entidades.Usuario;
import backend.ajude.repositories.UsuariosRepository;

@Service
public class UsuariosService {

    private UsuariosRepository<Usuario, String> usuariosDAO;
    private EnvioEmail envioEmail;

    public UsuariosService(UsuariosRepository<Usuario, String> usuariosDAO, EnvioEmail envioEmail){
        super();
        this.usuariosDAO = usuariosDAO;
        this.envioEmail = envioEmail;
    }

	public Usuario adicionaUsuario(Usuario usuario) throws ServletException {
        Optional<Usuario> provisory = usuariosDAO.findByEmail(usuario.getEmail());
	    if(provisory.isPresent()) {
            throw new ServletException("Usuario ja cadastrado");
        }
        this.envioEmail.sendNotification(usuario);
        return usuariosDAO.save(usuario);
	}
    
    public Optional<Usuario> getUsuario(String email) {
		return this.usuariosDAO.findByEmail(email);
	}
}