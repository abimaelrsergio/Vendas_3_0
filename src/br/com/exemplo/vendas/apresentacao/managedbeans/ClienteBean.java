package br.com.exemplo.vendas.apresentacao.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.exemplo.vendas.apresentacao.service.ClienteService;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@RequestScoped
@ManagedBean
public class ClienteBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ClienteVO clienteVO;
	private ClienteService service;
	private List<ClienteVO> clientes;
	private int tipoCliente;
	
	
	public ClienteBean() {
		clienteVO = new ClienteVO();
		service = new ClienteService();
		clientes = new ArrayList<ClienteVO>();
		try {
			clientes = listarClientes();
		} catch (LayerException e) {
			e.printStackTrace();
		}
	}

	public void grava() throws LayerException {
		System.out.println("Inserindo -->>> Nome: " + clienteVO.getNome() + "\t Endereco: " + clienteVO.getEndereco()+"Situacao: "
							+ clienteVO.getSituacao() + "Telefone" + clienteVO.getTelefone());

		Boolean sucesso = service.inserirCliente(clienteVO);
		clienteVO = (ClienteVO) listarClientes();
		
		System.out.println("Usuario Gravado com: " + sucesso);
	}

	public void excluir() throws LayerException {
		System.out.println("Excluindo -->>> Cliente: " + clienteVO.getNome());

		Boolean sucesso = service.excluirCliente(clienteVO);
		clienteVO = (ClienteVO) listarClientes();
		
		System.out.println("Cliente Excluido com: " + sucesso);
	}
	
	public void atualizar() throws LayerException {
		System.out.println("Atualizar -->>> Usuario: " + clienteVO.getNome());

		Boolean sucesso = service.alterarCliente(clienteVO);
		clientes = listarClientes();
		
		System.out.println("Usuario Excluido com: " + sucesso);
	}

	private List<ClienteVO> listarClientes() throws LayerException {
		System.out.println("Carregando clientes...");

		ServiceDTO responseDTO = service.listarClientes();
		ClienteVO[] clientesVO = (ClienteVO[]) responseDTO.get("listaClientes");

		if(clientesVO != null){
			return Arrays.asList(clientesVO);
		}else{
			return null;
		}
				
//		ClienteVO cliente;
//		
//		for(int x=0;x<=5;x++){
//			cliente = new ClienteVO();
//			cliente.setCodigo(null);
//			cliente.setEndereco("rua");
//			cliente.setNome("Empresa S.A.");
//			cliente.setSituacao("ativo");
//			cliente.setTelefone("34567890");
//			
//			clientes.add(cliente);
//		}
		
//		return clientes;
		
	}
	
	

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public List<ClienteVO> getClientes() {
		return clientes;
	}

	public int getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}


}
