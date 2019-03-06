package logsusuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ControleLogs {
	public ControleLogs() {}
	public void gerarLog(String msg, String caminho, boolean save) {
		
		try {
			FileWriter fw = new FileWriter(caminho, save);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(msg+"\r\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String lerLog() {
		FileReader fr;
		BufferedReader br;
		String UltimoUser = "";
		try {
			fr = new FileReader("logs/logs.txt");
			br = new BufferedReader(fr);
			while(br.ready()) {
				UltimoUser = br.readLine();
			}
		} catch (IOException e) {
			
		}
		return UltimoUser;
	}
}
