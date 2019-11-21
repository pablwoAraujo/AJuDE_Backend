package backend.ajude.controladores;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Comentario;
import backend.ajude.entidades.ComentarioDTO;
import backend.ajude.entidades.Usuario;
import backend.ajude.servicos.CampanhasService;
import backend.ajude.servicos.ComentariosService;
import backend.ajude.servicos.UsuariosService;

@RestController
public class ComentarioController {
    private CampanhasService campanhasService;
    private UsuariosService servicoUsuarios;
    private ComentariosService comentariosService;

    public ComentarioController(CampanhasService campanhasService, UsuariosService servicoUsuario, ComentariosService comentariosService){
        super();
        this.campanhasService = campanhasService;
        this.servicoUsuarios = servicoUsuario;
        this.comentariosService = comentariosService;
    }
    
    //faz um comentario na campanha
    @PostMapping("/comentaCampanha")
    public ResponseEntity<Set<Comentario>> comentarioCampanha(@RequestBody ComentarioDTO parcial){
        Usuario usuario = this.servicoUsuarios.getUsuario(parcial.getEmail()).get();
        //return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        Campanha campanha = this.campanhasService.getCampanha(parcial.getIdCampanha()).get();
        //return new ResponseEntity<Campanha>(campanha, HttpStatus.OK);
        Comentario comentario = this.comentariosService.transformaComentarioCampanha(parcial, campanha, usuario);
        this.comentariosService.adicionaComentario(comentario);
        return new ResponseEntity<Set<Comentario>>(this.campanhasService.adicionaComentario(comentario, campanha), HttpStatus.OK);
    }

    @PostMapping("/comentaComentario")
    public ResponseEntity<Comentario> comentarioComentario(@RequestBody ComentarioDTO parcial){
        Usuario usuario = this.servicoUsuarios.getUsuario(parcial.getEmail()).get();
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
}