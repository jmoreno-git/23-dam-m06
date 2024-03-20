package cat.proven.utils.exceptions;

/**
 * Enum to code errors.
 *
 * @author ProvenSoft
 */
public enum OpResult {
    DB_NOCONN(101),
    DB_INSERR(102),
    DB_DELERR(103),
    DB_SELERR(104),
    DB_SQLERR(105),
    DB_DRIVER(106),
    //TODO add more codes
    DB_RS2OBJ(107);
    //END TODO

    private final int code;

    private OpResult(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
