package br.com.exemplo.vendas.apresentacao.service;

import java.io.Serializable;

import br.com.exemplo.vendas.apresentacao.delegate.CompraBusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class CompraService  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Boolean inserirCompra(CompraVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("compraVO", vo);
		responseDTO = CompraBusinessDelegate.getInstance().inserirCompra(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

	public ServiceDTO listarCompras() throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();

		responseDTO = CompraBusinessDelegate.getInstance().selecionarTodosCompra();

		return responseDTO;
	}

	public Boolean alterarCompra(CompraVO  vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("compraVO", vo);
		responseDTO = CompraBusinessDelegate.getInstance().alterarCompra(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

	public Boolean excluirCompra(CompraVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("compraVO", vo);
		responseDTO = CompraBusinessDelegate.getInstance().excluirCompra(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

}
