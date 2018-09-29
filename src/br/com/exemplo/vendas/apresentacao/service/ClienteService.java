package br.com.exemplo.vendas.apresentacao.service;

import java.io.Serializable;

import br.com.exemplo.vendas.apresentacao.delegate.ClienteBusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class ClienteService implements Serializable{


	private static final long serialVersionUID = 1L;
	
	public Boolean inserirCliente(ClienteVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("clienteVO", vo);
		responseDTO = ClienteBusinessDelegate.getInstance().inserirCliente(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		
		return sucesso;
	}

	public ServiceDTO listarClientes() throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();

		responseDTO = ClienteBusinessDelegate.getInstance().selectionarClientes();

		return responseDTO;
	}

	public Boolean alterarCliente(ClienteVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("clienteVO", vo);
		responseDTO = ClienteBusinessDelegate.getInstance().alterarCliente(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		
		return sucesso;
	}

	public Boolean excluirCliente(ClienteVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("clienteVO", vo);
		responseDTO = ClienteBusinessDelegate.getInstance().excluirCliente(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		
		return sucesso;
	}

}