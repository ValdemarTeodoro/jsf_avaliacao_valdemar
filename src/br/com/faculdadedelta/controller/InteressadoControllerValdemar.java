package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.InteressadoDaoValdemar;
import br.com.faculdadedelta.modelo.InteressadoValdemar;

@ManagedBean
@SessionScoped
public class InteressadoControllerValdemar {
	
	private String CADASTRO_INTERESSADO = "cadastroInteressado.xhtml";
	private String LISTA_INTERESSADO = "listaInteressado.xhtml";
	
	private InteressadoValdemar interessado = new InteressadoValdemar();
	private InteressadoDaoValdemar dao = new InteressadoDaoValdemar();
	
	public InteressadoValdemar getInteressado() {
		return interessado;
	}
	public void setInteressado(InteressadoValdemar interessado) {
		this.interessado = interessado;
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		interessado = new InteressadoValdemar();
	}
	public String salvar() {
		try {
			if(interessado.getId()== null) {
				dao.incluir(interessado);
				exibirMensagem("Inclus�o realizada com sucesso");
				limparCampos();
			}else {
				dao.alterar(interessado);
				exibirMensagem("Altera��o realizada com sucesso");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opere��o! " + e.getMessage());
		}
		return CADASTRO_INTERESSADO;
	}
	public String editar() {
		return CADASTRO_INTERESSADO;
	}
	public String excluir() {
		try {
			dao.excluir(interessado);
			exibirMensagem("Exclus�o realizada com sucesso");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opere��o! " + e.getMessage());
		}
		return LISTA_INTERESSADO;
	}
	public List<InteressadoValdemar> getLista(){
		List<InteressadoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opere��o! " + e.getMessage());
		}
		return listaRetorno;
	}
}
