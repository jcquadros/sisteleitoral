package enums;

public enum Eleito {
    ELEITO, NAO_ELEITO;

    public Eleito getEleito(int eleito){
        if(eleito == 2 | eleito == 3){
            return Eleito.ELEITO;
        }else{
            return Eleito.NAO_ELEITO;
        }
    }
}
