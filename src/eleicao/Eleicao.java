package eleicao;

import java.util.Map;
import java.util.TreeMap;

import entidade.Candidato;
import entidade.Partido;
import enums.TipoDestinoVotos;

public class Eleicao {
    Map<Integer, Candidato> candidatos;
    Map<Integer, Partido> partidos;

    public Eleicao(Map<Integer, Candidato> candidatos, Map<Integer, Partido> partidos) {
        this.candidatos = candidatos;
        this.partidos = partidos;
    }

    public Map<Integer, Candidato> getCandidatos() {
        return new TreeMap<>(candidatos);
    }

    public Map<Integer, Partido> getPartidos() {
        return new TreeMap<>(partidos);
    }

    public void processaVotacao(Map<Integer, Integer> votos) {
        for (Map.Entry<Integer, Integer> entry : votos.entrySet()) {
            int numero = entry.getKey();
            int numeroVotos = entry.getValue();

            if (candidatos.containsKey(numero)) {
                Candidato candidato = candidatos.get(numero);
                if (candidato.getTipoDestinoVotos() == TipoDestinoVotos.VALIDO_LEGENDA) {
                    candidato.getPartido().addVotosLegenda(numeroVotos);
                } else if (candidato.isDeferido()) {
                    candidato.addVotosNominais(numeroVotos);
                    candidato.getPartido().addVotosNominais(numeroVotos);
                }

            } else if (partidos.containsKey(numero)) {
                Partido partido = partidos.get(numero);
                partido.addVotosLegenda(numeroVotos);

            } else {
                System.out.println("Candidato ou Partido não encontrado");
            }

        }

    }

}
