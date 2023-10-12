
import java.util.Map;

import eleicao.Eleicao;
import eleicao.LeitorEleicao;
import eleicao.LeitorVotos;
import eleicao.RelatoriosEleicao;
import entidade.Candidato;
import entidade.Partido;
import entidade.Voto;

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
        String data = args[3];

        LeitorEleicao leitorEleicao = new LeitorEleicao(caminhoArquivoCandidatos, caminhoArquivoVotacao);
        Eleicao eleicao = leitorEleicao.criaEleicao(cargo);

        Map<Integer, Candidato> candidatos = eleicao.getCandidatos();
        Map<Integer, Partido> partidos = eleicao.getPartidos();

        LeitorVotos leitorVotos = new LeitorVotos(caminhoArquivoVotacao);
        Map<Integer, Voto> votos = leitorVotos.criarVotacao(cargo);
        eleicao.processaVotacao(votos);

        RelatoriosEleicao r = new RelatoriosEleicao();
        System.out.println(r.relatorioNumeroDeVagas(candidatos));
        System.out.println(r.relatorioCandidatosEleitos(candidatos, cargo));
        System.out.println(r.relatorioCandidatosMaisVotados(candidatos));
        System.out.println(r.relatorioCandidatosNaoEleitosEleitosMajoritariamente(candidatos));
        System.out.println(r.relatorioCandidatosEleitosNaoEleitosMajoritariamente(candidatos));
    }
}
