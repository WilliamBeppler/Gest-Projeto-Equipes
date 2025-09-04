package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.dao.ProjetoDAO;
import br.com.gestaoprojetos.dao.UsuarioDAO;
import br.com.gestaoprojetos.model.Projeto;
import br.com.gestaoprojetos.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

public class TelaCadastroProjeto extends JFrame {

    private JTable tabelaProjetos;
    private DefaultTableModel modeloTabela;
    private ProjetoDAO projetoDAO;
    private UsuarioDAO usuarioDAO;

    // Campos do formulário
    private JTextField campoNome, campoDataInicio, campoDataFim;
    private JTextArea areaDescricao;
    private JComboBox<String> comboStatus;
    private JComboBox<UsuarioItem> comboGerentes;
    private JButton botaoSalvar;

    public TelaCadastroProjeto() {
        projetoDAO = new ProjetoDAO();
        usuarioDAO = new UsuarioDAO();

        setTitle("Cadastro de Projetos");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // --- Formulário ---
        add(new JLabel("Nome:")).setBounds(10, 10, 80, 25);
        campoNome = new JTextField();
        campoNome.setBounds(120, 10, 250, 25);
        add(campoNome);

        add(new JLabel("Descrição:")).setBounds(10, 40, 80, 25);
        areaDescricao = new JTextArea();
        JScrollPane scrollDescricao = new JScrollPane(areaDescricao);
        scrollDescricao.setBounds(120, 40, 250, 80);
        add(scrollDescricao);

        add(new JLabel("Data Início (dd/MM/yyyy):")).setBounds(400, 10, 150, 25);
        campoDataInicio = new JTextField();
        campoDataInicio.setBounds(550, 10, 100, 25);
        add(campoDataInicio);

        add(new JLabel("Data Fim Prev. (dd/MM/yyyy):")).setBounds(400, 40, 150, 25);
        campoDataFim = new JTextField();
        campoDataFim.setBounds(550, 40, 100, 25);
        add(campoDataFim);

        add(new JLabel("Status:")).setBounds(400, 70, 80, 25);
        comboStatus = new JComboBox<>(new String[]{"Planeado", "Em andamento", "Concluído", "Cancelado"});
        comboStatus.setBounds(550, 70, 150, 25);
        add(comboStatus);

        add(new JLabel("Gerente Responsável:")).setBounds(400, 100, 150, 25);
        comboGerentes = new JComboBox<>();
        comboGerentes.setBounds(550, 100, 150, 25);
        add(comboGerentes);
        carregarGerentes();

        botaoSalvar = new JButton("Salvar Novo Projeto");
        botaoSalvar.setBounds(10, 140, 180, 25);
        add(botaoSalvar);

        // --- Tabela ---
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Nome", "Status", "Data Fim Prevista"}, 0);
        tabelaProjetos = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaProjetos);
        scrollTabela.setBounds(10, 180, 760, 370);
        add(scrollTabela);

        // --- Ações ---
        botaoSalvar.addActionListener(e -> salvarProjeto());

        carregarProjetosNaTabela();
    }

    private void carregarGerentes() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        for (Usuario u : usuarios) {
            // Adicionamos apenas Gerentes e Administradores como possíveis gerentes
            if ("Gerente".equals(u.getPerfil()) || "Administrador".equals(u.getPerfil())) {
                comboGerentes.addItem(new UsuarioItem(u.getId_usuario(), u.getNome_completo()));
            }
        }
    }

    private void carregarProjetosNaTabela() {
        modeloTabela.setRowCount(0);
        List<Projeto> projetos = projetoDAO.listarTodos();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Projeto p : projetos) {
            modeloTabela.addRow(new Object[]{
                    p.getId_projeto(),
                    p.getNome(),
                    p.getStatus(),
                    p.getData_fim_prevista().format(formatter)
            });
        }
    }

    private void salvarProjeto() {
        try {
            Projeto projeto = new Projeto();
            projeto.setNome(campoNome.getText());
            projeto.setDescricao(areaDescricao.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            projeto.setData_inicio(LocalDate.parse(campoDataInicio.getText(), formatter));
            projeto.setData_fim_prevista(LocalDate.parse(campoDataFim.getText(), formatter));

            projeto.setStatus((String) comboStatus.getSelectedItem());

            UsuarioItem gerenteSelecionado = (UsuarioItem) comboGerentes.getSelectedItem();
            projeto.setId_gerente(gerenteSelecionado.getId());

            projetoDAO.cadastrar(projeto);

            JOptionPane.showMessageDialog(this, "Projeto cadastrado com sucesso!");
            carregarProjetosNaTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar projeto. Verifique os dados (especialmente as datas).", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Classe interna para guardar o ID e o Nome do usuário no ComboBox
    private static class UsuarioItem {
        private int id;
        private String nome;

        public UsuarioItem(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public int getId() {
            return id;
        }

        @Override
        public String toString() {
            return nome; // O que aparece no ComboBox
        }
    }
}