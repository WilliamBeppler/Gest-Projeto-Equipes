package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    private JLabel labelBoasVindas;

    public TelaPrincipal(Usuario usuarioLogado) {
        setTitle("Sistema de Gestão de Projetos - Painel Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuCadastros = new JMenu("Cadastros");
        JMenuItem itemUsuarios = new JMenuItem("Usuários");
        JMenuItem itemProjetos = new JMenuItem("Projetos");
        JMenuItem itemEquipas = new JMenuItem("Equipas (não implementado)");
        JMenuItem itemTarefas = new JMenuItem("Tarefas");
        menuCadastros.add(itemUsuarios);
        menuCadastros.add(itemProjetos);
        menuCadastros.add(itemEquipas);
        menuCadastros.add(itemTarefas);

        JMenu menuRelatorios = new JMenu("Relatórios");
        JMenuItem itemRelatorioProjetos = new JMenuItem("Andamento dos Projetos");
        JMenuItem itemRelatorioDesempenho = new JMenuItem("Desempenho dos Colaboradores");
        JMenuItem itemRelatorioRisco = new JMenuItem("Projetos em Risco"); // NOVO ITEM
        menuRelatorios.add(itemRelatorioProjetos);
        menuRelatorios.add(itemRelatorioDesempenho);
        menuRelatorios.add(itemRelatorioRisco); // NOVO ITEM ADICIONADO AO MENU

        menuBar.add(menuCadastros);
        menuBar.add(menuRelatorios);
        setJMenuBar(menuBar);

        labelBoasVindas = new JLabel("Bem-vindo(a), " + usuarioLogado.getNome_completo() + "!");
        labelBoasVindas.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelBoasVindas);

        // --- Ações dos Menus ---
        itemUsuarios.addActionListener(e -> new TelaCadastroUsuario().setVisible(true));
        itemProjetos.addActionListener(e -> new TelaCadastroProjeto().setVisible(true));
        itemTarefas.addActionListener(e -> new TelaCadastroTarefa().setVisible(true));
        itemRelatorioProjetos.addActionListener(e -> new TelaRelatorioProjetos().setVisible(true));
        itemRelatorioDesempenho.addActionListener(e -> new TelaRelatorioDesempenho().setVisible(true));

        // NOVA AÇÃO PARA O RELATÓRIO DE RISCO
        itemRelatorioRisco.addActionListener(e -> new TelaRelatorioProjetosRisco().setVisible(true));
    }
}