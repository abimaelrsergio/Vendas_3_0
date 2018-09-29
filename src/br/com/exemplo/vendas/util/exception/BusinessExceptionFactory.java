package br.com.exemplo.vendas.util.exception;

import java.util.Iterator;

import br.com.exemplo.vendas.util.log.LoggerGenerator;


/**
 * Responsável por criar as Exceções de Negócios
 *
 * @version 1.0
 */
public class BusinessExceptionFactory
{

	/**
	 * Método utilizado para obter uma exceção Business Exception
	 *
	 * @param	MsgExceptionList	informar uma lista do tipo MsgExceptionList
	 * @return 	BusinessException
	 * @see		MsgExceptionList
	 */
 	public static BusinessException getException(MsgExceptionList list)
 	{
		Iterator iterator = list.getMsgException().iterator();
		while (iterator.hasNext())
		{
			MsgException msg = (MsgException) iterator.next() ;
			if (Environment.getLogTraceSystemOut())
			{
					System.out.println ("--------------------------------:::     Exception Details      :::---------------------------------------");
					System.out.println (" Exception(logger) ->  [code]: " + msg.getCode() + " [message]: " + msg.getDetails());
					System.out.println ("--------------------------------:::   Exception Information    :::---------------------------------------");
			}
			LoggerGenerator.write(LoggerGenerator.EVENT_EXECUCAO_APRESENTACAO , msg.getCode(), (String) msg.getDetails() );
		}
		return new BusinessException(list);
	}

   }
