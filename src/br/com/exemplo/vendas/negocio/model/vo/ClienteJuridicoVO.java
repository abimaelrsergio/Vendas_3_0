package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;

import br.com.exemplo.vendas.negocio.entity.Cliente;

public class ClienteJuridicoVO 	extends ClienteVO implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String CNPJ;
	private String IE;
	private String endereco;

	public ClienteJuridicoVO(String cNPJ, String iE) {
		super();
		CNPJ = cNPJ;
		IE = iE;
	}
	
	public ClienteJuridicoVO(String cNPJ, String iE, String endereco) {
		this(cNPJ, iE);
		this.endereco = endereco;
	}

	public ClienteJuridicoVO(Long id, String cNPJ, String iE) {
		this(cNPJ, iE);
		this.id = id;
	}
	
	public ClienteJuridicoVO(Long id, String cNPJ, String iE, String endereco) {
		this(id, cNPJ, iE);
		this.endereco = endereco;
	}
	
	public ClienteJuridicoVO(Long id, String cNPJ, String iE, Cliente cliente) {
		super(cliente.getCodigo(), cliente.getNome(), iE, cliente.getTelefone(), cliente.getSituacao());
		this.id = id;
		CNPJ = cNPJ;
		IE = iE;
		this.endereco = cliente.getEndereco();
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	public String getIE() {
		return IE;
	}

	public void setIE(String IE) {
		this.IE = IE;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
