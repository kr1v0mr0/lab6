package lab5.Client.Commands;


import lab5.Common.Commands.Command;
import lab5.Common.Commands.CommanfTypes;
import lab5.Common.Tools.ExecutionResponse;

import java.util.Map;
import java.util.stream.Collectors;

public class Help extends Command {
    private Map<CommanfTypes,String[]> commands;

    public Help( Map<CommanfTypes, String[]> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;
    }

    /**
     * Исполнение команды
     *
     * @param arguments массив с аргументами
     * @return возвращает ответ о выполнении команды
     */
    @Override
    public ExecutionResponse apply(String[] arguments) {
        if (!arguments[1].isEmpty())
            return new ExecutionResponse(false, "Неправильное количество аргументов!\nИспользование: '" + getName() + "'");

        return new ExecutionResponse(true, commands.keySet().stream().map(command -> String.format(" %-35s%-1s%n", commands.get(command)[0], commands.get(command)[1])).collect(Collectors.joining("\n")));
    }
}