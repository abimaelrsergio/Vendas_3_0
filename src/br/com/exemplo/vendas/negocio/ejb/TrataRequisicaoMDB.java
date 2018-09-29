package br.com.exemplo.vendas.negocio.ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import br.com.exemplo.vendas.negocio.model.vo.CompraVO;

@MessageDriven(
		mappedName 			= "jms/TrataRequisicaoMDB",
		activationConfig 	=
        {
		@ActivationConfigProperty(	propertyName="acknowledgeMode", 
									propertyValue="Auto-acknowledge"),

		@ActivationConfigProperty(	propertyName="destinationType", 
									propertyValue="javax.jms.Queue"),

		@ActivationConfigProperty(	propertyName="destination", 
									propertyValue="queue/RecebeRequisicaoQueue")
		})
public class TrataRequisicaoMDB	implements MessageListener 
{

	public void onMessage( Message msg )
	{
		try
		{
			System.out.println("mensagem recebida -> "  + msg.getJMSMessageID( ) );
			CompraVO vo = ( CompraVO ) ( ( ObjectMessage ) msg ).getObject( )  ;
			System.out.println( vo );
			Thread.sleep( 3000 );
		}
		catch ( Exception e )
		{
			e.printStackTrace( );
		}
	}
}