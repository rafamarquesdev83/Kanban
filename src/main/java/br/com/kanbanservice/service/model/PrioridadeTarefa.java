package br.com.kanbanservice.service.model;

public enum PrioridadeTarefa {
	
	ALTA (0, "Prioridade alta"),
	MEDIA(1, "Prioridade média"),
	BAIXA(2, "Prioridade baixa");

	private Integer codigo;
	private String mensagem;
	
	
	private PrioridadeTarefa(Integer codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}


	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public String getMensagem() {
		return mensagem;
	}


	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	
	
	
}
