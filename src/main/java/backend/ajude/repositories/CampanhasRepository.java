package backend.ajude.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.ajude.entidades.Campanha;

/**
 * Repositorio de Campanhas
 */
@Repository
public interface CampanhasRepository<T, ID extends Serializable> extends JpaRepository<Campanha, Long> {
	/**
	 * Faz uma busca no repositorio a partir do nome da campanha
	 * @param nome o nome da campanha
	 * @return um Optional com a informacao da campanha
	 */
	Optional<Campanha> findByNome(String nome);

	/**
	 * Faz uma busca no repositorio a partir da url da campanha
	 * @param url a url da campanha
	 * @return um Optional com a informacao da campanha
	 */
	Optional<Campanha> findByUrl(String url);

}
