package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;

public class UsuarioRepository {
	private Connection conexao;
	private String CONNECTION_STRING = "jdbc:mysql://localhost:3306/unifacear";
	private String USUARIO = "root";
	private String SENHA = "root";

	public UsuarioRepository() {
		try {
			this.conexao = DriverManager.getConnection(CONNECTION_STRING, USUARIO, SENHA);
			if (!this.conexao.isClosed()) {
				System.out.println("A conexão foi estabelecida com sucesso!");
			} else {
				System.out.println("Não foi possível estabelecer a conexão com o BD!");
			}
		} catch (SQLException e) {
			System.out.println("Não foi possível se conectar com o BD!");
			e.printStackTrace();
		}
	}

	public void inserir(Usuario usuario) {
		String sql = "INSERT INTO tb_usuarios (ID_Usuario, nome, cpf) VALUES (?, ?, ?);";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
			ps.setInt(1, usuario.getId());
			ps.setString(2, usuario.getNome());
			ps.setString(3, usuario.getCpf());
			ps.execute();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Usuário " + usuario.getNome() + " não foi inserido no Banco de dados devido a duplicidade de dados!");
		} catch (SQLException e) {
			System.out.println("Usuário não foi inserido no banco de dados");
			e.printStackTrace();
		}
	}

	public void excluir(Usuario usuario) {
		String sql = "DELETE FROM tb_usuarios WHERE ID_Usuario = ?;";
		try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
			ps.setInt(1, usuario.getId());
			ps.execute();
			System.out.println("Usuario Removido do sistema");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(Usuario usuario) {
		String sql = "update tb_usuarios set nome=?, cpf = ? where ID_Usuario = ?";
		try {
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.setInt(3, usuario.getId());
			ps.execute();
			System.out.println("Os dados foram atualizados com sucesso");
		} catch (SQLException e) {
			System.out.println("Não foi possível atualizar os dados do usuário");
			e.printStackTrace();
		}
	}

	public Usuario consultarPorCpf(String cpf) {
		String sql = "SELECT * FROM tb_usuarios WHERE cpf = ?;";
		Usuario usuarioConsultado = null;
		try {
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuarioConsultado = new Usuario();
				usuarioConsultado.setId(rs.getInt("ID_Usuario"));
				usuarioConsultado.setNome(rs.getString("nome"));
				usuarioConsultado.setCpf(rs.getString("cpf"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioConsultado;
	}
	
	public Usuario consultarPorId(int id) {
		String sql = "SELECT * FROM tb_usuarios WHERE ID_Usuario = ?;";
		Usuario usuarioConsultado = null;
		try {
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuarioConsultado = new Usuario();
				usuarioConsultado.setId(rs.getInt("ID_Usuario"));
				usuarioConsultado.setNome(rs.getString("nome"));
				usuarioConsultado.setCpf(rs.getString("cpf"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioConsultado;
	}
	
	public List<Usuario> consultarPorIniciais(String iniciais) {
	    String sql = "SELECT * FROM tb_usuarios WHERE nome LIKE ?;";
	    List<Usuario> usuarios = new ArrayList<>();

	    try {
	        PreparedStatement ps = this.conexao.prepareStatement(sql);
	        ps.setString(1, iniciais + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Usuario usuario = new Usuario();
	            usuario.setId(rs.getInt("ID_Usuario"));
	            usuario.setNome(rs.getString("nome"));
	            usuario.setCpf(rs.getString("cpf"));
	            usuarios.add(usuario);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return usuarios;
	}

	
	public List<Usuario> retornaTodos(){
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "Select ID_Usuario, nome, cpf from tb_usuarios order by nome asc";
		try {
			PreparedStatement ps = this.conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("ID_Usuario"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setNome(rs.getString("nome"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			System.out.println("Não foi possivel realizar a pesquisa");
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public List<Usuario> pesquisaPorInicial(String inicial){
		//implementar
		return null;
	}
}
