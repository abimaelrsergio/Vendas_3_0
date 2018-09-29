package br.com.exemplo.vendas.apresentacao.delegate;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.negocio.interfaces.CompraInterface;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;
import br.com.exemplo.vendas.util.exception.SysExceptionFactory;
import br.com.exemplo.vendas.util.locator.ServiceLocator;
import br.com.exemplo.vendas.util.locator.ServiceLocatorException;
import br.com.exemplo.vendas.util.locator.ServiceLocatorFactory;

public class CompraBusinessDelegate {
	
	private static CompraBusinessDelegate instance = null;

	private ServiceLocator serviceLocator;

	private CompraBusinessDelegate() throws Exception {
		setServiceLocator();
	}

	public synchronized static CompraBusinessDelegate getInstance() throws LayerException {
		if (instance == null) {
			try {
				instance = new CompraBusinessDelegate();
			} catch (Exception exception) {
				throw SysExceptionFactory.getException(exception);
			}
		}
		return instance;
	}

	public ServiceDTO inserirCompra(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((CompraInterface) serviceLocator.getService("CompraBean/remote")).inserirCompra(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO excluirCompra(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((CompraInterface) serviceLocator.getService("CompraBean/remote")).excluirCompra(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO alterarCompra(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((CompraInterface) serviceLocator.getService("CompraBean/remote")).alterarCompra(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO selecionarTodosCompra() throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((CompraInterface) serviceLocator.getService("CompraBean/remote")).selecionarCompras();
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
