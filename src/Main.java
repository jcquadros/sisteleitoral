
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import eleicao.Eleicao;
import eleicao.LeitorEleicao;
import entidade.Candidato;
import entidade.Partido;
import relatorios.RelatoriosEleicao;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println(
                    "Usage: java -jar deputados.jar <opção_de_cargo> <caminho_arquivo_candidatos> <caminho_arquivo_votacao> <data>");
            return;
        }

        int cargo;
        if (args[0].equals("--estadual")) {
            cargo = 7;
        } else if (args[0].equals("--federal")) {
            cargo = 6;
        } else {
            System.out.println("Opção de cargo inválida");
            return;
        }

        String caminhoArquivoCandidatos = args[1];
        String caminhoArquivoVotacao = args[2];
        LocalDate data = LocalDate.parse(args[3], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        LeitorEleicao leitorEleicao = new LeitorEleicao(caminhoArquivoCandidatos, caminhoArquivoVotacao, cargo);
        Eleicao eleicao = leitorEleicao.criarEleicao();
        
        Map<Integer, Candidato> candidatos = eleicao.getCandidatos();
        Map<Integer, Partido> partidos = eleicao.getPartidos();
        

        eleicao.processaVotacao(leitorEleicao.criarVotacao());
        int i = 0;
        for (Partido p : partidos.values() ) {
            System.out.println((++i) + " - " + p);
        }

        System.out.println(candidatos.get(900));

        // RelatoriosEleicao r = new RelatoriosEleicao(candidatos, partidos, cargo, data);
        // System.out.println(r.numeroDeVagasEleicao());
        // System.out.println(r.candidatosEleitos());
        // System.out.println(r.candidatosMaisVotados());
        // System.out.println(r.candidatosNaoEleitosEleitosMajoritariamente());
        // System.out.println(r.candidatosEleitosNaoEleitosMajoritariamente());
        // System.out.println(r.votacaoPartidos());
        // System.out.println(r.primeiroUltimoColocadosPorPartido());
        // System.out.println(r.eleitosPorFaixaEtaria());
        // System.out.println(r.eleitosPorGenero());
        // System.out.println(r.totalDeVotos());

    }
}
