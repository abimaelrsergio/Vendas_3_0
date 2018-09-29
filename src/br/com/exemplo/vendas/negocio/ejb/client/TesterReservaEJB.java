package br.com.exemplo.vendas.negocio.ejb.client;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ReservaInterface;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesterReservaEJB {

	public static void main(String[] args) throws Exception
	{
		Hashtable<String, String> prop = new Hashtable<String, String>( );
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		
		Context ctx = new InitialContext( prop ) ;
	   
		ReservaInterface remote = (ReservaInterface) ctx.lookup("ReservaBean/remote");

		ServiceDTO requestDTO 	= new ServiceDTO( ) ;
		ServiceDTO responseDTO 	= new ServiceDTO( ) ;

		/**
		 * Inserir reserva
		 */
		ReservaVO vo = new ReservaVO(BigInteger.TEN, new java.util.Date(), "João Pé de Feijão", "INSERIDO", BigDecimal.TEN); 
		requestDTO.set("reservaVO", vo ) ;
		responseDTO = remote.inserirReserva( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso.booleanValue( ) )
		{
			System.out.println("Sucesso na inserção do processo!");
		}

		/**
		 * Consultar reserva
		 */
		ReservaVO vo5 = null;
		responseDTO = remote.selecionarTodosReserva();
		ReservaVO[ ] lista = ( ReservaVO[ ] ) responseDTO.get( "listaReserva" ) ;
		if ( lista != null )
		{
			for ( int i = 0; i < lista.length; i++ )
            {
	            vo5 = ( ReservaVO ) lista[ i ] ;
	            System.out.println( vo5.getAtendente() ) ;
            }
		}
		
		/**
		 * Alterar reserva
		 */
		vo5.setAtendente("ATUALIZADO");
		requestDTO.set("reservaVO", vo5 ) ;
		responseDTO = remote.alterarReserva( requestDTO ) ;
		Boolean sucesso2 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso2.booleanValue( ) )
		{
			System.out.println("Sucesso na atualização do processo!");
		}

		/**
		 * Excluir reserva
		 */
		requestDTO.set("reservaVO", vo5 ) ;
		responseDTO = remote.excluirReserva(  requestDTO ) ;
		Boolean sucesso3 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso3.booleanValue( ) )
		{
			System.out.println("Sucesso na exclusão do processo!");
		}
		
	}

}
