package backend.ajude.servicos;

import org.springframework.stereotype.Service;

import backend.ajude.entidades.Like;
import backend.ajude.entidades.Usuario;
import backend.ajude.repositories.LikeRepository;

/**
 * Servico de Likes
 */
@Service
public class LikeService {
    private LikeRepository<Like, Long> likesDAO;

    public LikeService(LikeRepository<Like, Long> likesDAO){
        super();
        this.likesDAO = likesDAO;
    }

    /**
     * Salva o Like no Repositorio de likes
     * @param Usuario o Usuario que deu o Like
     * @return Like o Like registrado
     */
    public Like salvaLike(Usuario Usuario){
        Like like = new Like(Usuario);
        return this.likesDAO.save(like);
    }

}