package br.com.kanbanservice.request;

import br.com.kanbanservice.service.model.PrioridadeTarefa;

public class ListaTarefaRequest {

	private PrioridadeTarefa prioridadeTarefa;

	public PrioridadeTarefa getPrioridadeTarefa() {
		return prioridadeTarefa;
	}

	public void setPrioridadeTarefa(PrioridadeTarefa prioridadeTarefa) {
		this.prioridadeTarefa = prioridadeTarefa;
	}

	@Override
	public String toString() {
		return "ListaTarefaRequest [prioridadeTarefa=" + prioridadeTarefa + "]";
	}




}
