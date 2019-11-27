package backend.ajude.servicos;

import org.springframework.stereotype.Service;

import backend.ajude.entidades.Like;
import backend.ajude.entidades.Usuario;
import backend.ajude.repositories.LikeRepository;

@Service
public class LikeService {
    private LikeRepository<Like, Long> likesDAO;

    public LikeService(LikeRepository<Like, Long> likesDAO){
        super();
        this.likesDAO = likesDAO;
    }

    public Like salvaLike(Usuario Usuario){
        Like like = new Like(Usuario);
        return this.likesDAO.save(like);
    }

}