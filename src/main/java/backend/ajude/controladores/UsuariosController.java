package backend.ajude.controladores;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.ajude.entidades.Usuario;
import backend.ajude.servicos.UsuariosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Controller de Usuarios")
@RestController
public class UsuariosController {

    private UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        super();
        this.usuariosService = usuariosService;
    }

    @ApiOperation(value="Verifica se o Usuario está logado")
    @GetMapping("/api")
    public ResponseEntity<String> verificaLogin(){
        return new ResponseEntity<String>("voce esta logado", HttpStatus.OK);
    }

    @ApiOperation(value="Cadastra um Usuario")
    @PostMapping("/api/usuarios")
    public ResponseEntity<Usuario> adicionaUsuario(@RequestBody Usuario usuario){
        try {
            return new ResponseEntity<Usuario>(this.usuariosService.adicionaUsuario(usuario), HttpStatus.CREATED);
        } catch (ServletException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
	}

    @ApiOperation(value="Recupera um Usuario pelo email")
    @GetMapping("/usuarios/{email}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable String email) {
		Optional<Usuario> usuario = this.usuariosService.getUsuario(email);
		if (usuario.isPresent())
			return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);

		return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
	}
}