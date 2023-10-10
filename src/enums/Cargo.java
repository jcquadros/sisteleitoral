package enums;

public enum Cargo {
    DEP_FEDERAL (6), DEP_ESTADUAL (7);

    private final int cargo;

    private Cargo(int cargo) {
        this.cargo = cargo;
    }

    public int getCargo() {
        return cargo;
    }

    public Cargo getCargo(int cargo) {
        if(cargo == 6){
            return Cargo.DEP_FEDERAL;
        }else{
            return Cargo.DEP_ESTADUAL;
        }
    }
}
