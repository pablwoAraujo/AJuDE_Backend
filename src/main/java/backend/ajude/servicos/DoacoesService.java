package backend.ajude.servicos;

import org.springframework.stereotype.Service;

import backend.ajude.entidades.Campanha;
import backend.ajude.entidades.Doacao;
import backend.ajude.entidades.DoacaoDTO;
import backend.ajude.entidades.Usuario;
import backend.ajude.repositories.DoacoesRepository;

@Service
public class DoacoesService {
    private DoacoesRepository<Doacao, Long> doacoesRepository;

    public DoacoesService(DoacoesRepository<Doacao, Long> doacoesRepository){
        super();
        this.doacoesRepository = doacoesRepository;
    }

	public Doacao parseDoacao(DoacaoDTO doacaoDTO, Usuario usuario, Campanha campanha) {
        return new Doacao(usuario, doacaoDTO.getValor(), campanha, doacaoDTO.getData());	
    }

	public void salvarDoacao(Doacao doacao) {
        this.doacoesRepository.save(doacao);
	}
}