package br.com.exemplo.vendas.negocio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.exemplo.vendas.negocio.entity.Compra;
import br.com.exemplo.vendas.util.log.LoggerGenerator;

public class CompraDAO  extends GenericDAO<Compra>
{
	public CompraDAO( EntityManager em )
	{
		super( em ) ;
	}
	
	public boolean inserir( Compra compra )
	{
		boolean result = Boolean.FALSE;
		try
		{
				em.persist( compra );
				result = Boolean.TRUE;
				if (debugInfo) {
					LoggerGenerator.write("Compra inserido: ", compra.getResponsavel());
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

	public boolean alterar( Compra compra )
	{
		boolean result = false;
		Compra existenteCompra = null;

		try
		{
			existenteCompra = em.find( Compra.class, compra.getId() );
			if ( existenteCompra != null )
			{
				em.merge( compra );
				result = true;
				if (debugInfo) {
					LoggerGenerator.write("Compra alterada: ", compra.getResponsavel());
				}
			}
			else
			{
				result = false;
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

	public boolean excluir( Compra compra )
	{
		boolean result = false;

		try
		{
			Compra compraEncontrada = em.find( Compra.class, compra.getId() );
			em.remove( compraEncontrada );
			result = true;
			if (debugInfo) {
				LoggerGenerator.write("Compra excluida: ", compra.getResponsavel());
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

	public List<Compra> localizarPorNumero( Compra compra )
	{
		List<Compra> result = new ArrayList<Compra>( );

		try
		{
			TypedQuery<Compra> q = em.createQuery( "from Compra where numero = :numero", Compra.class );
			q.setParameter( "numero", compra.getNumero());
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