package br.com.kanbanservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kanbanservice.service.model.PrioridadeTarefa;
import br.com.kanbanservice.service.model.Status;
import br.com.kanbanservice.service.model.Tarefa;
import br.com.kanbanservice.service.model.Usuario;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {
	
	public List<Tarefa>findAllById(Integer id);
	public List<Tarefa>findAllByPrioridadeTarefaAndUsuarioAndStatusIn(PrioridadeTarefa prioridadeTarefa, Usuario usuario, List<Status> listaStatus);
	public List<Tarefa>findAllByUsuarioAndStatusIn(Usuario usuario, List<Status> listaStatus);
	

}
