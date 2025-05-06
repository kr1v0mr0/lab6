package lab5.Client.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;

/**
 * Команда 'exit'. завершает программу (без сохранения в файл)
 */
public class Exit extends Command {
    private final Consolka consolka;

    public Exit(Consolka consolka) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.consolka = consolka;
    }
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            consolka.println("Неправильное количество аргументов!");
            consolka.println("Использование: '" + getName() + "'");
            return new ExecutionResponse(false, " ");
        }

        consolka.println("Завершение выполнения...");
        return new ExecutionResponse(true, "exit");
    }
}