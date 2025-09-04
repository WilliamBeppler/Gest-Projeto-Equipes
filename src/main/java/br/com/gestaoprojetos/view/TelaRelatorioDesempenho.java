package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.dao.TarefaDAO;
import br.com.gestaoprojetos.dao.UsuarioDAO;
import br.com.gestaoprojetos.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaRelatorioDesempenho extends JFrame {

    private JTable tabelaDesempenho;
    private DefaultTableModel modeloTabela;
    private UsuarioDAO usuarioDAO;
    private TarefaDAO tarefaDAO;

    public TelaRelatorioDesempenho() {
        usuarioDAO = new UsuarioDAO();
        tarefaDAO = new TarefaDAO();

        setTitle("Relatório - Desempenho dos Colaboradores");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- Tabela ---
        String[] colunas = {"ID Colaborador", "Nome Completo", "Tarefas Atribuídas", "Tarefas Concluídas"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaDesempenho = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabelaDesempenho);
        add(scrollPane);

        carregarDadosNaTabela();
    }

    private void carregarDadosNaTabela() {
        modeloTabela.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.listarTodos();

        for (Usuario u : usuarios) {
            // Para cada usuário, usamos os novos métodos do TarefaDAO para obter as contagens
            int tarefasAtribuidas = tarefaDAO.contarTarefasPorResponsavel(u.getId_usuario());
            int tarefasConcluidas = tarefaDAO.contarTarefasConcluidasPorResponsavel(u.getId_usuario());

            modeloTabela.addRow(new Object[]{
                    u.getId_usuario(),
                    u.getNome_completo(),
                    tarefasAtribuidas,
                    tarefasConcluidas
            });
        }
    }
}
