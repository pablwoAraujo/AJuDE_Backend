package backend.ajude.controladores;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Comentario;
import backend.ajude.entidades.ComentarioDTO;
import backend.ajude.entidades.Usuario;
import backend.ajude.servicos.CampanhasService;
import backend.ajude.servicos.ComentariosService;
import backend.ajude.servicos.JWTService;
import backend.ajude.servicos.UsuariosService;

@RestController
public class ComentarioController {
    private CampanhasService campanhasService;
    private UsuariosService servicoUsuarios;
    private ComentariosService comentariosService;
    private JWTService jwtService;

    public ComentarioController(CampanhasService campanhasService, UsuariosService servicoUsuario, ComentariosService comentariosService, JWTService jwtService){
        super();
        this.campanhasService = campanhasService;
        this.servicoUsuarios = servicoUsuario;
        this.comentariosService = comentariosService;
        this.jwtService = jwtService;

    }
    
    @PostMapping("/comentaCampanha")
    public ResponseEntity<List<Comentario>> comentarioCampanha(@RequestBody ComentarioDTO parcial,@RequestHeader("Authorization") String header) {
        String email;
        try {
            email = this.jwtService.getSujeitoDoToken(header);
        } catch (ServletException e) {
            return new ResponseEntity<List<Comentario>>(HttpStatus.UNAUTHORIZED);
        }
        Usuario usuario = this.servicoUsuarios.getUsuario(email).get();
        Campanha campanha = this.campanhasService.getCampanha(parcial.getIdCampanha()).get();
        Comentario comentario = this.comentariosService.transformaComentarioCampanha(parcial, campanha, usuario);
        this.comentariosService.adicionaComentario(comentario);
        return new ResponseEntity<List<Comentario>>(this.campanhasService.adicionaComentario(comentario, campanha), HttpStatus.CREATED);
    }

    @PostMapping("/comentaComentario")
    public ResponseEntity<Comentario> comentarioComentario(@RequestBody ComentarioDTO parcial,@RequestHeader("Authorization") String header) {
        String email;
        try {
            email = this.jwtService.getSujeitoDoToken(header);
        } catch (ServletException e) {
            return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED); 
        }
        Usuario usuario = this.servicoUsuarios.getUsuario(email).get();
        Comentario principal = this.comentariosService.getComentario(parcial.getIdCampanha()).get();
        Comentario novo = this.comentariosService.transformaComentarioCampanha(parcial, principal.getCampanha(), usuario);
        Comentario comentario = this.comentariosService.adicionaComentario(novo);
        return new ResponseEntity<Comentario>(this.comentariosService.comentarioComentario(principal, comentario), HttpStatus.CREATED); 
    }

    @DeleteMapping("/apagarComentario/{id}")
    public ResponseEntity<Comentario> removerComentarioCampanha(@PathVariable Long id,@RequestHeader("Authorization") String header){
        String email;
        try {
            email = this.jwtService.getSujeitoDoToken(header);
        } catch (ServletException e) {
            return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Comentario> comentario = this.comentariosService.getComentario(id);
        String emailDono = comentario.get().getUsuario().getEmail();
        if(email.equals(emailDono)){
            return new ResponseEntity<Comentario>(this.comentariosService.removeComentario(comentario.get()),HttpStatus.OK);
        }
        return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
    }
}