package internalPanel;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import Banco.BancoDados;
import acoes.ImplementaAcoes;
import classesDados.DadosAgendar;
import classesDados.DadosCliente;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;

public class CancelarProcedimento extends JInternalFrame {
	private ImplementaAcoes listener = new ImplementaAcoes(null,null,null,null,null,null,this,null);
	private JTable table;
	public DefaultTableModel modeloProcedimentos = new DefaultTableModel();
	String n;
	private JTextField textFieldFiltro;
	public CancelarProcedimento() {
		setFrameIcon(new ImageIcon("img\\cancelar.png"));
		getContentPane().setBackground(Color.WHITE);
		setClosable(true);
		setTitle("Cancelar Procedimento");
		setBounds(100, 100, 492, 314);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(listener);
		btnCancelar.setActionCommand("excluir");
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setBackground(Color.WHITE);
		
		textFieldFiltro = new JTextField();
		textFieldFiltro.setColumns(10);
		textFieldFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
				Vector cabecalho = new Vector(); 
				cabecalho.add("Paciente");
				cabecalho.add("Horario");
				cabecalho.add("Procedimento");
				if(!textFieldFiltro.getText().equals("")) {
					 DefaultTableModel nv = new DefaultTableModel(BancoDados.FiltroCancel(textFieldFiltro.getText().toLowerCase()),cabecalho);
					 table.setModel(nv);
				}else {
					table.setModel(modeloProcedimentos);
				}
				}catch(Exception ex) {
					JOptionPane.showInputDialog(this, "Erro: " + ex.getMessage());
				}
				}
		});
		
		JLabel lblFiltro = new JLabel("");
		lblFiltro.setIcon(new ImageIcon("C:\\Users\\Cassio-PC\\Desktop\\Diversos\\BancoDados\\GestaoCirurgica\\img\\pesquisa.png"));
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFiltro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldFiltro, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
							.addComponent(btnCancelar)))
					.addGap(23))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFiltro)
						.addComponent(textFieldFiltro, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(30))
		);
		
		table = new JTable(modeloProcedimentos);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				n = (String) table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				System.out.println(procurarId());
				
			}
		});
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		modeloProcedimentos.addColumn("Paciente");
		modeloProcedimentos.addColumn("Horario");
		modeloProcedimentos.addColumn("Procedimento");
	}
	public void carregarTabelaConsultas() {
		for(DadosAgendar da: BancoDados.ListarConsultas()) {
			modeloProcedimentos.addRow(new Object[]{
				da.getNome(),
				da.getHorario(),
				da.getProce()
			});
		}
	}
	public int procurarId() {
		int a = 0;
		for(DadosCliente dc: BancoDados.consultaId(n)) {
				a = dc.getId();
		}
		return a;
	}
	public void excluir() {
		BancoDados.excluirProce(procurarId());
	}
}
