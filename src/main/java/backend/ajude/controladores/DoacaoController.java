package backend.ajude.controladores;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Doacao;
import backend.ajude.entidades.DoacaoDTO;
import backend.ajude.entidades.Usuario;
import backend.ajude.servicos.CampanhasService;
import backend.ajude.servicos.DoacoesService;
import backend.ajude.servicos.JWTService;
import backend.ajude.servicos.UsuariosService;

@RestController
public class DoacaoController {
    private JWTService jwtService;
    private UsuariosService usuarioService;
    private CampanhasService campanhasService;
    private DoacoesService doacaoService;

    public DoacaoController(DoacoesService doacaoService, JWTService jwtService, UsuariosService usuarioService, CampanhasService campanhasService){
        super();
        this.doacaoService = doacaoService;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
        this.campanhasService = campanhasService;
    }

    @PostMapping("/doacao")
    public ResponseEntity<Campanha> doar(@RequestBody DoacaoDTO doacaoDTO, @RequestHeader("Authorization") String header) throws ServletException {
        String email = this.jwtService.getSujeitoDoToken(header);
        Usuario usuario = usuarioService.getUsuario(email).get();
        Campanha campanha = this.campanhasService.getCampanha(doacaoDTO.getIdCampanha()).get();
        Doacao doacao = this.doacaoService.parseDoacao(doacaoDTO, usuario, campanha);
        this.doacaoService.salvarDoacao(doacao);
        return new ResponseEntity<Campanha>(this.campanhasService.doar(campanha, doacao), HttpStatus.OK);
    }

    @GetMapping("/doacao/campanha/{id}")
    public ResponseEntity<List<Doacao>> doacoesDaCampanha(@PathVariable long id) throws ServletException {
        Optional<Campanha> campanha = this.campanhasService.getCampanha(id);
        if(campanha.isEmpty()){
            return new ResponseEntity<List<Doacao>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Doacao>>(campanha.get().getDoacoes(), HttpStatus.OK);
    }

    @GetMapping("/doacao/usuario/{email}")
    public ResponseEntity<List<Doacao>> doacoesDoUsuario(@PathVariable String email) throws ServletException {
        Optional<Usuario> usuario = this.usuarioService.getUsuario(email);
        if(usuario.isEmpty()){
            return new ResponseEntity<List<Doacao>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Doacao>>(this.campanhasService.doacoesDoUsuario(usuario.get()), HttpStatus.OK);
    }

}