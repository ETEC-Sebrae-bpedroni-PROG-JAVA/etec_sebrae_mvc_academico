package br.gov.sp.etecsebrae.model;

public class Curso {

	private String nome;

	private String sigla;

	public Curso() {
		super();
	}

	public Curso(String nome, String sigla) {
		super();
		this.nome = nome;
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
