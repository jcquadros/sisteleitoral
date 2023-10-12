package eleicao;

import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import entidade.Candidato;
import entidade.Partido;
import enums.Eleito;

public class RelatoriosEleicao {

    public String relatorioNumeroDeVagas(Map<Integer, Candidato> candidatos) {
        return "Número de vagas: " + numeroDeVagas(candidatos) + "\n";
    }

    private int numeroDeVagas(Map<Integer, Candidato> candidatos) {
        int candidatosEleitos = (int) candidatos.values().stream()
                .filter(candidato -> candidato.getSitEleito() == Eleito.ELEITO).count();

        return candidatosEleitos;
    }

    public String relatorioCandidatosEleitos(Map<Integer, Candidato> candidatos, int cargo) {
        String response = cargo == 6 ? "Deputados federais eleitos:\n" : "Deputados estaduais eleitos:\n";
        List<Candidato> candidatosEleitos = candidatos.values().stream()
                .filter(candidato -> candidato.getSitEleito() == Eleito.ELEITO)
                .sorted(Comparable::compareTo)
                .collect(Collectors.toList());

        int i = 0;
        for (Candidato c : candidatosEleitos)
            response += (++i) + " - " + c;

        return response;
    }

    public String relatorioCandidatosMaisVotados(Map<Integer, Candidato> candidatos) {
        String response = "Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):\n";
        List<Candidato> candidatosOrdenadosVotos = candidatos.values().stream()
                .sorted(Comparable::compareTo)
                .limit(numeroDeVagas(candidatos))
                .collect(Collectors.toList());

        int i = 0;
        for (Candidato c : candidatosOrdenadosVotos) {
            response += (++i) + " - " + c;
        }

        return response;
    }

    public String relatorioCandidatosNaoEleitosEleitosMajoritariamente(Map<Integer, Candidato> candidatos) {
        String response = "Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n";
        List<Candidato> candidatoNaoEleitos = candidatos.values().stream()
                .sorted(Comparable::compareTo)
                .limit(numeroDeVagas(candidatos))
                .filter(candidato -> candidato.getSitEleito() == Eleito.NAO_ELEITO)
                .collect(Collectors.toList());

        int i = 0;
        for (Candidato c : candidatoNaoEleitos) {
            response += (++i) + " - " + c;
        }

        return response;
    }

    public String relatorioCandidatosEleitosNaoEleitosMajoritariamente(Map<Integer, Candidato> candidatos) {
        String response = "Eleitos, que se beneficiaram do sistema proporcional:\n";
        List<Candidato> candidatosEleitos = candidatos.values().stream()
                .sorted(Comparable::compareTo)
                .filter(candidato -> candidato.getSitEleito() == Eleito.ELEITO)
                .collect(Collectors.toList());

        List<Candidato> candidatosMajoritarios = candidatos.values().stream()
                .sorted(Comparable::compareTo)
                .limit(candidatosEleitos.size())
                .collect(Collectors.toList());

        candidatosEleitos.removeAll(candidatosMajoritarios);

        int i = 0;
        for (Candidato c : candidatosEleitos) {
            response += (++i) + " - " + c;
        }
        return response;
    }

    public void relatorioCandidatosPorIdade(Map<Integer, Candidato> candidatos) {

        List<Candidato> candidatosOrdenadosIdade = candidatos.values().stream()
                .sorted(Comparable::compareTo)
                .collect(Collectors.toList());
        for (Candidato c : candidatosOrdenadosIdade) {
            System.out.println(c.getNome() + " " + c.getDataNascimento());
        }
    }

}
