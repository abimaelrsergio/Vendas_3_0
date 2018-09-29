package br.com.exemplo.vendas.apresentacao.delegate;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.negocio.interfaces.ClienteInterface;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;
import br.com.exemplo.vendas.util.exception.SysExceptionFactory;
import br.com.exemplo.vendas.util.locator.ServiceLocator;
import br.com.exemplo.vendas.util.locator.ServiceLocatorException;
import br.com.exemplo.vendas.util.locator.ServiceLocatorFactory;

public class ClienteBusinessDelegate {
	
	private static ClienteBusinessDelegate instance = null;

	private ServiceLocator serviceLocator;

	private ClienteBusinessDelegate() throws Exception {
		setServiceLocator();
	}

	public synchronized static ClienteBusinessDelegate getInstance() throws LayerException {
		if (instance == null) {
			try {
				instance = new ClienteBusinessDelegate();
			} catch (Exception exception) {
				throw SysExceptionFactory.getException(exception);
			}
		}
		return instance;
	}

	public ServiceDTO inserirCliente(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ClienteInterface) serviceLocator.getService("ClienteBean/remote")).inserirCliente(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO excluirCliente(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ClienteInterface) serviceLocator.getService("ClienteBean/remote")).excluirCliente(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO alterarCliente(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ClienteInterface) serviceLocator.getService("ClienteBean/remote")).alterarCliente(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO selectionarClientes() throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ClienteInterface) serviceLocator.getService("ClienteBean/remote")).selecionarClientes();
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
