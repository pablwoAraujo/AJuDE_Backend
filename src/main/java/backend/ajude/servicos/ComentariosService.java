package backend.ajude.servicos;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Comentario;
import backend.ajude.entidades.ComentarioDTO;
import backend.ajude.entidades.Usuario;
import backend.ajude.repositories.ComentariosRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Servico de Comentarios
 */
@Service
public class ComentariosService {

    private ComentariosRepository<Comentario, Long> comentariossDAO;

    public ComentariosService(ComentariosRepository<Comentario, Long> comentariossDAO) {
        super();
        this.comentariossDAO = comentariossDAO;
    }

    /**
     * Adiciona um Cometario no repositorio de Comentarios
     * @param comentario o Comentario a ser adicionado
     * @return Comentario o Comentario adicionado
     */
    public Comentario adicionaComentario(Comentario comentario) {
        return this.comentariossDAO.save(comentario);
    }

    /**
     * Adiciona um Comentario a um Comentario 
     * @param comentario1 o Comentario a ser adicionado o Comentartio 
     * @param comentario2 o novo Comentario adicionado ao Comentario "Pai"
     * @return Comentario o Comentario Pai onde foi adicionado o novo Comentario
     */
    public Comentario comentarioComentario(Comentario comentario1, Comentario comentario2) {
        Optional<Comentario> principal = this.comentariossDAO.findById(comentario1.getId());
        if(principal.isPresent()){
            principal.get().adcionaResposta(comentario2);
            return this.comentariossDAO.save(principal.get());
        }
        return principal.get();
    }

    /**
     * Transforma um ComentarioDTO em um Comentario valido 
     * @param parcial o ComentarioDTO que contem as informacoes para criar um Comentario
     * @param campanha a Campanha Onde o Comentario vai ser inserido 
     * @param usuario o Usuario que fez o Comentario
     * @return Comentario o novo Comentario criado a partir do ComentarioDTO
     */
    public Comentario transformaComentarioCampanha(ComentarioDTO parcial, Campanha campanha, Usuario usuario){
        Comentario comentario = new Comentario(parcial.getComentario(), usuario, campanha, true, parcial.getData());
        return comentario;
    }

    /**
     * Pesquisa e Retorna um Comentario a partir de seu id
     * @param id o id do Comentario
     * @return Comentario o Comentario capturado a partir do id
     */
	public Optional<Comentario> getComentario(long id) {
		return this.comentariossDAO.findById(id);
    }
    
    /**
     * "Remove" um Comentario, mudando seu texto para vazio e seu Status para false
     * @param comentario o Comantario a ser "Removido"
     * @return o Comentario apos ser alterada
     */
    public Comentario removeComentario(Comentario comentario) {
        Optional<Comentario> comem = this.comentariossDAO.findById(comentario.getId());
        Comentario theComentario = comem.get();
        theComentario.setStatus(false);
        theComentario.setComentario("");
        return this.comentariossDAO.save(theComentario);
    }

}