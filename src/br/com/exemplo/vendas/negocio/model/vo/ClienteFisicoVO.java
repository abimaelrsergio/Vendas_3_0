package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;

import br.com.exemplo.vendas.negocio.entity.Cliente;

public class ClienteFisicoVO extends ClienteVO 	implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String CPF;
	private String RG;

	public ClienteFisicoVO(String cPF, String rG) {
		super();
		CPF = cPF;
		RG = rG;
	}
	
	public ClienteFisicoVO(Long id, String cPF, String rG) {
		this(cPF, rG);
		this.id = id;
	}

	public ClienteFisicoVO(Long id, String cPF, String rG, Cliente cliente) {
		super(cliente.getCodigo(), cliente.getNome(), cliente.getEndereco(), cliente.getTelefone(), cliente.getSituacao());
		this.id = id;
		CPF = cPF;
		RG = rG;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String RG) {
		this.RG = RG;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
