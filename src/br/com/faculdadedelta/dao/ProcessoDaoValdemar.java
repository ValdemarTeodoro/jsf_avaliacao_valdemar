package br.com.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.faculdadedelta.modelo.InteressadoValdemar;
import br.com.faculdadedelta.modelo.ProcessoValdemar;
import br.com.faculdadedelta.util.Conexao;

public class ProcessoDaoValdemar {
		public void incluir(ProcessoValdemar processoValdemar) throws ClassNotFoundException, SQLException {
			Connection conn = Conexao.conectarNoBancoDeDados();
			String sql = "INSERT INTO processos(id_interessado, numero_proc, assunto_proc, data_autuacao_proc, valor_proc) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = null;
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, processoValdemar.getInteressadoValdemar().getId());
				ps.setString(2, processoValdemar.getNumeroProc().trim());
				ps.setString(3, processoValdemar.getAssuntoProc().trim());
				ps.setDate(4, new java.sql.Date(processoValdemar.getDataAutuacaoProc().getTime()));
				ps.setDouble(5, processoValdemar.getValorProc());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException(e);
			}finally {
				Conexao.fecharConexao(ps, conn, null);
			}
		}
		public void alterar(ProcessoValdemar processoValdemar) throws ClassNotFoundException, SQLException {
			Connection conn = Conexao.conectarNoBancoDeDados();
			String sql = "UPDATE processos SET id_interessado=?, numero_proc=?, assunto_proc=?, data_autuacao_proc=?, valor_proc=? WHERE id=?";
			PreparedStatement ps = null;
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, processoValdemar.getInteressadoValdemar().getId());
				ps.setString(2, processoValdemar.getNumeroProc().trim());
				ps.setString(3, processoValdemar.getAssuntoProc().trim());
				ps.setDate(4, new java.sql.Date(processoValdemar.getDataAutuacaoProc().getTime()));
				ps.setDouble(5, processoValdemar.getValorProc());
				ps.setLong(6, processoValdemar.getId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException(e);
			}finally {
				Conexao.fecharConexao(ps, conn, null);
			}
		}
		public void excluir(ProcessoValdemar processoValdemar) throws ClassNotFoundException, SQLException {
			Connection conn = Conexao.conectarNoBancoDeDados();
			String sql = "DELETE FROM processos WHERE id=?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setLong(1, processoValdemar.getId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException(e);
			}finally {
				Conexao.fecharConexao(ps, conn, null);
			}
		}
		public List<ProcessoValdemar> lista() throws ClassNotFoundException, SQLException{
			List<ProcessoValdemar> listaRetorno = new ArrayList<>();
			Connection conn = Conexao.conectarNoBancoDeDados();
			String sql = "SELECT p.id AS idProcesso, p.id_interessado AS idintProcesso, p.numero_proc AS numeProc, p.assunto_proc AS assuntoProc, p.data_autuacao_proc AS dataProc, p.valor_proc AS valorProc, i.id AS idInter, i.nome_interessado AS nomeInter, i.cpf_interessado AS cpfInter	FROM processos p inner join interessados i on p.id_interessado = i.id";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while(rs.next()) {
					ProcessoValdemar processo = new ProcessoValdemar();
					processo.setId(rs.getLong("idProcesso"));
					processo.setIdInteressado(rs.getInt("idintProcesso"));
					processo.setNumeroProc(rs.getString("numeProc").trim());
					processo.setAssuntoProc(rs.getString("assuntoProc").trim());
					processo.setDataAutuacaoProc(rs.getDate("dataProc"));
					processo.setValorProc(rs.getDouble("valorProc"));
					
					InteressadoValdemar interessado = new InteressadoValdemar();
					interessado.setId(rs.getLong("idInter"));
					interessado.setNomeInteressado(rs.getString("nomeInter").trim());
					interessado.setCpfInteressado(rs.getString("cpfInter").trim());
					processo.setInteressadoValdemar(interessado);
					
					listaRetorno.add(processo);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException(e);
			}finally {
				Conexao.fecharConexao(ps, conn, rs);
			}
			
			return listaRetorno;
		}
}
