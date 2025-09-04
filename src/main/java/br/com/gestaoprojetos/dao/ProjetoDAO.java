package br.com.gestaoprojetos.dao;

import br.com.gestaoprojetos.model.Projeto;
import br.com.gestaoprojetos.util.ConexaoMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    /**
     * Lista todos os projetos cadastrados no banco de dados.
     * @return Uma lista de objetos Projeto.
     */
    public List<Projeto> listarTodos() {
        String sql = "SELECT * FROM projetos";
        List<Projeto> projetos = new ArrayList<>();

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId_projeto(rs.getInt("id_projeto"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setData_inicio(rs.getDate("data_inicio").toLocalDate());
                projeto.setData_fim_prevista(rs.getDate("data_fim_prevista").toLocalDate());

                // A data_fim_real pode ser nula
                Date dataFimReal = rs.getDate("data_fim_real");
                if (dataFimReal != null) {
                    projeto.setData_fim_real(dataFimReal.toLocalDate());
                }

                projeto.setStatus(rs.getString("status"));
                projeto.setId_gerente(rs.getInt("id_gerente"));
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar projetos: " + e.getMessage());
        }
        return projetos;
    }

    /**
     * Cadastra um novo projeto no banco de dados.
     * @param projeto O objeto Projeto com os dados a serem inseridos.
     */
    public void cadastrar(Projeto projeto) {
        String sql = "INSERT INTO projetos (nome, descricao, data_inicio, data_fim_prevista, status, id_gerente) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, projeto.getNome());
            pstmt.setString(2, projeto.getDescricao());
            pstmt.setDate(3, Date.valueOf(projeto.getData_inicio()));
            pstmt.setDate(4, Date.valueOf(projeto.getData_fim_prevista()));
            pstmt.setString(5, projeto.getStatus());
            pstmt.setInt(6, projeto.getId_gerente());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar projeto: " + e.getMessage());
        }
    }

    /**
     * Lista todos os projetos que não estão concluídos e cuja data final prevista já passou.
     * @return Uma lista de objetos Projeto que estão em risco.
     */
    public java.util.List<Projeto> listarProjetosEmRisco() {
        // A função CURDATE() do SQL retorna a data atual.
        String sql = "SELECT * FROM projetos WHERE status <> 'Concluído' AND data_fim_prevista < CURDATE()";
        java.util.List<Projeto> projetos = new java.util.ArrayList<>();

        try (java.sql.Connection conexao = br.com.gestaoprojetos.util.ConexaoMySQL.getConexao();
             java.sql.PreparedStatement pstmt = conexao.prepareStatement(sql);
             java.sql.ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId_projeto(rs.getInt("id_projeto"));
                projeto.setNome(rs.getString("nome"));
                projeto.setData_fim_prevista(rs.getDate("data_fim_prevista").toLocalDate());
                projeto.setStatus(rs.getString("status"));
                projeto.setId_gerente(rs.getInt("id_gerente"));
                projetos.add(projeto);
            }
        } catch (java.sql.SQLException e) {
            System.err.println("Erro ao listar projetos em risco: " + e.getMessage());
        }
        return projetos;
    }
}
