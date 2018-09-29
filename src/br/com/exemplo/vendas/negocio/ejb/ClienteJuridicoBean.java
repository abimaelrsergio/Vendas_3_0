package br.com.exemplo.vendas.negocio.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteJuridicoLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteJuridicoRemote;
import br.com.exemplo.vendas.negocio.entity.Cliente;
import br.com.exemplo.vendas.negocio.entity.ClienteJuridico;
import br.com.exemplo.vendas.negocio.model.vo.ClienteJuridicoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ClienteJuridicoBean implements ClienteJuridicoRemote, ClienteJuridicoLocal
{
	@PersistenceContext( unitName="Vendas")
	EntityManager em ;
	
	public ServiceDTO inserirClienteJuridico( ServiceDTO requestDTO  ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteJuridicoVO vo = (ClienteJuridicoVO)requestDTO.get( "clienteJuridicoVO" );
		if ( vo != null )
		{
			ClienteJuridico clienteJuridico = null;
			if (vo.getCodigo() != null && vo.getCodigo() > 0) {
				Cliente cliente = new Cliente(vo.getNome(), vo.getEndereco(), vo.getTelefone(), vo.getSituacao());
				clienteJuridico = new ClienteJuridico(vo.getCNPJ(), vo.getIE(), cliente);
			} else {
				clienteJuridico = new ClienteJuridico(vo.getCNPJ(), vo.getIE());
			}
			
			if ( DaoFactory.getClienteJuridicoDAO( em ).inserir( clienteJuridico ) )
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

	public ServiceDTO alterarClienteJuridico( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteJuridicoVO vo = (ClienteJuridicoVO)requestDTO.get( "clienteJuridicoVO" );
		if ( vo != null )
		{
			ClienteJuridico clienteJuridico = null;
			if (vo.getCodigo() != null && vo.getCodigo() > 0) {
				Cliente cliente = new Cliente(vo.getNome(), vo.getEndereco(), vo.getTelefone(), vo.getSituacao());
				clienteJuridico = new ClienteJuridico(vo.getCNPJ(), vo.getIE(), cliente);
			} else {
				clienteJuridico = new ClienteJuridico(vo.getCNPJ(), vo.getIE());
			}
			clienteJuridico.setId(vo.getId());
			if ( DaoFactory.getClienteJuridicoDAO( em ).alterar( clienteJuridico ) )
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

	public ServiceDTO excluirClienteJuridico( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteJuridicoVO vo = (ClienteJuridicoVO)requestDTO.get( "clienteJuridicoVO" );
		if ( vo != null )
		{
			Cliente cliente = new Cliente(vo.getCodigo(), vo.getNome(), vo.getEndereco(), vo.getTelefone(), vo.getSituacao());
			ClienteJuridico clienteJuridico = new ClienteJuridico(vo.getId(), vo.getCNPJ(), vo.getIE(), cliente); 
			if ( DaoFactory.getClienteJuridicoDAO( em ).excluir( clienteJuridico ) )
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
	public ServiceDTO selecionarClienteJuridicos() throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteJuridico clienteJuridico = null ;
		List<ClienteJuridico> lista = DaoFactory.getClienteJuridicoDAO( em ).listar(  ) ;
		if ( ( lista != null ) && ( ! lista.isEmpty( ) ) )
		{
			ClienteJuridicoVO[ ] clienteJuridicos = new ClienteJuridicoVO[ lista.size( ) ] ;
			for ( int i = 0 ; i < lista.size( ); i++ )
			{
				clienteJuridico   		    = ( ClienteJuridico ) lista.get( i ) ;
				ClienteJuridicoVO clienteJuridicoVO = null;
				if (clienteJuridico.getCliente() != null && clienteJuridico.getCliente().getCodigo() > 0) {
					clienteJuridicoVO = new ClienteJuridicoVO(clienteJuridico.getId(), clienteJuridico.getCnpj(), clienteJuridico.getIe(),
							clienteJuridico.getCliente());
				} else {
					clienteJuridicoVO = new ClienteJuridicoVO(clienteJuridico.getId(), clienteJuridico.getCnpj(), clienteJuridico.getIe());
				}
 
				clienteJuridicos[ i ]       = clienteJuridicoVO ;
			}
			responseDTO.set("listaClienteJuridico",  clienteJuridicos ) ;
		}
		return responseDTO;	}
}
