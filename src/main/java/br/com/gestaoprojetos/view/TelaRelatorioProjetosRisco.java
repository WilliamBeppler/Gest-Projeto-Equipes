package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.dao.ProjetoDAO;
import br.com.gestaoprojetos.dao.UsuarioDAO;
import br.com.gestaoprojetos.model.Projeto;
import br.com.gestaoprojetos.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaRelatorioProjetosRisco extends JFrame {

    private JTable tabelaProjetosRisco;
    private DefaultTableModel modeloTabela;
    private ProjetoDAO projetoDAO;
    private UsuarioDAO usuarioDAO;

    public TelaRelatorioProjetosRisco() {
        projetoDAO = new ProjetoDAO();
        usuarioDAO = new UsuarioDAO();

        setTitle("Relatório - Projetos com Risco de Atraso");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // --- Tabela ---
        String[] colunas = {"ID Projeto", "Nome do Projeto", "Data Fim Prevista", "Gerente Responsável"};
        modeloTabela = new DefaultTableModel(colunas, 0);
        tabelaProjetosRisco = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabelaProjetosRisco);
        add(scrollPane);

        carregarDadosNaTabela();
    }

    private void carregarDadosNaTabela() {
        modeloTabela.setRowCount(0);
        List<Projeto> projetos = projetoDAO.listarProjetosEmRisco(); // Usando o novo método
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Projeto p : projetos) {
            Usuario gerente = usuarioDAO.buscarPorId(p.getId_gerente());
            String nomeGerente = (gerente != null) ? gerente.getNome_completo() : "Não definido";

            modeloTabela.addRow(new Object[]{
                    p.getId_projeto(),
                    p.getNome(),
                    p.getData_fim_prevista().format(formatter),
                    nomeGerente
            });
        }
    }
}
