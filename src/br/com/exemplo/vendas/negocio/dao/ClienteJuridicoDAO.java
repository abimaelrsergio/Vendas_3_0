package br.com.exemplo.vendas.negocio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.exemplo.vendas.negocio.entity.ClienteJuridico;
import br.com.exemplo.vendas.util.log.LoggerGenerator;

public class ClienteJuridicoDAO  extends GenericDAO<ClienteJuridico>
{
	public ClienteJuridicoDAO( EntityManager em )
	{
		super( em ) ;
	}
	
	public boolean inserir( ClienteJuridico clienteJuridico )
	{
		boolean result = Boolean.FALSE;
		try
		{
				em.persist( clienteJuridico );
				result = Boolean.TRUE;
				if (debugInfo) {
					LoggerGenerator.write("ClienteJuridico inserido: ", clienteJuridico.getCnpj());
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

	public boolean alterar( ClienteJuridico clienteJuridico )
	{
		boolean result = false;
		ClienteJuridico existenteClienteJuridico = null;

		try
		{
			existenteClienteJuridico = em.find( ClienteJuridico.class, clienteJuridico.getId() );
			if ( existenteClienteJuridico != null )
			{
				em.merge( clienteJuridico );
				result = true;
				if (debugInfo) {
					LoggerGenerator.write("ClienteJuridico alterado: ", clienteJuridico.getCnpj());
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

	public boolean excluir( ClienteJuridico clienteJuridico )
	{
		boolean result = false;

		try
		{
			ClienteJuridico clienteJuridicoEncontrada = em.find( ClienteJuridico.class, clienteJuridico.getId() );
			em.remove( clienteJuridicoEncontrada );
			result = true;
			if (debugInfo) {
				LoggerGenerator.write("ClienteJuridico excluido: ", clienteJuridico.getCnpj());
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

	public List<ClienteJuridico> localizarPorCnpj( ClienteJuridico clienteJuridico )
	{
		List<ClienteJuridico> result = new ArrayList<ClienteJuridico>( );

		try
		{
			TypedQuery<ClienteJuridico> q = em.createQuery( "from ClienteJuridico where cnpj = :cnpj", ClienteJuridico.class );
			q.setParameter( "cnpj", clienteJuridico.getCnpj());
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