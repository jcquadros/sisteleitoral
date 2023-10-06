package model;

import java.time.LocalDate;

public class Candidato {
    private int numero;
    private String nome; // nome do candidato
    private Partido partido; // partido do candidato
    private int numVotos;
    private LocalDate dtNascimento;
    private boolean eleito; /// eleito ou nao eleito
    private int genero; // feminino ou masculino
    private String tipoDestinoDosVotos; // se no for valido os votos serao nulos

    public Candidato(int numero, String nome, Partido partido, LocalDate dtNascimento, boolean eleito,
            int genero, String tipoDestinoVotos) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.numVotos = 0;
        this.dtNascimento = dtNascimento;
        this.eleito = eleito;
        this.genero = genero;
        this.tipoDestinoDosVotos = tipoDestinoVotos;
        partido.addCandidato(this);
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public Partido getPartido() {
        return partido;
    }

    public int getNumVotos() {
        return numVotos;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public boolean isEleito() {
        return eleito;
    }

    public int getGenero() {
        return genero;
    }

    public String getTipoDestinoDosVotos() {
        return tipoDestinoDosVotos;
    }

}
