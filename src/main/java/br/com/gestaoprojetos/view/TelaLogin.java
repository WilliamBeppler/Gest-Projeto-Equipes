package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.dao.UsuarioDAO;
import br.com.gestaoprojetos.model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Tela de login da aplicação.
 */
public class TelaLogin extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private UsuarioDAO usuarioDAO;

    public TelaLogin() {
        usuarioDAO = new UsuarioDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - Sistema de Gestão de Projetos");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Acessar o Sistema", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridwidth = 1; // Reset gridwidth

        JLabel lblLogin = new JLabel("Login:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblLogin, gbc);

        txtLogin = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtLogin, gbc);

        JLabel lblSenha = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblSenha, gbc);

        txtSenha = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(txtSenha, gbc);

        btnEntrar = new JButton("Entrar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnEntrar, gbc);

        btnEntrar.addActionListener(this::realizarLogin);

        // Adiciona o painel ao frame
        add(panel);
    }

    private void realizarLogin(ActionEvent e) {
        String login = txtLogin.getText();
        String senha = new String(txtSenha.getPassword());

        if (login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Usuario usuario = usuarioDAO.autenticar(login, senha);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso! Bem-vindo, " + usuario.getNomeCompleto(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            // Abre a tela principal e fecha a de login
            TelaPrincipal telaPrincipal = new TelaPrincipal(usuario);
            telaPrincipal.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Login ou senha inválidos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }
}
