package areaLogin;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import Banco.BancoDados;
import acoes.ImplementaAcoes;
import classesDados.DadosConta;
import classesDados.DadosUsuarios;
import logsusuarios.ControleLogs;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class Login extends JFrame {
	private ImplementaAcoes listener = new ImplementaAcoes(this,null,null,null,null,null,null,null);
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public Login() {
		setResizable(false);
		ControleLogs cl = new ControleLogs();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent arg0) {
				Calendar c = Calendar.getInstance();
				cl.gerarLog("Fim da execuçao do software : "+c.getTime().toString()+".", "logs/logUsuario.txt", true);
				cl.gerarLog("===========================================================", "logs/logUsuario.txt", true);
				BancoDados.fecharConexao();
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 371);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsuario = new JLabel("");
		lblUsuario.setIcon(new ImageIcon("img\\usuario.png"));
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textField = new JTextField();
		textField.setColumns(10);
		JLabel lblSenha = new JLabel("");
		lblSenha.setIcon(new ImageIcon("img\\senha.png"));
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textField.setText(cl.lerLog());
		passwordField = new JPasswordField();
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setForeground(new Color(0, 0, 0));
		btnEntrar.setIcon(new ImageIcon("img\\entrar.png"));
		btnEntrar.setBackground(Color.WHITE);
		btnEntrar.addActionListener(listener);
		btnEntrar.setActionCommand("entrar");
		
		JButton btnCriarConta = new JButton("Registro");
		btnCriarConta.setForeground(new Color(0, 0, 0));
		btnCriarConta.setIcon(new ImageIcon("img\\registro.png"));
		btnCriarConta.setBackground(Color.WHITE);
		btnCriarConta.addActionListener(listener);
		btnCriarConta.setActionCommand("cadastrarConta");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 102));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(99)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSenha)
						.addComponent(lblUsuario))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(passwordField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCriarConta, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
					.addGap(132))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuario))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnCriarConta)
								.addComponent(btnEntrar)))
						.addComponent(lblSenha))
					.addGap(49))
		);
		
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon("img\\acesso.png"));
		
		JLabel lblreaDeAcesso = new JLabel("\u00C1rea de Acesso");
		lblreaDeAcesso.setForeground(Color.WHITE);
		lblreaDeAcesso.setFont(new Font("Tahoma", Font.BOLD, 17));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(222)
							.addComponent(lbl1))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(180)
							.addComponent(lblreaDeAcesso, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(181, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblreaDeAcesso)
					.addContainerGap(33, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
	public DadosUsuarios getDadosLogin(){
        return new DadosUsuarios(textField.getText(), passwordField.getText());
    }
	public DadosConta getDadosConta() {
		return new DadosConta(0, null, textField.getText(), passwordField.getText(), null, null,null,null);
	}
}
