import java.util.List;

import util.CSVReader;

public class Main {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println(
                    "Usage: java -jar deputados.jar <opção_de_cargo> <caminho_arquivo_candidatos> <caminho_arquivo_votacao> <data>");
            return;
        }
        String cargo = args[0];
        String caminhoArquivoCandidatos = args[1];
        String caminhoArquivoVotacao = args[2];
        String data = args[3];

        CSVReader csvReaderCandidatos = new CSVReader(caminhoArquivoCandidatos);
        List<String[]> csvCandidatos = csvReaderCandidatos.getLines();

        for (String[] line : csvCandidatos) {
            for (String column : line) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
        System.out.println(csvCandidatos.size());

        CSVReader csvReaderVotacao = new CSVReader(caminhoArquivoVotacao);
        List<String[]> csvVotacao = csvReaderVotacao.getLines();

        // for (String[] line : csvVotacao) {
        // for (String column : line) {
        // System.out.print(column + " ");
        // }
        // System.out.println();
        // }
        System.out.println(csvVotacao.size());

    }
}
