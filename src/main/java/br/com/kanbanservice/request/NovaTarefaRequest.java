package br.com.kanbanservice.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.kanbanservice.service.model.PrioridadeTarefa;
import br.com.kanbanservice.service.model.Status;

public class NovaTarefaRequest {

	@NotBlank (message="A descricao deve ser preenchida")
	private String descricao;

	@NotNull (message="A prioridade deve ser informada")
	private PrioridadeTarefa prioridadeTarefa;

	@NotNull (message="O status deve ser informado com valores: NOVO, ANDAMENTO ou CONCLUIDA")
	private Status status;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "NovaTarefaRequest [descricao=" + descricao + ", prioridadeTarefa="
				+ prioridadeTarefa + ", status=" + status + "]";
	}
}