package br.com.kanbanservice.service.model;

public enum CodigoRetorno {

	SUCESSO(0, "Operação realizada com sucesso"),
	FALHA(1, "Dados inválidos");
	
	private Integer codigo;
	private String mensagem;
	
	
	private CodigoRetorno(Integer codigo, String mensagem) {
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
