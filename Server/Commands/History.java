package lab5.Server.Commands;

import lab5.Common.Commands.Command;
import lab5.Common.Tools.Consolka;
import lab5.Common.Tools.ExecutionResponse;
import lab5.Server.Managers.CommandManager;

import javax.print.DocFlavor;

/**
 * Команда 'history'. выводит последние 8 команд (без их аргументов)
 */
public class History extends Command {
    private final Consolka consolka;
    private CommandManager commandManager;

    public History(Consolka consolka) {
        super("history", "Вывыодит историю команд");
        this.consolka = consolka;

    }

    public void setCommandManager(CommandManager commandManager){
        this.commandManager = commandManager;
    }
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            //consolka.println("Неправильное количество аргументов!");
            //consolka.println("Использование: '" + getName() + "'");
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");
        }
        StringBuilder sb = new StringBuilder();
        commandManager.getCommandHistory().forEach(command -> {
            sb.append(" " + command);
        });
        return new ExecutionResponse(true, sb.toString());
    }
}