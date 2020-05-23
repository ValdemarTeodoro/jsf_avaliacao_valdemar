package br.com.faculdadedelta.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.faculdadedelta.dao.ProcessoDaoValdemar;
import br.com.faculdadedelta.modelo.InteressadoValdemar;
import br.com.faculdadedelta.modelo.ProcessoValdemar;

@ManagedBean
@SessionScoped
public class ProcessoControllerValdemar {
	private String CADASTRO_PROCESSO = "cadastroProcesso.xhtml";
	private String LISTA_PROCESSO = "listaProcesso.xhtml";
	
	private ProcessoValdemar processoValdemar = new ProcessoValdemar();
	private ProcessoDaoValdemar dao = new ProcessoDaoValdemar();
	private InteressadoValdemar interessadoSelecionado = new InteressadoValdemar();
	
	public ProcessoValdemar getProcessoValdemar() {
		return processoValdemar;
	}
	public void setProcessoValdemar(ProcessoValdemar processoValdemar) {
		this.processoValdemar = processoValdemar;
	}
	public InteressadoValdemar getInteressadoSelecionado() {
		return interessadoSelecionado;
	}
	public void setInteressadoSelecionado(InteressadoValdemar interessadoSelecionado) {
		this.interessadoSelecionado = interessadoSelecionado;
	}
	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void limparCampos() {
		processoValdemar = new ProcessoValdemar();
		interessadoSelecionado = new InteressadoValdemar();
	}
	public String salvar() {
		try {
			if(processoValdemar.getId()==null) {
				processoValdemar.setInteressadoValdemar(interessadoSelecionado);
				dao.incluir(processoValdemar);
				exibirMensagem("Inclusão realizada com sucesso");
				limparCampos();
			}else {
				processoValdemar.setInteressadoValdemar(interessadoSelecionado);
				dao.alterar(processoValdemar);
				exibirMensagem("Alteração realizada com sucesso");
				limparCampos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opereção! " + e.getMessage());
		}
		return CADASTRO_PROCESSO;
	}
	public String editar() {
		
		interessadoSelecionado = processoValdemar.getInteressadoValdemar();
		return CADASTRO_PROCESSO;
	}
	public String excluir() {
		try {
			dao.excluir(processoValdemar);
			exibirMensagem("Exclusão realizada com sucesso");
			limparCampos();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opereção! " + e.getMessage());
		}
		return LISTA_PROCESSO;
	}
	public List<ProcessoValdemar> getLista(){
		List<ProcessoValdemar> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.lista();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a opereção! " + e.getMessage());
		}
		return listaRetorno;
	}
}
