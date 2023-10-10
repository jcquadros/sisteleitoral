package enums;

public enum Deferido {
    DEFERIDO, INDEFERIDO;

    public Deferido getDeferido(int deferido){
        if(deferido == 2 | deferido == 16){
            return Deferido.DEFERIDO;
        }else{
            return Deferido.INDEFERIDO;
        }
    }

}
