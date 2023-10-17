package eleicao;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import comparadores.PartidoComparador;
import entidade.Candidato;
import entidade.Partido;
import enums.Eleito;

public class RelatoriosEleicao {
    private List<Candidato> candidatos;
    private List<Partido> partidos;
    private int cargo;

    public RelatoriosEleicao(Map<Integer, Candidato> candidatos, Map<Integer, Partido> partidos, int cargo) {
        this.candidatos = candidatos.values().stream().sorted(Comparable::compareTo).collect(Collectors.toList());
        this.partidos = partidos.values().stream().sorted(Comparable::compareTo).collect(Collectors.toList());
        this.cargo = cargo;
    }

    private int numeroDeVagas() {
        return (int) candidatos.stream().filter(candidato -> candidato.getSitEleito() == Eleito.ELEITO).count();
    }

    public String relatorioNumeroDeVagas() {
        return "Número de vagas: " + numeroDeVagas() + "\n";
    }

    public String relatorioCandidatosEleitos() {
        String response = this.cargo == 6 ? "Deputados federais eleitos:\n" : "Deputados estaduais eleitos:\n";
        List<Candidato> candidatosEleitos = candidatos.stream()
                .filter(candidato -> candidato.getSitEleito() == Eleito.ELEITO)
                .collect(Collectors.toList());

        int i = 0;
        for (Candidato c : candidatosEleitos)
            response += (++i) + " - " + c;

        return response;
    }

    public String relatorioCandidatosMaisVotados() {
        String response = "Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):\n";
        List<Candidato> candidatosOrdenadosVotos = candidatos.stream()
                .limit(numeroDeVagas())
                .collect(Collectors.toList());

        int i = 0;
        for (Candidato c : candidatosOrdenadosVotos) {
            response += (++i) + " - " + c;
        }

        return response;
    }

    public String relatorioCandidatosNaoEleitosEleitosMajoritariamente() {
        String response = "Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n";
        List<Candidato> candidatoNaoEleitos = candidatos.stream()
                .limit(numeroDeVagas())
                .filter(candidato -> candidato.getSitEleito() == Eleito.NAO_ELEITO)
                .collect(Collectors.toList());

        int i = 0;
        for (Candidato c : candidatoNaoEleitos) {
            response += (++i) + " - " + c;
        }

        return response;
    }

    public String relatorioCandidatosEleitosNaoEleitosMajoritariamente() {
        String response = "Eleitos, que se beneficiaram do sistema proporcional:\n";
        List<Candidato> candidatosEleitos = candidatos.stream()
                .filter(candidato -> candidato.getSitEleito() == Eleito.ELEITO)
                .collect(Collectors.toList());

        List<Candidato> candidatosMajoritarios = candidatos.stream()
                .limit(candidatosEleitos.size())
                .collect(Collectors.toList());

        candidatosEleitos.removeAll(candidatosMajoritarios);

        int i = 0;
        for (Candidato c : candidatosEleitos) {
            response += (++i) + " - " + c;
        }
        return response;
    }

    public String relatorioVotosTotais() {
        String response = "Votação dos partidos e número de candidatos eleitos:\n";

        int i = 0;
        for (Partido p : partidos) {
            int candEleitos = 0;
            for (Candidato c : p.getCandidatos()) {
                candEleitos += c.getSitEleito() == Eleito.ELEITO ? 1 : 0;
            }

            response += (++i) + " - " + p.getSigla() + " - " + p.getNumero() + ", "
                    + (p.getVotosLegenda() + p.getVotosNominais()) + " votos (" + p.getVotosNominais() + " nominais e "
                    + p.getVotosLegenda() + " de legenda), " + candEleitos + " candidatos eleitos\n";
        }
        return response;
    }

    public String relatorioPrimeiroUltimoPorPartido() {
        String response = "Primeiro e último colocados de cada partido:\n";
        int i = 0;
        List<Partido> partidosOrdenados = new LinkedList<>();

        for (Partido p : partidos) {
            List<Candidato> candidatosPartido = p.getCandidatos().stream().collect(Collectors.toList());
            Candidato primeiro = candidatosPartido.stream().max(Comparable::compareTo).get();
            Candidato ultimo = candidatosPartido.stream().min(Comparable::compareTo).get();

            if (primeiro != null && ultimo != null) {
                Partido partido = new Partido(p.getNumero(), p.getSigla(), p.getFederacao());
                partido.addCandidato(primeiro);
                if (primeiro != ultimo)
                    partido.addCandidato(ultimo);
                partidosOrdenados.add(partido);
            }
        }

        partidosOrdenados.sort(new PartidoComparador());

        for (Partido p : partidosOrdenados) {
            response += (++i) + " - " + p.getSigla() + " - " + p.getNumero() + ", ";
            Candidato c1 = (Candidato) p.getCandidatos().toArray()[0];
            response += c1.getNome() + " (" + c1.getNumero() + ", " + c1.getVotosNominais() + " votos)";
            if (p.getCandidatos().size() > 1) {
                Candidato c2 = (Candidato) p.getCandidatos().toArray()[1];
                response += " / " + c2.getNome() + " (" + c2.getNumero() + ", " + c2.getVotosNominais() + " votos)\n";
            }
        }

        return response;
    }

}
