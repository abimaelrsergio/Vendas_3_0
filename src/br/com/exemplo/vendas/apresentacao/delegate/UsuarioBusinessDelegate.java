package br.com.exemplo.vendas.apresentacao.delegate;

import java.rmi.RemoteException;

import br.com.exemplo.vendas.negocio.interfaces.UsuarioInterface;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;
import br.com.exemplo.vendas.util.exception.SysExceptionFactory;
import br.com.exemplo.vendas.util.locator.ServiceLocator;
import br.com.exemplo.vendas.util.locator.ServiceLocatorException;
import br.com.exemplo.vendas.util.locator.ServiceLocatorFactory;

public class UsuarioBusinessDelegate {
	private static UsuarioBusinessDelegate instance = null;

	private ServiceLocator serviceLocator;

	private UsuarioBusinessDelegate() throws Exception {
		setServiceLocator();
	}

	public synchronized static UsuarioBusinessDelegate getInstance() throws LayerException {
		if (instance == null) {
			try {
				instance = new UsuarioBusinessDelegate();
			} catch (Exception exception) {
				throw SysExceptionFactory.getException(exception);
			}
		}
		return instance;
	}

	public ServiceDTO inserirUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((UsuarioInterface) serviceLocator.getService("UsuarioBean/remote")).inserirUsuario(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO excluirUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((UsuarioInterface) serviceLocator.getService("UsuarioBean/remote")).excluirUsuario(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO alterarUsuario(ServiceDTO requestDTO) throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((UsuarioInterface) serviceLocator.getService("UsuarioBean/remote")).alterarUsuario(requestDTO);
		} catch (RemoteException remoteException) {
			throw SysExceptionFactory.getException(remoteException);
		} catch (ServiceLocatorException serviceLocatorException) {
			throw SysExceptionFactory.getException(serviceLocatorException);
		}
		return responseDTO;
	}

	public ServiceDTO selectionarTodosUsuarios() throws LayerException {
		ServiceDTO responseDTO = new ServiceDTO();
		try {
			responseDTO = ((UsuarioInterface) serviceLocator.getService("UsuarioBean/remote")).selecionarTodosUsuario();
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