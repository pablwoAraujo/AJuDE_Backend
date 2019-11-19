package backend.ajude.controladores;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario usuario){
        try {
            return new ResponseEntity<Usuario>(this.usuariosService.adicionaUsuario(usuario), HttpStatus.OK);
        } catch (ServletException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
	}

    //sO PRA TRESTE
    @GetMapping("/usuarios/{email}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable String email) {
		Optional<Usuario> usuario = this.usuariosService.getUsuario(email);
		if (usuario.isPresent())
			return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);

		return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
	}

}