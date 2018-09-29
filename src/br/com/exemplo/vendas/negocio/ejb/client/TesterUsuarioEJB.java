package br.com.exemplo.vendas.negocio.ejb.client;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.UsuarioInterface;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesterUsuarioEJB {

	public static void main(String[] args) throws Exception
	{
		Hashtable<String, String> prop = new Hashtable<String, String>( );
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		
		Context ctx = new InitialContext( prop ) ;
	   
		UsuarioInterface remote = (UsuarioInterface) ctx.lookup("UsuarioBean/remote");

		ServiceDTO requestDTO 	= new ServiceDTO( ) ;
		ServiceDTO responseDTO 	= new ServiceDTO( ) ;

		/**
		 * Inserir usuario
		 */
		UsuarioVO vo = new UsuarioVO("abimaelr.sergio", "T31501", "desenvolvedor", "Sênior", Boolean.FALSE, new Date());
		requestDTO.set("usuarioVO", vo ) ;
		responseDTO = remote.inserirUsuario( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso.booleanValue( ) )
		{
			System.out.println("Sucesso na execução do processo!");
		}

		/**
		 * Consultar usuarios
		 */
		UsuarioVO vo5 = null;
		responseDTO = remote.selecionarTodosUsuario() ;
		UsuarioVO[ ] lista = ( UsuarioVO[ ] ) responseDTO.get( "listaUsuario" ) ;
		if ( lista != null )
		{
			for ( int i = 0; i < lista.length; i++ )
            {
	            vo5 = ( UsuarioVO ) lista[ i ] ;
	            System.out.println( vo5 ) ;
            }
		}
		
		/**
		 * Alterar usuario
		 */
		
		vo5.setBloqueado(Boolean.TRUE);
		requestDTO.set("usuarioVO", vo5 ) ;
		responseDTO = remote.alterarUsuario( requestDTO ) ;
		Boolean sucesso2 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso2.booleanValue( ) )
		{
			System.out.println("Sucesso na execução do processo!");
		}

		/**
		 * Excluir usuario
		 */
		UsuarioVO vo3 = new UsuarioVO(null, "abimaelr.sergio", "T31501", "desenvolvedor", "Sênior", Boolean.FALSE, new Date());
		requestDTO.set("usuarioVO", vo3 ) ;
		responseDTO = remote.excluirUsuario(  requestDTO ) ;
		Boolean sucesso3 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso3.booleanValue( ) )
		{
			System.out.println("Sucesso na execução do processo!");
		}
		
	}

}
