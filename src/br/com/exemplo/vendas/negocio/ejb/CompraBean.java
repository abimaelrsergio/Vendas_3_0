package br.com.exemplo.vendas.negocio.ejb;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.CompraLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.CompraRemote;
import br.com.exemplo.vendas.negocio.entity.Compra;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class CompraBean implements CompraRemote, CompraLocal
{
	@PersistenceContext( unitName="Vendas")
	EntityManager em ;
	
	public ServiceDTO inserirCompra( ServiceDTO requestDTO  ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		CompraVO vo = (CompraVO)requestDTO.get( "compraVO" );
		if ( vo != null )
		{
			Compra compra = new Compra(vo.getNumero(), vo.getData(), vo.getResponsavel(), vo.getSituacao(), vo.getValor() ) ;
			if ( DaoFactory.getCompraDAO( em ).inserir( compra ) )
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

	public ServiceDTO alterarCompra( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		CompraVO vo = (CompraVO)requestDTO.get( "compraVO" );
		if ( vo != null )
		{
			Compra compra = new Compra(vo.getId(), vo.getNumero(), vo.getData(), vo.getResponsavel(), vo.getSituacao(), vo.getValor() ) ;
			compra.setId(vo.getId());
			if ( DaoFactory.getCompraDAO( em ).alterar( compra ) )
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

	public ServiceDTO excluirCompra( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		CompraVO vo = (CompraVO)requestDTO.get( "compraVO" );
		if ( vo != null )
		{
			Compra compra = new Compra(vo.getId(), vo.getNumero(), vo.getData(), vo.getResponsavel(), vo.getSituacao(), vo.getValor() ) ; ; 
			if ( DaoFactory.getCompraDAO( em ).excluir( compra ) )
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
	public ServiceDTO selecionarCompras() throws LayerException, RemoteException {
		ServiceDTO responseDTO = new ServiceDTO( );
		Compra compra = null ;
		List<Compra> lista = DaoFactory.getCompraDAO( em ).listar(  ) ;
		if ( ( lista != null ) && ( ! lista.isEmpty( ) ) )
		{
			CompraVO[ ] compras = new CompraVO[ lista.size( ) ] ;
			for ( int i = 0 ; i < lista.size( ); i++ )
			{
				compra   		    = ( Compra ) lista.get( i ) ;
				CompraVO compraVO = new CompraVO(compra.getId(), compra.getNumero(), compra.getData(), compra.getResponsavel(), compra.getSituacao(), compra.getValor() ) ; 
				compras[ i ]       = compraVO ;
			}
			responseDTO.set("listaCompra",  compras ) ;
		}
		return responseDTO;	}
}
