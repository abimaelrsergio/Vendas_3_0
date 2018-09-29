package br.com.exemplo.vendas.apresentacao.delegate;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.negocio.interfaces.RecebeRequisicaoInterface;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;
import br.com.exemplo.vendas.util.exception.SysExceptionFactory;
import br.com.exemplo.vendas.util.locator.ServiceLocator;
import br.com.exemplo.vendas.util.locator.ServiceLocatorException;
import br.com.exemplo.vendas.util.locator.ServiceLocatorFactory;

public class RecebeRequisicaoBusinessDelegate {
	private static RecebeRequisicaoBusinessDelegate instance = null;

	private ServiceLocator serviceLocator;

	private RecebeRequisicaoBusinessDelegate() throws Exception {
		setServiceLocator();
	}

	public synchronized static RecebeRequisicaoBusinessDelegate getInstance() throws LayerException {
		if (instance == null) {
			try {
				instance = new RecebeRequisicaoBusinessDelegate();
			} catch (Exception exception) {
				throw SysExceptionFactory.getException(exception);
			}
		}
		return instance;
	}

	public ServiceDTO inserirQueue(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((RecebeRequisicaoInterface) serviceLocator.getService("RecebeRequisicaoBean/remote")).inserirFila(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	private void setServiceLocator() throws Exception {
		this.serviceLocator = ServiceLocatorFactory.getServiceLocator("serviceLocator");

	}
}