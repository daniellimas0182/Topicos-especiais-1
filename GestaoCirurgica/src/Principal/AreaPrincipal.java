package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Banco.BancoDados;
import acoes.ImplementaAcoes;
import areaLogin.Login;
import classesDados.DadosCliente;
import classesDados.DadosConta;
import internalPanel.AgendarProcedimento;
import internalPanel.CadastroClienteIP;
import internalPanel.CadastroContaIP;
import internalPanel.CancelarProcedimento;
import internalPanel.ProcedimentosAgendados;
import logsusuarios.ControleLogs;
import receituario.Receita;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AreaPrincipal extends JFrame {
	private ImplementaAcoes listener = new ImplementaAcoes(null,this,null,null,null,null,null,null);
	private CadastroClienteIP clienteInternal = new CadastroClienteIP();
	private AgendarProcedimento consultaInternal = new AgendarProcedimento();
	private ProcedimentosAgendados consulAgendadas = new ProcedimentosAgendados();
	private Receita r = new Receita();
	private CancelarProcedimento cancelProce = new CancelarProcedimento();
	private JPanel contentPane;
	private JMenuItem menuItemPaciente;
	private JDesktopPane desktopPane;
	public AreaPrincipal(String user) {
		ControleLogs cl = new ControleLogs();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent arg0) {
				Calendar c = Calendar.getInstance();
				java.util.Date date = c.getTime();
				cl.gerarLog("Fim da execuçao do software : "+c.getTime().toString()+".", "logs/logUsuario.txt", true);
				cl.gerarLog("===========================================================", "logs/logUsuario.txt",true);
				BancoDados.fecharConexao();
				dispose();
			}
		});
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 834, 552);
		setLocationRelativeTo(null);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuCadastro = new JMenu("Cadastro");
		menuBar.add(menuCadastro);
		
		menuItemPaciente = new JMenuItem("Paciente");
		menuCadastro.add(menuItemPaciente);
		menuItemPaciente.addActionListener(listener);
		menuItemPaciente.setActionCommand("cadastrarCliente");
		
		JMenu menuAgenda = new JMenu("Agenda");
		menuBar.add(menuAgenda);
		
		JMenuItem menuItemAgendarProce = new JMenuItem("Agendar Procedimento");
		menuAgenda.add(menuItemAgendarProce);
		menuItemAgendarProce.addActionListener(listener);
		menuItemAgendarProce.setActionCommand("agendarConsultaip");
		
		JMenuItem menuItemProceAgendados = new JMenuItem("Procedimentos Agendados");
		menuAgenda.add(menuItemProceAgendados);
		menuItemProceAgendados.addActionListener(listener);
		menuItemProceAgendados.setActionCommand("consulAgendadas");
		
		JMenuItem mntmGerarReceita = new JMenuItem("Gerar Receita");
		menuAgenda.add(mntmGerarReceita);
		mntmGerarReceita.addActionListener(listener);
		mntmGerarReceita.setActionCommand("receita");
		JMenuItem mntmCancelarProce = new JMenuItem("Cancelar Procedimento");
		menuAgenda.add(mntmCancelarProce);
		mntmCancelarProce.addActionListener(listener);
		mntmCancelarProce.setActionCommand("cancelProce");
		
		JMenu menuSobre = new JMenu("Sobre");
		menuBar.add(menuSobre);
		
		JMenuItem menuItemSobreOSistema = new JMenuItem("Sobre o sistema");
		menuSobre.add(menuItemSobreOSistema);
		menuItemSobreOSistema.addActionListener(listener);
		menuItemSobreOSistema.setActionCommand("sobre");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(51, 51, 102));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
		);
		
		JLabel lblControleDeGesto = new JLabel("CONTROLE DE CONSULTAS");
		lblControleDeGesto.setForeground(Color.WHITE);
		lblControleDeGesto.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblControleDeGesto.setBounds(272, 138, 402, 44);
		desktopPane.add(lblControleDeGesto);
		
		JLabel lblUnesc = new JLabel("UNESC 2018");
		lblUnesc.setForeground(Color.WHITE);
		lblUnesc.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblUnesc.setBounds(350, 295, 174, 31);
		desktopPane.add(lblUnesc);
		
		JLabel lblCirurgica = new JLabel("CLINICAS");
		lblCirurgica.setForeground(Color.WHITE);
		lblCirurgica.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCirurgica.setBounds(370, 193, 159, 31);
		desktopPane.add(lblCirurgica);
		
		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 9));
		lblUser.setBounds(10, 467, 46, 14);
		desktopPane.add(lblUser);
		
		JLabel labelUser = new JLabel(user);
		labelUser.setForeground(Color.WHITE);
		cl.gerarLog(user, "logs/logs.txt",false);
		labelUser.setBounds(53, 467, 122, 14);
		desktopPane.add(labelUser);
		
		JLabel label1 = new JLabel("");
		label1.setIcon(new ImageIcon("img\\haste.png"));
		label1.setBounds(403, 11, 55, 116);
		desktopPane.add(label1);
		contentPane.setLayout(gl_contentPane);
	}
	public void setarInternalPaneCliente() {
		//if(desktopPane.getAllFrames().length == 0) {
		desktopPane.add(clienteInternal);
		clienteInternal.setVisible(true);
		/*}else {
			JOptionPane.showMessageDialog(null, "Por favor encerre o processo em execucao");
		}*/
	}
    public void setarAgendaConsulta() {
	    desktopPane.add(consultaInternal);
	    consultaInternal.setVisible(true);
	    consultaInternal.modelo.setNumRows(0);
	    consultaInternal.carregarTabela();
	}
  
    public void setarInternalPaneConsulAgendadas() {
    	desktopPane.add(consulAgendadas);
    	consulAgendadas.setVisible(true);
    	consulAgendadas.modeloConsultas.setNumRows(0);
    	consulAgendadas.carregarTabelaConsultas();
    }
    public void setarInternalPaneCancelar() {
    	desktopPane.add(cancelProce);
    	cancelProce.setVisible(true);
    	cancelProce.modeloProcedimentos.setNumRows(0);
    	cancelProce.carregarTabelaConsultas();
    }
    public void setarInternalPaneReceita() {
    	desktopPane.add(r);
    	r.setVisible(true);
    	r.modelo.setNumRows(0);
    	r.carregarTabelaConsultas();
    }
	public void setPosicaoCliente() {
		clienteInternal.setLocation(desktopPane.getWidth()/ 2 - clienteInternal.getWidth()/2, desktopPane.getHeight()/2 - clienteInternal.getHeight()/2);
	    }
	public void setPosicaoConsulta() {
		consultaInternal.setLocation(desktopPane.getWidth()/ 2 - consultaInternal.getWidth()/2, desktopPane.getHeight()/2 - consultaInternal.getHeight()/2);
	}
	public void setPosicaoConsulAgendadas() {
		consulAgendadas.setLocation(desktopPane.getWidth()/ 2 - consulAgendadas.getWidth()/2, desktopPane.getHeight()/2 - consulAgendadas.getHeight()/2);
	}
	public void setPosicaoCancelar() {
		cancelProce.setLocation(desktopPane.getWidth()/ 2 - cancelProce.getWidth()/2, desktopPane.getHeight()/2 - cancelProce.getHeight()/2);
	}
	public void setPosicaoReceita() {
		r.setLocation(desktopPane.getWidth()/ 2 - r.getWidth()/2, desktopPane.getHeight()/2 - r.getHeight()/2);
	}
}
