package io.metersphere.jmeter.mock.util.regex;


import io.metersphere.jmeter.mock.exception.RegexpIllegalException;
import io.metersphere.jmeter.mock.exception.TypeNotMatchException;
import io.metersphere.jmeter.mock.exception.UninitializedException;

public interface Node {

    String getExpression();

    String random() throws UninitializedException, RegexpIllegalException;

    boolean test();

    void init() throws RegexpIllegalException, TypeNotMatchException;

    boolean isInitialized();
}
