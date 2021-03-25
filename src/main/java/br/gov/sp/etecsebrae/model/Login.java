package br.gov.sp.etecsebrae.model;

public class Login {

	private String login;

	private String password;

	private String tipo;

	private String nome;

	public Login() {
		super();
	}

	public Login(String login, String password, String tipo, String nome) {
		super();
		this.login = login;
		this.password = password;
		this.tipo = tipo;
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
