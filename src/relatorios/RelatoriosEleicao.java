package relatorios;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import eleicao.Eleicao;
import entidade.Candidato;
import entidade.Partido;
import enums.Genero;
import relatorios.comparadores.CandidatoComparador;
import relatorios.comparadores.PartidoComparador;

/**
 * Esta classe fornece vários relatórios relacionados a uma eleição.
 */
public class RelatoriosEleicao {
    private List<Candidato> candidatos;
    private List<Partido> partidos;
    private int cargo;
    private LocalDate dataEleicao;

    /*
    * Constrói um objeto RelatoriosEleicao a partir de uma eleição.
    *
    * @param eleicao A eleição
    */
    public RelatoriosEleicao(Eleicao   eleicao) {
        this.candidatos = eleicao.getCandidatos().values().stream().sorted(Comparable::compareTo).collect(Collectors.toList());
        this.partidos = eleicao.getPartidos().values().stream().sorted(Comparable::compareTo).collect(Collectors.toList());
        this.cargo = eleicao.getCargo();
        this.dataEleicao = eleicao.getData();
    }

    /**
     * Retorna o número de vagas para o cargo em disputa.
     *
     * @return O número de vagas
     */
    private int numeroDeVagas() {
        return (int) candidatos.stream().filter(candidato -> candidato.isEleito()).count();
    }

    /**
     * Retorna uma string que descreve o número de vagas na eleição.
     *
     * @return A descrição do número de vagas
     */
    public String numeroDeVagasEleicao() {
        return "Número de vagas: " + numeroDeVagas() + "\n";
    }

    /**
     * Retorna a lista de candidatos eleitos no cargo especificado.
     *
     * @return A lista de candidatos eleitos
     */
    public String candidatosEleitos() {
        String response = this.cargo == 6 ? "Deputados federais eleitos:\n" : "Deputados estaduais eleitos:\n";
        int i = 0;

        List<Candidato> candidatosEleitos = candidatos.stream()
                .filter(candidato -> candidato.isEleito())
                .collect(Collectors.toList());

        for (Candidato c : candidatosEleitos)
            response += (++i) + " - " + c;

        return response;
    }

    /**
     * Retorna a lista de candidatos mais votados, respeitando o número de vagas.
     *
     * @return A lista de candidatos mais votados
     */
    public String candidatosMaisVotados() {
        String response = "Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):\n";
        int i = 0;

        List<Candidato> candidatosOrdenadosVotos = candidatos.stream()
                .limit(numeroDeVagas())
                .collect(Collectors.toList());

        for (Candidato c : candidatosOrdenadosVotos) {
            response += (++i) + " - " + c;
        }

        return response;
    }

    /**
     * Retorna a lista de candidatos que não foram eleitos mas teriam sido em um sistema majoritário.
     *
     * @return A lista de candidatos não eleitos majoritariamente
     */
    public String candidatosNaoEleitosEleitosMajoritariamente() {
        String response = "Teriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n";
        response += "(com sua posição no ranking de mais votados)\n";
        int numeroDeVagas = numeroDeVagas();

        for (int i = 0; i < numeroDeVagas; i++) {
            if (!candidatos.get(i).isEleito()) {
                response += (i + 1) + " - " + candidatos.get(i);
            }
        }

        return response;
    }

    /**
     * Retorna a lista de candidatos eleitos que se beneficiaram do sistema proporcional.
     *
     * @return A lista de candidatos eleitos no sistema proporcional
     */
    public String candidatosEleitosNaoEleitosMajoritariamente() {
        String response = "Eleitos, que se beneficiaram do sistema proporcional:\n";
        response += "(com sua posição no ranking de mais votados)\n";

        for (int i = numeroDeVagas(); i < candidatos.size(); i++) {
            if (candidatos.get(i).isEleito()) {
                response += (i + 1) + " - " + candidatos.get(i);
            }
        }
        return response;
    }

    /**
     * Retorna a votação de cada partido e o número de candidatos eleitos.
     *
     * @return A votação de cada partido e o número de candidatos eleitos
     */
    public String votacaoPartidos() {
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        String response = "Votação dos partidos e número de candidatos eleitos:\n";
        int i = 0;
        for (Partido p : partidos) {
            int candEleitos = 0;
            for (Candidato c : p.getCandidatos()) {
                candEleitos += c.isEleito() ? 1 : 0;
            }

            int totalDeVotosNom = p.getVotosNominais();
            int totalDeVotosLeg = p.getVotosLegenda();
            int totalDeVotos = totalDeVotosNom + totalDeVotosLeg;

            response += (++i) + " - " + p.getSigla() + " - " + p.getNumero() + ", ";
            response += nf.format(totalDeVotos) + (totalDeVotos > 1 ? " votos (" : " voto (");
            response += nf.format(totalDeVotosNom) + (totalDeVotosNom > 1 ? " nominais e " : " nominal e ");
            response += nf.format(totalDeVotosLeg) + (totalDeVotosLeg > 1 ? " de legenda), " : " de legenda), ");
            response += candEleitos + (candEleitos > 1 ? " candidatos eleitos\n" : " candidato eleito\n");
        }
        return response;
    }

    /**
     * Retorna os primeiros e últimos colocados de cada partido.
     *
     * @return Os primeiros e últimos colocados de cada partido
     */
    public String primeiroUltimoColocadosPorPartido() {
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        String response = "Primeiro e último colocados de cada partido:\n";
        int i = 0;

        List<Partido> partidosOrdenados = partidos.stream()
                .filter(partido -> partido.getCandidatos().size() > 0)
                .sorted(new PartidoComparador())
                .collect(Collectors.toList());

        for (Partido p : partidosOrdenados) {
            List<Candidato> candidatosPartido = p.getCandidatos().stream()
                    .filter(candidato -> candidato.isDeferido()) 
                    .sorted(new CandidatoComparador())
                    .collect(Collectors.toList());

            if (candidatosPartido.size() == 0)
                continue;

            response += (++i) + " - " + p.getSigla() + " - " + p.getNumero() + ", ";
            Candidato c1 = (Candidato) candidatosPartido.stream().max(new CandidatoComparador()).get();
            response += c1.getNome() + " (" + c1.getNumero() + ", " + nf.format(c1.getVotosNominais())
                    + (c1.getVotosNominais() > 1 ? " votos)" : " voto)");

            Candidato c2 = (Candidato) candidatosPartido.stream().min(new CandidatoComparador()).get();
            response += " / " + c2.getNome() + " (" + c2.getNumero() + ", " + nf.format(c2.getVotosNominais())
                    + (c2.getVotosNominais() > 1 ? " votos)\n" : " voto)\n");
        }
        return response;
    }

    /**
     * Retorna a distribuição dos candidatos eleitos por faixa etária.
     *
     * @return A distribuição dos candidatos eleitos por faixa etária
     */
    public String eleitosPorFaixaEtaria() {
        NumberFormat nf = NumberFormat.getPercentInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        List<Candidato> candidatosEleitos = candidatos.stream()
                .filter(candidato -> candidato.isEleito())
                .collect(Collectors.toList());

        int eleitosAte30 = 0;
        int eleitos30a40 = 0;
        int eleitos40a50 = 0;
        int eleitos50a60 = 0;
        int eleitos60mais = 0;
        double totalEleitos = candidatosEleitos.size();

        for (Candidato c : candidatosEleitos) {
            long idade = ChronoUnit.YEARS.between(c.getDataNascimento(), this.dataEleicao);

            if (idade < 30) {
                eleitosAte30++;
            } else if (idade < 40) {
                eleitos30a40++;
            } else if (idade < 50) {
                eleitos40a50++;
            } else if (idade < 60) {
                eleitos50a60++;
            } else {
                eleitos60mais++;
            }
        }

        String response = "Eleitos, por faixa etária (na data da eleição):\n";
        response += "      Idade < 30: " + eleitosAte30 + " ("
                + nf.format(eleitosAte30 / totalEleitos) + ")\n";
        response += "30 <= Idade < 40: " + eleitos30a40 + " ("
                + nf.format(eleitos30a40 / totalEleitos) + ")\n";
        response += "40 <= Idade < 50: " + eleitos40a50 + " ("
                + nf.format(eleitos40a50 / totalEleitos) + ")\n";
        response += "50 <= Idade < 60: " + eleitos50a60 + " ("
                + nf.format(eleitos50a60 / totalEleitos) + ")\n";
        response += "60 <= Idade     : " + eleitos60mais + " ("
                + nf.format(eleitos60mais / totalEleitos) + ")\n";

        return response;
    }

    /**
     * Retorna a distribuição dos candidatos eleitos por gênero.
     *
     * @return A distribuição dos candidatos eleitos por gênero
     */
    public String eleitosPorGenero() {
        
        NumberFormat nf = NumberFormat.getPercentInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);

        List<Candidato> candidatosEleitos = candidatos.stream()
                .filter(candidato -> candidato.isEleito())
                .collect(Collectors.toList());

        int eleitosFeminino = 0;
        int eleitosMasculino = 0;
        double totalEleitos = candidatosEleitos.size();

        for (Candidato c : candidatosEleitos) {
            if (c.getGenero() == Genero.FEMININO) {
                eleitosFeminino++;
            } else {
                eleitosMasculino++;
            }
        }

        String response = "Eleitos, por gênero:\n";
        response += "Feminino:  " + eleitosFeminino + " ("
                + nf.format(eleitosFeminino / totalEleitos) + ")\n";
        response += "Masculino: " + eleitosMasculino + " ("
                + nf.format(eleitosMasculino / totalEleitos) + ")\n";
        return response;
    }

    /**
     * Retorna o total de votos válidos, votos nominais e votos de legenda na eleição.
     *
     * @return O total de votos válidos, votos nominais e votos de legenda
     */
    public String totalDeVotos() {
        
        NumberFormat percentNf = NumberFormat.getPercentInstance(new Locale("pt", "BR"));
        NumberFormat intNf = NumberFormat.getInstance(new Locale("pt", "BR"));
        percentNf.setMinimumFractionDigits(2);

        double totalDeVotos = 0;
        int totalDeVotosNominais = 0;
        int totalDeVotosLegenda = 0;
        for (Partido p : partidos) {
            totalDeVotosNominais += p.getVotosNominais();
            totalDeVotosLegenda += p.getVotosLegenda();
        }

        totalDeVotos = totalDeVotosNominais + totalDeVotosLegenda;

        String response = "Total de votos válidos:    " + intNf.format(totalDeVotos) + "\n";
        response += "Total de votos nominais:   " + intNf.format(totalDeVotosNominais) + " ("
                + percentNf.format(totalDeVotosNominais / totalDeVotos) + ")\n";
        response += "Total de votos de legenda: " + intNf.format(totalDeVotosLegenda) + " ("
                + percentNf.format(totalDeVotosLegenda / totalDeVotos) + ")\n";

        return response;
    }

}
