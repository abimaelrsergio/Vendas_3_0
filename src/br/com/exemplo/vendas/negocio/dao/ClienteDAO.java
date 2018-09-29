package br.com.exemplo.vendas.negocio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.exemplo.vendas.negocio.entity.Cliente;
import br.com.exemplo.vendas.util.log.LoggerGenerator;

public class ClienteDAO  extends GenericDAO<Cliente>
{
	public ClienteDAO( EntityManager em )
	{
		super( em ) ;
	}
	
	public boolean inserir( Cliente cliente )
	{
		boolean result = Boolean.FALSE;
		try
		{
				em.persist( cliente );
				result = Boolean.TRUE;
				if (debugInfo) {
					LoggerGenerator.write("Cliente inserido: ", cliente.getNome());
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

	public boolean alterar( Cliente cliente )
	{
		boolean result = false;
		Cliente existenteCliente = null;

		try
		{
			existenteCliente = em.find( Cliente.class, cliente.getCodigo() );
			if ( existenteCliente != null )
			{
				em.merge( cliente );
				result = true;
				if (debugInfo) {
					LoggerGenerator.write("Cliente alterado: ", cliente.getNome());
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

	public boolean excluir( Cliente cliente )
	{
		boolean result = false;

		try
		{
			Cliente clienteEncontrada = em.find( Cliente.class, cliente.getCodigo() );
			em.remove( clienteEncontrada );
			result = true;
			if (debugInfo) {
				LoggerGenerator.write("Cliente excluido: ", cliente.getNome());
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

	public List<Cliente> localizarPorNome( Cliente cliente )
	{
		List<Cliente> result = new ArrayList<Cliente>( );

		try
		{
			TypedQuery<Cliente> q = em.createQuery( "from Cliente where nome like :nome", Cliente.class );
			q.setParameter( "nome", cliente.getNome());
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