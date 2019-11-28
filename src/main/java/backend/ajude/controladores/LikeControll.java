package backend.ajude.controladores;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Like;
import backend.ajude.entidades.Usuario;
import backend.ajude.servicos.CampanhasService;
import backend.ajude.servicos.JWTService;
import backend.ajude.servicos.LikeService;
import backend.ajude.servicos.UsuariosService;

@RestController
public class LikeControll {

    private LikeService likeService;
    private JWTService jwtService;
    private UsuariosService usuarioService;
    private CampanhasService campanhasService;

    public LikeControll(LikeService likeService, JWTService jwtService, UsuariosService usuarioService, CampanhasService campanhasService){
        super();
        this.likeService = likeService;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
        this.campanhasService = campanhasService;
    }

    @GetMapping("/like/{id}")
    public ResponseEntity<Campanha> darLike(@PathVariable Long id, @RequestHeader("Authorization") String header) {
        String email;
        try {
            email = this.jwtService.getSujeitoDoToken(header);
        } catch (ServletException e) {
            return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);

        }
        Usuario usuario = usuarioService.getUsuario(email).get();
        Like like = new Like(usuario);
        Campanha campanha = this.campanhasService.getCampanha(id).get();
        return new ResponseEntity<Campanha>(this.campanhasService.like(campanha, like), HttpStatus.OK);
    }
}