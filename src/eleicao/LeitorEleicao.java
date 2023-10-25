package eleicao;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import entidade.Candidato;
import entidade.Partido;
import enums.Genero;
import enums.TipoDestinoVotos;
import exceptions.LeituraDeArquivoException;

/**
 * A classe LeitorEleicao é responsável por ler dados relacionados a eleições de arquivos e criar um objeto de eleição.
 */
public class LeitorEleicao {
    
   
    private LeitorEleicao(){}

    
    static public Eleicao criarEleicao(String caminhoCandidatos, int cargo) {
        Map<Integer, Partido> partidos = new TreeMap<Integer, Partido>();
        Map<Integer, Candidato> candidatos = new TreeMap<Integer, Candidato>();

        try{

            FileInputStream f = new FileInputStream(caminhoCandidatos);
            Scanner scanner = new Scanner(f, "ISO-8859-1");
            
            scanner.nextLine(); // ignora primeira linha
            while (scanner.hasNextLine()) {
                String linha[] = scanner.nextLine().split(";");
        
                linha[13] = linha[13].substring(1, linha[13].length() - 1);
                linha[27] = linha[27].substring(1, linha[27].length() - 1);
                linha[28] = linha[28].substring(1, linha[28].length() - 1);
                linha[30] = linha[30].substring(1, linha[30].length() - 1);
                linha[16] = linha[16].substring(1, linha[16].length() - 1);
                linha[18] = linha[18].substring(1, linha[18].length() - 1);
                linha[68] = linha[68].substring(1, linha[68].length() - 1);
                linha[45] = linha[45].substring(1, linha[45].length() - 1);
                linha[67] = linha[67].substring(1, linha[67].length() - 1);
                linha[56] = linha[56].substring(1, linha[56].length() - 1);
                linha[42] = linha[42].substring(1, linha[42].length() - 1);

        
                // partido
                int nPartido = Integer.parseInt(linha[27]);
                String nomePartido = linha[28];
                int nFedPartido = Integer.parseInt(linha[30]);
                
                if (!partidos.containsKey(nPartido)) {
                    partidos.put(nPartido, new Partido(nPartido, nomePartido, nFedPartido));
                }
                
                Partido partido = (Partido) partidos.get(nPartido);

                if (cargo == Integer.parseInt(linha[13])) {
                    
                    // candidato
                    int nCandidato = Integer.parseInt(linha[16]);
                    String nomeCandidato = linha[18];
                    boolean deferido = Integer.parseInt(linha[68]) == 2 || Integer.parseInt(linha[68]) == 16;
                    boolean eleito = Integer.parseInt(linha[56]) == 2 || Integer.parseInt(linha[56]) == 3;
                    Genero genero = Genero.getGenero(Integer.parseInt(linha[45]));
                    TipoDestinoVotos tipoDstVts = TipoDestinoVotos.getTipoDestinoVotos(linha[67]);
                    
                    if(!deferido && tipoDstVts != TipoDestinoVotos.VALIDO_LEGENDA){
                        continue;
                    }
                    
                    LocalDate dataNasc = linha[42].equals("") ? null
                    : LocalDate.parse(linha[42], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    
                    candidatos.put(nCandidato, new Candidato(nCandidato, nomeCandidato, partido, deferido, eleito,
                    genero, tipoDstVts, dataNasc));
                }

            }
            scanner.close();

        }catch(NumberFormatException e){
            throw new LeituraDeArquivoException("Erro ao converter um numero durante a criação da eleição", e);
        }catch(IOException e){
            throw new LeituraDeArquivoException("Erro ao ler arquivo de candidatos", e);
        }
          
            return new Eleicao(candidatos, partidos);
        }
}
