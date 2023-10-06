package parser;

import model.Partido;

public class PartidoParser {

    public Partido parser(String numero, String sigla, String federacao) {
        return new Partido(Integer.parseInt(numero), sigla, Integer.parseInt(federacao));
    }

}
