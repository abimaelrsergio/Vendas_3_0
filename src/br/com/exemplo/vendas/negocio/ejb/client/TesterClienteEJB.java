package br.com.exemplo.vendas.negocio.ejb.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ClienteInterface;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesterClienteEJB {

	public static void main(String[] args) throws Exception
	{
		Hashtable<String, String> prop = new Hashtable<String, String>( );
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		
		Context ctx = new InitialContext( prop ) ;
	   
		ClienteInterface remote = (ClienteInterface) ctx.lookup("ClienteBean/remote");

		ServiceDTO requestDTO 	= new ServiceDTO( ) ;
		ServiceDTO responseDTO 	= new ServiceDTO( ) ;

		/**
		 * Inserir cliente
		 */
		ClienteVO vo = new ClienteVO("Abimael", "Av. Paulista, 1000, São Paulo", "70707070", "Sei lá, sei lá"); 
		requestDTO.set("clienteVO", vo ) ;
		responseDTO = remote.inserirCliente( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso.booleanValue( ) )
		{
			System.out.println("Sucesso na inserção do processo!");
		}

		/**
		 * Consultar cliente
		 */
		ClienteVO vo5 = null;
		responseDTO = remote.selecionarClientes();
		ClienteVO[ ] lista = ( ClienteVO[ ] ) responseDTO.get( "listaCliente" ) ;
		if ( lista != null )
		{
			for ( int i = 0; i < lista.length; i++ )
            {
	            vo5 = ( ClienteVO ) lista[ i ] ;
	            System.out.println( vo5 ) ;
            }
		}
		
		/**
		 * Alterar cliente
		 */
		vo5.setNome("ATUALIZADO");
		requestDTO.set("clienteVO", vo5 ) ;
		responseDTO = remote.alterarCliente( requestDTO ) ;
		Boolean sucesso2 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso2.booleanValue( ) )
		{
			System.out.println("Sucesso na atualização do processo!");
		}

		/**
		 * Excluir cliente
		 */
		requestDTO.set("clienteVO", vo5 ) ;
		responseDTO = remote.excluirCliente(  requestDTO ) ;
		Boolean sucesso3 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso3.booleanValue( ) )
		{
			System.out.println("Sucesso na exclusão do processo!");
		}
		
	}

}
