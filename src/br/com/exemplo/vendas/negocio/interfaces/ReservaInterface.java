package br.com.exemplo.vendas.negocio.interfaces;

public interface ReservaInterface {
	public br.com.exemplo.vendas.util.dto.ServiceDTO inserirReserva(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
			throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

	public br.com.exemplo.vendas.util.dto.ServiceDTO alterarReserva(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
			throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

	public br.com.exemplo.vendas.util.dto.ServiceDTO excluirReserva(br.com.exemplo.vendas.util.dto.ServiceDTO requestDTO)
			throws br.com.exemplo.vendas.util.exception.LayerException, java.rmi.RemoteException;

	public br.com.exemplo.vendas.util.dto.ServiceDTO selecionarTodosReserva() throws br.com.exemplo.vendas.util.exception.LayerException,
			java.rmi.RemoteException;
}
