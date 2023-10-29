import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import eleicao.Eleicao;
import eleicao.LeitorEleicao;
import eleicao.LeitorVotacao;
import entidade.Candidato;
import entidade.Partido;
import relatorios.RelatoriosEleicao;

/**
 * Esta classe contém o ponto de entrada (main) do programa para gerar relatórios eleitorais.
 */
public class Main {
    // Constantes para opções de cargo
    private static final int CARGO_ESTADUAL = 7;
    private static final int CARGO_FEDERAL = 6;
    
    /**
     * Ponto de entrada do programa.
     * 
     * @param args Os argumentos da linha de comando. Espera-se que tenham o formato:
     *             "<opção_de_cargo> <caminho_arquivo_candidatos> <caminho_arquivo_votacao> <data>"
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Usage: java -jar deputados.jar <opção_de_cargo> <caminho_arquivo_candidatos> <caminho_arquivo_votacao> <data>");
            return;
        }

        int cargo = getCargoFromArgs(args[0]);
        if (cargo == 0) {
            System.out.println("Opção de cargo inválida");
            return;
        }

        String caminhoArquivoCandidatos = args[1];
        String caminhoArquivoVotacao = args[2];
        LocalDate data = LocalDate.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));;

        Eleicao eleicao = LeitorEleicao.criarEleicao(caminhoArquivoCandidatos, cargo, data);
        eleicao.processaVotacao(LeitorVotacao.criarVotacao(caminhoArquivoVotacao, cargo));

        RelatoriosEleicao r = new RelatoriosEleicao(eleicao);
        printRelatorios(r);
    }

    private static int getCargoFromArgs(String arg) {
        if ("--estadual".equals(arg)) {
            return CARGO_ESTADUAL;
        } else if ("--federal".equals(arg)) {
            return CARGO_FEDERAL;
        }
        return 0; // Opção de cargo inválida
    }

    private static void printRelatorios(RelatoriosEleicao r) {
        System.out.println(r.numeroDeVagasEleicao());
        System.out.println(r.candidatosEleitos());
        System.out.println(r.candidatosMaisVotados());
        System.out.println(r.candidatosNaoEleitosEleitosMajoritariamente());
        System.out.println(r.candidatosEleitosNaoEleitosMajoritariamente());
        System.out.println(r.votacaoPartidos());
        System.out.println(r.primeiroUltimoColocadosPorPartido());
        System.out.println(r.eleitosPorFaixaEtaria());
        System.out.println(r.eleitosPorGenero());
        System.out.println(r.totalDeVotos());
    }
}
