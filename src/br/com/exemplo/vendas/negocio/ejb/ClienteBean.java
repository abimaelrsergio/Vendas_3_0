package br.com.exemplo.vendas.negocio.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteRemote;
import br.com.exemplo.vendas.negocio.entity.Cliente;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ClienteBean implements ClienteRemote, ClienteLocal
{
	@PersistenceContext( unitName="Vendas")
	EntityManager em ;
	
	public ServiceDTO inserirCliente( ServiceDTO requestDTO  ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteVO vo = (ClienteVO)requestDTO.get( "clienteVO" );
		if ( vo != null )
		{
			Cliente cliente = new Cliente(vo.getNome(), vo.getEndereco(), vo.getTelefone(), vo.getSituacao());
			if ( DaoFactory.getClienteDAO( em ).inserir( cliente ) )
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

	public ServiceDTO alterarCliente( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteVO vo = (ClienteVO)requestDTO.get( "clienteVO" );
		if ( vo != null )
		{
			Cliente cliente = new Cliente(vo.getCodigo(), vo.getNome(), vo.getEndereco(), vo.getTelefone(), vo.getSituacao());
			if ( DaoFactory.getClienteDAO( em ).alterar( cliente ) )
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

	public ServiceDTO excluirCliente( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteVO vo = (ClienteVO)requestDTO.get( "clienteVO" );
		if ( vo != null )
		{
			Cliente cliente = new Cliente(vo.getCodigo(), vo.getNome(), vo.getEndereco(), vo.getTelefone(), vo.getSituacao());
			if ( DaoFactory.getClienteDAO( em ).excluir( cliente ) )
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

	@Override
	public ServiceDTO selecionarClientes() throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO( );
		Cliente cliente = null ;
		List<Cliente> lista = DaoFactory.getClienteDAO( em ).listar(  ) ;
		if ( ( lista != null ) && ( ! lista.isEmpty( ) ) )
		{
			ClienteVO[ ] clientes = new ClienteVO[ lista.size( ) ] ;
			for ( int i = 0 ; i < lista.size( ); i++ )
			{
				cliente   		    = ( Cliente ) lista.get( i ) ;
				ClienteVO clienteVO = new ClienteVO(cliente.getCodigo(), cliente.getNome(), cliente.getEndereco(), cliente.getTelefone(), cliente.getSituacao()); 
				clientes[ i ]       = clienteVO ;
			}
			responseDTO.set("listaCliente",  clientes ) ;
		}
		return responseDTO;	}
}
