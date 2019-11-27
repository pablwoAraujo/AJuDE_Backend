package backend.ajude.controladores;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.CreateCampanha;
import backend.ajude.servicos.CampanhasService;
import backend.ajude.servicos.JWTService;
import backend.ajude.servicos.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

@RequestMapping("/api/campanhas")
@RestController
public class CampanhasController {

    private CampanhasService campanhasService;
    @Autowired
    private UsuariosService servicoUsuarios;
    private JWTService jwtService;

    public CampanhasController(CampanhasService campanhasService, UsuariosService servicoUsuario, JWTService jwtService){
        super();
        this.campanhasService = campanhasService;
        this.servicoUsuarios = servicoUsuario;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Campanha> adicionaCampanha(@RequestBody CreateCampanha campanha) throws ServletException {
        Campanha campanhaFinal = this.campanhasService.transformaCampanha(campanha);
        campanhaFinal.setDono(this.servicoUsuarios.getUsuario(campanha.getEmail()).get());
        return new ResponseEntity<Campanha>(this.campanhasService.adicionaCampanha(campanhaFinal), HttpStatus.OK);
    }

    @PutMapping("/encerar/{id}")
    public ResponseEntity<Campanha> encerraCampanha(@PathVariable long id, @RequestHeader("Authorization") String header) throws ServletException {
        Optional<Campanha> campanha = campanhasService.getCampanha(id);
        String email = this.jwtService.getSujeitoDoToken(header);
        if(campanha.isPresent()){            
            if(email.equals(campanha.get().getDono().getEmail())){
                Campanha reposta = this.campanhasService.encerraCampanha(campanha.get());
                return new ResponseEntity<Campanha>(reposta, HttpStatus.OK);
            }else{
                return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{url}")
    public ResponseEntity<Campanha> pesquisaCampanha(@PathVariable String url){
        Optional<Campanha> campanha = campanhasService.pesquisaCampanha(url);
        if(campanha.isPresent()){
            return new ResponseEntity<Campanha>(campanha.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("usuario/{email}")
    public ResponseEntity<List<Campanha>> pesquisaPorUsuario(@PathVariable String email){
        // Usuario usuario = this.servicoUsuarios.getUsuario(email).get();
        List<Campanha> campanha = campanhasService.pesquisaPorUsuario(email);
        if(!campanha.isEmpty()){
            return new ResponseEntity<List<Campanha>>(campanha, HttpStatus.OK);
        }
        return new ResponseEntity<List<Campanha>>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("pesquisar/{nome}")
    public ResponseEntity<List<Campanha>> pesquisaPorNome(@PathVariable String nome){
        List<Campanha> campanha = campanhasService.pesquisaPorNome(nome);
        if(!campanha.isEmpty()){
            return new ResponseEntity<List<Campanha>>(campanha, HttpStatus.OK);
        }
        return new ResponseEntity<List<Campanha>>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("ordena/{atributo}")
    public ResponseEntity<List<Campanha>> ordenaCampanhas(@PathVariable String atributo){
        return new ResponseEntity<List<Campanha>>(this.campanhasService.ordenaCampanhas(atributo), HttpStatus.OK);
    }
}

