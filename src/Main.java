import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eleicao.Eleicao;
import eleicao.LeitorEleicao;
import entidade.Candidato;
import entidade.Partido;
import util.CSVReader;


public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println(
                    "Usage: java -jar deputados.jar <opção_de_cargo> <caminho_arquivo_candidatos> <caminho_arquivo_votacao> <data>");
            return;
        }

        int cargo;
        if(args[0].equals("--estadual")){
            cargo = 7;
        }else if (args[0].equals("--federal")){
            cargo = 6;
        }else{
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

        for (Candidato candidato : candidatos.values()) {
            System.out.println(candidato);
        }

        for (Partido partido : partidos.values()) {
           System.out.println(partido);
        }

    }
}
