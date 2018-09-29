package br.com.exemplo.vendas.negocio.ejb.client;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ItemInterface;
import br.com.exemplo.vendas.negocio.model.vo.ItemVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesterItemEJB {

	public static void main(String[] args) throws Exception
	{
		Hashtable<String, String> prop = new Hashtable<String, String>( );
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		
		Context ctx = new InitialContext( prop ) ;
	   
		ItemInterface remote = (ItemInterface) ctx.lookup("ItemBean/remote");

		ServiceDTO requestDTO 	= new ServiceDTO( ) ;
		ServiceDTO responseDTO 	= new ServiceDTO( ) ;

		/**
		 * Inserir item
		 */
		ItemVO vo = new ItemVO(new BigInteger("20"), "Abimael Rodrigues", "Atualizado", new BigDecimal(10));
		requestDTO.set("itemVO", vo ) ;
		responseDTO = remote.inserirItem( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso.booleanValue( ) )
		{
			System.out.println("Sucesso na inserção do processo!");
		}

		/**
		 * Consultar itens
		 */
		ItemVO vo5 = null;
		responseDTO = remote.selecionarItems();
		ItemVO[ ] lista = ( ItemVO[ ] ) responseDTO.get( "listaItem" ) ;
		if ( lista != null )
		{
			for ( int i = 0; i < lista.length; i++ )
            {
	            vo5 = ( ItemVO ) lista[ i ] ;
	            System.out.println( vo5 ) ;
            }
		}
		
		/**
		 * Alterar item
		 */
		vo5.setResponsavel("Shirley Vanessa");
		requestDTO.set("itemVO", vo5 ) ;
		responseDTO = remote.alterarItem( requestDTO ) ;
		Boolean sucesso2 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso2.booleanValue( ) )
		{
			System.out.println("Sucesso na atualização do processo!");
		}

		/**
		 * Excluir item
		 */
		requestDTO.set("itemVO", vo5 ) ;
		responseDTO = remote.excluirItem(  requestDTO ) ;
		Boolean sucesso3 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso3.booleanValue( ) )
		{
			System.out.println("Sucesso na exclusão do processo!");
		}
		
	}

}
