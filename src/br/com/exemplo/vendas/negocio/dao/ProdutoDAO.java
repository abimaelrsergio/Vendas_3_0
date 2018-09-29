package br.com.exemplo.vendas.negocio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.exemplo.vendas.negocio.entity.Produto;
import br.com.exemplo.vendas.util.log.LoggerGenerator;

public class ProdutoDAO  extends GenericDAO<Produto>
{
	public ProdutoDAO( EntityManager em )
	{
		super( em ) ;
	}
	
	public boolean inserir( Produto produto )
	{
		boolean result = Boolean.FALSE;
		Produto existenteProduto = null;

		try
		{
			Query q = em.createQuery( "from Produto where codigo like :codigo" );
			q.setParameter( "codigo", produto.getCodigo() );

			try
			{
				existenteProduto = (Produto)q.getSingleResult( );
			}
			catch ( NoResultException  e )
			{
				existenteProduto = null ;
			}

			if ( existenteProduto == null )
			{
				em.persist( produto );
				result = Boolean.TRUE;
				if (debugInfo) {
					LoggerGenerator.write("Produto inserido: ", produto.getDescricao());
				}
			}
			else
			{
				produto.setCodigo( existenteProduto.getCodigo() );
				result = Boolean.FALSE;
			}

		}
		catch ( Exception e )
		{
			if ( debugInfo )
			{
				e.printStackTrace( );
			}
		}
		return result;
	}

	public boolean alterar( Produto produto )
	{
		boolean result = false;
		Produto existenteProduto = null;

		try
		{
			existenteProduto = em.find( Produto.class, produto.getCodigo() );
			if ( existenteProduto != null )
			{
				em.merge( produto );
				result = true;
				if (debugInfo) {
					LoggerGenerator.write("Produto alterado: ", produto.getDescricao());
				}
			}
			else
			{
				result = false;
				if (debugInfo) {
					LoggerGenerator.write("Produto Não alterado: ", produto.getDescricao());
				}
			}
		}
		catch ( Exception e )
		{
			if ( debugInfo )
			{
				e.printStackTrace( );
			}
			result = false;
		}
		return result;
	}

	public boolean excluir( Produto produto )
	{
		Produto obj = null;
		boolean result = false;

		try
		{
			Query q = em.createQuery( "from Produto where codigo = :codigo" );
			q.setParameter( "codigo", produto.getCodigo());
			obj = (Produto)q.getSingleResult( );
			em.remove( obj );
			result = true;
			if (debugInfo) {
				LoggerGenerator.write("Produto excluido: ", produto.getDescricao());
			}
		}
		catch ( Exception e )
		{
			if ( debugInfo )
			{
				e.printStackTrace( );
			}
		}
		return result;
	}

	public Produto localizarPorCodigo( Produto produto )
	{
		Produto obj = new Produto( );

		try
		{
			Query query = em.createQuery( "from Produto where codigo like :codigo" );
			query.setParameter( "codigo", produto.getCodigo() );
			obj = (Produto)query.getSingleResult( );
		}
		catch ( Exception e )
		{
			if ( debugInfo )
			{
				e.printStackTrace( );
			}
		}
		return obj;
	}

	public List<Produto> localizarPorDescricao( Produto produto )
	{
		List<Produto> result = new ArrayList<Produto>( );

		try
		{
			TypedQuery<Produto> q = em.createQuery( "from Produto where descricao like :descricao", Produto.class );
			q.setParameter( "descricao", produto.getDescricao());
			result = q.getResultList( );
		}
		catch ( Exception e )
		{
			if ( debugInfo )
			{
				e.printStackTrace( );
			}
		}
		return result;
	}
}