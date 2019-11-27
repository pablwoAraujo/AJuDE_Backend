package backend.ajude.controladores;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


}