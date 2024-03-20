package cat.proven.utils.exceptions;

/**
 * Exception to catch data access layer errors in Store application
 * @author ProvenSoft
 */
public class PersistException extends Exception {

    private int code;
    
    public PersistException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StoreDalException{");
        sb.append("message=").append(getMessage());
        sb.append("code=").append(code);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
