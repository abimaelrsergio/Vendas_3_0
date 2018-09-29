package br.com.exemplo.vendas.negocio.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.exemplo.vendas.negocio.dao.DaoFactory;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ItemLocal;
import br.com.exemplo.vendas.negocio.ejb.interfaces.ItemRemote;
import br.com.exemplo.vendas.negocio.entity.Item;
import br.com.exemplo.vendas.negocio.model.vo.ItemVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@Stateless
public class ItemBean implements ItemLocal, ItemRemote{
	
	@PersistenceContext( unitName="Vendas")
	EntityManager em ;
	
	public ServiceDTO inserirItem( ServiceDTO requestDTO  ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ItemVO vo = (ItemVO)requestDTO.get( "itemVO" );
		if ( vo != null )
		{
			Item item = new Item(vo.getQuantidade(), vo.getResponsavel(), vo.getSituacao(), vo.getValor()) ; 
			if ( DaoFactory.getItemDAO( em ).inserir( item ) )
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

	public ServiceDTO alterarItem( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ItemVO vo = (ItemVO)requestDTO.get( "itemVO" );
		if ( vo != null )
		{
			Item item = new Item(vo.getQuantidade(), vo.getResponsavel(), vo.getSituacao(), vo.getValor()) ; 
			item.setId(vo.getId());
			if ( DaoFactory.getItemDAO( em ).alterar( item ) )
			{
				responseDTO.set( "resposta", new Boolean( true ) );
			}
			else
			{
				responseDTO.set( "resposta", new Boolean( false ) );
			}
		}
		return responseDTO ;
	}

	public ServiceDTO excluirItem( ServiceDTO requestDTO ) throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		ItemVO vo = (ItemVO)requestDTO.get( "itemVO" );
		if ( vo != null )
		{
			Item item = new Item() ; 
			item.setId( vo.getId()) ;
			if ( DaoFactory.getItemDAO( em ).excluir( item ) )
			{
				responseDTO.set( "resposta", Boolean.TRUE );
			}
			else
			{
				responseDTO.set( "resposta", Boolean.TRUE );
			}
		}
		return responseDTO ;
	}

	public ServiceDTO selecionarItems() throws LayerException
	{
		ServiceDTO responseDTO = new ServiceDTO( );
		Item item = null ;
		List<Item> lista = DaoFactory.getItemDAO( em ).listar(  ) ;
		if ( ( lista != null ) && ( ! lista.isEmpty( ) ) )
		{
			ItemVO[ ] items = new ItemVO[ lista.size( ) ] ;
			for ( int i = 0 ; i < lista.size( ); i++ )
			{
				item   		    = ( Item ) lista.get( i ) ;
				ItemVO itemVO = new ItemVO(item.getId(), item.getQuantidade(), item.getResponsavel(), item.getSituacao(), item.getValor()) ;
				items[ i ]       = itemVO ;
			}
			responseDTO.set("listaItem",  items ) ;
		}
		return responseDTO;
	}

}
