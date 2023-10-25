package eleicao;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import exceptions.LeituraDeArquivoException;

public class LeitorVotacao {
    
    private LeitorVotacao(){}
    
    static public Map<Integer, Integer> criarVotacao(String caminhoVotos, int cargo) {
        TreeMap<Integer, Integer> votos = new TreeMap<>();
        try {
            FileInputStream f = new FileInputStream(caminhoVotos);
            Scanner scanner = new Scanner(f, "ISO-8859-1");
            scanner.nextLine(); // ignora primeira linha

            while (scanner.hasNextLine()) {
                String linha[] = scanner.nextLine().split(";");

                if (cargo == Integer.parseInt(linha[17].substring(1, linha[17].length() - 1))) {

                    int numeroCandidato = Integer.parseInt(linha[19].substring(1, linha[19].length() - 1));
                    int quantidadeVotos = Integer.parseInt(linha[21].substring(1, linha[21].length() - 1));

                    if (numeroCandidato == 95 || numeroCandidato == 96 || numeroCandidato == 97
                            || numeroCandidato == 98) {
                        continue;
                    }

                    if (!votos.containsKey(numeroCandidato)) {
                        votos.put(numeroCandidato, quantidadeVotos);
                    } else {
                        votos.put(numeroCandidato, votos.get(numeroCandidato) + quantidadeVotos);
                    }
                }

            }
            scanner.close();

        } catch (NumberFormatException e) {
            throw new LeituraDeArquivoException("Erro ao converter um numero durante a criação da votação", e);
        } catch (Exception e) {
            throw new LeituraDeArquivoException("Erro ao ler arquivo de votos", e);
        }
        return votos;
    }
}
