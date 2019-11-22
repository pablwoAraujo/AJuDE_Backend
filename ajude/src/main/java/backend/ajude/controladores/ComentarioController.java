package backend.ajude.controladores;

import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    //faz um comentario na campanha
    @PostMapping("/comentaCampanha")
    public ResponseEntity<Set<Comentario>> comentarioCampanha(@RequestBody ComentarioDTO parcial,@RequestHeader("Authorization") String header) throws ServletException {
        String email = this.jwtService.getSujeitoDoToken(header);
        Usuario usuario = this.servicoUsuarios.getUsuario(email).get();
        //return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        Campanha campanha = this.campanhasService.getCampanha(parcial.getIdCampanha()).get();
        //return new ResponseEntity<Campanha>(campanha, HttpStatus.OK);
        Comentario comentario = this.comentariosService.transformaComentarioCampanha(parcial, campanha, usuario);
        this.comentariosService.adicionaComentario(comentario);
        return new ResponseEntity<Set<Comentario>>(this.campanhasService.adicionaComentario(comentario, campanha), HttpStatus.OK);
    }

    @PostMapping("/comentaComentario")
    public ResponseEntity<Comentario> comentarioComentario(@RequestBody ComentarioDTO parcial,@RequestHeader("Authorization") String header) throws ServletException {
        String email = this.jwtService.getSujeitoDoToken(header);
        Usuario usuario = this.servicoUsuarios.getUsuario(email).get();
        //return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        Comentario principal = this.comentariosService.getComentario(parcial.getIdCampanha()).get();
        //return new ResponseEntity<Comentario>(principal, HttpStatus.OK);
        Comentario novo = this.comentariosService.transformaComentarioCampanha(parcial, principal.getCampanha(), usuario);
        Comentario comentario = this.comentariosService.adicionaComentario(novo);
        //return new ResponseEntity<Comentario>(novo, HttpStatus.OK);
        return new ResponseEntity<Comentario>(this.comentariosService.comentarioComentario(principal, comentario), HttpStatus.OK); 
        // this.comentariosService.adicionaComentario(comentario);
        // return new ResponseEntity<Set<Comentario>>(this.campanhasService.adicionaComentario(comentario, campanha), HttpStatus.OK);
    }

    @DeleteMapping("/apagarComentario/{id}")
    public ResponseEntity<Comentario> removerComentarioCampanha(@PathVariable Long id,@RequestHeader("Authorization") String header) throws ServletException{
        String email = this.jwtService.getSujeitoDoToken(header);
        Optional<Comentario> comentario = this.comentariosService.getComentario(id);
        String emailDono = comentario.get().getUsuario().getEmail();
        if(email.equals(emailDono)){
            //this.campanhasService.removeComentario(comentario.get().getCampanha(), comentario.get());
            //nao faz nada falta setar o boolean que eu ainda nao fiz
            return new ResponseEntity<Comentario>(this.comentariosService.removeComentario(comentario.get()),HttpStatus.OK);
        }
        return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
    }

    // @DeleteMapping("/apagarComentario/comentario/{id}")
    // public ResponseEntity<Comentario> removerComentarioComentario(@PathVariable Long id,@RequestHeader("Authorization") String header) throws ServletException{
    //     String email = this.jwtService.getSujeitoDoToken(header);
    //     Optional<Comentario> comentario = this.comentariosService.getComentario(id);
    //     String emailDono = comentario.get().getUsuario().getEmail();
    //     if(email.equals(emailDono)){
    //         return new ResponseEntity<Comentario>(this.comentariosService.removeComentario(comentario.get()), HttpStatus.OK);
    //         //this.campanhasService.removeComentario(comentario.get().getCampanha(), comentario.get());
    //         //nao faz nada falta setar o boolean que eu ainda nao fiz
    //         //return new ResponseEntity<Comentario>(this.comentariosService.removeComentario(comentario.get()),HttpStatus.OK);
    //     }
    //     return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
    // }

}