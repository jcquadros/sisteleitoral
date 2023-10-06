package parser;

import model.Candidato;
import model.Partido;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CandidatoParser {

    public Candidato parser(String numero, String nome, Partido partido, String dtNascimento, String eleito,
            String tipoDestinoDosVotos) {
        LocalDate data = LocalDate.parse(dtNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        boolean eleitoBool = Integer.parseInt(eleito) == 2 || Integer.parseInt(eleito) == 3;
        return new Candidato(Integer.parseInt(numero), nome, partido, data, eleitoBool, Integer.parseInt(eleito),
                tipoDestinoDosVotos);
    }
}
