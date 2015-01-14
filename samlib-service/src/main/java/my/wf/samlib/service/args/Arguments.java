package my.wf.samlib.service.args;

import java.util.Properties;

/**
 * Created by Serg on 14.01.2015.
 */
public class Arguments extends Properties {
    private Operation operation;

    public Arguments(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation(){
        return operation;
    }
}
