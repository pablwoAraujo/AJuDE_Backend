package backend.ajude.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import backend.ajude.Enum.StatusCampanha;
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

    public Campanha adicionaCampanha(Campanha campanha) throws ServletException {
        Optional<Campanha> provisory = campanhasDAO.findByNome(campanha.getNome());
        if(provisory.isPresent()) {
            throw new ServletException("Campanha já Cadastrada");
        }
        return campanhasDAO.save(campanha);
    }

    //public Campanha adcionaComentario(Campanha campanha, String comentario, String email) {

    //}

    public Optional<Campanha> getCampanha(long id) {
		return this.campanhasDAO.findById(id);
    }
    
    public List<Campanha> pesquisaPorNome(String nome){
        List<Campanha> campanhas = campanhasDAO.findAll();
        List<Campanha> saida = new ArrayList<Campanha>();
        for (int i=0; i < campanhas.size(); i++){
            if(campanhas.get(i).getNome().toLowerCase().contains(nome.toLowerCase())){
                saida.add(campanhas.get(i));
            }
        }
        return saida;
    }

    public Campanha transformaCampanha(CreateCampanha campanha) {
        Campanha salvar = new Campanha(campanha.getNome(),campanha.getUrl(),campanha.getDescricao(),campanha.getData(),StatusCampanha.ATIVA,campanha.getMeta(),0,null,0);
		return salvar;
	}

	public Optional<Campanha> pesquisaCampanha(String url) {
		return this.campanhasDAO.findByUrl(url);
	}
}
