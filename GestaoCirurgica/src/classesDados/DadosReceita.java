package classesDados;

public class DadosReceita {
	private String paciente, remedio, poso, unidade, data, uso;
	private int qtd;
	
	public DadosReceita() {}
	public DadosReceita(String paciente, String remedio, String poso, String unidade, String data, String uso, int qtd) {
		this.paciente = paciente;
		this.remedio = remedio;
		this.poso = poso;
		this.unidade = unidade;
		this.data = data;
		this.uso = uso;
		this.qtd = qtd;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getRemedio() {
		return remedio;
	}

	public void setRemedio(String remedio) {
		this.remedio = remedio;
	}

	public String getPoso() {
		return poso;
	}

	public void setPoso(String poso) {
		this.poso = poso;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
}
