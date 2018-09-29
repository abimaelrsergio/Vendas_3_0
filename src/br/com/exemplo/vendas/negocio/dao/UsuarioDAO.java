package br.com.exemplo.vendas.negocio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.exemplo.vendas.negocio.entity.Usuario;
import br.com.exemplo.vendas.util.log.LoggerGenerator;

public class UsuarioDAO  extends GenericDAO<Usuario>
{
	public UsuarioDAO( EntityManager em )
	{
		super( em ) ;
	}
	
	public boolean inserir( Usuario usuario )
	{
		boolean result = Boolean.FALSE;
		Usuario existenteUsuario = null;

		try
		{
			Query q = em.createQuery( "from Usuario where login like :login" );
			q.setParameter( "login", usuario.getLogin( ) );

			try
			{
				existenteUsuario = (Usuario)q.getSingleResult( );
			}
			catch ( NoResultException  e )
			{
				existenteUsuario = null ;
			}

			if ( existenteUsuario == null )
			{
				em.persist( usuario );
				result = Boolean.TRUE;
				if (debugInfo) {
					LoggerGenerator.write("Usuario inserido: ", usuario.getLogin());
				}
			}
			else
			{
				usuario.setLogin( existenteUsuario.getLogin( ) );
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

	public boolean alterar( Usuario usuario )
	{
		boolean result = false;
		Usuario existenteUsuario = null;

		try
		{
			existenteUsuario = em.find( Usuario.class, usuario.getId() );
			if ( existenteUsuario != null )
			{
				em.merge( usuario );
				result = true;
				if (debugInfo) {
					LoggerGenerator.write("Usuario alterado: ", usuario.getLogin());
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

	public boolean excluir( Usuario usuario )
	{
		Usuario obj = null;
		boolean result = false;

		try
		{
			Query q = em.createQuery( "from Usuario where login = :login" );
			q.setParameter( "login", usuario.getLogin( ) );
			obj = (Usuario)q.getSingleResult( );
			em.remove( obj );
			result = true;
			if (debugInfo) {
				LoggerGenerator.write("Usuario excluido: ", usuario.getLogin());
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

	public Usuario localizarPorLogin( Usuario usuario )
	{
		Usuario obj = new Usuario( );

		try
		{
			Query query = em
			        .createQuery( "from Usuario where login like :login" );
			query.setParameter( "login", usuario.getLogin( ) );
			obj = (Usuario)query.getSingleResult( );
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

	public List<Usuario> localizarPorNome( Usuario usuario )
	{
		List<Usuario> result = new ArrayList<Usuario>( );

		try
		{
			TypedQuery<Usuario> q = em.createQuery( "from Usuario where nome like :nome", Usuario.class );
			q.setParameter( "nome", usuario.getLogin() );
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