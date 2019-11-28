package backend.ajude.servicos;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Comentario;
import backend.ajude.entidades.ComentarioDTO;
import backend.ajude.entidades.Usuario;
import backend.ajude.repositories.ComentariosRepository;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

@Service
public class ComentariosService {

    private ComentariosRepository<Comentario, Long> comentariossDAO;

    public ComentariosService(ComentariosRepository<Comentario, Long> comentariossDAO) {
        super();
        this.comentariossDAO = comentariossDAO;
    }

    public Comentario adicionaComentario(Comentario comentario) {
        return this.comentariossDAO.save(comentario);
    }

    public Comentario comentarioComentario(Comentario comentario1, Comentario comentario2) {
        Optional<Comentario> principal = this.comentariossDAO.findById(comentario1.getId());
        if(principal.isPresent()){
            principal.get().adcionaResposta(comentario2);
            return this.comentariossDAO.save(principal.get());
        }
        return principal.get();
    }

    public Comentario transformaComentarioCampanha(ComentarioDTO parcial, Campanha campanha, Usuario usuario){
        Comentario comentario = new Comentario(parcial.getComentario(), usuario, campanha, true, parcial.getData());
        return comentario;
    }

	public Optional<Comentario> getComentario(long id) {
		return this.comentariossDAO.findById(id);
    }
    
    public Comentario removeComentario(Comentario comentario) throws ServletException {
        Optional<Comentario> comem = this.comentariossDAO.findById(comentario.getId());
        if(comem.isPresent()){
            Comentario theComentario = comem.get();
            theComentario.setStatus(false);
            theComentario.setComentario("");
            return this.comentariossDAO.save(theComentario);
        }
        throw new ServletException("Usuario ja cadastrado");
    }

}