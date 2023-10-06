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
        int i = 0;

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

        System.out.println(csvCandidatos.get(0)[13]);
        System.out.println(csvCandidatos.get(0)[68]);
        System.out.println(csvCandidatos.get(0)[16]);
        System.out.println(csvCandidatos.get(0)[18]);
        System.out.println(csvCandidatos.get(0)[27]);
        System.out.println(csvCandidatos.get(0)[28]);
        System.out.println(csvCandidatos.get(0)[30]);
        System.out.println(csvCandidatos.get(0)[42]);
        System.out.println(csvCandidatos.get(0)[56]);
        System.out.println(csvCandidatos.get(0)[45]);
        System.out.println(csvCandidatos.get(0)[67]);

        for (String[] string : csvCandidatos) {
            System.out.printf("%-10s %-10s %-10s %-30s %-10s %-15s %-15s %-10s %-10s %-10s %-10s\n", string[13],
                    string[68], string[16], string[18], string[27], string[28], string[30], string[42], string[56],
                    string[45], string[67]);
        }

    }
}
