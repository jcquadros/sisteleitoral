package relatorios.comparadores;

import entidade.Candidato;
import entidade.Partido;
import java.util.Comparator;
import java.util.Set;

public class PartidoComparador implements Comparator<Partido> {

    @Override
    public int compare(Partido o1, Partido o2) {
        if (o1 == null || o2 == null)
            throw new NullPointerException("Partido não pode ser nulo");

        Set<Candidato> candidatos1 = o1.getCandidatos();
        Set<Candidato> candidatos2 = o2.getCandidatos();

        if (candidatos1.size() == 0 || candidatos2.size() == 0)
            throw new RuntimeException("Partido sem candidatos");

        Candidato candidato1 = candidatos1.stream().max(new CandidatoComparador()).get();
        Candidato candidato2 = candidatos2.stream().max(new CandidatoComparador()).get();

        if (candidato1.getVotosNominais() != candidato2.getVotosNominais()){

            
            return candidato2.getVotosNominais() - candidato1.getVotosNominais();
        }

        return o1.getNumero() - o2.getNumero();
    }

}
