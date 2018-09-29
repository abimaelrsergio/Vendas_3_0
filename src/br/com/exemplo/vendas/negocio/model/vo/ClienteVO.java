package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;

public class ClienteVO 	implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private String endereco;
	private String telefone;
	private String situacao;
	
	public ClienteVO(){
		
	}

	public ClienteVO(String nome, String endereco, String telefone, String situacao) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.situacao = situacao;
	}
	
	public ClienteVO(Long codigo, String nome, String endereco, String telefone, String situacao) {
		this(nome, endereco, telefone, situacao);
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
}
