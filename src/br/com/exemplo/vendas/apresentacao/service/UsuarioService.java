package br.com.exemplo.vendas.apresentacao.service;

import java.io.Serializable;
import java.util.Date;

import br.com.exemplo.vendas.apresentacao.delegate.UsuarioBusinessDelegate;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class UsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;

	public Boolean inserirUsuario(UsuarioVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("usuarioVO", vo);
		responseDTO = UsuarioBusinessDelegate.getInstance().inserirUsuario(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

	public ServiceDTO listarUsuarios() throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();

		responseDTO = UsuarioBusinessDelegate.getInstance().selectionarTodosUsuarios();

		return responseDTO;
	}

	public Boolean alterarUsuario(UsuarioVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("usuarioVO", vo);
		responseDTO = UsuarioBusinessDelegate.getInstance().alterarUsuario(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}

	public Boolean excluirUsuario(UsuarioVO vo) throws LayerException {
		ServiceDTO requestDTO = new ServiceDTO();
		ServiceDTO responseDTO = new ServiceDTO();

		requestDTO.set("usuarioVO", vo);
		responseDTO = UsuarioBusinessDelegate.getInstance().excluirUsuario(requestDTO);
		Boolean sucesso = (Boolean) responseDTO.get("resposta");

		return sucesso;
	}
	
	public Boolean logarUsuario(UsuarioVO usuarioVO) {

		Boolean validado = null;
		Date date = new Date();

		if (usuarioVO.getLogin().equalsIgnoreCase("usuario") && usuarioVO.getSenha().equalsIgnoreCase("123")) {

			validado = true;
			usuarioVO.setNome("joao");
			usuarioVO.setUltimoAcesso(date);
			usuarioVO.setPerfil("1");
			usuarioVO.setGrupo("2");
			usuarioVO.setBloqueado(Boolean.FALSE);

		}
		return validado;
	}

}