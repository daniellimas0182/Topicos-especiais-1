package internalPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import acoes.ImplementaAcoes;
import classesDados.DadosConta;
import javax.swing.JFormattedTextField;

public class CadastroContaIP extends JFrame {
	private ImplementaAcoes listener = new ImplementaAcoes(null,null,this,null,null,null,null,null);
	private JTextField textFieldUser;
	private JPasswordField passwordFieldSenha;
	private JPasswordField passwordFieldConfirma;
	private JTextField textFieldEMail;
	private JFormattedTextField formattedTextFieldCPF, formattedTextFieldFone;
	private JTextField textFieldNome;
	
	public CadastroContaIP() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("Cadastrar Contas");
		setBounds(100, 100, 378, 362);
		setLocationRelativeTo(null);

		JLabel lblUsario = new JLabel("Usuario:");
		lblUsario.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textFieldUser = new JTextField();
		textFieldUser.setColumns(10);
		textFieldUser.requestFocus();
		textFieldUser.setToolTipText("Informe o nome de Usuario que deseja neste campo.");
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setToolTipText("Informe sua senha neste campo.");
		passwordFieldConfirma = new JPasswordField();
		passwordFieldConfirma.setToolTipText("Confirme sua senha neste campo.");
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha:");
		
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(listener);
		btnCadastrar.setActionCommand("cadastrarContaIP");
		
		JLabel lblCadastrarUmaNova = new JLabel("Cadastrar uma nova conta. ");
		lblCadastrarUmaNova.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textFieldEMail = new JTextField();
		textFieldEMail.setColumns(10);
		textFieldEMail.setToolTipText("Informe seu E-Mail neste campo.");
		JLabel label = new JLabel("*");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setToolTipText("Este campo e obrigatorio");
			}
		});
		label.setForeground(Color.RED);
		
		JLabel label_1 = new JLabel("*");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_1.setToolTipText("Este campo e obrigatorio");
			}
		});
		label_1.setForeground(Color.RED);
		
		JLabel label_2 = new JLabel("*");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_2.setToolTipText("Este campo e obrigatorio");
			}
		});
		label_2.setForeground(Color.RED);
		
		JLabel label_3 = new JLabel("*");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_3.setToolTipText("Este campo e obrigatorio");
			}
		});
		label_3.setForeground(Color.RED);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		formattedTextFieldFone = new JFormattedTextField();
		try {
			formattedTextFieldFone.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(##)#####-####")));
		}catch(ParseException e) {e.printStackTrace();}
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		formattedTextFieldCPF = new JFormattedTextField();
		try {
			formattedTextFieldCPF.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
		}catch(ParseException e) {e.printStackTrace();}
		
		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_4.setToolTipText("Este campo e obrigatorio");
			}
		});
		
		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_5.setToolTipText("Este campo e obrigatorio");
			}
		});
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(75)
					.addComponent(lblCadastrarUmaNova)
					.addContainerGap(84, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCadastrar)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(32)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCelular)
										.addComponent(lblCpf)
										.addComponent(lblEmail)))
								.addComponent(lblConfirmarSenha)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(27)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsario)
										.addComponent(lblSenha)
										.addComponent(lblNome))))
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(passwordFieldSenha, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(textFieldUser, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(formattedTextFieldFone, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(textFieldEMail, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(passwordFieldConfirma, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(formattedTextFieldCPF, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(textFieldNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_1)
						.addComponent(label_2)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(label_5))
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblCadastrarUmaNova)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(29))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome))
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(textFieldUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2, Alignment.TRAILING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(5))))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblUsario)
							.addGap(18)
							.addComponent(lblSenha)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(15)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_3)
										.addComponent(lblConfirmarSenha)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(6)
									.addComponent(passwordFieldConfirma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
									.addComponent(lblEmail))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldEMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(label_4)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_5)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(formattedTextFieldCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCpf)))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCelular)
						.addComponent(formattedTextFieldFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCadastrar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	public DadosConta getDadosConta(){
		return new DadosConta(0, textFieldNome.getText(), textFieldUser.getText(), passwordFieldSenha.getText(), passwordFieldConfirma.getText(), textFieldEMail.getText(), formattedTextFieldCPF.getText() , formattedTextFieldFone.getText());
    }
	public String verificarEmail() {
		if ((textFieldEMail.getText().contains("@")) && (textFieldEMail.getText().contains(".")) && (!textFieldEMail.getText().contains(" "))) {
			String usuario = new String(textFieldEMail.getText().substring(0, textFieldEMail.getText().lastIndexOf('@')));

			String dominio = new String(textFieldEMail.getText().substring(textFieldEMail.getText().lastIndexOf('@') + 1, textFieldEMail.getText().length()));

			if ((usuario.length() >=1) && (!usuario.contains("@")) && (dominio.contains(".")) && (!dominio.contains("@")) && (dominio.indexOf(".") >= 1) && (dominio.lastIndexOf(".") < dominio.length() - 1)) {
			            	return "autenticado";
			            } else {
			            	textFieldEMail.requestFocus();
			            	return "naoAutenticado";
			            }
			        } else {
			        	textFieldEMail.requestFocus();
			        	return "naoAutenticado";
			    }
	}
	public String verificaCelular() {return formattedTextFieldFone.getText().equals("(  )     -    ") ? "autenticado" : "naoAutenticado";}
	public String verificaCPF() {return formattedTextFieldCPF.getText().equals("   .   .   -  ") ? "autenticado" : "naoAutenticado";}
	public void setaTexto() {
		textFieldEMail.setText("");
		textFieldNome.setText("");
		textFieldUser.setText("");
		formattedTextFieldCPF.setText("");
		formattedTextFieldFone.setText("");
		passwordFieldConfirma.setText("");
		passwordFieldSenha.setText("");
	}

}
