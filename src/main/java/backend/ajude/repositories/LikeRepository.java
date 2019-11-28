package backend.ajude.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.ajude.entidades.Like;

/**
 * Repositorio de Likes
 */
@Repository
public interface LikeRepository<T, ID extends Serializable> extends JpaRepository<Like, Long> {

}
