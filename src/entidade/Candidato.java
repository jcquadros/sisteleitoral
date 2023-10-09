package entidade;

import java.time.LocalDate;
import enums.Eleito;
import enums.Genero;
import enums.TipoDestinoVotos;;

public class Candidato {
    private final int numero;
    private final String nome; // nome do candidato
    private Partido partido; // partido do candidato
    private int numVotos;
    private final LocalDate dtNascimento;
    private Eleito eleito; /// eleito ou nao eleito
    private Genero genero; // feminino ou masculino
    private TipoDestinoVotos tipoDestinoDosVotos; // se no for valido os votos serao nulos

    public Candidato(int numero, String nome, Partido partido, LocalDate dtNascimento, Eleito eleito,
            Genero genero, TipoDestinoVotos tipoDestinoVotos) {
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

    public Eleito getEleito() {
        return eleito;
    }

    public Genero getGenero() {
        return genero;
    }

    public TipoDestinoVotos getTipoDestinoDosVotos() {
        return tipoDestinoDosVotos;
    }

}
