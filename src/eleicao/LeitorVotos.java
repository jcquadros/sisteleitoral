package eleicao;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import entidade.Voto;
import util.CSVReader;

public class LeitorVotos {
    private String caminhoVotos;

    public LeitorVotos(String caminhoVotos) {
        this.caminhoVotos = caminhoVotos;
    }

    public Map<Integer, Voto> versaoIniciaVotacao(int cargo) {
        CSVReader csvReader = new CSVReader();
        csvReader.setPath(caminhoVotos);
        List<List<String>> linesVotos = csvReader.getLines();
        TreeMap<Integer, Voto> votos = new TreeMap<>();

        for (int i = 1; i < linesVotos.size(); i++) {

            List<String> line = linesVotos.get(i);
            if (cargo == Integer.parseInt(line.get(17))) {
                int numeroCandidato;
                int quantidadeVotos;

                try {
                    numeroCandidato = Integer.parseInt(line.get(19));
                    quantidadeVotos = Integer.parseInt(line.get(21));

                } catch (NumberFormatException e) {
                    System.out.println("Warning : numero do Candidato e quantidade de Votos nao convertida com sucesso "
                            + e.getMessage());
                    continue;
                }

                if (numeroCandidato == 95 || numeroCandidato == 96 || numeroCandidato == 97 || numeroCandidato == 98) {
                    continue;
                }

                if (!votos.containsKey(numeroCandidato)) {
                    Voto voto = new Voto(numeroCandidato);
                    votos.put(numeroCandidato, voto);
                }

                Voto voto = votos.get(numeroCandidato);
                voto.addVoto(quantidadeVotos);
            }
        }

        return votos;
    }

    public Map<Integer, Voto> criarVotacao(int cargo) {
        TreeMap<Integer, Voto> votos = new TreeMap<>();
        try {
            FileInputStream f = new FileInputStream(this.caminhoVotos);
            Scanner scanner = new Scanner(f, "ISO-8859-1");
            scanner.nextLine(); // ignora primeira linha
            while (scanner.hasNextLine()) {
                String linha[] = scanner.nextLine().split(";");
                if (cargo == Integer.parseInt(linha[17].replace("\"", ""))) {
                    int numeroCandidato;
                    int quantidadeVotos;

                    try {
                        numeroCandidato = Integer.parseInt(linha[19].replace("\"", ""));
                        quantidadeVotos = Integer.parseInt(linha[21].replace("\"", ""));

                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Warning : numero do Candidato e quantidade de Votos nao convertida com sucesso "
                                        + e.getMessage());
                        continue;
                    }

                    if (numeroCandidato == 95 || numeroCandidato == 96 || numeroCandidato == 97
                            || numeroCandidato == 98) {
                        continue;
                    }

                    if (!votos.containsKey(numeroCandidato)) {
                        Voto voto = new Voto(numeroCandidato);
                        votos.put(numeroCandidato, voto);
                    }

                    Voto voto = votos.get(numeroCandidato);
                    voto.addVoto(quantidadeVotos);
                }

            }
            scanner.close();

        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo de votos");
        }
        return votos;
    }
}
