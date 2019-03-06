package internalPanel;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import Banco.BancoDados;
import acoes.ImplementaAcoes;
import classesDados.DadosAgendar;
import classesDados.DadosCliente;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class AgendarProcedimento extends JInternalFrame {
	private ImplementaAcoes listener = new ImplementaAcoes(null,null,null,null,this,null,null,null);
	private DefaultListModel<String> lista = new DefaultListModel<String>();
	private JTextField textFieldNomeCliente;
	private JFormattedTextField formattedTextFieldData, formattedTextFieldHorario;
	private JTextArea textArea;
	private JTable table;
	public DefaultTableModel modelo = new DefaultTableModel();
	private JTextField textFieldValor;
	private JComboBox comboBox;
	private String comboBoxG;
	private JTextField textFieldFiltro;
	public AgendarProcedimento() {
		setFrameIcon(new ImageIcon("img\\agendar.png"));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Agendar Procedimento");
		setClosable(true);
		setBounds(100, 100, 539, 416);
		JLabel lblProcurarCliente = new JLabel("Nome do Cliente:");
		lblProcurarCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFieldNomeCliente = new JTextField();
		textFieldNomeCliente.setEditable(false);
		textFieldNomeCliente.setColumns(10);
		textFieldNomeCliente.setToolTipText("Informe o nome do cliente neste campo.");
		textFieldNomeCliente.requestFocus();
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblHorario = new JLabel("Horario:");
		lblHorario.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnAgendar = new JButton("Agendar");
		btnAgendar.setBackground(Color.WHITE);
		btnAgendar.addActionListener(listener);
		btnAgendar.setActionCommand("agendarproce");
		btnAgendar.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JLabel lblAgendarNovaConsulta = new JLabel("Agendar novo procedimento");
		lblAgendarNovaConsulta.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		formattedTextFieldData = new JFormattedTextField();
		formattedTextFieldData.setToolTipText("Informe a data da consulta neste campo.");
		try {
			formattedTextFieldData.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		formattedTextFieldHorario = new JFormattedTextField();
		formattedTextFieldHorario.setToolTipText("Informe o horario da consulta neste campo.");
		try {
			formattedTextFieldHorario.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##:##")));
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		JLabel lblObservaes = new JLabel("Observacoes:");
		lblObservaes.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textArea = new JTextArea();
		textArea.setToolTipText("Observacoes sobre a consulta.");
		JScrollPane jp = new JScrollPane(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblProcedimento = new JLabel("Procedimento:");
		lblProcedimento.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFieldValor = new JTextField();
		textFieldValor.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Consulta", "Cirurgia"}));
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			comboBoxG = (String) comboBox.getSelectedItem();
			}
		});
		
		JLabel lblFiltro = new JLabel("");
		lblFiltro.setIcon(new ImageIcon("C:\\Users\\Cassio-PC\\Desktop\\Diversos\\BancoDados\\GestaoCirurgica\\img\\pesquisa.png"));
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 14));
		
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
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(156)
							.addComponent(lblAgendarNovaConsulta))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblData)
									.addComponent(lblProcurarCliente, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblProcedimento)
									.addComponent(lblObservaes))
								.addComponent(lblFiltro))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldFiltro, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
								.addComponent(jp, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
								.addComponent(textFieldNomeCliente, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox, 0, 94, Short.MAX_VALUE)
										.addComponent(formattedTextFieldData, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
									.addGap(49)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblHorario)
										.addComponent(lblValor))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(formattedTextFieldHorario)
										.addComponent(textFieldValor, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))))
					.addGap(112))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(242, Short.MAX_VALUE)
					.addComponent(btnAgendar)
					.addGap(196))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAgendarNovaConsulta)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldNomeCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProcurarCliente))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(formattedTextFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(formattedTextFieldHorario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHorario))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProcedimento)
						.addComponent(lblValor)
						.addComponent(textFieldValor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblObservaes)
						.addComponent(jp, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldFiltro, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFiltro))
					.addGap(16)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAgendar)
					.addGap(17))
		);
		
		table = new JTable(modelo);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textFieldNomeCliente.setText((String) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		modelo.addColumn("Pacientes");
	}
	public DadosAgendar getDadosClienteAgendar(){
		return new DadosAgendar(0,textFieldNomeCliente.getText(), formattedTextFieldData.getText(), formattedTextFieldHorario.getText(), comboBoxG, textFieldValor.getText(), textArea.getText());
    }
	public String verificaData() { return formattedTextFieldData.getText().equals("  /  /    ") ? "autenticado": "naoAutenticado";}
	public String verificaHorario() { return formattedTextFieldHorario.getText().equals("  :  ") ? "autenticado": "naoAutenticado";}
	public String verificaProce(){ return comboBox.getSelectedItem().equals("") ? "autenticado" : "naoAutenticado";}
	
	public void carregarTabela() {
		for(DadosCliente d: BancoDados.consultas()) {
			modelo.addRow(new Object[]{
				d.getNome()
			});
		}
	}
	public void setaTexto() {
		textFieldNomeCliente.setText("");
		formattedTextFieldData.setText("");
		formattedTextFieldHorario.setText("");
		comboBox.setSelectedIndex(0);
		textFieldValor.setText("");
		textArea.setText("");
	}
}
