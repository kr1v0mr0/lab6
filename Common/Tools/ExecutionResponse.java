package lab5.Common.Tools;

import java.io.Serializable;

public record ExecutionResponse  (boolean exitCode, String message) implements Serializable{
    private static final long serialVersionUID = 1L;

}
