package relatorios.comparadores;

import entidade.Candidato;
import java.util.Comparator;

public class CandidatoComparador implements Comparator<Candidato> {

    @Override
    public int compare(Candidato o1, Candidato o2) {
        if (o1.getVotosNominais() != o2.getVotosNominais()) {
            return o1.getVotosNominais() - o2.getVotosNominais();
        } else if (o1.getDataNascimento() != null && o2.getDataNascimento() != null) {
            return o2.getDataNascimento().compareTo(o1.getDataNascimento());
        }
        return 0;
    }
}
