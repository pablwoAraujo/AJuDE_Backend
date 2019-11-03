package backend.ajude.controladores;

import backend.ajude.entidades.Campanha;
import backend.ajude.servicos.CampanhasService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;

@RestController
public class CampanhasController {

    private CampanhasService campanhasService;

    public CampanhasController(CampanhasService campanhasService){
        super();
        this.campanhasService = campanhasService;
    }

    @PostMapping("/api/campanhas")
    public ResponseEntity<Campanha> adicionaCampanha(@RequestBody Campanha campanha) throws ServletException {
        return new ResponseEntity<Campanha>(this.campanhasService.adicionaCampanha(campanha), HttpStatus.OK);
    }
}

