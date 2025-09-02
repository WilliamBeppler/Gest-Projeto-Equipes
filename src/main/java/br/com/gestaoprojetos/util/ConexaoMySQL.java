package br.com.gestaoprojetos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Gerencia a conexão com o banco de dados MySQL.
 */
public class ConexaoMySQL {
    // --- ATENÇÃO: Configure os dados de conexão abaixo ---
    private static final String URL = "jdbc:mysql://localhost:3306/gestao_projetos_db?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root"; // Coloque seu usuário do MySQL
    private static final String SENHA = "root";   // Coloque sua senha do MySQL

    private static Connection conexao = null;

    /**
     * Obtém uma conexão com o banco de dados.
     * @return um objeto Connection ou null em caso de falha.
     */
    public static Connection getConexao() {
        try {
            if (conexao == null || conexao.isClosed()) {
                // Carrega o driver JDBC do MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Estabelece a conexão
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: Driver MySQL não encontrado!", "Erro de Driver", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return conexao;
    }

    /**
     * Fecha a conexão com o banco de dados.
     */
    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
