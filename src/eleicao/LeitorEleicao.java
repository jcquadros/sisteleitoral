package eleicao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import entidade.Candidato;
import entidade.Partido;
import util.CSVReader;
import enums.Cargo;
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

    public Eleicao criaEleicao() {
        CSVReader csvReader = new CSVReader();
        csvReader.setPath(caminhoCandidatos);
        List<String[]> linesCandidatos = csvReader.getLines();
        // csvReader.setPath(this.caminhoVotos);
        // List<String[]> linesPartidos = csvReader.getLines();

        Map partidos = new TreeMap<Integer, Partido>();
        Map candidatos = new TreeMap<Integer, Candidato>();

        for (int i = 1; i < linesCandidatos.size(); i++) {
            String[] line = linesCandidatos.get(i);
            int cargoCandidato = Integer.parseInt(line[13].replace("\"", ""));

            // if (cargo.getCargo() == cargoCandidato) {
            int numeroPartido = Integer.parseInt(line[27].replace("\"", ""));
            String nomePartido = line[28].replace("\"", "");
            int numeroFederacaoPartido = Integer.parseInt(line[30].replace("\"", ""));

            if (!partidos.containsKey(numeroPartido)) {
                Partido partido = new Partido(numeroPartido, nomePartido,
                        numeroFederacaoPartido);
                partidos.put(numeroPartido, partido);
            }

            Partido partido = (Partido) partidos.get(numeroPartido);

            int numeroCandidato = Integer.parseInt(line[16].replace("\"", ""));
            String nomeCandidato = line[18].replace("\"", "");
            Deferido statusDeferidoCandidato = Deferido.getDeferido(Integer.parseInt(line[68].replace("\"", "")));
            Genero generoCandidato = Genero.getGenero(Integer.parseInt(line[45].replace("\"", "")));
            TipoDestinoVotos tipoDestinoVotosCandidato = TipoDestinoVotos
                    .getTipoDestinoVotos(line[67].replace("\"", ""));
            Eleito eleitoCandidato = Eleito.getEleito(Integer.parseInt(line[56].replace("\"", "")));
            LocalDate dataNascimentoCandidato = LocalDate.parse(line[42].replace("\"", ""),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Candidato candidato = new Candidato(numeroCandidato, nomeCandidato, partido,
                    statusDeferidoCandidato,
                    eleitoCandidato, generoCandidato, tipoDestinoVotosCandidato,
                    dataNascimentoCandidato);
            candidatos.put(numeroCandidato, candidato);
            // }

        }

        return new Eleicao(candidatos, partidos);
    }

}
