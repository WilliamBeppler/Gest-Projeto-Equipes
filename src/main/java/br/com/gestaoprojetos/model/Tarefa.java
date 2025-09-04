package br.com.gestaoprojetos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Tarefa {

    private int id_tarefa;
    private String titulo;
    private String descricao;
    private LocalDate data_inicio_prevista;
    private LocalDate data_fim_prevista;
    private LocalDateTime data_inicio_real;
    private LocalDateTime data_fim_real;
    private String status; // 'Pendente', 'Em execução', 'Concluída'
    private int id_projeto;
    private int id_responsavel;

    // Getters e Setters

    public int getId_tarefa() {
        return id_tarefa;
    }

    public void setId_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData_inicio_prevista() {
        return data_inicio_prevista;
    }

    public void setData_inicio_prevista(LocalDate data_inicio_prevista) {
        this.data_inicio_prevista = data_inicio_prevista;
    }

    public LocalDate getData_fim_prevista() {
        return data_fim_prevista;
    }

    public void setData_fim_prevista(LocalDate data_fim_prevista) {
        this.data_fim_prevista = data_fim_prevista;
    }

    public LocalDateTime getData_inicio_real() {
        return data_inicio_real;
    }

    public void setData_inicio_real(LocalDateTime data_inicio_real) {
        this.data_inicio_real = data_inicio_real;
    }

    public LocalDateTime getData_fim_real() {
        return data_fim_real;
    }

    public void setData_fim_real(LocalDateTime data_fim_real) {
        this.data_fim_real = data_fim_real;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(int id_projeto) {
        this.id_projeto = id_projeto;
    }

    public int getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(int id_responsavel) {
        this.id_responsavel = id_responsavel;
    }
}
