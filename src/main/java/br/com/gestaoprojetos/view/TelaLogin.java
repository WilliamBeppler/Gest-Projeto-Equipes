package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.dao.UsuarioDAO;
import br.com.gestaoprojetos.model.Usuario;
import br.com.gestaoprojetos.util.SegurancaUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaLogin extends JFrame {

    private JLabel labelLogin;
    private JLabel labelSenha;
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;

    public TelaLogin() {
        setTitle("Login - Sistema de Gestão de Projetos");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        labelLogin = new JLabel("Login:");
        labelLogin.setBounds(30, 30, 80, 25);
        add(labelLogin);

        campoLogin = new JTextField();
        campoLogin.setBounds(100, 30, 200, 25);
        add(campoLogin);

        labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(30, 70, 80, 25);
        add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(100, 70, 200, 25);
        add(campoSenha);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBounds(125, 110, 100, 30);
        add(botaoEntrar);

        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());

                if (login.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String senhaHash = SegurancaUtil.gerarHashSHA256(senha);
                UsuarioDAO usuarioDAO = new UsuarioDAO();

                // AQUI ESTÁ A CORREÇÃO: o nome do método é "verificarLogin"
                Usuario usuario = usuarioDAO.verificarLogin(login, senhaHash);

                if (usuario != null) {
                    dispose();
                    TelaPrincipal telaPrincipal = new TelaPrincipal(usuario);
                    telaPrincipal.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Login ou senha inválidos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}