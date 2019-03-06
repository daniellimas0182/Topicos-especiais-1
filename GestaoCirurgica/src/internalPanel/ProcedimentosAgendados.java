package internalPanel;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import acoes.ImplementaAcoes;
import classesDados.DadosAgendar;
import classesDados.DadosCliente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Banco.BancoDados;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class ProcedimentosAgendados extends JInternalFrame {
	private ImplementaAcoes listener = new ImplementaAcoes(null,null,null,null,null,this,null,null);
	public DefaultTableModel modeloConsultas = new DefaultTableModel();
	private JScrollPane scrollPane_1;
	private JTable tabelaConsultas;
	private static String n;
	private static String email;
	private static String tipoP;
	private static String data;
	private static String horario;
	private static String obs;
	private JTextField textFieldFiltro;
	
	public ProcedimentosAgendados() {
		setFrameIcon(new ImageIcon("img\\registro.png"));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Agenda de Procedimentos");
		setClosable(true);
		setBounds(100, 100, 597, 374);
				
		JLabel lblAgendaDeConsultas = new JLabel("Agenda de Procedimentos");
		lblAgendaDeConsultas.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		scrollPane_1 = new JScrollPane();
		
		JButton btnEmail = new JButton("Lembrete via Email");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(procurarTipoProce());
				System.out.println(procurarData());
				System.out.println(procurarHorario());
				System.out.println(procurarObs());
				Properties props = new Properties();
	            /** Parâmetros de conexão com servidor Live**/
	            props.put("mail.transport.protocol", "smtp");
	            props.put("mail.smtp.host", "smtp.live.com");
	            props.put("mail.smtp.socketFactory.port", "587");
	            props.put("mail.smtp.socketFactory.fallback", "false");
	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.port", "587");
	        	Session session = Session.getDefaultInstance(props,
	                    new javax.mail.Authenticator() {
	                         protected PasswordAuthentication getPasswordAuthentication() {
	                               return new PasswordAuthentication("lembreteclinica@outlook.com", "qweasdzxc123");
	                         }
	                    });
	        session.setDebug(true);
	        try {
	              Message message = new MimeMessage(session);
	              message.setFrom(new InternetAddress("lembreteclinica@outlook.com")); //Remetente

	              Address[] toUser = InternetAddress.parse(procurarEmail());  

	              message.setRecipients(Message.RecipientType.TO, toUser);
	              String titulo = "Aviso sobre sua "+procurarTipoProce();
	              message.setSubject(titulo);//Assunto
	              String msg = "Sua "+procurarTipoProce()+" esta agendada para a data "+procurarData()+" as "+procurarHorario()+" horas.\r\nObservacoes: "+procurarObs();
	              message.setText(msg);
	              Transport.send(message);
	              JOptionPane.showMessageDialog(null, "Lembrete enviado com sucesso!");				
	         } catch (MessagingException e) {
	             JOptionPane.showMessageDialog(null, "Falha no envio de e-mail.");
	        }
			}
		});
		
		btnEmail.setBackground(Color.WHITE);
		
		textFieldFiltro = new JTextField();
		textFieldFiltro.setColumns(10);
		textFieldFiltro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
				Vector cabecalho = new Vector(); 
				cabecalho.add("Paciente");
				cabecalho.add("Horario");
				cabecalho.add("Data");
				cabecalho.add("Procedimento");
				if(!textFieldFiltro.getText().equals("")) {
					 DefaultTableModel nv = new DefaultTableModel(BancoDados.FiltroProce(textFieldFiltro.getText().toLowerCase()),cabecalho);
					 tabelaConsultas.setModel(nv);
				}else {
					tabelaConsultas.setModel(modeloConsultas);
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
					.addContainerGap(214, Short.MAX_VALUE)
					.addComponent(lblAgendaDeConsultas)
					.addGap(175))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFiltro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldFiltro, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnEmail, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(lblAgendaDeConsultas)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEmail)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblFiltro)
							.addComponent(textFieldFiltro, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		tabelaConsultas = new JTable(modeloConsultas);
		scrollPane_1.setViewportView(tabelaConsultas);
		modeloConsultas.addColumn("Paciente");
		modeloConsultas.addColumn("Horario");
		modeloConsultas.addColumn("Data");
		modeloConsultas.addColumn("Procedimento");
		getContentPane().setLayout(groupLayout);
		tabelaConsultas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				n = (String) tabelaConsultas.getModel().getValueAt(tabelaConsultas.getSelectedRow(), tabelaConsultas.getSelectedColumn());
				System.out.println(procurarEmail());
			}
		});
	}
	
	public void carregarTabelaConsultas() {
		for(DadosAgendar da: BancoDados.ListarConsultas()) {
			modeloConsultas.addRow(new Object[]{
				da.getNome(),
				da.getHorario(),
				da.getData(),
				da.getProce()
			});
		}
	}
	/*private static Runnable t2 = new Runnable() {public void run() {	}};*/
	public static String procurarEmail() {
		for(DadosCliente dc: BancoDados.consultaEmailPaciente(n)) {
				email = dc.getEmail();
		}
		return email;
	}
	public static String procurarTipoProce() {
		for(DadosAgendar da: BancoDados.consultaTipoProcePaciente(n)) {
			tipoP = da.getProce();
	}
	return tipoP;
	}
	public static String procurarData() {
		for(DadosAgendar da: BancoDados.consultaData(n)) {
			data = da.getData();
	}
	return data;
	}
	public static String procurarHorario() {
		for(DadosAgendar da: BancoDados.consultaHorario(n)) {
			horario = da.getHorario();
	}
	return horario;
	}
	public static String procurarObs() {
		for(DadosAgendar da: BancoDados.consultaObs(n)) {
			obs = da.getObs();
	}
	return obs;
	}
}
