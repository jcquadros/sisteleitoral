package entidade;

import java.util.HashSet;
import java.util.Set;

public class Partido {
    private final int numero;
    private final String sigla;
    private final int federacao;
    private int votosLegenda;
    private int votosNominais;
    private Set<Candidato> candidatos = new HashSet<Candidato>();

    public Partido(int numero, String sigla, int federacao) {
        this.numero = numero;
        this.sigla = sigla;
        this.federacao = federacao;
        this.votosNominais = 0;
        this.votosLegenda = 0;
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

    public int getVotosLegenda() {
        return votosLegenda;
    }

    public void addCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    public void addVotosNominais(int votosNominais) {
        this.votosNominais += votosNominais;
    }

    public void addVotosLegenda(int votosLegenda) {
        this.votosLegenda += votosLegenda;
    }

    @Override
    public String toString() {
        return "Partido [numero=" + numero + ", sigla=" + sigla + ", federacao=" + federacao + ", votosLegenda="
                + votosLegenda + ", votosNominais=" + votosNominais + ", candidatos=" + candidatos.size() + "]";
    }

}