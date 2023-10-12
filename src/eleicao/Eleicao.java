package eleicao;

import java.util.Map;
import java.util.TreeMap;

import entidade.Candidato;
import entidade.Partido;
import entidade.Voto;
import enums.Deferido;
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

    public void processaVotacao(Map<Integer, Voto> votos) {
        for (Voto voto : votos.values()) {
            int numeroCandidato = voto.getNumeroCandidato();
            int quantidadeVotos = voto.getQuantidadeVotos();

            if (candidatos.containsKey(numeroCandidato)) {
                Candidato candidato = candidatos.get(numeroCandidato);
                if (candidato.getTipoDestinoVotos() == TipoDestinoVotos.VALIDO_LEGENDA) {
                    candidato.getPartido().addVotosLegenda(quantidadeVotos);
                } else if (candidato.getSitDeferido() == Deferido.DEFERIDO) {
                    candidato.addVotosNominais(quantidadeVotos);
                }
            } else if (partidos.containsKey(numeroCandidato)) {
                Partido partido = partidos.get(numeroCandidato);
                partido.addVotosLegenda(quantidadeVotos);
            } else {
                System.out.println("Candidato ou Partido não encontrado");
            }

        }
    }

}
