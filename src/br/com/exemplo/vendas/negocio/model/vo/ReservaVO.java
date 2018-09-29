package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class ReservaVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private BigInteger codigo;
	private Date data;
	private String atendente;
	private String situacao;
	private BigDecimal valor;
	private ClienteVO cliente;
	
	public ReservaVO(){
		
	}

	public ReservaVO(BigInteger codigo, Date data, String atendente, String situacao, BigDecimal valor) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.atendente = atendente;
		this.situacao = situacao;
		this.valor = valor;
	}
	
	public ReservaVO(Long id, BigInteger codigo, Date data, String atendente, String situacao, BigDecimal valor) {
		this(codigo, data, atendente, situacao, valor);
		this.id = id;
	}

	public ReservaVO(Long id, BigInteger codigo, Date data, String atendente, String situacao, BigDecimal valor, ClienteVO cliente) {
		this(id, codigo, data, atendente, situacao, valor);
		this.cliente = cliente;
	}

	public BigInteger getCodigo() {
		return codigo;
	}

	public void setCodigo(BigInteger codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public ClienteVO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
