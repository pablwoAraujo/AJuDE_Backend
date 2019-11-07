package backend.ajude.controladores;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.CreateCampanha;
import backend.ajude.servicos.CampanhasService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

@RequestMapping("/api/campanhas")
@RestController
public class CampanhasController {

    private CampanhasService campanhasService;

    public CampanhasController(CampanhasService campanhasService){
        super();
        this.campanhasService = campanhasService;
    }

    @PostMapping
    public ResponseEntity<Campanha> adicionaCampanha(@RequestBody CreateCampanha campanha) throws ServletException {
        return new ResponseEntity<Campanha>(this.campanhasService.adicionaCampanha(campanha), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campanha> visualizaCampanha(@PathVariable Long id){
        Optional<Campanha> campanha = campanhasService.getCampanha(id);
        if(campanha.isPresent()){
            return new ResponseEntity<Campanha>(campanha.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("pesquisar/{nome}")
    public ResponseEntity<List<Campanha>> pesquisaPorNome(@PathVariable String nome){
        List<Campanha> campanha = campanhasService.pesquisaPorNome(nome);
        if(!campanha.isEmpty()){
            return new ResponseEntity<List<Campanha>>(campanha, HttpStatus.OK);
        }
        return new ResponseEntity<List<Campanha>>(HttpStatus.NOT_FOUND);

    }



}

