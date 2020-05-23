package br.com.faculdadedelta.modelo;

import java.util.Date;


public class ProcessoValdemar {
	private Long id;
	private int idInteressado;
	private String numeroProc;
	private String assuntoProc; 
	private Date dataAutuacaoProc;
	private double valorProc;
	private double desconto;
	private double valorTota;
	private InteressadoValdemar interessadoValdemar;


	public ProcessoValdemar() {
		super();
	}
	
	public ProcessoValdemar(Long id, int idInteressado, String numeroProc, String assuntoProc, Date dataAutuacaoProc,
			double valorProc, double desconto, double valorTota, InteressadoValdemar interessadoValdemar) {
		super();
		this.id = id;
		this.idInteressado = idInteressado;
		this.numeroProc = numeroProc;
		this.assuntoProc = assuntoProc;
		this.dataAutuacaoProc = dataAutuacaoProc;
		this.valorProc = valorProc;
		this.desconto = desconto;
		this.valorTota = valorTota;
		this.interessadoValdemar = interessadoValdemar;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdInteressado() {
		return idInteressado;
	}

	public void setIdInteressado(int idInteressado) {
		this.idInteressado = idInteressado;
	}

	public String getNumeroProc() {
		return numeroProc;
	}

	public void setNumeroProc(String numeroProc) {
		this.numeroProc = numeroProc;
	}

	public String getAssuntoProc() {
		return assuntoProc;
	}

	public void setAssuntoProc(String assuntoProc) {
		this.assuntoProc = assuntoProc;
	}

	public Date getDataAutuacaoProc() {
		return dataAutuacaoProc;
	}

	public void setDataAutuacaoProc(Date dataAutuacaoProc) {
		this.dataAutuacaoProc = dataAutuacaoProc;
	}

	public double getValorProc() {
		return valorProc;
	}

	public void setValorProc(double valorProc) {
		this.valorProc = valorProc;
	}

	public double getValorTota() {
		if(valorProc >110 && dataAutuacaoProc.after(new Date(01/01/2010))) {
			valorTota = valorProc - desconto;
		}
		return valorTota;
	}
	
	public void setValorTota(double valorTota) {
			this.valorTota = valorTota;
	}
	

	public double getDesconto() {
		if(valorProc >110 && dataAutuacaoProc.after(new Date(01/01/2010))) {
			desconto = (valorProc*0.025);
		}
		return desconto;
	}

	public void setDesconto(double desconto) {
			this.desconto = desconto;
	}

	public InteressadoValdemar getInteressadoValdemar() {
		return interessadoValdemar;
	}

	public void setInteressadoValdemar(InteressadoValdemar interessadoValdemar) {
		this.interessadoValdemar = interessadoValdemar;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProcessoValdemar other = (ProcessoValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
