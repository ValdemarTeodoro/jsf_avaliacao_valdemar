package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.InteressadoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class InteressadoDaoValdemar {
	public void incluir(InteressadoValdemar interessadoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO interessados(nome_interessado, cpf_interessado) VALUES (?, ?)";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, interessadoValdemar.getNomeInteressado().trim());
			ps.setString(2, interessadoValdemar.getCpfInteressado().trim());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void alterar(InteressadoValdemar interessadoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE interessados SET nome_interessado=?, cpf_interessado=? WHERE id=?";
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, interessadoValdemar.getNomeInteressado().trim());
			ps.setString(2, interessadoValdemar.getCpfInteressado().trim());
			ps.setLong(3, interessadoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public void excluir(InteressadoValdemar interessadoValdemar) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM interessados WHERE id=?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, interessadoValdemar.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, null);
		}
	}
	public List<InteressadoValdemar> lista() throws ClassNotFoundException, SQLException{
		List<InteressadoValdemar> listaRetorno = new ArrayList<>();
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, nome_interessado, cpf_interessado FROM interessados";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				InteressadoValdemar interessado = new InteressadoValdemar();
				interessado.setId(rs.getLong("id"));
				interessado.setNomeInteressado(rs.getString("nome_interessado").trim());
				interessado.setCpfInteressado(rs.getString("cpf_interessado").trim());
				
				listaRetorno.add(interessado);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		
		return listaRetorno;
	}
	public InteressadoValdemar pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id, nome_interessado, cpf_interessado FROM interessados WHERE id=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		InteressadoValdemar retorno = new InteressadoValdemar();
		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				retorno.setId(rs.getLong("id"));
				retorno.setNomeInteressado(rs.getString("nome_interessado").trim());
				retorno.setCpfInteressado(rs.getString("cpf_interessado").trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			Conexao.fecharConexao(ps, conn, rs);
		}
		return retorno;
	}
}
