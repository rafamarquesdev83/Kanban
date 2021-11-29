package br.com.kanbanservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kanbanservice.service.model.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario,Integer> {

	public List<Usuario> findAllByEmail(String email);
	public Usuario findByEmailAndSenha(String email, String senha);
}
