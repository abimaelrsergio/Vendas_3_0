package br.com.exemplo.vendas.negocio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.exemplo.vendas.negocio.entity.Reserva;
import br.com.exemplo.vendas.util.log.LoggerGenerator;

public class ReservaDAO  extends GenericDAO<Reserva>
{
	public ReservaDAO( EntityManager em )
	{
		super( em ) ;
	}
	
	public boolean inserir( Reserva reserva )
	{
		boolean result = Boolean.FALSE;
		try
		{
				em.persist( reserva );
				result = Boolean.TRUE;
				if (debugInfo) {
					LoggerGenerator.write("Reserva inserido: ", reserva.getAtendente());
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

	public boolean alterar( Reserva reserva )
	{
		boolean result = false;
		Reserva existenteReserva = null;

		try
		{
			existenteReserva = em.find( Reserva.class, reserva.getId() );
			if ( existenteReserva != null )
			{
				em.merge( reserva );
				result = true;
				if (debugInfo) {
					LoggerGenerator.write("Reserva alterada: ", reserva.getAtendente());
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

	public boolean excluir( Reserva reserva )
	{
		boolean result = false;

		try
		{
			Reserva reservaEncontrada = em.find( Reserva.class, reserva.getId() );
			em.remove( reservaEncontrada );
			result = true;
			if (debugInfo) {
				LoggerGenerator.write("Reserva excluida: ", reserva.getAtendente());
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

	public List<Reserva> localizarPorCodigo( Reserva reserva )
	{
		List<Reserva> result = new ArrayList<Reserva>( );

		try
		{
			TypedQuery<Reserva> q = em.createQuery( "from Reserva where codigo = :codigo", Reserva.class );
			q.setParameter( "codigo", reserva.getCodigo());
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