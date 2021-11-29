package br.com.kanbanservice.request;

import javax.validation.constraints.NotBlank;

public class SolicitacaoAcessoRequest {
	
	@NotBlank (message="O nome deve ser informado")
	private String nome;
	
	@NotBlank (message="O email deve ser informado")
	private String email;
	
	@NotBlank (message="A senha deve ser informada")
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public  String toString() {
		return "SolicitacaoAcessoRequest [nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}
	
	

}
