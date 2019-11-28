package backend.ajude.servicos;

import org.springframework.stereotype.Service;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Doacao;
import backend.ajude.entidades.DoacaoDTO;
import backend.ajude.entidades.Usuario;
import backend.ajude.repositories.DoacoesRepository;

/**
 * Servico de Doacoes
 */
@Service
public class DoacoesService {
    private DoacoesRepository<Doacao, Long> doacoesRepository;

    public DoacoesService(DoacoesRepository<Doacao, Long> doacoesRepository){
        super();
        this.doacoesRepository = doacoesRepository;
    }

    /**
     * Transforma uma DoacaoDTO em uma Doacao valida
     * @param doacaoDTO a DoacaoDTO com as informacoes para criar uma Doacao
     * @param usuario o Usuario que fez a Doacao
     * @param campanha a Campanha a ser feita a Doacao
     * @return Doacao a nova Doacao criada
     */
	public Doacao parseDoacao(DoacaoDTO doacaoDTO, Usuario usuario, Campanha campanha) {
        return new Doacao(usuario, doacaoDTO.getValor(), campanha, doacaoDTO.getData());	
    }

    /**
     * Salva no repositorio uma Doacao
     * @param doacao a Doacao a ser salva no repositorio
     */
	public void salvarDoacao(Doacao doacao) {
        this.doacoesRepository.save(doacao);
	}
}