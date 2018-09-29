package br.com.exemplo.vendas.apresentacao.service;

import java.io.Serializable;

import br.com.exemplo.vendas.apresentacao.delegate.ProdutoBusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	public Boolean inserirProduto(ProdutoVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("produtoVO", vo);
		responseDTO = ProdutoBusinessDelegate.getInstance().inserirProduto(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

	public ServiceDTO listarProdutos() throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();

		responseDTO = ProdutoBusinessDelegate.getInstance().selectionarProdutos();

		return responseDTO;
	}

	public Boolean alterarProduto(ProdutoVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("produtoVO", vo);
		responseDTO = ProdutoBusinessDelegate.getInstance().alterarProduto(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

	public Boolean excluirProduto(ProdutoVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("produtoVO", vo);
		responseDTO = ProdutoBusinessDelegate.getInstance().excluirProduto(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

}