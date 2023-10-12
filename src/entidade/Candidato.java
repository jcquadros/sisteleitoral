package entidade;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

import enums.Deferido;
import enums.Eleito;
import enums.Genero;
import enums.TipoDestinoVotos;

public class Candidato implements Comparable<Candidato> {
    private int numero;
    private String nome;
    private Partido partido;
    private Deferido sitDeferido; // deferido ou indeferido
    private Eleito sitEleito; // eleito ou não eleito
    private Genero genero;
    private TipoDestinoVotos tipoDestinoVotos; // votos válidos, nulos, etc
    private LocalDate dataNascimento;
    private int votosNominais;

    public Candidato(int numero, String nome, Partido partido, Deferido sitDeferido, Eleito sitEleito, Genero genero,
            TipoDestinoVotos tipoDestinoVotos, LocalDate dataNascimento) {

        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.sitDeferido = sitDeferido;
        this.sitEleito = sitEleito;
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

    public Deferido getSitDeferido() {
        return sitDeferido;
    }

    public Eleito getSitEleito() {
        return sitEleito;
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
