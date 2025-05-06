package lab5.Common.Commands;

import lab5.Common.Tools.ExecutionResponse;
/**
 * Интерфейс для всех выполняемых команд.
 */
public interface Executable {
    /**
     * Выполнить что-либо.
     *
     * @param arguments Аргумент для выполнения
     * @return результат выполнения
     */
    ExecutionResponse apply(String[] arguments);
}