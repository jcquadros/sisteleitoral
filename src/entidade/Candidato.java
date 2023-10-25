package entidade;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

import enums.Genero;
import enums.TipoDestinoVotos;

/**
 * Esta classe representa um candidato em uma eleição.
 */
public class Candidato implements Comparable<Candidato> {
    private int numero;
    private String nome;
    private Partido partido;
    private boolean deferido; // deferido ou indeferido
    private boolean eleito; // eleito ou não eleito
    private Genero genero;
    private TipoDestinoVotos tipoDestinoVotos; // votos válidos, nulos, etc
    private LocalDate dataNascimento;
    private int votosNominais;

    /**
     * Constrói um objeto Candidato.
     *
     * @param numero            O número do candidato.
     * @param nome              O nome do candidato.
     * @param partido           O partido do candidato.
     * @param deferido          Indica se o candidato foi deferido.
     * @param eleito            Indica se o candidato foi eleito.
     * @param genero            O gênero do candidato.
     * @param tipoDestinoVotos  O tipo de destino dos votos.
     * @param dataNascimento    A data de nascimento do candidato.
     */
    public Candidato(int numero, String nome, Partido partido, boolean deferido, boolean eleito,
                    Genero genero, TipoDestinoVotos tipoDestinoVotos, LocalDate dataNascimento) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.deferido = deferido;
        this.eleito = eleito;
        this.genero = genero;
        this.tipoDestinoVotos = tipoDestinoVotos;
        this.dataNascimento = dataNascimento;
        this.votosNominais = 0;
        partido.addCandidato(this);
    }

    /**
     * Obtém o número do candidato.
     *
     * @return O número do candidato.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtém o nome do candidato.
     *
     * @return O nome do candidato.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o partido do candidato.
     *
     * @return O partido do candidato.
     */
    public Partido getPartido() {
        return partido;
    }

    /**
     * Verifica se o candidato foi deferido.
     *
     * @return `true` se o candidato foi deferido, caso contrário, `false`.
     */
    public boolean isDeferido() {
        return deferido;
    }

    /**
     * Verifica se o candidato foi eleito.
     *
     * @return `true` se o candidato foi eleito, caso contrário, `false`.
     */
    public boolean isEleito() {
        return eleito;
    }

    /**
     * Obtém o gênero do candidato.
     *
     * @return O gênero do candidato.
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Obtém o tipo de destino dos votos do candidato.
     *
     * @return O tipo de destino dos votos do candidato.
     */
    public TipoDestinoVotos getTipoDestinoVotos() {
        return tipoDestinoVotos;
    }

    /**
     * Obtém a data de nascimento do candidato.
     *
     * @return A data de nascimento do candidato.
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Obtém a quantidade de votos nominais recebidos pelo candidato.
     *
     * @return A quantidade de votos nominais.
     */
    public int getVotosNominais() {
        return votosNominais;
    }

    /**
     * Define a quantidade de votos nominais recebidos pelo candidato.
     *
     * @param votosNominais A quantidade de votos nominais a ser definida.
     */
    public void setVotosNominais(int votosNominais) {
        this.votosNominais = votosNominais;
    }

    /**
     * Adiciona votos nominais ao candidato.
     *
     * @param votosNominais A quantidade de votos nominais a serem adicionados.
     */
    public void addVotosNominais(int votosNominais) {
        this.votosNominais += votosNominais;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        String response = (partido.getFederacao() != -1) ? "*" : "";
        response += nome + " (" + partido.getSigla() + ", " + nf.format(votosNominais) + " votos)\n";
        return response;
    }

    @Override
    public int compareTo(Candidato o) {
        if (o == null)
            throw new NullPointerException("Candidato não pode ser nulo");

        if (this.getVotosNominais() != o.getVotosNominais())
            return o.getVotosNominais() - this.getVotosNominais();

        if (this.getDataNascimento() != null && o.getDataNascimento() != null)
            return this.getDataNascimento().compareTo(o.getDataNascimento());

        return 0;
    }
}
