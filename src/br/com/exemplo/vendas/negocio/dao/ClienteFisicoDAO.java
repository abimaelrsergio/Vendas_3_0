package br.com.exemplo.vendas.negocio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.exemplo.vendas.negocio.entity.ClienteFisico;
import br.com.exemplo.vendas.util.log.LoggerGenerator;

public class ClienteFisicoDAO  extends GenericDAO<ClienteFisico>
{
	public ClienteFisicoDAO( EntityManager em )
	{
		super( em ) ;
	}
	
	public boolean inserir( ClienteFisico clienteFisico )
	{
		boolean result = Boolean.FALSE;
		try
		{
				em.persist( clienteFisico );
				result = Boolean.TRUE;
				if (debugInfo) {
					LoggerGenerator.write("ClienteFisico inserido: ", clienteFisico.getCpf());
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

	public boolean alterar( ClienteFisico clienteFisico )
	{
		boolean result = false;
		ClienteFisico existenteClienteFisico = null;

		try
		{
			existenteClienteFisico = em.find( ClienteFisico.class, clienteFisico.getId() );
			if ( existenteClienteFisico != null )
			{
				em.merge( clienteFisico );
				result = true;
				if (debugInfo) {
					LoggerGenerator.write("ClienteFisico alterado: ", clienteFisico.getCpf());
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

	public boolean excluir( ClienteFisico clienteFisico )
	{
		boolean result = false;

		try
		{
			ClienteFisico clienteFisicoEncontrada = em.find( ClienteFisico.class, clienteFisico.getId() );
			em.remove( clienteFisicoEncontrada );
			result = true;
			if (debugInfo) {
				LoggerGenerator.write("ClienteFisico excluido: ", clienteFisico.getCpf());
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

	public List<ClienteFisico> localizarPorCpf( ClienteFisico clienteFisico )
	{
		List<ClienteFisico> result = new ArrayList<ClienteFisico>( );

		try
		{
			TypedQuery<ClienteFisico> q = em.createQuery( "from ClienteFisico where cpf = :cpf", ClienteFisico.class );
			q.setParameter( "cpf", clienteFisico.getCpf());
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