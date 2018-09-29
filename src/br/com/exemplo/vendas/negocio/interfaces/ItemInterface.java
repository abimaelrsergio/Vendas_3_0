package br.com.exemplo.vendas.negocio.interfaces;

public interface ItemInterface {

		public br.com.exemplo.vendas.util.dto.ServiceDTO inserirItem(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO alterarItem(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO excluirItem(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO selecionarItems() throws br.com.exemplo.vendas.util.exception.LayerException,
				java.rmi.RemoteException;
	}