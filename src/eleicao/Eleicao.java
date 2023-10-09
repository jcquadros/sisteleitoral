package eleicao;

import java.util.Map;
import java.util.TreeMap;

import entidade.Candidato;
import entidade.Partido;

public class Eleicao {
    Map<Integer, Candidato> candidatos;
    Map<Integer, Partido> partidos;

    public Eleicao(TreeMap<Integer, Candidato> candidatos, TreeMap<Integer, Partido> partidos) {
        this.candidatos = candidatos;
        this.partidos = partidos;
    }

    public TreeMap<Integer, Candidato> getCandidatos() {
        return new TreeMap<>(candidatos);
    }

    public TreeMap<Integer, Partido> getPartidos() {
        return new TreeMap<>(partidos);
    }
}
