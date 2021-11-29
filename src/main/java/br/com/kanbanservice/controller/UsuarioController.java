package br.com.kanbanservice.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kanbanservice.repository.UsuarioRepository;
import br.com.kanbanservice.request.SolicitacaoAcessoRequest;
import br.com.kanbanservice.response.KanbanResponse;
import br.com.kanbanservice.service.model.CodigoRetorno;
import br.com.kanbanservice.service.model.Usuario;
import br.com.kanbanservice.util.AES;

@RestController
@RequestMapping(path ="/usuario")
public class UsuarioController {

	private Logger logger = LoggerFactory.getLogger(getClass());	

	@Autowired
	Validator validator;

	@Autowired
	UsuarioRepository usuarioRepository;

	@PostMapping("/inclui")
	public ResponseEntity<KanbanResponse> incluirUsuario(SolicitacaoAcessoRequest solicitacaoAcessoRequest) {

		logger.info("Solicitando novo cadastro de usuario" + solicitacaoAcessoRequest.toString());
		Set<ConstraintViolation<SolicitacaoAcessoRequest>> violations = validator.validate(solicitacaoAcessoRequest);

		if (!violations.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new KanbanResponse(violations));
		}

		Usuario usuario = new Usuario();
		usuario.setEmail(solicitacaoAcessoRequest.getEmail());
		usuario.setNome(solicitacaoAcessoRequest.getNome());
		usuario.setSenha(AES.encrypt(solicitacaoAcessoRequest.getSenha()));
		usuarioRepository.save(usuario);
		logger.debug("Cadastra novo usuario", solicitacaoAcessoRequest.getEmail());

		return ResponseEntity.status(HttpStatus.OK).body(new KanbanResponse(CodigoRetorno.SUCESSO));
	} 
}

