package br.com.exemplo.vendas.apresentacao.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import br.com.exemplo.vendas.apresentacao.service.ClienteService;
import br.com.exemplo.vendas.apresentacao.service.ReservaService;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@RequestScoped
@ManagedBean
public class ReservaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private ReservaVO reservaVO;
	private ClienteVO clienteVO;
	private ReservaService service;
	private ClienteService clienteService;
	private List<ReservaVO> reservas;
	private List<ClienteVO> clientes;
	private List<SelectItem> comboCliente = new ArrayList<SelectItem>();
	
	public ReservaBean() {
		reservaVO = new ReservaVO();
		clienteVO = new ClienteVO();
		service = new ReservaService();
		reservas = new ArrayList<ReservaVO>();
		clientes = new ArrayList<ClienteVO>();
		clienteService = new ClienteService();
		
		try {
			reservas = listarReservas();
		} catch (LayerException e) {
			e.printStackTrace();
		}
		inicializaComboCliente();
	}

	public void grava() throws LayerException {
		System.out.println(reservaVO.getCliente().getNome());
		Boolean sucesso = service.inserirReserva(reservaVO);
		reservas = listarReservas();
		
		System.out.println("Produto Gravado com: " + sucesso);
	}

	public void excluir() throws LayerException {
		System.out.println("Excluindo -->>> Reserva: " + reservaVO.getCodigo());

		Boolean sucesso = service.excluirReserva(reservaVO);
		reservas = listarReservas();
		
		System.out.println("Reserva Excluido com: " + sucesso);
	}
	
	public void atualizar() throws LayerException {
		System.out.println("Atualizar -->>> reservas: " + reservaVO.getCodigo());

		Boolean sucesso = service.alterarReserva(reservaVO);
		reservas = listarReservas();
		
		System.out.println("reserva Excluida com: " + sucesso);
	}

	private List<ReservaVO> listarReservas() throws LayerException {
		System.out.println("Carregando reservas...");

		ServiceDTO responseDTO = service.listarReservas();
		 System.out.println(responseDTO);
		
		 ReservaVO[] reservasVO = (ReservaVO[]) responseDTO.get("listaReservas");
		 System.out.println("verifica lista "+reservasVO);
	
		 if(reservasVO != null){
			return Arrays.asList(reservasVO);
		}else{
			return null;
		}
		
	}
	
	private List<ClienteVO> listarClientes() throws LayerException {
		System.out.println("Carregando clientes...");

		ServiceDTO responseDTO = clienteService.listarClientes();
		ClienteVO[] clientesVO = (ClienteVO[]) responseDTO.get("listaClientes");

		if(clientesVO != null){
			return Arrays.asList(clientesVO);
		}else{
			return null;
		}
	}
		
	private void inicializaComboCliente() {
		comboCliente.clear();
		try {
			clientes = listarClientes();
		} catch (LayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(clientes != null){ 
			for(ClienteVO lista: clientes){
				comboCliente.add(new SelectItem(lista.getCodigo(), lista.getNome()));
			}
		}
	}

	public ReservaVO getReservaVO() {
		return reservaVO;
	}

	public void setReservaVO(ReservaVO reservaVO) {
		this.reservaVO = reservaVO;
	}

	public List<ReservaVO> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaVO> reservas) {
		this.reservas = reservas;
	}

	public List<ClienteVO> getClientes() {
		return clientes;
	}

	public void setClientes(List<ClienteVO> clientes) {
		this.clientes = clientes;
	}

	public List<SelectItem> getComboCliente() {
		return comboCliente;
	}

	public void setComboCliente(List<SelectItem> comboCliente) {
		this.comboCliente = comboCliente;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}
	
	
}
