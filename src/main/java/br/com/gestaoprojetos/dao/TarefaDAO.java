package br.com.gestaoprojetos.dao;

import br.com.gestaoprojetos.model.Tarefa;
import br.com.gestaoprojetos.util.ConexaoMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {

    public void cadastrar(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas (titulo, descricao, data_fim_prevista, status, id_projeto, id_responsavel) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setString(1, tarefa.getTitulo());
            pstmt.setString(2, tarefa.getDescricao());
            pstmt.setDate(3, Date.valueOf(tarefa.getData_fim_prevista()));
            pstmt.setString(4, tarefa.getStatus());
            pstmt.setInt(5, tarefa.getId_projeto());
            pstmt.setInt(6, tarefa.getId_responsavel());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar tarefa: " + e.getMessage());
        }
    }

    public List<Tarefa> listarTodas() {
        String sql = "SELECT * FROM tarefas";
        List<Tarefa> tarefas = new ArrayList<>();

        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId_tarefa(rs.getInt("id_tarefa"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setData_fim_prevista(rs.getDate("data_fim_prevista").toLocalDate());
                tarefa.setStatus(rs.getString("status"));
                tarefa.setId_projeto(rs.getInt("id_projeto"));
                tarefa.setId_responsavel(rs.getInt("id_responsavel"));
                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
        }
        return tarefas;
    }

    /**
     * Conta o número total de tarefas atribuídas a um responsável.
     * @param id_responsavel O ID do usuário.
     * @return O número total de tarefas.
     */
    public int contarTarefasPorResponsavel(int id_responsavel) {
        String sql = "SELECT COUNT(*) FROM tarefas WHERE id_responsavel = ?";
        int total = 0;
        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id_responsavel);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar tarefas por responsável: " + e.getMessage());
        }
        return total;
    }

    /**
     * Conta o número de tarefas com o status 'Concluída' para um responsável.
     * @param id_responsavel O ID do usuário.
     * @return O número de tarefas concluídas.
     */
    public int contarTarefasConcluidasPorResponsavel(int id_responsavel) {
        String sql = "SELECT COUNT(*) FROM tarefas WHERE id_responsavel = ? AND status = 'Concluída'";
        int total = 0;
        try (Connection conexao = ConexaoMySQL.getConexao();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {

            pstmt.setInt(1, id_responsavel);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao contar tarefas concluídas: " + e.getMessage());
        }
        return total;
    }
}
