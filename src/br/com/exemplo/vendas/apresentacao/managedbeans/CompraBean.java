package br.com.exemplo.vendas.apresentacao.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.exemplo.vendas.apresentacao.service.CompraService;
import br.com.exemplo.vendas.negocio.model.vo.ClienteVO;
import br.com.exemplo.vendas.negocio.model.vo.CompraVO;
import br.com.exemplo.vendas.negocio.model.vo.ReservaVO;
import br.com.exemplo.vendas.util.dto.ServiceDTO;
import br.com.exemplo.vendas.util.exception.LayerException;

@RequestScoped
@ManagedBean
public class CompraBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private CompraVO compraVO;
	private ReservaVO reservaVO;
	private ClienteVO clienteVO;
	
	private CompraService service;
	
	private List<CompraVO> compras;
	
	public CompraBean() {
		compraVO = new CompraVO();
		service = new CompraService();
		compras = new ArrayList<CompraVO>();
		try {
			compras = listarCompras();
		} catch (LayerException e) {
			e.printStackTrace();
		}
	}

	public void grava() throws LayerException {
		System.out.println("Inserindo -->>> numero: "+ compraVO.getNumero() +" Valor: " + compraVO.getValor() + "\t data: " + compraVO.getData());

		Boolean sucesso = service.inserirCompra(compraVO);
		compras = listarCompras();
		
		System.out.println("Produto Gravado com: " + sucesso);
	}

	public void excluir() throws LayerException {
		System.out.println("Excluindo -->>> Numero: " + compraVO.getNumero());

		Boolean sucesso = service.excluirCompra(compraVO);
		compras = listarCompras();
		
		System.out.println("Produto Excluido com: " + sucesso);
	}
	
	public void atualizar() throws LayerException {
		System.out.println("Atualizar -->>> compra numero: " + compraVO.getNumero());

		Boolean sucesso = service.alterarCompra(compraVO);
		compras = listarCompras();
		
		System.out.println("Produto Excluido com: " + sucesso);
	}

	private List<CompraVO> listarCompras() throws LayerException {
		System.out.println("Carregando produtos...");

		ServiceDTO responseDTO = service.listarCompras();
		 System.out.println(responseDTO);
		
		 CompraVO[] comprasVO = (CompraVO[]) responseDTO.get("listaCompras");
		 System.out.println("verifica lista "+comprasVO);
	
		 if(comprasVO != null){
			return Arrays.asList(comprasVO);
		}else{
			return null;
		}
		
	}

	public CompraVO getCompraVO() {
		return compraVO;
	}

	public void setCompraVO(CompraVO compraVO) {
		this.compraVO = compraVO;
	}

	public ReservaVO getReservaVO() {
		return reservaVO;
	}

	public void setReservaVO(ReservaVO reservaVO) {
		this.reservaVO = reservaVO;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public List<CompraVO> getCompras() {
		return compras;
	}

	public void setCompras(List<CompraVO> compras) {
		this.compras = compras;
	}
	
	

}
