package br.com.kanbanservice.service.model;

public enum Status {

	NOVO (0, "Novo"),
	ANDAMENTO (1, "Andamento"),
	CONCLUIDA (2, "Concluida");

	private Integer codigo;
	private String mensagem;

	private Status(Integer codigo, String mensagem) {
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
