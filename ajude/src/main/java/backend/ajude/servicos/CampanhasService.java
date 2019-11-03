package backend.ajude.servicos;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import backend.ajude.entidades.Usuario;
import backend.ajude.repositories.UsuariosRepository;

public class CampanhasService {
    private UsuariosRepository<Usuario, String> usuariosDAO;
}
