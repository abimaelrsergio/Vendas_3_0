package br.com.exemplo.vendas.negocio.interfaces;

public interface ClienteInterface {

		public br.com.exemplo.vendas.util.dto.ServiceDTO inserirCliente(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO alterarCliente(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO excluirCliente(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO selecionarClientes() throws br.com.exemplo.vendas.util.exception.LayerException,
				java.rmi.RemoteException;
	}