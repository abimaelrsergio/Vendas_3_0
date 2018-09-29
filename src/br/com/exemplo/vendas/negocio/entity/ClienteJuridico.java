package br.com.exemplo.vendas.negocio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TAB_CLIENTE_JURIDICO", uniqueConstraints = { @UniqueConstraint(columnNames = "cliente_COD_CLIENTE") })
public class ClienteJuridico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COD_CLIENTE_JURIDICO")
	private Long id;
	private String cnpj;
	private String ie;
	@OneToOne
	private Cliente cliente;

	public ClienteJuridico(){}

	public ClienteJuridico(String cnpj, String ie) {
		super();
		this.cnpj = cnpj;
		this.ie = ie;
	}
	
	public ClienteJuridico(String cnpj, String ie, Cliente cliente) {
		this(cnpj, ie);
		this.cliente = cliente;
	}
	
	public ClienteJuridico(Long id, String cnpj, String ie, Cliente cliente) {
		this(cnpj, ie, cliente);
		this.id = id;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteJuridico other = (ClienteJuridico) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
