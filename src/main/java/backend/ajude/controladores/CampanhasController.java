package backend.ajude.controladores;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.CreateCampanha;
import backend.ajude.servicos.CampanhasService;
import backend.ajude.servicos.JWTService;
import backend.ajude.servicos.UsuariosService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

@RestController
public class CampanhasController {

    private CampanhasService campanhasService;
    private UsuariosService servicoUsuarios;
    private JWTService jwtService;

    public CampanhasController(CampanhasService campanhasService, UsuariosService servicoUsuario, JWTService jwtService){
        super();
        this.campanhasService = campanhasService;
        this.servicoUsuarios = servicoUsuario;
        this.jwtService = jwtService;
    }

    @PostMapping("/api/campanhas")
    public ResponseEntity<Campanha> adicionaCampanha(@RequestBody CreateCampanha campanha) {
        this.campanhasService.verificaValidade();
        Campanha campanhaFinal;
        try {
            campanhaFinal = this.campanhasService.transformaCampanha(campanha);
        } catch (ServletException e1) {
            return new ResponseEntity<Campanha>(HttpStatus.BAD_REQUEST);      
        }
        campanhaFinal.setDono(this.servicoUsuarios.getUsuario(campanha.getEmail()).get());
        try {
            return new ResponseEntity<Campanha>(this.campanhasService.adicionaCampanha(campanhaFinal), HttpStatus.CREATED);
        } catch (ServletException e) {
            return new ResponseEntity<Campanha>(HttpStatus.CONFLICT);      
        }  
    }

    @PutMapping("/api/campanhas/encerar/{id}")
    public ResponseEntity<Campanha> encerraCampanha(@PathVariable long id, @RequestHeader("Authorization") String header) {
        Optional<Campanha> campanha = campanhasService.getCampanha(id);
        String email;
        try {
            email = this.jwtService.getSujeitoDoToken(header);
        } catch (ServletException e) {
            return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
        }
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

    @PutMapping("/api/campanhas/setDescricao/{id}")
    public ResponseEntity<Campanha> setDescricao(@PathVariable long id,@RequestBody String descricao ,@RequestHeader("Authorization") String header) {
        Optional<Campanha> campanha = campanhasService.getCampanha(id);
        String email;
        try {
            email = this.jwtService.getSujeitoDoToken(header);
        } catch (ServletException e) {
            return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
        }
        if(campanha.isPresent()){            
            if(email.equals(campanha.get().getDono().getEmail())){
                Campanha reposta = this.campanhasService.setDescricao(campanha.get(), descricao);
                return new ResponseEntity<Campanha>(reposta, HttpStatus.OK);
            }else{
                return new ResponseEntity<Campanha>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/campanhas/{url}")
    public ResponseEntity<Campanha> pesquisaCampanha(@PathVariable String url){
        this.campanhasService.verificaValidade();
        Optional<Campanha> campanha = campanhasService.pesquisaCampanha(url);
        if(campanha.isPresent()){
            return new ResponseEntity<Campanha>(campanha.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/campanhas/usuario/{email}")
    public ResponseEntity<List<Campanha>> pesquisaPorUsuario(@PathVariable String email){
        this.campanhasService.verificaValidade();
        List<Campanha> campanha = campanhasService.pesquisaPorUsuario(email);
        if(!campanha.isEmpty()){
            return new ResponseEntity<List<Campanha>>(campanha, HttpStatus.OK);
        }
        return new ResponseEntity<List<Campanha>>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/api/campanhas/pesquisar/{nome}")
    public ResponseEntity<List<Campanha>> pesquisaPorNome(@PathVariable String nome){
        this.campanhasService.verificaValidade();
        List<Campanha> campanha = campanhasService.pesquisaPorNome(nome);
        if(!campanha.isEmpty()){
            return new ResponseEntity<List<Campanha>>(campanha, HttpStatus.OK);
        }
        return new ResponseEntity<List<Campanha>>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/ordena/{atributo}")
    public ResponseEntity<List<Campanha>> ordenaCampanhas(@PathVariable String atributo){
        this.campanhasService.verificaValidade();
        return new ResponseEntity<List<Campanha>>(this.campanhasService.ordenaCampanhas(atributo), HttpStatus.OK);
    }
}

