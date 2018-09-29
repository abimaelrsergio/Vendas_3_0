package br.com.exemplo.vendas.negocio.ejb.client;

import java.math.BigDecimal;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.ProdutoInterface;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesterProdutoEJB {

	public static void main(String[] args) throws Exception
	{
		Hashtable<String, String> prop = new Hashtable<String, String>( );
		prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		prop.put(InitialContext.PROVIDER_URL, "jnp://localhost:1099");
		
		Context ctx = new InitialContext( prop ) ;
	   
		ProdutoInterface remote = (ProdutoInterface) ctx.lookup("ProdutoBean/remote");

		ServiceDTO requestDTO 	= new ServiceDTO( ) ;
		ServiceDTO responseDTO 	= new ServiceDTO( ) ;

		/**
		 * Inserir produto
		 */
		ProdutoVO vo = new ProdutoVO("Milho Verde", new BigDecimal(45.00), 10L, 7L);
		requestDTO.set("produtoVO", vo ) ;
		responseDTO = remote.inserirProduto( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso.booleanValue( ) )
		{
			System.out.println("Sucesso na inserção do processo!");
		}

		/**
		 * Consultar produtos
		 */
		ProdutoVO vo5 = null;
		responseDTO = remote.selecionarProdutos();
		ProdutoVO[ ] lista = ( ProdutoVO[ ] ) responseDTO.get( "listaProdutos" ) ;
		if ( lista != null )
		{
			for ( int i = 0; i < lista.length; i++ )
            {
	            vo5 = ( ProdutoVO ) lista[ i ] ;
	            System.out.println( vo5 ) ;
            }
		}
		
		/**
		 * Alterar produto
		 */
		
		vo5.setDescricao("O produto foi Atualizado");
		requestDTO.set("produtoVO", vo5 ) ;
		responseDTO = remote.alterarProduto( requestDTO ) ;
		Boolean sucesso2 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso2.booleanValue( ) )
		{
			System.out.println("Sucesso na atualização do processo!");
		}

		/**
		 * Excluir produto
		 */
		requestDTO.set("produtoVO", vo5 ) ;
		responseDTO = remote.excluirProduto(  requestDTO ) ;
		Boolean sucesso3 = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso3.booleanValue( ) )
		{
			System.out.println("Sucesso na exclusão do processo!");
		}
		
	}

}
