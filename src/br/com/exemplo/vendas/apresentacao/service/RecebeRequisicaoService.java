package br.com.exemplo.vendas.apresentacao.service;

import java.io.Serializable;

import br.com.exemplo.vendas.apresentacao.delegate.RecebeRequisicaoBusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class RecebeRequisicaoService implements Serializable{

	private static final long serialVersionUID = 1L;

	public String enviarCompra(CompraVO vo) throws LayerException {
		String ticket = null;
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("compraVO", vo);
		responseDTO = RecebeRequisicaoBusinessDelegate.getInstance().inserirQueue(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");
		if (sucesso.booleanValue()) {
			ticket = (String) responseDTO.get("ticket");
		}
		return ticket;
	}

}