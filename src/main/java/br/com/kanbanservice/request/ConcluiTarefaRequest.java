package br.com.kanbanservice.request;

import javax.validation.constraints.NotNull;

public class ConcluiTarefaRequest {

	@NotNull(message="O id da tarefa deve ser informado")
	private int id;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ConcluiTarefaRequest [id=" + id + "]";
	}
}

