package br.com.exemplo.vendas.apresentacao.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.exemplo.vendas.apresentacao.service.UsuarioService;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@RequestScoped
@ManagedBean
public class UsuarioBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private UsuarioVO usuarioVO;
	private UsuarioService service;
	private List<UsuarioVO> usuarios;

	
	public UsuarioBean() {
		usuarioVO = new UsuarioVO();
		service = new UsuarioService();
		usuarios = new ArrayList<UsuarioVO>();
		try {
			usuarios = listarUsuarios();
		} catch (LayerException e) {
			e.printStackTrace();
		}
	}

	public void grava() throws LayerException {
		System.out.println("Inserindo -->>> Usuario: " + usuarioVO.getLogin() + "\t Senha: " + usuarioVO.getSenha());

		Boolean sucesso = service.inserirUsuario(usuarioVO);
		usuarios = listarUsuarios();
		
		System.out.println("Usuario Gravado com: " + sucesso);
	}

	public void excluir() throws LayerException {
		System.out.println("Excluindo -->>> Usuario: " + usuarioVO.getLogin());

		Boolean sucesso = service.excluirUsuario(usuarioVO);
		usuarios = listarUsuarios();
		
		System.out.println("Usuario Excluido com: " + sucesso);
	}
	
	public void atualizar() throws LayerException {
		System.out.println("Atualizar -->>> Usuario: " + usuarioVO.getLogin());

		Boolean sucesso = service.alterarUsuario(usuarioVO);
		usuarios = listarUsuarios();
		
		System.out.println("Usuario Excluido com: " + sucesso);
	}

	private List<UsuarioVO> listarUsuarios() throws LayerException {
		System.out.println("Carregando usuarios...");

		ServiceDTO responseDTO = service.listarUsuarios();
		UsuarioVO[] usuariosVO = (UsuarioVO[]) responseDTO.get("listaUsuario");
		
//		UsuarioVO usu ;
//		for(int x=0;x<=5;x++){
//			usu = new UsuarioVO();
//		//	usu.setId((long) 10);
//			usu.setId((long)1 +x);
//			usu.setLogin("usuario"+x);
//			usu.setPerfil("perfil");
//			usu.setBloqueado(true);
//			usu.setGrupo("admin");
//			usu.setSenha("123");
//			usu.setUltimoAcesso(new Date());
//			
//			usuarios.add(usu);
//		}

		if(usuariosVO != null){
			return Arrays.asList(usuariosVO);
		}else{
			return null;
		}
			
//		return usuarios;
//		return null;
		
	}
	
//	public void logar() throws LayerException, IOException {
//		System.out.println("logar -->>> Usuario: " + usuarioVO.getLogin() + "\t Senha: " + usuarioVO.getSenha());
//
//		Boolean sucesso = service.logarUsuario(usuarioVO);
//		
//		if(sucesso){
//			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//
//			response.sendRedirect("docroot/index.xhtml");
//
//			FacesContext.getCurrentInstance().responseComplete();
//		}else{
//			
//			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//
//			response.sendRedirect("docroot/login.xhtml");
//		}
//		
//		System.out.println("Usuario Gravado com: " + sucesso);
//	}

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public List<UsuarioVO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	public void setUsuarios(List<UsuarioVO> usuarios) {
		this.usuarios = usuarios;
	}

	
	
}
