package br.com.exemplo.vendas.negocio.interfaces;

public interface ClienteFisicoInterface {

		public br.com.exemplo.vendas.util.dto.ServiceDTO inserirClienteFisico(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO alterarClienteFisico(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO excluirClienteFisico(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO selecionarClienteFisicos() throws br.com.exemplo.vendas.util.exception.LayerException,
				java.rmi.RemoteException;
	}