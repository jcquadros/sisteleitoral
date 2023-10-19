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

public class LeitorEleicao {
    String caminhoCandidatos;
    String caminhoVotos;
    int cargo;

    public LeitorEleicao(String caminhoCandidatos, String caminhoVotos, int cargo) {
        this.caminhoCandidatos = caminhoCandidatos;
        this.caminhoVotos = caminhoVotos;
        this.cargo = cargo;
    }

    public Eleicao criarEleicao()  {
        Map<Integer, Partido> partidos = new TreeMap<Integer, Partido>();
        Map<Integer, Candidato> candidatos = new TreeMap<Integer, Candidato>();

        try {
            FileInputStream f = new FileInputStream(this.caminhoCandidatos);
            Scanner scanner = new Scanner(f, "ISO-8859-1");

            scanner.nextLine(); // ignora primeira linha
            while (scanner.hasNextLine()) {
                String linha[] = scanner.nextLine().split(";");
                // trim e substrim
                linha[13] = linha[13].replace("\"", "");
                linha[27] = linha[27].replace("\"", "");
                linha[28] = linha[28].replace("\"", "");
                linha[30] = linha[30].replace("\"", "");
                linha[16] = linha[16].replace("\"", "");
                linha[18] = linha[18].replace("\"", "");
                linha[68] = linha[68].replace("\"", "");
                linha[45] = linha[45].replace("\"", "");
                linha[67] = linha[67].replace("\"", "");
                linha[56] = linha[56].replace("\"", "");
                linha[42] = linha[42].replace("\"", "");

                if (cargo == Integer.parseInt(linha[13])) {
                    // partido
                    int nPartido = Integer.parseInt(linha[27]);
                    String nomePartido = linha[28];
                    int nFedPartido = Integer.parseInt(linha[30]);

                    // candidato
                    int nCandidato = Integer.parseInt(linha[16]);
                    String nomeCandidato = linha[18];
                    boolean deferido = Integer.parseInt(linha[68]) == 2 || Integer.parseInt(linha[68]) == 16 ? true : false;
                    boolean eleito = Integer.parseInt(linha[56]) == 2 || Integer.parseInt(linha[56]) == 3 ? true : false;
                    Genero genero = Genero.getGenero(Integer.parseInt(linha[45]));
                    TipoDestinoVotos tipoDstVts = TipoDestinoVotos.getTipoDestinoVotos(linha[67]);

                    LocalDate dataNasc = linha[42].equals("") ? null : LocalDate.parse(linha[42], DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    if (!partidos.containsKey(nPartido)) {
                        partidos.put(nPartido, new Partido(nPartido, nomePartido, nFedPartido));
                    }

                    Partido partido = (Partido) partidos.get(nPartido);

                    candidatos.put(nCandidato, new Candidato(nCandidato, nomeCandidato, partido, deferido, eleito, genero, tipoDstVts, dataNasc));
                }

            }
            scanner.close();
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter um numero durante a criação da eleicao");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de candidatos");
        }

        return new Eleicao(candidatos, partidos);
    }

    public Map<Integer, Integer> criarVotacao() {
        TreeMap<Integer, Integer> votos = new TreeMap<>();
        try {
            FileInputStream f = new FileInputStream(this.caminhoVotos);
            Scanner scanner = new Scanner(f, "ISO-8859-1");
            scanner.nextLine(); // ignora primeira linha

            while (scanner.hasNextLine()) {
                String linha[] = scanner.nextLine().split(";");

                if (this.cargo == Integer.parseInt(linha[17].replace("\"", ""))) {

                    int numeroCandidato = Integer.parseInt(linha[19].replace("\"", ""));
                    int quantidadeVotos = Integer.parseInt(linha[21].replace("\"", ""));

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
            System.out.println("Erro ao converter um numero durante a criação da votação");
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo de votos");
        }
        return votos;
    }

}
