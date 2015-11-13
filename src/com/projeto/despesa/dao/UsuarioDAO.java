package com.java.geraDTO.classeGerada.bean; 

 import java.sql.*;
import java.util.*;
import com.projeto.despesa.utilitarios.conexao;
import javax.swing.*;



	public class UsuarioDAO {
	public UsuarioDAO(){
		try{
		 Class.forName(new conexao().config.getString("driver"));
		} catch (ClassNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Driver Erro: " + ex.getMessage(), "Construtor da Classe",0);
			ex.printStackTrace();
		}
	}
		public void salvar(Usuario usuario) throws SQLException {
		Connection con = DriverManager.getConnection(new conexao().config.getString("url") ,new conexao().config.getString("usuario"),new conexao().config.getString("senha"));
		PreparedStatement ps=null;
		String sqlSalvar="INSERT INTO usuario (nome, usuario, senha, nivel ) VALUES (?, ?, ?, ?)";
		try{
			ps = con.prepareStatement(sqlSalvar);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getUsuario());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getNivel());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null,"Inserido Com Sucesso: ","Mensagem do Sistema - Salvar",1);
			}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"NumberFormaterExeption Erro: "+ e.getMessage(),"ClasseDAO Func.Salvar",0);
			e.printStackTrace();
		}
			catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"NullPointerException Erro: "+ e.getMessage(),"ClasseDAO Func. Salvar",0);
			e.printStackTrace();
		}
			catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"SQLException Erro: "+ e.getMessage(),"ClasseDAO Func. Salvar",0);
			e.printStackTrace();
		}
			catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Exception Erro: "+ e.getMessage(),"ClasseDAO Func. Salvar",0);
			e.printStackTrace();
		}
			finally {
			ps.close();
			con.close();
			}
		}
		
		public void atualizar(Usuario usuario) throws SQLException {
		Connection con = DriverManager.getConnection(new conexao().config.getString("url") ,new conexao().config.getString("usuario"),new conexao().config.getString("senha"));
		PreparedStatement ps=null;
		String sqlAtualizar="UPDATE usuario SET nome=?, usuario=?, senha=?, nivel=? WHERE codigo=?";
		try{
			ps = con.prepareStatement(sqlAtualizar);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getUsuario());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getNivel());
			ps.setInt(5, usuario.getCodigo());
			ps.executeUpdate();
		JOptionPane.showMessageDialog(null,"Atualizado Com Sucesso: ","Mensagem do Sistema - Atualizar",1);
			}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"NumberFormaterExeption Erro: "+ e.getMessage(),"ClasseDAO Func.Atualizar",0);
			e.printStackTrace();
		}
			catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"NullPointerException Erro: "+ e.getMessage(),"ClasseDAO Func. Atualizar",0);
			e.printStackTrace();
		}
			catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"SQLException Erro: "+ e.getMessage(),"ClasseDAO Func. Atualizar",0);
			e.printStackTrace();
		}
			catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Exception Erro: "+ e.getMessage(),"ClasseDAO Func. Atualizar",0);
			e.printStackTrace();
		}
			finally {
			ps.close();
			con.close();
			}
		}
		
		public void excluir(Usuario usuario) throws SQLException {
		Connection con = DriverManager.getConnection(new conexao().config.getString("url") ,new conexao().config.getString("usuario"),new conexao().config.getString("senha"));
		PreparedStatement ps=null;
		String sqlExcluir="DELETE FROM usuario WHERE codigo=?";
		try{
			ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, usuario.getCodigo());
			ps.executeUpdate();
		JOptionPane.showMessageDialog(null,"Ecluido Com Sucesso: ","Mensagem do Sistema - Excluir",1);
			}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"NumberFormaterExeption Erro: "+ e.getMessage(),"ClasseDAO Func.Excluir",0);
			e.printStackTrace();
		}
			catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"NullPointerException Erro: "+ e.getMessage(),"ClasseDAO Func. Excluir",0);
			e.printStackTrace();
		}
			catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"SQLException Erro: "+ e.getMessage(),"ClasseDAO Func. Excluir",0);
			e.printStackTrace();
		}
			catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Exception Erro: "+ e.getMessage(),"ClasseDAO Func. Excluir",0);
			e.printStackTrace();
		}
			finally {
			ps.close();
			con.close();
			}
		}
		
		public Usuario localizar(Integer codigo) throws SQLException {
		Connection con = DriverManager.getConnection(new conexao().config.getString("url") ,new conexao().config.getString("usuario"),new conexao().config.getString("senha"));
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sqlLocalizar="SELECT * FROM usuario WHERE codigo=?";
		Usuario usuario =  new Usuario();
					try{
			ps = con.prepareStatement(sqlLocalizar);
			ps.setInt(1, codigo);
			rs = ps.executeQuery();
		//if(!rs.next()){
		//return null;
		//}
		usuario.setCodigo(rs.getInt("codigo"));
			usuario.setNome(rs.getString("nome"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setNivel(rs.getInt("nivel"));
			return usuario; 
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"NumberFormaterExeption Erro: "+ e.getMessage(),"ClasseDAO Func.Localizar",0);
			e.printStackTrace();
		}
			catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"NullPointerException Erro: "+ e.getMessage(),"ClasseDAO Func. Localizar",0);
			e.printStackTrace();
		}
			catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"SQLException Erro: "+ e.getMessage(),"ClasseDAO Func. Localizar",0);
			e.printStackTrace();
		}
			catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Exception Erro: "+ e.getMessage(),"ClasseDAO Func. Localizar",0);
			e.printStackTrace();
		}
			finally {
			ps.close();
			con.close();
			}
		return usuario; 
		}
		
		public List<Usuario> listar() throws SQLException {
		List<Usuario> resultado= new ArrayList<Usuario>();
		Connection con = DriverManager.getConnection(new conexao().config.getString("url") ,new conexao().config.getString("usuario"),new conexao().config.getString("senha"));
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sqlListar="SELECT * FROM usuario";
		Usuario usuario;
					try{
			ps = con.prepareStatement(sqlListar);
			rs = ps.executeQuery();
		//if(rs==null){
		//return null;
		//}
		while(rs.next()){
		usuario =  new Usuario();
			usuario.setCodigo(rs.getInt("codigo"));
			usuario.setNome(rs.getString("nome"));
			usuario.setUsuario(rs.getString("usuario"));
			usuario.setSenha(rs.getString("senha"));
			usuario.setNivel(rs.getInt("nivel"));
			resultado.add(usuario);
			}
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,"NumberFormaterExeption Erro: "+ e.getMessage(),"ClasseDAO Func.Localizar",0);
			e.printStackTrace();
		}
			catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,"NullPointerException Erro: "+ e.getMessage(),"ClasseDAO Func. Localizar",0);
			e.printStackTrace();
		}
			catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"SQLException Erro: "+ e.getMessage(),"ClasseDAO Func. Localizar",0);
			e.printStackTrace();
		}
			catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Exception Erro: "+ e.getMessage(),"ClasseDAO Func. Localizar",0);
			e.printStackTrace();
		}
			finally {
			ps.close();
			con.close();
			}
		return resultado; 
		}
		

	}