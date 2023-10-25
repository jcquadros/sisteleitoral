package eleicao;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import eleicao.exceptions.LeituraDeArquivoException;
import entidade.Candidato;
import entidade.Partido;
import enums.Genero;
import enums.TipoDestinoVotos;

/**
 * A classe LeitorEleicao é responsável por ler os dados de candidatos a partir de um arquivo.
 */
public class LeitorEleicao {

    private static final int INDICE_CARGO = 13; // OK
    private static final int INDICE_N_PARTIDO = 27; // OK
    private static final int INDICE_NOME_PARTIDO = 28; // ok
    private static final int INDICE_N_FED_PARTIDO = 30; // ok
    private static final int INDICE_N_CANDIDATO = 16;
    private static final int INDICE_NOME_CANDIDATO = 18;
    private static final int INDICE_DEFERIDO = 68;
    private static final int INDICE_ELEITO = 56;
    private static final int INDICE_GENERO = 45;
    private static final int INDICE_TIPO_DST_VTS = 67;
    private static final int INDICE_DATA_NASC = 42;

    private LeitorEleicao() {
    }

    /**
     * Cria um objeto Eleicao a partir de um arquivo de candidatos.
     *
     * @param caminhoCandidatos O caminho para o arquivo de candidatos.
     * @param cargo O identificador do cargo da eleição.
     * @return Um objeto Eleicao com candidatos e partidos.
     * @throws LeituraDeArquivoException Se ocorrer um erro durante a leitura do arquivo.
     */
    public static Eleicao criarEleicao(String caminhoCandidatos, int cargo) {
        Map<Integer, Partido> partidos = new TreeMap<>();
        Map<Integer, Candidato> candidatos = new TreeMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(caminhoCandidatos);
             Scanner scanner = new Scanner(fileInputStream, "ISO-8859-1")) {

            // Ignora a primeira linha
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linha[] = scanner.nextLine().split(";");
                processarLinhaCandidato(linha, partidos, candidatos, cargo);
            }
        } catch (IOException e) {
            throw new LeituraDeArquivoException("Erro ao ler arquivo de candidatos", e);
        }

        return new Eleicao(candidatos, partidos);
    }

    private static void processarLinhaCandidato(String[] linha, Map<Integer, Partido> partidos,
                                                 Map<Integer, Candidato> candidatos, int cargo) {
        
        linha[INDICE_CARGO] = linha[INDICE_CARGO].substring(1, linha[INDICE_CARGO].length() - 1);
        linha[INDICE_N_PARTIDO] = linha[INDICE_N_PARTIDO].substring(1, linha[INDICE_N_PARTIDO].length() - 1);
        linha[INDICE_NOME_PARTIDO] = linha[INDICE_NOME_PARTIDO].substring(1, linha[INDICE_NOME_PARTIDO].length() - 1);
        linha[INDICE_N_FED_PARTIDO] = linha[INDICE_N_FED_PARTIDO].substring(1, linha[INDICE_N_FED_PARTIDO].length() - 1);
        linha[INDICE_N_CANDIDATO] = linha[INDICE_N_CANDIDATO].substring(1, linha[INDICE_N_CANDIDATO].length() - 1);
        linha[INDICE_NOME_CANDIDATO] = linha[INDICE_NOME_CANDIDATO].substring(1, linha[INDICE_NOME_CANDIDATO].length() - 1);
        linha[INDICE_DEFERIDO] = linha[INDICE_DEFERIDO].substring(1, linha[INDICE_DEFERIDO].length() - 1);
        linha[INDICE_GENERO] = linha[INDICE_GENERO].substring(1, linha[INDICE_GENERO].length() - 1);
        linha[INDICE_TIPO_DST_VTS] = linha[INDICE_TIPO_DST_VTS].substring(1, linha[INDICE_TIPO_DST_VTS].length() - 1);
        linha[INDICE_ELEITO] = linha[INDICE_ELEITO].substring(1, linha[INDICE_ELEITO].length() - 1);
        linha[INDICE_DATA_NASC] = linha[INDICE_DATA_NASC].substring(1, linha[INDICE_DATA_NASC].length() - 1);

        try{

            int nPartido = Integer.parseInt(linha[INDICE_N_PARTIDO]);
            String nomePartido = linha[INDICE_NOME_PARTIDO];
            int nFedPartido = Integer.parseInt(linha[INDICE_N_FED_PARTIDO]);
            
            if (!partidos.containsKey(nPartido)) {
                partidos.put(nPartido, new Partido(nPartido, nomePartido, nFedPartido));
            }
            
            Partido partido = partidos.get(nPartido);
            
            if (cargo == Integer.parseInt(linha[INDICE_CARGO])) {
        
                boolean deferido = Integer.parseInt(linha[INDICE_DEFERIDO]) == 2 || Integer.parseInt(linha[INDICE_DEFERIDO]) == 16;
                TipoDestinoVotos tipoDstVts = TipoDestinoVotos.getTipoDestinoVotos(linha[INDICE_TIPO_DST_VTS]);
                
                if (!deferido && tipoDstVts != TipoDestinoVotos.VALIDO_LEGENDA) {
                    return;
                }

                int nCandidato = Integer.parseInt(linha[INDICE_N_CANDIDATO]);
                String nomeCandidato = linha[INDICE_NOME_CANDIDATO];
                boolean eleito = Integer.parseInt(linha[INDICE_ELEITO]) == 2 || Integer.parseInt(linha[INDICE_ELEITO]) == 3;
                Genero genero = Genero.getGenero(Integer.parseInt(linha[INDICE_GENERO]));
                LocalDate dataNasc = linha[INDICE_DATA_NASC].equals("") ? null :
                LocalDate.parse(linha[INDICE_DATA_NASC], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                
                candidatos.put(nCandidato, new Candidato(nCandidato, nomeCandidato, partido, deferido, eleito,
                genero, tipoDstVts, dataNasc));
            }

        }catch (NumberFormatException e) {
            throw new LeituraDeArquivoException("Erro ao converter um número durante a criação da eleição", e);
        }

    }
}
