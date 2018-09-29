package br.com.exemplo.vendas.negocio.interfaces;

public interface ProdutoInterface {

		public br.com.exemplo.vendas.util.dto.ServiceDTO inserirProduto(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO alterarProduto(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO excluirProduto(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
				throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

		public br.com.exemplo.vendas.util.dto.ServiceDTO selecionarProdutos() throws br.com.exemplo.vendas.util.exception.LayerException,
				java.rmi.RemoteException;
	}