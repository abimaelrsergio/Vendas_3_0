package br.com.exemplo.vendas.apresentacao.delegate;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.negocio.interfaces.ProdutoInterface;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;
import br.com.exemplo.vendas.util.exception.SysExceptionFactory;
import br.com.exemplo.vendas.util.locator.ServiceLocator;
import br.com.exemplo.vendas.util.locator.ServiceLocatorException;
import br.com.exemplo.vendas.util.locator.ServiceLocatorFactory;

public class ProdutoBusinessDelegate {
	private static ProdutoBusinessDelegate instance = null;

	private ServiceLocator serviceLocator;

	private ProdutoBusinessDelegate() throws Exception {
		setServiceLocator();
	}

	public synchronized static ProdutoBusinessDelegate getInstance() throws LayerException {
		if (instance == null) {
			try {
				instance = new ProdutoBusinessDelegate();
			} catch (Exception exception) {
				throw SysExceptionFactory.getException(exception);
			}
		}
		return instance;
	}

	public ServiceDTO inserirProduto(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).inserirProduto(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO excluirProduto(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).excluirProduto(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO alterarProduto(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).alterarProduto(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO selectionarProdutos() throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((ProdutoInterface) serviceLocator.getService("ProdutoBean/remote")).selecionarProdutos();
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