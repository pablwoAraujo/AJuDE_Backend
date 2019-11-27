package backend.ajude.servicos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;

import backend.ajude.Enum.StatusCampanha;
import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Comentario;
import backend.ajude.entidades.CreateCampanha;
import backend.ajude.entidades.Doacao;
import backend.ajude.entidades.Like;
import backend.ajude.ordenacao.OrdenacaoPorData;
import backend.ajude.ordenacao.OrdenacaoPorLikes;
import backend.ajude.ordenacao.OrdenacaoPorMeta;
import backend.ajude.repositories.CampanhasRepository;
import backend.ajude.repositories.LikeRepository;

import org.springframework.stereotype.Service;

@Service
public class CampanhasService {

    private CampanhasRepository<Campanha, Long> campanhasDAO;
    private LikeRepository<Campanha, Long> likesDAO;

    public CampanhasService(CampanhasRepository<Campanha, Long> campanhasDAO, LikeRepository<Campanha, Long> likesDAO) {
        super();
        this.campanhasDAO = campanhasDAO;
        this.likesDAO = likesDAO;
    }

    public Campanha adicionaCampanha(Campanha campanha) throws ServletException {
        Optional<Campanha> provisory = campanhasDAO.findByNome(campanha.getNome());
        if (provisory.isPresent()) {
            throw new ServletException("Campanha já Cadastrada");
        }
        return campanhasDAO.save(campanha);
    }

    public Optional<Campanha> getCampanha(long id) {
        return this.campanhasDAO.findById(id);
    }

    public List<Campanha> pesquisaPorNome(String nome) {
        List<Campanha> campanhas = campanhasDAO.findAll();
        List<Campanha> saida = new ArrayList<Campanha>();
        for (int i = 0; i < campanhas.size(); i++) {
            if (campanhas.get(i).getNome().toLowerCase().contains(nome.toLowerCase())) {
                saida.add(campanhas.get(i));
            }
        }
        return saida;
    }

    public Campanha transformaCampanha(CreateCampanha campanha) {
        Campanha salvar = new Campanha(campanha.getNome(), campanha.getUrl(), campanha.getDescricao(),
                campanha.getData(), StatusCampanha.ATIVA, campanha.getMeta(), 0, null);
        return salvar;
    }

    public Optional<Campanha> pesquisaCampanha(String url) {
        return this.campanhasDAO.findByUrl(url);
    }

    public Set<Comentario> adicionaComentario(Comentario comentario, Campanha campanha) {
        Optional<Campanha> campanhaRecuperada = this.campanhasDAO.findById(campanha.getId());
        if(campanhaRecuperada.isPresent()){
            campanha.adcionaComentario(comentario);
            return this.campanhasDAO.save(campanha).getHashcomentarios();
        }
        return null;
        
	}

	public Campanha like(Campanha campanha, Like like) {
        Set<Like> likes = campanha.getLikes();
        for (Like c: likes){
            if(c.getUser().equals(like.getUser())){
                likes.remove(c);
                Like provisorio = new Like(c.getId(), c.getUser());
                this.likesDAO.delete(provisorio);
                return this.campanhasDAO.save(campanha);
            }
        }
        this.likesDAO.save(like);
        campanha.adcionaLike(like);
		return this.campanhasDAO.save(campanha);
	}

	public List<Campanha> pesquisaPorUsuario(String email) {
        List<Campanha> campanhas = campanhasDAO.findAll();
        List<Campanha> saida = new ArrayList<Campanha>();
        for (int i = 0; i < campanhas.size(); i++) {
            if (campanhas.get(i).getDono().getEmail().toLowerCase().contains(email.toLowerCase())) {
                saida.add(campanhas.get(i));
            }
        }
        return saida;
	}

	public Campanha encerraCampanha(Campanha campanha) {
        campanha.setStatus(StatusCampanha.ENCERRADA);
        return this.campanhasDAO.save(campanha);
	}

	public Campanha doar(Campanha campanha, Doacao doacao) {
        Campanha auxiliar = this.campanhasDAO.findById(campanha.getId()).get();
        auxiliar.adcionaDoacao(doacao);
        auxiliar.setDoacao(auxiliar.getDoacao()+doacao.getValor());
		return this.campanhasDAO.save(auxiliar);
	}

	public List<Campanha> ordenaCampanhas(String atributo) {
        List<Campanha> lista = this.campanhasDAO.findAll();
        List<Campanha> listaOrdenada = new ArrayList<Campanha>();

        if(atributo.equals("like")){
            Collections.sort(lista, new OrdenacaoPorLikes());
        }else if(atributo.equals("data")){
            Collections.sort(lista, new OrdenacaoPorData());
        }else{
            Collections.sort(lista, new OrdenacaoPorMeta());
        }

        int contador = 0;
        for(Campanha campanha: lista){
            if (contador<5){
                if(campanha.getStatus().equals(StatusCampanha.ATIVA)){
                    listaOrdenada.add(campanha);
                    contador++;
                }
            }
        }
        return listaOrdenada;	
    }

}