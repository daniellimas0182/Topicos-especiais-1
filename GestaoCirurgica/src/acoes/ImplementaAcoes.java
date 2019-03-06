package acoes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import Banco.BancoDados;
import Principal.AreaPrincipal;
import areaLogin.Login;
import classesDados.DadosAgendar;
import classesDados.DadosCliente;
import classesDados.DadosConta;
import classesDados.DadosReceita;
import exception.Exceptions;
import internalPanel.AgendarProcedimento;
import internalPanel.CadastroClienteIP;
import internalPanel.CadastroContaIP;
import internalPanel.CancelarProcedimento;
import internalPanel.ProcedimentosAgendados;
import logsusuarios.ControleLogs;
import receituario.Receita;

public class ImplementaAcoes implements ActionListener{
	private Login login;
	private AreaPrincipal ap;
	private CadastroContaIP cadconpanel;
	private CadastroClienteIP cadclipanel;
	private AgendarProcedimento ac;
	private ProcedimentosAgendados consulAgen;
	private CancelarProcedimento cancelProce;
	private Receita r;
	public ImplementaAcoes(Login login, AreaPrincipal ap, CadastroContaIP cadconpanel, CadastroClienteIP cadclipanel, AgendarProcedimento ac, ProcedimentosAgendados consulAgen, CancelarProcedimento cancelProce, Receita r) {
		this.login = login;
		this.ap = ap;
		this.cadconpanel = cadconpanel;
		this.cadclipanel = cadclipanel;
		this.ac = ac;
		this.consulAgen = consulAgen;
		this.cancelProce = cancelProce;
		this.r = r;
	}
	ControleLogs cl = new ControleLogs();
	Calendar c = Calendar.getInstance();

	@Override
	public void actionPerformed(ActionEvent ae) {
		if("entrar".equals(ae.getActionCommand())) {
			DadosConta du = login.getDadosConta();
			if(du.getNomeUsuario().equals("") && du.getSenha().equals("")) {
				JOptionPane.showMessageDialog(null, "Primeiramente informe os dados.");
			}else if(BancoDados.validarLogin(du.getNomeUsuario(), du.getSenha())) {
				cl.gerarLog("usuario : "+du.getNomeUsuario()+".", "logs/logUsuario.txt", true);
				new AreaPrincipal(du.getNomeUsuario()).setVisible(true);
				login.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "Dados invalidos, verifique as informacoes");
			}
		}else if("sobre".equals(ae.getActionCommand())) {
			cl.gerarLog("Menu Item : Sobre.", "logs/logUsuario.txt",true);
			JOptionPane.showMessageDialog(null, "Software para o controle de processos clinicos, desenvolvido por Daniel Limas Alexandre,\npara atender os requisitos da disciplina de topicos especiais I,\nimpostos pelo professor Gilberto Vieira.");
		}else if("cadastrarConta".equals(ae.getActionCommand())) {
			cl.gerarLog("Tentativa de registro de nova conta.", "logs/logUsuario.txt",true);
			new CadastroContaIP().setVisible(true);
		}else if("cadastrarContaIP".equals(ae.getActionCommand())) {
			try{
			DadosConta dConta = cadconpanel.getDadosConta();
			DadosConta dadosc = new DadosConta();
			dadosc.setNome(dConta.getNome());
			dadosc.setNomeUsuario(dConta.getNomeUsuario());
			dadosc.setSenha(dConta.getSenha());
			dadosc.setEmail(dConta.getEmail());
			dadosc.setCpf(dConta.getCpf());
			dadosc.setCelular(dConta.getCelular());
			if(!dadosc.getNome().toString().trim().equals("")) {
			if(!dadosc.getNomeUsuario().toString().trim().equals("")) {
			if(!dadosc.getSenha().toString().trim().equals("")) {
			if(dConta.getSenha().equals(dConta.getConfirSenha())) {
			if(cadconpanel.verificarEmail().equals("autenticado")) {
				if (!cadconpanel.verificaCPF().equals("autenticado")) {
					if (!cadconpanel.verificaCelular().equals("autenticado")) {
						BancoDados.inserirContas(dadosc);
						cadconpanel.setaTexto();
						cadconpanel.dispose();
						cl.gerarLog("Conta registrada com sucesso.", "logs/logUsuario.txt", true);
						JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso.");
					}else{
						throw new Exceptions("Celular invalido");
					}
				}else{
					throw new Exceptions("CPF invalido");
				}
				}else{
				throw new Exceptions("E-mail invalido");
			}
			}else {
				throw new Exceptions("As senha nao correspondem entre si.");
			}
			}else{
				throw new Exceptions("Senha nao informada.");
			}
			}else {
				throw new Exceptions("Nome de usuario nao informado.");
			}
			}else {
				throw new Exceptions("Nome nao informado.");
			}
		}catch(Exceptions e){
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"ERRO", JOptionPane.ERROR_MESSAGE);
		}
		}else if("cadastrarCliente".equals(ae.getActionCommand())) {
			cl.gerarLog("Menu Item : cadastrar paciente.", "logs/logUsuario.txt",true);
			ap.setarInternalPaneCliente();
			ap.setPosicaoCliente();
		}else if("cadastrarClienteIP".equals(ae.getActionCommand())) {
			try{
				DadosCliente dCliente = cadclipanel.getDadosCliente();
				DadosCliente dc = new DadosCliente();
			    dc.setNome(dCliente.getNome());
			    dc.setSexo(dCliente.getSexo());
			    dc.setUf(dCliente.getUf());
			    dc.setFone(dCliente.getFone());
			    dc.setCidade(dCliente.getCidade());
			    dc.setRua(dCliente.getRua());
			    dc.setEmail(dCliente.getEmail());
			    dc.setCpf(dCliente.getCpf());
				if(!dc.getNome().toString().trim().equals("")){
					if (!cadclipanel.verificaSexo().equals("autenticado")){
						if (!cadclipanel.verificaUF().equals("autenticado")) {
							if (!cadclipanel.verificaFone().equals("autenticado")) {
								if (!dc.getCidade().toString().trim().equals("")) {
									if (!dc.getRua().toString().trim().equals("")) {
										if (cadclipanel.verificarEmail().equals("autenticado")){
											if (!cadclipanel.verificaCPF().equals("autenticado")) {
												BancoDados.inserirDados(dc);
												cadclipanel.setaTexto();
												cadclipanel.dispose();
												cl.gerarLog("Cadastrar paciente : paciente cadastrado com sucesso.", "logs/logUsuario.txt",true);
												JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso.");
											}else{
												throw new Exceptions("Verifique o CPF");
											}
										}else {
											throw new Exceptions("Verifique o E-Mail");
										}
									}else{
										throw new Exceptions("Verifique a Rua");
									}
								}else{
									throw new Exceptions("Verifique a Cidade");
								}
							}else{
								throw new Exceptions("Verifique o Celular");
							}
						}else{
							throw new Exceptions("Verifique o Estado");
						}
					}else{
						throw new Exceptions("Verifique o Sexo");
					}
				}else{
					throw new Exceptions("Verifique o nome");
				}
			}catch(Exceptions e){
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}else if("agendarproce".equals(ae.getActionCommand())) {
			try{
			DadosAgendar dAgendar = ac.getDadosClienteAgendar();
			DadosAgendar da = new DadosAgendar();
			da.setNome(dAgendar.getNome());
			da.setData(dAgendar.getData());
			da.setHorario(dAgendar.getHorario());
			da.setProce(dAgendar.getProce());
			da.setValor(dAgendar.getValor());
			da.setObs(dAgendar.getObs());
			if(!da.getNome().toString().trim().equals("")) {
			if(!ac.verificaData().equals("autenticado")) {
			if(!ac.verificaHorario().equals("autenticado")) {
			if (!ac.verificaProce().equals("autenticado")) {
				if (!da.getValor().toString().trim().equals("")) {
					BancoDados.AgendarProce(da);
					cl.gerarLog("Agendar Procedimento : procedimento agendado com sucesso."+dAgendar.getProce(), "logs/logUsuario.txt", true);
					JOptionPane.showMessageDialog(null, dAgendar.getProce() + " agendada para o(a)\r\nPaciente: " + dAgendar.getNome() + "\r\nData: " + dAgendar.getData() + "\r\nHorario: " + dAgendar.getHorario() + "\r\nDescricao: " + dAgendar.getObs() + "\r\nValor: " + dAgendar.getValor());
					ac.setaTexto();
					ac.dispose();
				}else{
					throw new Exceptions("Nenhum valor informado.");
				}
			}else {
				throw new Exceptions("Verifique o Procedimento");
			}
			}else{
				throw new Exceptions("Verifique o horario");			}
			}else{
				throw new Exceptions("Verifique a data");			}
			}else {
				throw new Exceptions("Verifique o nome");
			}
			}catch(Exceptions e){
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}else if("agendarConsultaip".equals(ae.getActionCommand())) {
			cl.gerarLog("Menu Item : Agendar Procedimento.", "logs/logUsuario.txt",true);
			ap.setarAgendaConsulta();
			ap.setPosicaoConsulta();
		}else if("procurar".equals(ae.getActionCommand())) {
			//cl.gerarLog("Procurando paciente no banco.", "logs/logUsuario.txt",true);
			ac.modelo.setNumRows(0);
			ac.carregarTabela();
		}else if("consulAgendadas".equals(ae.getActionCommand())) {
			cl.gerarLog("Menu Item : Procedimentos Agendados.", "logs/logUsuario.txt",true);
			ap.setarInternalPaneConsulAgendadas();
			ap.setPosicaoConsulAgendadas();
		}else if("cancelProce".equals(ae.getActionCommand())) {
			cl.gerarLog("Menu Item : Cancelar Procedimentos.", "logs/logUsuario.txt",true);
			ap.setarInternalPaneCancelar();
			ap.setPosicaoCancelar();
		}else if("receita".equals(ae.getActionCommand())) {
			cl.gerarLog("Menu Item : Gerar Receitas.", "logs/logUsuario.txt",true);
			ap.setarInternalPaneReceita();
			ap.setPosicaoReceita();
		}else if("gerarReceita".equals(ae.getActionCommand())) {
			try {
			DadosReceita dReceita = r.getDadosReceita();
			DadosReceita dr = new DadosReceita();
			dr.setPaciente(dReceita.getPaciente());
			dr.setRemedio(dReceita.getRemedio());
			dr.setPoso(dReceita.getPoso());
			dr.setUnidade(dReceita.getUnidade());
			dr.setUso(dReceita.getUso());
			dr.setQtd(dReceita.getQtd());
			//if(!dr.getPaciente().toString().trim().equals("")) {
			if(!dr.getRemedio().toString().trim().equals("")) {
			if(!r.verificaPoso().equals("autenticado")) {
			if(!r.verificaUni().equals("autenticado")) {
			if(!r.verificaUso().equals("autenticado")) {
			if(!(r.verificaQtd() == 0)) {
			if(!r.verificaData().equals("autenticado")) {
				cl.gerarLog("Gerar Receitas : Receita Gerada.", "logs/receita.doc",false);
				String msg ="                            RECEITUARIO                             \r\n" 
						+ "___________________________________________________________________\r\n"
						+ "                                                                   \r\n"
						+ "Paciente: "+dReceita.getPaciente()+"              Profissional: "+cl.lerLog()+"\r\n"
						+ "-------------------------------------------------------------------\r\n"
						+ "            Remédio: "+dReceita.getRemedio()+"          Unidade: "+dReceita.getUnidade()+"\r\n"
						+ "                                                                               \r\n"
						+ "            Posologia: "+dReceita.getPoso()+"                            \r\n"
						+ "                                                                   \r\n"
						+ "            Modo de uso: "+dReceita.getUso()+"         Quantidade: "+dReceita.getQtd()+"         \r\n"
						+ "-------------------------------------------------------------------\r\n"
						+ "                                                                   \r\n"
						+ "Assinatura médica:___________________             Data: "+dReceita.getData()+".\r\n"
						   + "___________________________________________________________________";
				
				cl.gerarLog(msg, "logs/receita.doc",false);
				try {
					java.awt.Desktop.getDesktop().open(new File("logs\\receita.doc"));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Arquivo word esta aberto.");
				}
				r.dispose();
				r.setaTexto();
			}else {
				throw new Exceptions("Data nao informada.");
			}
			}else {
				throw new Exceptions("Deve ser informado pelo menos 1 para quantidade.");
			}
			}else {
				throw new Exceptions("Uso nao informado.");
			}
			}else {
				throw new Exceptions("Unidade nao informada.");
			}			
			}else {
				throw new Exceptions("Posologia nao informada.");
			}	
			}else {
				throw new Exceptions("Nenhum remedio informado.");
			}
			/*}else {
				throw new Exceptions("Nenhum paciente selecionado.");
			}*/
			}catch(Exceptions e){
				JOptionPane.showMessageDialog(null, e.getMessage(),
						"ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}else if("excluir".equals(ae.getActionCommand())) {
			cl.gerarLog("Menu Item : Procedimento cancelado com sucesso.", "logs/logUsuario.txt",true);
			cancelProce.excluir();
			cancelProce.modeloProcedimentos.setNumRows(0);
			cancelProce.carregarTabelaConsultas();
		}
	}
	
}
