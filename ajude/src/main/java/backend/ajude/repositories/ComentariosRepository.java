package backend.ajude.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.ajude.entidades.Comentario;

@Repository
public interface ComentariosRepository<T, ID extends Serializable> extends JpaRepository<Comentario, Long> {

}
