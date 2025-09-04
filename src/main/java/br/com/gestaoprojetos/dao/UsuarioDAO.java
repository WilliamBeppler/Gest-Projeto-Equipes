package br.com.gestaoprojetos.dao;

import br.com.gestaoprojetos.model.Usuario;
import br.com.gestaoprojetos.util.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por todas as operações de banco de dados
 * relacionadas à entidade Usuario.
 */
public class UsuarioDAO {

    /**
     * Verifica as credenciais de login no banco de dados.
     *
     * @param login O login do usuário.
     * @param senhaHash A senha do usuário já codificada em SHA-256.
     * @return Um objeto Usuario se as credenciais forem válidas, caso contrário, null.
     */
    public Usuario verificarLogin(String login, String senhaHash) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConexaoMySQL.getConexao();
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, login);
            pstmt.setString(2, senhaHash);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Usuario usuarioEncontrado = new Usuario();
                usuarioEncontrado.setId_usuario(rs.getInt("id_usuario"));
                usuarioEncontrado.setNome_completo(rs.getString("nome_completo"));
                usuarioEncontrado.setLogin(rs.getString("login"));
                usuarioEncontrado.setPerfil(rs.getString("perfil"));
                return usuarioEncontrado;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao verificar login: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lista todos os usuários cadastrados no banco de dados.
     * @return Uma lista de objetos Usuario.
     */
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConexaoMySQL.getConexao();
            pstmt = conexao.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNome_completo(rs.getString("nome_completo"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setEmail(rs.getString("email"));
                usuario.setCargo(rs.getString("cargo"));
                usuario.setLogin(rs.getString("login"));
                usuario.setPerfil(rs.getString("perfil"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    /**
     * Cadastra um novo usuário no banco de dados.
     * @param usuario O objeto Usuario com os dados a serem inseridos.
     */
    public void cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome_completo, cpf, email, cargo, login, senha, perfil) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            conexao = ConexaoMySQL.getConexao();
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome_completo());
            pstmt.setString(2, usuario.getCpf());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getCargo());
            pstmt.setString(5, usuario.getLogin());
            pstmt.setString(6, usuario.getSenha()); // A senha já deve vir com hash
            pstmt.setString(7, usuario.getPerfil());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    /**
     * Atualiza os dados de um usuário existente.
     * @param usuario O objeto Usuario com os dados atualizados.
     */
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome_completo = ?, cpf = ?, email = ?, cargo = ?, login = ?, perfil = ? WHERE id_usuario = ?";
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            conexao = ConexaoMySQL.getConexao();
            pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, usuario.getNome_completo());
            pstmt.setString(2, usuario.getCpf());
            pstmt.setString(3, usuario.getEmail());
            pstmt.setString(4, usuario.getCargo());
            pstmt.setString(5, usuario.getLogin());
            pstmt.setString(6, usuario.getPerfil());
            pstmt.setInt(7, usuario.getId_usuario());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    /**
     * Exclui um usuário do banco de dados pelo seu ID.
     * @param id_usuario O ID do usuário a ser excluído.
     */
    public void excluir(int id_usuario) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        Connection conexao = null;
        PreparedStatement pstmt = null;

        try {
            conexao = ConexaoMySQL.getConexao();
            pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id_usuario);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }
    /**
     * Busca um único usuário pelo seu ID.
     * @param id_usuario O ID do usuário a ser procurado.
     * @return Um objeto Usuario se encontrado, caso contrário, null.
     */
    public Usuario buscarPorId(int id_usuario) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexao = ConexaoMySQL.getConexao();
            pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, id_usuario);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNome_completo(rs.getString("nome_completo"));
                // Preencha outros campos se precisar deles
                return usuario;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário por ID: " + e.getMessage());
        }
        return null;
    }
}