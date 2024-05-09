package io.metersphere.assertions.exception;

/**
 * @Author: jianxing
 * @CreateTime: 2024-05-08  11:12
 */
public class JSONAssertionException extends RuntimeException {
    private String actualValue;

    public JSONAssertionException() {
        super();
    }

    public JSONAssertionException(String message, String actualValue) {
        super(message);
        this.actualValue = actualValue;
    }

    public JSONAssertionException(String message) {
        super(message);
    }

    public JSONAssertionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JSONAssertionException(Throwable cause) {
        super(cause);
    }

    public String getActualValue() {
        return actualValue;
    }

}
