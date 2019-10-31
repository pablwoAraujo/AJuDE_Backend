package backend.ajude.controladores;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.ajude.entidades.Usuario;
import backend.ajude.servicos.UsuariosService;

@RestController
public class UsuariosController {

    private UsuariosService usuariosService;


    public UsuariosController(UsuariosService usuariosService) {
        super();
        this.usuariosService = usuariosService;
    }

    @PostMapping("/api/usuarios")
    public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario usuario) throws ServletException {
		return new ResponseEntity<Usuario>(this.usuariosService.adicionaUsuario(usuario), HttpStatus.OK);
	}


}