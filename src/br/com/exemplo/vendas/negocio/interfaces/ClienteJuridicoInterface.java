package br.com.exemplo.vendas.negocio.interfaces;

public interface ClienteJuridicoInterface {

		public br.com.exemplo.vendas.util.dto.ServiceDTO inserirClienteJuridico(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO alterarClienteJuridico(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO excluirClienteJuridico(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO selecionarClienteJuridicos() throws br.com.exemplo.vendas.util.exception.LayerException,
				java.rmi.RemoteException;
	}