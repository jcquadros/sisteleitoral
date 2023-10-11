package eleicao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import entidade.Candidato;
import entidade.Partido;
import util.CSVReader;
import enums.Deferido;
import enums.Genero;
import enums.TipoDestinoVotos;
import exceptions.CandidatoException;
import exceptions.PartidoException;
import enums.Eleito;

public class LeitorEleicao {
    String caminhoCandidatos;
    String caminhoVotos;

    public LeitorEleicao(String caminhoCandidatos, String caminhoVotos) {
        this.caminhoCandidatos = caminhoCandidatos;
        this.caminhoVotos = caminhoVotos;
    }

    public Eleicao criaEleicao(int cargo) {
        CSVReader csvReader = new CSVReader();
        csvReader.setPath(caminhoCandidatos);
        List<List<String>> linesCandidatos = csvReader.getLines();

        Map partidos = new TreeMap<Integer, Partido>();
        Map candidatos = new TreeMap<Integer, Candidato>();

        for (int i = 1; i < linesCandidatos.size(); i++) {
            List<String> line = linesCandidatos.get(i);
            try {

                if (cargo == Integer.parseInt(line.get(13))) {

                    int numeroPartido = Integer.parseInt(line.get(27));
                    String nomePartido = line.get(28);
                    int numeroFederacaoPartido = Integer.parseInt(line.get(30));

                    if (!partidos.containsKey(numeroPartido)) {
                        try {
                            Partido partido = new Partido(numeroPartido, nomePartido, numeroFederacaoPartido);
                            partidos.put(numeroPartido, partido);
                        } catch (Exception e) {
                            new PartidoException("Erro ao criar um partido");
                            System.exit(1);
                        }
                    }

                    Partido partido = (Partido) partidos.get(numeroPartido);

                    int numeroCandidato = Integer.parseInt(line.get(16));
                    String nomeCandidato = line.get(18);
                    Deferido statusDeferidoCandidato = Deferido.getDeferido(Integer.parseInt(line.get(68)));
                    Genero generoCandidato = Genero.getGenero(Integer.parseInt(line.get(45)));
                    TipoDestinoVotos tipoDestinoVotosCandidato = TipoDestinoVotos.getTipoDestinoVotos(line.get(67));
                    Eleito eleitoCandidato = Eleito.getEleito(Integer.parseInt(line.get(56)));

                    String myDateString = line.get(42);
                    LocalDate dataNascimentoCandidato = myDateString.equals("") ? null
                            : LocalDate.parse(myDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    try {
                        Candidato candidato = new Candidato(numeroCandidato, nomeCandidato, partido,
                                statusDeferidoCandidato,
                                eleitoCandidato, generoCandidato, tipoDestinoVotosCandidato,
                                dataNascimentoCandidato);

                        candidatos.put(numeroCandidato, candidato);

                    } catch (Exception e) {
                        new CandidatoException("Erro ao criar um candidato");
                        System.exit(1);
                    }

                }
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter um numero durante a criação da eleicao");
                System.exit(1);

            }
        }

        return new Eleicao(candidatos, partidos);
    }

}
