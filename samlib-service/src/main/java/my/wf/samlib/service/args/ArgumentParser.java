package my.wf.samlib.service.args;

/**
 * Created by Serg on 14.01.2015.
 */
public class ArgumentParser {
    public static void parseAndRun(String[] args){
        getOperation(args).getExecutive(args).run();
    }

    private static Operation getOperation(String[] args){
        if(null == args || 0 == args.length){
            return Operation.HELP;
        }
        switch (args[0]){
            case "run": return Operation.RUN;
            case "backup": return Operation.BACKUP;
            case "restore": return Operation.RESTORE;
            default:return Operation.HELP;
        }
    }

}
