package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.negocio.entity.Reserva;

public class ItemVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private BigInteger quantidade;
	private Produto produto;
	private String responsavel;
	private String situacao;
	private BigDecimal valor;
	private Reserva reserva;

	public ItemVO(BigInteger quantidade, String responsavel, String situacao, BigDecimal valor) {
		this.quantidade = quantidade;
		this.responsavel = responsavel;
		this.situacao = situacao;
		this.valor = valor;
	}
	
	public ItemVO(Long id, BigInteger quantidade, String responsavel, String situacao, BigDecimal valor) {
		this(quantidade, responsavel, situacao, valor);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigInteger quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	@Override
	public String toString() {
		return "ItemVO [id=" + id + ", quantidade=" + quantidade
				+ ", produto=" + produto + ", responsavel=" + responsavel
				+ ", situacao=" + situacao + ", valor=" + valor + ", reserva=" + reserva + "]";
	}
}
