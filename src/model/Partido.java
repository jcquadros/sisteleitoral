package model;

import java.util.HashSet;
import java.util.Set;

public class Partido {
    private int numero;
    private String sigla;
    private int federacao;
    private Set<Candidato> candidatos = new HashSet<Candidato>();

    public Partido(int numero, String sigla, int federacao) {
        this.numero = numero;
        this.sigla = sigla;
        this.federacao = federacao;
    }

    public int getNumero() {
        return numero;
    }

    public String getSigla() {
        return sigla;
    }

    public int getFederacao() {
        return federacao;
    }

    public Set<Candidato> getCandidatos() {
        return new HashSet<>(candidatos);
    }

    public void addCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

}