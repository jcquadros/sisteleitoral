package relatorios.comparadores;

import entidade.Candidato;
import java.util.Comparator;

public class CandidatoComparador implements Comparator<Candidato> {

    @Override
    public int compare(Candidato o1, Candidato o2) {
        if (o1.getVotosNominais() != o2.getVotosNominais())
            return o2.getVotosNominais() - o1.getVotosNominais();

        return o1.getPartido().getNumero() - o2.getPartido().getNumero();
    }
}
