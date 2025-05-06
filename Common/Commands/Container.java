package lab5.Common.Commands;

import java.io.Serializable;

public class Container implements Serializable {
    private static final long serialVersionUID = 15L;
    private CommanfTypes commandType;
    private String args;

    public Container(CommanfTypes commandType, String args) {
        this.commandType = commandType;
        this.args = args;
    }

    public CommanfTypes getCommandType() {
        return commandType;
    }

    public String getArgs() {
        return args;
    }
}