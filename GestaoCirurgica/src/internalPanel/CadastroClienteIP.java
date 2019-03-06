package internalPanel;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import acoes.ImplementaAcoes;
import classesDados.DadosCliente;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;

public class CadastroClienteIP extends JInternalFrame {
	private ImplementaAcoes listener = new ImplementaAcoes(null,null,null,this,null,null,null,null);
	private JTextField textFieldNome;
	private JTextField textFieldCidade;
	private JTextField textFieldRua;
	private JTextField textFieldEMail;
	private JComboBox comboBoxSexo;
	private JComboBox comboBoxUF;
	private JFormattedTextField formattedTextFieldCPF, formattedTextFieldFone;
	String comboBoxSexoG, comboBoxUFG;
	public CadastroClienteIP() {
		setFrameIcon(new ImageIcon("img\\adduser.png"));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Cadastrar Paciente");
		setClosable(true);
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBounds(100, 100, 335, 374);
		
		JLabel lblCadastrarNovoCliente = new JLabel("Cadastrar novo paciente.");
		lblCadastrarNovoCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setToolTipText("Informe o nome do cliente neste campo.");
		textFieldNome.requestFocus();
		
		textFieldCidade = new JTextField();
		textFieldCidade.setColumns(10);
		textFieldCidade.setToolTipText("Informe a cidade referente ao cliente neste campo.");
		
		textFieldRua = new JTextField();
		textFieldRua.setColumns(10);
		textFieldRua.setToolTipText("Informe a rua referente ao cliente neste campo.");

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(listener);
		btnCadastrar.setActionCommand("cadastrarClienteIP");
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textFieldEMail = new JTextField();
		textFieldEMail.setColumns(10);
		textFieldEMail.setToolTipText("Informe o E-Mail do cliente neste campo.");

		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		comboBoxSexo = new JComboBox();
		comboBoxSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBoxSexo.setMaximumRowCount(3);
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"","Masculino", "Feminino"}));
		comboBoxSexo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			comboBoxSexoG = (String) comboBoxSexo.getSelectedItem();
			}
		});
		comboBoxSexo.setToolTipText("Selecione o sexo referente ao cliente neste campo.");
		formattedTextFieldCPF = new JFormattedTextField();
		formattedTextFieldCPF.setToolTipText("Informe o CPF referente ao cliente neste campo.");
		formattedTextFieldCPF.setForeground(Color.BLACK);
		try {
			formattedTextFieldCPF.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblUf = new JLabel("UF:");
		lblUf.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		comboBoxUF = new JComboBox();
		comboBoxUF.setModel(new DefaultComboBoxModel(new String[] {"","AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"}));
		comboBoxUF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			comboBoxUFG = (String) comboBoxUF.getSelectedItem();
			}
		});
		comboBoxUF.setToolTipText("Informe o estado referente ao cliente neste campo.");

		JLabel label_1 = new JLabel("*");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label_1.setToolTipText("Este campo e obrigatorio");
			}
		});
		label_1.setForeground(Color.RED);
		
		JLabel label = new JLabel("*");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setToolTipText("Este campo e obrigatorio");
			}
		});
		label.setForeground(Color.RED);
		
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
		
		JLabel label_4 = new JLabel("*");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_4.setToolTipText("Este campo e obrigatorio");
			}
		});
		label_4.setForeground(Color.RED);
		
		JLabel label_5 = new JLabel("*");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_5.setToolTipText("Este campo e obrigatorio");
			}
		});
		label_5.setForeground(Color.RED);
		
		JLabel label_6 = new JLabel("*");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_6.setToolTipText("Este campo e obrigatorio");
			}
		});
		label_6.setForeground(Color.RED);
		
		JLabel label_7 = new JLabel("*");
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_7.setToolTipText("Este campo e obrigatorio");
			}
		});
		label_7.setForeground(Color.RED);
		
		formattedTextFieldFone = new JFormattedTextField();
		formattedTextFieldFone.setToolTipText("Informe o celular referente ao cliente neste campo.");
		try {
			formattedTextFieldFone.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(##)#####-####")));
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCadastrar, Alignment.TRAILING)
						.addComponent(lblEmail)
						.addComponent(lblSexo)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCelular)
								.addComponent(lblCidade)
								.addComponent(lblRua)
								.addComponent(lblCpf)
								.addComponent(lblNome))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(textFieldCidade, 198, 211, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(comboBoxSexo, 0, 125, Short.MAX_VALUE)
									.addGap(2)
									.addComponent(label_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblUf)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBoxUF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(formattedTextFieldFone, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(textFieldRua, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(textFieldEMail, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(formattedTextFieldCPF, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
								.addComponent(lblCadastrarNovoCliente, Alignment.LEADING))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(label_5)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 6, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7))
					.addGap(33))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCadastrarNovoCliente)
					.addGap(14)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(lblSexo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxSexo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxUF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUf)
								.addComponent(label)
								.addComponent(label_2))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCelular)
						.addComponent(label_3)
						.addComponent(formattedTextFieldFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCidade)
						.addComponent(textFieldCidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRua)
						.addComponent(textFieldRua, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textFieldEMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(formattedTextFieldCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCpf)
						.addComponent(label_7))
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(btnCadastrar)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
	}
	public DadosCliente getDadosCliente(){
		return new DadosCliente(textFieldNome.getText(), comboBoxSexoG, formattedTextFieldFone.getText(), textFieldCidade.getText(), textFieldRua.getText(), textFieldEMail.getText(), formattedTextFieldCPF.getText(), comboBoxUFG);
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
	public void setaTexto() {
		textFieldNome.setText("");
		textFieldCidade.setText("");
		textFieldEMail.setText("");
		textFieldRua.setText("");
		comboBoxSexo.setSelectedIndex(0);
		comboBoxUF.setSelectedIndex(0);
		formattedTextFieldCPF.setText("");
		formattedTextFieldFone.setText("");
	}
	public String verificaUF() {return comboBoxUF.getSelectedItem().equals("") ? "autenticado" : "naoAutenticado";}
	public String verificaSexo() { return comboBoxSexo.getSelectedItem().equals("") ? "autenticado" : "naoAutenticado";}
	public String verificaFone() { return formattedTextFieldFone.getText().equals("(  )     -    ") ? "autenticado" : "naoAutenticado";}
	public String verificaCPF() { return formattedTextFieldCPF.getText().equals("   .   .   -  ") ? "autenticado" : "naoAutenticado";}
}
