package eleicao;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import entidade.Candidato;
import entidade.Partido;
import util.CSVReader;
import enums.Deferido;
import enums.Genero;
import enums.TipoDestinoVotos;
import enums.Eleito;

public class LeitorEleicao {
    String caminhoCandidatos;
    String caminhoVotos;

    public LeitorEleicao(String caminhoCandidatos, String caminhoVotos) {
        this.caminhoCandidatos = caminhoCandidatos;
        this.caminhoVotos = caminhoVotos;
    }

    public Eleicao versaoCriaEleicao(int cargo) {
        CSVReader csvReader = new CSVReader();
        csvReader.setPath(caminhoCandidatos);
        List<List<String>> linesCandidatos = csvReader.getLines();

        Map<Integer, Partido> partidos = new TreeMap<Integer, Partido>();
        Map<Integer, Candidato> candidatos = new TreeMap<Integer, Candidato>();

        for (int i = 1; i < linesCandidatos.size(); i++) {
            List<String> line = linesCandidatos.get(i);
            try {

                if (cargo == Integer.parseInt(line.get(13))) {

                    int nPartido = Integer.parseInt(line.get(27));
                    String nomePartido = line.get(28);
                    int nFedPartido = Integer.parseInt(line.get(30));

                    if (!partidos.containsKey(nPartido)) {
                        try {
                            Partido partido = new Partido(nPartido, nomePartido, nFedPartido);
                            partidos.put(nPartido, partido);
                        } catch (Exception e) {
                            System.out.println("Erro ao criar um partido " + e.getMessage());
                        }
                    }

                    Partido partido = (Partido) partidos.get(nPartido);

                    int nCandidato = Integer.parseInt(line.get(16));
                    String nomeCandidato = line.get(18);
                    Deferido sttsDefCandidato = Deferido.getDeferido(Integer.parseInt(line.get(68)));
                    Genero genCandidato = Genero.getGenero(Integer.parseInt(line.get(45)));
                    TipoDestinoVotos tipoDstVtCandidato = TipoDestinoVotos.getTipoDestinoVotos(line.get(67));
                    Eleito eleitoCandidato = Eleito.getEleito(Integer.parseInt(line.get(56)));

                    String myDateString = line.get(42);
                    LocalDate dataNascCandidato = myDateString.equals("") ? null
                            : LocalDate.parse(myDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    try {
                        Candidato candidato = new Candidato(nCandidato, nomeCandidato, partido, sttsDefCandidato,
                                eleitoCandidato, genCandidato, tipoDstVtCandidato, dataNascCandidato);

                        candidatos.put(nCandidato, candidato);

                    } catch (Exception e) {
                        System.out.println("Erro ao criar um candidato " + e.getMessage());
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter um numero durante a criação da eleicao");
            }
        }

        return new Eleicao(candidatos, partidos);
    }

    public Eleicao criaEleicao(int cargo) {
        Map<Integer, Partido> partidos = new TreeMap<Integer, Partido>();
        Map<Integer, Candidato> candidatos = new TreeMap<Integer, Candidato>();

        try {
            FileInputStream f = new FileInputStream(this.caminhoCandidatos);
            Scanner scanner = new Scanner(f, "ISO-8859-1");

            scanner.nextLine(); // ignora primeira linha
            while (scanner.hasNextLine()) {
                String linha[] = scanner.nextLine().split(";");
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

                try {
                    if (cargo == Integer.parseInt(linha[13])) {
                        int nPartido = Integer.parseInt(linha[27]);
                        String nomePartido = linha[28];
                        int nFedPartido = Integer.parseInt(linha[30]);

                        int nCandidato = Integer.parseInt(linha[16]);
                        String nomeCandidato = linha[18];
                        Deferido sttsDefCandidato = Deferido.getDeferido(Integer.parseInt(linha[68]));
                        Genero genCandidato = Genero.getGenero(Integer.parseInt(linha[45]));
                        TipoDestinoVotos tipoDstVtCandidato = TipoDestinoVotos.getTipoDestinoVotos(linha[67]);
                        Eleito eleitoCandidato = Eleito.getEleito(Integer.parseInt(linha[56]));
                        String myDateString = linha[42];
                        LocalDate dataNascCandidato = myDateString.equals("") ? null
                                : LocalDate.parse(myDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                        if (!partidos.containsKey(nPartido)) {
                            try {
                                Partido partido = new Partido(nPartido, nomePartido, nFedPartido);
                                partidos.put(nPartido, partido);
                            } catch (Exception e) {
                                System.out.println("Erro ao criar um partido " + e.getMessage());
                                continue;
                            }
                        }

                        Partido partido = (Partido) partidos.get(nPartido);

                        try {
                            Candidato candidato = new Candidato(nCandidato, nomeCandidato, partido, sttsDefCandidato,
                                    eleitoCandidato, genCandidato, tipoDstVtCandidato, dataNascCandidato);
                            candidatos.put(nCandidato, candidato);
                        } catch (Exception e) {
                            System.out.println("Erro ao criar um candidato " + e.getMessage());
                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter um numero durante a criação da eleicao");
                } catch (NullPointerException e) {
                    System.out.println("Erro ao tentar recuperar um valor nulo durante a criação da eleicao");
                }

            }
            scanner.close();
        } catch (Exception e) {

        }

        return new Eleicao(candidatos, partidos);
    }

}
