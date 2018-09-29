package br.com.exemplo.vendas.negocio.ejb.client;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.CompraInterface;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesterCompraEJB {

	public static void main(String[] args) throws Exception
	{
		Hashtable<String, String> prop = new Hashtable<String, String>( );
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		
		Context ctx = new InitialContext( prop ) ;
	   
		CompraInterface remote = (CompraInterface) ctx.lookup("CompraBean/remote");

		ServiceDTO requestDTO 	= new ServiceDTO( ) ;
		ServiceDTO responseDTO 	= new ServiceDTO( ) ;

		/**
		 * Inserir compra
		 */
		CompraVO vo = new CompraVO(BigInteger.TEN, new Date(), "Abimael", "Inserido", new BigDecimal("100000"));
		requestDTO.set("compraVO", vo ) ;
		responseDTO = remote.inserirCompra( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso.booleanValue( ) )
		{
			System.out.println("Sucesso na inserção do processo!");
		}

		/**
		 * Consultar compra
		 */
		CompraVO vo5 = null;
		responseDTO = remote.selecionarCompras();
		CompraVO[ ] lista = ( CompraVO[ ] ) responseDTO.get( "listaCompra" ) ;
		if ( lista != null )
		{
			for ( int i = 0; i < lista.length; i++ )
            {
	            vo5 = ( CompraVO ) lista[ i ] ;
	            System.out.println( vo5 ) ;
            }
		}
		
		/**
		 * Alterar compra
		 */
		vo5.setResponsavel("Shirley Vanessa");
		requestDTO.set("compraVO", vo5 ) ;
		responseDTO = remote.alterarCompra( requestDTO ) ;
		Boolean sucesso2 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso2.booleanValue( ) )
		{
			System.out.println("Sucesso na atualização do processo!");
		}

		/**
		 * Excluir compra
		 */
		requestDTO.set("compraVO", vo5 ) ;
		responseDTO = remote.excluirCompra(  requestDTO ) ;
		Boolean sucesso3 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso3.booleanValue( ) )
		{
			System.out.println("Sucesso na exclusão do processo!");
		}
		
	}

}
