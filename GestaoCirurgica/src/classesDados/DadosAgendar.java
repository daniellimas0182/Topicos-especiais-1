package classesDados;

import java.sql.Date;

public class DadosAgendar {
	private int id;
	private String nome, data, horario, proce, valor, obs;
	public DadosAgendar(){}
	public DadosAgendar(int id, String nome, String data, String horario, String proce, String valor, String obs) {
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.horario = horario;
		this.proce = proce;
		this.valor = valor;
		this.obs = obs;
	}
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getProce() {
		return proce;
	}

	public void setProce(String proce) {
		this.proce = proce;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
