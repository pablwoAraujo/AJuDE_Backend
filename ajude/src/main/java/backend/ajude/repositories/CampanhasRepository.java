package backend.ajude.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.ajude.entidades.Campanha;

@Repository
public interface CampanhasRepository<T, ID extends Serializable> extends JpaRepository<Campanha, Long> {
	Optional<Campanha> findById(Long id);
}
