package eleicao;

import java.util.Map;
import java.util.TreeMap;

import entidade.Candidato;
import entidade.Partido;
import enums.TipoDestinoVotos;

/**
 * Esta classe representa uma eleição com candidatos e partidos.
 */
public class Eleicao {
    private Map<Integer, Candidato> candidatos;
    private Map<Integer, Partido> partidos;

    /**
     * Constrói uma eleição com os candidatos e partidos especificados.
     *
     * @param candidatos os candidatos participantes na eleição
     * @param partidos os partidos participantes na eleição
     */
    public Eleicao(Map<Integer, Candidato> candidatos, Map<Integer, Partido> partidos) {
        this.candidatos = new TreeMap<>(candidatos);
        this.partidos = new TreeMap<>(partidos);
    }

    /**
     * Retorna um mapa dos candidatos participantes na eleição.
     *
     * @return um novo TreeMap dos candidatos
     */
    public Map<Integer, Candidato> getCandidatos() {
        return new TreeMap<>(candidatos);
    }

    /**
     * Retorna um mapa dos partidos participantes na eleição.
     *
     * @return um novo TreeMap dos partidos
     */
    public Map<Integer, Partido> getPartidos() {
        return new TreeMap<>(partidos);
    }

    /**
     * Processa a votação com base nos votos fornecidos.
     *
     * @param votos um mapa dos votos, onde a chave é o número do candidato ou partido e o valor é o número de votos
     */
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
            }
        }
    }
}
