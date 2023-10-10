package eleicao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import entidade.Candidato;
import entidade.Partido;
import util.CSVReader;
// o leitor de eleicao recebera o caminho para dois arquivos csv de candidatos e de votos e se encarregara de redirecionar a leitura para csvReader e criar uma eleicao que contem candidatos e partidos retornando essa eleicao pronta
public class LeitorEleicao {
    String caminhoCandidatos;
    String caminhoVotos;

    public LeitorEleicao(String caminhoCandidatos, String caminhoVotos) {
        this.caminhoCandidatos = caminhoCandidatos;
        this.caminhoVotos = caminhoVotos;
    }

    public Eleicao criaEleicao(){
        CSVReader csvReader = new CSVReader();
        csvReader.setPath(caminhoCandidatos);
        List<String[]> linesCandidatos = csvReader.getLines();
        csvReader.setPath(this.caminhoVotos);
        List<String[]> linesPartidos = csvReader.getLines();

        
        Map partidos = new TreeMap<Integer, Partido>();
        Map candidatos = new TreeMap<Integer, Candidato>();

        // TODO: implementar leitura de candidatos e partidos
        return new Eleicao(partidos, candidatos);
    }

    
}
