package br.com.exemplo.vendas.negocio.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.UsuarioLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.UsuarioRemote;
import br.com.exemplo.vendas.negocio.entity.Usuario;
import br.com.exemplo.vendas.negocio.model.vo.UsuarioVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class UsuarioBean implements UsuarioRemote, UsuarioLocal
{
	@PersistenceContext( unitName="Vendas")
	EntityManager em ;
	
	public ServiceDTO inserirUsuario( ServiceDTO requestDTO  ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		UsuarioVO vo = (UsuarioVO)requestDTO.get( "usuarioVO" );
		if ( vo != null )
		{
			Usuario usuario = new Usuario( vo.getLogin( ), vo.getSenha( ), vo.getGrupo( ), vo.getPerfil( ), vo.getBloqueado(), vo.getUltimoAcesso() ) ; 
			if ( DaoFactory.getUsuarioDAO( em ).inserir( usuario ) )
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

	public ServiceDTO alterarUsuario( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		UsuarioVO vo = (UsuarioVO)requestDTO.get( "usuarioVO" );
		if ( vo != null )
		{
			Usuario usuario = new Usuario(vo.getLogin( ), vo.getSenha( ), vo.getGrupo( ), vo.getPerfil( ), vo.getBloqueado(), vo.getUltimoAcesso() ) ;
			usuario.setId(vo.getId());
			if ( DaoFactory.getUsuarioDAO( em ).alterar( usuario ) )
			{
				responseDTO.set( "resposta", Boolean.TRUE );
			}
			else
			{
				responseDTO.set( "resposta", Boolean.FALSE );
			}
		}
		return responseDTO ;
	}

	public ServiceDTO excluirUsuario( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		UsuarioVO vo = (UsuarioVO)requestDTO.get( "usuarioVO" );
		if ( vo != null )
		{
			Usuario usuario = new Usuario(  ) ; 
			usuario.setLogin( vo.getLogin( ) ) ;
			if ( DaoFactory.getUsuarioDAO( em ).excluir( usuario ) )
			{
				responseDTO.set( "resposta", Boolean.TRUE );
			}
			else
			{
				responseDTO.set( "resposta", Boolean.FALSE );
			}
		}
		return responseDTO ;
	}

	public ServiceDTO selecionarTodosUsuario() throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		Usuario usuario = null ;
		List<Usuario> lista = DaoFactory.getUsuarioDAO( em ).listar(  ) ;
		if ( ( lista != null ) && ( ! lista.isEmpty( ) ) )
		{
			UsuarioVO[ ] usuarios = new UsuarioVO[ lista.size( ) ] ;
			for ( int i = 0 ; i < lista.size( ); i++ )
			{
				usuario   		    = ( Usuario ) lista.get( i ) ;
				UsuarioVO usuarioVO = new UsuarioVO( usuario.getId(), usuario.getLogin( ), usuario.getSenha( ), usuario.getGrupo( ), usuario.getPerfil( ),
											  usuario.getBloqueado( ), usuario.getUltimoAcesso( )) ;
				usuarios[ i ]       = usuarioVO ;
			}
			responseDTO.set("listaUsuario",  usuarios ) ;
		}
		return responseDTO;
	}
}
