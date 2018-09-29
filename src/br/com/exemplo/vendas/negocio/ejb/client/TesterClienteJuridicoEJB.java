package br.com.exemplo.vendas.negocio.ejb.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ClienteJuridicoInterface;
import br.com.exemplo.vendas.negocio.model.vo.ClienteJuridicoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesterClienteJuridicoEJB {

	public static void main(String[] args) throws Exception
	{
		Hashtable<String, String> prop = new Hashtable<String, String>( );
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		
		Context ctx = new InitialContext( prop ) ;
	   
		ClienteJuridicoInterface remote = (ClienteJuridicoInterface) ctx.lookup("ClienteJuridicoBean/remote");

		ServiceDTO requestDTO 	= new ServiceDTO( ) ;
		ServiceDTO responseDTO 	= new ServiceDTO( ) ;

		/**
		 * Inserir clienteJuridico
		 */
		ClienteJuridicoVO vo = new ClienteJuridicoVO("15691576850", "277885765", "Av. Guimarães Magalhães, 65, parque primavera"); 
		requestDTO.set("clienteJuridicoVO", vo ) ;
		responseDTO = remote.inserirClienteJuridico( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso.booleanValue( ) )
		{
			System.out.println("Sucesso na inserção do processo!");
		}

		/**
		 * Consultar clienteJuridico
		 */
		ClienteJuridicoVO vo5 = null;
		responseDTO = remote.selecionarClienteJuridicos();
		ClienteJuridicoVO[ ] lista = ( ClienteJuridicoVO[ ] ) responseDTO.get( "listaClienteJuridico" ) ;
		if ( lista != null )
		{
			for ( int i = 0; i < lista.length; i++ )
            {
	            vo5 = ( ClienteJuridicoVO ) lista[ i ] ;
	            System.out.println( vo5 ) ;
            }
		}
		
		/**
		 * Alterar clienteJuridico
		 */
		vo5.setCNPJ("ATUALIZANDO CNPJ");
		requestDTO.set("clienteJuridicoVO", vo5 ) ;
		responseDTO = remote.alterarClienteJuridico( requestDTO ) ;
		Boolean sucesso2 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso2.booleanValue( ) )
		{
			System.out.println("Sucesso na atualização do processo!");
		}

		/**
		 * Excluir clienteJuridico
		 */
		requestDTO.set("clienteJuridicoVO", vo5 ) ;
		responseDTO = remote.excluirClienteJuridico(  requestDTO ) ;
		Boolean sucesso3 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso3.booleanValue( ) )
		{
			System.out.println("Sucesso na exclusão do processo!");
		}
		
	}

}
