package enums;

public enum Genero {
    FEMININO (4), MASCULINO (2);

    private final int genero;

    private Genero(int genero) {
        this.genero = genero;
    }

    public int getGenero() {
        return genero;
    }

    public Genero getGenero(int genero) {
        if(genero == 4){
            return Genero.FEMININO;
        }else{
            return Genero.MASCULINO;
        }
    }

}


