package entidade;

import java.util.HashSet;
import java.util.Set;

public class Partido {
    private final int numero;
    private final String sigla;
    private final int federacao;
    private int votosNominais;
    private Set<Candidato> candidatos = new HashSet<Candidato>();

    public Partido(int numero, String sigla, int federacao, int votosNominais) {
        this.numero = numero;
        this.sigla = sigla;
        this.federacao = federacao;
        this.votosNominais = votosNominais;
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

    public int getVotosNominais() {
        return votosNominais;
    }

    public void addCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

}