package br.com.exemplo.vendas.apresentacao.delegate;

import static org.junit.Assert.fail;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

public class BusinessDelegateTest {

	@Test
	public void testGetInstance() throws LayerException {
		BusinessDelegate instance = BusinessDelegate.getInstance();
		Assert.assertNotNull(instance);
	}

	@Test
	public void testInserirUsuario() throws LayerException {
		UsuarioVO vo = new UsuarioVO( "login2", "senha2", "grupo2", "perfil", "bloqueado", new Date( ), "nome" );
		ServiceDTO requestDTO = new ServiceDTO( );
		ServiceDTO responseDTO = new ServiceDTO( );
		
		requestDTO.set( "usuarioVO", vo );
		responseDTO = BusinessDelegate.getInstance( ).inserirUsuario( requestDTO );
		Boolean sucesso = (Boolean)responseDTO.get( "resposta" );
		
		Assert.assertTrue(sucesso);
	}

	@Test
	public void testAlterarUsuario() throws LayerException {
		UsuarioVO vo = new UsuarioVO( "login2", "senha2", "grupo2", "perfil55", "bloqueado", new Date( ), "nome" );
		ServiceDTO requestDTO = new ServiceDTO( );
		ServiceDTO responseDTO = new ServiceDTO( );
		
		requestDTO.set( "usuarioVO", vo );
		responseDTO = BusinessDelegate.getInstance( ).alterarUsuario( requestDTO );
		Boolean sucesso = (Boolean)responseDTO.get( "resposta" );
		
		Assert.assertTrue(sucesso);
	}	
	
	@Test
	public void testExcluirUsuario() throws LayerException {
		UsuarioVO vo = new UsuarioVO( "login2", "senha2", "grupo2", "perfil", "bloqueado", new Date( ), "nome" );
		ServiceDTO requestDTO = new ServiceDTO( );
		ServiceDTO responseDTO = new ServiceDTO( );
		
		requestDTO.set( "usuarioVO", vo );
		responseDTO = BusinessDelegate.getInstance( ).excluirUsuario( requestDTO );
		Boolean sucesso = (Boolean)responseDTO.get( "resposta" );
		
		Assert.assertTrue(sucesso);
	}

	@Test
	public void testSelectionarTodosUsuarios() {
		fail("Not yet implemented");
	}

	@Test
	public void testInserirQueue() {
		fail("Not yet implemented");
	}

}
