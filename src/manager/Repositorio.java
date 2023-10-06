package manager;

import java.util.HashMap;
import java.util.Map;

import model.Candidato;
import model.Partido;

public class Repositorio {
    private Map<Integer, Partido> partidos = new HashMap<>();
    private Map<Integer, Candidato> candidatos = new HashMap<>();

    public void addPartido(Partido partido) {
        partidos.put(partido.getNumero(), partido);
    }

    public void addCandidato(Candidato candidato) {
        candidatos.put(candidato.getNumero(), candidato);
    }

    public Partido getPartido(int numero) {
        return partidos.get(numero);
    }

    public Candidato getCandidato(int numero) {
        return candidatos.get(numero);
    }
}
