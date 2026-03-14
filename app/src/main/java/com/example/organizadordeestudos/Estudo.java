package com.example.organizadordeestudos;

public class Estudo {
    private int id;
    private String disciplina;
    private String horario;

    public Estudo(int id, String disciplina, String horario) {
        this.id = id;
        this.disciplina = disciplina;
        this.horario = horario;
    }

    public int getId() { return id; }
    public String getDisciplina() { return disciplina; }
    public String getHorario() { return horario; }
}