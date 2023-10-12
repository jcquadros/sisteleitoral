package entidade;

public class Voto {
    private int numeroCandidato = 0;
    private int quantidadeVotos = 0;

    public Voto(int numeroCandidato) {
        this.numeroCandidato = numeroCandidato;
    }

    public void addVoto(int quantidadeVotos) {
        this.quantidadeVotos += quantidadeVotos;
    }

    public int getQuantidadeVotos() {
        return quantidadeVotos;
    }

    public int getNumeroCandidato() {
        return numeroCandidato;
    }
}
