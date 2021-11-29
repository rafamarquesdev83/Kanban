package br.com.kanbanservice.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity (name = "tarefa")
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 250, nullable =  false)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "prioridade",length = 15, nullable = false)
	private PrioridadeTarefa prioridadeTarefa;

	@Enumerated(EnumType.STRING)
	@Column(name = "status",length = 15, nullable = false)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", descricao=" + descricao + ", prioridadeTarefa=" + prioridadeTarefa + ", status="
				+ status + ", usuario=" + usuario + "]";
	}


}