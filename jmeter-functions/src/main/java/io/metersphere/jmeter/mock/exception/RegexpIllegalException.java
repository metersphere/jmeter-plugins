package io.metersphere.jmeter.mock.exception;

public class RegexpIllegalException extends Exception {
    public RegexpIllegalException(String message) {
        super(message);
    }

    public RegexpIllegalException(String regexp, int index) {
        super(String.format("Invalid regular expression: %s, Index: %d", regexp, index));
    }
}
