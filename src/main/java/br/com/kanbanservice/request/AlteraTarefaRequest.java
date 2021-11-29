package br.com.kanbanservice.request;

import javax.validation.constraints.NotNull;

import br.com.kanbanservice.service.model.PrioridadeTarefa;

public class AlteraTarefaRequest {

	@NotNull(message="O id da tarefa deve ser informado")
	private int id;

	@NotNull(message="Informe a descricao")
	private String descricao;

	@NotNull (message="A prioridade deve ser informada")
	private PrioridadeTarefa prioridadeTarefa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PrioridadeTarefa getPrioridadeTarefa() {
		return prioridadeTarefa;
	}

	public void setPrioridadeTarefa(PrioridadeTarefa prioridadeTarefa) {
		this.prioridadeTarefa = prioridadeTarefa;
	}

	@Override
	public String toString() {
		return "AlteraTarefaRequest [id=" + id + ", descricao=" + descricao + ", prioridadeTarefa=" + prioridadeTarefa
				+ "]";
	}




}
