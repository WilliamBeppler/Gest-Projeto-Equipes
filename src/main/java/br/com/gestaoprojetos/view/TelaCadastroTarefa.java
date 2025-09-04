package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.dao.ProjetoDAO;
import br.com.gestaoprojetos.dao.TarefaDAO;
import br.com.gestaoprojetos.dao.UsuarioDAO;
import br.com.gestaoprojetos.model.Projeto;
import br.com.gestaoprojetos.model.Tarefa;
import br.com.gestaoprojetos.model.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaCadastroTarefa extends JFrame {

    private JTable tabelaTarefas;
    private DefaultTableModel modeloTabela;
    private TarefaDAO tarefaDAO;
    private ProjetoDAO projetoDAO;
    private UsuarioDAO usuarioDAO;

    // Campos do formulário
    private JTextField campoTitulo, campoDataFim;
    private JTextArea areaDescricao;
    private JComboBox<String> comboStatus;
    private JComboBox<ProjetoItem> comboProjetos;
    private JComboBox<UsuarioItem> comboResponsaveis;
    private JButton botaoSalvar;

    public TelaCadastroTarefa() {
        tarefaDAO = new TarefaDAO();
        projetoDAO = new ProjetoDAO();
        usuarioDAO = new UsuarioDAO();

        setTitle("Cadastro de Tarefas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // --- Formulário ---
        add(new JLabel("Título:")).setBounds(10, 10, 80, 25);
        campoTitulo = new JTextField();
        campoTitulo.setBounds(150, 10, 250, 25);
        add(campoTitulo);

        add(new JLabel("Descrição:")).setBounds(10, 40, 80, 25);
        areaDescricao = new JTextArea();
        JScrollPane scrollDescricao = new JScrollPane(areaDescricao);
        scrollDescricao.setBounds(150, 40, 250, 80);
        add(scrollDescricao);

        add(new JLabel("Data Fim Prev. (dd/MM/yyyy):")).setBounds(420, 10, 180, 25);
        campoDataFim = new JTextField();
        campoDataFim.setBounds(600, 10, 100, 25);
        add(campoDataFim);

        add(new JLabel("Status:")).setBounds(420, 40, 80, 25);
        comboStatus = new JComboBox<>(new String[]{"Pendente", "Em execução", "Concluída"});
        comboStatus.setBounds(600, 40, 150, 25);
        add(comboStatus);

        add(new JLabel("Projeto:")).setBounds(420, 70, 80, 25);
        comboProjetos = new JComboBox<>();
        comboProjetos.setBounds(600, 70, 150, 25);
        add(comboProjetos);
        carregarProjetos();

        add(new JLabel("Responsável:")).setBounds(420, 100, 150, 25);
        comboResponsaveis = new JComboBox<>();
        comboResponsaveis.setBounds(600, 100, 150, 25);
        add(comboResponsaveis);
        carregarResponsaveis();

        botaoSalvar = new JButton("Salvar Nova Tarefa");
        botaoSalvar.setBounds(10, 140, 180, 25);
        add(botaoSalvar);

        // --- Tabela ---
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Título", "Status", "Data Fim Prevista"}, 0);
        tabelaTarefas = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaTarefas);
        scrollTabela.setBounds(10, 180, 760, 370);
        add(scrollTabela);

        // --- Ações ---
        botaoSalvar.addActionListener(e -> salvarTarefa());

        carregarTarefasNaTabela();
    }

    private void carregarProjetos() {
        List<Projeto> projetos = projetoDAO.listarTodos();
        for (Projeto p : projetos) {
            comboProjetos.addItem(new ProjetoItem(p.getId_projeto(), p.getNome()));
        }
    }

    private void carregarResponsaveis() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        for (Usuario u : usuarios) {
            comboResponsaveis.addItem(new UsuarioItem(u.getId_usuario(), u.getNome_completo()));
        }
    }

    private void carregarTarefasNaTabela() {
        modeloTabela.setRowCount(0);
        List<Tarefa> tarefas = tarefaDAO.listarTodas();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Tarefa t : tarefas) {
            modeloTabela.addRow(new Object[]{
                    t.getId_tarefa(),
                    t.getTitulo(),
                    t.getStatus(),
                    t.getData_fim_prevista().format(formatter)
            });
        }
    }

    private void salvarTarefa() {
        try {
            Tarefa tarefa = new Tarefa();
            tarefa.setTitulo(campoTitulo.getText());
            tarefa.setDescricao(areaDescricao.getText());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            tarefa.setData_fim_prevista(LocalDate.parse(campoDataFim.getText(), formatter));

            tarefa.setStatus((String) comboStatus.getSelectedItem());

            ProjetoItem projetoSelecionado = (ProjetoItem) comboProjetos.getSelectedItem();
            tarefa.setId_projeto(projetoSelecionado.getId());

            UsuarioItem responsavelSelecionado = (UsuarioItem) comboResponsaveis.getSelectedItem();
            tarefa.setId_responsavel(responsavelSelecionado.getId());

            tarefaDAO.cadastrar(tarefa);

            JOptionPane.showMessageDialog(this, "Tarefa cadastrada com sucesso!");
            carregarTarefasNaTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar tarefa. Verifique todos os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- Classes internas para os ComboBoxes ---

    private static class ProjetoItem {
        private int id;
        private String nome;

        public ProjetoItem(int id, String nome) { this.id = id; this.nome = nome; }
        public int getId() { return id; }
        @Override public String toString() { return nome; }
    }

    private static class UsuarioItem {
        private int id;
        private String nome;

        public UsuarioItem(int id, String nome) { this.id = id; this.nome = nome; }
        public int getId() { return id; }
        @Override public String toString() { return nome; }
    }
}
