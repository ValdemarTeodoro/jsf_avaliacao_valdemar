package br.com.faculdadedelta.modelo;

public class InteressadoValdemar {
	private Long id;
	private String nomeInteressado;
	private String cpfInteressado;
	public InteressadoValdemar() {
		super();
	}
	public InteressadoValdemar(Long id, String nomeInteressado, String cpfInteressado) {
		super();
		this.id = id;
		this.nomeInteressado = nomeInteressado;
		this.cpfInteressado = cpfInteressado;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeInteressado() {
		return nomeInteressado;
	}
	public void setNomeInteressado(String nomeInteressado) {
		this.nomeInteressado = nomeInteressado;
	}
	public String getCpfInteressado() {
		return cpfInteressado;
	}
	public void setCpfInteressado(String cpfInteressado) {
		this.cpfInteressado = cpfInteressado;
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
		InteressadoValdemar other = (InteressadoValdemar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
