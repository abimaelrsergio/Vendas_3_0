package br.com.exemplo.vendas.negocio.ejb.client;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ClienteFisicoInterface;
import br.com.exemplo.vendas.negocio.model.vo.ClienteFisicoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesterClienteFisicoEJB {

	public static void main(String[] args) throws Exception
	{
		Hashtable<String, String> prop = new Hashtable<String, String>( );
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		
		Context ctx = new InitialContext( prop ) ;
	   
		ClienteFisicoInterface remote = (ClienteFisicoInterface) ctx.lookup("ClienteFisicoBean/remote");

		ServiceDTO requestDTO 	= new ServiceDTO( ) ;
		ServiceDTO responseDTO 	= new ServiceDTO( ) ;

		/**
		 * Inserir clienteFisico
		 */
		ClienteFisicoVO vo = new ClienteFisicoVO("16598734508", "277886545"); 
		requestDTO.set("clienteFisicoVO", vo ) ;
		responseDTO = remote.inserirClienteFisico( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso.booleanValue( ) )
		{
			System.out.println("Sucesso na inserção do processo!");
		}

		/**
		 * Consultar clienteFisico
		 */
		ClienteFisicoVO vo5 = null;
		responseDTO = remote.selecionarClienteFisicos();
		ClienteFisicoVO[ ] lista = ( ClienteFisicoVO[ ] ) responseDTO.get( "listaClienteFisico" ) ;
		if ( lista != null )
		{
			for ( int i = 0; i < lista.length; i++ )
            {
	            vo5 = ( ClienteFisicoVO ) lista[ i ] ;
	            System.out.println( vo5.getCPF() ) ;
            }
		}
		
		/**
		 * Alterar clienteFisico
		 */
		vo5.setCPF("ATUALIZADO NOVO CPF");
		requestDTO.set("clienteFisicoVO", vo5 ) ;
		responseDTO = remote.alterarClienteFisico( requestDTO ) ;
		Boolean sucesso2 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso2.booleanValue( ) )
		{
			System.out.println("Sucesso na atualização do processo!");
		}

		/**
		 * Excluir clienteFisico
		 */
		requestDTO.set("clienteFisicoVO", vo5 ) ;
		responseDTO = remote.excluirClienteFisico(  requestDTO ) ;
		Boolean sucesso3 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso3.booleanValue( ) )
		{
			System.out.println("Sucesso na exclusão do processo!");
		}
		
	}

}
