package backend.ajude.servicos;

import java.util.Optional;

import javax.servlet.ServletException;

import backend.ajude.entidades.Campanha;
import org.springframework.stereotype.Service;

import backend.ajude.entidades.Campanha;
import backend.ajude.repositories.UsuariosRepository;

public class CampanhasService {
    private UsuariosRepository<Campanha, Long> campanhasDAO;

    public CampanhasService(UsuariosRepository<Campanha, Long> campanhasDAO){
        super();
        this.campanhasDAO = campanhasDAO;
    }

    public Campanha adicionaCampanha(Campanha campanha) throws ServletException {
        Optional<Campanha> provisory = campanhasDAO.findById(campanha.getId());
        if(provisory.isPresent()) {
            throw new ServletException("Campanha já Cadastrada");
        }
        return campanhasDAO.save(campanha);
    }

    public


}
