package br.com.exemplo.vendas.apresentacao.service;

import java.io.Serializable;
import java.util.Date;

import br.com.exemplo.vendas.apresentacao.delegate.ReservaBusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class ReservaService implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Boolean inserirReserva(ReservaVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("reservaVO", vo);
		responseDTO = ReservaBusinessDelegate.getInstance().inserirReserva(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

	public ServiceDTO listarReservas() throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();

		responseDTO = ReservaBusinessDelegate.getInstance().selecionarTodosReserva();

		return responseDTO;
	}

	public Boolean alterarReserva(ReservaVO  vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("reservaVO", vo);
		responseDTO = ReservaBusinessDelegate.getInstance().alterarReserva(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

	public Boolean excluirReserva(ReservaVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("reservaVO", vo);
		responseDTO = ReservaBusinessDelegate.getInstance().excluirReserva(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}
	
	
}
