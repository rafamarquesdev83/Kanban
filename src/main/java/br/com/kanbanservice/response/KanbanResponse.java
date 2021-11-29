package br.com.kanbanservice.response;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import br.com.kanbanservice.service.model.CodigoRetorno;



public class KanbanResponse {


	protected int codigoRetorno;
	protected String mensagem;

	public KanbanResponse() {
		super();
	}

	public KanbanResponse(CodigoRetorno codigoRetorno, String mensagem) {
		super();
		this.codigoRetorno = codigoRetorno.getCodigo();
		this.mensagem = mensagem;
	}

	public KanbanResponse (Set<? extends ConstraintViolation<?>> violations) {
		this.codigoRetorno = CodigoRetorno.FALHA.getCodigo();
		this.mensagem = violations.stream()
				.map(cv -> cv.getMessage())
				.collect(Collectors.joining(", "));
	}

	public KanbanResponse(CodigoRetorno codigoRetorno) {
		super();
		this.codigoRetorno = codigoRetorno.getCodigo();
	}

	public int getCodigoRetorno() {
		return codigoRetorno;
	}

	public void setCodigoRetorno(int codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "KanbanResponse [codigoRetorno=" + codigoRetorno + ", mensagem=" + mensagem + "]";
	}



}
