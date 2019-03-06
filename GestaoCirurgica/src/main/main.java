package main;

import java.util.Calendar;

import Banco.BancoDados;
import areaLogin.Login;
import logsusuarios.ControleLogs;

public class main {
	public static void main(String[] args) {
		ControleLogs cl = new ControleLogs();
		Calendar c = Calendar.getInstance();
		cl.gerarLog("Inicialização do software, data/horario: "+c.getTime().toString()+".", "logs/logUsuario.txt", true);
		new Login().setVisible(true); BancoDados.getConexao();
		}
}
