package relatorios.comparadores;

import entidade.Candidato;

import java.util.Comparator;

/**
 * Um comparador personalizado para comparar objetos Candidato com base em votos nominais e data de nascimento.
 */
public class CandidatoComparador implements Comparator<Candidato> {

    /**
     * Compara dois candidatos com base em votos nominais e data de nascimento.
     *
     * @param o1 O primeiro candidato a ser comparado.
     * @param o2 O segundo candidato a ser comparado.
     * @return Um valor negativo se o primeiro candidato tiver menos votos nominais do que o segundo candidato,
     *         um valor positivo se o primeiro candidato tiver mais votos nominais do que o segundo candidato. 
     *         Se a data de nascimento estiver disponível para ambos, a comparação secundária será feita com base 
     *         na data de nascimento caso contrario retornará zero.
     * @throws NullPointerException Se o primeiro ou segundo candidato for nulo.
     */
    @Override
    public int compare(Candidato o1, Candidato o2) {
        if (o1 == null || o2 == null) {
            throw new NullPointerException("Candidato não pode ser nulo");
        }

        if (o1.getVotosNominais() != o2.getVotosNominais()) {
            return o1.getVotosNominais() - o2.getVotosNominais();
        } else if (o1.getDataNascimento() != null && o2.getDataNascimento() != null) {
            return o2.getDataNascimento().compareTo(o1.getDataNascimento());
        }
        return 0;
    }
}
