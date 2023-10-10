import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        String cargo = args[0];
        String caminhoArquivoCandidatos = args[1];
        String caminhoArquivoVotacao = args[2];
        String data = args[3];

        CSVReader csvReaderCandidatos = new CSVReader(caminhoArquivoCandidatos);
        List<String[]> csvCandidatos = csvReaderCandidatos.getLines();

        for (String[] linha : csvCandidatos) {
            System.out.println(linha[67]);
        }
        // CD_CARGO | 13
        // CD_SITUACAO_CANDIDATO_TOT | 68
        // NR_CANDIDATO | 16
        // NM_URNA_CANDIDATO | 18
        // NR_PARTIDO | 27
        // SG_PARTIDO | 28
        // NR_FEDERACAO | 30
        // DT_NASCIMENTO | 42
        // CD_SIT_TOT_TURNO | 56
        // CD_GENERO | 45
        // NM_TIPO_DESTINACAO_VOTOS | 67

    }
}
