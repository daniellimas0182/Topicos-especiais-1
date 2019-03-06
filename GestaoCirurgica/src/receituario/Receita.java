package receituario;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import Banco.BancoDados;
import acoes.ImplementaAcoes;
import classesDados.DadosCliente;
import classesDados.DadosReceita;
import logsusuarios.ControleLogs;
import javax.swing.JSpinner;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Receita extends JInternalFrame {
	private ImplementaAcoes listener = new ImplementaAcoes(null,null,null,null,null,null,null,this);
	private JTable table;
	public DefaultTableModel modelo = new DefaultTableModel();
	private String select;
	private JComboBox comboBoxPoso, comboBoxUnidade, comboBoxUso;
	private JTextField textFieldRemedio;
	private JTextField textFieldProfi;
	private String comboBoxUniG, comboBoxPosoG, comboBoxUsoG;
	private JFormattedTextField formattedTextField;
	private JSpinner spinner;
	private JTextField textFieldFiltro;
	public Receita() {
		setFrameIcon(new ImageIcon("img\\pilula.png"));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Gerar Receita");
		setClosable(true);
		setBounds(100, 100, 578, 283);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblRemedio = new JLabel("Remedio:");
		lblRemedio.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textFieldRemedio = new JTextField();
		textFieldRemedio.setColumns(10);
		
		comboBoxPoso = new JComboBox();
		comboBoxPoso.setBackground(Color.WHITE);
		comboBoxPoso.setModel(new DefaultComboBoxModel(new String[] {"", "01 comprimido por dia.", "01 comprimido a cada 12 horas.", "02 comprimido a cada 12 horas.", "03 comprimido a cada 12 horas.", "01 comprimido antes de cada refeicao."}));
		comboBoxPoso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			comboBoxPosoG = (String) comboBoxPoso.getSelectedItem();
			}
		});
		
		JLabel lblPoso = new JLabel("Posologia:");
		lblPoso.setToolTipText("Ramo da terapeutica que se ocupa da dosagem dos medicamentos.");
		lblPoso.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblProfissional = new JLabel("Profissional:");
		lblProfissional.setFont(new Font("Tahoma", Font.BOLD, 12));
		ControleLogs cl = new ControleLogs();
		textFieldProfi = new JTextField(cl.lerLog());
		textFieldProfi.setEditable(false);
		textFieldProfi.setColumns(10);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		formattedTextField = new JFormattedTextField();
		try {
			formattedTextField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		JLabel lblUso = new JLabel("Uso:");
		lblUso.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		comboBoxUso = new JComboBox();
		comboBoxUso.setBackground(Color.WHITE);
		comboBoxUso.setModel(new DefaultComboBoxModel(new String[] {"", "Uso Interno.", "Uso Externo.", "Uso Retal.", "Uso Intra-nasal."}));
		comboBoxUso.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			comboBoxUsoG = (String) comboBoxUso.getSelectedItem();
			}
		});
		
		
		JLabel lblUnidade = new JLabel("Unidade:");
		lblUnidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		comboBoxUnidade = new JComboBox();
		comboBoxUnidade.setBackground(Color.WHITE);
		comboBoxUnidade.setModel(new DefaultComboBoxModel(new String[] {"", "50 mg.", "100 mg.", "150 mg.", "200 mg.", "250 mg.", "300 mg."}));
		comboBoxUnidade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			comboBoxUniG = (String) comboBoxUnidade.getSelectedItem();
			}
		});
		
		JButton btnGerarReceita = new JButton("Gerar Receita");
		btnGerarReceita.setBackground(Color.WHITE);
		btnGerarReceita.addActionListener(listener);
		btnGerarReceita.setActionCommand("gerarReceita");
		
		spinner = new JSpinner();
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		textFieldFiltro = new JTextField();
		textFieldFiltro.setColumns(10);
		textFieldFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					Vector cabecalho = new Vector(); 
					cabecalho.add("Pacientes");
					if(!textFieldFiltro.getText().equals("")) {
						 DefaultTableModel nv = new DefaultTableModel(BancoDados.Filtro(textFieldFiltro.getText().toLowerCase()),cabecalho);
						 table.setModel(nv);
					}else {
						table.setModel(modelo);
					}
					}catch(Exception ex) {
						JOptionPane.showInputDialog(this, "Erro: " + ex.getMessage());
					}
					}
			}
		);
		
		JLabel lblFiltro = new JLabel("");
		lblFiltro.setIcon(new ImageIcon("C:\\Users\\Cassio-PC\\Desktop\\Diversos\\BancoDados\\GestaoCirurgica\\img\\pesquisa.png"));
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 12));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblFiltro)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textFieldFiltro, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(16)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblRemedio)
										.addComponent(lblPoso)
										.addComponent(lblUnidade)))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblUso)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblProfissional)
										.addComponent(lblQuantidade))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBoxPoso, 0, 232, Short.MAX_VALUE)
								.addComponent(textFieldRemedio, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblData)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBoxUso, 0, 232, Short.MAX_VALUE)
								.addComponent(comboBoxUnidade, 0, 232, Short.MAX_VALUE)
								.addComponent(textFieldProfi, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
						.addComponent(btnGerarReceita))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRemedio)
								.addComponent(textFieldRemedio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPoso)
								.addComponent(comboBoxPoso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUnidade)
								.addComponent(comboBoxUnidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxUso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUso))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblQuantidade)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblData))
								.addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblProfissional)
								.addComponent(textFieldProfi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnGerarReceita)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldFiltro, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblFiltro)))
					.addGap(52))
		);
		
		table = new JTable(modelo);
		table.setBackground(Color.WHITE);
		modelo.addColumn("Pacientes");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				select = (String) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				System.out.println(select);
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}
	public DadosReceita getDadosReceita() {
		return new DadosReceita(select, textFieldRemedio.getText(),comboBoxPosoG ,comboBoxUniG, formattedTextField.getText(), comboBoxUsoG, (int)spinner.getValue());
	}
	public void carregarTabelaConsultas() {
		for(DadosCliente dc: BancoDados.consultas()) {
			modelo.addRow(new Object[]{
				dc.getNome()
			});
		}
	}
	public String verificaPoso() {return comboBoxPoso.getSelectedItem().equals("") ? "autenticado" : "naoAutenticado";}
	public String verificaUni() {return comboBoxUnidade.getSelectedItem().equals("") ? "autenticado" : "naoAutenticado";}
	public String verificaUso() {return comboBoxUso.getSelectedItem().equals("") ? "autenticado" : "naoAutenticado";}
	public int verificaQtd() {return (int) spinner.getValue();}
	public String verificaData() { return formattedTextField.getText().equals("  /  /    ") ? "autenticado": "naoAutenticado";}
	public void setaTexto() {
		textFieldRemedio.setText("");
		formattedTextField.setText("");
		comboBoxPoso.setSelectedIndex(0);
		comboBoxUnidade.setSelectedIndex(0);
		comboBoxUso.setSelectedIndex(0);
		spinner.setValue(0);
	}


}
