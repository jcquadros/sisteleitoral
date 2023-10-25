package relatorios.comparadores;

import entidade.Candidato;
import entidade.Partido;
import java.util.Comparator;
import java.util.Set;

/**
 * Um comparador personalizado para comparar objetos Partido com base no candidato com mais votos nominais.
 */
public class PartidoComparador implements Comparator<Partido> {

    /**
     * Compara dois partidos com base no candidato de cada partido que possui mais votos nominais. Caso de desempate, o partido 
     * com o menor número é retornado.
     *
     * @param o1 O primeiro partido a ser comparado.
     * @param o2 O segundo partido a ser comparado.
     * @return Um valor negativo se o primeiro partido tiver um candidato com mais votos nominais do que o segundo partido,
     *         um valor positivo se o segundo partido tiver um candidato com mais votos nominais do que o primeiro partido. Se ambos 
     *         forem iguais, um valor negativo se o primeiro partido tiver um número menor do que o segundo partido, um valor positivo
     *         se o segundo partido tiver um número menor do que o primeiro partido.
     *         Se um dos partidos não tiver candidatos,será lançada uma exceção.
     * @throws NullPointerException Se o primeiro ou segundo partido for nulo.
     * @throws RuntimeException Se um dos partidos não tiver candidatos.
     */
    @Override
    public int compare(Partido o1, Partido o2) {
        if (o1 == null || o2 == null) {
            throw new NullPointerException("Partido não pode ser nulo");
        }

        Set<Candidato> candidatos1 = o1.getCandidatos();
        Set<Candidato> candidatos2 = o2.getCandidatos();

        if (candidatos1.size() == 0 || candidatos2.size() == 0) {
            throw new RuntimeException("Partido sem candidatos");
        }

        Candidato candidato1 = candidatos1.stream().max(new CandidatoComparador()).get();
        Candidato candidato2 = candidatos2.stream().max(new CandidatoComparador()).get();

        if (candidato1.getVotosNominais() != candidato2.getVotosNominais()) {
            return candidato2.getVotosNominais() - candidato1.getVotosNominais();
        }

        return o1.getNumero() - o2.getNumero();
    }
}
