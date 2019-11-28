package backend.ajude.servicos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import backend.ajude.Enum.StatusCampanha;
import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Comentario;
import backend.ajude.entidades.CreateCampanha;
import backend.ajude.entidades.Doacao;
import backend.ajude.entidades.Like;
import backend.ajude.entidades.Usuario;
import backend.ajude.ordenacao.OrdenacaoPorData;
import backend.ajude.ordenacao.OrdenacaoPorLikes;
import backend.ajude.ordenacao.OrdenacaoPorMeta;
import backend.ajude.repositories.CampanhasRepository;
import backend.ajude.repositories.LikeRepository;

import org.springframework.stereotype.Service;

/**
 * Servico de Campanhas
 */
@Service
public class CampanhasService {

    private CampanhasRepository<Campanha, Long> campanhasDAO;
    private LikeRepository<Campanha, Long> likesDAO;

    public CampanhasService(CampanhasRepository<Campanha, Long> campanhasDAO, LikeRepository<Campanha, Long> likesDAO) {
        super();
        this.campanhasDAO = campanhasDAO;
        this.likesDAO = likesDAO;
    }

    /**
     * Adiciona uma Campanha no repositorio de campanhas
     * @param campanha a Campanha a ser adicionada
     * @return Campanha a Campanha salva
     * @throws ServletException se existir uma campanha com mesmo nome ja registrada
     */
    public Campanha adicionaCampanha(Campanha campanha) throws ServletException {
        Optional<Campanha> provisory = campanhasDAO.findByNome(campanha.getNome());
        if (provisory.isPresent()) {
            throw new ServletException("Campanha já Cadastrada");
        }
        return campanhasDAO.save(campanha);
    }

    /**
     * Pesquisa e retorna uma Campanha a partir do id
     * @param id o id da campanha
     * @return Optional<Campanha> a campanha buscada
     */
    public Optional<Campanha> getCampanha(long id) {
        return this.campanhasDAO.findById(id);
    }

    /**
     * Pesquisa e retorna uma lista de Campanhas a partir de uma substring
     * @param nome o nome da substring
     * @return List<Campanha> a lista de campanhas que contem a substring no nome
     */
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

    /**
     * Transforma uma CreateCampanha em uma Campanha 
     * @param campanha a CreateCampanha com as informacoes basicas parar criar uma Campanha
     * @return Campanha a Campanha criada a partir da CreateCampanha
     * @throws ServletException se a Campanha possui uma informacao invalida
     */
    public Campanha transformaCampanha(CreateCampanha campanha) throws ServletException{
        if(campanha.getNome()!=null && campanha.getData()!=null && campanha.getMeta()!=0 && campanha.getUrl()!=null){
            Campanha salvar = new Campanha(campanha.getNome(), campanha.getUrl(), campanha.getDescricao(),
            campanha.getData(), StatusCampanha.ATIVA, campanha.getMeta(), 0, null);
            return salvar;
        }else{
            throw new ServletException("Campanha mau formada!");
        }
    }

    /**
     * Pesquisa e Retorna uma Campanha a patir da url
     * @param url a url da Campanha
     * @return Optional<Campanha> a campanha buscada
     */
    public Optional<Campanha> pesquisaCampanha(String url) {
        return this.campanhasDAO.findByUrl(url);
    }

    /**
     * Adiciona um Comentario a uma Campanha
     * @param comentario o Comentario a ser adicionado na Campanha
     * @param campanha a Campanha onde sera adicionado o Comentario 
     * @return List<Comentario> a lista de Comentarios da Campanha passada como parametro
     */
    public List<Comentario> adicionaComentario(Comentario comentario, Campanha campanha) {
        Optional<Campanha> campanhaRecuperada = this.campanhasDAO.findById(campanha.getId());
        if(campanhaRecuperada.isPresent()){
            campanha.adcionaComentario(comentario);
            return this.campanhasDAO.save(campanha).getHashcomentarios();
        }
        return null;
        
	}

    /**
     * Adiciona/Retira um like de uma Campanha
     * @param campanha a campanha onde sera adicionado/removido o like
     * @param like o Like com informacao do Usuario 
     * @return Campanha a Campanha apos a alteracao
     */
	public Campanha like(Campanha campanha, Like like) {
        List<Like> likes = campanha.getLikes();
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

    /**
     * Pesquisa/Retorna uma lista com as Campanhas que um Usuario criou/duou
     * @param email o email do Usuario 
     * @return List<Campanha> a lista de Campanhas que o Usuario criou/duou
     */
	public List<Campanha> pesquisaPorUsuario(String email) {
        List<Campanha> campanhas = campanhasDAO.findAll();
        List<Campanha> saida = new ArrayList<Campanha>();
        for(Campanha campanha: campanhas){
            if (campanha.getDono().getEmail().toLowerCase().equals(email.toLowerCase())) {
                saida.add(campanha);
            }
        }
        for(Campanha campanha: campanhas){
            for(Doacao doacao: campanha.getDoacoes()){
                if (doacao.getUsuario().getEmail().toLowerCase().equals(email.toLowerCase())) {
                    if(!saida.contains(campanha)){
                        saida.add(campanha);
                    }
                }
            }
        }
        return saida;
	}

    /**
     * Encerra uma Campanha mudando seu Status
     * @param campanha a Campanha a ser alterada
     * @return Campanha a Campanha apos ser alterada
     */
	public Campanha encerraCampanha(Campanha campanha) {
        campanha.setStatus(StatusCampanha.ENCERRADA);
        return this.campanhasDAO.save(campanha);
	}

    /**
     * Adiciona uma Doacao a uma Campanha
     * @param campanha a Campanha onde sera feita a Doacao
     * @param doacao a Doacao a ser adicionada a Campanha
     * @return Campanha a Campanha apos ser alterada
     */
	public Campanha doar(Campanha campanha, Doacao doacao) {
        Campanha auxiliar = this.campanhasDAO.findById(campanha.getId()).get();
        auxiliar.adcionaDoacao(doacao);
        auxiliar.setDoacao(auxiliar.getDoacao()+doacao.getValor());
		return this.campanhasDAO.save(auxiliar);
	}

    /**
     * Ordena a Campanha a partir do numero de likes, pela data ou pela quantidade que falta para
     * a campanha atingir a meta
     * @param atributo o atributo que determinara o tipo da ordenacao 
     * @return List<Campanha> a lista de Campanhas ordenada
     */
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
                    if(atributo.equals("meta")){
                        if(campanha.getMeta() - campanha.getDoacao()>0){
                            listaOrdenada.add(campanha);
                            contador++;
                        }
                    }else{
                        listaOrdenada.add(campanha);
                        contador++;
                    }
                }
            }
        }
        return listaOrdenada;	
    }

    /**
     * Muda a descricao da Campanha 
     * @param campanha a Campanha onde sera alterada a descricao
     * @param descricao a nova descricao da Campanha
     * @return a Campanha apos ser alterada
     */
	public Campanha setDescricao(Campanha campanha, String descricao) {
        campanha.setDescricao(descricao);
		return this.campanhasDAO.save(campanha);
	}

    /**
     * Verifica se as Campanhas no Repositorio ja passaram do tempo estimado e muda seu 
     * Status no repositorio
     */
    public void verificaValidade(){
        Date dataAtual = new Date();
        List<Campanha> lista = this.campanhasDAO.findAll();
        for(Campanha campanha: lista){
            if(dataAtual.compareTo(campanha.getData())> 0){
                if(campanha.getMeta()- campanha.getDoacao()>0){
                    campanha.setStatus(StatusCampanha.VENCIDA);
                }else{
                    campanha.setStatus(StatusCampanha.CONCLUIDA);
                }
                this.campanhasDAO.save(campanha);
            }
        }

    }

    /**
     * Retorna uma Lista com todas as doacoes de um Usuario
     * @param usuario o Usuario que sera buscado as doacoes
     * @return List<Doacao> a lista com todas as doacoes de um Usuario
     */
	public List<Doacao> doacoesDoUsuario(Usuario usuario) {
        List<Campanha> campanhas = this.campanhasDAO.findAll();
        List<Doacao> doacoes = new ArrayList<>();
        for(Campanha campanha: campanhas){
            for(Doacao doacao: campanha.getDoacoes()){
                if(doacao.getUsuario().equals(usuario)){
                    doacoes.add(doacao);
                }
            }
        }
		return doacoes;
	}
}