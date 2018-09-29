package br.com.exemplo.vendas.negocio.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ProdutoLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ProdutoRemote;
import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.negocio.model.vo.ProdutoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ProdutoBean implements ProdutoLocal, ProdutoRemote{
	
	@PersistenceContext( unitName="Vendas")
	EntityManager em ;
	
	public ServiceDTO inserirProduto( ServiceDTO requestDTO  ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ProdutoVO vo = (ProdutoVO)requestDTO.get( "produtoVO" );
		if ( vo != null )
		{
			Produto produto = new Produto(vo.getDescricao(), vo.getPreco(),vo.getEstoque()) ; 
			if ( DaoFactory.getProdutoDAO( em ).inserir( produto ) )
			{
				responseDTO.set( "resposta", Boolean.TRUE );
			}
			else
			{
				responseDTO.set( "resposta", Boolean.FALSE );
			}
		}
		return responseDTO;
	}

	public ServiceDTO alterarProduto( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ProdutoVO vo = (ProdutoVO)requestDTO.get( "produtoVO" );
		if ( vo != null )
		{
			Produto produto = new Produto(vo.getCodigo(),vo.getDescricao(), vo.getPreco(), vo.getEstoque()) ; 
			produto.setCodigo(vo.getCodigo());
			if ( DaoFactory.getProdutoDAO( em ).alterar( produto ) )
			{
				responseDTO.set( "resposta", new Boolean( true ) );
			}
			else
			{
				responseDTO.set( "resposta", new Boolean( false ) );
			}
		}
		return responseDTO ;
	}

	public ServiceDTO excluirProduto( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ProdutoVO vo = (ProdutoVO)requestDTO.get( "produtoVO" );
		if ( vo != null )
		{
			Produto produto = new Produto() ; 
			produto.setCodigo( vo.getCodigo()) ;
			if ( DaoFactory.getProdutoDAO( em ).excluir( produto ) )
			{
				responseDTO.set( "resposta", new Boolean( true ) );
			}
			else
			{
				responseDTO.set( "resposta", new Boolean( false ) );
			}
		}
		return responseDTO ;
	}

	public ServiceDTO selecionarProdutos() throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		Produto produto = null ;
		List<Produto> lista = DaoFactory.getProdutoDAO( em ).listar(  ) ;
		if ( ( lista != null ) && ( ! lista.isEmpty( ) ) )
		{
			ProdutoVO[ ] produtos = new ProdutoVO[ lista.size( ) ] ;
			for ( int i = 0 ; i < lista.size( ); i++ )
			{
				produto   		    = ( Produto ) lista.get( i ) ;
				ProdutoVO produtoVO = new ProdutoVO(produto.getCodigo(), produto.getDescricao(), 
						 produto.getEstoque(),produto.getPreco());
				produtos[ i ]       = produtoVO ;
			}
			responseDTO.set("listaProdutos",  produtos ) ;
		}
		return responseDTO;
	}

}
