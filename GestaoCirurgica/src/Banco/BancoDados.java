package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import areaLogin.Login;
import classesDados.DadosAgendar;
import classesDados.DadosCliente;
import classesDados.DadosConta;

public class BancoDados {
	private static Connection conexao = null;

   private BancoDados() {}
   
	public static Connection getConexao(){
	    if(conexao == null) {
		try {
            Class.forName("org.hsqldb.jdbcDriver");
            conexao = DriverManager.getConnection("jdbc:hsqldb:file:hsqldb/demo/db/database", "SA", "");
            System.out.println("conectou");
        } catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro na conexao com o banco", "Atencao", JOptionPane.WARNING_MESSAGE);
            System.out.println(e);
        } catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro na conexao com o banco", "Atencao", JOptionPane.WARNING_MESSAGE);
        }
}
        return conexao;
}
	
	public static void fecharConexao() {
		if(conexao != null) {
			try {
				conexao.createStatement().execute("shutdown");
				conexao.close();
				System.out.println("fechou");
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro na conexao com o banco", "Atencao", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

    public static void inserirContas(DadosConta dadosc) {
    	try {
    		PreparedStatement stmt = conexao.prepareStatement("insert into medicos(nome, usuario, senha, email, cpf, celular) values(?,?,?,?,?,?)");
    		stmt.setString(1, dadosc.getNome());
    		stmt.setString(2, dadosc.getNomeUsuario());
    		stmt.setString(3, dadosc.getSenha());
    		stmt.setString(4, dadosc.getEmail());
    		stmt.setString(5, dadosc.getCpf());
    		stmt.setString(6, dadosc.getCelular());
    		stmt.execute();
    		conexao.commit();
    		System.out.println("conta cadastrada");
        }catch (SQLException e){
			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
    	}
    	}
    public static boolean validarLogin(String usuario, String senha) {
        boolean x = false;
    	try {
    	PreparedStatement stmt = conexao.prepareStatement("SELECT usuario, senha FROM medicos WHERE Usuario = ? and senha = ?");
    	stmt.setString(1, usuario);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
    		x = true;
    	}
    	}catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
    	}
		return x;
    }

    	public static void inserirDados(DadosCliente dc){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		System.out.println(BancoDados.pegaIdMedico(du.getNomeUsuario()));
            try{
            	PreparedStatement stmt = conexao.prepareStatement("INSERT INTO pacientes(nome,sexo,uf,fone,cidade,rua,email,cpf,idmedico) VALUES(?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, dc.getNome());
                stmt.setString(2, dc.getSexo());
                stmt.setString(3, dc.getUf());
                stmt.setString(4, dc.getFone());
                stmt.setString(5, dc.getCidade());
                stmt.setString(6, dc.getRua());
                stmt.setString(7, dc.getEmail());
                stmt.setString(8, dc.getCpf());
                stmt.setInt(9, pegaIdMedico(du.getNomeUsuario()));
                stmt.execute();
                stmt.close();
                System.out.println("Dados cadastrados.");
            }catch (SQLException e){
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
            }
        }
    	
    	public static void AgendarProce(DadosAgendar da){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
            try{
            	PreparedStatement stmt = conexao.prepareStatement("INSERT INTO procedimento(nome,data,horario,proce,valor,observacoes,idmedico) VALUES(?,?,?,?,?,?,?)");
            	stmt.setString(1, da.getNome());
                stmt.setString(2, da.getData());
                stmt.setString(3, da.getHorario());
                stmt.setString(4, da.getProce());
                stmt.setString(5, da.getValor());
                stmt.setString(6, da.getObs());
                stmt.setInt(7, pegaIdMedico(du.getNomeUsuario()));
                stmt.execute();
                stmt.close();
                System.out.println("procedimento cadastrados.");
            }catch (SQLException e){
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
            }
        }
    	public static void excluirProce(int id) {
    		try{
            	PreparedStatement stmt = conexao.prepareStatement("delete from procedimento where id = ?");
            	stmt.setInt(1, id);
                stmt.execute();
                stmt.close();
                //conexao.commit();
    			JOptionPane.showMessageDialog(null, "Procedimento Cancelado.", "Atencao", JOptionPane.WARNING_MESSAGE);
            }catch (SQLException e){
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
            }
    	}
    	public static List<DadosCliente> consultas(){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		System.out.println(pegaIdMedico(du.getNomeUsuario()));
        	List<DadosCliente> lista = new ArrayList<DadosCliente>();
        	try {
            	PreparedStatement stmt = conexao.prepareStatement("SELECT nome FROM pacientes where idmedico = "+pegaIdMedico(du.getNomeUsuario()));
            	ResultSet rs = stmt.executeQuery();
        		while(rs.next()) {
        			DadosCliente tb = new DadosCliente();
        			tb.setNome(rs.getString("nome"));
        			lista.add(tb);
            	}
        	}catch(SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
        	}
        	System.out.println(BancoDados.pegaIdMedico(du.getNomeUsuario()));
        	return lista;
        }
    	public static List<DadosAgendar> ListarConsultas(){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
        	List<DadosAgendar> lista = new ArrayList<DadosAgendar>();
        	try {
            	PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM procedimento where idmedico = "+pegaIdMedico(du.getNomeUsuario()));
            	ResultSet rs = stmt.executeQuery();
        		while(rs.next()) {
        			DadosAgendar tb = new DadosAgendar();
        			tb.setNome(rs.getString("nome"));
        			tb.setHorario(rs.getString("horario"));
        			tb.setData(rs.getString("data"));
        			tb.setProce(rs.getString("proce"));
        			lista.add(tb);
            	}
        	}catch(SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
        	}
        	return lista;
        }
    	public static List<DadosCliente> consultaId(String n){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		String n2 = "'"+n+"'";
        	List<DadosCliente> lista = new ArrayList<DadosCliente>();
        	try {
            	PreparedStatement stmt = conexao.prepareStatement("SELECT id FROM procedimento WHERE nome = "+n2+" AND idmedico = "+pegaIdMedico(du.getNomeUsuario()));
            	ResultSet rs = stmt.executeQuery();
        		while(rs.next()) {
        			DadosCliente tb = new DadosCliente();
        			//tb.setNome(rs.getString("nome"));
        			tb.setId(rs.getInt("id"));
        			lista.add(tb);
            	}
        	}catch(SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
        	}
        	return lista;
        }
    	public static List<DadosCliente> consultaEmailPaciente(String n){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		String n2 = "'"+n+"'";
        	List<DadosCliente> lista = new ArrayList<DadosCliente>();
        	try {
            	PreparedStatement stmt = conexao.prepareStatement("SELECT email FROM pacientes WHERE nome = "+n2+" AND idmedico = "+pegaIdMedico(du.getNomeUsuario()));
            	ResultSet rs = stmt.executeQuery();
        		while(rs.next()) {
        			DadosCliente tb = new DadosCliente();
        			//tb.setId(rs.getInt("id"));
        			tb.setEmail(rs.getString("email"));
        			lista.add(tb);
            	}
        	}catch(SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
        	}
        	return lista;
        }
    	public static List<DadosAgendar> consultaTipoProcePaciente(String n){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		String n2 = "'"+n+"'";
        	List<DadosAgendar> lista = new ArrayList<DadosAgendar>();
        	try {
            	PreparedStatement stmt = conexao.prepareStatement("SELECT proce FROM procedimento WHERE nome = "+n2+" AND idmedico = "+pegaIdMedico(du.getNomeUsuario()));
            	ResultSet rs = stmt.executeQuery();
        		while(rs.next()) {
        			DadosAgendar tb = new DadosAgendar();
        			tb.setProce(rs.getString("proce"));
        			lista.add(tb);
            	}
        	}catch(SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
        	}
        	return lista;
        }
    	public static List<DadosAgendar> consultaData(String n){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		String n2 = "'"+n+"'";
        	List<DadosAgendar> lista = new ArrayList<DadosAgendar>();
        	try {
            	PreparedStatement stmt = conexao.prepareStatement("SELECT data FROM procedimento WHERE nome = "+n2+" AND idmedico = "+pegaIdMedico(du.getNomeUsuario()));
            	ResultSet rs = stmt.executeQuery();
        		while(rs.next()) {
        			DadosAgendar tb = new DadosAgendar();
        			tb.setData(rs.getString("data"));
        			lista.add(tb);
            	}
        	}catch(SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
        	}
        	return lista;
        }
    	public static List<DadosAgendar> consultaHorario(String n){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		String n2 = "'"+n+"'";
        	List<DadosAgendar> lista = new ArrayList<DadosAgendar>();
        	try {
            	PreparedStatement stmt = conexao.prepareStatement("SELECT horario FROM procedimento WHERE nome = "+n2+" AND idmedico = "+pegaIdMedico(du.getNomeUsuario()));
            	ResultSet rs = stmt.executeQuery();
        		while(rs.next()) {
        			DadosAgendar tb = new DadosAgendar();
        			tb.setHorario(rs.getString("horario"));
        			lista.add(tb);
            	}
        	}catch(SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
        	}
        	return lista;
        }
    	public static List<DadosAgendar> consultaObs(String n){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		String n2 = "'"+n+"'";
        	List<DadosAgendar> lista = new ArrayList<DadosAgendar>();
        	try {
            	PreparedStatement stmt = conexao.prepareStatement("SELECT observacoes FROM procedimento WHERE nome = "+n2+" AND idmedico = "+pegaIdMedico(du.getNomeUsuario()));
            	ResultSet rs = stmt.executeQuery();
        		while(rs.next()) {
        			DadosAgendar tb = new DadosAgendar();
        			tb.setObs(rs.getString("observacoes"));
        			lista.add(tb);
            	}
        	}catch(SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
        	}
        	return lista;
        }
    	public static Vector Filtro(String filtro) throws Exception {
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		Vector tb = new Vector();
    		String url = "select * from pacientes where lower(nome) like '%"+filtro+"%' AND idmedico = "+pegaIdMedico(du.getNomeUsuario());
    		PreparedStatement ps = getConexao().prepareStatement(url);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			Vector nl = new Vector();
    			nl.add(rs.getString("nome"));
    			tb.add(nl);
    		}
    		return tb;
    	}
    	public static Vector FiltroProce(String filtro) throws Exception {
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		Vector tb = new Vector();
    		String url = "select * from procedimento where lower(nome) like '%"+filtro+"%' AND idmedico = "+pegaIdMedico(du.getNomeUsuario());
    		PreparedStatement ps = getConexao().prepareStatement(url);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			Vector nl = new Vector();
    			nl.add(rs.getString("nome"));
    			nl.add(rs.getString("horario"));
    			nl.add(rs.getString("data"));
    			nl.add(rs.getString("proce"));
    			tb.add(nl);
    		}
    		return tb;
    	}
    	public static Vector FiltroCancel(String filtro) throws Exception {
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		Vector tb = new Vector();
    		String url = "select * from procedimento where lower(nome) like '%"+filtro+"%' AND idmedico = "+pegaIdMedico(du.getNomeUsuario());
    		PreparedStatement ps = getConexao().prepareStatement(url);
    		ResultSet rs = ps.executeQuery();
    		while(rs.next()) {
    			Vector nl = new Vector();
    			nl.add(rs.getString("nome"));
    			nl.add(rs.getString("horario"));
    			nl.add(rs.getString("proce"));
    			tb.add(nl);
    		}
    		return tb;
    	}
    	public static List<DadosConta> setaIdMedico(String n){
    		Login login = new Login();
			DadosConta du = login.getDadosConta();
    		String n2 = "'"+n+"'";
        	List<DadosConta> lista = new ArrayList<DadosConta>();
        	try {
            	PreparedStatement stmt = conexao.prepareStatement("SELECT id FROM medicos WHERE usuario = "+n2);
            	ResultSet rs = stmt.executeQuery();
        		while(rs.next()) {
        			DadosConta tb = new DadosConta();
        			tb.setId(rs.getInt("id"));
        			lista.add(tb);
            	}
        	}catch(SQLException e) {
    			JOptionPane.showMessageDialog(null, "Erro em: "+e, "Atencao", JOptionPane.WARNING_MESSAGE);
        	}
        	return lista;
        }
    	public static int pegaIdMedico(String user) {
    		int a = 0;
			for(DadosConta dc: setaIdMedico(user)) {//acho que pode tirar o BancoDados
					a = dc.getId();
			}
			return a;
    	}
    	
}
