package classesDados;

public class DadosCliente {
	private int id;
	private String nome, sexo, fone, cidade, rua, email, cpf, uf;
	
	public DadosCliente(String nome, String sexo, String fone, String cidade, String rua, String email, String cpf, String uf) {
		//this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.fone = fone;
		this.cidade = cidade;
		this.rua = rua;
		this.email = email;
		this.cpf = cpf;
		this.uf = uf;
	}
	
	public DadosCliente() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
