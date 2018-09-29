package br.com.exemplo.vendas.negocio.ejb.client;

import java.math.BigInteger;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import br.com.exemplo.vendas.negocio.interfaces.RecebeRequisicaoInterface;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;

public class TesterQueue {

	public static void main (String[] args) throws Exception
	{
		Hashtable<String, String> prop = new Hashtable<String, String>();
	    prop.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
	    prop.put(InitialContext.PROVIDER_URL,"jnp://localhost:1099");

		Context context = new InitialContext( prop ) ;
		RecebeRequisicaoInterface remote = ( RecebeRequisicaoInterface ) context.lookup("RecebeRequisicaoBean/remote") ;

		ServiceDTO requestDTO 	= new ServiceDTO( ) ;
		ServiceDTO responseDTO 	= new ServiceDTO( ) ;

		CompraVO vo = new CompraVO(  ) ;
		vo.setCliente( 1000 ) ;
		vo.setNumero( new BigInteger("1000") ) ;
		vo.setData( new Date( ) ) ;
		requestDTO.set("compraVO", vo ) ;
		responseDTO = remote.inserirFila( requestDTO ) ;
		Boolean sucesso = ( Boolean ) responseDTO.get("resposta") ;
		if ( sucesso.booleanValue( ) )
		{
			System.out.println("Sucesso na execução do processo!" + responseDTO.get("ticket") );
		}
	}
}
