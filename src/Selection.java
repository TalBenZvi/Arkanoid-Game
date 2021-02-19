public class Selection<T> {
    private String key;
    private String message;
    private T returnValue;

    public Selection(String key, String message, T returnValue) {
        this.key = key;
        this.message = message;
        this.returnValue = returnValue;
    }

    public String getKey() {
        return this.key;
    }

    public String getMessage() {
        return this.message;
    }

    public T getReturnValue() {
        return this.returnValue;
    }
}
