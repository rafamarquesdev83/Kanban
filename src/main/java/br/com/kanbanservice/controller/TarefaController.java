package br.com.kanbanservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kanbanservice.repository.TarefaRepository;
import br.com.kanbanservice.repository.UsuarioRepository;
import br.com.kanbanservice.request.AlteraTarefaRequest;
import br.com.kanbanservice.request.ConcluiTarefaRequest;
import br.com.kanbanservice.request.ExcluiTarefaRequest;
import br.com.kanbanservice.request.ListaTarefaRequest;
import br.com.kanbanservice.request.NovaTarefaRequest;
import br.com.kanbanservice.response.KanbanResponse;
import br.com.kanbanservice.response.ListaTarefaResponse;
import br.com.kanbanservice.service.model.CodigoRetorno;
import br.com.kanbanservice.service.model.Status;
import br.com.kanbanservice.service.model.Tarefa;
import br.com.kanbanservice.service.model.Usuario;

@RestController
@RequestMapping(path ="/tarefa")
public class TarefaController {

	private Logger logger = LoggerFactory.getLogger(getClass());	

	@Autowired
	Validator validator;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TarefaRepository tarefaRepository;

	@PostMapping("/inclui")
	public ResponseEntity<KanbanResponse> incluirTarefa(NovaTarefaRequest novaTarefaRequest) {

		logger.info("Solicitando nova tarefa" + novaTarefaRequest.toString());
		Set<ConstraintViolation<NovaTarefaRequest>> violations = validator.validate(novaTarefaRequest);

		if (!violations.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new KanbanResponse(violations));
		}

		Integer idUsuario = Integer.parseInt(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

		if(!usuarioOptional.isPresent()) {

			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		Tarefa tarefa = new Tarefa();
		tarefa.setDescricao(novaTarefaRequest.getDescricao());
		tarefa.setPrioridadeTarefa(novaTarefaRequest.getPrioridadeTarefa());
		tarefa.setStatus(novaTarefaRequest.getStatus());
		tarefa.setUsuario(usuarioOptional.get());
		tarefaRepository.save(tarefa);

		return ResponseEntity.status(HttpStatus.OK).body(new KanbanResponse(CodigoRetorno.SUCESSO));

	}

	@PostMapping("/exclui")
	public ResponseEntity<KanbanResponse> exclui (ExcluiTarefaRequest excluiTarefaRequest) {

		logger.info("Executando exclusao de tarefa"
				+ excluiTarefaRequest.toString());

		Set<ConstraintViolation<ExcluiTarefaRequest>> violations = validator.validate(excluiTarefaRequest);

		if (!violations.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new KanbanResponse(violations));
		}

		Integer idUsuario = Integer.parseInt(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}
		List<Tarefa> tarefas = tarefaRepository.findAllById(excluiTarefaRequest.getId());

		if(!tarefas.isEmpty()) {
			Tarefa tarefa = tarefas.get(0);
			tarefaRepository.delete(tarefa);

			return ResponseEntity.status(HttpStatus.OK).body(new KanbanResponse(CodigoRetorno.SUCESSO));
		}else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new KanbanResponse(CodigoRetorno.FALHA, "Tarefa nao encontrada para o id informado"));
		}
	}

	@PostMapping("/altera")
	public ResponseEntity<KanbanResponse> altera (AlteraTarefaRequest alteraTarefaRequest) {

		logger.info("Altera a tarefa" + alteraTarefaRequest.toString());

		Set<ConstraintViolation<AlteraTarefaRequest>> violations = validator.validate(alteraTarefaRequest);

		if (!violations.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new KanbanResponse(violations));
		}

		Integer idUsuario = Integer.parseInt(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		Usuario usuario  = usuarioOptional.get();

		List<Tarefa> tarefas = tarefaRepository.findAllById(alteraTarefaRequest.getId());

		if(!tarefas.isEmpty()) {
			Tarefa tarefa = tarefas.get(0);

			if(usuario.getId() == tarefa.getUsuario().getId()){
				tarefa.setDescricao(alteraTarefaRequest.getDescricao());
				tarefa.setPrioridadeTarefa(alteraTarefaRequest.getPrioridadeTarefa());
				tarefaRepository.save(tarefa);
			}else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new KanbanResponse(CodigoRetorno.FALHA, "Usuario da sessao nao corresponde ao usuario da tarefa"));
			}
		}else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new KanbanResponse(CodigoRetorno.FALHA, "Nenhuma tarefa encontrada para o Id informado"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new KanbanResponse(CodigoRetorno.SUCESSO));
	}

	@PostMapping("/conclui")
	public ResponseEntity<KanbanResponse> conclui(ConcluiTarefaRequest concluiTarefaRequest) {

		logger.info("Conclui tarefa" + concluiTarefaRequest.toString());

		Set<ConstraintViolation<ConcluiTarefaRequest>> violations = validator.validate(concluiTarefaRequest);

		if (!violations.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new KanbanResponse(violations));
		}

		Integer idUsuario = Integer.parseInt(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		Usuario usuario  = usuarioOptional.get();

		List<Tarefa> tarefas = tarefaRepository.findAllById(concluiTarefaRequest.getId());

		if(!tarefas.isEmpty()) {
			Tarefa tarefa = tarefas.get(0);
			if(usuario.getId() == tarefa.getUsuario().getId()){
				tarefa.setStatus(Status.CONCLUIDA);
				tarefaRepository.save(tarefa);
				return ResponseEntity.status(HttpStatus.OK).body(new KanbanResponse(CodigoRetorno.SUCESSO));
			}
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new KanbanResponse(CodigoRetorno.FALHA, "Tarefa não encontrada"));
	}

	@PostMapping("/lista")
	public ResponseEntity<KanbanResponse> listaTarefa(ListaTarefaRequest listaTarefaRequest) {

		Integer idUsuario = Integer.parseInt(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

		if(!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}

		Usuario usuario  = usuarioOptional.get();
		List<Tarefa> tarefas = new ArrayList<Tarefa>();

		if(listaTarefaRequest.getPrioridadeTarefa() != null) {

			tarefas = tarefaRepository.findAllByPrioridadeTarefaAndUsuarioAndStatusIn(listaTarefaRequest.getPrioridadeTarefa(), usuario, Arrays.asList(Status.ANDAMENTO, Status.NOVO));
		}else {
			tarefas = tarefaRepository.findAllByUsuarioAndStatusIn(usuario,Arrays.asList(Status.ANDAMENTO, Status.NOVO));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ListaTarefaResponse(CodigoRetorno.SUCESSO, tarefas));
	}
}








