package entidade;

import java.util.HashSet;
import java.util.Set;

/**
 * Esta classe representa um partido político.
 */
public class Partido implements Comparable<Partido> {
    private final int numero;
    private final String sigla;
    private final int federacao;
    private int votosLegenda;
    private int votosNominais;
    private Set<Candidato> candidatos = new HashSet<Candidato>();

    /**
     * Constrói um objeto Partido com o número, sigla e federação especificados.
     *
     * @param numero O número do partido.
     * @param sigla  A sigla do partido.
     * @param federacao O número da federação do partido.
     */
    public Partido(int numero, String sigla, int federacao) {
        this.numero = numero;
        this.sigla = sigla;
        this.federacao = federacao;
        this.votosLegenda = 0;
        this.votosNominais = 0;
    }

    /**
     * Obtém o número do partido.
     *
     * @return O número do partido.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtém a sigla do partido.
     *
     * @return A sigla do partido.
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Obtém o número da federação do partido.
     *
     * @return O número da federação do partido.
     */
    public int getFederacao() {
        return federacao;
    }

    /**
     * Obtém a lista de candidatos filiados ao partido.
     *
     * @return Um conjunto de candidatos filiados ao partido.
     */
    public Set<Candidato> getCandidatos() {
        return new HashSet<>(candidatos);
    }

    /**
     * Obtém a quantidade de votos de legenda recebidos pelo partido.
     *
     * @return A quantidade de votos de legenda recebidos pelo partido.
     */
    public int getVotosLegenda() {
        return votosLegenda;
    }

    /**
     * Obtém a quantidade de votos nominais recebidos pelo partido.
     *
     * @return A quantidade de votos nominais recebidos pelo partido.
     */
    public int getVotosNominais() {
        return votosNominais;
    }

    /**
     * Adiciona um candidato filiado ao partido.
     *
     * @param candidato O candidato a ser adicionado ao partido.
     */
    public void addCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    /**
     * Adiciona votos de legenda ao partido.
     *
     * @param votosLegenda A quantidade de votos de legenda a serem adicionados ao partido.
     */
    public void addVotosLegenda(int votosLegenda) {
        this.votosLegenda += votosLegenda;
    }

    /**
     * Adiciona votos nominais ao partido.
     *
     * @param numeroVotos A quantidade de votos nominais a serem adicionados ao partido.
     */
    public void addVotosNominais(int numeroVotos) {
        this.votosNominais += numeroVotos;
    }

    @Override
    public String toString() {
        return "Partido [numero=" + numero + ", sigla=" + sigla + ", federacao=" + federacao + ", votosLegenda="
                + votosLegenda + ", candidatos=" + candidatos.size() + "]";
    }

    @Override
    public int compareTo(Partido o) {
        if (o == null)
            throw new NullPointerException("Partido não pode ser nulo");

        if (this.votosLegenda + this.votosNominais != o.votosLegenda + o.votosNominais) {
            return (o.votosLegenda + o.votosNominais) - (this.votosLegenda + this.votosNominais);
        }
        return this.numero - o.numero;
    }
}
