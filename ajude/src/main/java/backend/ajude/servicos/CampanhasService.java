package backend.ajude.servicos;

import java.util.Optional;

import javax.servlet.ServletException;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.CreateCampanha;
import backend.ajude.repositories.CampanhasRepository;

import org.springframework.stereotype.Service;

@Service
public class CampanhasService {
    
    private CampanhasRepository<Campanha, Long> campanhasDAO;

    public CampanhasService(CampanhasRepository<Campanha, Long> campanhasDAO){
        super();
        this.campanhasDAO = campanhasDAO;
    }

    public Campanha adicionaCampanha(CreateCampanha campanha) throws ServletException {
        Optional<Campanha> provisory = campanhasDAO.findByNome(campanha.getNome());
        if(provisory.isPresent()) {
            throw new ServletException("Campanha já Cadastrada");
        }
        return criaCampanha(campanha);
    }

    public Campanha criaCampanha(CreateCampanha campanha) throws ServletException{
        Campanha salvar = new Campanha(campanha.getNome(),"a",campanha.getDescricao(),"","",campanha.getMeta(),1.2,null,"",1);
        return campanhasDAO.save(salvar);
    }


    public Optional<Campanha> getCampanha(long id) {
		return this.campanhasDAO.findById(id);
	}
}
