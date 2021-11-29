package br.com.kanbanservice.response;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import br.com.kanbanservice.service.model.CodigoRetorno;
import br.com.kanbanservice.service.model.Tarefa;

public class ListaTarefaResponse extends KanbanResponse {

	protected List<Tarefa> tarefas;


	public ListaTarefaResponse() {
		super();
	}

	public ListaTarefaResponse(CodigoRetorno codigoRetorno, String mensagem) {
		super(codigoRetorno, mensagem);
	}

	public ListaTarefaResponse(CodigoRetorno codigoRetorno, List<Tarefa> tarefas) {
		super(codigoRetorno);
		this.tarefas = tarefas;
	}

	public ListaTarefaResponse(Set<? extends ConstraintViolation<?>> violations) {
		super(violations);
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	@Override
	public String toString() {
		return "ListaTarefaResponse [tarefas=" + tarefas + "]";
	}

}
