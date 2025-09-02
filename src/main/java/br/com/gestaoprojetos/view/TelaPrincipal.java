package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.model.Usuario;
import javax.swing.*;
import java.awt.*;

/**
 * Tela principal (dashboard) da aplicação após o login.
 */
public class TelaPrincipal extends JFrame {

    private Usuario usuarioLogado;

    public TelaPrincipal(Usuario usuario) {
        this.usuarioLogado = usuario;
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Gestão de Projetos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel Superior com informações do usuário
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblBoasVindas = new JLabel("Bem-vindo, " + usuarioLogado.getNomeCompleto() + " | Perfil: " + usuarioLogado.getPerfil());
        lblBoasVindas.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(lblBoasVindas, BorderLayout.WEST);

        JButton btnLogout = new JButton("Sair");
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema?", "Confirmação de Saída", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                new TelaLogin().setVisible(true);
                this.dispose();
            }
        });
        topPanel.add(btnLogout, BorderLayout.EAST);

        // Painel Central com funcionalidades
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JLabel lblMensagem = new JLabel("Funcionalidades do sistema aparecerão aqui.");
        centerPanel.add(lblMensagem);

        // Adiciona botões com base no perfil do usuário
        adicionarBotoesPorPerfil(centerPanel);

        // Adiciona os painéis ao frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void adicionarBotoesPorPerfil(JPanel panel) {
        // Limpa o painel antes de adicionar novos botões
        panel.removeAll();
        panel.add(new JLabel("Selecione uma opção:"));

        switch (usuarioLogado.getPerfil()) {
            case ADMINISTRADOR:
                panel.add(criarBotao("Gerenciar Usuários"));
                panel.add(criarBotao("Gerenciar Projetos"));
                panel.add(criarBotao("Gerenciar Equipes"));
                panel.add(criarBotao("Ver Relatórios"));
                break;
            case GERENTE:
                panel.add(criarBotao("Meus Projetos"));
                panel.add(criarBotao("Gerenciar Equipes"));
                panel.add(criarBotao("Ver Relatórios"));
                break;
            case COLABORADOR:
                panel.add(criarBotao("Minhas Tarefas"));
                break;
        }

        panel.revalidate();
        panel.repaint();
    }

    private JButton criarBotao(String texto) {
        JButton button = new JButton(texto);
        button.setPreferredSize(new Dimension(200, 40));
        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidade '" + texto + "' ainda não implementada.", "Em Desenvolvimento", JOptionPane.INFORMATION_MESSAGE);
        });
        return button;
    }
}