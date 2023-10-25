package eleicao;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import eleicao.exceptions.LeituraDeArquivoException;

/**
 * A classe LeitorVotacao é responsável por ler os dados de votação a partir de um arquivo.
 */
public class LeitorVotacao {

    private static final int INDICE_CARGO = 17;
    private static final int INDICE_NUMERO_CANDIDATO = 19;
    private static final int INDICE_QUANTIDADE_VOTOS = 21;
    private static final int NUMERO_MINIMO_CANDIDATO = 95;
    private static final int NUMERO_MAXIMO_CANDIDATO = 98;

    private LeitorVotacao() {
    }

    /**
     * Cria um mapa de votação a partir de um arquivo.
     *
     * @param caminhoVotos O caminho para o arquivo de votação.
     * @param cargo O identificador do cargo da eleição.
     * @return Um mapa de números de candidatos e suas contagens de votos correspondentes.
     * @throws LeituraDeArquivoException Se ocorrer um erro ao ler o arquivo.
     */
    public static Map<Integer, Integer> criarVotacao(String caminhoVotos, int cargo) {
        TreeMap<Integer, Integer> votos = new TreeMap<>();

        try (FileInputStream f = new FileInputStream(caminhoVotos); Scanner scanner = new Scanner(f, "ISO-8859-1")) {
            scanner.nextLine(); // ignora a primeira linha

            while (scanner.hasNextLine()) {
                String linha[] = scanner.nextLine().split(";");

                int cargoLido = Integer.parseInt(linha[INDICE_CARGO].substring(1, linha[INDICE_CARGO].length() - 1));

                if (cargo == cargoLido) {
                    int numeroCandidato = Integer.parseInt(linha[INDICE_NUMERO_CANDIDATO].substring(1, linha[INDICE_NUMERO_CANDIDATO].length() - 1));
                    int quantidadeVotos = Integer.parseInt(linha[INDICE_QUANTIDADE_VOTOS].substring(1, linha[INDICE_QUANTIDADE_VOTOS].length() - 1));

                    if (numeroCandidato >= NUMERO_MINIMO_CANDIDATO && numeroCandidato <= NUMERO_MAXIMO_CANDIDATO) {
                        continue;
                    }

                    votos.put(numeroCandidato, votos.getOrDefault(numeroCandidato, 0) + quantidadeVotos);
                }
            }
        } catch (NumberFormatException e) {
            throw new LeituraDeArquivoException("Erro ao converter um número durante a criação da votação", e);
        } catch (Exception e) {
            throw new LeituraDeArquivoException("Erro ao ler arquivo de votos", e);
        }

        return votos;
    }
}
