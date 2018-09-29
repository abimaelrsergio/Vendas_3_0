package br.com.exemplo.vendas.negocio.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteFisicoLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ClienteFisicoRemote;
import br.com.exemplo.vendas.negocio.entity.Cliente;
import br.com.exemplo.vendas.negocio.entity.ClienteFisico;
import br.com.exemplo.vendas.negocio.model.vo.ClienteFisicoVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ClienteFisicoBean implements ClienteFisicoRemote, ClienteFisicoLocal
{
	@PersistenceContext( unitName="Vendas")
	EntityManager em ;
	
	public ServiceDTO inserirClienteFisico( ServiceDTO requestDTO  ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteFisicoVO vo = (ClienteFisicoVO)requestDTO.get( "clienteFisicoVO" );
		if ( vo != null )
		{
			ClienteFisico clienteFisico = null;
			if (vo.getCodigo() != null && vo.getCodigo() > 0) {
				Cliente cliente = new Cliente(vo.getNome(), vo.getEndereco(), vo.getTelefone(), vo.getSituacao());
				clienteFisico = new ClienteFisico(vo.getCPF(), vo.getRG(), cliente);
			} else {
				clienteFisico = new ClienteFisico(vo.getCPF(), vo.getRG());
			}
			if ( DaoFactory.getClienteFisicoDAO( em ).inserir( clienteFisico ) )
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

	public ServiceDTO alterarClienteFisico( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteFisicoVO vo = (ClienteFisicoVO)requestDTO.get( "clienteFisicoVO" );
		if ( vo != null )
		{
			ClienteFisico clienteFisico = null;
			if (vo.getCodigo() != null && vo.getCodigo() > 0) {
				Cliente cliente = new Cliente(vo.getCodigo(), vo.getNome(), vo.getEndereco(), vo.getTelefone(), vo.getSituacao());
				clienteFisico = new ClienteFisico(vo.getId(), vo.getCPF(), vo.getRG(), cliente);
			} else {
				clienteFisico = new ClienteFisico(vo.getId(), vo.getCPF(), vo.getRG());				
			}
			
			clienteFisico.setId(vo.getId());
			if ( DaoFactory.getClienteFisicoDAO( em ).alterar( clienteFisico ) )
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

	public ServiceDTO excluirClienteFisico( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteFisicoVO vo = (ClienteFisicoVO)requestDTO.get( "clienteFisicoVO" );
		if ( vo != null )
		{
			Cliente cliente = new Cliente(vo.getCodigo(), vo.getNome(), vo.getEndereco(), vo.getTelefone(), vo.getSituacao());
			ClienteFisico clienteFisico = new ClienteFisico(vo.getId(), vo.getCPF(), vo.getRG(), cliente);
			if ( DaoFactory.getClienteFisicoDAO( em ).excluir( clienteFisico ) )
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
	public ServiceDTO selecionarClienteFisicos() throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO( );
		ClienteFisico clienteFisico = null ;
		List<ClienteFisico> lista = DaoFactory.getClienteFisicoDAO( em ).listar(  ) ;
		if ( ( lista != null ) && ( ! lista.isEmpty( ) ) )
		{
			ClienteFisicoVO[ ] clienteFisicos = new ClienteFisicoVO[ lista.size( ) ] ;
			for ( int i = 0 ; i < lista.size( ); i++ )
			{
				clienteFisico = (ClienteFisico) lista.get(i);
				ClienteFisicoVO clienteFisicoVO = null;
				if (clienteFisico.getCliente() != null && clienteFisico.getCliente().getCodigo() > 0) {
					clienteFisicoVO = new ClienteFisicoVO(clienteFisico.getId(), clienteFisico.getCpf(), clienteFisico.getRg(),
							clienteFisico.getCliente());
				} else {
					clienteFisicoVO = new ClienteFisicoVO(clienteFisico.getId(), clienteFisico.getCpf(), clienteFisico.getRg());
				}
				clienteFisicos[ i ]       = clienteFisicoVO ;
			}
			responseDTO.set("listaClienteFisico",  clienteFisicos ) ;
		}
		return responseDTO;	}
}
