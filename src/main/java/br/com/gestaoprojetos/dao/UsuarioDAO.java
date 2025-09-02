package br.com.gestaoprojetos.dao;


import br.com.gestaoprojetos.model.Perfil;

import br.com.gestaoprojetos.model.Usuario;
import br.com.gestaoprojetos.util.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Realiza a autenticação e persistência de dados do usuário.
 * ATENÇÃO: A lógica de senha está simplificada (texto plano).
 * Em um sistema real, use hashing (ex: bcrypt).
 */
public class UsuarioDAO {

    public Usuario autenticar(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
        try (Connection conn = ConexaoMySQL.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login);
            stmt.setString(2, senha); // Em produção, compare o hash da senha

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id_usuario"));
                    usuario.setNomeCompleto(rs.getString("nome_completo"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setPerfil(Perfil.valueOf(rs.getString("perfil")));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
