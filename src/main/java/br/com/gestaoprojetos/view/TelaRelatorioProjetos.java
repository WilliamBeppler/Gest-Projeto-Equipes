package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.dao.ProjetoDAO;
import br.com.gestaoprojetos.dao.UsuarioDAO;
import br.com.gestaoprojetos.model.Projeto;
import br.com.gestaoprojetos.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaRelatorioProjetos extends JFrame {

    private JTable tabelaProjetos;
    private DefaultTableModel modeloTabela;
    private ProjetoDAO projetoDAO;
    private UsuarioDAO usuarioDAO;

    public TelaRelatorioProjetos() {
        projetoDAO = new ProjetoDAO();
        usuarioDAO = new UsuarioDAO();

        setTitle("Relatório - Andamento dos Projetos");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- Tabela ---
        String[] colunas = {"ID", "Nome do Projeto", "Status", "Data Início", "Data Fim Prevista", "Gerente Responsável"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaProjetos = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabelaProjetos);
        add(scrollPane); // Adiciona o painel de rolagem ao frame

        carregarDadosNaTabela();
    }

    private void carregarDadosNaTabela() {
        modeloTabela.setRowCount(0); // Limpa a tabela antes de carregar
        List<Projeto> projetos = projetoDAO.listarTodos();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Projeto p : projetos) {
            // Para cada projeto, buscamos o nome do gerente usando o novo método no DAO
            Usuario gerente = usuarioDAO.buscarPorId(p.getId_gerente());
            String nomeGerente = (gerente != null) ? gerente.getNome_completo() : "Não definido";

            modeloTabela.addRow(new Object[]{
                    p.getId_projeto(),
                    p.getNome(),
                    p.getStatus(),
                    p.getData_inicio().format(formatter),
                    p.getData_fim_prevista().format(formatter),
                    nomeGerente
            });
        }
    }
}
