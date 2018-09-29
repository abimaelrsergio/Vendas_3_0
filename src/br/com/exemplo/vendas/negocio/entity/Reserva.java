package br.com.exemplo.vendas.negocio.entity;

import java.io.Serializable;
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
@Table(name="TAB_RESERVA")
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private BigInteger codigo;
	private Date data;
	private String atendente;
	private String situacao;
	private BigDecimal valor;
	@ManyToOne
	@JoinColumn(name="COD_CLIENTE")
	private Cliente cliente;
	
	public Reserva(){}

	public Reserva(BigInteger codigo, Date data, String atendente, String situacao, BigDecimal valor) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.atendente = atendente;
		this.situacao = situacao;
		this.valor = valor;
	}
	
	public Reserva(Long id, BigInteger codigo, Date data, String atendente, String situacao, BigDecimal valor) {
		this(codigo, data, atendente, situacao, valor);
		this.id = id;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
