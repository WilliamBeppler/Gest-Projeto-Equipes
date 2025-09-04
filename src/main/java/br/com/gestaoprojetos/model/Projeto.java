package br.com.gestaoprojetos.model;

import java.time.LocalDate;

public class Projeto {

    private int id_projeto;
    private String nome;
    private String descricao;
    private LocalDate data_inicio;
    private LocalDate data_fim_prevista;
    private LocalDate data_fim_real;
    private String status; // 'Planeado', 'Em andamento', 'Concluído', 'Cancelado'
    private int id_gerente;

    // Getters e Setters

    public int getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(int id_projeto) {
        this.id_projeto = id_projeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim_prevista() {
        return data_fim_prevista;
    }

    public void setData_fim_prevista(LocalDate data_fim_prevista) {
        this.data_fim_prevista = data_fim_prevista;
    }

    public LocalDate getData_fim_real() {
        return data_fim_real;
    }

    public void setData_fim_real(LocalDate data_fim_real) {
        this.data_fim_real = data_fim_real;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_gerente() {
        return id_gerente;
    }

    public void setId_gerente(int id_gerente) {
        this.id_gerente = id_gerente;
    }
}
