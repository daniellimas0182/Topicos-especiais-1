package classesDados;

public class DadosConta {
	private int id;
	private String nome, nomeUsuario, senha, confirSenha, email, cpf,celular;
	
	public DadosConta(int id, String nome, String nomeUsuario, String senha, String confirSenha, String email, String cpf, String celular) {
		this.id = id;
		this.nome = nome;
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.confirSenha = confirSenha;
		this.email = email;
		this.cpf = cpf;
		this.celular = celular;
	}
	public DadosConta() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirSenha() {
		return confirSenha;
	}

	public void setConfirSenha(String confirSenha) {
		this.confirSenha = confirSenha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
