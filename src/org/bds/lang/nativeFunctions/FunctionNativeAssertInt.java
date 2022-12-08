package org.bds.lang.nativeFunctions;

import org.bds.lang.Parameters;
import org.bds.lang.type.Type;
import org.bds.lang.type.Types;
import org.bds.run.BdsThread;

public class FunctionNativeAssertInt extends FunctionNativeAssert {

    private static final long serialVersionUID = 2793384407874961408L;


    public FunctionNativeAssertInt() {
        super();
    }

    @Override
    protected void initFunction() {
        functionName = "assert";
        returnType = Types.BOOL;

        String[] argNames = {"msg", "expected", "value"};
        Type[] argTypes = {Types.STRING, Types.INT, Types.INT};
        parameters = Parameters.get(argTypes, argNames);
        addNativeFunction();
    }

    /**
     * Return null if assertion succeeds.
     * If assertion fails, return the assertion failed message to be shown on STDERR when bds exits
     */
    @Override
    protected Object runFunctionNative(BdsThread bdsThread) {
        long expected = bdsThread.getInt("expected");
        long value = bdsThread.getInt("value");
        return expected == value ? null : bdsThread.getString("msg");
    }
}
