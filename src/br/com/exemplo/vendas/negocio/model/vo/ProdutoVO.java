package br.com.exemplo.vendas.negocio.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoVO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long codigo;
	private String descricao;
	private BigDecimal preco;
	private Long estoque;
	
	public ProdutoVO(){
		
	}
	
	public ProdutoVO(Long codigo, String descricao, Long estoque, BigDecimal preco){
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
		this.codigo = codigo;
	}
	
	public ProdutoVO( String descricao, BigDecimal preco, Long estoque){
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
	}	
	
	public ProdutoVO( String descricao, BigDecimal preco, Long estoque, Long codigo){
		this(descricao, preco, estoque);
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Long getEstoque() {
		return estoque;
	}
	public void setEstoque(Long estoque) {
		this.estoque = estoque;
	}
	
	@Override
	public String toString() {
		return "ProdutoVO [codigo=" + codigo + ", descricao=" + descricao
				+ ", preco=" + preco + ", estoque=" + estoque + "]";
	}
	
}
