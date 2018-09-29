package br.com.exemplo.vendas.apresentacao.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.exemplo.vendas.apresentacao.service.ProdutoService;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@RequestScoped
@ManagedBean
public class ProdutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ProdutoVO produtoVO;
	private ProdutoService service;
	private List<ProdutoVO> produtos;
	
	public ProdutoBean() {
		produtoVO = new ProdutoVO();
		service = new ProdutoService();
		produtos = new ArrayList<ProdutoVO>();
		try {
			produtos = listarProdutos();
		} catch (LayerException e) {
			e.printStackTrace();
		}
	}

	public void grava() throws LayerException {
		System.out.println("Inserindo -->>> Codigo: "+ produtoVO.getCodigo() +" Produto: " + produtoVO.getDescricao() + "\t preco: " + produtoVO.getPreco());

		Boolean sucesso = service.inserirProduto(produtoVO);
		produtos = listarProdutos();
		
		System.out.println("Produto Gravado com: " + sucesso);
	}

	public void excluir() throws LayerException {
		System.out.println("Excluindo -->>> Produto: " + produtoVO.getDescricao());

		Boolean sucesso = service.excluirProduto(produtoVO);
		produtos = listarProdutos();
		
		System.out.println("Produto Excluido com: " + sucesso);
	}
	
	public void atualizar() throws LayerException {
		System.out.println("Atualizar -->>> Produto: " + produtoVO.getDescricao());

		Boolean sucesso = service.alterarProduto(produtoVO);
		produtos = listarProdutos();
		
		System.out.println("Produto Excluido com: " + sucesso);
	}

	private List<ProdutoVO> listarProdutos() throws LayerException {
		System.out.println("Carregando produtos...");

		ServiceDTO responseDTO = service.listarProdutos();
		 System.out.println(responseDTO);
		
		 ProdutoVO[] produtosVO = (ProdutoVO[]) responseDTO.get("listaProdutos");
		 System.out.println("verifica lista "+produtosVO);
	
		 if(produtosVO != null){
			return Arrays.asList(produtosVO);
		}else{
			return null;
		}
		
	}

	public ProdutoVO getProdutoVO() {
		return produtoVO;
	}

	public List<ProdutoVO> getProdutos() {
		return produtos;
	}

	public void setProdutoVO(ProdutoVO produtoVO) {
		this.produtoVO = produtoVO;
	}

	public void setProdutos(List<ProdutoVO> produtos) {
		this.produtos = produtos;
	}
	
}
