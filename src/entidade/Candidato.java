package entidade;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

import enums.Genero;
import enums.TipoDestinoVotos;

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

    public Candidato(int numero, String nome, Partido partido, boolean deferido, boolean eleito, Genero genero,
            TipoDestinoVotos tipoDestinoVotos, LocalDate dataNascimento) {
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

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public Partido getPartido() {
        return partido;
    }

    public boolean isDeferido() {
        return deferido;
    }

    public boolean isEleito() {
        return eleito;
    }

    public Genero getGenero() {
        return genero;
    }

    public TipoDestinoVotos getTipoDestinoVotos() {
        return tipoDestinoVotos;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public int getVotosNominais() {
        return votosNominais;
    }

    public void setVotosNominais(int votosNominais) {
        this.votosNominais = votosNominais;
    }

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

        if (this.getVotosNominais() != o.getVotosNominais())
            return o.getVotosNominais() - this.getVotosNominais();
        if (this.getDataNascimento() != null && o.getDataNascimento() != null)
            return this.getDataNascimento().compareTo(o.getDataNascimento());
        return 0;

    }
}
