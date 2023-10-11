package enums;

public enum Cargo {
    DEP_FEDERAL(6), DEP_ESTADUAL(7);

    private final int cargo;

    private Cargo(int cargo) {
        this.cargo = cargo;
    }

    public int getCargo() {
        return cargo;
    }

    public static Cargo getCargo(int cargo) {
        if (cargo == 6) {
            return Cargo.DEP_FEDERAL;
        } else {
            return Cargo.DEP_ESTADUAL;
        }
    }

    public static Cargo getCargo(String cargo) {
        if (cargo.equals("--federal")) {
            return Cargo.DEP_FEDERAL;
        } else if (cargo.equals("--estadual")) {
            return Cargo.DEP_ESTADUAL;
        } else {
            return null; // exception
        }
    }
}
