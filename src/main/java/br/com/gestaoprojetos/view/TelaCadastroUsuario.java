package br.com.gestaoprojetos.view;

import br.com.gestaoprojetos.dao.UsuarioDAO;
import br.com.gestaoprojetos.model.Usuario;
import br.com.gestaoprojetos.util.SegurancaUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class TelaCadastroUsuario extends JFrame {

    private JTextField campoId, campoNome, campoCpf, campoEmail, campoCargo, campoLogin;
    private JPasswordField campoSenha;
    private JComboBox<String> comboPerfil;
    private JButton botaoNovo, botaoSalvar, botaoExcluir;
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    private UsuarioDAO usuarioDAO;

    public TelaCadastroUsuario() {
        usuarioDAO = new UsuarioDAO();

        setTitle("Cadastro de Usuários");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        add(new JLabel("ID:")).setBounds(10, 10, 80, 25);
        campoId = new JTextField();
        campoId.setBounds(100, 10, 80, 25);
        campoId.setEditable(false);
        add(campoId);

        add(new JLabel("Nome Completo:")).setBounds(10, 40, 100, 25);
        campoNome = new JTextField();
        campoNome.setBounds(100, 40, 250, 25);
        add(campoNome);

        add(new JLabel("CPF:")).setBounds(10, 70, 80, 25);
        campoCpf = new JTextField();
        campoCpf.setBounds(100, 70, 150, 25);
        add(campoCpf);

        add(new JLabel("Email:")).setBounds(10, 100, 80, 25);
        campoEmail = new JTextField();
        campoEmail.setBounds(100, 100, 250, 25);
        add(campoEmail);

        add(new JLabel("Cargo:")).setBounds(380, 10, 80, 25);
        campoCargo = new JTextField();
        campoCargo.setBounds(450, 10, 150, 25);
        add(campoCargo);

        add(new JLabel("Login:")).setBounds(380, 40, 80, 25);
        campoLogin = new JTextField();
        campoLogin.setBounds(450, 40, 150, 25);
        add(campoLogin);

        add(new JLabel("Senha:")).setBounds(380, 70, 80, 25);
        campoSenha = new JPasswordField();
        campoSenha.setBounds(450, 70, 150, 25);
        add(campoSenha);

        add(new JLabel("Perfil:")).setBounds(380, 100, 80, 25);
        comboPerfil = new JComboBox<>(new String[]{"Administrador", "Gerente", "Colaborador"});
        comboPerfil.setBounds(450, 100, 150, 25);
        add(comboPerfil);

        botaoNovo = new JButton("Novo");
        botaoNovo.setBounds(10, 140, 80, 25);
        add(botaoNovo);

        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(100, 140, 80, 25);
        add(botaoSalvar);

        botaoExcluir = new JButton("Excluir");
        botaoExcluir.setBounds(190, 140, 80, 25);
        add(botaoExcluir);

        modeloTabela = new DefaultTableModel(new String[]{"ID", "Nome", "Login", "Perfil"}, 0);
        tabelaUsuarios = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        scrollPane.setBounds(10, 180, 760, 370);
        add(scrollPane);

        botaoNovo.addActionListener(e -> limparCampos());
        botaoSalvar.addActionListener(e -> salvarUsuario());
        botaoExcluir.addActionListener(e -> excluirUsuario());

        tabelaUsuarios.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                carregarDadosDoUsuarioSelecionado();
            }
        });

        carregarUsuariosNaTabela();
    }

    private void carregarUsuariosNaTabela() {
        modeloTabela.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        for (Usuario u : usuarios) {
            modeloTabela.addRow(new Object[]{u.getId_usuario(), u.getNome_completo(), u.getLogin(), u.getPerfil()});
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoCpf.setText("");
        campoEmail.setText("");
        campoCargo.setText("");
        campoLogin.setText("");
        campoSenha.setText("");
        comboPerfil.setSelectedIndex(0);
    }

    private void salvarUsuario() {
        if (campoNome.getText().isEmpty() || campoLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e Login são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNome_completo(campoNome.getText());
        usuario.setCpf(campoCpf.getText());
        usuario.setEmail(campoEmail.getText());
        usuario.setCargo(campoCargo.getText());
        usuario.setLogin(campoLogin.getText());
        usuario.setPerfil((String) comboPerfil.getSelectedItem());

        if (campoId.getText().isEmpty()) {
            if (new String(campoSenha.getPassword()).isEmpty()) {
                JOptionPane.showMessageDialog(this, "A senha é obrigatória para novos usuários.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            usuario.setSenha(SegurancaUtil.gerarHashSHA256(new String(campoSenha.getPassword())));
            usuarioDAO.cadastrar(usuario);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        } else {
            usuario.setId_usuario(Integer.parseInt(campoId.getText()));
            usuarioDAO.atualizar(usuario);
            JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!");
        }

        limparCampos();
        carregarUsuariosNaTabela();
    }

    private void excluirUsuario() {
        if (campoId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem a certeza que deseja excluir este usuário?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            usuarioDAO.excluir(Integer.parseInt(campoId.getText()));
            JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso!");
            limparCampos();
            carregarUsuariosNaTabela();
        }
    }

    private void carregarDadosDoUsuarioSelecionado() {
        int linhaSelecionada = tabelaUsuarios.getSelectedRow();
        if (linhaSelecionada != -1) {
            int idUsuario = (int) modeloTabela.getValueAt(linhaSelecionada, 0);

            // Esta é uma simplificação para o nosso exemplo.
            // O ideal seria ter um método no DAO para buscar um único usuário pelo ID
            // e preencher todos os campos. Por agora, vamos apenas preencher o ID
            // para que o botão "Excluir" funcione.
            campoId.setText(String.valueOf(idUsuario));
            // Pode preencher os outros campos se quiser, usando os dados da tabela
            campoNome.setText((String) modeloTabela.getValueAt(linhaSelecionada, 1));
            campoLogin.setText((String) modeloTabela.getValueAt(linhaSelecionada, 2));
            comboPerfil.setSelectedItem(modeloTabela.getValueAt(linhaSelecionada, 3));
        }
    }
}