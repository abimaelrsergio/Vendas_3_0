package br.com.exemplo.vendas.negocio.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="TAB_COMPRA")
public class Compra {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private BigInteger numero;
	private Date data;
	private String responsavel;
	private String situacao;
	private BigDecimal valor;
	private Reserva reserva;
	@ManyToOne
	@JoinColumn(name="COD_CLIENTE")
	private Cliente cliente;
	
	public Compra(){}
	
	public Compra(Long id, BigInteger numero, Date data, String responsavel, String situacao, BigDecimal valor, Reserva reserva, Cliente cliente) {
		super();
		this.id = id;
		this.numero = numero;
		this.data = data;
		this.responsavel = responsavel;
		this.situacao = situacao;
		this.valor = valor;
		this.reserva = reserva;
		this.cliente = cliente;
	}

	public Compra(Long id, BigInteger numero, Date data, String responsavel, String situacao, BigDecimal valor) {
		super();
		this.id = id;
		this.numero = numero;
		this.data = data;
		this.responsavel = responsavel;
		this.situacao = situacao;
		this.valor = valor; 
	}	

	public Compra(BigInteger numero, Date data, String responsavel, String situacao, BigDecimal valor) {
		super();
		this.numero = numero;
		this.data = data;
		this.responsavel = responsavel;
		this.situacao = situacao;
		this.valor = valor; 
	}
	
	public BigInteger getNumero() {
		return numero;
	}
	public void setNumero(BigInteger numero) {
		this.numero = numero;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
