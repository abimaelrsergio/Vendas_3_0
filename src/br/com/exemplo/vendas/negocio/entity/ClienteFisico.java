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
@Table(name = "TAB_CLIENTE_FISICO", uniqueConstraints = { @UniqueConstraint(columnNames = "cliente_COD_CLIENTE") })
public class ClienteFisico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COD_CLIENTE_FISICO")
	private Long id;
	private String cpf;
	private String rg;
	@OneToOne
	private Cliente cliente;

	public ClienteFisico(){
		
	}
	
	public ClienteFisico(String cpf, String rg) {
		super();
		this.cpf = cpf;
		this.rg = rg;
	}

	public ClienteFisico(Long id, String cpf, String rg) {
		this(cpf, rg);
		this.id = id;
	}
	
	public ClienteFisico(String cpf, String rg, Cliente cliente) {
		this(cpf, rg);
		this.cliente = cliente;
	}
	
	public ClienteFisico(Long id, String cpf, String rg, Cliente cliente) {
		this(cpf, rg, cliente);
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteFisico other = (ClienteFisico) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
