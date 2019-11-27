package backend.ajude.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.ajude.entidades.Doacao;

@Repository
public interface DoacoesRepository<T, ID extends Serializable> extends JpaRepository<Doacao, Long> {

}
